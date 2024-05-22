package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserLoginDTO;
import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.entities.RefreshToken;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.InvalidCredentialsException;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.exception.UserAlreadyExistsException;
import io.bookbar.bookbarbackend.mapper.UserMapper;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.security.JwtUtils;
import io.bookbar.bookbarbackend.service.AuthService;
import io.bookbar.bookbarbackend.service.impl.RefreshTokenServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RefreshTokenServiceImpl refreshTokenService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                           RefreshTokenServiceImpl refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.refreshTokenService = refreshTokenService;

    }

    public User register(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByUsername(userRegistrationDTO.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("Username is already taken");
        }
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email is already taken");
        }
        User user = UserMapper.toUser(userRegistrationDTO, passwordEncoder);
        return userRepository.save(user);
    }

    public Map<String, String> authenticate(UserLoginDTO userLoginDTO) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String token = jwtUtils.generateToken(user);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

            Map<String, String> tokens = new HashMap<>();
            tokens.put("token", token);
            tokens.put("refreshToken", refreshToken.getToken());
            return tokens;
        } catch (AuthenticationException e) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    public String refreshToken(String refreshToken) {
        RefreshToken token = refreshTokenService.findByToken(refreshToken)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
        refreshTokenService.verifyExpiration(token);

        User user = token.getUser();
        return jwtUtils.generateToken(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}

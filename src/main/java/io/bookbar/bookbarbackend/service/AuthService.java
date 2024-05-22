package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserLoginDTO;
import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.InvalidCredentialsException;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.exception.UserAlreadyExistsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Map;

public interface AuthService {
    User register(UserRegistrationDTO userRegistrationDTO) throws UserAlreadyExistsException;

    Map<String, String> authenticate(UserLoginDTO userLoginDTO) throws InvalidCredentialsException;

    String refreshToken(String refreshToken) throws ResourceNotFoundException;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.SignUpDto;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.enums.UserRole;
import io.bookbar.bookbarbackend.exception.InvalidJwtException;
import io.bookbar.bookbarbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    public void signUp(SignUpDto data) throws InvalidJwtException {

        if (repository.findByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserRole role = UserRole.USER;
        User newUser = new User(data.username(), data.email(), encryptedPassword, role);

        repository.save(newUser);
    }
}

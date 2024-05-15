package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserMapper;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = UserMapper.toUser(userRegistrationDTO, passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserRegistrationDTO userRegistrationDTO) {
        User existingUser = getUserById(id);
        existingUser.setUsername(userRegistrationDTO.getUsername());
        existingUser.setEmail(userRegistrationDTO.getEmail());
        existingUser.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        existingUser.setStreet(userRegistrationDTO.getStreet());
        existingUser.setCity(userRegistrationDTO.getCity());
        existingUser.setCountry(userRegistrationDTO.getCountry());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }
}


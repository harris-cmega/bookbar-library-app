package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.entities.User;
import java.util.List;

public interface UserService {
    User createUser(UserRegistrationDTO userRegistrationDTO);
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UserRegistrationDTO userRegistrationDTO);
    void deleteUser(Long id);
}


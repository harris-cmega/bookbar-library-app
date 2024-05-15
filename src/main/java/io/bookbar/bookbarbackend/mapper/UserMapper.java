package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserRegistrationDTO;
import io.bookbar.bookbarbackend.dto.UserResponseDTO;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.enums.UserRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setRole(user.getRole().name());
        responseDTO.setBalance(user.getBalance());
        responseDTO.setStreet(user.getStreet());
        responseDTO.setCity(user.getCity());
        responseDTO.setCountry(user.getCountry());
        return responseDTO;
    }

    public static User toUser(UserRegistrationDTO dto, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setStreet(dto.getStreet());
        user.setCity(dto.getCity());
        user.setCountry(dto.getCountry());
        user.setRole(UserRole.USER);
        return user;
    }
}

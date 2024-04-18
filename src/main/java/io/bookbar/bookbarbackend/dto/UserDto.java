package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;
}

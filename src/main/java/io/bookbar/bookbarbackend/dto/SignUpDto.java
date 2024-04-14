package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.enums.UserRole;

public record SignUpDto(
         String username,
         String email,
         String password) {
}

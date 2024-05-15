package io.bookbar.bookbarbackend.dto;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private Double balance;
    private String street;
    private String city;
    private String country;
}

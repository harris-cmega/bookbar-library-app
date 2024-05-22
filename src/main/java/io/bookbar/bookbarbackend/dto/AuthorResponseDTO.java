package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String biography;
    private String nationality;
    private LocalDate birthDate;
    private LocalDate deathDate;
}

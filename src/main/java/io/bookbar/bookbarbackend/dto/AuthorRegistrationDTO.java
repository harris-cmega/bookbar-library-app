package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AuthorRegistrationDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;

    @Size(max = 1000, message = "Biography must be less than 1000 characters")
    private String biography;

    @Size(max = 100, message = "Nationality must be less than 100 characters")
    private String nationality;

    private LocalDate birthDate;
    private LocalDate deathDate;
}

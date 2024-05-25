package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryDTO {

    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name should not exceed 100 characters")
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 200, message = "Address should not exceed 200 characters")
    private String address;

    @NotBlank(message = "City is mandatory")
    @Size(max = 100, message = "City should not exceed 100 characters")
    private String city;

    @NotBlank(message = "State is mandatory")
    @Size(max = 50, message = "State should not exceed 50 characters")
    private String state;

    @NotBlank(message = "Zip Code is mandatory")
    @Pattern(regexp = "\\d{5}", message = "Zip Code should be 5 digits")
    private String zip_code;

    @NotBlank(message = "Phone is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Phone should be 10 digits")
    private String phone;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Opening hours are mandatory")
    private String opening_hours;
}

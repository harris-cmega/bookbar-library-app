package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaymentDTO {
    private Long id;

    @NotBlank(message = "User email is mandatory")
    @Email(message = "Email should be valid")
    private String userEmail;

    @NotNull(message = "Amount is mandatory")
    private Double amount;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private List<String> products;
}
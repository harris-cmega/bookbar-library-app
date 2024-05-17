package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardDTO {

    private Long id;

    @NotBlank(message = "Card number is mandatory")
    @Pattern(regexp = "\\d{16}", message = "Card number must be 16 digits")
    private String cardNumber;

    @NotNull(message = "User ID is mandatory")
    private Long userID;
}

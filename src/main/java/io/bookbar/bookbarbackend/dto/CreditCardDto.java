package io.bookbar.bookbarbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDto {
    private Long id;
    private String cardNumber;
    private Long userID;
}

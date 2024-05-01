package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.CreditCardDto;
import io.bookbar.bookbarbackend.entities.CreditCard;

public class CreditCardMapper {
    public static CreditCardDto mapToCreditCardDto(CreditCard creditCard){
        return new CreditCardDto(
                creditCard.getId(),
                creditCard.getCardNumber(),
                creditCard.getUserID()
        );
    }

    public static CreditCard mapToCreditCard(CreditCardDto creditCardDto){
        return new CreditCard(
                creditCardDto.getId(),
                creditCardDto.getCardNumber(),
                creditCardDto.getUserID()
        );
    }
}

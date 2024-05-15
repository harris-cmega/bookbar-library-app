package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.CreditCardDTO;
import io.bookbar.bookbarbackend.entities.CreditCard;

public class CreditCardMapper {
    public static CreditCard toCreditCardEntity(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = new CreditCard();
        creditCard.setId(creditCardDTO.getId());
        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setUserID(creditCardDTO.getUserID());
        return creditCard;
    }

    public static CreditCardDTO toCreditCardDTO(CreditCard creditCard) {
        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setId(creditCard.getId());
        creditCardDTO.setCardNumber(creditCard.getCardNumber());
        creditCardDTO.setUserID(creditCard.getUserID());
        return creditCardDTO;
    }
}

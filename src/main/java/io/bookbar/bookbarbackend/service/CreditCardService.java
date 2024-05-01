package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.CreditCardDto;

import java.util.List;

public interface CreditCardService {
    CreditCardDto createCreditCard(CreditCardDto creditCardDto);

    CreditCardDto getCreditCardById(Long id);

    List<CreditCardDto> getAllCreditCards();

    CreditCardDto updateCreditCard(Long id, CreditCardDto updatedCreditCard);

    void deleteCreditCard(Long id);
}

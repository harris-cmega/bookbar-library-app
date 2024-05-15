package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.CreditCardDTO;

import java.util.List;

public interface CreditCardService {
    CreditCardDTO createCreditCard(CreditCardDTO creditCardDTO);
    CreditCardDTO getCreditCardById(Long id);
    List<CreditCardDTO> getAllCreditCards();
    CreditCardDTO updateCreditCard(Long id, CreditCardDTO creditCardDTO);
    void deleteCreditCard(Long id);
}

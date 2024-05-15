package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.CreditCardDTO;
import io.bookbar.bookbarbackend.entities.CreditCard;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.CreditCardMapper;
import io.bookbar.bookbarbackend.repository.CreditCardRepository;
import io.bookbar.bookbarbackend.service.CreditCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Override
    public CreditCardDTO createCreditCard(CreditCardDTO creditCardDTO) {
        CreditCard creditCard = CreditCardMapper.toCreditCardEntity(creditCardDTO);
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        return CreditCardMapper.toCreditCardDTO(savedCreditCard);
    }

    @Override
    public CreditCardDTO getCreditCardById(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card not found"));
        return CreditCardMapper.toCreditCardDTO(creditCard);
    }

    @Override
    public List<CreditCardDTO> getAllCreditCards() {
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards.stream()
                .map(CreditCardMapper::toCreditCardDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CreditCardDTO updateCreditCard(Long id, CreditCardDTO creditCardDTO) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card not found"));

        creditCard.setCardNumber(creditCardDTO.getCardNumber());
        creditCard.setUserID(creditCardDTO.getUserID());

        CreditCard updatedCreditCard = creditCardRepository.save(creditCard);
        return CreditCardMapper.toCreditCardDTO(updatedCreditCard);
    }

    @Override
    public void deleteCreditCard(Long id) {
        CreditCard creditCard = creditCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Credit Card not found"));
        creditCardRepository.delete(creditCard);
    }
}

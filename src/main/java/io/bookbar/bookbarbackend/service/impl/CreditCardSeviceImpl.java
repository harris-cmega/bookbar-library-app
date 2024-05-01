package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.CreditCardDto;
import io.bookbar.bookbarbackend.entities.CreditCard;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.CreditCardMapper;
import io.bookbar.bookbarbackend.repository.CreditCardRepository;
import io.bookbar.bookbarbackend.service.CreditCardService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCardSeviceImpl implements CreditCardService {

    private CreditCardRepository creditCardRepository;
    @Override
    public CreditCardDto createCreditCard(CreditCardDto creditCardDto) {
        CreditCard creditCard = CreditCardMapper.mapToCreditCard(creditCardDto);
        CreditCard savedCreditCard = creditCardRepository.save(creditCard);
        return CreditCardMapper.mapToCreditCardDto(savedCreditCard);
    }

    @Override
    public CreditCardDto getCreditCardById(Long id){
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Credit Card does not exist with given ID: " + id));
        return CreditCardMapper.mapToCreditCardDto(creditCard);
    }

    @Override
    public List<CreditCardDto> getAllCreditCards(){
        List<CreditCard> creditCards = creditCardRepository.findAll();
        return creditCards.stream().map((creditCard) -> CreditCardMapper.mapToCreditCardDto(creditCard)).collect(Collectors.toList());
    }

    @Override
    public CreditCardDto updateCreditCard(Long id, CreditCardDto updatedCreditCard){
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Credit Card does not exist with given ID: " +id));
        creditCard.setCardNumber(updatedCreditCard.getCardNumber());
        creditCard.setUserID(updatedCreditCard.getUserID());

        CreditCard updatedCreditCardObj = creditCardRepository.save(creditCard);

        return  CreditCardMapper.mapToCreditCardDto(updatedCreditCardObj);
    }

    @Override
    public void deleteCreditCard(Long id){
        CreditCard creditCard = creditCardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Credit Card does not exist with given ID: " +id));
        creditCardRepository.deleteById(id);
    }

}

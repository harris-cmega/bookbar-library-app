package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.CreditCardDto;
import io.bookbar.bookbarbackend.entities.CreditCard;
import io.bookbar.bookbarbackend.service.CreditCardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creditCards")
public class CreditCardController {
    private CreditCardService creditCardService;

    // Build add Credit Card REST API
    @PostMapping
    public ResponseEntity<CreditCardDto> createCreditCard(@RequestBody CreditCardDto creditCardDto){
        CreditCardDto savedCreditCard = creditCardService.createCreditCard(creditCardDto);
        return  new ResponseEntity<>(savedCreditCard, HttpStatus.CREATED);
    }

    // Build Get Credit Card REST API
    @GetMapping("{id}")
    public ResponseEntity<CreditCardDto> getCreditCardById(@PathVariable("id") Long id){
        CreditCardDto creditCardDto = creditCardService.getCreditCardById(id);
        return ResponseEntity.ok(creditCardDto);
    }

    // Build Get All Credit Cards REST API
    @GetMapping
    public ResponseEntity<List<CreditCardDto>> getAllCreditCards(){
        List<CreditCardDto> creditCards = creditCardService.getAllCreditCards();
        return ResponseEntity.ok(creditCards);
    }

    // Build Update Credit Card REST API
    @PutMapping("{id}")
    public ResponseEntity<CreditCardDto> updateCreditCard(@PathVariable("id") Long id, @RequestBody CreditCardDto updatedCreditCard){
        CreditCardDto creditCardDto = creditCardService.updateCreditCard(id, updatedCreditCard);
        return ResponseEntity.ok(creditCardDto);
    }

    // Build Delete Credit Card REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCreditCard(@PathVariable("id") Long id){
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.ok("Credit Card deleted successfully!");
    }
}

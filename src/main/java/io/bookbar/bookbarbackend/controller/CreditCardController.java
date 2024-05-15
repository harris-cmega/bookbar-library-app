package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.CreditCardDTO;
import io.bookbar.bookbarbackend.service.CreditCardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCardDTO> createCreditCard(@RequestBody @Valid CreditCardDTO creditCardDTO) {
        CreditCardDTO savedCreditCard = creditCardService.createCreditCard(creditCardDTO);
        return new ResponseEntity<>(savedCreditCard, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<CreditCardDTO> getCreditCardById(@PathVariable("id") Long id) {
        CreditCardDTO creditCardDTO = creditCardService.getCreditCardById(id);
        return ResponseEntity.ok(creditCardDTO);
    }

    @GetMapping
    public ResponseEntity<List<CreditCardDTO>> getAllCreditCards() {
        List<CreditCardDTO> creditCards = creditCardService.getAllCreditCards();
        return ResponseEntity.ok(creditCards);
    }

    @PutMapping("{id}")
    public ResponseEntity<CreditCardDTO> updateCreditCard(@PathVariable("id") Long id, @RequestBody @Valid CreditCardDTO creditCardDTO) {
        CreditCardDTO updatedCreditCard = creditCardService.updateCreditCard(id, creditCardDTO);
        return ResponseEntity.ok(updatedCreditCard);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCreditCard(@PathVariable("id") Long id) {
        creditCardService.deleteCreditCard(id);
        return ResponseEntity.noContent().build();
    }
}

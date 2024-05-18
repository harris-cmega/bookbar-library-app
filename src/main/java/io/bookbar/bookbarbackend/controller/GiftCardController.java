package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.service.GiftCardService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/giftcards")
public class GiftCardController {

    private final GiftCardService giftCardService;

    @PostMapping
    public ResponseEntity<GiftCardDTO> createGiftCard(@RequestBody @Valid GiftCardDTO giftCardDto) {
        GiftCardDTO savedGiftCard = giftCardService.createGiftCard(giftCardDto);
        return new ResponseEntity<>(savedGiftCard, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<GiftCardDTO> getGiftCardById(@PathVariable("id") Long giftCardId) {
        GiftCardDTO giftCardDto = giftCardService.getGiftCardById(giftCardId);
        return ResponseEntity.ok(giftCardDto);
    }

    @GetMapping
    public ResponseEntity<List<GiftCardDTO>> getAllGiftCards() {
        List<GiftCardDTO> giftCards = giftCardService.getAllGiftCards();
        return ResponseEntity.ok(giftCards);
    }

    @PutMapping("{id}")
    public ResponseEntity<GiftCardDTO> updateGiftCard(@PathVariable("id") Long giftCardId, @RequestBody @Valid GiftCardDTO giftCardDto) {
        GiftCardDTO updatedGiftCard = giftCardService.updateGiftCard(giftCardId, giftCardDto);
        return ResponseEntity.ok(updatedGiftCard);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGiftCard(@PathVariable("id") Long giftCardId) {
        giftCardService.deleteGiftCard(giftCardId);
        return ResponseEntity.ok("GiftCard deleted");
    }
}

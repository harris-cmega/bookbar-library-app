package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.service.GiftCardService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/giftcard")
public class GiftCardController {
    private GiftCardService giftCardService;

    // API per me shtu gift cards
    @PostMapping
    public ResponseEntity<GiftCardDTO> createGiftCard(@RequestBody GiftCardDTO giftCardDTO){
        GiftCardDTO savedGiftCard = giftCardService.createGiftCard(giftCardDTO);
        return new ResponseEntity<>(savedGiftCard, HttpStatus.CREATED);
    }

    // API per me i marr gift cards mbas ID
    @GetMapping("{id}")
    public ResponseEntity<GiftCardDTO> getGiftCardById(@PathVariable("id") Long giftCardId){
        GiftCardDTO giftCardDTO = giftCardService.getGiftCardById(giftCardId);
        return ResponseEntity.ok(giftCardDTO);
    }

    // API per me i marr krejt gift cards
    @GetMapping
    public ResponseEntity<List<GiftCardDTO>> getAllGiftCards(){
        List<GiftCardDTO> giftCards = giftCardService.getAllGiftCards();
        return ResponseEntity.ok(giftCards);
    }

    // API per me i bo update gift cards
    @PutMapping("{id}")
    public ResponseEntity<GiftCardDTO> updateGiftCard(@PathVariable("id") Long id, @RequestBody GiftCardDTO updatedGiftCard){
        GiftCardDTO giftCardDTO = giftCardService.updateGiftCard(id, updatedGiftCard);
        return ResponseEntity.ok(giftCardDTO);
    }

    // API per me i fshi gift cards
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteGiftCard(@PathVariable("id") Long giftCardId){
        giftCardService.deleteGiftCard(giftCardId);
        return ResponseEntity.ok("Deleted gift card");
    }

}

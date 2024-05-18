package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;

import java.util.List;

public interface GiftCardService {
    GiftCardDTO createGiftCard(GiftCardDTO giftCardDto);
    GiftCardDTO getGiftCardById(Long id);
    List<GiftCardDTO> getAllGiftCards();
    GiftCardDTO updateGiftCard(Long id, GiftCardDTO giftCardDto);
    void deleteGiftCard(Long id);
}


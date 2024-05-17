package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;

import java.util.List;

public interface GiftCardService {
    GiftCardDTO createGiftCard(GiftCardDTO giftCardDTO);

    GiftCardDTO getGiftCardById(long giftCardId);

    List<GiftCardDTO> getAllGiftCards();

    GiftCardDTO updateGiftCard(long giftCardId, GiftCardDTO updatedGiftCard);

    void deleteGiftCard(long giftCardId);
}


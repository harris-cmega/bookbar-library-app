package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.entities.GiftCard;

public class GiftCardMapper {
    public static GiftCardDTO mapToGiftCardDTO(GiftCard giftCard) {
        return new GiftCardDTO(
                giftCard.getId(),
                giftCard.getName(),
                giftCard.getValue()
        );
    }

    public static GiftCard mapToGiftCard(GiftCardDTO giftCardDTO) {
        return new GiftCard(
                giftCardDTO.getId(),
                giftCardDTO.getName(),
                giftCardDTO.getValue()
        );
    }
}

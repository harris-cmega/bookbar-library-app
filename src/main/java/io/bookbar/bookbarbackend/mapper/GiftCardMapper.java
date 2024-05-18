package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.entities.GiftCard;

public class GiftCardMapper {
    public static GiftCard toGiftCardEntity(GiftCardDTO giftCardDto) {
        GiftCard giftCard = new GiftCard();
        giftCard.setId(giftCardDto.getId());
        giftCard.setName(giftCardDto.getName());
        giftCard.setValue(giftCardDto.getValue());
        return giftCard;
    }

    public static GiftCardDTO toGiftCardDto(GiftCard giftCard) {
        GiftCardDTO giftCardDto = new GiftCardDTO();
        giftCardDto.setId(giftCard.getId());
        giftCardDto.setName(giftCard.getName());
        giftCardDto.setValue(giftCard.getValue());
        return giftCardDto;
    }
}

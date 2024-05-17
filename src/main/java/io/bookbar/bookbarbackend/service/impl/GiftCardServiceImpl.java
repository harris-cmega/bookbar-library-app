package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.entities.GiftCard;
import io.bookbar.bookbarbackend.exception.GiftCardNotFoundException;
import io.bookbar.bookbarbackend.mapper.GiftCardMapper;
import io.bookbar.bookbarbackend.repository.GiftCardRepository;
import io.bookbar.bookbarbackend.service.GiftCardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GiftCardServiceImpl implements GiftCardService {

    private GiftCardRepository giftCardRepository;

    @Override
    public GiftCardDTO createGiftCard(GiftCardDTO giftCardDTO) {
        GiftCard giftCard = GiftCardMapper.mapToGiftCard(giftCardDTO);
        GiftCard savedGiftCard = giftCardRepository.save(giftCard);

        return GiftCardMapper.mapToGiftCardDTO(savedGiftCard);
    }

    @Override
    public GiftCardDTO getGiftCardById(long giftCardId) {
        GiftCard giftCard = giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> new GiftCardNotFoundException("No Gift Card is existing with the given ID" + giftCardId));

        return GiftCardMapper.mapToGiftCardDTO(giftCard);
    }

    @Override
    public List<GiftCardDTO> getAllGiftCards() {
        List<GiftCard> giftCards = giftCardRepository.findAll();

        return giftCards.stream().map((giftCard -> GiftCardMapper.mapToGiftCardDTO(giftCard)))
                .collect(Collectors.toList());
    }

    @Override
    public GiftCardDTO updateGiftCard(long giftCardId, GiftCardDTO updatedGiftCard) {
        GiftCard giftCard = giftCardRepository.findById(giftCardId)
                .orElseThrow(() -> new GiftCardNotFoundException("No Gift Card is existing with the given ID" + giftCardId));

        giftCard.setName(updatedGiftCard.getName());
        giftCard.setValue(updatedGiftCard.getValue());

        GiftCard updatedGiftCardObj = giftCardRepository.save(giftCard);

        return GiftCardMapper.mapToGiftCardDTO(updatedGiftCardObj);
    }

    @Override
    public void deleteGiftCard(long giftCardId) {

        GiftCard giftCard = giftCardRepository.findById(giftCardId).orElseThrow(
                () -> new GiftCardNotFoundException("No Gift Card is existing with the given ID" + giftCardId)
        );

        giftCardRepository.deleteById(giftCardId);

    }
}

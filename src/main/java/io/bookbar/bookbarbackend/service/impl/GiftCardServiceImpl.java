package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.GiftCardDTO;
import io.bookbar.bookbarbackend.entities.GiftCard;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
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

    private final GiftCardRepository giftCardRepository;

    @Override
    public GiftCardDTO createGiftCard(GiftCardDTO giftCardDto) {
        GiftCard giftCard = GiftCardMapper.toGiftCardEntity(giftCardDto);
        GiftCard savedGiftCard = giftCardRepository.save(giftCard);
        return GiftCardMapper.toGiftCardDto(savedGiftCard);
    }

    @Override
    public GiftCardDTO getGiftCardById(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GiftCard not found"));
        return GiftCardMapper.toGiftCardDto(giftCard);
    }

    @Override
    public List<GiftCardDTO> getAllGiftCards() {
        List<GiftCard> giftCards = giftCardRepository.findAll();
        return giftCards.stream()
                .map(GiftCardMapper::toGiftCardDto)
                .collect(Collectors.toList());
    }

    @Override
    public GiftCardDTO updateGiftCard(Long id, GiftCardDTO giftCardDto) {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GiftCard not found"));
        giftCard.setName(giftCardDto.getName());
        giftCard.setValue(giftCardDto.getValue());
        GiftCard updatedGiftCard = giftCardRepository.save(giftCard);
        return GiftCardMapper.toGiftCardDto(updatedGiftCard);
    }

    @Override
    public void deleteGiftCard(Long id) {
        GiftCard giftCard = giftCardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("GiftCard not found"));
        giftCardRepository.delete(giftCard);
    }
}

package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.SubscriptionDTO;
import io.bookbar.bookbarbackend.entities.Subscription;
import io.bookbar.bookbarbackend.entities.User;

public class SubscriptionMapper {

    public static Subscription toSubscriptionEntity(SubscriptionDTO dto) {
        if (dto == null) {
            return null;
        }
        Subscription subscription = new Subscription();
        subscription.setId(dto.getId());
        subscription.setName(dto.getName());
        subscription.setPrice(dto.getPrice());
        subscription.setStartTime(dto.getStartTime());
        subscription.setEndTime(dto.getEndTime());
        subscription.setCustomer(new User());
        subscription.getCustomer().setId(dto.getCustomerId());
        return subscription;
    }

    public static SubscriptionDTO toSubscriptionDTO(Subscription entity) {
        if (entity == null) {
            return null;
        }
        SubscriptionDTO dto = new SubscriptionDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setCustomerId(entity.getCustomer().getId());
        return dto;
    }
}
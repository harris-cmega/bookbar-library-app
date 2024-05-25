package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;
import io.bookbar.bookbarbackend.entities.UserSubscription;

public class UserSubscriptionMapper {
    public static UserSubscriptionDto mapToUserSubscriptionDto(UserSubscription userSubscription){
        return new UserSubscriptionDto(
                userSubscription.getId(),
                userSubscription.getUserId(),
                userSubscription.getSubscriptionId(),
                userSubscription.getStartDate(),
                userSubscription.getEndDate()
        );
    }

    public static UserSubscription mapToUserSubscription(UserSubscriptionDto userSubscriptionDto){
        return new UserSubscription(
                userSubscriptionDto.getId(),
                userSubscriptionDto.getUserId(),
                userSubscriptionDto.getSubscriptionId(),
                userSubscriptionDto.getStartDate(),
                userSubscriptionDto.getEndDate()
        );
    }
}

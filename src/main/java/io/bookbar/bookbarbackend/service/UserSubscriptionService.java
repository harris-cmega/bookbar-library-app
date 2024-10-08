package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;

import java.util.List;

public interface UserSubscriptionService {
    UserSubscriptionDto createUserSubscription(UserSubscriptionDto userSubscriptionDto);

    UserSubscriptionDto getUserSubscriptionById(Long id);

    List<UserSubscriptionDto> getAllUserSubscriptions();

    UserSubscriptionDto updateUserSubscription(Long id, UserSubscriptionDto updatedUserSubscription);

    void deleteUserSubscription(Long id);

    UserSubscriptionDto createSubscriptionForUser(Long userId, UserSubscriptionDto userSubscriptionDto);
}

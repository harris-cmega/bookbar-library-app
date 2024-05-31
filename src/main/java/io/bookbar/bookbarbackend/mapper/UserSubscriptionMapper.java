package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.entities.UserSubscription;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSubscriptionMapper {
    public static UserSubscriptionDto mapToUserSubscriptionDto(UserSubscription userSubscription) {
        UserSubscriptionDto userSubscriptionDto = new UserSubscriptionDto();
        userSubscriptionDto.setId(userSubscription.getId());
        userSubscriptionDto.setUserId(userSubscription.getUser().getId());
        userSubscriptionDto.setSubscriptionId(userSubscription.getSubscriptionId());
        userSubscriptionDto.setStartDate(userSubscription.getStartDate());
        userSubscriptionDto.setEndDate(userSubscription.getEndDate());
        return userSubscriptionDto;
    }

    public static UserSubscription mapToUserSubscription(UserSubscriptionDto userSubscriptionDto) {
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setId(userSubscriptionDto.getId());

        User user = new User();
        user.setId(userSubscriptionDto.getUserId());
        userSubscription.setUser(user);

        userSubscription.setSubscriptionId(userSubscriptionDto.getSubscriptionId());
        userSubscription.setStartDate(userSubscriptionDto.getStartDate());
        userSubscription.setEndDate(userSubscriptionDto.getEndDate());
        return userSubscription;
    }
}

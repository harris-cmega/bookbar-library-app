package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;
import io.bookbar.bookbarbackend.entities.UserSubscription;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserSubscriptionMapper;
import io.bookbar.bookbarbackend.repository.UserSubscriptionRepository;
import io.bookbar.bookbarbackend.service.UserSubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserSubscriptionServiceImpl implements UserSubscriptionService {
    private UserSubscriptionRepository userSubscriptionRepository;

    @Override
    public UserSubscriptionDto createUserSubscription(UserSubscriptionDto userSubscriptionDto) {
        UserSubscription userSubscription = UserSubscriptionMapper.mapToUserSubscription(userSubscriptionDto);
        UserSubscription savedUserSubscription = userSubscriptionRepository.save(userSubscription);
        return UserSubscriptionMapper.mapToUserSubscriptionDto(savedUserSubscription);
    }

    @Override
    public UserSubscriptionDto getUserSubscriptionById(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User subscription with given id does not exist!"));
        return UserSubscriptionMapper.mapToUserSubscriptionDto(userSubscription);
    }

    @Override
    public List<UserSubscriptionDto> getAllUserSubscription() {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAll();
        return userSubscriptions.stream().map((userSubscription) -> UserSubscriptionMapper.mapToUserSubscriptionDto(userSubscription))
                .collect(Collectors.toList());
    }

    @Override
    public UserSubscriptionDto updateUserSubscription(Long id, UserSubscriptionDto updatedUserSubscription) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User subscription with given id does not exist!")
                );

        userSubscription.setUserId(updatedUserSubscription.getUserId());
        userSubscription.setSubscriptionId(updatedUserSubscription.getSubscriptionId());
        userSubscription.setStartDate(updatedUserSubscription.getStartDate());
        userSubscription.setEndDate(updatedUserSubscription.getEndDate());

        UserSubscription updatedUserSubscriptionObj = userSubscriptionRepository.save(userSubscription);

        return UserSubscriptionMapper.mapToUserSubscriptionDto(updatedUserSubscriptionObj);
    }

    @Override
    public void deleteUserSubscription(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User subscription with given id does not exist!")
                );

        userSubscriptionRepository.deleteById(id);
    }
}

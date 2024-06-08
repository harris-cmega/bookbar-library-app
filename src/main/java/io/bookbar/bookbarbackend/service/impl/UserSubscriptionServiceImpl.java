package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.UserSubscriptionDto;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.entities.UserSubscription;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.UserSubscriptionMapper;
import io.bookbar.bookbarbackend.repository.UserRepository;
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
    private UserRepository userRepository;
    private UserSubscriptionMapper userSubscriptionMapper;

    @Override
    public UserSubscriptionDto createUserSubscription(UserSubscriptionDto userSubscriptionDto) {
        UserSubscription userSubscription = userSubscriptionMapper.mapToUserSubscription(userSubscriptionDto);
        UserSubscription savedUserSubscription = userSubscriptionRepository.save(userSubscription);
        return userSubscriptionMapper.mapToUserSubscriptionDto(savedUserSubscription);
    }

    @Override
    public UserSubscriptionDto getUserSubscriptionById(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User subscription with given id does not exist!"));
        return userSubscriptionMapper.mapToUserSubscriptionDto(userSubscription);
    }

    @Override
    public List<UserSubscriptionDto> getAllUserSubscriptions() {
        List<UserSubscription> userSubscriptions = userSubscriptionRepository.findAll();
        return userSubscriptions.stream().map(UserSubscriptionMapper::mapToUserSubscriptionDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserSubscriptionDto updateUserSubscription(Long id, UserSubscriptionDto updatedUserSubscription) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User subscription with given id does not exist!"));

        userSubscription.setSubscriptionId(updatedUserSubscription.getSubscriptionId());
        userSubscription.setStartDate(updatedUserSubscription.getStartDate());
        userSubscription.setEndDate(updatedUserSubscription.getEndDate());

        UserSubscription updatedUserSubscriptionObj = userSubscriptionRepository.save(userSubscription);

        return userSubscriptionMapper.mapToUserSubscriptionDto(updatedUserSubscriptionObj);
    }

    @Override
    public void deleteUserSubscription(Long id) {
        UserSubscription userSubscription = userSubscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User subscription with given id does not exist!"));

        userSubscriptionRepository.deleteById(id);
    }

    @Override
    public UserSubscriptionDto createSubscriptionForUser(Long userId, UserSubscriptionDto userSubscriptionDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User with given id does not exist!"));

        UserSubscription userSubscription = userSubscriptionMapper.mapToUserSubscription(userSubscriptionDto);
        userSubscription.setUser(user);

        UserSubscription savedUserSubscription = userSubscriptionRepository.save(userSubscription);
        return userSubscriptionMapper.mapToUserSubscriptionDto(savedUserSubscription);
    }
}

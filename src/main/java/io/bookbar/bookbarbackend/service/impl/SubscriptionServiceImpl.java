package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.SubscriptionDTO;
import io.bookbar.bookbarbackend.entities.Subscription;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.SubscriptionMapper;
import io.bookbar.bookbarbackend.repository.SubscriptionRepository;
import io.bookbar.bookbarbackend.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = SubscriptionMapper.toSubscriptionEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return SubscriptionMapper.toSubscriptionDTO(savedSubscription);
    }

    @Override
    public SubscriptionDTO getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        return SubscriptionMapper.toSubscriptionDTO(subscription);
    }

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream()
                .map(SubscriptionMapper::toSubscriptionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO) {
        Subscription existingSubscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        Subscription updatedSubscription = SubscriptionMapper.toSubscriptionEntity(subscriptionDTO);
        updatedSubscription.setId(existingSubscription.getId()); // Ensure the ID is not changed
        subscriptionRepository.save(updatedSubscription);
        return SubscriptionMapper.toSubscriptionDTO(updatedSubscription);
    }

        @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        subscriptionRepository.delete(subscription);
    }
}
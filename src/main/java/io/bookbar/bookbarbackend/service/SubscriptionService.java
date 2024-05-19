package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.SubscriptionDTO;
import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO getSubscriptionById(Long id);
    List<SubscriptionDTO> getAllSubscriptions();
    SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO);
    void deleteSubscription(Long id);
}
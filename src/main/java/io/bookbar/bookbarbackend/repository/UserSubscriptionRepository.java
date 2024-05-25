package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.UserSubscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
}

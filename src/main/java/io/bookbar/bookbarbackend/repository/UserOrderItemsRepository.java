package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.UserOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserOrderItemsRepository extends JpaRepository<UserOrderItems, Long> {

    // Custom query method to find all order items by order ID
    List<UserOrderItems> findByOrderId(Long orderId);
}

package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}

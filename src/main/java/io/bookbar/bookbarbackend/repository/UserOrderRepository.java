package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Long> {
}

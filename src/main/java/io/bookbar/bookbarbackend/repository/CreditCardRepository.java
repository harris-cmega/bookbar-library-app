package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}

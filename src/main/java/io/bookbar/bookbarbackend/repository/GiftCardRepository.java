package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends JpaRepository<GiftCard, Long> {
}

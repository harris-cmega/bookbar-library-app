package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    @Transactional
    void deleteByUserId(Long userId);
}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken token);

    void deleteByUserId(Long userId);
}

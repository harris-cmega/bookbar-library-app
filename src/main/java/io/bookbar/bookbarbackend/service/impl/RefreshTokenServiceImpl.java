package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.entities.RefreshToken;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.RefreshTokenRepository;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refreshExpirationMs}")
    private Long refreshTokenDurationMs;


    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(Long userId) {

        // Delete existing refresh tokens for the user
        refreshTokenRepository.deleteByUserId(userId);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        RefreshToken savedToken = refreshTokenRepository.save(refreshToken);

        return savedToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new ResourceNotFoundException("Refresh token was expired. Please make a new signin request");
        }
        return token;
    }

    public void deleteByUserId(Long userId) {
        refreshTokenRepository.deleteByUserId(userId);
    }
}

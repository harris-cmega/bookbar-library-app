package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByApiKey(String apiKey);
}

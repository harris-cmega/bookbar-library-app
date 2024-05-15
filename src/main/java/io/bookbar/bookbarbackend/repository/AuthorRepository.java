package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}

package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    Author findByName(String name);
}

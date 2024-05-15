package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Library;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByName(String name);
}

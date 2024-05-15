package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}

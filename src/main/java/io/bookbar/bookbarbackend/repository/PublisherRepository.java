package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findByName(String name);
}

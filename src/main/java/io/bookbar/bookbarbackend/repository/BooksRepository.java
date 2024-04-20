package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Integer> {
}

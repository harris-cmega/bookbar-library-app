package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingOrAuthor_NameContaining(String title, String authorName);
    Page<Book> findAll(Pageable pageable);
    List<Book> findByTitleContainingIgnoreCase(String title);
}
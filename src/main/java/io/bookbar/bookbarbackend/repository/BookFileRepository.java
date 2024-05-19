package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.BookFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookFileRepository extends JpaRepository<BookFile, Long> {
}

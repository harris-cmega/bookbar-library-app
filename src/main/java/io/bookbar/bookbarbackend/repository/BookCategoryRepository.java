package io.bookbar.bookbarbackend.repository;

import io.bookbar.bookbarbackend.entities.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory, Long> {
    List<BookCategory> findByBook_Id(Long bookId);
    List<BookCategory> findByCategory_Id(Long categoryId);
}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookCategoryService {
    BookCategoryDTO createBookCategory(BookCategoryDTO bookCategoryDto);
    BookCategoryDTO getBookCategoryById(Long id);
    Page<BookCategoryDTO> getAllBookCategories(Pageable pageable);  // Updated to use Pageable
    BookCategoryDTO updateBookCategory(Long id, BookCategoryDTO bookCategoryDto);
    void deleteBookCategory(Long id);
    List<BookCategoryDTO> getBookCategoriesByBookId(Long bookId);  // Added method to find by book ID
    List<BookCategoryDTO> getBookCategoriesByCategoryId(Long categoryId);  // Added method to find by category ID
}

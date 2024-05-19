package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;

import java.util.List;

public interface BookCategoryService {
    BookCategoryDTO createBookCategory(BookCategoryDTO bookCategoryDto);
    BookCategoryDTO getBookCategoryById(Long id);
    List<BookCategoryDTO> getAllBookCategories();
    BookCategoryDTO updateBookCategory(Long id, BookCategoryDTO bookCategoryDto);
    void deleteBookCategory(Long id);
}

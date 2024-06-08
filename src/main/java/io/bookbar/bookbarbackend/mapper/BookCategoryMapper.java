package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;
import io.bookbar.bookbarbackend.entities.BookCategory;

public class BookCategoryMapper {
    public static BookCategory toBookCategoryEntity(BookCategoryDTO bookCategoryDto) {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setId(bookCategoryDto.getId());
        return bookCategory;
    }

    public static BookCategoryDTO toBookCategoryDto(BookCategory bookCategory) {
        BookCategoryDTO bookCategoryDto = new BookCategoryDTO();
        bookCategoryDto.setId(bookCategory.getId());
        bookCategoryDto.setBook_id(bookCategory.getBook().getId());
        bookCategoryDto.setBook_title(bookCategory.getBook().getTitle());  // Set book title
        bookCategoryDto.setCategory_id(bookCategory.getCategory().getId());
        bookCategoryDto.setCategory_name(bookCategory.getCategory().getName());  // Set category name
        return bookCategoryDto;
    }
}

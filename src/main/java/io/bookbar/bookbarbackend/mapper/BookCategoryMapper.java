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
        bookCategoryDto.setBookId(bookCategory.getBook().getId());
        bookCategoryDto.setCategoryId(bookCategory.getCategory().getId());
        return bookCategoryDto;
    }
}

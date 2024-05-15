package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.entities.Book;

public class BookMapper {
    public static Book toBookEntity(BookDTO bookDto) {
        Book book = new Book();
        book.setId(bookDto.getISBN());
        book.setTitle(bookDto.getTitle());
        book.setLanguage(bookDto.getLanguage());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setImage(bookDto.getImage());
        book.setPageNumber(bookDto.getPageNumber());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        return book;
    }

    public static BookDTO toBookDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setISBN(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPublicationDate(book.getPublicationDate());
        bookDto.setImage(book.getImage());
        bookDto.setPageNumber(book.getPageNumber());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        return bookDto;
    }
}

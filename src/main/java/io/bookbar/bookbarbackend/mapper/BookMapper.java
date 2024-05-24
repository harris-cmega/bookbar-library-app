package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Library;
import io.bookbar.bookbarbackend.entities.Publisher;

public class BookMapper {
    public static Book toBookEntity(BookDTO bookDto, Author author, Library library, Publisher publisher) {
        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setLanguage(bookDto.getLanguage());
        book.setPublication_date(bookDto.getPublication_date());
        book.setImage(bookDto.getImage());
        book.setPage_number(bookDto.getPage_number());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        book.setAuthor(author);
        book.setLibrary(library);
        book.setPublisher(publisher);
        return book;
    }

    public static BookDTO toBookDto(Book book) {
        BookDTO bookDto = new BookDTO();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setLanguage(book.getLanguage());
        bookDto.setPublication_date(book.getPublication_date());
        bookDto.setImage(book.getImage());
        bookDto.setPage_number(book.getPage_number());
        bookDto.setPrice(book.getPrice());
        bookDto.setDescription(book.getDescription());
        bookDto.setAuthor_id(book.getAuthor().getId());
        bookDto.setAuthor_name(book.getAuthor().getName()); // Set author name
        bookDto.setLibrary_id(book.getLibrary().getId());
        bookDto.setLibrary_name(book.getLibrary().getName()); // Set library name
        bookDto.setPublisher_id(book.getPublisher().getId());
        bookDto.setPublisher_name(book.getPublisher().getName()); // Set publisher name
        return bookDto;
    }
}

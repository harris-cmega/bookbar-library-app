package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BooksDto;
import io.bookbar.bookbarbackend.entities.Books;

public class BooksMapper {

    public static BooksDto mapToBooksDto(Books books) {
        return new BooksDto(
                books.getId(),
                books.getTitle(),
                books.getAuthor(),
                books.getPublisher(),
                books.getISBN()
        );
    }

    public static Books mapToBooks(BooksDto booksDto) {
        return new Books(
                booksDto.getId(),
                booksDto.getTitle(),
                booksDto.getAuthor(),
                booksDto.getPublisher(),
                booksDto.getISBN()
        );
    }
}

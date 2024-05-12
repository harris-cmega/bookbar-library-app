package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BooksDto;
import io.bookbar.bookbarbackend.entities.Books;

public class BooksMapper {

    public static BooksDto mapToBooksDto(Books books) {
        return new BooksDto(
                books.getISBN(),
                books.getTitle(),
                books.getLanguage(),
                books.getPublicationDate(),
                books.getImage(),
                books.getPageNumber(),
                books.getPrice(),
                books.getDescription(),
                books.getAuthorID(),
                books.getLibraryID(),
                books.getPublisherID()
        );
    }

    public static Books mapToBooks(BooksDto booksDto) {
        return new Books(
                booksDto.getISBN(),
                booksDto.getTitle(),
                booksDto.getLanguage(),
                booksDto.getPublicationDate(),
                booksDto.getImage(),
                booksDto.getPageNumber(),
                booksDto.getPrice(),
                booksDto.getDescription(),
                booksDto.getAuthorID(),
                booksDto.getLibraryID(),
                booksDto.getPublisherID()
        );
    }
}

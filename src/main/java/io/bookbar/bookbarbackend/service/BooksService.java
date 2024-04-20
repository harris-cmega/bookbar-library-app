package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BooksDto;

import java.util.List;

public interface BooksService {
    BooksDto createBook(BooksDto booksDto);

    BooksDto getBooksById(int booksId);

    List<BooksDto> getAllBooks();

    BooksDto updateBook(int booksId, BooksDto updatedBook);

    void deleteBook(int booksId);
}

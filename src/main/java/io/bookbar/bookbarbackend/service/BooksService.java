package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BooksDto;

import java.util.List;

public interface BooksService {
    BooksDto createBook(BooksDto booksDto);

    BooksDto getBooksById(Long booksId);

    List<BooksDto> getAllBooks();

    BooksDto updateBook(Long booksId, BooksDto updatedBook);

    void deleteBook(Long booksId);
}

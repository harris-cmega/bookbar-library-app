package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDto);
    BookDTO getBooksById(Long id);
    List<BookDTO> getAllBooks();
    BookDTO updateBook(Long id, BookDTO bookDto);
    void deleteBook(Long id);
}

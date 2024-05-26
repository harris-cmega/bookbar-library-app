package io.bookbar.bookbarbackend.service;

import org.springframework.data.domain.Page;
import io.bookbar.bookbarbackend.dto.BookDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDto);
    BookDTO getBookById(Long id);
    Page<BookDTO> getAllBooks(Pageable pageable);
    BookDTO updateBook(Long id, BookDTO bookDto);
    void deleteBook(Long id);
    List<BookDTO> searchBooks(String query);
}

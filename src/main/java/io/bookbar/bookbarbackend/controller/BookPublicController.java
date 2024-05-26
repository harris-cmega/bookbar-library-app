package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/public/books")
public class BookPublicController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(Pageable pageable) {
        Page<BookDTO> books = bookService.getAllBooks(pageable);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long bookId) {
        BookDTO bookDto = bookService.getBookById(bookId);
        return ResponseEntity.ok(bookDto);
    }
}

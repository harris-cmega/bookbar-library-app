package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.service.BookService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody @Valid BookDTO bookDto) {
        BookDTO savedBooks = bookService.createBook(bookDto);
        return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable("id") Long booksId) {
        BookDTO bookDto = bookService.getBooksById(booksId);
        return ResponseEntity.ok(bookDto);
    }

    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long booksId, @RequestBody @Valid BookDTO updatedBooks) {
        BookDTO bookDto = bookService.updateBook(booksId, updatedBooks);
        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long booksId) {
        bookService.deleteBook(booksId);
        return ResponseEntity.ok("Book deleted");
    }
}

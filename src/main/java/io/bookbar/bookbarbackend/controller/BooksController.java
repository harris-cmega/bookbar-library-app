package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BooksDto;
import io.bookbar.bookbarbackend.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BooksController {

    private BooksService booksService;

    // REST API per me shtu nje liber
    @PostMapping
    public ResponseEntity<BooksDto> createBook(@RequestBody BooksDto booksDto) {
        BooksDto savedBooks = booksService.createBook(booksDto);
        return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
    }

    // REST API per GET
    @GetMapping("{id}")
    public ResponseEntity<BooksDto> getAllBooks(@PathVariable("id") int booksId) {
        BooksDto booksDto = booksService.getBooksById(booksId);
        return ResponseEntity.ok(booksDto);
    }

    // REST API per GET all books

    @GetMapping
    public ResponseEntity<List<BooksDto>> getAllBooks() {
        List<BooksDto> books = booksService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // REST API per update books
    @PutMapping("{id}")
    public ResponseEntity<BooksDto> updateBook(@PathVariable("id") int booksId, @RequestBody BooksDto updatedBooks) {
        BooksDto booksDto = booksService.updateBook(booksId, updatedBooks);
        return ResponseEntity.ok(booksDto);
    }

    // REST API per delete te books
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int booksId) {
        booksService.deleteBook(booksId);
        return ResponseEntity.ok("Book deleted");
    }
}

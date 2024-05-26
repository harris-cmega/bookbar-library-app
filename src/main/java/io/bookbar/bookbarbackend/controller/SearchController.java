package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookDTO>> searchBooks(@RequestParam String query) {
        List<BookDTO> books = bookService.searchBooks(query);
        return ResponseEntity.ok(books);
    }
}

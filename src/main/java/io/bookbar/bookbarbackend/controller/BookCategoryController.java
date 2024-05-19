package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;
import io.bookbar.bookbarbackend.service.BookCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    private final BookCategoryService bookCategoryService;

    @PostMapping
    public ResponseEntity<BookCategoryDTO> createBookCategory(@RequestBody @Valid BookCategoryDTO bookCategoryDto) {
        BookCategoryDTO savedBookCategory = bookCategoryService.createBookCategory(bookCategoryDto);
        return new ResponseEntity<>(savedBookCategory, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookCategoryDTO> getBookCategoryById(@PathVariable("id") Long bookCategoryId) {
        BookCategoryDTO bookCategoryDto = bookCategoryService.getBookCategoryById(bookCategoryId);
        return ResponseEntity.ok(bookCategoryDto);
    }

    @GetMapping
    public ResponseEntity<List<BookCategoryDTO>> getAllBookCategories() {
        List<BookCategoryDTO> bookCategories = bookCategoryService.getAllBookCategories();
        return ResponseEntity.ok(bookCategories);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookCategoryDTO> updateBookCategory(@PathVariable("id") Long bookCategoryId, @RequestBody @Valid BookCategoryDTO bookCategoryDto) {
        BookCategoryDTO updatedBookCategory = bookCategoryService.updateBookCategory(bookCategoryId, bookCategoryDto);
        return ResponseEntity.ok(updatedBookCategory);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookCategory(@PathVariable("id") Long bookCategoryId) {
        bookCategoryService.deleteBookCategory(bookCategoryId);
        return ResponseEntity.ok("BookCategory deleted");
    }
}

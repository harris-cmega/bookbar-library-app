package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;
import io.bookbar.bookbarbackend.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book-categories")
public class BookCategoryController {

    @Autowired
    private BookCategoryService bookCategoryService;

    @PostMapping
    public ResponseEntity<BookCategoryDTO> createBookCategory(@RequestBody BookCategoryDTO bookCategoryDto) {
        BookCategoryDTO createdBookCategory = bookCategoryService.createBookCategory(bookCategoryDto);
        return ResponseEntity.ok(createdBookCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookCategoryDTO> getBookCategoryById(@PathVariable Long id) {
        BookCategoryDTO bookCategoryDto = bookCategoryService.getBookCategoryById(id);
        return ResponseEntity.ok(bookCategoryDto);
    }

    @GetMapping
    public ResponseEntity<Page<BookCategoryDTO>> getAllBookCategories(Pageable pageable) {
        Page<BookCategoryDTO> bookCategories = bookCategoryService.getAllBookCategories(pageable);
        return ResponseEntity.ok(bookCategories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookCategoryDTO> updateBookCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDto) {
        BookCategoryDTO updatedBookCategory = bookCategoryService.updateBookCategory(id, bookCategoryDto);
        return ResponseEntity.ok(updatedBookCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookCategory(@PathVariable Long id) {
        bookCategoryService.deleteBookCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-book/{bookId}")
    public ResponseEntity<List<BookCategoryDTO>> getBookCategoriesByBookId(@PathVariable Long bookId) {
        List<BookCategoryDTO> bookCategories = bookCategoryService.getBookCategoriesByBookId(bookId);
        return ResponseEntity.ok(bookCategories);
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<BookCategoryDTO>> getBookCategoriesByCategoryId(@PathVariable Long categoryId) {
        List<BookCategoryDTO> bookCategories = bookCategoryService.getBookCategoriesByCategoryId(categoryId);
        return ResponseEntity.ok(bookCategories);
    }
}

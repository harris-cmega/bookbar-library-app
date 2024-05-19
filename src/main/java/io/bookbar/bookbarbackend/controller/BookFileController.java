package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.BookFileDTO;
import io.bookbar.bookbarbackend.service.BookFileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/book-files")
public class BookFileController {

    private final BookFileService bookFileService;

    public BookFileController(BookFileService bookFileService) {
        this.bookFileService = bookFileService;
    }

    @PostMapping
    public ResponseEntity<BookFileDTO> createBookFile(@RequestBody @Valid BookFileDTO bookFileDto) {
        BookFileDTO savedBookFile = bookFileService.createBookFile(bookFileDto);
        return new ResponseEntity<>(savedBookFile, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookFileDTO> getBookFileById(@PathVariable("id") Long bookFileId) {
        BookFileDTO bookFileDto = bookFileService.getBookFileById(bookFileId);
        return ResponseEntity.ok(bookFileDto);
    }

    @GetMapping
    public ResponseEntity<List<BookFileDTO>> getAllBookFiles() {
        List<BookFileDTO> bookFiles = bookFileService.getAllBookFiles();
        return ResponseEntity.ok(bookFiles);
    }

    @PutMapping("{id}")
    public ResponseEntity<BookFileDTO> updateBookFile(@PathVariable("id") Long bookFileId, @RequestBody @Valid BookFileDTO bookFileDto) {
        BookFileDTO updatedBookFile = bookFileService.updateBookFile(bookFileId, bookFileDto);
        return ResponseEntity.ok(updatedBookFile);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBookFile(@PathVariable("id") Long bookFileId) {
        bookFileService.deleteBookFile(bookFileId);
        return ResponseEntity.ok("BookFile deleted");
    }
}

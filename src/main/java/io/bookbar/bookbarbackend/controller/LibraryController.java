package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.LibraryDTO;
import io.bookbar.bookbarbackend.service.LibraryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
@AllArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping
    public ResponseEntity<LibraryDTO> createLibrary(@RequestBody @Valid LibraryDTO libraryDTO) {
        LibraryDTO createdLibrary = libraryService.createLibrary(libraryDTO);
        return new ResponseEntity<>(createdLibrary, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryDTO> getLibraryById(@PathVariable Long id) {
        LibraryDTO libraryDTO = libraryService.getLibraryById(id);
        return ResponseEntity.ok(libraryDTO);
    }

    @GetMapping
    public ResponseEntity<List<LibraryDTO>> getAllLibraries() {
        List<LibraryDTO> libraries = libraryService.getAllLibraries();
        return ResponseEntity.ok(libraries);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryDTO> updateLibrary(@PathVariable Long id, @RequestBody @Valid LibraryDTO libraryDTO) {
        LibraryDTO updatedLibrary = libraryService.updateLibrary(id, libraryDTO);
        return ResponseEntity.ok(updatedLibrary);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        libraryService.deleteLibrary(id);
        return ResponseEntity.noContent().build();
    }
}

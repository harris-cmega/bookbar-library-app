package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;
import io.bookbar.bookbarbackend.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody @Valid AuthorRegistrationDTO authorDTO) {
        AuthorResponseDTO responseDTO = authorService.createAuthor(authorDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<AuthorResponseDTO>> saveAuthors(@RequestBody @Valid List<AuthorRegistrationDTO> authorDTOs) {
        List<AuthorResponseDTO> responseDTOs = authorService.saveAuthors(authorDTOs);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors() {
        List<AuthorResponseDTO> responseDTOs = authorService.getAuthors();
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        AuthorResponseDTO responseDTO = authorService.getAuthorById(id);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorResponseDTO> getAuthorByName(@PathVariable String name) {
        AuthorResponseDTO responseDTO = authorService.getAuthorByName(name);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(@PathVariable Long id, @RequestBody @Valid AuthorRegistrationDTO authorDTO) {
        AuthorResponseDTO responseDTO = authorService.updateAuthor(id, authorDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthorById(@PathVariable Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
}

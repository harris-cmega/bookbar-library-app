package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;
import io.bookbar.bookbarbackend.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/public/authors")
public class AuthorPublicController {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAuthors();
        return ResponseEntity.ok(authors);
    }
}

package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.entities.Author;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private Long id;
    private String title;
    private Author author; // Consider using AuthorDto for better separation of concerns
    private String publisher;
    private String ISBN;
}
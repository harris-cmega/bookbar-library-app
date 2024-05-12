package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.entities.Library;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BooksDto {
    private int ISBN;
    private String title;

    private String language;

    private String publicationDate;

    private Blob image;


    private int pageNumber;

    private double price;

    private String description;

    private Author authorID;

    private Library libraryID;
}
package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long ISBN;
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Publication date is mandatory")
    private String publicationDate;

    private byte[] image;

    @NotNull(message = "Page number is mandatory")
    private int pageNumber;

    @NotNull(message = "Price is mandatory")
    private double price;

    private String description;

    @NotNull(message = "Author ID is mandatory")
    private Long authorId;

    @NotNull(message = "Library ID is mandatory")
    private Long libraryId;

    @NotNull(message = "Publisher ID is mandatory")
    private Long publisherId;
}

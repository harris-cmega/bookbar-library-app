package io.bookbar.bookbarbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Publication date is mandatory")
    private String publication_date;

    private byte[] image;

    @NotNull(message = "Page number is mandatory")
    private int page_number;

    @NotNull(message = "Price is mandatory")
    private double price;

    private String description;

    @NotNull(message = "Author ID is mandatory")
    private Long author_id;
    private String author_name; // Add this field

    @NotNull(message = "Library ID is mandatory")
    private Long library_id;
    private String library_name; // Add this field

    @NotNull(message = "Publisher ID is mandatory")
    private Long publisher_id;
    private String publisher_name; // Add this field
}

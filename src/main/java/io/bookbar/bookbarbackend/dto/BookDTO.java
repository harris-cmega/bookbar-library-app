package io.bookbar.bookbarbackend.dto;

import io.bookbar.bookbarbackend.entities.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BookDTO {
    private Long id;

    @NotNull
    @Size(min = 1, max = 255)
    @NotBlank(message = "Title is mandatory")
    private String title;

    @Size(min = 1, max = 255)
    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Publication date is mandatory")
    private String publication_date;

    private String image;

    @NotNull(message = "Page number is mandatory")
    private int page_number;

    @NotNull(message = "Price is mandatory")
    private double price;

    @Size(max = 2000)
    private String description;

    private Long author_id;
    private String author_name;

    private Long library_id;
    private String library_name;

    private Long publisher_id;
    private String publisher_name;

    private List<CategoryDTO> categories;
}

package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class BookCategoryDTO {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long book_id;
    private String book_title;

    @NotNull(message = "Category ID is mandatory")
    private Long category_id;
    private String category_name;
}

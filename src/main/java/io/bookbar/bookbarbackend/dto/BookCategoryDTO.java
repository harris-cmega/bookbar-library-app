package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class BookCategoryDTO {
    private Long id;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    @NotNull(message = "Category ID is mandatory")
    private Long categoryId;
}

package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Getter
@Setter
public class BookFileDTO {
    private Long id;

    @NotBlank(message = "Filename is mandatory")
    @Size(max = 255, message = "Filename can't be longer than 255 characters")
    private String filename;

    @NotNull(message = "Size is mandatory")
    private Long size;

    @NotBlank(message = "Format is mandatory")
    @Size(max = 255, message = "Format can't be longer than 255 characters")
    private String format;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;
}

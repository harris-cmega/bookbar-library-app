package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
public class RatingDTO {
    private Long id;

    @NotNull(message = "Review is mandatory")
    private Double review;

    @Size(max = 255, message = "Comment can't be longer than 255 characters")
    private String comment;

    @NotNull(message = "Date is mandatory")
    private LocalDateTime date;

    @NotNull(message = "Book ID is mandatory")
    private Long bookId;

    @NotNull(message = "User ID is mandatory")
    private Long userId;
}

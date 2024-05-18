package io.bookbar.bookbarbackend.dto;

import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class CategoryDTO {
    private Long id;

    @NotBlank (message = "Name is mandatory")
    private String name;
}

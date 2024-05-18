package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.CategoryDTO;
import io.bookbar.bookbarbackend.entities.Category;

public class CategoryMapper {
    public static Category toCategoryEntity(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        return category;
    }

    public static CategoryDTO toCategoryDto(Category category) {
        CategoryDTO categoryDto = new CategoryDTO();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        return categoryDto;
    }
}

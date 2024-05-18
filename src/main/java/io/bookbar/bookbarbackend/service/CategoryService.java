package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO categoryDto);
    CategoryDTO getCategoryById(Long id);
    List<CategoryDTO> getAllCategories();
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDto);
    void deleteCategory(Long id);
}


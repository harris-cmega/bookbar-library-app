package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.BookCategoryDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.BookCategory;
import io.bookbar.bookbarbackend.entities.Category;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.BookCategoryMapper;
import io.bookbar.bookbarbackend.repository.BookCategoryRepository;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.CategoryRepository;
import io.bookbar.bookbarbackend.service.BookCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BookCategoryRepository bookCategoryRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public BookCategoryDTO createBookCategory(BookCategoryDTO bookCategoryDto) {
        Book book = bookRepository.findById(bookCategoryDto.getBook_id())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Category category = categoryRepository.findById(bookCategoryDto.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        BookCategory bookCategory = BookCategoryMapper.toBookCategoryEntity(bookCategoryDto);
        bookCategory.setBook(book);
        bookCategory.setCategory(category);
        BookCategory savedBookCategory = bookCategoryRepository.save(bookCategory);
        return BookCategoryMapper.toBookCategoryDto(savedBookCategory);
    }

    @Override
    public BookCategoryDTO getBookCategoryById(Long id) {
        BookCategory bookCategory = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookCategory not found"));
        return BookCategoryMapper.toBookCategoryDto(bookCategory);
    }

    @Override
    public Page<BookCategoryDTO> getAllBookCategories(Pageable pageable) {
        Page<BookCategory> bookCategories = bookCategoryRepository.findAll(pageable);
        return bookCategories.map(BookCategoryMapper::toBookCategoryDto);
    }

    @Override
    public BookCategoryDTO updateBookCategory(Long id, BookCategoryDTO bookCategoryDto) {
        BookCategory bookCategory = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookCategory not found"));

        Book book = bookRepository.findById(bookCategoryDto.getBook_id())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        Category category = categoryRepository.findById(bookCategoryDto.getCategory_id())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        bookCategory.setBook(book);
        bookCategory.setCategory(category);
        BookCategory updatedBookCategory = bookCategoryRepository.save(bookCategory);
        return BookCategoryMapper.toBookCategoryDto(updatedBookCategory);
    }

    @Override
    public void deleteBookCategory(Long id) {
        BookCategory bookCategory = bookCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookCategory not found"));
        bookCategoryRepository.delete(bookCategory);
    }

    @Override
    public List<BookCategoryDTO> getBookCategoriesByBookId(Long bookId) {
        List<BookCategory> bookCategories = bookCategoryRepository.findByBook_Id(bookId);
        return bookCategories.stream()
                .map(BookCategoryMapper::toBookCategoryDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookCategoryDTO> getBookCategoriesByCategoryId(Long categoryId) {
        List<BookCategory> bookCategories = bookCategoryRepository.findByCategory_Id(categoryId);
        return bookCategories.stream()
                .map(BookCategoryMapper::toBookCategoryDto)
                .collect(Collectors.toList());
    }
}

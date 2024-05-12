package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.BooksDto;
import io.bookbar.bookbarbackend.entities.Books;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.BooksMapper;
import io.bookbar.bookbarbackend.repository.BooksRepository;
import io.bookbar.bookbarbackend.service.BooksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BooksServiceImpl implements BooksService {

    private BooksRepository booksRepository;

    @Override
    public BooksDto createBook(BooksDto booksDto) {
        Books books = BooksMapper.mapToBooks(booksDto);
        Books savedBooks = booksRepository.save(books);

        return BooksMapper.mapToBooksDto(savedBooks);
    }

    @Override
    public BooksDto getBooksById(Long booksId) {
        Books books = booksRepository.findById(booksId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Book not  with this ID: " + booksId));
        return BooksMapper.mapToBooksDto(books);
    }

    @Override
    public List<BooksDto> getAllBooks() {
        List<Books> books = booksRepository.findAll();
        return books.stream().map(employee -> BooksMapper.mapToBooksDto((Books) books)).collect(Collectors.toList());
    }

    @Override
    public BooksDto updateBook(Long booksId, BooksDto updatedBook) {
        Books books = booksRepository.findById(booksId).orElseThrow(
                () -> new ResourceNotFoundException("Book not  with this ID: " + booksId)
        );

        books.setTitle(updatedBook.getTitle());
        books.setAuthorID(updatedBook.getAuthorID());
        books.setISBN(updatedBook.getISBN());

        Books updatedBooksObj = booksRepository.save(books);

        return BooksMapper.mapToBooksDto(updatedBooksObj);
    }

    @Override
    public void deleteBook(Long booksId) {

        Books books = booksRepository.findById(booksId).orElseThrow(
                () -> new ResourceNotFoundException("Book not  with this ID: " + booksId)
        );

        booksRepository.deleteById(booksId);
    }


}

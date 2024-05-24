package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.BookDTO;
import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Library;
import io.bookbar.bookbarbackend.entities.Publisher;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.BookMapper;
import io.bookbar.bookbarbackend.repository.AuthorRepository;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.LibraryRepository;
import io.bookbar.bookbarbackend.repository.PublisherRepository;
import io.bookbar.bookbarbackend.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public BookDTO createBook(BookDTO bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthor_id())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        Library library = libraryRepository.findById(bookDto.getLibrary_id())
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher_id())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        Book book = BookMapper.toBookEntity(bookDto, author, library, publisher);
        Book savedBook = bookRepository.save(book);
        return BookMapper.toBookDto(savedBook);
    }

    @Override
    public BookDTO getBooksById(Long booksId) {
        Book book = bookRepository.findById(booksId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return BookMapper.toBookDto(book);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(BookMapper::toBookDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDTO updateBook(Long booksId, BookDTO bookDto) {
        Book book = bookRepository.findById(booksId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        Author author = authorRepository.findById(bookDto.getAuthor_id())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        Library library = libraryRepository.findById(bookDto.getLibrary_id())
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        Publisher publisher = publisherRepository.findById(bookDto.getPublisher_id())
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));

        book.setTitle(bookDto.getTitle());
        book.setLanguage(bookDto.getLanguage());
        book.setPublication_date(bookDto.getPublication_date());
        book.setImage(bookDto.getImage());
        book.setPage_number(bookDto.getPage_number());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());
        book.setAuthor(author);
        book.setLibrary(library);
        book.setPublisher(publisher);

        Book updatedBook = bookRepository.save(book);
        return BookMapper.toBookDto(updatedBook);
    }

    @Override
    public void deleteBook(Long booksId) {
        Book book = bookRepository.findById(booksId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookRepository.delete(book);
    }
}

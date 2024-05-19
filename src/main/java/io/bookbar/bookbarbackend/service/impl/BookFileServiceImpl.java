package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.BookFileDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.BookFile;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.BookFileMapper;
import io.bookbar.bookbarbackend.repository.BookFileRepository;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.service.BookFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookFileServiceImpl implements BookFileService {

    private final BookFileRepository bookFileRepository;
    private final BookRepository bookRepository;

    @Override
    public BookFileDTO createBookFile(BookFileDTO bookFileDto) {
        Book book = bookRepository.findById(bookFileDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        BookFile bookFile = BookFileMapper.toBookFileEntity(bookFileDto);
        bookFile.setBook(book);

        BookFile savedBookFile = bookFileRepository.save(bookFile);
        return BookFileMapper.toBookFileDto(savedBookFile);
    }

    @Override
    public BookFileDTO getBookFileById(Long id) {
        BookFile bookFile = bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookFile not found"));
        return BookFileMapper.toBookFileDto(bookFile);
    }

    @Override
    public List<BookFileDTO> getAllBookFiles() {
        List<BookFile> bookFiles = bookFileRepository.findAll();
        return bookFiles.stream()
                .map(BookFileMapper::toBookFileDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookFileDTO updateBookFile(Long id, BookFileDTO bookFileDto) {
        BookFile bookFile = bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookFile not found"));

        Book book = bookRepository.findById(bookFileDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        bookFile.setFilename(bookFileDto.getFilename());
        bookFile.setSize(bookFileDto.getSize());
        bookFile.setFormat(bookFileDto.getFormat());
        bookFile.setBook(book);

        BookFile updatedBookFile = bookFileRepository.save(bookFile);
        return BookFileMapper.toBookFileDto(updatedBookFile);
    }

    @Override
    public void deleteBookFile(Long id) {
        BookFile bookFile = bookFileRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookFile not found"));
        bookFileRepository.delete(bookFile);
    }
}

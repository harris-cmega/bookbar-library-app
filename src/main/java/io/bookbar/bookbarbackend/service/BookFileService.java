package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.BookFileDTO;

import java.util.List;

public interface BookFileService {
    BookFileDTO createBookFile(BookFileDTO bookFileDto);
    BookFileDTO getBookFileById(Long id);
    List<BookFileDTO> getAllBookFiles();
    BookFileDTO updateBookFile(Long id, BookFileDTO bookFileDto);
    void deleteBookFile(Long id);
}

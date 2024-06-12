package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.BookFileDTO;
import io.bookbar.bookbarbackend.entities.BookFile;

public class BookFileMapper {
    public static BookFile toBookFileEntity(BookFileDTO bookFileDto) {
        BookFile bookFile = new BookFile();
        bookFile.setId(bookFileDto.getId());
        bookFile.setFilename(bookFileDto.getFilename());
        bookFile.setSize(bookFileDto.getSize());
        bookFile.setFormat(bookFileDto.getFormat());
        bookFile.setEpubFile(bookFileDto.getEpubFile());
        return bookFile;
    }

    public static BookFileDTO toBookFileDto(BookFile bookFile) {
        BookFileDTO bookFileDto = new BookFileDTO();
        bookFileDto.setId(bookFile.getId());
        bookFileDto.setFilename(bookFile.getFilename());
        bookFileDto.setSize(bookFile.getSize());
        bookFileDto.setFormat(bookFile.getFormat());
        bookFileDto.setEpubFile(bookFile.getEpubFile());
        bookFileDto.setBookId(bookFile.getBook().getId());
        return bookFileDto;
    }
}

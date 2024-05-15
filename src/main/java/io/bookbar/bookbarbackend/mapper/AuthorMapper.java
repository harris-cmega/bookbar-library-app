package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;
import io.bookbar.bookbarbackend.entities.Author;

public class AuthorMapper {
    public static Author toAuthor(AuthorRegistrationDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        return author;
    }

    public static AuthorResponseDTO toAuthorResponseDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId((long) author.getId());
        dto.setName(author.getName());
        return dto;
    }
}
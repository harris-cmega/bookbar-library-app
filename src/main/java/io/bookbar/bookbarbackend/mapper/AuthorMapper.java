package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;
import io.bookbar.bookbarbackend.entities.Author;

public class AuthorMapper {
    public static Author toAuthor(AuthorRegistrationDTO dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setEmail(dto.getEmail());
        author.setBiography(dto.getBiography());
        author.setNationality(dto.getNationality());
        author.setBirthDate(dto.getBirthDate());
        author.setDeathDate(dto.getDeathDate());
        return author;
    }

    public static AuthorResponseDTO toAuthorResponseDTO(Author author) {
        AuthorResponseDTO dto = new AuthorResponseDTO();
        dto.setId(author.getId());
        dto.setName(author.getName());
        dto.setEmail(author.getEmail());
        dto.setBiography(author.getBiography());
        dto.setNationality(author.getNationality());
        dto.setBirthDate(author.getBirthDate());
        dto.setDeathDate(author.getDeathDate());
        return dto;
    }
}

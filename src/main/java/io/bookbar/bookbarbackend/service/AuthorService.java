package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;

import java.util.List;

public interface AuthorService {
    AuthorResponseDTO createAuthor(AuthorRegistrationDTO authorDTO);
    List<AuthorResponseDTO> saveAuthors(List<AuthorRegistrationDTO> authorDTOs);
    List<AuthorResponseDTO> getAuthors();
    AuthorResponseDTO getAuthorById(Long id);
    AuthorResponseDTO getAuthorByName(String name);
    AuthorResponseDTO updateAuthor(Long id, AuthorRegistrationDTO authorDTO);
    void deleteAuthorById(Long id);
}

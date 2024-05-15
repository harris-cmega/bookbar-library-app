package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.AuthorRegistrationDTO;
import io.bookbar.bookbarbackend.dto.AuthorResponseDTO;
import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.AuthorMapper;
import io.bookbar.bookbarbackend.repository.AuthorRepository;
import io.bookbar.bookbarbackend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorResponseDTO createAuthor(AuthorRegistrationDTO authorDTO) {
        Author author = AuthorMapper.toAuthor(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return AuthorMapper.toAuthorResponseDTO(savedAuthor);
    }

    @Override
    public List<AuthorResponseDTO> saveAuthors(List<AuthorRegistrationDTO> authorDTOs) {
        List<Author> authors = authorDTOs.stream()
                .map(AuthorMapper::toAuthor)
                .collect(Collectors.toList());
        List<Author> savedAuthors = authorRepository.saveAll(authors);
        return savedAuthors.stream()
                .map(AuthorMapper::toAuthorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorResponseDTO> getAuthors() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(AuthorMapper::toAuthorResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        return AuthorMapper.toAuthorResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO getAuthorByName(String name) {
        Author author = authorRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with name " + name));
        return AuthorMapper.toAuthorResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRegistrationDTO authorDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        author.setName(authorDTO.getName());
        Author updatedAuthor = authorRepository.save(author);
        return AuthorMapper.toAuthorResponseDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthorById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id " + id);
        }
        authorRepository.deleteById(id);
    }
}

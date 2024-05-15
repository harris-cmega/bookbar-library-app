package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.LibraryDTO;

import java.util.List;

public interface LibraryService {
    LibraryDTO createLibrary(LibraryDTO libraryDTO);
    LibraryDTO getLibraryById(Long id);
    List<LibraryDTO> getAllLibraries();
    LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO);
    void deleteLibrary(Long id);
}

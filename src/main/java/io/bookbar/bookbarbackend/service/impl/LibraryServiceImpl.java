package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.LibraryDTO;
import io.bookbar.bookbarbackend.entities.Library;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.LibraryMapper;
import io.bookbar.bookbarbackend.repository.LibraryRepository;
import io.bookbar.bookbarbackend.service.LibraryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;

    @Override
    public LibraryDTO createLibrary(LibraryDTO libraryDTO) {
        Library library = LibraryMapper.toLibraryEntity(libraryDTO);
        Library savedLibrary = libraryRepository.save(library);
        return LibraryMapper.toLibraryDTO(savedLibrary);
    }

    @Override
    public LibraryDTO getLibraryById(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        return LibraryMapper.toLibraryDTO(library);
    }

    @Override
    public List<LibraryDTO> getAllLibraries() {
        List<Library> libraries = libraryRepository.findAll();
        return libraries.stream()
                .map(LibraryMapper::toLibraryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LibraryDTO updateLibrary(Long id, LibraryDTO libraryDTO) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        library.setName(libraryDTO.getName());
        library.setAddress(libraryDTO.getAddress());
        library.setCity(libraryDTO.getCity());
        library.setState(libraryDTO.getState());
        library.setZip_code(libraryDTO.getZip_code());
        library.setPhone(libraryDTO.getPhone());
        library.setEmail(libraryDTO.getEmail());
        library.setOpening_hours(libraryDTO.getOpening_hours());
        Library updatedLibrary = libraryRepository.save(library);
        return LibraryMapper.toLibraryDTO(updatedLibrary);
    }

    @Override
    public void deleteLibrary(Long id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Library not found"));
        libraryRepository.delete(library);
    }
}

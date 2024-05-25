package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.LibraryDTO;
import io.bookbar.bookbarbackend.entities.Library;

public class LibraryMapper {

    public static LibraryDTO toLibraryDTO(Library library) {
        LibraryDTO dto = new LibraryDTO();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setAddress(library.getAddress());
        dto.setCity(library.getCity());
        dto.setState(library.getState());
        dto.setZip_code(library.getZip_code());
        dto.setPhone(library.getPhone());
        dto.setEmail(library.getEmail());
        dto.setOpening_hours(library.getOpening_hours());
        return dto;
    }

    public static Library toLibraryEntity(LibraryDTO dto) {
        Library library = new Library();
        library.setId(dto.getId());
        library.setName(dto.getName());
        library.setAddress(dto.getAddress());
        library.setCity(dto.getCity());
        library.setState(dto.getState());
        library.setZip_code(dto.getZip_code());
        library.setPhone(dto.getPhone());
        library.setEmail(dto.getEmail());
        library.setOpening_hours(dto.getOpening_hours());
        return library;
    }
}

package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.entities.Library;
import io.bookbar.bookbarbackend.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libraries")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        Library createdLibrary = libraryService.createLibrary(library);
        return new ResponseEntity<>(createdLibrary, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Library>> getLibraries() {
        List<Library> libraries = libraryService.getLibraries();
        return new ResponseEntity<>(libraries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable long id) {
        Library library = libraryService.getLibraryById(id);
        return new ResponseEntity<>(library, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> updateLibrary(@PathVariable long id, @RequestBody Library library) {
        library.setId(id);
        Library updatedLibrary = libraryService.updateLibrary(library);
        return new ResponseEntity<>(updatedLibrary, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibraryById(@PathVariable long id) {
        libraryService.deleteLibraryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

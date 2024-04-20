package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.entities.Library;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepo;

    public Library createLibrary(Library library) {
        return libraryRepo.save(library);
    }

    public List<Library> saveLibraries(List <Library> libraries){
        return libraryRepo.saveAll(libraries);
    }

    public List<Library> getLibraries(){
        return libraryRepo.findAll();
    }

    public Library getLibraryById(long id){
        return libraryRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Library with given id does not exist: " + id));
    }

    public Library getLibraryByName(String name){
        return libraryRepo.findByName(name);
    }


    public Library updateLibrary(Library library){
        Library existingLibrary = getLibraryById(library.getId());
        existingLibrary.setName(library.getName());
        existingLibrary.setEmail(library.getEmail());
        existingLibrary.setPassword(library.getPassword());
        existingLibrary.setImage(library.getImage());
        existingLibrary.setStreet(library.getStreet());
        existingLibrary.setCity(library.getCity());
        existingLibrary.setCountry(library.getCountry());
        return libraryRepo.save(existingLibrary);
    }
    public void deleteLibraryById(long id){
        libraryRepo.deleteById(id);
    }
}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepo;

    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    public List<Author> saveAuthors(List <Author> authors){
        return authorRepo.saveAll(authors);
    }

    public List<Author> getAuthors(){
        return authorRepo.findAll();
    }

    public Author getAuthorById(int id){
        return authorRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Author with given id does not exist: " + id));
    }

    public Author getAuthorByName(String name){
        return authorRepo.findByName(name);
    }

    public Author updateAuthor(Author author){
        Author existingAuthor = authorRepo.findById(author.getAuthorID()).orElseThrow(() -> new ResourceNotFoundException("Author with given id does not exist"));
        existingAuthor.setName(author.getName());
        existingAuthor.setEmail(author.getEmail());
        existingAuthor.setPassword(author.getPassword());
        existingAuthor.setDescription(author.getDescription());
        existingAuthor.setImage(author.getImage());
        existingAuthor.setCountry(author.getCountry());
        return authorRepo.save(existingAuthor);
    }

    public void deleteAuthorById(int id){
        authorRepo.deleteById(id);
    }


}


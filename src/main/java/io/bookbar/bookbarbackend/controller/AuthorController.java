package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.entities.Author;
import io.bookbar.bookbarbackend.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/createAuthor")
    public Author createAuthor(@RequestBody Author author){
        return authorService.createAuthor(author);
    }

    @PostMapping("/saveAuthors")
    public List<Author> saveAuthors(@RequestBody List<Author> authors){
        return authorService.saveAuthors(authors);
    }

    @GetMapping("/authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/author/{id}")
    public Author getAuthor(@PathVariable int id){
        return authorService.getAuthorById(id);
    }

    @GetMapping("/author/{name}")
    public Author getAuthorByName(@PathVariable String name){
        return authorService.getAuthorByName(name);
    }

    @PostMapping("/updateAuthor")
    public Author updateAuthor(@RequestBody Author author){
        return authorService.updateAuthor(author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAuthor(@PathVariable int id){
        authorService.deleteAuthorById(id);
    }
}

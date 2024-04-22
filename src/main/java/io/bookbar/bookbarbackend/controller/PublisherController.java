package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.entities.Publisher;
import io.bookbar.bookbarbackend.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @PostMapping("/createPublisher")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.createPublisher(publisher);
    }

    @GetMapping("/publisher/{id}")
    public Publisher getPublisher(@PathVariable int id){
        return publisherService.getPublisherById(id);
    }

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers(){
        return publisherService.getAllPublishers();
    }

    @GetMapping("/publisher/{name}")
    public Publisher getPublisherByName(@PathVariable String name){
        return publisherService.getPublisherByName(name);
    }

    @PostMapping("/updatePublisher")
    public Publisher updateAuthor(@RequestBody Publisher publisher) {
        return publisherService.updatePublisher(publisher);
    }

    @DeleteMapping("deletePublisher/{id}")
    public void deletePublisher(@PathVariable int id) {
        publisherService.deletePublisher(id);
    }
}

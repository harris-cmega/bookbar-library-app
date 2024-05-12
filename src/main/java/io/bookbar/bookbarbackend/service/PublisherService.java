package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.Publisher;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepo;

    public Publisher createPublisher(Publisher publisher){
        return publisherRepo.save(publisher);
    }

    public Publisher getPublisherById(int id){
        return publisherRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Publisher with given id does not exist: " + id));
    }

    public List<Publisher> getAllPublishers(){
        return publisherRepo.findAll();
    }

    public Publisher getPublisherByName(String name){
        return publisherRepo.findByName(name);
    }

    public Publisher updatePublisher(Publisher publisher){
        Publisher existingPublisher = publisherRepo.findById(publisher.getId()).orElseThrow(() -> new ResourceNotFoundException("Publisher with given id does not exist: " + publisher.getId()));
        existingPublisher.setName(publisher.getName());
        existingPublisher.setEmail(publisher.getEmail());
        existingPublisher.setPassword(publisher.getPassword());
        existingPublisher.setCountry(publisher.getCountry());
        existingPublisher.setPhoneNumber(publisher.getPhoneNumber());
        return publisherRepo.save(existingPublisher);
    }
    public void deletePublisher(int id){
        publisherRepo.deleteById(id);
    }


}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.PublisherDTO;

import java.util.List;

public interface PublisherService {
    PublisherDTO createPublisher(PublisherDTO publisherDTO);
    PublisherDTO getPublisherById(Long id);
    List<PublisherDTO> getAllPublishers();
    PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO);
    void deletePublisher(Long id);
}

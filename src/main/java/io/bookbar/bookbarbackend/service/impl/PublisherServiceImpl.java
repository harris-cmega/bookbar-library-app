package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.PublisherDTO;
import io.bookbar.bookbarbackend.entities.Publisher;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.PublisherMapper;
import io.bookbar.bookbarbackend.repository.PublisherRepository;
import io.bookbar.bookbarbackend.service.PublisherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public PublisherDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = PublisherMapper.toPublisherEntity(publisherDTO);
        Publisher savedPublisher = publisherRepository.save(publisher);
        return PublisherMapper.toPublisherDTO(savedPublisher);
    }

    @Override
    public PublisherDTO getPublisherById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
        return PublisherMapper.toPublisherDTO(publisher);
    }

    @Override
    public List<PublisherDTO> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        return publishers.stream()
                .map(PublisherMapper::toPublisherDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PublisherDTO updatePublisher(Long id, PublisherDTO publisherDTO) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
        publisher.setName(publisherDTO.getName());
        publisher.setAddress(publisherDTO.getAddress());
        publisher.setCity(publisherDTO.getCity());
        publisher.setState(publisherDTO.getState());
        publisher.setZipCode(publisherDTO.getZipCode());
        publisher.setPhone(publisherDTO.getPhone());
        publisher.setEmail(publisherDTO.getEmail());
        publisher.setWebsite(publisherDTO.getWebsite());
        Publisher updatedPublisher = publisherRepository.save(publisher);
        return PublisherMapper.toPublisherDTO(updatedPublisher);
    }

    @Override
    public void deletePublisher(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Publisher not found"));
        publisherRepository.delete(publisher);
    }
}

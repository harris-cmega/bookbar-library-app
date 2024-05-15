package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.PublisherDTO;
import io.bookbar.bookbarbackend.entities.Publisher;

public class PublisherMapper {

    public static PublisherDTO toPublisherDTO(Publisher publisher) {
        PublisherDTO dto = new PublisherDTO();
        dto.setId(publisher.getId());
        dto.setName(publisher.getName());
        dto.setAddress(publisher.getAddress());
        dto.setCity(publisher.getCity());
        dto.setState(publisher.getState());
        dto.setZipCode(publisher.getZipCode());
        dto.setPhone(publisher.getPhone());
        dto.setEmail(publisher.getEmail());
        dto.setWebsite(publisher.getWebsite());
        return dto;
    }

    public static Publisher toPublisherEntity(PublisherDTO dto) {
        Publisher publisher = new Publisher();
        publisher.setId(dto.getId());
        publisher.setName(dto.getName());
        publisher.setAddress(dto.getAddress());
        publisher.setCity(dto.getCity());
        publisher.setState(dto.getState());
        publisher.setZipCode(dto.getZipCode());
        publisher.setPhone(dto.getPhone());
        publisher.setEmail(dto.getEmail());
        publisher.setWebsite(dto.getWebsite());
        return publisher;
    }
}

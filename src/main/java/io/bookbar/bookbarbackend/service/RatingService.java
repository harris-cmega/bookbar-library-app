package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.dto.RatingDTO;

import java.util.List;

public interface RatingService {
    RatingDTO createRating(RatingDTO ratingDto);
    RatingDTO getRatingById(Long id);
    List<RatingDTO> getAllRatings();
    RatingDTO updateRating(Long id, RatingDTO ratingDto);
    void deleteRating(Long id);
}

package io.bookbar.bookbarbackend.service;

import io.bookbar.bookbarbackend.entities.Rating;
import java.util.List;

public interface RatingService {
    Rating saveRating(Rating rating);
    List<Rating> getAllRatings();
    Rating getRatingById(Long id);
    Rating updateRating(Long id, Rating rating);
    void deleteRating(Long id);
}
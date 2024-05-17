package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.entities.Rating;
import io.bookbar.bookbarbackend.repository.RatingRepository;
import io.bookbar.bookbarbackend.service.RatingService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public Rating saveRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating getRatingById(Long id) {
        return ratingRepository.findById(id).orElse(null);
    }

    @Override
    public Rating updateRating(Long id, Rating rating) {
        Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> new RuntimeException("Rating not found"));
        existingRating.setReview(rating.getReview());
        existingRating.setComment(rating.getComment());
        existingRating.setDate(rating.getDate());
        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteRating(Long id) {
        ratingRepository.deleteById(id);
    }
}
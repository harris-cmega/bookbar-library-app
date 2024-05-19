package io.bookbar.bookbarbackend.mapper;

import io.bookbar.bookbarbackend.dto.RatingDTO;
import io.bookbar.bookbarbackend.entities.Rating;

public class RatingMapper {
    public static Rating toRatingEntity(RatingDTO ratingDto) {
        Rating rating = new Rating();
        rating.setId(ratingDto.getId());
        rating.setReview(ratingDto.getReview());
        rating.setComment(ratingDto.getComment());
        rating.setDate(ratingDto.getDate());
        return rating;
    }

    public static RatingDTO toRatingDto(Rating rating) {
        RatingDTO ratingDto = new RatingDTO();
        ratingDto.setId(rating.getId());
        ratingDto.setReview(rating.getReview());
        ratingDto.setComment(rating.getComment());
        ratingDto.setDate(rating.getDate());
        ratingDto.setBookId(rating.getBook().getId());
        ratingDto.setUserId(rating.getUser().getId());
        return ratingDto;
    }
}

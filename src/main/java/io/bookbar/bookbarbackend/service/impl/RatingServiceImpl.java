package io.bookbar.bookbarbackend.service.impl;

import io.bookbar.bookbarbackend.dto.RatingDTO;
import io.bookbar.bookbarbackend.entities.Book;
import io.bookbar.bookbarbackend.entities.Rating;
import io.bookbar.bookbarbackend.entities.User;
import io.bookbar.bookbarbackend.exception.ResourceNotFoundException;
import io.bookbar.bookbarbackend.mapper.RatingMapper;
import io.bookbar.bookbarbackend.repository.BookRepository;
import io.bookbar.bookbarbackend.repository.RatingRepository;
import io.bookbar.bookbarbackend.repository.UserRepository;
import io.bookbar.bookbarbackend.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    @Override
    public RatingDTO createRating(RatingDTO ratingDto) {
        Book book = bookRepository.findById(ratingDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        User user = userRepository.findById(ratingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Rating rating = RatingMapper.toRatingEntity(ratingDto);
        rating.setBook(book);
        rating.setUser(user);
        Rating savedRating = ratingRepository.save(rating);
        return RatingMapper.toRatingDto(savedRating);
    }

    @Override
    public RatingDTO getRatingById(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
        return RatingMapper.toRatingDto(rating);
    }

    @Override
    public List<RatingDTO> getAllRatings() {
        List<Rating> ratings = ratingRepository.findAll();
        return ratings.stream()
                .map(RatingMapper::toRatingDto)
                .collect(Collectors.toList());
    }

    @Override
    public RatingDTO updateRating(Long id, RatingDTO ratingDto) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));

        Book book = bookRepository.findById(ratingDto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        User user = userRepository.findById(ratingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        rating.setReview(ratingDto.getReview());
        rating.setComment(ratingDto.getComment());
        rating.setDate(ratingDto.getDate());
        rating.setBook(book);
        rating.setUser(user);

        Rating updatedRating = ratingRepository.save(rating);
        return RatingMapper.toRatingDto(updatedRating);
    }

    @Override
    public void deleteRating(Long id) {
        Rating rating = ratingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rating not found"));
        ratingRepository.delete(rating);
    }
}

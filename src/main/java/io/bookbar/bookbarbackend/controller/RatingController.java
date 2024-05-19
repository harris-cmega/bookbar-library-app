package io.bookbar.bookbarbackend.controller;

import io.bookbar.bookbarbackend.dto.RatingDTO;
import io.bookbar.bookbarbackend.service.RatingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ratings")
public class RatingController {

    private final RatingService ratingService;

    @PostMapping
    public ResponseEntity<RatingDTO> createRating(@RequestBody @Valid RatingDTO ratingDto) {
        RatingDTO savedRating = ratingService.createRating(ratingDto);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<RatingDTO> getRatingById(@PathVariable("id") Long ratingId) {
        RatingDTO ratingDto = ratingService.getRatingById(ratingId);
        return ResponseEntity.ok(ratingDto);
    }

    @GetMapping
    public ResponseEntity<List<RatingDTO>> getAllRatings() {
        List<RatingDTO> ratings = ratingService.getAllRatings();
        return ResponseEntity.ok(ratings);
    }

    @PutMapping("{id}")
    public ResponseEntity<RatingDTO> updateRating(@PathVariable("id") Long ratingId, @RequestBody @Valid RatingDTO ratingDto) {
        RatingDTO updatedRating = ratingService.updateRating(ratingId, ratingDto);
        return ResponseEntity.ok(updatedRating);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteRating(@PathVariable("id") Long ratingId) {
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok("Rating deleted");
    }
}

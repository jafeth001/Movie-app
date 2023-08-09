package com.backendengineer.springfordemo.Service;

import com.backendengineer.springfordemo.Entity.Review;
import com.backendengineer.springfordemo.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public Review postreview(Review review) {
        return reviewRepository.save(review);
    }

    public Optional<Review> getreviewbyid(Long id) {
        return reviewRepository.findById(id);
    }

    public Review updatebyid(Long id, Review review) {
        Review reviews = reviewRepository.findById(id).get();
        if (Objects.nonNull(review.getBody()) && !"".equalsIgnoreCase(review.getBody())) {
            reviews.setBody(review.getBody());
        }
        return reviewRepository.save(reviews);
    }

}

package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Review;

public interface ReviewService {
    List<Review> getReviewByMovie(Long movieId);
    List<Review> getReviewByUserId(Long user_id);
    Review addReview(Review review);
    void deleteReview(Long reviewId);
    Review updateReview(Long reviewId, Review review);
}

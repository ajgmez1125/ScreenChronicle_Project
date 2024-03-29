package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Review;

public interface ReviewService
{
        List<Review> getReviewByMovie(Long movie_id);
        void updateReview(Review review);
        void addReview(Review review);
        void deleteReview(Review review);
}

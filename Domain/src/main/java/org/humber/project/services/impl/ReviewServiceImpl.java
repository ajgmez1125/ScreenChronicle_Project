package org.humber.project.services.impl;

import lombok.RequiredArgsConstructor;
import org.humber.project.domain.Review;
import org.humber.project.services.ReviewJPAService;
import org.humber.project.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService
{
    private ReviewJPAService reviewJPAService;

    @Autowired
    public ReviewServiceImpl(ReviewJPAService reviewJPAService)
    {
        this.reviewJPAService = reviewJPAService;
    }

    @Override
    public List<Review> getReviewByMovie(Long movie_id) {
        return this.reviewJPAService.getReviewsByMovie(movie_id);
    }

    @Override
    public List<Review> getReviewByUserId(Long user_id) {
        return reviewJPAService.getReviewsByUserId(user_id);
    }

    @Override
    public Review updateReview(Long reviewId, Review review) {
        if (reviewJPAService.exists(reviewId)) {
            review.setReview_id(reviewId);
            return reviewJPAService.save(review);
        } else {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
    }

    @Override
    public Review addReview(Review review) {
        return reviewJPAService.save(review);
    }

    @Override
    public void deleteReview(Long reviewId) {
        if (reviewJPAService.exists(reviewId)) {
            reviewJPAService.deleteById(reviewId);
        } else {
            throw new IllegalArgumentException("Review not found with ID: " + reviewId);
        }
    }
}

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
    public void updateReview(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateReview'");
    }

    @Override
    public void addReview(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addReview'");
    }

    @Override
    public void deleteReview(Review review) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteReview'");
    }
}

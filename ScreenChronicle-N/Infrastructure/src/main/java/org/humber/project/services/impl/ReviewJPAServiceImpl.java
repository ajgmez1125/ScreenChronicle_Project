package org.humber.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.humber.project.domain.Review;
import org.humber.project.repositories.ReviewJPARepository;
import org.humber.project.repositories.entities.ReviewEntity;
import org.humber.project.services.ReviewJPAService;
import org.humber.project.transformers.MovieEntityTransformer;
import org.humber.project.transformers.ReviewEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewJPAServiceImpl implements ReviewJPAService
{
    private ReviewJPARepository reviewJPARepository;

    @Autowired
    public ReviewJPAServiceImpl(ReviewJPARepository reviewJPARepository)
    {
        this.reviewJPARepository = reviewJPARepository;
    }

    @Override
    public List<Review> getReviewsByMovie(Long movie_id)
    {
        List<ReviewEntity> reviewList = this.reviewJPARepository.findByMovieId(movie_id);
        return Optional.of(reviewList)
                .map(ReviewEntityTransformer::transformToReviews)
                .orElse(null);
    }
}

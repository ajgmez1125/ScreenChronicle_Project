package org.humber.project.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.humber.project.domain.Review;
import org.humber.project.repositories.ReviewJPARepository;
import org.humber.project.repositories.entities.ReviewEntity;
import org.humber.project.repositories.UserJPARepository;
import org.humber.project.repositories.MovieJPARepository;
import org.humber.project.services.ReviewJPAService;
import org.humber.project.transformers.ReviewEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewJPAServiceImpl implements ReviewJPAService {

    private final ReviewJPARepository reviewRepository;
    private final UserJPARepository userJPARepository;
    private final MovieJPARepository movieJPARepository;

    @Autowired
    public ReviewJPAServiceImpl(ReviewJPARepository reviewRepository, 
                                UserJPARepository userJPARepository,
                                MovieJPARepository movieJPARepository) {
        this.reviewRepository = reviewRepository;
        this.userJPARepository = userJPARepository;
        this.movieJPARepository = movieJPARepository;
    }

    @Override
    public List<Review> getReviewsByMovie(Long movie_id) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByMovieId(movie_id);
        return reviewEntities.stream()
                .map(ReviewEntityTransformer::transformToReview)
                .collect(Collectors.toList());
    }

    @Override
    public List<Review> getReviewsByUserId(Long user_id) {
        List<ReviewEntity> reviewEntities = reviewRepository.findByUserId(user_id);
        return reviewEntities.stream()
                .map(ReviewEntityTransformer::transformToReview)
                .collect(Collectors.toList());
    }

    @Override
    public Review save(Review review) {
        ReviewEntity reviewEntity = ReviewEntityTransformer.transformToReviewEntity(review, userJPARepository, movieJPARepository);
        return ReviewEntityTransformer.transformToReview(reviewRepository.save(reviewEntity));
    }

    @Override
    public Review update(Review review) {
        ReviewEntity reviewEntity = ReviewEntityTransformer.transformToReviewEntity(review, userJPARepository, movieJPARepository);
        return ReviewEntityTransformer.transformToReview(reviewRepository.save(reviewEntity));
    }

    @Override
    public void deleteById(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public boolean exists(Long reviewId) {
        return reviewRepository.existsById(reviewId);
    }
}

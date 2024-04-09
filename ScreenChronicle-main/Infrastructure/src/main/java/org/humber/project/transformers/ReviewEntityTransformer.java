package org.humber.project.transformers;

import java.util.List;
import java.util.stream.Collectors;

import org.humber.project.domain.Review;
import org.humber.project.repositories.MovieJPARepository;
import org.humber.project.repositories.UserJPARepository;
import org.humber.project.repositories.entities.MovieEntity;
import org.humber.project.repositories.entities.ReviewEntity;
import org.humber.project.repositories.entities.UserEntity;

public class ReviewEntityTransformer
{
    public static Review transformToReview(ReviewEntity reviewEntity) {
        return Review.builder()
                .review_id(reviewEntity.getReview_id())
                .user_id(reviewEntity.getUser().getUser_id())
                .movie_id(reviewEntity.getMovie().getMovie_id())
                .review(reviewEntity.getReviewText())
                .rating(reviewEntity.getRating())
                .build();
    }

    public static ReviewEntity transformToReviewEntity(Review review, UserJPARepository userJPARepository, MovieJPARepository movieJPARepository)
    {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReview_id(review.getReview_id());

        UserEntity user = userJPARepository.findById(review.getUser_id()).orElse(null);
        reviewEntity.setUser(user);

        MovieEntity movie = movieJPARepository.findById(review.getMovie_id()).orElse(null);
        reviewEntity.setMovie(movie);

        return reviewEntity;
    }

    public static List<Review> transformToReviews(List<ReviewEntity> entities)
    {
        return entities.stream()
        .map(ReviewEntityTransformer::transformToReview)
        .collect(Collectors.toList());
    }
}

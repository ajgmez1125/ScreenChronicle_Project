package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Review;

public interface ReviewJPAService {
    List<Review> getReviewsByMovie(Long movie_id);
}

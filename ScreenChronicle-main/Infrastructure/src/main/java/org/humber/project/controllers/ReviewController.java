package org.humber.project.controllers;

import org.humber.project.services.ReviewService;
import org.humber.project.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService)
    {
        this.reviewService = reviewService;
    }

    @GetMapping("/movie/{movie_id}")
    public List<Review> getReviewsByMovie(@PathVariable Long movie_id)
    {
        return reviewService.getReviewByMovie(movie_id);
    }
}

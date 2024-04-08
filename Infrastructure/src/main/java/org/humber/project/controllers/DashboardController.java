package org.humber.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Review;
import org.humber.project.services.ApplicationUserDetailsService;
import org.humber.project.services.MovieService;
import org.humber.project.services.ReviewService;
import org.humber.project.services.UserService;
import org.humber.project.services.WatchlistService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private WatchlistService watchlistService;

    @Autowired
    private ApplicationUserDetailsService userService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        long userId = userService.getUserIdByUsername(username);
        List<Review> reviews = reviewService.getReviewByUserId(userId);
        List<Movie> watchlist = watchlistService.getWatchlistFromUser(userId);
        
        model.addAttribute("reviews", reviews);
        model.addAttribute("watchlist", watchlist);
        
        return "dashboard";
    }
}


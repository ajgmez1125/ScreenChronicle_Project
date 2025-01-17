package org.humber.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import java.security.Principal;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.humber.project.domain.Movie;
import org.humber.project.domain.Review;
import org.humber.project.services.ApplicationUserDetailsService;
import org.humber.project.services.UserService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;


@Controller
public class MVCController {

    private final RestTemplate restTemplate;
    
    private ApplicationUserDetailsService userService;

    @Autowired
    public MVCController(RestTemplateBuilder restTemplateBuilder, ApplicationUserDetailsService userService) {
        this.userService = userService;
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String homepage(Model model) {
        ResponseEntity<Movie[]> moviesResponse = restTemplate.getForEntity("http://localhost:8080/api/movie", Movie[].class);
        Movie[] movies = moviesResponse.getBody();
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/movie/{movie_id}")
    public String movie(Model model, @PathVariable Long movie_id) {
        ResponseEntity<Movie> movieResponse = restTemplate.getForEntity("http://localhost:8080/api/movie/"+movie_id, Movie.class);
        Movie movie = movieResponse.getBody();
        model.addAttribute("movie", movie);

        ResponseEntity<Review[]> reviewResponse = restTemplate.getForEntity("http://localhost:8080/api/review/movie/"+movie_id, Review[].class);
        Review[] reviews = reviewResponse.getBody();
        model.addAttribute("reviews", reviews);
        return "movie";
    }

    @GetMapping("/add-movie")
    public String addMovie(Model model)
    {
        return "add-movie";
    }
    

    @GetMapping("/recommended")
    public String recommendedMovies(Model model, Principal principal)
    {
        String username = principal.getName();
        long user_id = this.userService.getUserIdByUsername(username);
        System.out.println("RETRIEVED: " + user_id);
        ResponseEntity<Movie[]> recommendedMovies = restTemplate.getForEntity("http://localhost:8080/api/movie/recommended/"+user_id, Movie[].class);
        Movie[] movies = recommendedMovies.getBody();
        System.out.println(Arrays.toString(movies));
        model.addAttribute("movies", movies);
        return "recommended-movies";
    }
}

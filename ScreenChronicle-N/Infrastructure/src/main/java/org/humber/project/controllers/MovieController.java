package org.humber.project.controllers;

import org.humber.project.services.MovieService;
import org.humber.project.domain.Movie;
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
@RequestMapping("/api/movie")
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<Movie> getAllMovies()
    {
        return movieService.getAllMovies();
    }

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable Long movieId)
    {
        return movieService.getMovie(movieId);
    }

    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(@RequestParam("title") String title,
                           @RequestParam("date") String date,
                           @RequestParam("length") Integer length,
                           @RequestParam("director") String director,
                           @RequestParam("description") String description) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setDate(date);
        movie.setLength(length);
        movie.setDirector(director);
        movie.setDescription(description);

        Movie createdMovie = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovie);
    }
}

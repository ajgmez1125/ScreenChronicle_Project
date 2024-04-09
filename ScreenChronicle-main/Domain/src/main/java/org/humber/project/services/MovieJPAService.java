package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;

public interface MovieJPAService {
    List<Movie> getAllMovies();

    List<Movie> searchMoviesByTitle(String title);

    Movie getMovie(Long movieId);
    Movie addMovie(Movie movie);
    List<Movie> getMoviesByGenre(String genre);
}

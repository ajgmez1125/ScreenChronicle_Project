package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;

public interface MovieJPAService {
    List<Movie> getAllMovies();
    Movie getMovie(Long movieId);
    Movie addMovie(Movie movie);
}

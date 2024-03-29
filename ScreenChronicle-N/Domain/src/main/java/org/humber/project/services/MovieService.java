package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;

public interface MovieService
{
    Movie getMovie(Long movieId);
    List<Movie> getAllMovies();
    List<Movie> getRecomendedMovies(String userId);
    Movie addMovie(Movie movie);
}

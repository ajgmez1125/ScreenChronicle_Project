package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;

public interface MovieService
{
    Movie getMovie(Long movieId);
    List<Movie> getAllMovies();
    Movie addMovie(Movie movie);
    public List<Movie> getRecomendedMovies(Long user_id);
}

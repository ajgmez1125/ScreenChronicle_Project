package org.humber.project.services.impl;

import lombok.RequiredArgsConstructor;
import org.humber.project.domain.Movie;
import org.humber.project.services.MovieJPAService;
import org.humber.project.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.servers.Server;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService
{
    private MovieJPAService movieJPAService;

    @Autowired
    public MovieServiceImpl(MovieJPAService movieJPAService)
    {
        this.movieJPAService = movieJPAService;
    }

    @Override
    public Movie getMovie(Long movieId) {
        return movieJPAService.getMovie(movieId);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieJPAService.getAllMovies();
    }

    @Override
    public List<Movie> getRecomendedMovies(String userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRecomendedMovies'");
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieJPAService.addMovie(movie);
    }
    
}

package org.humber.project.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.humber.project.domain.Movie;
import org.humber.project.repositories.MovieJPARepository;
import org.humber.project.services.MovieJPAService;
import org.humber.project.transformers.MovieEntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.humber.project.repositories.entities.MovieEntity;

@Service
public class MovieJPAServiceImpl implements MovieJPAService{

    private MovieJPARepository movieJPARepository;

    @Autowired
    public MovieJPAServiceImpl(MovieJPARepository movieJPARepository)
    {
        this.movieJPARepository = movieJPARepository;
    }

    @Override
    public List<Movie> getAllMovies()
    {
        return Optional.of(movieJPARepository.findAll())
                .map(MovieEntityTransformer::transformToMovies)
                .orElse(null);
    }

    @Override
    public Movie getMovie(Long movieId) {
        return movieJPARepository.findById(movieId)
                .map(MovieEntityTransformer::transformToMovie)
                .orElse(null);
    }

    @Override
    public Movie addMovie(Movie movie) {
        MovieEntity movieEntity = MovieEntityTransformer.transformToMovieEntity(movie);
        MovieEntity savedMovie = movieJPARepository.save(movieEntity);
        return MovieEntityTransformer.transformToMovie(savedMovie);
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return Optional.of(this.movieJPARepository.getMoviesByGenre(genre.toUpperCase()))
                .map(MovieEntityTransformer::transformToMovies)
                .orElse(null);
    }

    @Override
    public List<Movie> searchMoviesByTitle(String title) {
        List<MovieEntity> movieEntities = movieJPARepository.searchByTitle(title);
        return movieEntities.stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private Movie mapToDomain(MovieEntity movieEntity) {
        return Movie.builder()
                .movie_id(movieEntity.getMovie_id())
                .title(movieEntity.getTitle())
                .date(movieEntity.getDate())
                .length(movieEntity.getLength())
                .director(movieEntity.getDirector())
                .description(movieEntity.getDescription())
                .build();
    }
}

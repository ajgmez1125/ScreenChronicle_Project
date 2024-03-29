package org.humber.project.transformers;

import java.util.List;
import java.util.stream.Collectors;

import org.humber.project.domain.Movie;
import org.humber.project.repositories.entities.MovieEntity;

public class MovieEntityTransformer {
    public static Movie transformToMovie(MovieEntity movieEntity) {
        return Movie.builder()
                .movie_id(movieEntity.getMovie_id())
                .title(movieEntity.getTitle())
                .date(movieEntity.getDate())
                .length(movieEntity.getLength())
                .director(movieEntity.getDirector())
                .description(movieEntity.getDescription())
                .build();
    }

    public static MovieEntity transformToMovieEntity(Movie movie) {
        MovieEntity MovieEntity = new MovieEntity();
        MovieEntity.setMovie_id(movie.getMovie_id());
        MovieEntity.setTitle(movie.getTitle());
        MovieEntity.setDate(movie.getDate());
        MovieEntity.setLength(movie.getLength());
        MovieEntity.setDirector(movie.getDirector());
        MovieEntity.setDescription(movie.getDescription());
        return MovieEntity;
    }

    public static List<Movie> transformToMovies(List<MovieEntity> entities) {
        return entities.stream()
                .map(MovieEntityTransformer::transformToMovie)
                .collect(Collectors.toList());
    }
}

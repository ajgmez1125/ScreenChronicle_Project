package org.humber.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.humber.project.domain.Movie;
import org.humber.project.repositories.WatchlistJPARepository;
import org.humber.project.repositories.entities.MovieEntity;
import org.humber.project.services.WatchlistJPAService;
import org.humber.project.transformers.MovieEntityTransformer;
import org.springframework.stereotype.Service;

@Service
public class WatchlistJPAServiceImpl implements WatchlistJPAService
{
    private WatchlistJPARepository watchlistJPARepository;

    public WatchlistJPAServiceImpl(WatchlistJPARepository watchlistJPARepository)
    {
        this.watchlistJPARepository = watchlistJPARepository;
    }

    @Override
    public List<Movie> getWatchlistFromUser(Long user_id) {
        List<MovieEntity> movies = this.watchlistJPARepository.getWatchlistFromUser(user_id);
        return Optional.of(movies)
                .map(MovieEntityTransformer::transformToMovies)
                .orElse(null);
    }
}

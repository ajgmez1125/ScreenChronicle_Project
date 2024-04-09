package org.humber.project.services.impl;

import java.util.List;
import java.util.Optional;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Watchlist;
import org.humber.project.repositories.MovieJPARepository;
import org.humber.project.repositories.UserJPARepository;
import org.humber.project.repositories.WatchlistJPARepository;
import org.humber.project.repositories.entities.MovieEntity;
import org.humber.project.repositories.entities.WatchlistEntity;
import org.humber.project.services.WatchlistJPAService;
import org.humber.project.transformers.MovieEntityTransformer;
import org.humber.project.transformers.WatchlistTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WatchlistJPAServiceImpl implements WatchlistJPAService
{
    private WatchlistJPARepository watchlistJPARepository;

    private UserJPARepository userJPARepository;

    private MovieJPARepository movieJPARepository;

    @Autowired
    public WatchlistJPAServiceImpl(WatchlistJPARepository watchlistJPARepository, UserJPARepository userJPARepository, MovieJPARepository movieJPARepository)
    {
        this.watchlistJPARepository = watchlistJPARepository;
        this.userJPARepository = userJPARepository;
        this.movieJPARepository = movieJPARepository;
    }

    public WatchlistJPAServiceImpl(WatchlistJPARepository watchlistJPARepository)
    {
        this.watchlistJPARepository = watchlistJPARepository;
    }

    @Override
    public List<Movie> getWatchlistFromUser(Long user_id) {
        System.out.println("JPA SERVICE: " + user_id);
        List<MovieEntity> movies = this.watchlistJPARepository.getWatchlistFromUser(user_id);
        return Optional.of(movies)
                .map(MovieEntityTransformer::transformToMovies)
                .orElse(null);
    }

    @Override
    public Watchlist addToWatchList(Long movie_id, Long user_id) {
        Watchlist watchlist = new Watchlist();
        watchlist.setMovie_id(movie_id);
        watchlist.setUser_id(user_id);
        WatchlistEntity watchlistEntity = WatchlistTransformer.transformtoWatchlistEntity(watchlist, this.userJPARepository, this.movieJPARepository);
        watchlistJPARepository.save(watchlistEntity);
        return watchlist;
    }
    @Override
    public void deleteFromWatchlist(Long movie_id) {
        watchlistJPARepository.deleteFromWatchlist(movie_id);
    }
}

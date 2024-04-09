package org.humber.project.services.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.User;
import org.humber.project.domain.Watchlist;
import org.humber.project.services.UserService;
import org.humber.project.services.WatchlistJPAService;
import org.humber.project.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService
{
    private WatchlistJPAService watchlistJPAService;

    @Autowired
    public WatchlistServiceImpl(WatchlistJPAService watchlistJPAService)
    {
        this.watchlistJPAService = watchlistJPAService;
    }

    @Override
    public List<Movie> getWatchlistFromUser(Long user_id) {
        return this.watchlistJPAService.getWatchlistFromUser(user_id);
    }

    @Override
    public Watchlist addToWatchList(Long movie_id, Long user_id) {
        return this.watchlistJPAService.addToWatchList(movie_id, user_id);
    }

    @Override
    @Transactional
    public void deleteFromWatchlist(Long movie_id) {
        watchlistJPAService.deleteFromWatchlist(movie_id);
    }
}
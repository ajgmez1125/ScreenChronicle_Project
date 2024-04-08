package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Watchlist;

public interface WatchlistJPAService
{
    List<Movie> getWatchlistFromUser(Long user_id);
    Watchlist addToWatchList(Long watchlist, Long user_id);
}
package org.humber.project.services;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Watchlist;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface WatchlistService {
    List<Movie> getWatchlistFromUser(Long user_id);
    Watchlist addToWatchList(Long watchlist, Long user_id);
    void deleteFromWatchlist(Long movie_id);
}

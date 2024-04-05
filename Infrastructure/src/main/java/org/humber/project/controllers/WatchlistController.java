package org.humber.project.controllers;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.domain.Watchlist;
import org.humber.project.services.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @Autowired
    public WatchlistController(WatchlistService watchlistService) {
        this.watchlistService = watchlistService;
    }

    // @PostMapping("/add")
    // public ResponseEntity<Watchlist> addToWatchlist(@RequestBody Watchlist Watchlist) {

    //     Watchlist addedToWatchlist = WatchlistService.addToWatchlist(Watchlist);
    //     return ResponseEntity.status(HttpStatus.CREATED).body(addedToWatchlist);
    // }
    @GetMapping("/from-user/{user_id}")
    public List<Movie> getWatchlistFromUser(@PathVariable Long user_id) {
        return this.watchlistService.getWatchlistFromUser(user_id);
    }
    
}
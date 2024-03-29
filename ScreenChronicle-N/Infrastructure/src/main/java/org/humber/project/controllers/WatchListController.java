package org.humber.project.controllers;

import org.humber.project.domain.WatchList;
import org.humber.project.services.WatchListJPAService;
import org.humber.project.domain.Movie;
import org.humber.project.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/watchlist")
public class WatchListController {

    private final WatchListJPAService watchListJPAService;
    private final MovieService MovieService;
    @Autowired
    public WatchListController(WatchListJPAService watchListJPAService, org.humber.project.services.MovieService movieService) {
        this.watchListJPAService = watchListJPAService;
        this.MovieService = movieService;
    }

    @PostMapping("/add")
    public ResponseEntity<WatchList> addToWatchList(@RequestBody WatchList watchList) {
        // Fetch the corresponding movie based on movie_id
        Movie movie = MovieService.getMovie(watchList.getMovie_id());

        if (movie != null) {
            // Set the movie in the WatchList object
            watchList.setMovie(movie);

            // Add the WatchList to the database
            WatchList addedToWatchList = watchListJPAService.addToWatchList(watchList);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedToWatchList);
        } else {
            // Handle the case where the movie with the given ID does not exist
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

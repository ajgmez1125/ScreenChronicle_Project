package org.humber.project.services.impl;

import lombok.RequiredArgsConstructor;
import org.humber.project.domain.Movie;
import org.humber.project.services.MovieJPAService;
import org.humber.project.services.MovieService;
import org.humber.project.services.WatchlistJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.swagger.v3.oas.annotations.servers.Server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class MovieServiceImpl implements MovieService
{
    private MovieJPAService movieJPAService;
    private WatchlistJPAService watchlistJPAService;

    @Autowired
    public MovieServiceImpl(MovieJPAService movieJPAService, WatchlistJPAService watchlistJPAService)
    {
        this.movieJPAService = movieJPAService;
        this.watchlistJPAService = watchlistJPAService;
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
    public Movie addMovie(Movie movie) {
        return movieJPAService.addMovie(movie);
    }

    @Override
    public List<Movie> getRecomendedMovies(Long user_id) {
        final int RETURN_AMOUNT = 20;
        System.out.println("SERVICE: " + user_id);
        List<Movie> userWatchlist = this.watchlistJPAService.getWatchlistFromUser(user_id);
        System.out.println("WATCHLIST" + userWatchlist);
        if(userWatchlist.isEmpty())
        {
            return null;
        }
        HashMap<String, Integer> genreCount = new HashMap<>();
        System.out.println("COUNT GENRES BEGIN");
        for (Movie movie : userWatchlist)
        {
            String genre = movie.getGenre();

            if (genreCount.containsKey(genre))
            {
                genreCount.put(genre, genreCount.get(genre) + 1);
            }
            else
            {
                genreCount.put(genre, 1);
            }
        }
        System.out.println("COUNT GENRES END: " + genreCount);
        String mostPopularGenre = Collections.max(genreCount.keySet());
        System.out.println("hes behind me isnt he....");
        if(mostPopularGenre == null)
        {
            return null;
        }
        Random r = new Random();
        List<Movie> genreMovies = this.movieJPAService.getMoviesByGenre(mostPopularGenre.toUpperCase());
        List<Movie> recommendationList = new ArrayList<Movie>();
        for(int i = 0; i < RETURN_AMOUNT; i++)
        {
            if (i > genreMovies.size())
            {
                break;
            }
            Movie movie = genreMovies.get(r.nextInt(genreMovies.size()));
            recommendationList.add(i, movie);
        }
        System.out.println("d");
        return recommendationList;
    }
}

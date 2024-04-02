package org.humber.project.services.impl;

import org.humber.project.domain.Movie;
import org.humber.project.domain.WatchList;
import org.humber.project.repositories.WatchListJPARepository;
import org.humber.project.repositories.entities.WatchListEntity;
import org.humber.project.services.WatchListJPAService;
import org.humber.project.services.MovieJPAService;
import org.humber.project.transformers.WatchListTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListJPAServiceImpl implements WatchListJPAService {

    private final WatchListJPARepository watchListJPARepository;
    private final WatchListTransformer watchListTransformer;
    private final MovieJPAService movieJPAService; // Add MovieJPAService field

    @Autowired
    public WatchListJPAServiceImpl(WatchListJPARepository watchListJPARepository, WatchListTransformer watchListTransformer, MovieJPAService movieJPAService) {
        this.watchListJPARepository = watchListJPARepository;
        this.watchListTransformer = watchListTransformer;
        this.movieJPAService = movieJPAService; // Initialize MovieJPAService field
    }

    @Override
    public List<WatchList> getWatchListByUserId(Long userId) {
        List<WatchListEntity> watchListEntities = watchListJPARepository.findAllByUserId(userId);
        return watchListEntities.stream()
                .map(watchListTransformer::toDto)
                .map(this::populateMovieData)
                .collect(Collectors.toList());
    }

    private WatchList populateMovieData(WatchList watchList) {
        // Fetch movie data based on watchlist.movie_id using injected MovieJPAService
        System.out.println("Attempting to fetch movie data for watchlist: " + watchList.getMovie_id());
        Movie movie = movieJPAService.getMovie(watchList.getMovie_id());

        if (movie != null) {
            watchList.setMovie(movie);
        } else {
            System.err.println("Movie data not found for watchlist: " + watchList.getMovie_id());
        }
        return watchList;
    }

    @Override
    public WatchList addToWatchList(WatchList watchList) {
        WatchListEntity watchListEntity = watchListTransformer.toEntity(watchList);
        WatchListEntity savedWatchListEntity = watchListJPARepository.save(watchListEntity);
        return watchListTransformer.toDto(savedWatchListEntity);
    }
}


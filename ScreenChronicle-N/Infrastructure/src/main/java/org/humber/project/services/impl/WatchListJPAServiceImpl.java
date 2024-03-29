package org.humber.project.services.impl;

import org.humber.project.domain.Movie;
import org.humber.project.domain.WatchList;
import org.humber.project.repositories.WatchListJPARepository;
import org.humber.project.repositories.entities.WatchListEntity;
import org.humber.project.services.WatchListJPAService;
import org.humber.project.transformers.WatchListTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.humber.project.services.MovieJPAService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WatchListJPAServiceImpl implements WatchListJPAService {

    private final WatchListJPARepository watchListJPARepository;
    private final WatchListTransformer watchListTransformer;
    private MovieJPAService movieJPAService;

    @Autowired
    public WatchListJPAServiceImpl(WatchListJPARepository watchListJPARepository, WatchListTransformer watchListTransformer) {
        this.watchListJPARepository = watchListJPARepository;
        this.watchListTransformer = watchListTransformer;
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
        Movie movie = movieJPAService.getMovie(watchList.getMovie_id());

        if (movie != null) {
            watchList.setMovie(movie);
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

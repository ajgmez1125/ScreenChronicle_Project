package org.humber.project.transformers;

import org.humber.project.domain.WatchList;
import org.humber.project.repositories.entities.WatchListEntity;
import org.springframework.stereotype.Component;

@Component
public class WatchListTransformer {

    public WatchListEntity toEntity(WatchList watchList) {
        WatchListEntity entity = new WatchListEntity();
        entity.setWatchlistId(watchList.getWatchlistId());
        entity.setUserId(watchList.getUser_id());
        entity.setMovieId(watchList.getMovie_id());
        return entity;
    }

    public WatchList toDto(WatchListEntity entity) {
        return WatchList.builder()
                .watchlistId(entity.getWatchlistId())
                .user_id(entity.getUserId())
                .movie_id(entity.getMovieId())
                .build();
    }
}
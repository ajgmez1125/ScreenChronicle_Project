package org.humber.project.transformers;

import org.humber.project.domain.Watchlist;
import org.humber.project.repositories.MovieJPARepository;
import org.humber.project.repositories.UserJPARepository;
import org.humber.project.repositories.entities.MovieEntity;
import org.humber.project.repositories.entities.WatchlistEntity;
import org.humber.project.repositories.entities.UserEntity;
import org.humber.project.repositories.entities.WatchlistEntity;
import org.springframework.stereotype.Component;

@Component
public class WatchlistTransformer
{
    public static WatchlistEntity transformtoWatchlistEntity(Watchlist watchlist, UserJPARepository userJPARepository, MovieJPARepository movieJPARepository)
    {
        WatchlistEntity watchlistEntity = new WatchlistEntity();

        UserEntity user = userJPARepository.findById(watchlist.getUser_id()).orElse(null);
        watchlistEntity.setUser_id(user);

        MovieEntity movie = movieJPARepository.findById(watchlist.getMovie_id()).orElse(null);
        watchlistEntity.setMovie_id(movie);

        return watchlistEntity;
    }

    // public Watchlist toDto(WatchlistEntity entity) {
    //     return Watchlist.builder()
    //             .WatchlistId(entity.getWatchlistId())
    //             .user_id(entity.getUserId())
    //             .movie_id(entity.getMovieId())
    //             .build();
    // }
}
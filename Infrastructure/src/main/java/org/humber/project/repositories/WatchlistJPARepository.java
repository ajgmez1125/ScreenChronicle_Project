package org.humber.project.repositories;

import org.humber.project.repositories.entities.MovieEntity;
import org.humber.project.repositories.entities.WatchlistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WatchlistJPARepository extends JpaRepository<WatchlistEntity, Long> {
    @Query("SELECT w.movie_id from WatchlistEntity w WHERE w.user_id.user_id = :user_id")
    List<MovieEntity> getWatchlistFromUser(Long user_id);
}
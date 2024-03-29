package org.humber.project.repositories;

import java.util.List;

import org.humber.project.repositories.entities.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewJPARepository extends JpaRepository<ReviewEntity, Long>
{
    @Query("SELECT r FROM ReviewEntity r WHERE r.movie.movie_id = :movieId")
    List<ReviewEntity> findByMovieId(Long movieId);
}

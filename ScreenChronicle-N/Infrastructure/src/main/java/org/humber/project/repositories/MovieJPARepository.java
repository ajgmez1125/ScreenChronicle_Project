package org.humber.project.repositories;

import java.util.List;

import org.humber.project.domain.Movie;
import org.humber.project.repositories.entities.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieJPARepository extends JpaRepository<MovieEntity, Long>{
}
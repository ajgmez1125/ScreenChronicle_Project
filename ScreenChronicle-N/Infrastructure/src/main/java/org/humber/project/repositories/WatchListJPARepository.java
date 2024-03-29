package org.humber.project.repositories;

import org.humber.project.repositories.entities.WatchListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface WatchListJPARepository extends JpaRepository<WatchListEntity, Long> {
    List<WatchListEntity> findAllByUserId(Long user_id);

}


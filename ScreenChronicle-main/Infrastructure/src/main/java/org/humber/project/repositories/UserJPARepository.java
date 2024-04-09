package org.humber.project.repositories;

import java.util.List;
import java.util.Optional;

import org.humber.project.repositories.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserJPARepository extends JpaRepository<UserEntity, Long>
{
    Optional<UserEntity> findByUsername(String username);
    boolean existsByUsername(String username);
}

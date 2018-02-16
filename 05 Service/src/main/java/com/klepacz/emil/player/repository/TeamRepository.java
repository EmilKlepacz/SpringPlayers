package com.klepacz.emil.player.repository;


import com.klepacz.emil.player.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Integer> {
    Optional<TeamEntity> findByName(String name);
}

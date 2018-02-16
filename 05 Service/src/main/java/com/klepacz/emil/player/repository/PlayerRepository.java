package com.klepacz.emil.player.repository;


import com.klepacz.emil.player.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlayerRepository extends JpaRepository<PlayerEntity, Integer>{

}

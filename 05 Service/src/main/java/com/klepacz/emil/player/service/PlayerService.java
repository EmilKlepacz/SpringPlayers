package com.klepacz.emil.player.service;

import com.klepacz.emil.player.entity.PlayerEntity;
import com.klepacz.emil.player.model.Player;
import com.klepacz.emil.player.repository.PlayerRepository;
import com.klepacz.emil.player.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
//    private final List<Player> players = new ArrayList<>(Arrays.asList(new Player("Emil", "Klepacz", 23), new Player("David", "Beckham", 44)));

    public List<Player> getAllPlayers() {
        return toPlayerDto(playerRepository.findAll());
    }

    public Optional<Player> getById(Integer id) {
        return playerRepository.findById(id).map(playerEntity -> toPlayerDto(playerEntity));
    }

    public PlayerService(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    public void add(Player player) {
        playerRepository.save(toEntity(player));
    }

    public void remove(Integer id) {
        playerRepository.deleteById(id);
    }

    public void edit(Player player) {
        checkNotNull(player, "Player cannot be null");
        checkArgument(player.getId() != null, "If player is to be edited, it needs it's id to be set.");
        playerRepository.save(toEntity(player));
    }

    private PlayerEntity toEntity(Player player) {
        return new PlayerEntity(player.getId(), player.getName(), player.getSurname(), player.getAge());
    }

    private Player toPlayerDto(PlayerEntity entity) {
        return new Player(entity.getId(), entity.getName(), entity.getSurname(), entity.getAge(), entity.getTeam() == null ? "" : entity.getTeam().getName());
    }

    private List<Player> toPlayerDto(List<PlayerEntity> entities) {
        return entities.stream().map(this::toPlayerDto).collect(Collectors.toList());
    }
}

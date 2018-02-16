package com.klepacz.emil.player.service;

import com.klepacz.emil.player.model.Player;
import com.klepacz.emil.player.repository.PlayerRepository;
import com.klepacz.emil.player.repository.TeamRepository;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;

public class PlayerServiceTest {

    private final PlayerRepository repository = mock(PlayerRepository.class);
    private final TeamRepository teamRepository = mock(TeamRepository.class);
    private PlayerService service = new PlayerService(repository, teamRepository);

    @Test
    public void shouldAddPlayer() {
        service.add(new Player("Fake", "Player", 25));
        assertThat(service.getAllPlayers().size()).isEqualTo(3);
    }

//    @Test
//    public void shouldRemovePlayer(){
//        service.remove(new Player("Emil", "Klepacz", 23));
//        assertThat(service.getAllPlayers().size()).isEqualTo(1);
//    }

}
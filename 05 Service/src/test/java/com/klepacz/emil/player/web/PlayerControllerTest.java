package com.klepacz.emil.player.web;

import com.klepacz.emil.player.model.Player;
import com.klepacz.emil.player.repository.PlayerRepository;
import com.klepacz.emil.player.service.PlayerService;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Locale;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class PlayerControllerTest {
    private final PlayerRepository repository = mock(PlayerRepository.class);
    private final PlayerService service = new PlayerService(repository);
    private final MessageSource messageSource = new MessageSource() {
        @Override
        public String getMessage(String s, @Nullable Object[] objects, @Nullable String s1, Locale locale) {
            return null;
        }

        @Override
        public String getMessage(String s, @Nullable Object[] objects, Locale locale) throws NoSuchMessageException {
            return null;
        }

        @Override
        public String getMessage(MessageSourceResolvable messageSourceResolvable, Locale locale) throws NoSuchMessageException {
            return null;
        }
    };
    private final PlayerController controller = new PlayerController(service, messageSource);
    private final MockMvc mvc = standaloneSetup(controller).build();

    @Test
    public void display() throws Exception {
        MvcResult result = mvc.perform(get("/players/display")).andExpect(status().isOk()).andReturn();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getModelAndView().getViewName()).isEqualTo("players");
        softly.assertThat(service.getAllPlayers()).hasSize(2);
        softly.assertAll();
    }

    @Test
    public void add() throws Exception {
        MvcResult result = mvc.perform(post("/players/action").param("name", "ThirdName").param("surname", "ThirdSurname").param("actionType", "add"))
                .andExpect(status().isOk()).andReturn();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getModelAndView().getViewName()).isEqualTo("players");
        softly.assertThat(service.getAllPlayers()).hasSize(3);
        softly.assertAll();
    }

    @Test
    public void remove() throws Exception {
        service.add(new Player("NameToRemove", "SurnameToRemove", 25));

        MvcResult result = mvc.perform(post("/remove").param("name", "NameToRemove").param("surname", "SurnameToRemove"))
                .andExpect(status().isOk()).andReturn();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(result.getModelAndView().getViewName()).isEqualTo("players");
        softly.assertThat(service.getAllPlayers()).hasSize(2);
        softly.assertAll();
    }
}
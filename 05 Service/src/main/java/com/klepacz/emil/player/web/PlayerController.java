package com.klepacz.emil.player.web;

import com.klepacz.emil.player.model.Player;
import com.klepacz.emil.player.service.PlayerService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

@Controller
@RequestMapping("players")
@SessionAttributes("playerSession")
public class PlayerController {
    private final PlayerService playerService;
    private final MessageSource messageSource;

    public PlayerController(PlayerService playerService, MessageSource messageSource) {
        this.playerService = playerService;
        this.messageSource = messageSource;
    }

    @ModelAttribute
    public PlayerSession session() {
        return new PlayerSession();
    }

    @ModelAttribute
    public Player defaultPlayer() {
        return new Player("Default Name", "Default Surname", 25);
    }

    @GetMapping("display")
    public String display(Model model) {
        model.addAttribute("playersModel", playerService.getAllPlayers());

        return "players";
    }

    @PostMapping("action")
    public String action(@Valid @ModelAttribute Player player, BindingResult bindingResult,
                         Model model,
                         @RequestParam(required = false, defaultValue = "") String actionType,
                         @RequestParam(defaultValue = "") String removeById,
                         @RequestParam(defaultValue = "") String openEditPagePlayerId,
                         @ModelAttribute PlayerSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("playersModel", playerService.getAllPlayers());
            return "players";
        }
        if (actionType.equalsIgnoreCase("add") && !actionType.isEmpty()) {
            playerService.add(player);

            session.setCounter(session.getCounter() + 1);
            session.setMostRecentPlayer(player);
            model.addAttribute("playersModel", playerService.getAllPlayers());
            return "redirect:display";

        } else if (!removeById.isEmpty()) {
            playerService.remove(Integer.valueOf(removeById));
            model.addAttribute("playersModel", playerService.getAllPlayers());

            return "redirect:display";
        } else if (!openEditPagePlayerId.isEmpty()) {
            Player playerToEdit = playerService.getById(Integer.valueOf(openEditPagePlayerId)).orElse(new Player());
            model.addAttribute("playerToEdit", playerToEdit);

            return "editPlayer";
        }
        return "redirect:display";
    }

    @PostMapping("edit")
    public String action(@Valid @ModelAttribute Player player, BindingResult bindingResult,
                         Model model,
                         @RequestParam(defaultValue = "") String playerId) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("playersModel", playerService.getAllPlayers());
            return "players";
        } else if (!playerId.isEmpty()) {
            player.setId(Integer.valueOf(playerId));
            playerService.edit(player);
            return "redirect:display";
        }
        return "redirect:display";
    }


    @GetMapping("/range/{from}/{to}")
    public String displayRange(@PathVariable int from, @PathVariable int to, Model model) {

        model.addAttribute("playersModel", playerService.getAllPlayers().subList(from, to + 1));
        return "players";
    }

    @GetMapping("/get/{index}")
    public String displayOne(@PathVariable int index, Model model) {
        model.addAttribute("playersModel", playerService.getAllPlayers().subList(index - 1, index));
        return "players";
    }


    @ExceptionHandler(Exception.class)
    public String error(Exception exception, Model model, Locale locale) {
        exception.printStackTrace();
        model.addAttribute("exception", exception);
        model.addAttribute("message", messageSource.getMessage("error.message", null, locale));
        return "error";
    }

}

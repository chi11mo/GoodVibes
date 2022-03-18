package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.Player;
import com.example.FootbalLeague.controller.TwitchInteraction;
import com.example.FootbalLeague.exception.UserNotFoundException;
import com.example.FootbalLeague.repo.PlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    @Autowired
    public PlayerService(PlayerRepo playerRepo) {
        this.playerRepo = playerRepo;
    }

    public Player addPlayer(Player player) {
        player.setPlayerCode(UUID.randomUUID().toString());

        TwitchInteraction twitchInteraction = new TwitchInteraction();
        player.setImgUrl(String.valueOf(twitchInteraction.getUser( player.getTwitch()).get(0).getProfileImageUrl()));

        return playerRepo.save(player);
    }

    public List<Player> findAllPlayers() {
        return playerRepo.findAll();
    }

    public Player findPlayerByID(Long id) {
        return playerRepo.findPlayerById(id)
                .orElseThrow(() -> new UserNotFoundException("User By ID" + id + "was not found."));
    }

    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);
    }
}

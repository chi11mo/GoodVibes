package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.*;
import com.example.FootbalLeague.controller.TwitchInteraction;
import com.example.FootbalLeague.exception.UserNotFoundException;

import com.example.FootbalLeague.repo.PlayerRepo;

import com.example.FootbalLeague.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;

    private final ClubService clubService;

    private final GameService gameService;

    private final PasswordEncoder passwordEncoder;

    private final TokenServiceImpl tokenService;

    private final TokenRepository tokenRepository;

    @Autowired
    public PlayerService(PlayerRepo playerRepo, ClubService clubService, GameService gameService, PasswordEncoder passwordEncoder, TokenServiceImpl tokenService, TokenRepository tokenRepository) {
        this.playerRepo = playerRepo;
        this.clubService = clubService;
        this.gameService = gameService;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.tokenRepository = tokenRepository;
    }

    /**
     * Service method to add {@link Player} to register with a new linked {@link Club}
     * and add Twitch profile img to it.
     *
     * @param player {@link Player} to add it to Player list.
     * @return the added Player .
     */
    public Player addPlayer(Player player) {


        player.setPlayerCode(UUID.randomUUID().toString());
        player.setClub(clubService.addClub(new Club(player.getTwitch())));
        if (player.getRole() == null) {
            player.setRole(Role.PLAYER);
        } else {
            player.setRole(player.getRole());
        }
        TwitchInteraction twitchInteraction = new TwitchInteraction();
        player.setImgUrl(String.valueOf(twitchInteraction.getUser(player.getTwitch()).get(0).getProfileImageUrl()));

        final String encodedPassword = passwordEncoder.encode(player.getPassword());
        player.setPassword(encodedPassword);
        // System.out.println(String.valueOf(player.getId()));

        return playerRepo.save(player);
    }

    /**
     * Service method to get all registered {@link Player}.
     *
     * @return a List of all {@link Player}.
     */
    public List<Player> findAllPlayers() {
        return (List<Player>) playerRepo.findAll();
    }

    /**
     * Service method to find a specific {@link Player} by id.
     *
     * @param id for identify the searched {@link Player}.
     * @return the searched {@link Player}.
     */
    public Player findPlayerByID(Long id) {
        return playerRepo.findPlayerById(id);
    }

    /**
     * Service method to delete a {@link Player} by Id.
     *
     * @param id id for identify the searched {@link Player}.
     */
    public void deletePlayer(Long id) {
        playerRepo.deleteById(id);
    }

    /**
     * Service method to update a {@link Player}
     *
     * @param newPlayer new {@link Player} to updated.
     * @return updated {@link Player}.
     */
    public Player updateUser(final Player newPlayer) {
        final Player oldPlayer = playerRepo.findPlayerById(newPlayer.getId());

        newPlayer.setId(oldPlayer.getId());

        return playerRepo.save(newPlayer);
    }

    /**
     * Service method to find a {@link Player} by eMail.
     *
     * @param eMail for identify the searched {@link Player}.
     * @return the searched {@link Player}.
     */
    public Player findPlayerByeMail(final String eMail) {
        return playerRepo.findPlayerByeMail(eMail);
    }

    /**
     * Service method to reset the given Password for the {@link Player}.
     *
     * @param token       {@link Token}.
     * @param newPassword the one.
     * @return response.
     */
    public String resetPassword(final String token, final String newPassword) {
        final Player player = tokenService.getPlayerByToken(token);
        player.setPassword(passwordEncoder.encode(newPassword));
        playerRepo.save(player);
        return "SUCCESS";
    }

    /**
     * @param token
     * @return
     */
    public boolean checkForToken(final String token) {
        final Token tokenByTokenContent = tokenRepository.findTokenByTokenContent(token);
        final Player player = findPlayerByToken(token);

        if (tokenByTokenContent.getExpiresAt().isBefore(LocalDateTime.now())) {
            //logger.info("Token abgelaufen");
            return false;
        } else if (player.isActive()) {
            // logger.info("User ist bereits aktiviert");
            return true;
        } else {
            setPlayerIsEnabled(player);
            // logger.info("User wurde aktiviert");
            return true;
        }
    }

    /**
     * Service method to activated a {@link Player}.
     *
     * @param player {@link Player}/
     */
    public void setPlayerIsEnabled(final Player player) {
        player.setIsEnabled(true);
        playerRepo.save(player);

    }

    /**
     * Service method to find a {@link Player} by token.
     *
     * @param tokenContent for identify the searched {@link Player}.
     * @return the searched {@link Player}.
     */
    public Player findPlayerByToken(final String tokenContent) {
        return playerRepo.findPlayerById(tokenRepository.findTokenByTokenContent(tokenContent).getPlayer().getId());
    }


}

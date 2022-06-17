package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.Game;
import com.example.FootbalLeague.repo.GameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    private final GameRepo gameRepo;
    private final ClubService clubService;


    @Autowired
    public GameService(GameRepo gameRepo, ClubService clubService) {
        this.gameRepo = gameRepo;


        this.clubService = clubService;
    }

    /**
     * Service Method to add a {@link Game} to the schedule.
     *
     * @param game {@link Game} to add.
     * @return {@link Game} which added.
     */
    public Game addGame(Game game) {
        game.setGameCode(UUID.randomUUID().toString());

        return gameRepo.save(game);
    }

    /**
     * Service Method to find all registred {@link Game}.
     *
     * @return List of registred {@link Game}.
     */
    public List<Game> findAllGames() {
        return (List<Game>) gameRepo.findAll();
    }

    /**
     * Service method to find a {@link Game} by Id.
     *
     * @param id to find the {@link Game}.
     * @return the searched {@link Game}.
     */
    public Game findGameByID(Long id) {
        return gameRepo.findGameById(id);
    }

    /**
     * Service method to delete a specific {@link Game} by Id.
     *
     * @param id for finding the {@link Game}.
     */
    public void deleteGame(Long id) {
        gameRepo.deleteById(id);
    }

    /**
     * Service method to get {@link Game} List sort by played or not Played.
     *
     * @param isPlayed to get all played or not played {@link Game}.
     * @return list of {@link Game}.
     */
    public List<Game> findNotPlayedGames(final boolean isPlayed) {
        List<Game> allGameList = findAllGames();
        List<Game> allNotPlayedGamesList = new ArrayList<>();
        List<Game> allPlayedGamesList = new ArrayList<>();

        for (Game game : allGameList) {
            if (!game.isPlayed()) {
                allNotPlayedGamesList.add(game);
            } else {
                allPlayedGamesList.add(game);
            }
        }
        if (isPlayed) {
            return allPlayedGamesList;
        } else {
            return allNotPlayedGamesList;
        }
    }


}

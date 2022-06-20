package com.example.FootbalLeague;


import com.example.FootbalLeague.Model.Club;
import com.example.FootbalLeague.Model.Game;
import com.example.FootbalLeague.Model.Player;
import com.example.FootbalLeague.Model.Role;
import com.example.FootbalLeague.service.ClubService;
import com.example.FootbalLeague.service.GameService;
import com.example.FootbalLeague.service.PlayerService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InitializeDatabase implements InitializingBean {
    private final PlayerService playerService;
    private final ClubService clubService;
    private final GameService gameService;

    @Autowired
    public InitializeDatabase(final PlayerService playerService, final ClubService clubService, final GameService gameService) {
        this.playerService = playerService;
        this.clubService = clubService;
        this.gameService = gameService;


    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (playerService.findAllPlayers().size() == 0) {
          //  Club club;
          //  club = new Club("chi11mo");
           // clubService.addClub(club);
            playerService.addPlayer(new Player("dominic.w@gmx.de", "password123", "chi11mo", "chi11mo", "chimo", "psn", Role.ADMIN));
          //  club = new Club("PesCoach");
            playerService.addPlayer(new Player("pesCoach@gmx.de", "password123", "PesCoach", "PesCoach", "chimo", "psn", Role.PLAYER));

            playerService.addPlayer(new Player("jonhDoe@gmx.de", "password123", "JohnDoe", "Thijs", "johndong", "johnnyrakette", Role.PLAYER));

            playerService.addPlayer(new Player("MaximillianPrecht@gmx.de", "password123", "MaxDax", "TFBlade", "kew", "kew", Role.PLAYER));

            playerService.addPlayer(new Player("ChefderMustermann@gmx.de", "password123", "KEww", "amouranth", "kukuw", "playstationstinkt", Role.PLAYER));
        }


        if (gameService.findAllGames().size() == 0) {


            gameService.addGame(new Game("chi11mo", "JohnDoe", 1, "Febuary", 2022));

            gameService.addGame(new Game("PesCoach", "MaxDax", 10, "March", 2022));

            gameService.addGame(new Game("KEww", "PesCoach", 10, "March", 2022));
        }

    }


}

package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.Club;
import com.example.FootbalLeague.Model.Game;
import com.example.FootbalLeague.Model.Player;
import com.example.FootbalLeague.repo.ClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ClubService {
    private final ClubRepo clubRepo;


    @Autowired
    public ClubService(ClubRepo clubRepo) {
        this.clubRepo = clubRepo;

    }

    /**
     * Service Method to adding new Club for a Player.
     *
     * @param club to adding new  {@link Club}.
     * @return the saved Club {@link Club}.
     */
    public Club addClub(final Club club) {
        club.setClubCode(UUID.randomUUID().toString());
        return clubRepo.save(club);
    }

    /**
     * Service Method to get all registered Clubs.
     *
     * @return a List of all registered {@link Club}.
     */
    public List<Club> findAllClubs() {
        return (List<Club>) clubRepo.findAll();
    }

    /**
     * Finding a {@link Club} by Id.
     *
     * @param id for finding a {@link Club}.
     * @return the searched {@link Club}.
     */
    public Club findClubByID(final Long id) {
        return clubRepo.findClubById(id);
    }

    /**
     * Adding a new {@link Game} in the {@link Club} Schedule.
     *
     * @param clubOne to register used {@link Club} for the Game Schedule.
     * @param clubTwo to register used {@link Club} for the Game Schedule.
     * @param game    {@link Game} for schedule games.
     */
    public void addGame(final Club clubOne, final Club clubTwo, final Game game) {
        List<Game> gameList = clubOne.getGame();
        gameList.add(game);
        clubOne.setGame(gameList);
        updateClub(clubOne);

        List<Game> gameList2 = clubTwo.getGame();
        gameList2.add(game);
        clubTwo.setGame(gameList2);
        updateClub(clubTwo);

    }

    /**
     * To delete a {@link Club} by Id.
     *
     * @param id for find the {@link Club} to delete.
     */
    public void deleteClub(final Long id) {
        clubRepo.deleteById(id);
    }

    /**
     * Service Method to update a {@link Club}.
     *
     * @param newClub {@link Club} to update.
     * @return the updated {@link Club}.
     */
    public Club updateClub(final Club newClub) {
        final Club oldClub = clubRepo.findClubById(newClub.getId());

        newClub.setId(oldClub.getId());

        return clubRepo.save(newClub);
    }

    public Club findClubByName(String clubName) {
        List<Club> clubList = (List<Club>) clubRepo.findAll();
        for(Club club: clubList){
           // System.out.println(clubName  + club.getTeam());
            if(Objects.equals(club.getTeam(), clubName)){
             //   System.out.println("successsssssss");
                return club;
            }
        }
        return null;
    }

    public List<Club> findIsActiveClubs(boolean isActive) {
        List<Club> clubList = (List<Club>) clubRepo.findAll();
        List<Club> newClubList = null;
        for(Club club: clubList){
            // System.out.println(clubName  + club.getTeam());
            if(club.isActive()==isActive){
                //   System.out.println("successsssssss");
               newClubList.add(club);
            }
        }
        return newClubList;
    }
}

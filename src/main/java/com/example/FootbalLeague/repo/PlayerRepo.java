package com.example.FootbalLeague.repo;

import com.example.FootbalLeague.Model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlayerRepo extends CrudRepository<Player,Long> {
    void deleteById(Long id);

   Player findPlayerById(Long id);

    /**
     * Finds a user by the given mail adress.
     *
     * @param email the mail of the searched {@link Player}
     * @return an Optional with the requested {@link Player} if found
     */
    Player findPlayerByeMail(String email);
}

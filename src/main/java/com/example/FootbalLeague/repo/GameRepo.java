package com.example.FootbalLeague.repo;
import com.example.FootbalLeague.Model.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GameRepo extends CrudRepository<Game,Long> {


    void deleteById(Long id);

    Game findGameById(Long id);
}

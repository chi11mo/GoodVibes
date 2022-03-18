package com.example.FootbalLeague.repo;

import com.example.FootbalLeague.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepo extends JpaRepository<Player,Long> {
    void deleteById(Long id);

   Optional<Player> findPlayerById(Long id);
}

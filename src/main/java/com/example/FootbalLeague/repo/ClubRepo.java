package com.example.FootbalLeague.repo;

import com.example.FootbalLeague.Model.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClubRepo extends JpaRepository<Club,Long> {
    void deleteById(Long id);

    Optional<Club> findClubById(Long id);
}

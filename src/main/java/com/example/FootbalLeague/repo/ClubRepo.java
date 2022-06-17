package com.example.FootbalLeague.repo;

import com.example.FootbalLeague.Model.Club;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClubRepo extends CrudRepository<Club,Long> {
    void deleteById(Long id);

   Club findClubById(Long id);
}

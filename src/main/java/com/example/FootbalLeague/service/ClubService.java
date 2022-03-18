package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.Club;
import com.example.FootbalLeague.exception.UserNotFoundException;
import com.example.FootbalLeague.repo.ClubRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClubService {
    private final ClubRepo clubRepo;

    @Autowired
    public ClubService(ClubRepo clubRepo) {
        this.clubRepo = clubRepo;
    }

    public Club addClub(Club club) {
        club.setClubCode(UUID.randomUUID().toString());
        return clubRepo.save(club);
    }

    public List<Club> findAllClubs() {
        return clubRepo.findAll();
    }

    public Club findClubByID(Long id) {
        return clubRepo.findClubById(id)
                .orElseThrow(() -> new UserNotFoundException("Club By ID" + id + "was not found."));
    }

    public void deleteClub(Long id) {
        clubRepo.deleteById(id);
    }
}

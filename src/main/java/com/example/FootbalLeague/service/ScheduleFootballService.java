package com.example.FootbalLeague.service;

import com.example.FootbalLeague.Model.Club;
import com.example.FootbalLeague.Model.ScheduleFootball;
import com.example.FootbalLeague.exception.UserNotFoundException;
import com.example.FootbalLeague.repo.ClubRepo;
import com.example.FootbalLeague.repo.ScheduleFootballRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
@Service
public class ScheduleFootballService {
    private final ScheduleFootballRepo scheduleFootballRepo;
    @Autowired
    public ScheduleFootballService(ScheduleFootballRepo scheduleFootballRepo) {
        this.scheduleFootballRepo = scheduleFootballRepo;
    }

    public ScheduleFootball addSchedule(ScheduleFootball scheduleFootball) {
        scheduleFootball.setScheduleCode(UUID.randomUUID().toString());
        return scheduleFootballRepo.save(scheduleFootball);
    }

    public List<ScheduleFootball> findAllSchedules() {
        return scheduleFootballRepo.findAll();
    }

    public ScheduleFootball findScheduleByID(Long id) {
        return scheduleFootballRepo.findScheduleById(id)
                .orElseThrow(() -> new UserNotFoundException("Schedule By ID" + id + "was not found."));
    }

    public void deleteSchedule(Long id) {
        scheduleFootballRepo.deleteById(id);
    }
}

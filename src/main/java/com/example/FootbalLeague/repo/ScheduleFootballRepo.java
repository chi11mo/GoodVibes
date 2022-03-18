package com.example.FootbalLeague.repo;
import com.example.FootbalLeague.Model.ScheduleFootball;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleFootballRepo extends JpaRepository<ScheduleFootball,Long> {


    void deleteById(Long id);

    Optional<ScheduleFootball> findScheduleById(Long id);
}

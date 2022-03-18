package com.example.FootbalLeague.Model;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "schedule")
public class ScheduleFootball implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;


    String teamOne;
    String teamTwo;
    int day;
    String month;
    int year;

    @Column(nullable = false, updatable = false)
    private String scheduleCode;


    public ScheduleFootball(Long id, String teamOne, String teamTwo, int day, String month, int year, String scheduleCode) {
        this.id = id;
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.day = day;
        this.month = month;
        this.year = year;
        this.scheduleCode = scheduleCode;
    }

    public ScheduleFootball() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getScheduleCode() {
        return scheduleCode;
    }

    public void setScheduleCode(String scheduleCode) {
        this.scheduleCode = scheduleCode;
    }
}

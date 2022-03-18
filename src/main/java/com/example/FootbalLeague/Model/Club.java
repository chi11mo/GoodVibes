package com.example.FootbalLeague.Model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "club")
public class Club implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;


    private String team;
    private int plays;
    private int wins;
    private int loses;
    private int defines;
    private int goals;
    private int goalsAgainst;
    private int goalDiff;
    private int points;


    @Column(nullable = false, updatable = false)
    private String clubCode;




    public Club(long id, String team, int plays, int wins, int loses, int defines, int goals, int goalsAgainst, int goalDiff, int points, String clubCode) {
        this.id = id;
        this.team = team;
        this.plays = plays;
        this.wins = wins;
        this.loses = loses;
        this.defines = defines;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDiff = goalDiff;
        this.points = points;
        this.clubCode = clubCode;
    }

    public Club() {

    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDefines() {
        return defines;
    }

    public void setDefines(int defines) {
        this.defines = defines;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getClubCode() {
        return clubCode;
    }

    public void setClubCode(String clubCode) {
        this.clubCode = clubCode;
    }
}

package com.example.FootbalLeague.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "club")
public class Club implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column
    private String team;
    @Column
    private int plays;
    @Column
    private int wins;
    @Column
    private int loses;
    @Column
    private int defines;
    @Column
    private int goals;
    @Column
    private int goalsAgainst;
    @Column
    private int goalDiff;
    @Column
    private int points;
    @Column
    private boolean active;

    @ManyToMany
    private List<Game> game;

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
        this.active = false;
        this.clubCode = clubCode;
    }

    public Club(String team) {

        this.team = team;
        this.plays = 0;
        this.wins = 0;
        this.loses = 0;
        this.defines = 0;
        this.goals = 0;
        this.goalsAgainst = 0;
        this.goalDiff = 0;
        this.points = 0;
        this.active = false;


    }

    public Club() {

    }
}

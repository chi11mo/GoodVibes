package com.example.FootbalLeague.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;


    @Column
    String clubNameOne;
    @Column
    String clubNameTwo;
    int day;
    String month;
    int year;

    @Column
    boolean isPlayed = false;


    @Column
    int scoreTeamOne = 0;

    @Column
    int scoreTeamTwo = 0;


    @Column(nullable = false, updatable = false)
    private String gameCode;


    public Game(Long id, String clubNameOne, String clubNameTwo, int day, String month, int year, String gameCode) {
        this.id = id;
        this.clubNameOne = clubNameOne;
        this.clubNameTwo = clubNameTwo;
        this.day = day;
        this.month = month;
        this.year = year;
        this.gameCode = gameCode;
    }

    public Game() {
    }


    public Game(String clubNameOne, String clubNameTwo, int day, String month, int year) {
        this.clubNameOne = clubNameOne;
        this.clubNameTwo = clubNameTwo;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}

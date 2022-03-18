package com.example.FootbalLeague.Model;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;
    private String email;
    private String discord;
    private String twitch;
    private String psn;
    private String imgUrl;
    @Column(nullable = false, updatable = false)
    private String playerCode;


    public Player(Long id, String name, String email, String discord, String twitch, String psn, String imgUrl, String playerCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.discord = discord;
        this.twitch = twitch;
        this.psn = psn;
        this.imgUrl = imgUrl;
        this.playerCode = playerCode;

    }

    public Player() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiscord() {
        return discord;
    }

    public void setDiscord(String discord) {
        this.discord = discord;
    }

    public String getTwitch() {
        return twitch;
    }

    public void setTwitch(String twitch) {
        this.twitch = twitch;
    }

    public String getPsn() {
        return psn;
    }

    public void setPsn(String psn) {
        this.psn = psn;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setPlayerCode(String playerCode) {
        this.playerCode = playerCode;
    }

    public String getPlayerCode() {
        return playerCode;
    }

}

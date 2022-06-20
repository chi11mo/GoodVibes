package com.example.FootbalLeague.Model;


import javax.persistence.*;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "players")
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String twitch;
    private String eMail;
    private String discord;
    private String psn;
    private String steam;
    private String imgUrl;

    @Lob
    @Column
    private String password;

    @OneToOne
    private Club club;

    private Role role;

    private boolean isActive;
    @Column(nullable = false, updatable = false)
    private String playerCode;

    public Player(Long id, String eMail, String password, String discord, String twitch, String steam, String psn, String imgUrl, Role role, Club club, boolean isActive, String playerCode) {
        this.id = id;
        this.eMail = eMail;
        this.discord = discord;
        this.twitch = twitch;
        this.psn = psn;
        this.steam = steam;
        this.imgUrl = imgUrl;
        this.playerCode = playerCode;
        this.password = password;
        this.role = role;
        this.isActive = true;
        this.club = null;

    }



    public Player(String eMail, String password, String discord, String twitch, String steam, String psn, Role role) {

        this(null, eMail, password, discord, twitch, steam, psn, null, role, null, true, null);
    }

    public Player() {

    }

    public void setIsEnabled(boolean isActive) {
        this.isActive = true;
    }

    /**
     * public String getRoleString() {
     * switch (getRole()) {
     * case Role.PLAYER:
     * return "Player";
     * case Role.ADMIN:
     * return "Admin";
     * case Role.MODERATOR:
     * return "Moderator";
     * }
     * return null;
     * }
     **/
    public String getRoleString() {
        return this.role.toString();
    }
}

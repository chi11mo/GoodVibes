package com.example.FootbalLeague.Model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * This class represents a Token.
 */
@Getter
@Setter
@Table(name = "token")
@Entity
public class Token {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String tokenID;

    @Column
    private String tokenContent;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @OneToOne(targetEntity = Player.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "playerID")
    private Player player;

    /**
     * Token constructor.
     *
     * @param tokenContent   Token tokenContent
     * @param expiresAt      Token expiresAt
     * @param player           Token user
     */
    public Token(final String tokenContent, final LocalDateTime expiresAt, final Player player) {
        this.tokenContent = tokenContent;
        this.expiresAt = expiresAt;
        this.player = player;
    }

    protected Token() {

    }
}


package com.example.FootbalLeague.service;


import com.example.FootbalLeague.Model.Player;
import com.example.FootbalLeague.Model.Token;
import com.example.FootbalLeague.repo.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * An implementation of {@link TokenServiceImpl}.
 */
@Service
public class TokenServiceImpl  implements TokenService {
    /**
     * Public field that gives a value for expiring the token.
     */
    @SuppressWarnings("PMD.FinalFieldCouldBeStatic")
    public final int expiredAtUpdate = 20;
    private final TokenRepository tokenRepository;

    /**
     * Service to generate, find, an modify {@link Token}.
     *
     * @param tokenRepository repository.
     */
    @Autowired
    public TokenServiceImpl(final TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }


    @SuppressWarnings("PMD.LocalVariableCouldBeFinal")
    @Override
    public Token generateToken(final Player player) {
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(expiredAtUpdate);
        String tokenContent = UUID.randomUUID().toString();


        while (tokenRepository.findTokenByTokenContent(tokenContent) != null) {
            tokenContent = UUID.randomUUID().toString();
        }
        Token token = new Token(tokenContent, expiresAt, player);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Token findTokenbyContent(final String content) {
        return tokenRepository.findTokenByTokenContent(content);
    }

    @Override
    public void increaseExpiredTime(final Token token) {
        token.setExpiresAt(LocalDateTime.now().plusMinutes(expiredAtUpdate));
        tokenRepository.save(token);
    }


    @Override
    public Player getPlayerByToken(final String tokenContent) {
        return tokenRepository.findTokenByTokenContent(tokenContent).getPlayer();
    }
}



package com.example.FootbalLeague.service;


import com.example.FootbalLeague.Model.Player;
import com.example.FootbalLeague.Model.Token;

/**
 * Interface for the TokenService Layer.
 */
public interface TokenService {

    /**
     * method used to generate Token.
     *
     * @param player The User for who the token is supposed to be created
     * @return The created Token
     */
    Token generateToken(Player player);

    /**
     * method used to find a Token by its unique content.
     *
     * @param content the conent of the wanted Token
     * @return the Token which has the provided content
     */
    Token findTokenbyContent(String content);

    /**
     * method used to increase the Time of when the Token is expired.
     *
     * @param token the token of which the Time is supposed to get increased
     **/
    void increaseExpiredTime(Token token);

    /**
     * method used to get The User of a Token.
     *
     * @param tokenContent the String corresponding to the token of which the user is to be found
     * @return the User
     */
    Player getPlayerByToken(String tokenContent);
}

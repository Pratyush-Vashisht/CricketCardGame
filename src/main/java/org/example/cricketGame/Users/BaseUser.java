package org.example.cricketGame.Users;

import lombok.Data;
import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.enums.UserTypeEnum;
import org.example.cricketGame.model.Card;

import java.util.*;

import static org.example.cricketGame.Utils.Constants.DEFAULT_USER_HEALTH;

@Data
public abstract class BaseUser {
    protected UUID id = UUID.randomUUID();
    protected String name;
    protected double health = DEFAULT_USER_HEALTH;
    protected Map<String, Card> cards = new HashMap<>();
    protected GameMode gameMode;
    protected boolean isAlive = true;
    protected UserTypeEnum userTypeEnum;
    protected Map<String, Card> usedCards = new HashMap<>();

    public BaseUser(String name, GameMode gameMode, UserTypeEnum userTypeEnum) {
        this.name = name;
        this.gameMode = gameMode;
        this.userTypeEnum = userTypeEnum;
    }

    public void reduceHealth(double amount) {
        health = Math.max(0, health - amount);
    }
//    public Card playCard(Attribute attribute) {
//        if (cards.isEmpty()) return null;
//        return cards.remove(0);
//    }


    @Override
    public String toString() {
        return "\n\nUser - " + name +
                "\nid= " + id +
                "\nhealth= " + health +
                "\ngameMode= " + gameMode.getGameModeEnum().name() +
                "\nisAlive= " + isAlive +
                "\nuserTypeEnum= " + userTypeEnum +
                "\ncards= " + cards;
    }

    public Card getCardByPlayerName(String playerName) {
        return cards.get(playerName);
    }
}

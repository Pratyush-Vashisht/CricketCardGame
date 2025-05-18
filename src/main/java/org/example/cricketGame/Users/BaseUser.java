package org.example.cricketGame.Users;

import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.model.Card;
import org.example.cricketGame.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@lombok.Data
public abstract class BaseUser implements User {
    protected UUID id = UUID.randomUUID();
    protected String name;
    protected double health = 100;
    protected List<Card> cards = new ArrayList<>();
    protected GameMode gameMode;
    protected boolean isAlive = true;

    public BaseUser(String name, GameMode gameMode) {
        this.name = name;
        this.gameMode = gameMode;
    }

    public void reduceHealth(double amount) { health = Math.max(0, health - amount); }
//    public Card playCard(Attribute attribute) {
//        if (cards.isEmpty()) return null;
//        return cards.remove(0);
//    }


}

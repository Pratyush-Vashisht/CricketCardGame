package org.example.cricketGame.Users;

import org.example.cricketGame.Game.GameMode;
import org.example.cricketGame.model.Attribute;
import org.example.cricketGame.model.Card;
import org.example.cricketGame.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseUser implements User {
    protected UUID id = UUID.randomUUID();
    protected String name;
    protected double health = 100;
    protected List<Card> cards = new ArrayList<>();
    protected GameMode gameMode;

    public BaseUser(String name, GameMode gameMode) {
        this.name = name;
        this.gameMode = gameMode;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public double getHealth() { return health; }
    public void reduceHealth(double amount) { health = Math.max(0, health - amount); }
    public boolean isAlive() { return health > 0; }
    public List<Card> getCards() { return cards; }
    public GameMode getGameMode() { return gameMode; }
//    public Card playCard(Attribute attribute) {
//        if (cards.isEmpty()) return null;
//        return cards.remove(0);
//    }


}

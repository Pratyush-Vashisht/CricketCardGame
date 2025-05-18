package org.example.cricketGame.model;

import org.example.cricketGame.Game.modes.GameMode;

import java.util.List;
import java.util.UUID;

public interface User {
    UUID getId();
    String getName();
    GameMode getGameMode();
    double getHealth();
    void reduceHealth(double amount);
    boolean isAlive();
    List<Card> getCards();
//    Card playCard(Attribute attribute);
}

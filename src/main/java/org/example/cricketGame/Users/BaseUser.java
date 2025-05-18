package org.example.cricketGame.Users;

import lombok.Data;
import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.enums.UserTypeEnum;
import org.example.cricketGame.model.Card;
import org.example.cricketGame.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.example.cricketGame.Utils.Constants.DEFAULT_USER_HEALTH;

@Data
public abstract class BaseUser implements User {
    protected UUID id = UUID.randomUUID();
    protected String name;
    protected double health = DEFAULT_USER_HEALTH;
    protected List<Card> cards = new ArrayList<>();
    protected GameMode gameMode;
    protected boolean isAlive = true;
    protected UserTypeEnum userTypeEnum;

    public BaseUser(String name, GameMode gameMode, UserTypeEnum userTypeEnum) {
        this.name = name;
        this.gameMode = gameMode;
        this.userTypeEnum = userTypeEnum;
    }

    public void reduceHealth(double amount) { health = Math.max(0, health - amount); }
//    public Card playCard(Attribute attribute) {
//        if (cards.isEmpty()) return null;
//        return cards.remove(0);
//    }


}

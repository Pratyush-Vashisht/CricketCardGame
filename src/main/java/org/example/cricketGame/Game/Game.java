package org.example.cricketGame.Game;

import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Round> rounds;
    private List<Card> cards;
    private List<BaseUser> users;

    public Game(List<Card> cards, List<BaseUser> users) {
        this.rounds = new ArrayList<>();
        this.cards = cards;
        this.users = users;
    }

}

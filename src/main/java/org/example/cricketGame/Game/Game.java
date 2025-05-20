package org.example.cricketGame.Game;

import lombok.Data;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.model.Card;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    private List<Card> cards;
    private List<BaseUser> users;
    private int numberOfRounds;

    private List<Round> rounds;

    public Game(List<Card> cards, List<BaseUser> users,int numberOfRounds) {
        this.rounds = new ArrayList<>();
        this.cards = cards;
        this.users = users;
        this.numberOfRounds = numberOfRounds;
    }

}

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

    private List<Round> rounds;

    public Game(List<Card> cards, List<BaseUser> users) {
        this.rounds = new ArrayList<>();
        this.cards = cards;
        this.users = users;
    }

}

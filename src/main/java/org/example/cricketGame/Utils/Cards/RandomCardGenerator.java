package org.example.cricketGame.Utils.Cards;

import org.example.cricketGame.model.Card;

import java.util.ArrayList;
import java.util.List;

public class RandomCardGenerator implements CardGenerator {


    public List<Card> generateRandomCards(int numberOfCards) {
        ArrayList<Card> cards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            int runs = (int) (Math.random() * 10000);
            int matches = (int) (Math.random() * 500);
            int centuries = (int) (Math.random() * 100);
            int fifties = (int) (Math.random() * 200);
            int catches = (int) (Math.random() * 300);
            int wickets = (int) (Math.random() * 400);
            cards.add(new Card("Player "+ (i+1),runs, matches, centuries, fifties, catches, wickets));
        }
        return cards;
    }

    @Override
    public List<Card> generateCards(int numberOfCards) {
        return generateRandomCards(numberOfCards);
    }
}

package org.example.cricketGame.Utils;

import org.example.cricketGame.model.Card;

import java.util.List;

public interface CardGenerator {
     List<Card> generateCards(int numberOfCards);
}

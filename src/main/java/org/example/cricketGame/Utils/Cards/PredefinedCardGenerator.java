package org.example.cricketGame.Utils.Cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.model.Card;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PredefinedCardGenerator {
    public static List<Card> LoadCards(){
        List<Card> cards = new ArrayList<>();
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            CardData[] cardData = objectMapper.readValue(new File(Constants.CARD_JSON_PATH), CardData[].class);
            for (CardData card : cardData) {
                cards.add(new Card(
                        card.name,
                        card.runs,
                        card.matches,
                        card.centuries,
                        card.fifties,
                        card.catches,
                        card.wickets
                ));
            }

        } catch (Exception e) {
//            throw new RuntimeException(e);
            System.out.printf("Error loading cards: %s", e.getMessage());
        }
        return cards;
    }


    public static List<Card> generateCards(int numberOfCards) {
        List<Card> cards = LoadCards();
        Collections.shuffle(cards);
        return cards.subList(0, numberOfCards);
    }
}

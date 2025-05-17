package org.example.cricketGame.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.cricketGame.model.Card;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PredefinedCardGenerator implements CardGenerator{
    public List<Card> LoadCards(){
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


    @Override
    public List<Card> generateCards(int numberOfCards) {
        return LoadCards();
    }
}

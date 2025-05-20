package org.example.cricketGame.model.Attributes;

import org.example.cricketGame.model.Card;

public class MatchesAttribute implements AttributeStrategy{
    @Override
    public int getValue(Card card) {
        return card.getMatches();
    }
}

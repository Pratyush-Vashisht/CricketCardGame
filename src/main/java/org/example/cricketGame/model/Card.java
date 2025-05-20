package org.example.cricketGame.model;

import lombok.Data;
import org.example.cricketGame.enums.Attribute;

// TODO Implementation should use interfaces or abstract classes to enable attribute extension
@Data
public class Card {
    private String name;
    private int runs;
    private int matches;
    private int centuries;
    private int fifties;
    private int catches;
    private int wickets;
    private boolean isLast;
    private boolean isActive;

    public Card(String name, int runs, int matches, int centuries, int fifties, int catches, int wickets) {
        this.name = name;
        this.runs = runs;
        this.matches = matches;
        this.centuries = centuries;
        this.fifties = fifties;
        this.catches = catches;
        this.wickets = wickets;
        this.isLast = false;
        this.isActive = true;
    }
    public int getAttributeValue(Attribute attribute) {
        switch (attribute) {
            case RUNS: return this.getRuns();
            case WICKETS: return this.getWickets();
            case CENTURIES: return this.getCenturies();
            case CATCHES: return this.getCatches();
            case FIFTIES: return this.getFifties();
            case MATCHES: return this.getMatches();
            default: return 0;
        }
    }
    @Override
    public String toString() {
        return String.format("Player: %s, \tRuns: %d, \tMatches: %d, \tCenturies: %d, \t50s: %d, \tCatches: %d, \tWickets: %d\n",
                name, runs, matches, centuries, fifties, catches, wickets);
    }
}

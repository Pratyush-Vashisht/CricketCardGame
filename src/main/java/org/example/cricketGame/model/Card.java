package org.example.cricketGame.model;

import lombok.Data;

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

    public Card(String name, int runs, int matches, int centuries, int fifties, int catches, int wickets) {
        this.name = name;
        this.runs = runs;
        this.matches = matches;
        this.centuries = centuries;
        this.fifties = fifties;
        this.catches = catches;
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return String.format("Player: %s, \tRuns: %d, \tMatches: %d, \tCenturies: %d, \t50s: %d, \tCatches: %d, \tWickets: %d\n",
                name, runs, matches, centuries, fifties, catches, wickets);
    }
}

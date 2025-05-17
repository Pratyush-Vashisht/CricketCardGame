package org.example.cricketGame.model;

public class Card {
    private int runs;
    private int matches;
    private int centuries;
    private int fifties;
    private int catches;
    private int wickets;

    public Card(int runs, int matches, int centuries, int fifties, int catches, int wickets) {
        this.runs = runs;
        this.matches = matches;
        this.centuries = centuries;
        this.fifties = fifties;
        this.catches = catches;
        this.wickets = wickets;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int getCenturies() {
        return centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public int getFifties() {
        return fifties;
    }

    public void setFifties(int fifties) {
        this.fifties = fifties;
    }

    public int getCatches() {
        return catches;
    }

    public void setCatches(int catches) {
        this.catches = catches;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    @Override
    public String toString() {
        return String.format("Card(Runs: %d, Matches: %d, Centuries: %d, 50s: %d, Catches: %d, Wickets: %d)",
                runs, matches, centuries, fifties, catches, wickets);
    }
}

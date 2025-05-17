package org.example.cricketGame.Game;

public class StandardMode implements GameMode{
    @Override
    public double calculateHealthLossOpponent() {
        return 10.0;
    }

    @Override
    public double calculateHealthLossPlayer() {
        return 10.0;
    }
}

package org.example.cricketGame.Game.modes;

import org.example.cricketGame.enums.GameModeEnum;

public abstract class GameMode {
    private boolean isGameModeActive = true;

    public abstract double calculateHealthLossOpponent();
    public abstract double calculateHealthLossPlayer();
    public abstract GameModeEnum getGameModeEnum();

    public boolean isGameModeActive() {
        return isGameModeActive;
    }

    public void deactivateGameMode() {
        isGameModeActive = false;
    }
}
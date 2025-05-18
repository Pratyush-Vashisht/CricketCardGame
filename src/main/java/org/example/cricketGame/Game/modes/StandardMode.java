package org.example.cricketGame.Game.modes;

import org.example.cricketGame.enums.GameModeEnum;

public class StandardMode extends GameMode {
    @Override
    public double calculateHealthLossOpponent() {
        return 10.0;
    }

    @Override
    public double calculateHealthLossPlayer() {
        return 10.0;
    }

    @Override
    public GameModeEnum getGameModeEnum() {
        return GameModeEnum.STANDARD;
    }

    @Override
    public void deactivateGameMode() {
        // do not deactivate standard Mode
    }
}

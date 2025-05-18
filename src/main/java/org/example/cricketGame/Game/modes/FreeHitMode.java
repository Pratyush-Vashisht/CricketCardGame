package org.example.cricketGame.Game.modes;

import org.example.cricketGame.enums.GameModeEnum;

// TODO
public class FreeHitMode extends GameMode {

    @Override
    public double calculateHealthLossOpponent() {
        return 0;
    }

    @Override
    public double calculateHealthLossPlayer() {
        return 0;
    }

    @Override
    public GameModeEnum getGameModeEnum() {
        return GameModeEnum.FREE_HIT;
    }
}

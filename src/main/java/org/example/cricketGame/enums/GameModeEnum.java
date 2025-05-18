package org.example.cricketGame.enums;

import org.example.cricketGame.Game.modes.*;

public enum GameModeEnum {
    STANDARD(new StandardMode()),
    FREE_HIT(new FreeHitMode()),
    SUPER_MODE(new SuperMode()),
    POWER_PLAY(new PowerPlayMode()),
    WORLD_CUP(new WorldCupMode());

    private final GameMode gameMode;

    GameModeEnum(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }
}

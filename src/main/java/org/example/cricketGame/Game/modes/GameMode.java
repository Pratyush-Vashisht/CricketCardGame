package org.example.cricketGame.Game.modes;

import org.example.cricketGame.enums.GameModeEnum;

import java.util.Arrays;
import java.util.List;

public interface GameMode {
    static GameModeEnum getRandomGameMode() {
        List<GameModeEnum> list = Arrays.asList(GameModeEnum.values())    ;
        int randomIndex = (int) (Math.random() * list.size());
        return list.get(randomIndex);
    }
    double calculateHealthLossOpponent();
    double calculateHealthLossPlayer();
}
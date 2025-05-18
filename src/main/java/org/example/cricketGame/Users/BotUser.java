package org.example.cricketGame.Users;

import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.enums.UserTypeEnum;

import java.util.Arrays;
import java.util.List;

public class BotUser extends BaseUser {
    public BotUser(String name, GameMode gameMode) {
        super(name, gameMode, UserTypeEnum.BOT);
    }


    public static GameModeEnum getRandomGameMode() {
        List<GameModeEnum> list = Arrays.asList(GameModeEnum.values())    ;
        int randomIndex = (int) (Math.random() * list.size());
        return list.get(randomIndex);
    }
}

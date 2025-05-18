package org.example.cricketGame.Users;

import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.enums.UserTypeEnum;

public class BotUser extends BaseUser {
    public BotUser(String name, GameMode gameMode) {
        super(name, gameMode, UserTypeEnum.BOT);
    }
}

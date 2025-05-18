package org.example.cricketGame.Users;

import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.enums.UserTypeEnum;

public class HumanUser extends BaseUser {
    public HumanUser(String name, GameMode gameMode) {
        super(name, gameMode, UserTypeEnum.HUMAN);
    }
}

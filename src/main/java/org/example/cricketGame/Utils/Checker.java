package org.example.cricketGame.Utils;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Game.modes.GameMode;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.model.Card;

import java.util.*;

public class Checker{

    public static void checkRound(Round round, Map<UUID,BaseUser> userMap, Queue<UUID> userQueue,int roundNumber) {
        GameMode gameMode = round.getInitiatingUserGameMode().getGameMode();
        gameMode.applyHealthStrategy(round, userMap, userQueue);
        for(Card card : round.getAllUserCardMap().values()){
            card.setActive(false);
        }
        round.getActiveUsersIdSet().clear();
        for(BaseUser user : userMap.values()){
            if(user.isAlive()){
                round.getActiveUsersIdSet().add(user.getId());
            }
        }
        System.out.println(round.toString());
    }




}

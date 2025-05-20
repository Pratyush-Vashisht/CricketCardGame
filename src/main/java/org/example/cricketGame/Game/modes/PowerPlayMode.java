package org.example.cricketGame.Game.modes;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;

import java.util.*;

// TODO
public class PowerPlayMode extends GameMode {

//    @Override
//    public double calculateHealthLossOpponent() {
//        return 0;
//    }
//
//    @Override
//    public double calculateHealthLossPlayer() {
//        return 0;
//    }

    @Override
    public GameModeEnum getGameModeEnum() {
        return GameModeEnum.POWER_PLAY;
    }

    @Override
    public void applyHealthStrategy(Round round, Map<UUID, BaseUser> userMap, Queue<UUID> userQueue) {
        Attribute attr1 = round.getPrimaryAttribute();
        Attribute attr2 = round.getSecondaryAttribute();
        UUID roundInitiatingUserId = round.getInitiatingUserId();
        BaseUser initiatingUser = userMap.get(round.getInitiatingUserId());
        initiatingUser.getGameMode().deactivateGameMode();

        List<Map.Entry<UUID, Card>> cardEntries = new ArrayList<>(round.getAllUserCardMap().entrySet());
        UUID winnerFirstAttributeId = cardEntries.stream().max(Comparator.comparingInt(entry -> entry.getValue().getAttributeValue(attr1))).get().getKey();
        UUID winnerSecondAttributeId = cardEntries.stream().max(Comparator.comparingInt(entry -> entry.getValue().getAttributeValue(attr2))).get().getKey();
        UUID winnerId =( roundInitiatingUserId.equals(winnerFirstAttributeId) || roundInitiatingUserId.equals(winnerSecondAttributeId)) ? roundInitiatingUserId : winnerFirstAttributeId;


        for (Map.Entry<UUID, Card> entry : round.getAllUserCardMap().entrySet()) {
            UUID userId = entry.getKey();
            BaseUser user = userMap.get(userId);
            if(!userId.equals(winnerId)){
                user.reduceHealth(Constants.STANDARD_MODE_PLAYER_DAMAGE);
            }
            round.getHealthAfter().put(userId, user.getHealth());
        }

        // Eliminate players with 0 or less health
        this.removePlayers(userMap,userQueue);
    }

}

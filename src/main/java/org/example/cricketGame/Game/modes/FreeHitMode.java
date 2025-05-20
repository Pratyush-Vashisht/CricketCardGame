package org.example.cricketGame.Game.modes;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;

import java.util.*;

// TODO
public class FreeHitMode extends GameMode {

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
        return GameModeEnum.FREE_HIT;
    }

    @Override
    public void applyHealthStrategy(Round round, Map<UUID, BaseUser> userMap, Queue<UUID> userQueue) {
        Attribute attribute = round.getPrimaryAttribute();
        UUID roundInitiatorId = round.getInitiatingUserId();
        BaseUser initiatingUser = userMap.get(round.getInitiatingUserId());
        initiatingUser.getGameMode().deactivateGameMode();
        List<Map.Entry<UUID, Card>> cardEntries = new ArrayList<>(round.getAllUserCardMap().entrySet());

//        // Sort descending by attribute
//        cardEntries.sort((e1, e2) ->
//                Integer.compare(e2.getValue().getAttributeValue(attribute), e1.getValue().getAttributeValue(attribute)));
//
//        UUID winnerId = cardEntries.get(0).getKey();
//        BaseUser winner = userMap.get(winnerId);
        // Sorting not required as we are only interested in the winner

        // Find the winner by getting the max value of the attribute
        UUID winnerId = cardEntries.stream().max(Comparator.comparingInt(entry -> entry.getValue().getAttributeValue(attribute))).get().getKey();

        for (Map.Entry<UUID, Card> entry : cardEntries) {
            UUID userId = entry.getKey();
            BaseUser user = userMap.get(userId);

            if (!userId.equals(winnerId) && userId.equals(roundInitiatorId)) {
                user.reduceHealth(Constants.FREE_HIT_PLAYER_DAMAGE);
            }
            else if(!userId.equals(winnerId) && !userId.equals(roundInitiatorId)) {
                user.reduceHealth(Constants.FREE_HIT_OPPONENT_DAMAGE);
            }

            round.getHealthAfter().put(userId, user.getHealth());
        }

        // Remove dead players
        this.removePlayers(userMap,userQueue);
    }
}

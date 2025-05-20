package org.example.cricketGame.Game.modes;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;

import java.util.*;

// TODO
public class WorldCupMode extends GameMode {
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
        return GameModeEnum.WORLD_CUP;
    }

    @Override
    public void applyHealthStrategy(Round round, Map<UUID, BaseUser> userMap, Queue<UUID> userQueue) {
        Attribute attribute = round.getPrimaryAttribute();
        BaseUser initiatingUser = userMap.get(round.getInitiatingUserId());
        initiatingUser.getGameMode().deactivateGameMode();
        round.getAllUserCardMap().put(round.getInitiatingUserId(), round.getInitiatingUserCard());
        round.getActiveUsersIdSet().add(round.getInitiatingUserId());

        List<Map.Entry<UUID, Card>> cardEntries = new ArrayList<>(round.getAllUserCardMap().entrySet());



        UUID winnerId = cardEntries.stream().max(Comparator.comparingInt(entry -> entry.getValue().getAttributeValue(attribute))).get().getKey();
        BaseUser winner = userMap.get(winnerId);

        boolean isFinalCard = winner.getCards().size() == 1;

        for (Map.Entry<UUID, Card> entry : cardEntries) {
            UUID userId = entry.getKey();
            BaseUser user = userMap.get(userId);

            if (!userId.equals(winnerId)) {
                user.reduceHealth(isFinalCard ? 20 : 10); // double damage if final card
            }

            round.getHealthAfter().put(userId, user.getHealth());
        }

        this.removePlayers(userMap,userQueue);
    }

}

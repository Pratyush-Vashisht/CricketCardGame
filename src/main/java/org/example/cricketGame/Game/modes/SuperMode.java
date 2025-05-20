package org.example.cricketGame.Game.modes;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Attributes.AttributeFactory;
import org.example.cricketGame.model.Attributes.AttributeStrategy;
import org.example.cricketGame.model.Card;

import java.util.*;

// TODO
public class SuperMode extends GameMode {
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
        return GameModeEnum.SUPER_MODE;
    }

    @Override
    public void applyHealthStrategy(Round round, Map<UUID, BaseUser> userMap, Queue<UUID> userQueue) {
        AttributeStrategy attribute = round.getPrimaryAttributeStrategy();
        BaseUser initiatingUser = userMap.get(round.getInitiatingUserId());
        initiatingUser.getGameMode().deactivateGameMode();
        List<Map.Entry<UUID, Card>> cardEntries = new ArrayList<>(round.getAllUserCardMap().entrySet());

        UUID winnerId = cardEntries.stream().max(Comparator.comparingInt(entry -> (attribute.getValue(entry.getValue())))).get().getKey();
        BaseUser winner = userMap.get(winnerId);

        AttributeStrategy runsStrategy = AttributeFactory.getAttribute(Attribute.RUNS);
        AttributeStrategy wicketsStrategy = AttributeFactory.getAttribute(Attribute.WICKETS);

        boolean hasHighestRuns = winner.getCards().values().stream()
                .max(Comparator.comparingInt(runsStrategy::getValue))
                .map(runsStrategy::getValue)
                .orElse(0) >=
                userMap.values().stream().flatMap(u -> u.getCards().values().stream())
                        .mapToInt(runsStrategy::getValue).max().orElse(0);

        boolean hasHighestWickets = winner.getCards().values().stream()
                .max(Comparator.comparingInt(wicketsStrategy::getValue))
                .map(wicketsStrategy::getValue)
                .orElse(0) >=
                userMap.values().stream().flatMap(u -> u.getCards().values().stream())
                        .mapToInt(wicketsStrategy::getValue).max().orElse(0);

        boolean qualifies = hasHighestRuns && hasHighestWickets;

        for (Map.Entry<UUID, Card> entry : cardEntries) {
            UUID userId = entry.getKey();
            BaseUser user = userMap.get(userId);

            if (!userId.equals(winnerId)) {
                if (qualifies) {
                    user.reduceHealth(Constants.SUPER_MODE_TRUE_DAMAGE); // fixed damage
                } else {
                    user.reduceHealth(Constants.STANDARD_MODE_OPPONENT_DAMAGE); // fallback standard damage
                }
            }

            round.getHealthAfter().put(userId, user.getHealth());
        }

        this.removePlayers(userMap,userQueue);
    }

}

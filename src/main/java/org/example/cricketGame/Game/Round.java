package org.example.cricketGame.Game;

import org.example.cricketGame.enums.Attribute;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Round {
    private UUID initiatingPlayerId;
    private List<UUID> activeUsersIds ;
    private Attribute attribute;
    private Map<UUID, Integer> healthBefore;
    private Map<UUID, Integer> healthAfter;
    private String winnerName;
    private UUID winnerId;

    public Round(UUID initiatingPlayerId, Attribute attribute, Map<UUID, Integer> healthBefore, Map<UUID, Integer> healthAfter, String winnerName, UUID winnerId) {
        this.initiatingPlayerId = initiatingPlayerId;
        this.activeUsersIds = activeUsersIds;
        this.attribute = attribute;
        this.healthBefore = healthBefore;
        this.healthAfter = healthAfter;
        this.winnerName = winnerName;
        this.winnerId = winnerId;
    }
}

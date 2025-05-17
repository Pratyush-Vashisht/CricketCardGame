package org.example.cricketGame.Game;

import org.example.cricketGame.model.Attribute;

import java.util.Map;
import java.util.UUID;

public class Round {
    private UUID initiatingPlayerId;
    private Attribute attribute;
    private Map<UUID, Integer> healthBefore;
    private Map<UUID, Integer> healthAfter;
    private String winnerName;
    private UUID winnerId;

    public Round(UUID initiatingPlayerId, Attribute attribute, Map<UUID, Integer> healthBefore, Map<UUID, Integer> healthAfter, String winnerName, UUID winnerId) {
        this.initiatingPlayerId = initiatingPlayerId;
        this.attribute = attribute;
        this.healthBefore = healthBefore;
        this.healthAfter = healthAfter;
        this.winnerName = winnerName;
        this.winnerId = winnerId;
    }
}

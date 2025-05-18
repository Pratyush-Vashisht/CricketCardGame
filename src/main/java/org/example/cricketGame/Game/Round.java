package org.example.cricketGame.Game;

import lombok.Data;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;

import java.util.*;

@Data
public class Round {
    private UUID initiatingUserId;
    private Attribute attribute;
    private GameModeEnum initiatingUserGameMode;
    private Card initiatingUserCard;
    private Set<UUID> activeUsersIdSet = new HashSet<>();


    private Map<UUID, Card> allUserCardMap = new HashMap<>();
    private Map<UUID, Double> healthBefore = new HashMap<>();
    private Map<UUID, Double> healthAfter = new HashMap<>();
    private String winnerName;
    private UUID winnerId;

    public Round(UUID initiatingUserId,
                 Attribute attribute,
                 GameModeEnum initiatingUserGameMode,
                 Card initiatingUserCard) {
        this.initiatingUserId = initiatingUserId;
        this.attribute = attribute;
        this.initiatingUserGameMode = initiatingUserGameMode;
        this.initiatingUserCard = initiatingUserCard;

        this.activeUsersIdSet.add(initiatingUserId);
        this.allUserCardMap.put(initiatingUserId, initiatingUserCard);
    }
}

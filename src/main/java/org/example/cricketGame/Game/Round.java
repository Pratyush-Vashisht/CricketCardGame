package org.example.cricketGame.Game;

import lombok.Data;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Attributes.AttributeStrategy;
import org.example.cricketGame.model.Card;

import java.util.*;

@Data
public class Round {
    private UUID initiatingUserId;
    private AttributeStrategy primaryAttributeStrategy;
    private AttributeStrategy secondaryAttributeStrategy;
    private GameModeEnum initiatingUserGameMode;
    private Card initiatingUserCard;
    private Set<UUID> activeUsersIdSet = new HashSet<>();

    private Map<UUID, Card> allUserCardMap = new HashMap<>();
    private Map<UUID, Double> healthBefore = new HashMap<>();
    private Map<UUID, Double> healthAfter = new HashMap<>();
    private String winnerName;
    private UUID winnerId;

    public Round(UUID initiatingUserId,
                 AttributeStrategy attributeStrategy1,
                 AttributeStrategy attributeStrategy2,
                 GameModeEnum initiatingUserGameMode,
                 Card initiatingUserCard) {
        this.initiatingUserId = initiatingUserId;
        this.primaryAttributeStrategy = attributeStrategy1;
        this.secondaryAttributeStrategy = attributeStrategy2;
        this.initiatingUserGameMode = initiatingUserGameMode;
        this.initiatingUserCard = initiatingUserCard;
        this.activeUsersIdSet.add(initiatingUserId);
        this.allUserCardMap.put(initiatingUserId, initiatingUserCard);
    }

    public Round(UUID initiatingUserId,
                 AttributeStrategy attributeStrategy1,
                 GameModeEnum initiatingUserGameMode,
                 Card initiatingUserCard) {
        this(initiatingUserId,
                attributeStrategy1,
                null,
                initiatingUserGameMode,
                initiatingUserCard);
    }

}

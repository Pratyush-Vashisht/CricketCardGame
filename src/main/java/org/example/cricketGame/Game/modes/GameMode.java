package org.example.cricketGame.Game.modes;

import org.example.cricketGame.Game.Round;
import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.enums.GameModeEnum;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;

public abstract class GameMode {
    private boolean isGameModeActive = true;

//    public abstract double calculateHealthLossOpponent();
//    public abstract double calculateHealthLossPlayer();
    public abstract GameModeEnum getGameModeEnum();
    public abstract void applyHealthStrategy(Round round, Map<UUID, BaseUser> userMap, Queue<UUID> userQueue);

    public boolean isGameModeActive() {
        return isGameModeActive;
    }

    public void deactivateGameMode() {
        isGameModeActive = false;
    }

    public void removePlayers(Map<UUID, BaseUser> userMap, Queue<UUID> userQueue) {
        Iterator<UUID> iterator = userQueue.iterator();

        while (iterator.hasNext()) {
            UUID userId = iterator.next();
            BaseUser user = userMap.get(userId);

            if (user.getHealth() <= 0) {
                user.setAlive(false); // Mark as not alive
                iterator.remove();    // Safe removal from queue
            }
        }
    }
}
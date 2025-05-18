package org.example.cricketGame.Utils;

import java.util.List;

public class Constants {
    // Game Modes
    // Standard Mode
    public static final double STANDARD_MODE_PLAYER_DAMAGE = 10.0;
    public static final double STANDARD_MODE_OPPONENT_DAMAGE = 10.0;

    // Free Hit Mode
    public static final double FREE_HIT_PLAYER_DAMAGE = 15.0;
    public static final double FREE_HIT_OPPONENT_DAMAGE = 12.5;

    // Power Play Mode
    public static final double POWER_PLAY_PLAYER_DAMAGE = 15.0;
    public static final double POWER_PLAY_OPPONENT_DAMAGE = 12.5;

    // Super Mode
    public static final double SUPER_MODE_PLAYER_DAMAGE = 15.0;
    public static final double SUPER_MODE_OPPONENT_DAMAGE = 12.5;

    // World Cup Mode
    public static final double WORLD_CUP_PLAYER_DAMAGE = 20.0;
    public static final double WORLD_CUP_OPPONENT_DAMAGE = 15.0;



    // Game Inputs
    public static final int MAX_USERS = 2;
//    public static final int MAX_PLAYER_CARDS = 100;
    public static final String CARD_JSON_PATH = "src/main/resources/cards.json";
    public static final double DEFAULT_USER_HEALTH = 100.0;

    private Constants() {
        // Prevent instantiation
    }
}

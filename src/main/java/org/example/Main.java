package org.example;

import org.example.cricketGame.Game.GameSimulation;
import org.example.cricketGame.Utils.input.ConsoleInputProvider;
import org.example.cricketGame.Utils.input.InputProvider;
import org.example.cricketGame.enums.GameModeEnum;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static InputProvider inputProvider;
    public static void main(String[] args) {
        inputProvider = new ConsoleInputProvider();

        int numUsers = inputProvider.getIntInput("Please enter number of users: ");

        // Show game mode options
        System.out.println("Please select a Game Mode from the following:");
        GameModeEnum[] gameModes = GameModeEnum.values();
        List<GameModeEnum> gameModeList = new ArrayList<>(Arrays.asList(gameModes));
        gameModeList.remove(0); // Remove the first element (0th index) from the list
        for (int i = 0; i < gameModeList.size(); i++) {
            System.out.println((i+1) + ": " + gameModeList.get(i).name());
        }

        // Collect user details
        List<Pair<String, GameModeEnum>> userDetails = new ArrayList<>();
        for (int i = 1; i <= numUsers; i++) {
            String username = inputProvider.getStringInput("Enter name for user " + i + ": ");
            int modeIndex;
            while (true) {
                modeIndex = inputProvider.getIntInput("Select game mode (1-" + (gameModeList.size()) + "): ");
                if (modeIndex >= 0 && modeIndex < gameModes.length) break;
                System.out.println("Invalid option. Try again.");
            }
            userDetails.add(Pair.with(username, gameModeList.get(modeIndex-1)));
        }

        // Initialize and start game
        GameSimulation gameSimulation = new GameSimulation();
        int cardsPerPlayer = inputProvider.getIntInput("Enter number of cards per player: ");

        gameSimulation.initGame(numUsers, cardsPerPlayer, userDetails);
        gameSimulation.startGame();
        // Close input provider
        if (inputProvider instanceof ConsoleInputProvider) {
            ((ConsoleInputProvider) inputProvider).close();
        }
    }
}

/**
 * public class Main {
 *     public static void main(String[] args) {
 *         System.out.println("Please Enter no of users!");
 *         // TODO take input for no of users
 *         System.out.println("Please Enter User name and game mode!");
 *         System.out.println("Please enter Game Modes as");
 *         List<GameModeEnum> gameModes = List.of(GameModeEnum.values());
 *         for (int i = 0; i < gameModes.size(); i++) {
 *             System.out.println(i + " for game mode " + gameModes.get(i).name());
 *         }
 *
 *         List<Pair<String, GameModeEnum>> userDetails = new ArrayList<>();
 *         userDetails.add(Pair.with("Nikhil", GameModeEnum.SUPER_MODE));
 *         userDetails.add(Pair.with("Pratyush", GameModeEnum.FREE_HIT));
 *         // TODO take input for user name and game mode
 *         // create user
 *
 *
 *         // create loo
 *
 *         GameSimulation gameSimulation = new GameSimulation();
 *         gameSimulation.initGame(4,NO_OF_CARDS_PER_USER, userDetails);
 *         gameSimulation.startGame();
 *     }
 * }
 */
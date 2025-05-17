package org.example.cricketGame.Game;

import org.example.cricketGame.Utils.Constants;

public class GameSimulation implements GameProgress{
////    @Override
/// /    public void startGame()

    public GameSimulation(int numberOfUsers, int numberOfCardsPerPlayer) {
        System.out.println("Game started with " + numberOfUsers + " players.");
        // Initialize game components, players, and other necessary elements here
        // For example, you can create player objects, set up the game board, etc.
        // You can also implement the logic for the game loop here
        int botUsers = Constants.MAX_USERS - numberOfUsers;

    }
    public void initGame() {
        System.out.println("Game started!");
        // Initialize game components, players, and other necessary elements here
        // For example, you can create player objects, set up the game board, etc.
        // You can also implement the logic for the game loop here


    }
    public void startGame() {
    }

    @Override
    public void endGame() {

    }

    @Override
    public void saveGameProgress() {

    }



////    @Override
//    public void loadGameProgress() {
//
//    }
}

package org.example.cricketGame.Game;

import org.example.cricketGame.Utils.PredefinedCardGenerator;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.model.Card;

import java.util.List;

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
        PredefinedCardGenerator predefinedCardGenerator = new PredefinedCardGenerator();
        List<Card> cards = predefinedCardGenerator.generateCards(Constants.MAX_USERS);
        // list of cards in deck
        for(Card card : cards) {
            System.out.println(card.toString());
        }
//        for(int i = 0;i < numberOfUsers; i++) {
//
//        }
        // Initialize game components, players, and other necessary elements here
        // For example, you can create player objects, set up the game board, etc.
        // You can also implement the logic for the game loop here


    }
    public void startGame() {
        System.out.println("Game started!");
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

package org.example.cricketGame.Game;

import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Users.BotUser;
import org.example.cricketGame.Users.HumanUser;
import org.example.cricketGame.Utils.PredefinedCardGenerator;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class GameSimulation implements GameProgress{

    private Game game;
////    @Override
/// /    public void startGame()

    public void initGame(int numberOfUsers, int numberOfCardsPerPlayer, List<Pair<String, GameModeEnum>> userDetails) {

        System.out.println("Game started with " + numberOfUsers + " players.");

        List<Card> cards = getCardsData();

        List<BaseUser> users = getUsersData(numberOfUsers, userDetails);

        game = new Game(cards, users);

    }

    private List<BaseUser> getUsersData(int numberOfUsers, List<Pair<String, GameModeEnum>> userDetails) {
        int noOfBotUsers = Constants.MAX_USERS - numberOfUsers;
        // ADD Bots data in userDetails

        List<BaseUser> users = new ArrayList<>();
        userDetails.forEach(userDetail -> {
            System.out.println(userDetail.getValue0() + " " + userDetail.getValue1());
            users.add(new HumanUser(userDetail.getValue0(), userDetail.getValue1().getGameMode()));
        });


        List<Pair<String, GameModeEnum>> botDetails = getBotUserData(noOfBotUsers);
        botDetails.forEach(botDetail -> {
            System.out.println(botDetail.getValue0() + " " + botDetail.getValue1());
            users.add(new BotUser(botDetail.getValue0(), botDetail.getValue1().getGameMode()));
        });

        return users;
    }

    private List<Pair<String, GameModeEnum>> getBotUserData(int noOfBotUsers) {
        List<Pair<String, GameModeEnum>> botDetails = new ArrayList<>();
        return botDetails;
    }

    private List<Card> getCardsData() {
        System.out.println("Game started!");
        List<Card> cards = PredefinedCardGenerator.generateCards(Constants.MAX_PLAYER_CARDS);
        // list of cards in deck
        for(Card card : cards) {
            System.out.println(card.toString());
        }
        return cards;
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



//    @Override
//    public void loadGameProgress() {
//
//    }
}

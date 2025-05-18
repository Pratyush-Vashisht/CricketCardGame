package org.example.cricketGame.Game;

import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Users.BotUser;
import org.example.cricketGame.Users.HumanUser;
import org.example.cricketGame.Utils.Cards.PredefinedCardGenerator;
import org.example.cricketGame.Utils.Constants;
import org.example.cricketGame.enums.Attribute;
import org.example.cricketGame.enums.GameModeEnum;
import org.example.cricketGame.model.Card;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class GameSimulation implements GameProgress {

    private Game game;
////    @Override
/// /    public void startGame()

    public void initGame(int numberOfUsers, int numberOfCardsPerPlayer, List<Pair<String, GameModeEnum>> userDetails) {

        // take CARD_DECK_SIZE from main list and set in Game


        List<BaseUser> users = getUsersData(numberOfUsers, userDetails);

        List<Card> cards = getCardsData(users.size() * numberOfCardsPerPlayer);

        // distribute these deck ard among the players
        distributeCardsInUsers(cards, users);

        users.forEach(user -> printLog(user.toString()));

        game = new Game(cards, users);
    }

    private void distributeCardsInUsers(List<Card> cards, List<BaseUser> users) {
        int userCount = users.size();
        for (int i = 0; i < cards.size(); i++) {
            BaseUser user = users.get(i % userCount); // round-robin
            user.getCards().put(cards.get(i).getName(), cards.get(i));             // assign card to user
        }
    }

    private List<BaseUser> getUsersData(int numberOfUsers, List<Pair<String, GameModeEnum>> userDetails) {
        printLog("Preparing User Data......");
        int noOfBotUsers = Constants.MAX_USERS - numberOfUsers;
        // ADD Bots data in userDetails

        List<BaseUser> users = new ArrayList<>();
        userDetails.forEach(userDetail -> {
            users.add(new HumanUser(userDetail.getValue0(), userDetail.getValue1().getGameMode()));
        });


        List<Pair<String, GameModeEnum>> botDetails = getBotUserData(noOfBotUsers);
        botDetails.forEach(botDetail -> {
            printLog(botDetail.getValue0() + " " + botDetail.getValue1());
            users.add(new BotUser(botDetail.getValue0(), botDetail.getValue1().getGameMode()));
        });

        return users;
    }

    private List<Pair<String, GameModeEnum>> getBotUserData(int noOfBotUsers) {
        List<Pair<String, GameModeEnum>> botDetails = new ArrayList<>();
        for (int i = 0; i < noOfBotUsers; i++) {
            String botName = "Bot" + (i + 1);
            GameModeEnum gameMode = BotUser.getRandomGameMode();
            botDetails.add(Pair.with(botName, gameMode));
            System.out.println("Bot Name: " + botName + ", Game Mode: " + gameMode);
        }
        return botDetails;
    }

    private List<Card> getCardsData(int cardDeckSize) {
        printLog("Preparing Deck of cards of size - " + cardDeckSize);
        List<Card> cards = PredefinedCardGenerator.generateCards(cardDeckSize);
        int index = 0;
        for (Card card : cards) {
            printLog(++index + ": " + card.toString());
        }

        printLog("Above are the prepared cards");
        return cards;
    }

    public void startGame() {
        printLog("Game started with " + game.getUsers().size() + " players.");

        Queue<UUID> userQueue = new LinkedList<>(game.getUsers().stream().map(BaseUser::getId).toList());

        Map<UUID, BaseUser> userMap = game.getUsers().stream()
                .collect(Collectors.toMap(BaseUser::getId, user -> user));

        Scanner scanner = new Scanner(System.in);

        int roundNumber = 1;
        int userTurn = 0;
        while (roundNumber < 5) {
            printLog("Round - " + roundNumber + " begin");


            Round round = prepareRound(userMap, scanner, userQueue);
            game.getRounds().add(round);

//            checkRound(round, userQueue)
            // winner,
            // health update and save in round
            // clean up the queue for lost users

            // print round


            printLog("Round - " + roundNumber + " is over \n\n");
            if (userQueue.size() == 1) {
                declareWinner(userMap.get(userQueue.poll()));
                break;
            }
            roundNumber++;
        }
    }

    private Round prepareRound(Map<UUID, BaseUser> userMap, Scanner scanner, Queue<UUID> userQueue) {
        UUID currentUserId = userQueue.poll(); // remove top user
        BaseUser currentUser = userMap.get(currentUserId);
        printLog("Hey - " + currentUser.getName() + ", It's your turn.");
        printLog("Please input your player name");
        String playerName = scanner.nextLine();
        printLog("Please input attribute type - " + Arrays.toString(Attribute.values()));
        String attributeType = scanner.nextLine();

        GameModeEnum currentUserGameMode = GameModeEnum.STANDARD;
        if (currentUser.getGameMode().getGameModeEnum() != GameModeEnum.STANDARD && currentUser.getGameMode().isGameModeActive()) {
            printLog("You have a special Game Mode Left - " + currentUser.getGameMode().getGameModeEnum());
            printLog("Would you like to use - y/n ");
            String useGameMode = scanner.nextLine();

            if (useGameMode != null && useGameMode.equalsIgnoreCase("y")) {
                currentUser.getGameMode().deactivateGameMode();
                currentUserGameMode = currentUser.getGameMode().getGameModeEnum();
            }
        }

        Round round = new Round(currentUserId,
                Attribute.getAttributeByName(attributeType),
                currentUserGameMode,
                currentUser.getCardByPlayerName(playerName)); // TODO update active user list by queue
        round.getHealthBefore().put(currentUser.getId(), currentUser.getHealth());

        // Other player input processing
        int otherPlayerCount = userQueue.size();
        for (int i = 0; i < otherPlayerCount; i++) {
            UUID opponentUserId = userQueue.poll();
            BaseUser opponentUser = userMap.get(opponentUserId);
            printLog("Hey - " + opponentUser.getName());
            printLog("Please input your player name");
            String opponentPlayerName = scanner.nextLine();

            round.getHealthBefore().put(opponentUser.getId(), opponentUser.getHealth());
            round.getAllUserCardMap().put(opponentUserId, opponentUser.getCardByPlayerName(opponentPlayerName));
            round.getActiveUsersIdSet().add(opponentUserId);

            userQueue.add(opponentUserId);
        }

        userQueue.add(currentUserId); // put the current user at the end of queue

        return round;
    }

    private void declareWinner(BaseUser user) {
        printLog(user.toString() + "is a winner");
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

    private void printLog(String msg) {
        System.out.println(msg);
    }
}

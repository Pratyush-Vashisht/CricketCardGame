package org.example.cricketGame.Game;

import org.example.cricketGame.Users.BaseUser;
import org.example.cricketGame.Users.BotUser;
import org.example.cricketGame.Users.HumanUser;
import org.example.cricketGame.Utils.Cards.PredefinedCardGenerator;
import org.example.cricketGame.Utils.Checker;
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

        game = new Game(cards, users, numberOfCardsPerPlayer);
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
        int maxRounds = game.getNumberOfRounds();
        int userTurn = 0;
        while (roundNumber <= maxRounds) {
            printLog("Round - " + roundNumber + " begin");


            Round round = prepareRound(userMap, scanner, userQueue, roundNumber, maxRounds);
            game.getRounds().add(round);

//            checkRound(round, userQueue)
            // winner,
            // health update and save in round
            // clean up the queue for lost users

            // print round

            Checker.checkRound(round, userMap, userQueue, roundNumber);

            printLog("Round - " + roundNumber + " is over \n\n");
            if (roundNumber == maxRounds) {
                declareWinner(userQueue,userMap);
                break;
            }
            roundNumber++;
        }
    }

    private Round prepareRound(Map<UUID, BaseUser> userMap, Scanner scanner, Queue<UUID> userQueue,int roundNumber, int MaxRounds) {
        UUID currentUserId = userQueue.poll(); // remove top user
        BaseUser currentUser = userMap.get(currentUserId);
        printLog("Hey - " + currentUser.getName() + ", It's your turn.");
        printLog("Your current Cards - " + currentUser.getCards().values().stream().filter(card-> card.isActive()).collect(Collectors.toList()));
        Card selectedCard = null;
        while (true) {
            System.out.print("Enter the name of the player/card: ");
            String playerName = scanner.nextLine();
            selectedCard = currentUser.getCardByPlayerName(playerName);
            if (selectedCard == null) {
                System.out.println("Invalid card name. Please try again.");
            } else if (!selectedCard.isActive()) {
                System.out.println("This card has already been used or is inactive. Please choose another card.");
            } else {
                break; // valid and active card found
            }
        }
        printLog("Please input attribute type - " + Arrays.toString(Attribute.values()));
        String attributeType1 = scanner.nextLine();
        String attributeType2 = null;
        GameModeEnum currentUserGameMode = GameModeEnum.STANDARD;
        if (currentUser.getGameMode().getGameModeEnum() != GameModeEnum.STANDARD && currentUser.getGameMode().isGameModeActive()) {
            printLog("You have a special Game Mode Left - " + currentUser.getGameMode().getGameModeEnum());
            printLog("Would you like to use - y/n ");
            if(currentUser.getGameMode().getGameModeEnum() == GameModeEnum.SUPER_MODE && roundNumber != MaxRounds) {
                printLog(currentUser.getGameMode().getGameModeEnum() + " cannot activate till last card");
            }
            else if(currentUser.getGameMode().getGameModeEnum() == GameModeEnum.POWER_PLAY){
                printLog("Select Another Attribute - " + Arrays.toString(Attribute.values()));
                attributeType2 = scanner.nextLine();
            }
            else{

                String useGameMode = scanner.nextLine();

                if (useGameMode != null && useGameMode.equalsIgnoreCase("y")) {
                    currentUser.getGameMode().deactivateGameMode();
                    currentUserGameMode = currentUser.getGameMode().getGameModeEnum();
                }
            }

        }

        Round round = new Round(currentUserId,
                Attribute.getAttributeByName(attributeType1),
                currentUserGameMode,
                selectedCard); // TODO update active user list by queue
        round.getHealthBefore().put(currentUser.getId(), currentUser.getHealth());

        // Other player input processing
        int otherPlayerCount = userQueue.size();
        for (int i = 0; i < otherPlayerCount; i++) {
            UUID opponentUserId = userQueue.poll();
            BaseUser opponentUser = userMap.get(opponentUserId);
            printLog("Hey - " + opponentUser.getName());
            printLog("Please input your player name");
            printLog("Your current Cards - " + opponentUser.getCards().values().stream().filter(card-> card.isActive()).collect(Collectors.toList()));
            String opponentPlayerName = scanner.nextLine();

            round.getHealthBefore().put(opponentUser.getId(), opponentUser.getHealth());
            round.getAllUserCardMap().put(opponentUserId, opponentUser.getCardByPlayerName(opponentPlayerName));
            round.getActiveUsersIdSet().add(opponentUserId);

            userQueue.add(opponentUserId);
        }

        userQueue.add(currentUserId); // put the current user at the end of queue

        return round;
    }

    private void declareWinner(Queue<UUID> userQueue,Map<UUID, BaseUser> userMap) {
        double mx_health = 0;
        List<BaseUser> winners = new ArrayList<>();
        while(!userQueue.isEmpty()) {
            BaseUser user = userMap.get(userQueue.poll());
            if (user.getHealth() > 0 && user.getHealth() > mx_health) {
                mx_health = user.getHealth();
                winners.clear();
                winners.add(user);
            }
            else if(user.getHealth() > 0 && user.getHealth() == mx_health) {
                winners.add(user);
            }
        }
        if (winners.size() > 1) {
            printLog("Game is Draw, multiple players are winners");
            for (BaseUser user : winners) {
                printLog(user.getName() + " with health - " + user.getHealth());
            }
        } else if (winners.size() == 1) {
            BaseUser winner = winners.get(0);
            printLog("Winner is - " + winner.getName() + " with health - " + winner.getHealth());
        } else {
            printLog("No winner, all players are out of health.");
        }
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

package org.example;

import org.example.cricketGame.Game.GameSimulation;
import org.example.cricketGame.enums.GameModeEnum;
import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Please Enter no of users!");
        // TODO take input for no of users
        System.out.println("Please Enter User name and game mode!");
        System.out.println("Please enter Game Modes as");
        List<GameModeEnum> gameModes = List.of(GameModeEnum.values());
        for (int i = 0; i < gameModes.size(); i++) {
            System.out.println(i + " for game mode " + gameModes.get(i).name());
        }

        List<Pair<String, GameModeEnum>> userDetails = new ArrayList<>();
        // TODO take input for user name and game mode
        // create user


        // create loo

        GameSimulation gameSimulation = new GameSimulation();
        gameSimulation.initGame(4,10, userDetails);
        gameSimulation.startGame();
    }
}
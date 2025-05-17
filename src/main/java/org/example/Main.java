package org.example;

import org.example.cricketGame.Game.GameSimulation;

public class Main {
    public static void main(String[] args) {
        GameSimulation gameSimulation = new GameSimulation(10,10);
        gameSimulation.initGame();
        gameSimulation.startGame();
    }
}
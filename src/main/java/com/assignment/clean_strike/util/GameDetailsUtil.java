package com.assignment.clean_strike.util;

import com.assignment.clean_strike.model.Player;

import java.util.ArrayList;

public class GameDetailsUtil {
    private int numberOfPlayersSelectedByUser;
    private ArrayList<Player> players;
    private OutputUtil outputUtil;
    private InputUtil inputUtil;

    public GameDetailsUtil(InputUtil inputUtil, OutputUtil outputUtil) {
        this.inputUtil = inputUtil;
        this.outputUtil = outputUtil;
    }

    public void displayMainMenu() {
        outputUtil.display("Welcome to Clean Strike");
        outputUtil.display("Enter number of players (By default 2 players are selected). Enter\n 1. 'a' for 2 players\n 2. 'b' for 4 players");
        switch (inputUtil.read()) {
            case "a":
                numberOfPlayersSelectedByUser = 2;
                break;
            case "b":
                numberOfPlayersSelectedByUser = 4;
                break;
            default:
                outputUtil.display("Creating game using 2 players.");
                numberOfPlayersSelectedByUser = 2;
        }
    }

    public int getNumberOfPlayersSelectedByUser() {
        return numberOfPlayersSelectedByUser;
    }

    public ArrayList<Player> getPlayers() {
        players = new ArrayList<>();
        for (int playerNumber = 1; playerNumber <= numberOfPlayersSelectedByUser; ++playerNumber) {
            players.add(new Player("Player-" + playerNumber));

        }
        return players;
    }
}

package com.assignment.clean_strike.util;

import com.assignment.clean_strike.model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class GameDetailsUtil {
    private int numberOfPlayersSelectedByUser;
    private ArrayList<Player> players;

    public GameDetailsUtil() {
    }

    public void displayMainMenu() {
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.display("Welcome to Clean Strike");
        outputUtil.display("Enter number of players (By default 2 players are selected). Enter\n 1. 'a' for 2 players\n 2. 'b' for 4 players");

        InputUtil inputUtil = new InputUtil(new Scanner(System.in));
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

package com.assignment.clean_strike.others;

import com.assignment.clean_strike.util.GameDetailsUtil;
import com.assignment.clean_strike.util.InputUtil;
import com.assignment.clean_strike.util.OutputUtil;

import java.util.Scanner;

public class GameDemo {

    public static void main(String[] args) {
        GameDetailsUtil gameDetailsUtil = new GameDetailsUtil(new InputUtil(new Scanner(System.in)), new OutputUtil());
        gameDetailsUtil.displayMainMenu();

        CarromBoardGameEntity carromBoardGameEntity = new CarromBoardGameEntity(gameDetailsUtil.getNumberOfPlayersSelectedByUser(),
                gameDetailsUtil.getPlayers());

        CleanStrikeGameController cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity,
                new InputUtil(new Scanner(System.in)), new OutputUtil());

        cleanStrikeGameController.startGameMessage();

        InputUtil inputUtil = new InputUtil(new Scanner(System.in));
        OutputUtil outputUtil = new OutputUtil();
        while (true) {
            boolean isGameOver = cleanStrikeGameController.run();
            outputUtil.display("Press any key to continue...");
            inputUtil.read();

            if (isGameOver) {
                break;
            }
        }
    }
}
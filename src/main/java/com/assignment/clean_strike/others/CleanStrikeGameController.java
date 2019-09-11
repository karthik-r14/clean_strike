package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.TurnMenu;
import com.assignment.clean_strike.util.InputUtil;
import com.assignment.clean_strike.util.OutputUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class CleanStrikeGameController {
    private CarromBoardGameEntity carromBoardGameEntity;
    private TurnMenu turnMenu;
    private int turnState;

    //This constructor is used in the Game Demo.
    public CleanStrikeGameController(CarromBoardGameEntity carromBoardGameEntity) {
        this.carromBoardGameEntity = carromBoardGameEntity;
        this.turnState = 1;
        setupTurnMenu();
    }

    //This constructor is used in the unit tests.
    public CleanStrikeGameController(CarromBoardGameEntity carromBoardGameEntity, TurnMenu turnMenu, int turnState) {
        this.carromBoardGameEntity = carromBoardGameEntity;
        this.turnMenu = turnMenu;
        this.turnState = turnState;
    }

    private void setupTurnMenu() {
        ArrayList<String> turnMenuList = new ArrayList<>();

        turnMenuList.add("1. Strike");
        turnMenuList.add("2. Multi Strike (2 coins pocketed)");
        turnMenuList.add("3. Multi Strike (3 coins pocketed)");
        turnMenuList.add("4. Multi Strike (4 coins pocketed)");
        turnMenuList.add("5. Red Strike");
        turnMenuList.add("6. Striker Strike");
        turnMenuList.add("7. Defunct Coin");
        turnMenuList.add("8. None");

        turnMenu = new TurnMenu(turnMenuList);
    }

    public boolean run() {
        OutputUtil outputUtil = new OutputUtil();
        InputUtil inputUtil = new InputUtil(new Scanner(System.in));
        outputUtil.display(String.format("\n %s :  Choose an outcome from the list below", carromBoardGameEntity.getPlayers().get(turnState - 1).getName()));
        turnMenu.displayMenu();

        outputUtil.display("Enter your choice from (1 to " + turnMenu.getTurnMenuList().size() + ")");
        int option = 0;

        try {
            option = Integer.parseInt(inputUtil.read());
        } catch (NumberFormatException exception) {
            outputUtil.display("Please enter a Number between 1 and " + turnMenu.getTurnMenuList().size() + " in your choice not a String.Please try again.");
            return false;
        }

        switch (carromBoardGameEntity.getNumberOfPlayers()) {
            case 2:
                try {
                    switch (turnState) {
                        case 1:
                            carromBoardGameEntity.updateGameState(1, turnMenu.getTurnMenuList().get(option - 1));
                            turnState = 2;
                            break;
                        case 2:
                            carromBoardGameEntity.updateGameState(2, turnMenu.getTurnMenuList().get(option - 1));
                            turnState = 1;
                            break;
                    }
                } catch (IndexOutOfBoundsException exception) {
                    outputUtil.display("Please enter a choice between 1 and " + turnMenu.getTurnMenuList().size() + " only.Please try again.");
                    return false;
                }
                break;
        }

        carromBoardGameEntity.showGameStatistics();
        return carromBoardGameEntity.isGameOver();
    }

    public void startGameMessage() {
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.display("The game begins now!");
    }
}
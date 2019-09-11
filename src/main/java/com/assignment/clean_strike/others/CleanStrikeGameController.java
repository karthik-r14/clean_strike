package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.TurnMenu;
import com.assignment.clean_strike.util.InputUtil;
import com.assignment.clean_strike.util.OutputUtil;

import java.util.ArrayList;

public class CleanStrikeGameController {
    private CarromBoardGameEntity carromBoardGameEntity;
    private TurnMenu turnMenu;
    private int turnState;
    private InputUtil inputUtil;
    private OutputUtil outputUtil;

    //This constructor is used in the unit tests.
    public CleanStrikeGameController(CarromBoardGameEntity carromBoardGameEntity, TurnMenu turnMenu, int turnState, InputUtil inputUtil, OutputUtil outputUtil) {
        this.carromBoardGameEntity = carromBoardGameEntity;
        this.turnState = turnState;
        this.inputUtil = inputUtil;
        this.outputUtil = outputUtil;
        this.turnMenu = turnMenu;
    }

    //This constructor is used in GameDemo and in some of the unit tests.
    public CleanStrikeGameController(CarromBoardGameEntity carromBoardGameEntity, InputUtil inputUtil, OutputUtil outputUtil) {
        this.carromBoardGameEntity = carromBoardGameEntity;
        this.inputUtil = inputUtil;
        this.outputUtil = outputUtil;
        this.turnState = 1;
        setupTurnMenu();
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
        outputUtil.display(String.format("\n %s :  Choose an outcome from the list below", carromBoardGameEntity.getPlayers().get(turnState - 1).getName()));
        turnMenu.displayMenu();

        outputUtil.display("Enter your choice from (1 to 8)");
        int option = Integer.parseInt(inputUtil.read());

        switch (carromBoardGameEntity.getNumberOfPlayers()) {
            case 2:
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
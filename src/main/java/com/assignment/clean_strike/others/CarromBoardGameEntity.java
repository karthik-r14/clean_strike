package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.Coin;
import com.assignment.clean_strike.model.Player;
import com.assignment.clean_strike.util.OutputUtil;

import java.util.ArrayList;

public class CarromBoardGameEntity {
    public static final int NUMBER_OF_BLACK_COINS = 9;
    public static final int MINIMUM_DIFFERENCE_IN_POINTS = 3;
    public static final int THRESHOLD_POINTS_TO_WIN = 5;
    private int numberOfPlayers;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private ArrayList<Coin> blackCoins;
    private Coin redCoin;
    private Coin striker;

    //This constructor is used in the unit tests.
    public CarromBoardGameEntity(int numberOfPlayers, ArrayList<Player> players, ArrayList<Coin> blackCoins, Coin redCoin, Coin striker) {
        this.numberOfPlayers = numberOfPlayers;
        this.players = players;
        this.blackCoins = blackCoins;
        this.redCoin = redCoin;
        this.striker = striker;
    }

    //This constructor is used in the Game Demo.
    public CarromBoardGameEntity(int numberOfPlayers, ArrayList<Player> players) {
        setupBlackCoins();
        setupRedCoin();
        setupStriker();
        this.numberOfPlayers = numberOfPlayers;
        this.players = players;
    }

    private void setupStriker() {
        striker = new Coin(11, CoinType.STRIKER, true);
    }

    private void setupRedCoin() {
        redCoin = new Coin(10, CoinType.RED, true);
    }

    private void setupBlackCoins() {
        blackCoins = new ArrayList<>();
        for (int coinId = 1; coinId <= NUMBER_OF_BLACK_COINS; ++coinId) {
            blackCoins.add(new Coin(coinId, CoinType.BLACK, true));
        }
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void updateGameState(int playerId, String option) {
        OutputUtil outputUtil = new OutputUtil();
        int playerIndex = playerId - 1;
        currentPlayer = players.get(playerIndex);
        int points = currentPlayer.getPoints();

        if ((allBlackCoinsArePocketed()) && (option.equals("1. Strike") || option.equals("2. Multi Strike (2 coins pocketed)") || option.equals("3. Multi Strike (3 coins pocketed)") || option.equals("4. Multi Strike (4 coins pocketed)"))) {
            outputUtil.display("All black coins are pocketed.Hence, strike cannot happen.");
            return;
        }

        switch (option) {
            case "1. Strike":
                updatePlayerAndCoinStateInCaseOfBlackStrike(1, 1);
                break;

            case "2. Multi Strike (2 coins pocketed)":
                updatePlayerAndCoinStateInCaseOfBlackStrike(2, 2);
                break;

            case "3. Multi Strike (3 coins pocketed)":
                updatePlayerAndCoinStateInCaseOfBlackStrike(2, 2);
                break;

            case "4. Multi Strike (4 coins pocketed)":
                updatePlayerAndCoinStateInCaseOfBlackStrike(2, 2);
                break;

            case "5. Red Strike":
                if (redCoin.isOnBoard()) {
                    updatePlayerStateInCaseOfStrike(3);
                    removeRedCoinFromBoard();
                    outputUtil.display("Red coin is now removed from the board & will not be in play from now on.");

                } else {
                    outputUtil.display("Red coin is already pocketed.And is not in play");
                }
                break;

            case "6. Striker Strike":
                updatePlayerStateInCaseOfFoul(1);
                displayFoulMessage();

                break;

            case "7. Defunct Coin":
                updatePlayerStateInCaseOfFoul(2);
                removeBlackCoinsFromBoard(1);
                displayFoulMessage();
                break;

            case "8. None":
                currentPlayer.setTimeLineOfTurn(currentPlayer.getTimeLineOfTurn() + "N");

                if (currentPlayer.hasNotPocketedACoinForThreeStraightTurns()) {
                    currentPlayer.setPoints(points - 1);
                    currentPlayer.incrementFouls();
                    displayFoulMessage();
                }
                break;
        }
    }

    private void displayFoulMessage() {
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.display(currentPlayer.getName() + " - " + "You have committed a foul.");
        outputUtil.display("Your Foul Count = " + currentPlayer.getFoulCount());
    }

    private void updatePlayerAndCoinStateInCaseOfBlackStrike(int pointsToBeIncremented, int numberOfBlackCoinsToBeRemovedFromBoard) {
        updatePlayerStateInCaseOfStrike(pointsToBeIncremented);
        removeBlackCoinsFromBoard(numberOfBlackCoinsToBeRemovedFromBoard);

    }

    private void updatePlayerStateInCaseOfStrike(int pointsToBeIncremented) {
        currentPlayer.setPoints(currentPlayer.getPoints() + pointsToBeIncremented);
        currentPlayer.setTimeLineOfTurn(currentPlayer.getTimeLineOfTurn() + "P");
    }

    private void updatePlayerStateInCaseOfFoul(int pointsToBeDecremented) {
        int points = currentPlayer.getPoints();

        currentPlayer.incrementFouls();
        currentPlayer.setTimeLineOfTurn(currentPlayer.getTimeLineOfTurn() + "N");
        if (currentPlayer.getFoulCount() >= 3) {
            currentPlayer.setPoints(points - pointsToBeDecremented - 1);
        } else {
            currentPlayer.setPoints(points - pointsToBeDecremented);
        }

        if (currentPlayer.hasNotPocketedACoinForThreeStraightTurns()) {
            currentPlayer.setPoints(currentPlayer.getPoints() - 1);
        }
    }

    private void removeRedCoinFromBoard() {
        if (redCoin.isOnBoard()) {
            redCoin.setOnBoard(false);
        }
    }

    private void removeBlackCoinsFromBoard(int numberOfBlackCoinsTobeOffBoarded) {
        OutputUtil outputUtil = new OutputUtil();
        int count = 0;

        if (allBlackCoinsArePocketed()) {
            outputUtil.display("All black coins are pocketed.");
            return;
        }

        int blackCoinIndex = 0;
        while (count < numberOfBlackCoinsTobeOffBoarded) {
            if (blackCoins.get(blackCoinIndex).isOnBoard()) {
                blackCoins.get(blackCoinIndex).setOnBoard(false);
                count++;
            }
            blackCoinIndex++;
        }
    }

    private int getNumberOfBlackCoinsOnBoard() {
        int numberOfPocketedBlackCoinsOnBoard = 0;

        for (Coin blackCoin : blackCoins) {
            if (blackCoin.isOnBoard()) {
                numberOfPocketedBlackCoinsOnBoard++;
            }
        }

//        blackCoins.stream()
//                .filter(coin -> coin.isOnBoard())
//                .collect(Collectors.toList()).size();

        return numberOfPocketedBlackCoinsOnBoard;
    }

    private boolean allBlackCoinsArePocketed() {
        int numberOfPocketedBlackCoins = 0;

        for (Coin blackCoin : blackCoins) {
            if (!blackCoin.isOnBoard()) {
                numberOfPocketedBlackCoins++;
            }
        }

        if (numberOfPocketedBlackCoins == NUMBER_OF_BLACK_COINS) {
            return true;
        }

        return false;
    }

    private boolean isAllCoinsPocketed() {
        if (allBlackCoinsArePocketed() && !redCoin.isOnBoard()) {
            return true;
        }
        return false;
    }


    public void showGameStatistics() {
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.display("<-----------GAME STATS----------->");
        for (Player player : players) {
            outputUtil.display(player.getName() + " STATS");
            outputUtil.display(" Score : " + player.getPoints());
            outputUtil.display(" Fouls : " + player.getFoulCount());
        }

        outputUtil.display("Number of black coins still on board :" + getNumberOfBlackCoinsOnBoard());
        outputUtil.display("Number of black coins pocketed :" + (NUMBER_OF_BLACK_COINS - getNumberOfBlackCoinsOnBoard()));
        outputUtil.display("Is Red Coin on the board : " + redCoin.isOnBoard());
        outputUtil.display("<---------END--------->");
    }

    public boolean isGameOver() {
        OutputUtil outputUtil = new OutputUtil();
        Player player1 = players.get(0);
        Player player2 = players.get(1);
        int player1Points = player1.getPoints();
        int player2Points = player2.getPoints();
        int differenceInPoints;
        int higherPoints;

        if (player1Points > player2Points) {
            differenceInPoints = player1Points - player2Points;
            higherPoints = player1Points;
        } else {
            differenceInPoints = player2Points - player1Points;
            higherPoints = player2Points;
        }

        if (differenceInPoints >= MINIMUM_DIFFERENCE_IN_POINTS && player1.getPoints() >= THRESHOLD_POINTS_TO_WIN) {
            outputUtil.display(player1.getName() + " Wins!");
            return true;
        } else if (differenceInPoints >= MINIMUM_DIFFERENCE_IN_POINTS && player2.getPoints() >= THRESHOLD_POINTS_TO_WIN) {
            outputUtil.display(player2.getName() + " Wins!");
            return true;
        } else if (isAllCoinsPocketed() && (differenceInPoints <= MINIMUM_DIFFERENCE_IN_POINTS || higherPoints < THRESHOLD_POINTS_TO_WIN)) {
            outputUtil.display("Match Drawn! : All coins are exhausted.Also, there is no significant difference between the scores of the players.");
            return true;
        }

        outputUtil.display("The Game Continues!");
        return false;
    }
}
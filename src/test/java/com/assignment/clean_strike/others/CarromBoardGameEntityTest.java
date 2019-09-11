package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.Coin;
import com.assignment.clean_strike.model.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CarromBoardGameEntityTest {

    private CarromBoardGameEntity carromBoardGameEntity;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }


    @Test
    public void updateGameStateForAPlayerAndOptionStrike() {
    }

    @Test
    public void showGameStatisticsForAScenario() {
        int numberOfPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(10);
        Player player2 = new Player("Player 2");
        player2.setPoints(5);
        players.add(player1);
        players.add(player2);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.showGameStatistics();

        String expectedOutputString = "<-----------GAME STATS----------->\n" +
                "Player 1 STATS\n" +
                " Score : 10\n" + " Fouls : 0\n" +
                "Player 2 STATS\n" +
                " Score : 5\n" + " Fouls : 0\n" +
                "Number of black coins still on board :9\n" +
                "Number of black coins pocketed :0\n" +
                "Is Red Coin on the board : true\n" +
                "<---------END--------->\n";

        assertEquals(expectedOutputString, outputContent.toString());
    }

    @Test
    public void GameIsOverAndPlayer1Wins() {
        int numberOfPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(10);
        Player player2 = new Player("Player 2");
        player2.setPoints(5);
        players.add(player1);
        players.add(player2);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        boolean gameIsOver = carromBoardGameEntity.isGameOver();

        assertEquals("Player 1 Wins!\n", outputContent.toString());
        assertTrue(gameIsOver);
    }

    @Test
    public void gameIsOverAndPlayer2Wins() {
        int numberOfPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        Player player2 = new Player("Player 2");
        player2.setPoints(10);
        players.add(player1);
        players.add(player2);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        boolean gameIsOver = carromBoardGameEntity.isGameOver();

        assertEquals("Player 2 Wins!\n", outputContent.toString());
        assertTrue(gameIsOver);
    }

    @Test
    public void gameIsOverAndMatchIsDrawn() {
        int numberOfPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(6);
        Player player2 = new Player("Player 2");
        player2.setPoints(8);
        players.add(player1);
        players.add(player2);

        ArrayList<Coin> blackCoins;
        blackCoins = new ArrayList<>();
        for (int coinId = 1; coinId <= CarromBoardGameEntity.NUMBER_OF_BLACK_COINS; ++coinId) {
            blackCoins.add(new Coin(coinId, CoinType.BLACK, false));
        }

        Coin redCoin;
        redCoin = new Coin(10, CoinType.RED, false);
        Coin striker;
        striker = new Coin(11, CoinType.STRIKER, true);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players, blackCoins, redCoin, striker);

        boolean gameIsOver = carromBoardGameEntity.isGameOver();

        assertEquals("Match Drawn! : All coins are exhausted.Also, there is no significant difference between the scores of the players.\n", outputContent.toString());
        assertTrue(gameIsOver);
    }

    @Test
    public void gameIsNotOver() {
        int numberOfPlayers = 2;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        Player player2 = new Player("Player 2");
        player2.setPoints(6);
        players.add(player1);
        players.add(player2);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        boolean gameIsOver = carromBoardGameEntity.isGameOver();

        assertEquals("The Game Continues!\n", outputContent.toString());
        assertFalse(gameIsOver);
    }

    @Test
    public void updateGameStateShouldDisplayAMessageWhenAllCoinsArePocketedAndAStrikeOptionIsChosen() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        ArrayList<Coin> blackCoins;
        blackCoins = new ArrayList<>();
        for (int coinId = 1; coinId <= CarromBoardGameEntity.NUMBER_OF_BLACK_COINS; ++coinId) {
            blackCoins.add(new Coin(coinId, CoinType.BLACK, false));
        }

        Coin redCoin;
        redCoin = new Coin(10, CoinType.RED, false);
        Coin striker;
        striker = new Coin(11, CoinType.STRIKER, true);


        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players, blackCoins, redCoin, striker);

        carromBoardGameEntity.updateGameState(1, "1. Strike");

        assertEquals("All black coins are pocketed.Hence, strike cannot happen.\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldIncrementPlayerPointsByOneWhenAStrikeOptionIsChosenByPlayer() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "1. Strike");

        assertEquals(5, players.get(0).getPoints());
    }

    @Test
    public void updateGameStateShouldIncrementPlayerPointsByTwoWhenAMultiStrikeOfTwoCoinsIsChosenByPlayer() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "2. Multi Strike (2 coins pocketed)");

        assertEquals(6, players.get(0).getPoints());
    }

    @Test
    public void updateGameStateShouldIncrementPlayerPointsByTwoWhenAMultiStrikeOfThreeCoinsIsChosenByPlayer() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "3. Multi Strike (3 coins pocketed)");

        assertEquals(6, players.get(0).getPoints());
    }

    @Test
    public void updateGameStateShouldIncrementPlayerPointsByTwoWhenAMultiStrikeOfFourCoinsIsChosenByPlayer() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "4. Multi Strike (4 coins pocketed)");

        assertEquals(6, players.get(0).getPoints());
    }

    @Test
    public void updateGameStateShouldIncrementPlayerPointsByThreeWhenARedStrikeHappensAndRedCoinIsThereOnTheBoard() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        ArrayList<Coin> blackCoins;
        blackCoins = new ArrayList<>();
        for (int coinId = 1; coinId <= CarromBoardGameEntity.NUMBER_OF_BLACK_COINS; ++coinId) {
            blackCoins.add(new Coin(coinId, CoinType.BLACK, false));
        }

        Coin redCoin;
        redCoin = new Coin(10, CoinType.RED, true);
        Coin striker;
        striker = new Coin(11, CoinType.STRIKER, true);


        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players, blackCoins, redCoin, striker);

        carromBoardGameEntity.updateGameState(1, "5. Red Strike");

        assertEquals(7, players.get(0).getPoints());
        assertEquals("Red coin is now removed from the board & will not be in play from now on.\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDisplayMessageWhenARedStrikeHappensAndRedCoinIsNotThereOnTheBoard() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(4);
        players.add(player1);

        ArrayList<Coin> blackCoins;
        blackCoins = new ArrayList<>();
        for (int coinId = 1; coinId <= CarromBoardGameEntity.NUMBER_OF_BLACK_COINS; ++coinId) {
            blackCoins.add(new Coin(coinId, CoinType.BLACK, false));
        }

        Coin redCoin;
        redCoin = new Coin(10, CoinType.RED, false);
        Coin striker;
        striker = new Coin(11, CoinType.STRIKER, true);


        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players, blackCoins, redCoin, striker);

        carromBoardGameEntity.updateGameState(1, "5. Red Strike");

        assertEquals("Red coin is already pocketed.And is not in play\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByOneWhenThereIsAStrikerStrike() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "6. Striker Strike");

        assertEquals(4, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 1\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByTwoWhenThereIsAStrikeStrikeAndFoulCountIsGreaterThanOrEqualToThree() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.incrementFouls();
        player1.incrementFouls();
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "6. Striker Strike");

        assertEquals(3, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 3\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByTwoWhenThereIsAStrikeStrikeAndPlayerHasNotPocketedACoinForThreeStraightTurns() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.incrementFouls();
        player1.setTimeLineOfTurn("NN");
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "6. Striker Strike");

        assertEquals(3, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 2\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByThreeWhenThereIsAStrikeStrikeAndPlayerHasNotPocketedACoinForThreeStraightTurnsAndFoulCountIsGreaterThanOrEqualToThre() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.incrementFouls();
        player1.incrementFouls();
        player1.setTimeLineOfTurn("NN");
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "6. Striker Strike");

        assertEquals(2, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 3\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByTwoWhenPlayerSelectsDefunctCoinOption() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "7. Defunct Coin");

        assertEquals(3, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 1\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByThreeWhenPlayerSelectsDefunctCoinOptionAndFoulCountIsMoreThanOrEqualToThree() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.incrementFouls();
        player1.incrementFouls();
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "7. Defunct Coin");

        assertEquals(2, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 3\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByThreeWhenPlayerSelectsDefunctCoinOptionAndPlayerHasNotPocketedACoinForThreeStraightTurns() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.setTimeLineOfTurn("NN");
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "7. Defunct Coin");

        assertEquals(2, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 1\n", outputContent.toString());
    }

    @Test
    public void updateGameStateShouldDecrementPlayerPointsByOneWhenPlayerSelectsNoneAsOptionAndPlayerHasNotPocketedACoinForThreeConsecutiveTurns() {
        int numberOfPlayers = 1;
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player("Player 1");
        player1.setPoints(5);
        player1.setTimeLineOfTurn("NN");
        players.add(player1);

        carromBoardGameEntity = new CarromBoardGameEntity(numberOfPlayers, players);

        carromBoardGameEntity.updateGameState(1, "8. None");

        assertEquals(4, players.get(0).getPoints());
        assertEquals("Player 1 - You have committed a foul.\nYour Foul Count = 1\n", outputContent.toString());
    }
}
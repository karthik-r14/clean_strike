package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.Player;
import com.assignment.clean_strike.model.TurnMenu;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.*;

public class CleanStrikeGameControllerTest {
    private CarromBoardGameEntity carromBoardGameEntity;
    private CleanStrikeGameController cleanStrikeGameController;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        carromBoardGameEntity = mock(CarromBoardGameEntity.class);
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void shouldShowStartGameMessage() {
        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity);
        cleanStrikeGameController.startGameMessage();
        assertEquals("The game begins now!\n", outputContent.toString());
    }

    @Test
    public void shouldRunGameAndPlayer1ChoosesOptionOneThatIsStrikeFromMenu() {
        int numberOfPlayers = 2;
        int turnState = 1;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        ArrayList<String> turnMenuList = new ArrayList<>();
        turnMenuList.add("1. Strike");
        TurnMenu turnMenu = new TurnMenu(turnMenuList);

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState);
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        when(carromBoardGameEntity.getNumberOfPlayers()).thenReturn(numberOfPlayers);
        when(carromBoardGameEntity.getPlayers()).thenReturn(players);

        cleanStrikeGameController.run();

        verify(carromBoardGameEntity).updateGameState(1, "1. Strike");
        verify(carromBoardGameEntity).showGameStatistics();
        verify(carromBoardGameEntity).isGameOver();
    }

    @Test
    public void shouldRunGameAndPlayer2ChoosesOptionOneThatIsStrikeFromMenu() {
        int numberOfPlayers = 2;
        int turnState = 2;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        ArrayList<String> turnMenuList = new ArrayList<>();
        turnMenuList.add("1. Strike");
        TurnMenu turnMenu = new TurnMenu(turnMenuList);

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState);
        when(carromBoardGameEntity.getNumberOfPlayers()).thenReturn(numberOfPlayers);
        when(carromBoardGameEntity.getPlayers()).thenReturn(players);
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        cleanStrikeGameController.run();

        verify(carromBoardGameEntity).updateGameState(2, "1. Strike");
        verify(carromBoardGameEntity).showGameStatistics();
        verify(carromBoardGameEntity).isGameOver();
    }

    @Test
    public void runGameShouldReturnFalseAndPrintAMessageWhenAStringIsEnteredAsChoiceForATurn() {
        int turnState = 1;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        ArrayList<String> turnMenuList = new ArrayList<>();
        turnMenuList.add("1. Strike");
        TurnMenu turnMenu = new TurnMenu(turnMenuList);

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState);
        when(carromBoardGameEntity.getPlayers()).thenReturn(players);
        String input = "abc";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        boolean isGameOver = cleanStrikeGameController.run();

        assertFalse(isGameOver);
        assertEquals("\n Player 1 :  Choose an outcome from the list below\n" +
                "<--Turn Menu-->\n" +
                "1. Strike\n" +
                "Enter your choice from (1 to 1)\n" +
                "Please enter a Number between 1 and 1 in your choice not a String.Please try again.\n", outputContent.toString());
    }

    @Test
    public void runGameShouldReturnFalseAndPrintMessageWhenANumberEnteredAsTheChoiceIsNotPartOfTheChoiceList() {
        int numberOfPlayers = 2;
        int turnState = 1;

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Player 1"));
        players.add(new Player("Player 2"));

        ArrayList<String> turnMenuList = new ArrayList<>();
        turnMenuList.add("1. Strike");
        TurnMenu turnMenu = new TurnMenu(turnMenuList);

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState);
        when(carromBoardGameEntity.getNumberOfPlayers()).thenReturn(numberOfPlayers);
        when(carromBoardGameEntity.getPlayers()).thenReturn(players);
        String input = "100";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        boolean isGameOver = cleanStrikeGameController.run();

        assertFalse(isGameOver);
        assertEquals("\n Player 1 :  Choose an outcome from the list below\n" +
                "<--Turn Menu-->\n" +
                "1. Strike\n" +
                "Enter your choice from (1 to 1)\n" +
                "Please enter a choice between 1 and 1 only.Please try again.\n", outputContent.toString());
    }
}
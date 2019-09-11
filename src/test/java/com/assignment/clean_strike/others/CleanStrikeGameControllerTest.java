package com.assignment.clean_strike.others;

import com.assignment.clean_strike.model.Player;
import com.assignment.clean_strike.model.TurnMenu;
import com.assignment.clean_strike.util.InputUtil;
import com.assignment.clean_strike.util.OutputUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CleanStrikeGameControllerTest {
    private CarromBoardGameEntity carromBoardGameEntity;
    private CleanStrikeGameController cleanStrikeGameController;
    private InputUtil inputUtil;
    private OutputUtil outputUtil;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() throws Exception {
        inputUtil = mock(InputUtil.class);
        carromBoardGameEntity = mock(CarromBoardGameEntity.class);
        outputUtil = new OutputUtil();
        System.setOut(new PrintStream(outputContent));
    }

    @Test
    public void shouldShowStartGameMessage() {
        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, inputUtil, outputUtil);
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

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState, inputUtil, outputUtil);

        when(inputUtil.read()).thenReturn("1");

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

        cleanStrikeGameController = new CleanStrikeGameController(carromBoardGameEntity, turnMenu, turnState, inputUtil, outputUtil);
        when(inputUtil.read()).thenReturn("1");
        when(carromBoardGameEntity.getNumberOfPlayers()).thenReturn(numberOfPlayers);
        when(carromBoardGameEntity.getPlayers()).thenReturn(players);

        cleanStrikeGameController.run();

        verify(carromBoardGameEntity).updateGameState(2, "1. Strike");
        verify(carromBoardGameEntity).showGameStatistics();
        verify(carromBoardGameEntity).isGameOver();
    }
}
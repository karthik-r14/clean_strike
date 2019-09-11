package com.assignment.clean_strike.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameDetailsUtilTest {
    private InputUtil inputUtil;
    private OutputUtil outputUtil;

    private GameDetailsUtil gameDetailsUtil;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        inputUtil = mock(InputUtil.class);
        outputUtil = new OutputUtil();
        gameDetailsUtil = new GameDetailsUtil(inputUtil, outputUtil);
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayMainMenuAndUserInputsOptionAForSelectingTwoPlayers() {
        when(inputUtil.read()).thenReturn("a");

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n", outputContent.toString());
        assertEquals(2, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }

    @Test
    public void shouldDisplayMainMenuAndUserInputsOptionBForSelectingFourPlayers() {
        when(inputUtil.read()).thenReturn("b");

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n", outputContent.toString());
        assertEquals(4, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }

    @Test
    public void shouldDisplayMenuAndUserInputsAnythingThenTwoPlayersShouldBeSelected() {
        when(inputUtil.read()).thenReturn("c");

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n" +
                "Creating game using 2 players.\n", outputContent.toString());
        assertEquals(2, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }
}
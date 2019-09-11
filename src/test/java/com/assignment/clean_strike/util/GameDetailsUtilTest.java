package com.assignment.clean_strike.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameDetailsUtilTest {
    private GameDetailsUtil gameDetailsUtil;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        gameDetailsUtil = new GameDetailsUtil();
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayMainMenuAndUserInputsOptionAForSelectingTwoPlayers() {
        String input = "a";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n", outputContent.toString());
        assertEquals(2, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }

    @Test
    public void shouldDisplayMainMenuAndUserInputsOptionBForSelectingFourPlayers() {
        String input = "b";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n", outputContent.toString());
        assertEquals(4, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }

    @Test
    public void shouldDisplayMenuAndUserInputsAnythingThenTwoPlayersShouldBeSelected() {
        String input = "c";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        gameDetailsUtil.displayMainMenu();

        assertEquals("Welcome to Clean Strike\nEnter number of players (By default 2 players are selected). Enter\n" +
                " 1. 'a' for 2 players\n" +
                " 2. 'b' for 4 players\n" +
                "Creating game using 2 players.\n", outputContent.toString());
        assertEquals(2, gameDetailsUtil.getNumberOfPlayersSelectedByUser());
    }
}
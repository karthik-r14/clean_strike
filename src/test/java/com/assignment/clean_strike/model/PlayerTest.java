package com.assignment.clean_strike.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void setUp() throws Exception {
        player = new Player("Player 1");
    }

    @Test
    public void hasNotPocketedACoinForThreeStraightTurnsShouldReturnTrueWhenNoCoinHasBeenPocketedForThreeTurns() {
        player.setTimeLineOfTurn("NNN");

        assertTrue(player.hasNotPocketedACoinForThreeStraightTurns());
    }

    @Test
    public void hasNotPocketedACoinForThreeStraightTurnsShouldReturnFalseWhenACoinHasBeenPocketedForThreeTurns() {
        player.setTimeLineOfTurn("PPNNNP");

        assertFalse(player.hasNotPocketedACoinForThreeStraightTurns());
    }

    @Test
    public void hasNotPocketedACoinForThreeStraightTurnsShouldReturnFalseWhenLessThanThreeCoinsHavBeenPocketed() {
        player.setTimeLineOfTurn("PP");

        assertFalse(player.hasNotPocketedACoinForThreeStraightTurns());
    }
}
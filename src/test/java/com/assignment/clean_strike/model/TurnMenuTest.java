package com.assignment.clean_strike.model;

import com.assignment.clean_strike.model.TurnMenu;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TurnMenuTest {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
    private TurnMenu turnMenu;

    @Before
    public void setUp() {
        ArrayList<String> menu = new ArrayList<>();
        menu.add("a) Option1");
        menu.add("b) Option2");

        turnMenu = new TurnMenu(menu);

        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayMenu() {
        turnMenu.displayMenu();
        assertEquals("<--Turn Menu-->\na) Option1\nb) Option2\n", outputContent.toString());
    }
}
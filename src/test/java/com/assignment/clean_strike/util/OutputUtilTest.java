package com.assignment.clean_strike.util;

import com.assignment.clean_strike.util.OutputUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class OutputUtilTest {

    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outputContent));
    }

    @After
    public void CleanUpStreams() {
        System.setOut(System.out);
    }

    @Test
    public void shouldDisplayMessage() {
        OutputUtil outputUtil = new OutputUtil();

        outputUtil.display("hello");

        assertEquals("hello\n", outputContent.toString());
    }
}
package com.assignment.clean_strike.util;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class InputUtilTest {
    @Test
    public void shouldReturnValueEnteredByTheUser() {
        final ByteArrayInputStream inputStream = new ByteArrayInputStream("hello".getBytes());

        System.setIn(inputStream);
        InputUtil senseInput = new InputUtil(new Scanner(System.in));

        assertEquals("hello", senseInput.read());
    }
}
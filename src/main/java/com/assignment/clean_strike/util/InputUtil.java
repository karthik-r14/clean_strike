package com.assignment.clean_strike.util;

import java.util.Scanner;

public class InputUtil {
    private Scanner scanner;

    public InputUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    public String read() {
        return scanner.nextLine();
    }
}
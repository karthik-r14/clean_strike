package com.assignment.clean_strike.model;

public class Player {
    private String name;
    private int points;
    private int foulCount;
    private String timeLineOfTurn = "";

    public void setTimeLineOfTurn(String timeLineOfTurn) {
        this.timeLineOfTurn = timeLineOfTurn;
    }

    public String getTimeLineOfTurn() {
        return timeLineOfTurn;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementFouls() {
        foulCount++;
    }

    public int getFoulCount() {
        return foulCount;
    }

    public boolean hasNotPocketedACoinForThreeStraightTurns() {
        int timeLineTurnLength = timeLineOfTurn.length();

        if (timeLineTurnLength >= 3) {
            //N denotes NOT POCKETED a coin and P denotes POCKETED a coin for a turn.
            if (timeLineOfTurn.substring(timeLineTurnLength - 3, timeLineTurnLength).equals("NNN")) {
                return true;
            }
        }

        return false;
    }
}

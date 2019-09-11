package com.assignment.clean_strike.model;

import com.assignment.clean_strike.others.CoinType;

public class Coin {
    private int coinId;
    private CoinType coinType;
    private boolean isOnBoard;

    public Coin(int coinId, CoinType coinType, boolean isOnBoard) {
        this.coinId = coinId;
        this.coinType = coinType;
        this.isOnBoard = isOnBoard;
    }

    public boolean isOnBoard() {
        return isOnBoard;
    }

    public void setOnBoard(boolean onBoard) {
        isOnBoard = onBoard;
    }
}
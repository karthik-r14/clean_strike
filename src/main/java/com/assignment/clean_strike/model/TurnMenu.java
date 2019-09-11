package com.assignment.clean_strike.model;

import com.assignment.clean_strike.util.OutputUtil;

import java.util.ArrayList;

public class TurnMenu {
    private ArrayList<String> turnMenuList;

    public TurnMenu(ArrayList<String> turnMenuList) {
        this.turnMenuList = turnMenuList;
    }

    public void displayMenu() {
        OutputUtil outputUtil = new OutputUtil();
        outputUtil.display("<--Turn Menu-->");

        for (String option : turnMenuList) {
            outputUtil.display(option);
        }
    }

    public ArrayList<String> getTurnMenuList() {
        return turnMenuList;
    }
}
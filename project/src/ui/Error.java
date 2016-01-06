package ui;

import java.util.ArrayList;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import util.StringTools;

public class Error {

    private ArrayList<String> rows;

    public Error(String text) {
        this.rows = StringTools.multiRow(text, 16);
    }

    public void display() {
        LCD.clear();
        LCD.drawString("ERROR!", 5, 1);

        int y = 3;
        for(String row : this.rows) {
            row = row.trim();
            LCD.drawString(row, (16 - row.length()) / 2, y);
            y++;
        }

        Button.waitForPress();
        LCD.clear();
    }

}

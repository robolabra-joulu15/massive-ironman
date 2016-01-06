package ui;

import lejos.nxt.LCD;

public class Pointer {

    private int xPos;
    private int yPos;
    private char style;

    public Pointer(int xPos, int yPos, char style) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.style = style;
    }

    public void setY(int y) {
        LCD.clear(this.xPos, this.yPos, 1);
        this.yPos = y;
        this.render();
    }

    public void setX(int x) {
        LCD.clear(this.xPos, this.yPos, 1);
        this.xPos = x;
        this.render();
    }

    public void render() {
        LCD.drawChar(this.style, this.xPos, this.yPos);
    }

}

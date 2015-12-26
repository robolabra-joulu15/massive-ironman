package ui;

import lejos.nxt.LCD;

public class Pointer {

	private int xPos;
	private int row;
	
	public Pointer(int xPos, int row) {
		this.xPos = xPos;
		this.row = row;
    }
	
	public void setRow(int row) {
		LCD.clear(this.xPos, this.row, 1);
		this.row = row;
		this.render();
	}
	
	public void render() {
		LCD.drawChar('>', this.xPos, this.row);
	}
	
}

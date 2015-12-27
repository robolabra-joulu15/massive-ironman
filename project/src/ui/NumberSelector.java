package ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import util.NumberArrayTools;

public class NumberSelector {

	private int[] numbers;
	private Pointer pointer;
	private int firstNumberX;
	private int currentSelection;
	
	public NumberSelector(int numbers, int value) {
		this.numbers = NumberArrayTools.numberToArray(numbers, value);
		this.firstNumberX = (16 - numbers) / 2;
		this.pointer = new Pointer(this.firstNumberX, 6, '^');
	}
	
	public int select() {
		this.render();
		this.pointer.render();
		
		while(true) {
			int key = Button.waitForPress();
			
			if (key == Button.ID_LEFT) {
				this.currentSelection--;
				if (this.currentSelection < 0) this.currentSelection = this.numbers.length-1;
			}else if (key == Button.ID_RIGHT) {
				this.currentSelection++;
				if (this.currentSelection >= this.numbers.length) this.currentSelection = 0;
			}else if (key == Button.ID_ENTER) {
				this.numbers[this.currentSelection]++;
				if (this.numbers[this.currentSelection] > 9) this.numbers[this.currentSelection] = 0;
			}else if (key == Button.ID_ESCAPE) {
				return NumberArrayTools.arrayToNumber(this.numbers);
			}
			
			this.renderNumbers();
			this.pointer.setX(this.firstNumberX + this.currentSelection);
		}
	}
	
	public void render() {
		LCD.drawString("Select value:", 2, 3);
		this.renderNumbers();
	}
	
	private void renderNumbers() {
		LCD.clear(this.firstNumberX, 5, this.numbers.length);
		for (int i = 0; i < this.numbers.length; i++) {
			LCD.drawInt(this.numbers[i], this.firstNumberX + i, 5);
		}
	}
	
}

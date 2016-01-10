package ui.components;

import ui.components.Pointer;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import util.NumberArrayTools;

/*

  This class is a UI component used to change integer and double values in
  configuration menus.

*/

public class NumberSelector {

    private int[] numbers;
    private Pointer pointer;
    private int firstNumberX;
    private int currentSelection;
    private int pointerLocation;
    private int decimals;

    //for integer values, you don't have to specify the decimal parameter as it defaults to 0
    public NumberSelector(int numbers, double value) {
        this(numbers, value, 0);
    }

    public NumberSelector(int numbers, double value, int decimals) {
        this.numbers = NumberArrayTools.numberToArray(numbers, value, decimals);
        this.firstNumberX = (16 - numbers) / 2;
        this.pointer = new Pointer(this.firstNumberX, 6, '^');
        this.decimals = decimals;
    }

    public double select() {
        this.render();
        this.pointer.render();

        while (true) {
            int key = Button.waitForPress();

            if (key == Button.ID_LEFT) {
                this.currentSelection--;
                this.pointerLocation--;

                if (this.currentSelection == this.numbers.length - this.decimals - 1) {
                    this.pointerLocation--;
                }

                if (this.currentSelection < 0) {
                    this.currentSelection = this.numbers.length - 1;
                    if (this.decimals > 0) {
                        this.pointerLocation = this.numbers.length;
                    } else {
                        this.pointerLocation = this.numbers.length - 1;
                    }
                }
            }else if (key == Button.ID_RIGHT) {
                this.currentSelection++;
                this.pointerLocation++;

                if (this.currentSelection == this.numbers.length - this.decimals) {
                    this.pointerLocation++;
                }

                if (this.currentSelection >= this.numbers.length) {
                    this.currentSelection = 0;
                    this.pointerLocation = 0;
                }
            }else if (key == Button.ID_ENTER) {
                this.numbers[this.currentSelection]++;
                if (this.numbers[this.currentSelection] > 9) {
                    this.numbers[this.currentSelection] = 0;
                }
            }else if (key == Button.ID_ESCAPE) {
                return NumberArrayTools.arrayToNumber(this.numbers, this.decimals);
            }

            this.renderNumbers();
            this.pointer.setX(this.firstNumberX + this.pointerLocation);
        }
    }

    public void render() {
        LCD.drawString("Select value:", 2, 3);
        this.renderNumbers();
    }

    private void renderNumbers() {
        LCD.clear(this.firstNumberX, 5, this.numbers.length);
        int add = 0;

        for (int i = 0; i < this.numbers.length; i++) {
            if (i == this.numbers.length - this.decimals) {
                LCD.drawChar(',', this.firstNumberX + i, 5);
                add = 1;
            }
            LCD.drawInt(this.numbers[i], this.firstNumberX + i + add, 5);
        }
    }

}

package logic;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class EscapeButtonListener {
    public void buttonPressed(Button b) {
        LCD.drawString("ENTER pressed", 0, 0);
      }

      public void buttonReleased(Button b) {
        LCD.clear();
      }
}

package robo;

import lejos.nxt.Button;
import lejos.nxt.ButtonListener;

public class EscapeKuuntelija implements ButtonListener {
	
	public EscapeKuuntelija() {
	}

	@Override
	public void buttonPressed(Button b) {
		System.exit(0);
		
	}

	@Override
	public void buttonReleased(Button b) {
	}

}

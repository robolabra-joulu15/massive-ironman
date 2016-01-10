package Robotti;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.utility.Delay;

public class Main {

	public static void main(String[] args) {

		Brick brick = BrickFinder.getDefault();
		Key escape = brick.getKey("Escape");
		Actions action = new Actions();
		Colour colour = new Colour();

		action.kaynnista();

		while (!escape.isDown()) {
			if (action.block()) {
				action.stop();
				action.sort(colour.Sample());
			}
			Delay.msDelay(50);
		}

	}

}

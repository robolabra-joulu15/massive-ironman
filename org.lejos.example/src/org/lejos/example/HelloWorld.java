package org.lejos.example;

import lejos.nxt.*;

/**
 * Example leJOS Project with an ant build file
 * 
 */
public class HelloWorld {
	private static String Drawing;

	public static void main(String[] args) {

		System.out.println("Press enter to start, esc is emerengy stop.");
		Printer nav = new Printer(6, Motor.A, Motor.B, Motor.C);
		Button.waitForPress(5000);
		while (true) {
			try {
				if (Button.ESCAPE.isPressed()) {
					throw new InterruptedException();

				} else if (Button.ENTER.isPressed()) {
					nav.PickUpAndPlantBlock(0, 0);
					nav.PickUpAndPlantBlock(1, 0);
					nav.PickUpAndPlantBlock(2, 0);
					/*Give instructions to build picture here.
					 * Couldn't  make filereader work in time.
					 * 
					 */
				}
			} catch (InterruptedException e) {
				Motor.A.stop();
				Motor.B.stop();
				Motor.C.stop();
				break;
			}
		}
	}
}

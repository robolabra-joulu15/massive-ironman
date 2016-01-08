package org.lejos.example;

import lejos.nxt.*;

/**
 * Example leJOS Project with an ant build file
 *
 */
public class HelloWorld {

	public static void main(String[] args) {
		Motor.A.setSpeed(80); //max 900?
		Motor.A.rotate(45);
		Motor.A.rotate(-45);
		Motor.A.rotate(45);
		Motor.A.rotate(-45);
		Motor.A.rotate(45);
		Motor.A.rotate(-44);
		Button.waitForPress();
//		Motor.A.rotate(360);
	}
}

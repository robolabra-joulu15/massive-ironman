package Main;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.nxt.TouchSensor;
import basicBehaviour.RoboRoutine;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// Because multiple frustrating and unnecessary debugging sessions
		// caused by crappy sensors, the following is to test that everything is
		// okay with them. The real program starts after pressing NXT.LEFT.
		TouchSensor button = new TouchSensor(SensorPort.S1);
		SoundSensor voice = new SoundSensor(SensorPort.S2);
		while (!Button.LEFT.isPressed()) {
			if (button.isPressed()) {
				System.out.println("BUTTON WORKS");
				System.out.println("VOICE VALUE: " + voice.readValue());
				Thread.sleep(500);
			}
			LCD.clear();
		}
		// SENSOR TESTING ENDS

		System.out
				.println("Robot is ready for the action\nkill System with LEFT-arrow\nConfiguration with RIGHT-arrow");
		Thread.sleep(3000);
		RoboRoutine robot = new RoboRoutine();
		robot.run();
	}
}

package basicBehaviour;

import java.util.ArrayList;

import triggers.ButtonTrigger;
import triggers.Trigger;
import triggers.VoiceTrigger;
import lejos.nxt.*;

/*
 * This class includes Lego-robot's basic functionality and working routine.
 * Lego-robot will consist of					2 servoMotors	(ports A and B)
 * 								1 buttonSensor	(port 1)
 * 								1 voiceSensor	(port 2)
 * A-servo will be used for firepower (rapidly spinning legostick).
 * B-servo will be used to release ammos (dogcandys). Ammos will be dropped to firing unit and are thrown randomly.
 * Button and Voice -sensors are used to start the routine.
 * 
 * Triggers are implemented at runtime or via constructor (or both).
 * This Class is also ready for taking in new kind of Triggers, which implements Trigger-class. 
 * All triggers are kept in this.triggers -Arraylist.
 * TriggerListener takes care of trigger-launches and necessary controlling afterwards (flag / pause).
 */

public class RoboRoutine extends Thread {
	private int motorBTachoStart;
	private ArrayList<Trigger> triggers;
	private TriggerListener triggerListener;
	private int counter;

	public RoboRoutine() {
		Motor.A.setSpeed(500);
		Motor.A.setAcceleration(10000);
		motorBTachoStart = Motor.B.getPosition();
		this.triggerListener = new TriggerListener(this);
		triggers = new ArrayList<Trigger>();
		this.counter = 0;
	}

	public RoboRoutine(ArrayList<Trigger> triggers) {
		Motor.A.setSpeed(500);
		Motor.A.setAcceleration(10000);
		motorBTachoStart = Motor.B.getPosition();
		this.triggerListener = new TriggerListener(this);
		this.triggers = triggers;
		this.counter = 0;
	}

	public void wholeRoutine() {
		LCD.clear();
		System.out.println("DOG FEEDING\nON GOING..");
		this.startFirePower();
		this.releaseAmmos(1);
		this.stopFirePower();
		this.increaseCounter();
	}

	public void startFirePower() {
		Motor.A.backward();
	}

	public void stopFirePower() {
		Motor.A.flt();
	}

	public void releaseAmmos(int howMany) {
		waiting(200);
		for (int i = 0; i < howMany; i++) {
			Motor.B.rotate(15);
			lockAmmos();
			waiting(1000);
		}
	}

	public void lockAmmos() {
		Motor.B.rotateTo(motorBTachoStart);
	}

	public void run() {
		Thread threadi = new Thread(triggerListener);
		threadi.start();
	}

	public boolean addTrigger() {
		// TriggerListener calls this method if user wants to activate trigger
		// at runtime.
		// Triggers are activated (added to this.triggers) by pressing
		// NXT-RIGHT and then LEFT/RIGHT-series. E.g. ButtonTrigger is added
		// by pressing NXT.RIGHT for conf.mode and then NXT.RIGHT again.
		// LEFT/RIGHT-series must be configured in this method for new
		// activating purposes.
		if (Button.ENTER.isPressed() || Button.ESCAPE.isPressed()) {
			System.out.println("Nothing added");
			return false;
		} else if (Button.RIGHT.isPressed()) {
			ButtonTrigger button = new ButtonTrigger();
			triggers.add(button);
			LCD.clear();
			System.out.println("ButtonTrigger added\nto triggers");
			waiting(1000);
			return true;
		} else if (Button.LEFT.isPressed()) {
			LCD.clear();
			VoiceTrigger voice = new VoiceTrigger();
			triggers.add(voice);
			System.out.println("VoiceTrigger added\nto triggers");
			waiting(1000);
			return true;
		} else {
			System.out.println("Nothing added");
			waiting(1000);
			return false;
		}
	}

	private void waiting(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	public int getCounter() {
		return counter;
	}

	public void increaseCounter() {
		this.counter++;
	}
}

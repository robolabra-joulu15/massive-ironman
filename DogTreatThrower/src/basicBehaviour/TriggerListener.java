package basicBehaviour;

import java.util.ArrayList;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import triggers.Trigger;

/* This class controls all user- and trigger-interaction:
 * 					1. Makes own thread for every single trigger (in RoboRoutine)
 * 					2. Starts threads
 * 	LOOPS HERE
 * 	|				3. Listening...
 * 	| EXIT IF..			3.1 Listens if NXT.LEFT isPressed
 * 	|					3.1.1. Kills the whole project
 * 	|				3.2 Listens if NXT.RIGHT isPressed
 * 	|					3.2.1. Cancel possible Trigger-flags and set Triggers in wait-mode
 * 	|					3.2.2. Calls RoboRoutine.addTrigger
 * 	|					3.2.3. After Robot's dogFeeding, initialize triggers again to default state (back to 1.)
 * 	|				3.3 Listens if any Trigger thread flags FIRE (SensorButton is pressed, SensorVoice over limit)
 * 	|					3.3.1. Pause Threads (makes them wait)
 * 	|					3.3.2. Calls Robot's routine
 * 	V					3.3.3. Clears Trigger Flags and Pauses --> Place triggers in default state
 * 	TO HERE					3.3.4. Listens again (back to 3.)
 * 
 */

public class TriggerListener implements Runnable {
	private RoboRoutine robo;
	private int basicWaitingTimeForMessages = 2000;
	private ArrayList<Thread> runningTriggerThreads;

	public TriggerListener(RoboRoutine robo) {
		this.robo = robo;
		runningTriggerThreads = new ArrayList<Thread>();
	}

	public void listenTriggers() {
		/*
		 * NXT-bricks right arrow button is used to recognize "addTrigger"-mode
		 * if it is pressed, robo-class starts to listen other button presses
		 * and if pressed series match new trigger's "serial-code" (sequence of
		 * LEFT/RIGHT-arrows), it is added to the trigger-list in
		 * RoboRoutine-class. RoboRoutine class return out printed message if
		 * new trigger is added or not.
		 */
		addAndStartThreads();

		for (;;) {
			LCD.clear();
			System.out.println("Dog has been\nfed " + robo.getCounter()
					+ " times");
			if (Button.LEFT.isPressed()) {
				// This if-statement kills the whole project
				LCD.clear();
				System.out.println("Killing the System..");
				waiting(500);
				System.exit(0);
			}
			if (Button.RIGHT.isPressed()) {
				/*
				 * This if-statement is listening if addTrigger-method should be
				 * accessed, if YES, then allTriggers are paused to give user
				 * peace to make configurations (add new triggers). After
				 * addTrigger-method is finished, all triggers are initialized
				 * again (to include also newly added triggers).
				 */
				cancelAllFlags();
				pauseAllTriggerListeners();
				LCD.clear();
				System.out.println("Accessing the \nconfiguration \nmode..");
				waiting(basicWaitingTimeForMessages);
				if (robo.addTrigger()) {
					LCD.clear();
					System.out.println("Trigger added \nsuccesfully");
					waiting(basicWaitingTimeForMessages);
					this.addAndStartThreads();
				} else {
					System.out.println("No trigger added");
					waiting(basicWaitingTimeForMessages);
				}
			}
			isAnyTriggerFlagged();
			waiting(500);
		}
	}

	private void isAnyTriggerFlagged() {
		/*
		 * This method is called after every Listener-loop, if any of the
		 * Trigger-Threads is having FLAG(true), then Listener starts robot's
		 * routine and controls triggers' states.
		 */
		for (Trigger t : robo.getTriggers()) {
			if (t.getFlag()) {
				pauseAllTriggerListeners();
				LCD.clear();
				System.out.println("Trigger: \n" + t.getName()
						+ "\nwas triggered, \nfeeding dog..");
				waiting(basicWaitingTimeForMessages);
				robo.wholeRoutine(); // calls RoboRoutine and performs dog feeding
				LCD.clear();
				cancelAllFlags();
				System.out.println("All triggres\nare in\nDefault state");
				waiting(basicWaitingTimeForMessages);
				activateAllTriggerListeners();
				break;
			}
		}
	}

	private void addAndStartThreads() {
		/*
		 * This method initialize all triggers added in RoboRoutine-class. All
		 * triggerListeners in RoboRoutine-class' ArrayList are added in
		 * this.runningTriggerThreads and started in different threads for
		 * multithreading purposes. Starting is done in different method
		 * which is called here this.startAllThreads().
		 */
		runningTriggerThreads.clear();
		LCD.clear();
		System.out.println("Starting all \nTriggers..");
		waiting(basicWaitingTimeForMessages);

		for (Trigger t : robo.getTriggers()) {
			Thread temp = new Thread(t, t.getName());
			runningTriggerThreads.add(temp);
		}

		int howManyStarted = startAllThreads();
		LCD.clear();
		System.out.println(howManyStarted + " Thread(s)\nstarted..");
		waiting(basicWaitingTimeForMessages * 2);
	}

	public int startAllThreads() {
		// Starts all threads in and return count
		int howManyStarted = 0;
		for (Thread t : runningTriggerThreads) {
			t.setName("Thread #: " + howManyStarted);
			t.start();
			howManyStarted++;
		}
		return howManyStarted;
	}

	private void pauseAllTriggerListeners() {
		for (Trigger t : robo.getTriggers()) {
			t.setPause(true);
		}
	}

	private void activateAllTriggerListeners() {
		for (Trigger t : robo.getTriggers()) {
			t.setPause(false);
		}
	}

	private void cancelAllFlags() {
		for (Trigger t : robo.getTriggers()) {
			t.setFlag(false);
		}
	}

	private void waiting(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		listenTriggers();
	}
}

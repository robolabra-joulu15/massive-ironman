package logic;

import lejos.nxt.Button;
import lejos.nxt.Sound;
import lejos.robotics.subsumption.Behavior;

public class ShutdownBehavior implements Behavior {
	
	@Override
    public void action() {
	    Sound.beep();
	    System.exit(0);
    }

	@Override
    public void suppress() {
    }

	@Override
    public boolean takeControl() {
	    return Button.ESCAPE.isPressed();
    }

}

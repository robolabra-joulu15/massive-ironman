package ohjelma;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Behavior;

public class Stop implements Behavior{

	@Override
	public void action() {
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

package ohjelma;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;


public class DriveForward implements Behavior{
	
	private boolean suppressed = false;
	
	
	@Override
	public void action() {
		suppressed = false;
		Motor.A.forward();
		Motor.B.forward();
		while(!suppressed) {
			Thread.yield();
		}
	}

	@Override
	public void suppress() {
		suppressed = true;
		
	}

	@Override
	public boolean takeControl() {
		return !ObstacleFound.needNewDirection();
	}
	
}

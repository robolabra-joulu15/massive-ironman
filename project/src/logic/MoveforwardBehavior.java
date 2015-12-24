package logic;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class MoveforwardBehavior implements Behavior {
	
	private boolean suppressed = false;
	private NXTRegulatedMotor left;
	private NXTRegulatedMotor right;
	
	public MoveforwardBehavior(NXTRegulatedMotor left, NXTRegulatedMotor right) {
	    this.left = left;
	    this.right = right;
    }
	
	@Override
    public void action() {
		suppressed = false;
		left.forward();
		right.forward();
		while(!suppressed) {
			Thread.yield();
		}
		left.stop();
		right.stop();
    }

	@Override
    public void suppress() {
		this.suppressed = true;
    }

	@Override
    public boolean takeControl() {
	    return true; //always take control if possible
    }
	
}

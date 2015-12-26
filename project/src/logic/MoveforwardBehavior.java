package logic;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class MoveforwardBehavior implements Behavior {
	
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private LightSensor light;
	
	public MoveforwardBehavior(DifferentialPilot pilot, LightSensor light) {
	    this.pilot = pilot;
	    this.light = light;
    }
	
	@Override
    public void action() {
		this.suppressed = false;
		this.pilot.forward();
		
		while(!suppressed) {
			Thread.yield();	
		}
		
		this.pilot.stop();
	}

	@Override
    public void suppress() {
		this.suppressed = true;
    }

	@Override
    public boolean takeControl() {
	    return this.light.readValue() <= 40; 
    }
	
}

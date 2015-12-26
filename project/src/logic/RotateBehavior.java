package logic;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class RotateBehavior implements Behavior {

	private DifferentialPilot pilot;
	private LightSensor light;
	private boolean suppressed = false;
	
	public RotateBehavior(DifferentialPilot pilot, LightSensor light) {
		this.pilot = pilot;
		this.light = light;
	}
	
	@Override
    public void action() {
	    this.suppressed = false;
		
		while(!suppressed) {
			Thread.yield();
		}
		
    }

	@Override
    public void suppress() {
	    this.suppressed = true;
    }

	@Override
    public boolean takeControl() {
	    return this.light.readValue() > 40;
    }

}

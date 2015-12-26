package logic;

import lejos.nxt.LightSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;

public class RotateBehavior implements Behavior {

	private DifferentialPilot pilot;
	private LightSensor light;
	private boolean suppress = false;
	
	public RotateBehavior(DifferentialPilot pilot, LightSensor light) {
		this.pilot = pilot;
		this.light = light;
	}
	
	@Override
    public void action() {
		
    }

	@Override
    public void suppress() {
		suppress = true;
    }

	@Override
    public boolean takeControl() {
	    return this.light.readValue() > 40;
    }

}

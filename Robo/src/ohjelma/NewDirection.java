package ohjelma;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class NewDirection implements Behavior{
	
	private boolean suppressed = false;
	private DifferentialPilot pilot;
	private UltrasonicSensor sonar;
	private int range1;
	private int range2;
	
	public NewDirection() {
		pilot = new DifferentialPilot(2.25f, 5.75f, Motor.A, Motor.B);
		sonar = new UltrasonicSensor(SensorPort.S2);
		range1 = 0;
		range2 = 0;
	}
	
	@Override
	public void action() {
		suppressed = false;
		execute();
		while ( ObstacleFound.needNewDirection() == true){
			Thread.yield();
		}
		
		
		

		
	}
	
	public void execute() {
		Motor.C.rotate(90);
		Delay.msDelay(400);
		sonar.ping();
		Delay.msDelay(400);
		range1 = sonar.getDistance();
		
		Motor.C.rotate(-180);
		Delay.msDelay(100);
		sonar.ping();
		Delay.msDelay(400);
		range2 = sonar.getDistance();
		Motor.C.rotate(90);
		
		decideDirection();
		ObstacleFound.noNeed();
	}
	
	public void decideDirection() {
		if(range1 > range2) {
			pilot.rotate(-90);
		} else if(range2 > range1) {
			pilot.rotate(90);
		} else {
			pilot.travel(-5);
			action();
		}
	}

	@Override
	public void suppress() {
				
	}

	@Override
	public boolean takeControl() {
		return ObstacleFound.needNewDirection();
	}

}

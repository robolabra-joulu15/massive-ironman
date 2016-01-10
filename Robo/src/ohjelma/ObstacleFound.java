package ohjelma;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;


public class ObstacleFound implements Behavior{

	private TouchSensor touch;
	private UltrasonicSensor sonar;
	private boolean suppressed;
	private DifferentialPilot pilot;
	private static boolean needDirection = false;
	
	public ObstacleFound() {
		touch = new TouchSensor(SensorPort.S1);
		sonar = new UltrasonicSensor(SensorPort.S2);
		pilot = new DifferentialPilot(2.25f, 5.75f, Motor.A, Motor.B);
		
	}
	
	public static void noNeed() {
		needDirection = false;
	}
	
	public static boolean needNewDirection() {
		return needDirection;
	}
	

	@Override
	public void action() {
		suppressed = false;
		pilot.travel(-5,true);
		
		while(pilot.isMoving() && !suppressed) {
			Thread.yield();
		}
		
		
		needDirection = true;
	}

	@Override
	public void suppress() {
		
		
	}

	@Override
	public boolean takeControl() {
		sonar.continuous();
		return touch.isPressed() || sonar.getDistance() < 30;
	}
	
}

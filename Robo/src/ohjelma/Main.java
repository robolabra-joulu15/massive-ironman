package ohjelma;
import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;


public class Main {
	public static void main(String[] args) {
		
		
		Behavior b1 = new DriveForward();
		Behavior b2 = new ObstacleFound();
		Behavior b3 = new NewDirection();
		Behavior b4 = new Stop();
		
		Behavior [] bArray = {b1, b2, b3, b4};
		Arbitrator arb = new Arbitrator(bArray);
		
		Button.waitForPress();
		arb.start();
		
		
		
	}
}

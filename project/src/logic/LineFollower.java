package logic;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
    
    public void start() {
    	
    	DifferentialPilot dp = new DifferentialPilot(5.6f, 12.3f, Motor.A, Motor.B);
    	Button.ENTER.waitForPress();
    	dp.rotate(360);
    	
    	BehaviorHandler behaviors = new BehaviorHandler();
    	
    	behaviors.addBehavior(new ShutdownBehavior(), 0);
    	//behaviors.addBehavior(new MoveforwardBehavior(Motor.A, Motor.B), 1);
    	
    	Arbitrator arbitrator = new Arbitrator(behaviors.getArray());
    	arbitrator.start();
    
    }
    
}

package logic;

import lejos.nxt.Motor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
    
    public void start() {
    	
    	BehaviorHandler behaviors = new BehaviorHandler();
    	
    	behaviors.addBehavior(new ShutdownBehavior(), 0);
    	behaviors.addBehavior(new MoveforwardBehavior(Motor.A, Motor.B), 4);
    	
    	Arbitrator arbitrator = new Arbitrator(behaviors.getArray());
    	arbitrator.start();
    
    }
    
}

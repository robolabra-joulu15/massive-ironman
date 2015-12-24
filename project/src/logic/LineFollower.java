package logic;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
    
    public void start() {
    	
    	BehaviorHandler behaviors = new BehaviorHandler();
    	
    	behaviors.addBehavior(new ShutdownBehavior(), 0);
    	
    	Arbitrator arbitrator = new Arbitrator(behaviors.getArray());
    	arbitrator.start();
    
    }
    
}

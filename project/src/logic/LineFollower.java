package logic;

import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
    
    public void start() {
    	
    	Behavior shutdown = new ShutdownBehavior();
    	
    	Behavior[] behaviors = {shutdown};
    	
    	Arbitrator arbitrator = new Arbitrator(behaviors);
    	arbitrator.start();
    
    }
    
}

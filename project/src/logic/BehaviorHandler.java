package logic;

import lejos.robotics.subsumption.Behavior;
import util.PriorityQueue;


public class BehaviorHandler {
	
	private PriorityQueue behaviors;
	
	public BehaviorHandler() {
		behaviors = new PriorityQueue(10);
		
	    behaviors.insert(new ShutdownBehavior(), 1);
	    behaviors.insert(new ShutdownBehavior(), 3);
	    behaviors.insert(new ShutdownBehavior(), 2);
	    behaviors.insert(new ShutdownBehavior(), 0);
	    behaviors.insert(new ShutdownBehavior(), 4);
    
	    while(!behaviors.isEmpty()) {
	    	Behavior deleted = behaviors.del_min();
	    }
	}
	
	//TODO: priorityqueue...
	
	

}

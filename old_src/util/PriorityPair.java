package util;

import lejos.robotics.subsumption.Behavior;

/*
 
 This used to be a generic class... Read notes in file PriorityQueue.java!
 
 */

public class PriorityPair {

	private int priority;
	private Behavior object;
	
	public PriorityPair(Behavior object, int priority) {
		this.object = object;
		this.priority = priority;
	}
	
	public int getPriority() {
	    return priority;
    }
	
	public Behavior getObject() {
	    return object;
    }
	
}

package logic;

import lejos.nxt.Button;
import lejos.robotics.subsumption.Behavior;
import util.PriorityPair;
import util.PriorityQueue;

/*
 
 This class uses the PriorityQueue class I made to automatically sort behaviors in a correct order and easily 
 converting them to an array. This allows flexible adding of behaviors.
 
 */

public class BehaviorHandler {
	
	private PriorityQueue behaviors;
	
	public BehaviorHandler() {
		behaviors = new PriorityQueue(255);
	}
	
	//Smaller priority value means higher priority
	public boolean addBehavior(Behavior behavior, int priority) {
		return behaviors.insert(behavior, priority);
	}
	
	public Behavior[] getArray() {
		int size = behaviors.size();
		Behavior[] ret = new Behavior[size];
		PriorityQueue clone = new PriorityQueue(this.behaviors);
		
		for (int i = ret.length-1; i >= 0; i--) {
			ret[i] = clone.del_min();
		}
		
		return ret;
	}

}

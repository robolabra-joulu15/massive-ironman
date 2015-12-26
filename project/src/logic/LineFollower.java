package logic;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class LineFollower {
    
    public void start() {
    	
    	DifferentialPilot pilot = new DifferentialPilot(5.6f, 12.0f, Motor.A, Motor.B);
    	pilot.setRotateSpeed(180);
    	
    	LightSensor light = new LightSensor(SensorPort.S1);
    	light.setFloodlight(true);
    	
    	BehaviorHandler behaviors = new BehaviorHandler();
    	
    	behaviors.addBehavior(new ShutdownBehavior(), 0);
    	behaviors.addBehavior(new MoveforwardBehavior(pilot, light), 2);
    	behaviors.addBehavior(new RotateBehavior(pilot, light), 1);
    	
    	Arbitrator arbitrator = new Arbitrator(behaviors.getArray());
    	
    	arbitrator.start();
    
    }
    
}

package org.lejos.example;



import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

//import lejos.robotics.;

public class HelloWorld {
	
	
	
	
	public static void main(String[] args) {
		
			
		Motor.A.setSpeed(85);
		Motor.B.setSpeed(50);
		Motor.C.setSpeed(50);
		
		Robot robot = new Robot(0, 0); //Enter start position if different from (0,0)
		robot.pilot = new DifferentialPilot(5.6, 16.5, Motor.B, Motor.C); //Enter tire diameter instead of 5.6 and track diameter in place of 16.5 if different
		MoveDecider decider = new MoveDecider(robot);
		while(!Button.ESCAPE.isPressed()){
			robot.takeReadings();
			decider.decide();
			robot.move();
			decider.getPossiblePositions().clear();
		}
		
		robot.map.printMap();
		Button.waitForPress();
		
		
		
		
		
		
		
		

		
		
		
		

	}

}

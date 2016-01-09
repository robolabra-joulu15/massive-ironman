package org.lejos.example;


import java.util.ArrayList;
import java.util.Random;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

import lejos.robotics.localization.*;
//import lejos.robotics.;

public class HelloWorld {
	
	
	
	
	public static void main(String[] args) {
		
			
		Motor.A.setSpeed(70);
		Motor.B.setSpeed(50);
		Motor.C.setSpeed(50);
		//HelloWorld traveler = new HelloWorld();;
		//traveler.map = new Map();
	
		
		//traveler.currentx = 0;
		//traveler.currenty = 0;

		//traveler.pilot = new DifferentialPilot(5.6, 16.5, Motor.B, Motor.C);
		//traveler.usensor = new UltrasonicSensor(SensorPort.S1);
		//traveler.secsensor = new UltrasonicSensor(SensorPort.S2);
		//traveler.takeReadings();
		//traveler.map.printMap();
		//Button.waitForPress();
		
		Robot robot = new Robot(0, 0);
		MoveDecider decider = new MoveDecider(robot);
		while(!Button.ESCAPE.isPressed()){
			robot.takeReadings();
			decider.decide();
			robot.move();
		}
		
		robot.map.printMap();
		Button.waitForPress();
		
		
		
		
		
		
		
		

		
		
		
		

	}

}

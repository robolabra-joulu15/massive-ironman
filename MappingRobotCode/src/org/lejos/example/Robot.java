package org.lejos.example;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	private UltrasonicSensor sensor1;
	private UltrasonicSensor sensor2;
	
	private Positions currentPosition; //where the robot is on the grid
	private Positions nextPosition; //where the MoveDecider has decided the robot should go next
	Map map;
	private int valueFromRight, valueFromLeft, valueFromFront, valueFromBack; 
	DifferentialPilot pilot;
	
	public Robot(int startx, int starty){
		this.currentPosition = new Positions(startx, starty);
		init();
	}
	
	//Initializes the sensors and the map
	public void init(){
		sensor1 = new UltrasonicSensor(SensorPort.S1);
		sensor2 = new UltrasonicSensor(SensorPort.S2);
		map = new Map();
		
	}
	
	
	//Ultrasonic sensor1 rotates and takes reads from the front, left and back. Sensor2 takes readings from right
public void takeReadings(){		
		map.setMapValue(currentPosition.getX(), currentPosition.getY(), map.getMapValue(currentPosition.getX(), currentPosition.getY())+1);		
			
				valueFromFront = sensor1.getDistance();
				Motor.A.rotate(-90);
				valueFromLeft = sensor1.getDistance();
				Motor.A.rotate(-90);
				valueFromBack = sensor1.getDistance();
				Motor.A.rotate(180);
				valueFromRight = sensor2.getDistance();
										
	}

	//Executes the robot's actual move sequence based on what the MoveDecider has set to the nextPosition variable
	//Then it updates the robot's currentPosition
public void move(){	
	if(this.nextPosition == MoveDecider.toforward){
		
		pilot.travel(40);		
			
	}
	
	if(this.nextPosition == MoveDecider.tobackwards){
		
		pilot.travel(-40);			
	}
	
	if(this.nextPosition == MoveDecider.toleft){
		pilot.steer(200, 90);
		pilot.travel(40);
		pilot.steer(200, -90);		
	}
	
	if(this.nextPosition == MoveDecider.toright){
		pilot.steer(200, -90);
		pilot.travel(40);
		pilot.steer(200, 90);
		
	}
	
	if(this.nextPosition == MoveDecider.donothing){
		
	}
	
	currentPosition.setNewCoordiniates(this.nextPosition.getX(), this.nextPosition.getY());
		
}


public Positions getCurrentPosition(){
	return this.currentPosition;
}

public int getValueFromFront(){
	return this.valueFromFront;
}

public int getValueFromBack(){
	return this.valueFromBack;
}

public int getValueFromLeft(){
	return this.valueFromLeft;
}

public int getValueFromRight(){
	return this.valueFromRight;
}

public void setNextPosition(Positions p){
	this.nextPosition = p;
}




}

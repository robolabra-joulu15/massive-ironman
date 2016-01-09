package org.lejos.example;

import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Robot {
	
	private UltrasonicSensor sensor1;
	private UltrasonicSensor sensor2;
	
	private Positions currentPosition;
	private Positions nextPosition;
	Map map;
	private int valueFromRight, valueFromLeft, valueFromFront;
	private DifferentialPilot pilot;
	
	public Robot(int startx, int starty){
		this.currentPosition = new Positions(startx, starty);
		init();
	}
	
	public void init(){
		sensor1 = new UltrasonicSensor(SensorPort.S1);
		sensor2 = new UltrasonicSensor(SensorPort.S2);
		map = new Map();
		pilot = new DifferentialPilot(5.6, 16.5, Motor.B, Motor.C);
	}
	
	
	
public void takeReadings(){		
		map.setMapValue(currentPosition.getX(), currentPosition.getY(), map.getMapValue(currentPosition.getX(), currentPosition.getY())+1);		
			
				valueFromRight = sensor2.getDistance();
				valueFromFront = sensor1.getDistance();
				Motor.A.rotate(-90);
				valueFromLeft = sensor1.getDistance();
				Motor.A.rotate(90);
										
	}


public void move(){
	
	if(this.nextPosition == MoveDecider.toforward){
		
		pilot.travel(40);		
		//map.setMapValue(currentx, currenty, map.getMapValue(currentx, currenty)+1);		
	}
	
	if(this.nextPosition == MoveDecider.tobackwards){
		
		pilot.travel(-40);
		
		//map.setMapValue(currentx, currenty, map.getMapValue(currentx, currenty)+1);		
	}
	
	if(this.nextPosition == MoveDecider.toleft){
		pilot.steer(200, 90);
		pilot.travel(40);
		pilot.steer(200, -90);
		//map.setMapValue(currentx, currenty, map.getMapValue(currentx, currenty)+1);
	}
	
	if(this.nextPosition == MoveDecider.toright){
		pilot.steer(200, -90);
		pilot.travel(40);
		pilot.steer(200, 90);
		//map.setMapValue(currentx, currenty, map.getMapValue(currentx, currenty)+1);
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

package org.lejos.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import lejos.nxt.Button;
import lejos.nxt.Sound;

//Decides the robot's next move
public class MoveDecider {
	
	private Random random;
	private Robot robot;
	private ArrayList<Positions> possiblePositions;
	static Positions toforward;
	static Positions toleft;
	static Positions toright;
	static Positions tobackwards;
	static Positions donothing;
	
	public MoveDecider(Robot robot){
		this.robot = robot;
		this.possiblePositions = new ArrayList<Positions>();
		this.random = new Random();
		init();
		
	}
	
	//initializes all the possible positions in relation to the given start position
	public void init(){
		toright = new Positions(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY());
		toforward = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1);
		toleft = new Positions(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY());
		tobackwards = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1);
		donothing = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY());
	}
	
	//decide method make sure that the robot doesn't add a move that is beyond the range 
	//of the grid (e.g (-1, 0)) to the list of possible moves. Then it calls methods that decide if each specific move is possible 
	//according to the ultrasonic sensor readings
	public void decide(){		
		if(!((robot.getCurrentPosition().getY() +1)> 50)){
			decideIfForward();
		}
	
		if(!((robot.getCurrentPosition().getX() -1)< 0)){
			decideIfLeft();
		}
				
		if(!((robot.getCurrentPosition().getX() +1)> 50)){
			decideIfRight();
		}
		
		
		if(!((robot.getCurrentPosition().getY() -1)< 0)){
			decideIfBackwards();
		}
		
		//makes sure that there is at least move to do nothing so that the Random generator doesn't call an exception
		if(possiblePositions.size()== 0){
			donothing.setNewCoordiniates(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY());
			possiblePositions.add(donothing);
		}
		
		checkIfAgain();
		
		pickRandomMove();
			
	}
	
	//checkIfAgain makes sure that the robot prefers to go to a grid position it has never been to over a position it has been to
	//numerous times. If the robot has been to a position more than twice already and it is not the only possible move
	//then it removes it from the list of possible moves.
	
	public void checkIfAgain(){
		Sound.beepSequence();
		for(Iterator<Positions> iterator = possiblePositions.iterator(); iterator.hasNext();){
			Positions p = iterator.next();
			if((robot.map.getMapValue(p.getX(), p.getY()) > 2) && possiblePositions.size()>1){
				System.out.println(p);
				
				iterator.remove();
				Sound.buzz();
			}
			Sound.beep();
		}
	}
	
	
	//Randomly picks a position for the robot to move to from the list of possibilities
	public void pickRandomMove(){
		
		int decision = random.nextInt((possiblePositions.size()-1) + 1);

		robot.setNextPosition(possiblePositions.get(decision));
		
	}
	
	
	//decides if it is possible for the robot to move backwards based on sensor readings. Then it increments by 1 if backwards is open and possible
	//but it decrements by 1 if it is impossible and blocked by a wall. Then it adds the position to the possiblePositions list if it is open
	public void decideIfBackwards(){

		if (robot.getValueFromBack() < 40){
			robot.map.setMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1, robot.map.getMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1)-1);
		}
		else{
			robot.map.setMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1, robot.map.getMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1)+1);
		}
		tobackwards.setNewCoordiniates(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1);
		
		if(robot.map.getMapValue(tobackwards.getX(), tobackwards.getY())>0){
			possiblePositions.add(tobackwards);				
		}
			
	}
	
	//decides if it is possible for the robot to move left based on sensor readings. Then it increments by 1 if left is open and possible
	//but it decrements by 1 if it is impossible and blocked by a wall. Then it adds the position to the possiblePositions list if it is open
	
	
	public void decideIfLeft(){

		if (robot.getValueFromLeft() < 40){
			
			
			robot.map.setMapValue(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY(), robot.map.getMapValue(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY())-1);
		}
		else{
			robot.map.setMapValue(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY(), robot.map.getMapValue(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY())+1);
		}
		
		toleft.setNewCoordiniates(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY());
		if(robot.map.getMapValue(toleft.getX(), toleft.getY())>0){
			possiblePositions.add(toleft);			
			
		}
	
		
	}
	
	//decides if it is possible for the robot to move forward based on sensor readings. Then it increments by 1 if forward is open and possible
	//but it decrements by 1 if it is impossible and blocked by a wall. Then it adds the position to the possiblePositions list if it is open
		
	public void decideIfForward(){

		if (robot.getValueFromFront() < 30){
			robot.map.setMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1, robot.map.getMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1)-1);
		}
		else{
			robot.map.setMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1, robot.map.getMapValue(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1)+1);
		}
		toforward.setNewCoordiniates(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1);
		
		if(robot.map.getMapValue(toforward.getX(), toforward.getY())>0){
			possiblePositions.add(toforward);				
		}
	
		
	}
	
	//decides if it is possible for the robot to move right based on sensor readings. Then it increments by 1 if right is open and possible
	//but it decrements by 1 if it is impossible and blocked by a wall. Then it adds the position to the possiblePositions list if it is open
	
	public void decideIfRight(){		
		if(robot.getValueFromRight()< 40){
			
			robot.map.setMapValue(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY(), robot.map.getMapValue(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY())-1);
		}
		else{
			robot.map.setMapValue(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY(), robot.map.getMapValue(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY())+1);
		}
		toright.setNewCoordiniates(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY());
		if(robot.map.getMapValue(toright.getX(), toright.getY())>0){
			possiblePositions.add(toright);			
		}
		
	}
	
	//Returns the possiblePositions list
	public ArrayList<Positions> getPossiblePositions(){
		return this.possiblePositions;
	}


}

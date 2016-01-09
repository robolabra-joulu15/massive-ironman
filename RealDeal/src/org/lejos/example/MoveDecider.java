package org.lejos.example;

import java.util.ArrayList;
import java.util.Random;

import lejos.nxt.Sound;

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
	
	public void init(){
		toright = new Positions(robot.getCurrentPosition().getX()+1, robot.getCurrentPosition().getY());
		toforward = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()+1);
		toleft = new Positions(robot.getCurrentPosition().getX()-1, robot.getCurrentPosition().getY());
		tobackwards = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1);
		donothing = new Positions(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY());
	}
	
	
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
		
		
		if(possiblePositions.size()< 1 ){
			decideIfBackwards();
		}
		
		pickRandomMove();
			
	}
	
	
	
	public void pickRandomMove(){
		
		int decision = random.nextInt((possiblePositions.size()-1) + 1);

		robot.setNextPosition(possiblePositions.get(decision));
		
	}
	
	
	
	public void decideIfBackwards(){

		if(!(robot.getCurrentPosition().getY() == 0)){
			tobackwards.setNewCoordiniates(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY()-1);
			possiblePositions.add(tobackwards);
		}
		else{
			donothing.setNewCoordiniates(robot.getCurrentPosition().getX(), robot.getCurrentPosition().getY());
			possiblePositions.add(donothing);
			Sound.beepSequence();
		}		
	}
	
	
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


}

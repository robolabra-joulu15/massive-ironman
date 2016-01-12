package org.lejos.example;


//Positions class mainly makes life easier while dealing with all the coordinates
public class Positions {
	
	private int x,y;
	
	public Positions(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setNewCoordiniates(int x, int y){
		this.x = x;
		this.y = y;
	}
	

}

package org.lejos.example;


import lejos.nxt.LCD;


//Map class handles all the mapping functionality
public class Map {
	
	private int[][] mapArray;
	
	public Map(){
		this.mapArray = new int[50][50];
	}
	
	//prints map array onto lcd. Called at the end of program
	public void printMap(){		
		for(int x = 0; x<15; x++){
			
			for(int y = 0; y<8; y++){				
				if(this.mapArray[x][y]>0){
					LCD.drawString("x", x, 7-y);
				}
				else{
					LCD.drawString("o", x, 7-y);
				}
			}			
		}		
	}
	
	//sets the value of array[x][y] to given value
	public void setMapValue(int x, int y, int value){
		this.mapArray[x][y] = value;		
	}
	
	//returns value of array[x][y]
	public int getMapValue(int x, int y){
		return this.mapArray[x][y];
	}

}

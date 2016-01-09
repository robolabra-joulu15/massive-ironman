package org.lejos.example;


import lejos.nxt.LCD;

public class Map {
	
	private int[][] mapArray;
	
	public Map(){
		this.mapArray = new int[50][50];
	}
	
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
	
	public void setMapValue(int x, int y, int value){
		this.mapArray[x][y] = value;
		
	}
	
	public int getMapValue(int x, int y){
		return this.mapArray[x][y];
	}

}

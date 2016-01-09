package Movement;
import java.util.ArrayList;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Logic {

	UltrasonicSensor ultra; //Allows us to calculate the distance to the walls
	DifferentialPilot pilot;//Handles the controlling of the vehicle
	int dist = 30; 			//Distance traveled each time we go forward/backward
	ArrayList<String> path = new ArrayList<String>(); //A list containing the turns require to get to the current position
	boolean start = true;   //false if we have ever moved
	
	public Logic(UltrasonicSensor sensor, DifferentialPilot pilotti)
	{
		ultra = sensor;
		pilot = pilotti;
		path.add("start"); //causes the bot to turn around if it ever backtracks to the starting position
	}
	
	public void setDistance(int x)
	{
		dist = x;
	}
	
	//When called, moves the robot forward and then starts the checking procedure
	public char forward()
	{	
		if(!start) //just so we don't run over walls if we begin facing a wall
		{
			pilot.travel(dist);
			start = false;
		}
		/*if there is a path to the left, adds a left turn to the path and returns 'f'
		*so that the main loop knows to keep going forward */ 
		if(checkLeft()) 
		{
			path.add("left");
			return 'f';
		}else
		{
			if(checkRight()) //See above, only checking the "front" instead of left
			{
				path.add("front");
				return 'f';
			}else
			{
				if(checkRight()) // ^ checks the right this time
				{
					path.add("right");
					return 'f';
				}else  //If no paths exist we are in a dead end, so it's time to backtrack so we return 'b'
					{
					pilot.rotate(-90);
					return 'b';
				}
			}
		}
	}
	
	public char backTrack()
	{
		pilot.travel(-dist);
		
		String direction = path.get(path.size()-1); //get the direction chosen in this spot the last time
		rmLast(); //remove said direction from the path
		
		if(direction.equals("left")) //if we chose left last time, we can still go forward or right
		{
			if(checkRight()) //go forward if you can
			{
				path.add("front");
				return 'f';
			}else
			{
				if(checkRight()) //if forward is not okay, check right and go if you can
				{
					path.add("right");
					return 'f';
				}else 
				{ //if neither works, start backtracking
					pilot.rotate(-90);
					return 'b';
				}
			}
		}
		if(direction.equals("front")) //if last time we went forward, we still need to check right
		{
			if(checkRight())
			{
				path.add("right");
				return 'f';
			}else //if there is no path right, continue backtracking
			{
				pilot.rotate(-90);
				return 'b';
			}
		}
		if(direction.equals("right")) //if last time we went right, we know there is no path available so backtrack more
		{
			pilot.rotate(-90);
			return 'b';
		}
		
		if(direction.equals("start")) //if we are in the start position, turn around and continue as normal
		{
			pilot.rotate(180);
			
			if(ultra.getRange() < 20) //if after turning around in the start position, there still is no path 
			{						  //there actually is no way out so we return 'n' for "no-path" and the robot stops
				return 'n';
			}
			return 'f';
		}
		
		return 'n'; //should never actually get here but in case something goes really bad, stop the program.
	}
	
	void rmLast() //I just got annoyed typing this x1000
	{
		path.remove(path.size()-1);
	}
	
	public boolean checkRight() //checks if there is a path to the right, returns true if there is, false otherwise
	{
		pilot.rotate(90);
		
		if(ultra.getRange() < 20)
			return false;
		
		return true;
	}
	

	public boolean checkLeft() //same as above but to the left
	{
		pilot.rotate(-90);
		if(ultra.getRange() < 20)
			return false;
		
		return true;
	}
}

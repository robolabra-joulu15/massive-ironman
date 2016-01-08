package Movement;

import java.util.ArrayList;

import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Logic {

	UltrasonicSensor ultra;
	DifferentialPilot pilot;
	int dist = 30;
	ArrayList<String> path = new ArrayList<String>();
	
	public Logic(UltrasonicSensor sensor, DifferentialPilot pilotti)
	{
		ultra = sensor;
		pilot = pilotti;
	}
	
	public void setDistance(int x)
	{
		dist = x;
	}
	
	public char forward()
	{
		pilot.travel(dist);
		if(checkLeft())
		{
			path.add("left");
			return 'f';
		}else
		{
			if(checkRight())
			{
				path.add("front");
				return 'f';
			}else
			{
				if(checkRight())
				{
					path.add("right");
					return 'f';
				}else
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
		rmLast();
		
		String direction = path.get(path.size()-1);
		
		if(direction.equals("left"))
		{
			if(checkRight())
			{
				path.add("front");
				return 'f';
			}else
			{
				if(checkRight())
				{
					path.add("right");
					return 'f';
				}else
				{
					pilot.rotate(-90);
					return 'b';
				}
			}
		}
		if(direction.equals("front"))
		{
			if(checkRight())
			{
				path.add("right");
				return 'f';
			}else
			{
				pilot.rotate(-90);
				return 'b';
			}
		}
		if(direction.equals("right"))
		{
			pilot.rotate(-90);
			return 'b';
		}
		
		if(direction.equals("start"))
		{
			pilot.rotate(180);
			return 'f';
		}
		
		return 'f'; //should never actually get here
	}
	
	void rmLast()
	{
		path.remove(path.size()-1);
	}
	
	public boolean checkRight()
	{
		pilot.rotate(90);
		
		if(ultra.getRange() < 20)
			return false;
		
		return true;
	}
	

	public boolean checkLeft()
	{
		pilot.rotate(-90);
		if(ultra.getRange() < 20)
			return false;
		
		return true;
	}
}

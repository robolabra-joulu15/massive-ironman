import java.util.ArrayList;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
public class Laby
{

	DifferentialPilot pilot = new DifferentialPilot(4.6, 12.1, Motor.A, Motor.C);
	TouchSensor touch = new TouchSensor(SensorPort.S1);
	UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	ArrayList<Integer> path = new ArrayList<Integer>();
	boolean backTracking = false;
	int dist = 25;
	
	void mainloop()
	{
		while(!touch.isPressed())
		{
			while(backTracking = false)
			{
				pilot.travel(dist);
				if(!checkLeft())
				{
					if(!checkRight())
					{
						if(!checkRight())
						{
							//We are in a dead end so it's time to go back
							backTracking = true;
							pilot.rotate(-90);
							
						}else{path.add(3);}
					}else{path.add(2);}
				}else{path.add(1);}	
			}
			
			while(backTracking)
			{
				pilot.travel(-dist);
				
				if(path.get(path.size()-1) == 1)
				{
					if(!checkRight())
					{
						path.remove(path.size()-1);
						pilot.travel(-dist);
					}else
					{
						if(!checkRight())
						{
							path.remove(path.size()-1);
							pilot.travel(-dist);
						}else
						{
							backTracking = false;
						}
					}
				}
				if(path.get(path.size()-1) == 2)
				{
					if(!checkRight())
					{
						pilot.rotate(-90);
						pilot.travel(-dist);
					}
					else
					{
						backTracking = false;
					}
				}
				if(path.get(path.size()-1) == 3)
				{
					pilot.rotate(-90);
					pilot.travel(-dist);
				}
			}
		}
	}

	public boolean checkRight()
	{
		pilot.rotate(90);
		
		if(ultra.getRange() < 18)
			return false;
		
		return true;
	}

	public boolean checkLeft()
	{
		pilot.rotate(-90);
		if(ultra.getRange() < 18)
			return false;
		
		return true;
	}
	
	
	public static void main (String[] aArg) throws Exception
	{
		Laby l = new Laby();
		l.mainloop();
	}
	
}

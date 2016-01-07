import java.util.ArrayList;


import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;
public class Laby
{

	DifferentialPilot pilot = new DifferentialPilot(5.6f, 10.5f, Motor.A, Motor.C);
	TouchSensor touch = new TouchSensor(SensorPort.S1);
	UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	ArrayList<Integer> path = new ArrayList<Integer>();
	boolean backTracking = false;
	int dist = 30;
	
	
	//Todo: Make this actually readable
	void mainloop() throws Exception
	{

		path.add(0); //So the robot knows to go backwards if the original square is a dead end
		pilot.setTravelSpeed(20);
		pilot.setRotateSpeed(30);
		
		boolean start = true; 
		while(!touch.isPressed())
		{
			while(backTracking = false && !touch.isPressed());
			{
				if(!start)
				{
					pilot.travel(dist);
				}

				start = false;
				
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
			
			while(backTracking && !touch.isPressed())
			{
				pilot.travel(-dist);

				if(path.get(path.size()-1) == 3)
				{
					rmLast();
					pilot.rotate(-90);
					pilot.travel(-dist);
				}
				
				if(path.get(path.size()-1) == 2)
				{
					if(!checkRight())
					{
						pilot.rotate(-90);
						pilot.travel(-dist);
						rmLast();
					}
					else
					{
						rmLast();
						path.add(3);
						backTracking = false;
					}
				}
				
				
				if(path.get(path.size()-1) == 1)
				{
					if(!checkRight())
					{
						if(!checkRight())
						{
							rmLast();
							pilot.rotate(-90);
						}else
						{
							rmLast();
							path.add(3);
							backTracking = false;
						}
					}else
					{
						rmLast();
						path.add(2);
						backTracking = false;
					}
				}
				
				if(path.get(path.size()-1) == 0)
				{
					pilot.rotate(180);
					backTracking = false;
				}
				
			}
		}
	}

	public void rmLast() //Got lazy writing this a gazillion times
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
	
	
	public static void main (String[] aArg) throws Exception
	{
		Laby l = new Laby();
		l.mainloop();
	}
	
}

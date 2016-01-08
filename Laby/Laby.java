import Movement.Logic;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;

public class Laby
{
	
	DifferentialPilot pilot = new DifferentialPilot(5.6f, 11.4f, Motor.A, Motor.C);
	TouchSensor touch = new TouchSensor(SensorPort.S1);
	UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	Logic logic = new Logic(ultra, pilot);
	
	void mainloop() throws Exception
	{

		pilot.setTravelSpeed(20);
		pilot.setRotateSpeed(60);
		char dir = 'f';
		
		while(!touch.isPressed())
		{
			//forward and backTrack both return a char (either f or b) which indicate which one we should call next
			if(dir == 'f')
				dir = logic.forward();
			
			if(dir == 'b')
				dir = logic.backTrack();
		}	
	}
	
	public static void main (String[] aArg) throws Exception
	{
		Laby l = new Laby();
		l.mainloop();
	}
}

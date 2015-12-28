
import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;



public class Main {
	static RegulatedMotor belt = Motor.A;
	static RegulatedMotor arm = Motor.C;
	static RegulatedMotor feeder = Motor.D;
    
	public static void main(String[] args) {
		
		
		ControlThread b = new ControlThread(arm, feeder, belt);
		b.start();						
		
			

	}

}
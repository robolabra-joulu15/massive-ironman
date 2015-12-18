import lejos.nxt.*;

public class MotorController {

	private NXTRegulatedMotor m1;
	private NXTRegulatedMotor m2;
	private int startSwingSpeed;
	private int defaultAccelerationIntervalMs;
	
	public MotorController()
	{
		startSwingSpeed = 150;
		defaultAccelerationIntervalMs = 150;
		m1 = new NXTRegulatedMotor(MotorPort.A);
		m2 = new NXTRegulatedMotor(MotorPort.B);
	}
	
	public void swingNoseDown() throws InterruptedException
	{
		int speed = startSwingSpeed;
		while(speed > 10)
		{
			noseDown(speed, defaultAccelerationIntervalMs);
			speed /= 2;
		}
		neutral();
	}
	public void swingNoseUp() throws InterruptedException
	{
		int speed = startSwingSpeed;
		while(speed > 10)
		{
			noseUp(speed, defaultAccelerationIntervalMs);
			speed /= 2;
		}
		neutral();
	}
	
	
	
	public void noseDown(int speed, int time) throws InterruptedException
	{
		setSpeed(speed);
		m1.forward();
		m2.forward();
		Thread.sleep(time);
	}
	public void noseUp(int speed, int time) throws InterruptedException
	{
		setSpeed(speed);
		m1.backward();
		m2.backward();
		Thread.sleep(time);
	}
	
	
	
	
	
	private void setSpeed(int speed)
	{
		m1.setSpeed(speed);
		m2.setSpeed(speed);
	}
	
	
	public void neutral()
	{
		m1.flt();
		m2.flt();
	}
	
}

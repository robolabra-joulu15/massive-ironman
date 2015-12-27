import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;


public class MotorController2 {

	private NXTRegulatedMotor m1;
	private NXTRegulatedMotor m2;
	
	public MotorController2()
	{
		m1 = new NXTRegulatedMotor(MotorPort.A);
		m2 = new NXTRegulatedMotor(MotorPort.B);
	}
	
	public void forward(int speed) throws InterruptedException
	{
		m1.setSpeed(speed);
		m2.setSpeed(speed);
		
		
		
		m1.forward();
		m2.forward();
		
		Thread.sleep(20);
	}
	public void backward(int speed) throws InterruptedException
	{
		m1.setSpeed(speed);
		m2.setSpeed(speed);
		
		m1.backward();
		m2.backward();
		
		Thread.sleep(20);
	}
	public void flt()
	{
		m1.flt();
		m2.flt();
	}
	
}

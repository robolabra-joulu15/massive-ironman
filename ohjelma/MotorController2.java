import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

/**
 * self describing class for controlling two nxt motors as a clone of each other.
 * Motors should be connected in ports A and B.
 * @author hexvaara
 *
 */
public class MotorController2 {

	private NXTRegulatedMotor m1;
	private NXTRegulatedMotor m2;
	private float landSpeedCorrectionFactor = 0.3f;
	
	public MotorController2()
	{
		m1 = new NXTRegulatedMotor(MotorPort.A);
		m2 = new NXTRegulatedMotor(MotorPort.B);
	}
	
	/**
	 * Commands both motors forward, calculates land speed correction value by getting current speed from motors and adding correction to new speed.
	 * @param speed
	 * @throws InterruptedException
	 */
	public void forward(int speed) throws InterruptedException
	{
		m1.setSpeed(speed + landSpeedCorrectionFactor * m1.getSpeed());
		m2.setSpeed(speed + landSpeedCorrectionFactor * m2.getSpeed());
		
		m1.forward();
		m2.forward();
		
		Thread.sleep(20);
	}
	
	/**
	 * Commands both motors backward, calculates land speed correction value by getting current speed from motors and adding correction to new speed.
	 * @param speed
	 * @throws InterruptedException
	 */
	public void backward(int speed) throws InterruptedException
	{
		m1.setSpeed(speed - landSpeedCorrectionFactor * m1.getSpeed());
		m2.setSpeed(speed - landSpeedCorrectionFactor * m2.getSpeed());
		
		m1.backward();
		m2.backward();
		
		Thread.sleep(20);
	}
	
	/**
	 * Commands both motors to float.
	 */
	public void flt()
	{
		m1.flt();
		m2.flt();
	}
}

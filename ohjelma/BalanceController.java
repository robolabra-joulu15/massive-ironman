/**
 * class for using processed sensor data from sensor controller to control motors.
 * 
 * @author hexvaara
 *
 */

public class BalanceController {

	private SensorController2 sc;
	private MotorController2 mc;
	private CorrectionMap cm;
	
	public BalanceController(SensorController2 s, MotorController2 m)
	{
		sc = s;
		mc = m;
		cm = new CorrectionMap();
	}
	
	/**
	 * a single iteration for keeping the robot in balance. reads data from sensors and commands motors, determines fall down halt if angle is greater than 85.
	 * 
	 * @throws InterruptedException
	 */
	public void iteration() throws InterruptedException
	{
		int current_angle = sc.get().angle();
		float acceleration = sc.get().acceleration();
		
		if (Math.abs(current_angle) > 85) // when robot is fallen down, motors stop.
		{
			mc.flt();
		} else // get measured angle and angle speed and get correction amounts from correction map.
		{
			int toMotors = cm.getMotorSpeed(current_angle, (int) acceleration);
		
			if (toMotors > 0) mc.forward(toMotors);
			else if (toMotors < 0) mc.backward(toMotors);
			else mc.flt();
		}
		
	}
}

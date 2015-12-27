import lejos.nxt.LCD;


public class BalanceController {

	private SensorController2 sc;
	private MotorController2 mc;
	private CorrectionMap cm;
	
	//private int history_size;
	//private int[] history;
	//private int history_index;
	
	private int[] nose_up_strong_table, nose_down_strong_table, nose_up_weak_table, nose_down_weak_table;
	
	public BalanceController(SensorController2 s, MotorController2 m)
	{
		sc = s;
		mc = m;
		//history_index = 0;
		//history_size = 5;
		//history = new int[history_size];
		
		cm = new CorrectionMap();
	}
	
	public void iteration() throws InterruptedException
	{
		//if (history_index == history_size) history_index = 0;
		
		int current_angle = sc.get().angle();
		float acceleration = sc.get().acceleration();
		//float acceleration = acceleration();
		
		if (Math.abs(current_angle) > 85)
		{
			mc.flt();
		}
		
		int toMotors = cm.getMotorSpeed(current_angle, (int) acceleration);
		
		//toMotors = (int)(toMotors*1.5f);
		
		if (toMotors > 0) mc.forward(toMotors);
		else if (toMotors < 0) mc.backward(toMotors);
		else mc.flt();
		
	}
	
	
}

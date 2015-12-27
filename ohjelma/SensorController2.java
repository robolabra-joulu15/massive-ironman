import lejos.nxt.*;

public class SensorController2 {

	private LightSensor ls;
	private MovingPointAverageFilter filter;
	private MovingPointAverageFilter acc_filter;
	
	private int raw_min, raw_max, raw_balance;
	private int positive_scale, negative_scale;
	private float positive_scalar, negative_scalar;
	private int last_read_angle;
	private float last_read_acceleration;
	private int last_read_light_value;
	private float max_acceleration;
	
	
	public SensorController2()
	{
		ls = new LightSensor(SensorPort.S3);
		filter = new MovingPointAverageFilter(3);
		acc_filter = new MovingPointAverageFilter(3);
		last_read_acceleration = 0f;
		last_read_angle = 0;
		last_read_light_value = 0;
		max_acceleration = 0;
	}
	
	public void lcdPrint()
	{
		LCD.clear();
		LCD.drawString("angle : "+last_read_angle, 1, 0);
		LCD.drawString("bp : "+raw_balance, 1, 1);
		LCD.drawString("raw : "+last_read_light_value, 1, 2);
		LCD.drawString("min : "+raw_min, 1, 4);
		LCD.drawString("max : "+raw_max, 1, 5);
		LCD.drawString("acc : "+last_read_acceleration, 1, 6);
		LCD.drawString("maxAcc : "+max_acceleration, 1, 7);
	}
	
	private int raw()
	{
		last_read_light_value = ls.getNormalizedLightValue();
		return last_read_light_value;
	}
	
	public void cal_nose_down()
	{
		raw_min = raw();
	}
	public void cal_nose_up()
	{
		raw_max = raw();
	}
	public void cal_bal()
	{
		raw_balance = raw();
	}
	public void cal_finally()
	{
		positive_scale = raw_max - raw_balance;
		negative_scale = raw_balance - raw_min;
		
		positive_scalar = 100.0f / positive_scale;
		negative_scalar = 100.0f / negative_scale;
		
	}
	
	
	public AngleAcceleration get() // returns angle from -100 to 100
	{
		int raw_zero_biased = filter.filter(raw())-raw_balance;
		int current_angle;
		
		if (raw_zero_biased > 0) current_angle = (int)(raw_zero_biased * positive_scalar);
		else current_angle = (int)(raw_zero_biased * negative_scalar);
		
		float acceleration = acc_filter.floatFilter(current_angle - last_read_angle);
		
		last_read_acceleration = acceleration;
		last_read_angle = current_angle;
		
		if (Math.abs(acceleration) > max_acceleration) max_acceleration = acceleration;
		
		
		return new AngleAcceleration(current_angle, acceleration);
	}
}

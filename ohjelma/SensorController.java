import lejos.nxt.*;


public class SensorController {

	private LightSensor light;
	
	private int light_min;
	private int light_max;
	
	private int balance_point;
	
	public SensorController() throws InterruptedException
	{
		light_min = Integer.MAX_VALUE;
		light_max = Integer.MIN_VALUE;
		
		
		//initialize sensors
		
		light = new LightSensor(SensorPort.S3);
		light.setFloodlight(true);
		
		
		
	}
	
	public void calibrateNoseDown()
	{
		
		int l = light.getNormalizedLightValue();
		if (l < light_min) light_min = l;
	}
	
	public void calibrateNoseUp()
	{
		
		int l = light.getNormalizedLightValue();
		if (l > light_max) light_max = l;
	}
	
	public void calibrateBalancePoint() throws InterruptedException
	{
		int sum = 0;
		
		Sound.buzz();
		for (int i = 0; i < 50; i++)
		{
			sum += light();
			Thread.sleep(100);
		}
		Sound.buzz();
		
		
		
		balance_point = sum/50;
	}
	
	
	
	
	private int cache_size = 6;
	private int[] cache = new int[cache_size];
	private int sum = 0;
	private int index = 0;
	private int filtered_input;
	
	
	
	private int moving_point_average(int input)
	{
		if (index == cache_size) index = 0;
		
		sum -= cache[index];
		cache[index] = input;
		sum += input;
		index += 1;
		
		filtered_input = sum/cache_size;
		return sum / cache_size;
	}
	
	public int acceleration() throws InterruptedException
	{
		
		
		return last_angle-angle();
	}
	
	private int last_angle = 0;
	public int angle()
	{
		//sonic_dist = sonic();
		//light_dist = light();
		
		// scale both in same scale
		
		int plus_scale = light_max - balance_point;
		int minus_scale = balance_point - light_min;
		
		float plus_multiplier = 60.0f / plus_scale;
		float minus_multiplier = 60.0f / minus_scale;
		
		int value = light();
		
		//if (value > light_max-5) return 1000;
		//if (value < light_min+5) return 1000;
		
		int unscaled_angle = value-balance_point;
		
		if (unscaled_angle > 0)
		{
			last_angle = (int)(unscaled_angle * plus_multiplier);
			return last_angle;
		}
		else if (unscaled_angle < 0)
		{
			last_angle = (int)(unscaled_angle * minus_multiplier);
			return last_angle;
		}
		else
		{
			last_angle = 0;
			return 0;
		}
		
		
		//return light.getNormalizedLightValue()-balance_point; 
		
	}
	
	public void lcdPrint()
	{
		LCD.clear();
		LCD.drawString("angle : "+angle(), 1, 0);
		LCD.drawString("bp : "+balance_point, 1, 1);
		LCD.drawString("raw : "+light.getNormalizedLightValue(), 1, 2);
		LCD.drawString("fltr : "+filtered_input, 1, 3);
		LCD.drawString("min : "+light_min, 1, 4);
		LCD.drawString("max : "+light_max, 1, 5);
	}
	
	
	private int light()
	{
		// return 0 to 10 000
		return moving_point_average(light.getNormalizedLightValue());
	}
	
	
}

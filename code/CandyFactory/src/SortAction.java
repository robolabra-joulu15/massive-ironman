
import java.util.HashMap;

import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


class SortAction 
{
	private RegulatedMotor arm;	
	private RegulatedMotor belt;	
	private HashMap<String,Integer> colorCount;

	SortAction(RegulatedMotor m2,RegulatedMotor m)
	{
		this.arm = m2;
		this.belt = m;
		colorCount = new HashMap();

	}

	
	public HashMap<String, Integer> getColorCount() {
		return colorCount;
	}

	public boolean run(int color)
	{

		RegulatedMotor feeder = Motor.D;
		boolean isValidColor = false;
		
		if (color==Color.GREEN) {
			Delay.msDelay(720);
			arm.rotateTo(25);
			Delay.msDelay(500);
			arm.rotateTo(0);			
			isValidColor = true;

		}
		else if (color==Color.RED) {
			Delay.msDelay(1600);
			arm.rotateTo(25);
			Delay.msDelay(500);
			arm.rotateTo(0);			
			isValidColor = true;

		}

		else if(color==Color.YELLOW) {
			arm.rotateTo(25);
			Delay.msDelay(650);
			arm.rotateTo(0);			
			isValidColor = true;
		}
		else if (color==Color.WHITE) {
			belt.forward();
			Delay.msDelay(700);
			belt.backward();
			isValidColor = true;
		}
		arm.stop();
		
		
		if (isValidColor) {
			addColorCount(color);
			feeder.backward();
			Delay.msDelay(220);
			feeder.stop();			
		}
		
		return isValidColor;
			
	}




	public void addColorCount(int color) {
		
		String ColorString = ColorEnum.getInstance(color).name();
		if (colorCount.containsKey(ColorString)) {
			int pcs = colorCount.get(ColorString) + 1;
			colorCount.put(ColorString, pcs);
		} else {
			colorCount.put(ColorString,1);	
		}
	}


}
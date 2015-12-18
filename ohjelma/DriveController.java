
public class DriveController {

	private SensorController sc;
	private MotorController mc;
	private int base_speed = 55;
	private int multiplier = 18;
	private int iteration_time = 20;
	
	public DriveController(SensorController sc, MotorController mc)
	{
		this.sc = sc;
		this.mc = mc;		
	}
	
	public void balance() throws InterruptedException
	{
		int angle = sc.angle();
		
		if (angle < 0 && angle >= -50) mc.noseDown(speed(angle), iteration_time);
		else if (angle > 0 && angle <= 50) mc.noseUp((int)(speed(angle)*1.2), iteration_time);
		else mc.neutral();
	}
	
	private int speed(int angle)
	{
		
		//int base = 50;
		//int multiplier = 2;
		angle = Math.abs(angle);
		
		switch (angle)
		{
			case 0 : return 0;
			case 1 : return 5;
			case 2 : return 10;
			case 3 : return 15;
			case 4 : return 20;
			case 5 : return 30;
			case 6 : return 40;
			case 7 : return 50;
			case 8 : return 60;
			case 9 : return 70;
			case 10 : return 110;
			case 11 : return 120;
			case 12 : return 130;
			case 13 : return 140;
			case 14 : return 160;
			case 15 : return 180;
			case 16 : return 200;
			case 17 : return 210;
			case 18 : return 240; // add
			case 19 : return 250;
			case 20 : return 260;
			case 21 : return 270;
			case 22 : return 280;
			case 23 : return 290;
			case 24 : return 300;
			case 25 : return 310;
			case 26 : return 320;
			case 27 : return 330;
			case 28 : return 340;
			case 29 : return 350;
			case 30 : return 360;
			case 31 : return 370;
			case 32 : return 380;
			case 33 : return 390;
			case 34 : return 400;
			case 35 : return 400;
			case 36 : return 400;
			case 37 : return 400;
			case 38 : return 400;
			case 39 : return 400;
			case 40 : return 400;
			case 41 : return 400;
			case 42 : return 400;
			case 43 : return 400;
			case 44 : return 400;
			case 45 : return 400;
			case 46 : return 400;
			case 47 : return 400;
			case 48 : return 400;
			case 49 : return 400;
			case 50 : return 400;
			case 51 : return 400;
			case 52 : return 400;
			case 53 : return 400;
			case 54 : return 400;
			case 55 : return 400;
			case 56 : return 400;
			case 57 : return 400;
			case 58 : return 400;
			case 59 : return 400;
			case 60 : return 400;
			default : return 400;
		}
		
//		if (angle < 2) return 30;
//		else if (angle < 3) return 45;
//		else if (angle < 4) return 60;
//		else if (angle < 5) return 80;
//		else if (angle < 6) return 100;
//		else if (angle < 7) return 130;
//		else if (angle < 9) return 180;
//		else if (angle < 12) return 500;
//		else if (angle < 16) return 500;
//		else if (angle < 20) return 500;
//		else if (angle < 25) return 500;
//		else return 1000;
		
		
		//return base_speed+angle*multiplier;
		
		
	}
	
}

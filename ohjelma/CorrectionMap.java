/**
 * Class for generating a correction map and finding correction values for balancing a "segway" type robot.
 * 
 * @author hexvaara
 *
 */

public class CorrectionMap {

	private int[][] map;
	private int size;
	private int[] angleMap;
	private int[] speedMap;
	
	public CorrectionMap()
	{
		size = 21;
		double scaleC = 0.0022;
		double scaleX = 3.0;
		map = makeMap(scaleC, scaleX, size);
		angleMap = makeAngleMap();
		speedMap = makeSpeedMap();
		
		
	}
	
	/**
	 * makes a "hashmap" for all possible angles from -100 to 100 so finding a index for angle in correction map is O(1), if else statements tend to slow down the robot.
	 * @return
	 */
	private int[] makeAngleMap()
	{
		int[] returnValue = new int[201];
		
		for	(int i = 0; i < 201; i++)
		{
			returnValue[i] = getAngleIndex(i-100);
		}
		
		return returnValue;
	}
	
	/**
	 * makes a "hashmap" for all possible speeds from -100 to 100 for finding speed index in correction map is O(1) operation.
	 * @return
	 */
	private int[] makeSpeedMap()
	{
		int[] returnValue = new int[201];
		
		for (int i = 0; i< 201; i++)
		{
			returnValue[i] = getSpeedIndex(i-100);
		}
		
		return returnValue;
		
	}
	
	/**
	 * makes a 3D correction map for finding out in O(1) time what the correction move should be when robot is falling down.
	 * 
	 * map is generated "diagonally" determining a ax³+bx function and scaleC = a, scaleX = b. by changing values a and b the aggressiveness of correction moves can be altered for finding values that stands the robot. 
	 * 
	 * in vertical axis there are speeds and angles are horizontal, illustration is only 11x11 and current version is 21x21 sized,
	 * where speed is the angle speed in which the robot is falling and angle the current tilt angle of robot.
	 * 
	 * sign and greatness of every point in map specifies the amount and orientation of correction movement. ie: when robot is in balance, index of 5,5 will be used and correction is 0.
	 * 
	 * 
	 * (not) possible illustration of 11x11 map, for demonstrating what this is all about mostly, hope you'll get the idea :) :
	 * 
	 * private int [][] map =
	 * { 
	 *		{-400,-400,-400,-400,-200,-100, -64, -36, -16,  -4,   0},	
	 *		{-400,-400,-400,-200,-100, -64, -36, -16,  -4,   0,   4},
	 *		{-400,-400,-200,-100, -64, -36, -16,  -4,   0,   4,  16},
	 *		{-400,-200,-100, -64, -36, -16,  -4,   0,   4,  16,  36},
	 *		{-200,-100, -64, -36, -16,  -4,   0,   4,  16,  36,  64},
	 *		{-100, -64, -36, -16,  -4,   0,   4,  16,  36,  64, 100},
	 *		{ -64, -36, -16,  -4,   0,   4,  16,  36,  64, 100, 200},
	 *		{ -36, -16,  -4,   0,   4,  16,  36,  64, 100, 200, 400},
	 *		{ -16,  -4,   0,   4,  16,  36,  64, 100, 200, 400, 400},
	 *		{  -4,   0,   4,  16,  36,  64, 100, 200, 400, 400, 400},
	 *		{   0,   4,  16,  36,  64, 100, 200, 400, 400, 400, 400}
     * };
	 * 
	 * @param scaleC
	 * @param scaleX
	 * @param size
	 * @return
	 */
	private int[][] makeMap(double scaleC, double scaleX, int size)
	{
		int[][] returnValue = new int[size][size];
		
		int center = size / 2;
		
		for (int i=0; i < size; i++)
		{
			for(int j=0; j<size; j++)
			{
				int x = ((j-center)*5)+i*5-center*5;
				returnValue[i][j] = (int)(scaleC*x*x*x+scaleX*x);
			}
		}
		return returnValue;
	}


	/**
	 * for debugging
	 */
	public void printf()
	{
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				System.out.print(map[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	/**
	 * for debugging
	 */
	public void printAngleMap()
	{
		System.out.println("angle map:");
		for(int i = 0; i<201; i++)
		{
			System.out.println(i+" : "+angleMap[i]);
		}
	}
	
	/**
	 * for debugging
	 */
	public void printSpeedMap()
	{
		System.out.println("speed map:");
		for(int i = 0; i<201; i++)
		{
			System.out.println(i+" : "+speedMap[i]);
		}
	}
	
	/**
	 * helps in anglemap making process, time consuming if else's are run only in the starting period and results are calculated to ram for O(1) operations.
	 * 
	 * @param angle
	 * @return
	 */
	private int getAngleIndex(int angle)
	{
		int angle_index = 10;
		
		if (angle > -90 && angle < -81) 		angle_index = 20;
		else if (angle >= -81 && angle < -64) 	angle_index = 19;
		else if (angle >= -64 && angle < -49) 	angle_index = 18;
		else if (angle >= -49 && angle < -36) 	angle_index = 17;
		else if (angle >= -36 && angle < -25) 	angle_index = 16;
		else if (angle >= -25 && angle < -16) 	angle_index = 15;
		else if (angle >= -16 && angle < -9) 	angle_index = 14;
		else if (angle >= -9 && angle < -4) 	angle_index = 13;
		else if (angle >= -4 && angle < -1) 	angle_index = 12;
		else if (angle >= -1 && angle < 0) 		angle_index = 11;
		else if (angle == 0) 					angle_index = 10;
		else if (angle <= 1 && angle > 0) 		angle_index = 9;
		else if (angle <= 4 && angle > 1) 		angle_index = 8;
		else if (angle <= 9 && angle > 4) 		angle_index = 7;
		else if (angle <= 16 && angle > 9) 		angle_index = 6;
		else if (angle <= 25 && angle > 16) 	angle_index = 5;
		else if (angle <= 36 && angle > 25) 	angle_index = 4;
		else if (angle <= 49 && angle > 36) 	angle_index = 3;
		else if (angle <= 64 && angle > 49) 	angle_index = 2;
		else if (angle <= 81 && angle > 64) 	angle_index = 1;
		else if (angle <= 90 && angle > 81) 	angle_index = 0;
		
		return angle_index;
	}
	
	/**
	 * helps in speedmap making process, time consuming if else's are run only in the starting period and results are calculated to ram for O(1) operations.
	 * 
	 * @param angle
	 * @return
	 */
	private int getSpeedIndex(int speed)
	{
		int speed_index = 10;
		
		if (speed < 90 && speed > 81)		speed_index = 0;
		else if (speed <= 81 && speed > 64)     speed_index = 1;
		else if (speed <= 64 && speed > 49)     speed_index = 2;
		else if (speed <= 49 && speed > 36)     speed_index = 3;
		else if (speed <= 36 && speed > 25) 	speed_index = 4;
		else if (speed <= 25 && speed > 16) 	speed_index = 5;
		else if (speed <= 16 && speed > 9 )		speed_index = 6;
		else if (speed <= 9 && speed > 4)    	speed_index = 7;
		else if (speed <= 4 && speed > 1)   	speed_index = 8;
		else if (speed <= 1 && speed > 0)   	speed_index = 9;
		else if (speed == 0 )					speed_index = 10;
		else if (speed >= -1 && speed < 0)     	speed_index = 11;
		else if (speed >= -4 && speed < -1)     speed_index = 12;
		else if (speed >= -9 && speed < -4)     speed_index = 13;
		else if (speed >= -16 && speed < -9) 	speed_index = 14;
		else if (speed >= -25 && speed < -16)	speed_index = 15;
		else if (speed >= -36 && speed < -25)	speed_index = 16;
		else if (speed >= -49 && speed < -36)   speed_index = 17;
		else if (speed >= -64 && speed < -49)   speed_index = 18;
		else if (speed >= -81 && speed < -64)   speed_index = 19;
		else if (speed >= -90 && speed < -81 )	speed_index = 20;
		
		return speed_index;
	}
	
	/**
	 * returns the orientation and greatness of correction movement in O(1) time depending on robot angle and falling speed.
	 * @param angle
	 * @param speed
	 * @return
	 */
	public int getMotorSpeed(int angle, int speed)
	{
		if (speed > -99 && speed < 99 && angle > -99 && angle < 99)
		{
			int speed_index = speedMap[speed+100];
			int angle_index = angleMap[angle+100];
		
			return map[speed_index][angle_index];
		}
		else return 0;
	}
	
	/**
	 * TODO: implement correction with landspeed.
	 * 
	 * 
	 * @param angle
	 * @param speed
	 * @param landspeed
	 * @return
	 */
	public int getMotorSpeed(int angle, int speed, int landspeed)
	{
		// not implemented yet
		return 0;
	}
	
}

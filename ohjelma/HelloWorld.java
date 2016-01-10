import lejos.nxt.*;

public class HelloWorld
{
  public static void main (String[] aArg) throws Exception
  {
	  //debug();
	  run();
  }
  
  /**
   * prints info to local console and you are able to view outputs from algorithm without uploading software to nxt and determine if it seems legit or not.
   * @throws InterruptedException
   */
  public static void debug() throws InterruptedException
  {
	  CorrectionMap m = new CorrectionMap();
	  
	  //System.out.println(m.getMotorSpeed(10, -20));
	  
	  m.printf();
	  m.printAngleMap();
	  m.printSpeedMap();
  }
  
  /**
   * starts the robot, assuming its tilted nose up, calibration starts recording sensor value when nose up, then asks by lowering beep sequence to place the nose down and it records the nose down sensor value.
   * finally after one beep robot must be placed in balance for recording sensor value for balance.
   * 
   * this all must be done for causes of errors by varying light conditions.
   * 
   * finally robot starts balancing program.
   * 
   * @throws InterruptedException
   */
  public static void run() throws InterruptedException
  {
	  SensorController2 s = new SensorController2();
	  MotorController2 m = new MotorController2();
	  
	  Sound.beepSequenceUp();
	  
	  Thread.sleep(1000);
	  
	  // calibrate nose up, place robot nose up.
	  s.cal_nose_up();
	  Sound.buzz();
	  Sound.beepSequence();
	  Thread.sleep(3000);
	  
	  // calibrate nose down, place robot nose down.
	  s.cal_nose_down();
	  Sound.buzz();
	  Sound.beep();
	  Thread.sleep(5000);

	  
	  // calibrate balance point, hold robot in balance.
	  s.cal_bal();
	  Sound.beepSequence();
	  s.cal_finally();
	  Thread.sleep(1000);
	  
	  BalanceController b = new BalanceController(s, m);
	  
	  while (true) 
	  {  
		  b.iteration();
		  s.lcdPrint();
		  Thread.sleep(10);
	  }
  }
}

import lejos.nxt.*;

public class HelloWorld
{
  public static void main (String[] aArg)
  throws Exception
  {
	  
	  //CorrectionMap m = new CorrectionMap();
	  
	  //System.out.println(m.getMotorSpeed(10, -20));
	  
	  //m.printf();
	  //m.printAngleMap();
	  //m.printSpeedMap();
	  
	  run();
	  
	  
	  
	  
	  
//	  MotorController2 m = new MotorController2();
//	  
//	  while ( true )
//	  {	  
//	  for ( int i = 0; i < 1000; i += 2)
//	  {
//		  m.backward(i);
//		  Thread.sleep(20);
//	  }
//	  for ( int i = 1000; i > 0; i -=2)
//	  {
//		  m.backward(i);
//		  Thread.sleep(20);
//	  }
//	  for ( int i = 0; i < 1000; i += 2)
//	  {
//		  m.forward(i);
//		  Thread.sleep(20);
//	  }
//	  for ( int i = 1000; i > 0; i -= 2)
//	  {
//		  m.forward(i);
//		  Thread.sleep(20);
//	  }
//	  }
	  
	  
	  
	  
	  
  }
  
  public static void run() throws InterruptedException
  {
	  SensorController2 s = new SensorController2();
	  MotorController2 m = new MotorController2();
	  
	  Sound.beepSequenceUp();
	  
	  Thread.sleep(1000);
	  
	  //s.calibrateNoseUp();
	  s.cal_nose_up();
	  Sound.buzz();
	  Sound.beepSequence();
	  Thread.sleep(3000);
	  //s.calibrateNoseDown();
	  s.cal_nose_down();
	  Sound.buzz();
	  Sound.beep();
	  Thread.sleep(5000);

	  
	  //s.calibrateBalancePoint();
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

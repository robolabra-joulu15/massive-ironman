import lejos.nxt.*;

public class HelloWorld
{
  public static void main (String[] aArg)
  throws Exception
  {
	  SensorController s = new SensorController();
	  MotorController m = new MotorController();
	  
	  Sound.beepSequenceUp();
	  
	  Thread.sleep(1000);
	  
	  s.calibrateNoseUp();
	  Sound.buzz();
	  Sound.beepSequence();
	  Thread.sleep(3000);
	  s.calibrateNoseDown();
	  Sound.buzz();
	  Sound.beep();
	  Thread.sleep(5000);

	  
	  s.calibrateBalancePoint();
	  Sound.beepSequence();
	  
	  Thread.sleep(1000);
	  
	  DriveController d = new DriveController(s,m);
	  
	  while (true) 
	  {  
		  d.balance();
		  s.lcdPrint();
		  //Thread.sleep(100);
	  }
//	  while(true) 
//	  {
//		  s.lcdPrint();
//		  Thread.sleep(100);
//	  }
  }
}

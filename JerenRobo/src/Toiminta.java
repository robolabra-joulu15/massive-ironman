import lejos.nxt.*;
import lejos.nxt.ColorSensor.Color;
import lejos.util.Delay;
import lejos.nxt.SensorPort;


public class Toiminta {
  public static void main (String[] args) {
	  LightSensor sensor = new LightSensor(SensorPort.S1);
	  int vari;
	  Motor.A.setSpeed(45);
	  Motor.B.setSpeed(45);
	  int kierrokset = 0;
	  
	  while(kierrokset <= 5){
	  
    Motor.A.rotate(45);
    vari = sensor.getNormalizedLightValue();
    if(vari <= 500){
    	Motor.B.rotateTo(90);
    	Delay.msDelay(2000);
    	Motor.B.rotateTo(0);
    }
    else if(vari > 500){
    	Motor.B.rotateTo(-90);
    	Delay.msDelay(2000);
    	Motor.B.rotateTo(0);
    }else{
    	Motor.B.rotateTo(180);
    	Button.waitForPress();
    	Motor.B.rotateTo(0);
    }
    Motor.A.rotate(45);
    Delay.msDelay(1000);
    kierrokset++;
    	
	  }
    
	  }
  
}
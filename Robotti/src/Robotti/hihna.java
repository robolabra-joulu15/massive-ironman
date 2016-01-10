package Robotti;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class hihna {
	private RegulatedMotor hihna;
	private RegulatedMotor arm;
	
	public hihna(){
		arm = new EV3LargeRegulatedMotor(MotorPort.A);
		hihna = new EV3MediumRegulatedMotor(MotorPort.B);
	}
	
	public void kaynnista(){
		hihna.backward();
	}
	
	public void stop(){
		hihna.stop();
	}

	public void right(){
		arm.rotate(60);
		Delay.msDelay(500);
		hihna.rotate(-200);
		Delay.msDelay(500);
		arm.rotate(-80);
		Delay.msDelay(500);
		arm.rotate(20);
		kaynnista();
	}
	
	public void left(){
		arm.rotate(-60);
		Delay.msDelay(500);
		hihna.rotate(-200);
		Delay.msDelay(500);
		arm.rotate(80);
		Delay.msDelay(500);
		arm.rotate(-20);
		kaynnista();
	}
	
	public void poisto(){
		arm.rotate(60);
		Delay.msDelay(500);
		kaynnista();
		Delay.msDelay(1000);
		arm.rotate(-60);
	}
	
	public void hataseis(){
		hihna.stop();
		arm.stop();
	}

}

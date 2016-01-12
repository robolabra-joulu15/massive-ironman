package org.lejos.example;

import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
//luokka hoitaa "pilotin" tehtävän eli liikkumisen
public class Pilot {

	private DifferentialPilot pilot;
	
	public Pilot (DifferentialPilot pl) {
		this.pilot = pl;
		this.pilot.setTravelSpeed(15);	
	}
	//antaa robotille käskyn liikkua
	public void move() { 
		this.pilot.setTravelSpeed(15);
		pilot.forward();
	}
	//pysäyttää robotin
	public void stop() { 
		pilot.stop();
	}
	//käännytään oikealle pyörittämällä vain vasenta pyörää
	public void turnRight(){  
		Motor.B.setSpeed(30);  
		Motor.B.forward();
	}
	
}

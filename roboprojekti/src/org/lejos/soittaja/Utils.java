package org.lejos.soittaja;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.util.Delay;

/* Includes a couple useful methods for the other classes.
*/

public class Utils {

	//listen for four claps and return the average interval between them.
	public static int getTempo() {
		SensorPort sp = SensorPort.S1;
    SoundSensor sensor = new SoundSensor(sp, true);
    int claps = 0;
    long last = 0;
    int clap_intervals = 0;

    while(claps < 4){
    	LCD.drawString("Kuunnellaan tempoa: " + claps, 0, 2);
    	int vol = sensor.readValue();

    	if(vol > 30){
    		long clap = System.currentTimeMillis();
    		if(last != 0){
    			clap_intervals += (clap - last);
    		}
    		last = clap;
    		claps++;
    		LCD.drawString("tempo: " + clap_intervals, 0, 2);
    		LCD.drawString("clap: " + claps, 0, 4);
    		//wait the sound to decrease before listening for the next clap
    		Delay.msDelay(200);
    	}
    	Delay.msDelay(20);
    }

		return clap_intervals / 3;
	}

	public static void resetMotors(){
		Motor.A.rotateTo(0);
		Motor.B.rotateTo(0);
		Motor.C.rotateTo(0);
	}
}

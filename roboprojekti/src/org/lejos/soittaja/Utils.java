package org.lejos.soittaja;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;
import lejos.util.Delay;

public class Utils {
	
	public static int getTempo(){
		SensorPort sp = SensorPort.S1;
        SoundSensor sensor = new SoundSensor(sp, true);
        int claps = 0;
        long last = 0;
        int bpm = 0;
        
        while(claps < 4){
        	LCD.drawString("Kuunnellaan tempoa: " + claps, 0, 2);
        	int vol = sensor.readValue();
        	
        	if(vol > 30){
        		long hit = System.currentTimeMillis();
        		if(last != 0){
        			bpm += (hit - last);
        		} else{
        			bpm += 600;
        		}
        		last = hit;
        		claps++;
        		LCD.drawString("tempo: " + bpm, 0, 2);
        		LCD.drawString("clap: " + claps, 0, 4);
        		Delay.msDelay(200);
        	}
        	Delay.msDelay(20);
        	
        }
        int res = bpm / 4;
        bpm = Math.round((float)(1000 / res) * 60);
		return res;
	}
}

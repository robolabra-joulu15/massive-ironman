package org.lejos.example;

import lejos.nxt.*;
import lejos.robotics.navigation.DifferentialPilot;

//tämä luokka hoitaa kokonaisuuden ja käyttää muita luokkia hyödyksi
public class HelloWorld { //MAINCLASS, en uskallta renameta ettei hajoa

	
    private static Reader reader = new Reader(new LightSensor(SensorPort.S1));
    private static Pilot pilot = new Pilot(new DifferentialPilot(5.6, 17.5f, Motor.A, Motor.B));
    private static Sound sound = new Sound(new SoundSensor(SensorPort.S4));
    
    public static void main(String[] args) {
    	
    	System.out.println("HelloMaailma!!");
        Button.waitForPress(); //käynnistetään napista

		while (true) {
        
        while (!sound.getStatus()) {
        sound.checkNoise();  //käynnistetään taputuksesta
        }
        try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}	
		
        while(sound.getStatus()) { 			
        	pilot.move();
        	sound.checkNoise();  	//pysäytetään taputuksesta
        	if(reader.getValue()<40) {			//tarkistetaan ollaanko viivalle, jos ei niin korjataan.
        		pilot.stop(); 
        		while(reader.getValue()<40) {  //käännytään kunnes ollaan viivalle. (tämä robotti menee vaaleaa viivaa ja stoppaa mustalla)
        			pilot.turnRight();
        		}
        		pilot.stop();
        	}
        }
        pilot.stop();	
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}    
    }      		
    }
}

        

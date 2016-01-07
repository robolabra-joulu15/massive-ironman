package org.lejos.soittaja;

import lejos.nxt.*;
import lejos.util.*;

public class Soittaja extends Thread{
	private int tempo;
	private int strumSpeed;
	private String song;
	private boolean slow;
	private boolean doubleTempo;
	
	public Soittaja(String song){
		this.tempo = 130;
		this.song = song;
		this.strumSpeed = 220;
		this.slow = false;
		this.doubleTempo = false;
		Motor.C.setSpeed(strumSpeed);
		LCD.drawString("Luotiin soittaja" + song, 0, 1);
	}
	
	
	public void playChord(char chord, boolean single, int volume, boolean chordChange){
		
		if(chord == 'D'){
			Motor.A.rotateTo(0, true);
		} else if(chord == 'G'){
			Motor.A.rotateTo(90, true);
		} else if(chord == 'A'){
			Motor.A.rotateTo(-90, true);
		} else if(chord == ' '){
			Delay.msDelay(tempo);
			return;
		} else if(chord == '!'){
			slow = !slow;
			return;
		} else if(chord == '<' || chord == '>'){
			if(doubleTempo){
				tempo *= 2;
			} else{
				tempo /= 2;
			}
			doubleTempo = !doubleTempo;
			return;
		}

		Motor.B.rotateTo(volumeToMotorAngle(volume), true);
		strumDown(single, slow, doubleTempo);
		
		if(chordChange){
			Motor.A.rotateTo(0, true);
		}
		Delay.msDelay(80);
	}
	
	public void strumDown(boolean single, boolean slow, boolean doubleTempo){
		Delay.msDelay(80); // wait the volume to settle
		if(slow){
			Motor.C.setSpeed(80);
		}
		Motor.C.rotateTo(80, true); // vie 0.28 sekuntia
		
		// if single is set false, the chord is played twice, once down and once up
		if(single){
			Delay.msDelay(tempo * 2 - 200);
			Motor.B.rotateTo(0, true);
			Motor.C.rotateTo(0, true);
			Delay.msDelay(190);

		} else{
			Delay.msDelay(tempo + 200);
			Motor.C.rotateTo(0, true);
			Delay.msDelay(tempo - 190);
		}
		if(slow){
			Motor.C.setSpeed(strumSpeed);
		}
	}
	
	//guitar pattern from the song viidestoista yö
	public void juiceSointu(){
		Motor.A.rotateTo(0, true);
		Motor.B.rotateTo(volumeToMotorAngle(4), true);
		Delay.msDelay(80); // wait the volume to settle
		
		Motor.C.rotateTo(80);
		Delay.msDelay((int)(tempo * 1.3 - (strumSpeed / 70)));
		Motor.C.rotateTo(0);
		Motor.C.rotateTo(80);
		Motor.B.rotateTo(0, true);
		Motor.C.rotateTo(0);
		Delay.msDelay((int)(tempo * 1.5 - (strumSpeed / 70)));
		
	}
	
	// modifies delay to sound right when playing
	public void musicalDelay(boolean single){
		if(single){
			Delay.msDelay((tempo * 2) - (strumSpeed / 70) - 300);
		} else{
			Delay.msDelay(tempo - (strumSpeed / 70) - 150);
		}
	}
	
	//converts volume value to motor angles, where input values 0-5 are converted to angles between 55-75
	public int volumeToMotorAngle(int volume){
		if(volume >= 0 && volume <= 5){
			return (volume * 4) + 55;
		}
		//else return default value
		return 65;
	}
	
	public void setTempo(int tempo){
		this.tempo = tempo;
	}
	
	public void run(){
		int i = 0;
		while(i < song.length() - 1){
			char sointu = song.charAt(i);
			char nextSointu = song.charAt(i + 1);
//			playChord(sointu, true, 4, false);
			playChord(sointu, (i % 2 == 0), 4, (sointu != nextSointu));
			i++;
		}
		playChord(song.charAt(i), true, 5, false);
		Motor.A.rotateTo(0);  
		Motor.B.rotateTo(0);
		Motor.C.rotateTo(0);
	}
}

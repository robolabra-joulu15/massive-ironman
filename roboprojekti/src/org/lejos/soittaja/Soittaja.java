package org.lejos.soittaja;

import lejos.nxt.*;
import lejos.util.*;

public class Soittaja extends Thread{
	private int tempo;
	private int strumSpeed;
	private String chords;
	private boolean slow;
	private boolean doubleTempo;

	public Soittaja(String chords){
		this.tempo = 500;
		this.chords = chords;
		this.strumSpeed = 220;
		this.slow = false;
		this.doubleTempo = false;
		Motor.C.setSpeed(strumSpeed);
		LCD.drawString("Luotiin soittaja", 0, 1);
	}


	public void playChord(char chord, boolean single, int volume, boolean chordChange){

		if(chord == 'D'){
			Motor.A.rotateTo(0, true);
		} else if(chord == 'G'){
			Motor.A.rotateTo(90, true);
		} else if(chord == 'A'){
			Motor.A.rotateTo(-90, true);
		} else if(chord == ' '){
			Delay.msDelay(tempo + 160);
			return;
		} else if(chord == '!'){
			slow = !slow;
			return;
		} else if(chord == '<' || chord == '>'){
			toggleDoubleTempo();
			return;
		} else {
			return;
		}

		Motor.C.setSpeed(strumSpeed);
		useVolume(volume);
		strumDown(single, slow, doubleTempo);

		if(chordChange){
			Motor.A.rotateTo(0, true);
		}
		Delay.msDelay(80);
	}

	// Strums down the strings. Can be single, double (down and up in double tempo) and/or slow strum.
	public void strumDown(boolean single, boolean slow, boolean doubleTempo){
		//slow chords are played at slower speeds
		if(slow){
			Motor.C.setSpeed(80);
		}
		//strum down
		Motor.C.rotateTo(80, true);

		// if single is set false, the chord is played twice, once down and once up
		if(single){
			//lifts the pick and returns it up without touching the strings
			Delay.msDelay(tempo * 2 - 200);
			Motor.B.rotateTo(0, true);
			Motor.C.rotateTo(0, true);
			Delay.msDelay(200);

		} else {
			//returns pick up and play the chord again
			Delay.msDelay(tempo + 200);
			Motor.C.rotateTo(0, true);
			Delay.msDelay(tempo - 200);
		}
	}

	/* Takes volume values between 0-5 and adjusts the motor angle (=height of the pick) accordingly
	 * so that smaller values provide smaller sound and bigger values bigger sound
	 */
	public void useVolume(int volume){
		if(volume >= 0 && volume <= 5){
			Motor.B.rotateTo((volume * 4) + 55, true);
		} else{
			//else use default value
			Motor.B.rotateTo(65, true);
		}
		Delay.msDelay(80); // wait the volume to settle
	}

	//doubles or halves the interval between chords
	public void toggleDoubleTempo(){
		if(doubleTempo){
			tempo *= 2;
		} else{
			tempo /= 2;
		}
		doubleTempo = !doubleTempo;
	}

	//set the time between chords in milliseconds, single chords are played at 2x tempo
	public void setTempo(int tempo){
		this.tempo = tempo;
	}

	//reads and plays chords one by one using the given tempo and song
	public void run(){
		for(int i = 0; i < chords.length() - 1; i++){
			char sointu = chords.charAt(i);
			char nextSointu = chords.charAt(i + 1);
			playChord(sointu, true, 4, (sointu != nextSointu));
		}
		//play last chord as slow single strum chord with volume 4
		playChord(chords.charAt(chords.length() - 1), true, 4, false);
		Utils.resetMotors();
	}
}

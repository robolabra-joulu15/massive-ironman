package org.lejos.soittaja;

import lejos.nxt.*;
import lejos.util.*;

public class Laulaja extends Thread{

	private String melodia;
	private int tempo;
	private int volume;

	public Laulaja(String melodia, int volume){
		this.melodia = melodia;
		this.tempo = 500;
		this.volume = volume;
	}

	public void run(){
		Sound.setVolume(volume);
		Delay.msDelay(160); //Hand picked delay to sync with Soittaja :)

		//Go through each char in melodia and play them in tempo. Empty char is a musical break.
		for(int i = 0; i < melodia.length(); i++){
			char note = melodia.charAt(i);
			if(note == ' '){
				Delay.msDelay(tempo + 160);
			} else{
				Sound.playNote(Sound.PIANO, noteToTone(note), tempo);
			}
		}
	}

	//set the interval between played notes
	public void setTempo(int tempo){
		this.tempo = tempo;
	}

	/* Converts given chars (0-8) to note-frequencies so that 1 returns note A,
	2 returns note H and so on in according to D-major scale.
	(This presumes your guitar is tuned in D-major)
	*/
	public int noteToTone(char c){
		int baseTone = 293;
		switch (c) {
		case '0':
			return 392;
		case '1':
			return 440;
		case '2':
			return 494;
		case '3':
			return 554;
		case '4':
			return 587;
		case '5':
			return 659;
		case '6':
			return 739;
		case '7':
			return 784;
		case '8':
			return 880;
		default:
			return baseTone;
		}
	}
}

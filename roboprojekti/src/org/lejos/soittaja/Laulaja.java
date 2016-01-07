package org.lejos.soittaja;

import lejos.nxt.*;
import lejos.util.*;

public class Laulaja extends Thread{
	
	private String melodia;
	private int tempo;
	
	public Laulaja(String melodia){
		this.melodia = melodia;
		this.tempo = 500;
	}
	
	public void run(){
		Sound.setVolume(50);
		Delay.msDelay(200);
		for(int i = 0; i < melodia.length(); i++){
			
			char note = melodia.charAt(i);
			if(note == ' '){
				Delay.msDelay(tempo + 160);
			} else{
				Sound.playNote(Sound.PIANO, noteToTone(note), tempo);
			}
		}
	}
	public void setTempo(int tempo){
		this.tempo = tempo;
	}
	
	private int noteToTone(char c){
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

package util;

import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;

/*
  
 This class is used to capsulate config values so they're easy to use and edit everywhere using these methods.
  
 */

public class Configuration {

	public int movementSpeed;
	public MotorPort leftMotor;
	public MotorPort rightMotor;
	public int lineColor;
	public int backgroundColor;
	
	public Configuration() {
	    //EDIT DEFAULTS HERE
		this.movementSpeed = 360;
		this.leftMotor = MotorPort.A;
		this.rightMotor = MotorPort.B;
		this.lineColor = 35;
		this.backgroundColor = 55;
    }
	
	public void setBackgroundColor(int backgroundColor) {
	    this.backgroundColor = backgroundColor;
    }
	
	public void setLeftMotor(char motor) {
	    this.leftMotor = charToMotor(motor);
    }

	public void setRightMotor(char motor) {
	    this.rightMotor = charToMotor(motor);
    }
	
	public void setLineColor(int lineColor) {
	    this.lineColor = lineColor;
    }

	public void setMovementSpeed(int movementSpeed) {
	    this.movementSpeed = movementSpeed;
    }
	
	public int getBackgroundColor() {
	    return backgroundColor;
    }
	
	public MotorPort getLeftMotorPort() {
	    return leftMotor;
    }

	public MotorPort getRightMotorPort() {
	    return rightMotor;
    }
	
	public char getLeftMotorPortChar() {
	    return motorPortToChar(this.leftMotor);
    }

	public char getRightMotorPortChar() {
	    return motorPortToChar(this.rightMotor);
    }
	
	public int getLineColor() {
	    return lineColor;
    }
	
	public int getMovementSpeed() {
	    return movementSpeed;
    }
	
	public MotorPort charToMotorPort(char c) {
		if (c == 'A') {
			return MotorPort.A;
		}else if (c == 'B') {
			return MotorPort.B;
		}else if (c == 'C') {
			return MotorPort.C;
		}else {
			return null;
		}
	}
	
	public char motorPortToChar(MotorPort port) {
		if (port.equals(MotorPort.A)) {
			return 'A';
		}else if (port.equals(MotorPort.B)) {
			return 'B';
		}else if (port.equals(MotorPort.C)) {
			return 'C';
		}else {
			return '0';
		}
	}
	
}

package util;

import lejos.nxt.Motor;
import lejos.robotics.RegulatedMotor;

/*
  
 This class is used to capsulate config values so they're easy to use and edit everywhere using these methods.
  
 */

public class Configuration {

	public int movementSpeed;
	public int rotationSpeed;
	public RegulatedMotor leftMotor;
	public RegulatedMotor rightMotor;
	public double wheelWidth;
	public double trackWidth;
	public int lineColor;
	public int backgroundColor;
	
	public Configuration() {
	    //EDIT DEFAULTS HERE
		this.movementSpeed = 20;
		this.rotationSpeed = 180;
		this.leftMotor = Motor.A;
		this.rightMotor = Motor.B;
		this.wheelWidth = 5.6;
		this.trackWidth = 12.0;
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
	
	public void setRotationSpeed(int rotationSpeed) {
	    this.rotationSpeed = rotationSpeed;
    }
	
	public void setTrackWidth(double trackWidth) {
	    this.trackWidth = trackWidth;
    }
	
	public void setWheelWidth(double wheelWidth) {
	    this.wheelWidth = wheelWidth;
    }
	
	public int getBackgroundColor() {
	    return backgroundColor;
    }
	
	public RegulatedMotor getLeftMotor() {
	    return leftMotor;
    }

	public RegulatedMotor getRightMotor() {
	    return rightMotor;
    }
	
	public char getLeftMotorChar() {
	    return motorToChar(this.leftMotor);
    }

	public char getRightMotorChar() {
	    return motorToChar(this.rightMotor);
    }
	
	public int getLineColor() {
	    return lineColor;
    }
	
	public int getMovementSpeed() {
	    return movementSpeed;
    }
	
	public int getRotationSpeed() {
	    return rotationSpeed;
    }
	
	public double getTrackWidth() {
	    return trackWidth;
    }
	
	public double getWheelWidth() {
	    return wheelWidth;
    }
	
	public RegulatedMotor charToMotor(char c) {
		if (c == 'A') {
			return Motor.A;
		}else if (c == 'B') {
			return Motor.B;
		}else if (c == 'C') {
			return Motor.C;
		}else {
			return null;
		}
	}
	
	public char motorToChar(RegulatedMotor motor) {
		if (motor.equals(Motor.A)) {
			return 'A';
		}else if (motor.equals(Motor.B)) {
			return 'B';
		}else if (motor.equals(Motor.C)) {
			return 'C';
		}else {
			return '0';
		}
	}
	
}

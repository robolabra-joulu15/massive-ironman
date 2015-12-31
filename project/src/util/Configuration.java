package util;

import lejos.nxt.MotorPort;

/*
  
 This class is used to capsulate config values so they're easy to use and edit everywhere using these methods.
  
 */

public class Configuration {

	public int movementSpeed;
	public MotorPort leftMotorPort;
	public MotorPort rightMotorPort;
	public int lineColor;
	public int backgroundColor;
	public int PID_kp;
	public int PID_ki;
	public int PID_kd;
	
	public Configuration() {
	    //EDIT DEFAULTS HERE
		this.movementSpeed = 20;
		this.leftMotorPort = MotorPort.A;
		this.rightMotorPort = MotorPort.B;
		this.lineColor = 35;
		this.backgroundColor = 55;
		this.PID_kp = 10;
		this.PID_ki = 1;
		this.PID_kd = 100;
	}
	
	public void setPID_kd(int pIDKd) {
	    PID_kd = pIDKd;
    }
	
	public void setPID_ki(int pIDKi) {
	    PID_ki = pIDKi;
    }
	
	public void setPID_kp(int pIDKp) {
	    PID_kp = pIDKp;
    }
	
	public void setBackgroundColor(int backgroundColor) {
	    this.backgroundColor = backgroundColor;
    }
	
	public void setLeftMotorPort(char motor) {
	    this.leftMotorPort = charToMotorPort(motor);
    }

	public void setRightMotorPort(char motor) {
	    this.rightMotorPort = charToMotorPort(motor);
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
	
	public int getPID_kd() {
	    return PID_kd;
    }
	
	public int getPID_ki() {
	    return PID_ki;
    }
	
	public int getPID_kp() {
	    return PID_kp;
    }
	
	public MotorPort getLeftMotorPort() {
	    return leftMotorPort;
    }

	public MotorPort getRightMotorPort() {
	    return rightMotorPort;
    }
	
	public char getLeftMotorPortChar() {
	    return motorPortToChar(this.leftMotorPort);
    }

	public char getRightMotorPortChar() {
	    return motorPortToChar(this.rightMotorPort);
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

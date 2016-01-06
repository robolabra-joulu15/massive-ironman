package util;

import lejos.nxt.MotorPort;

/*
  
  This class is used to encapsulate configuration values so they're easy to use 
  and edit everywhere using these methods. Contains mainly getters and setters.
  
*/

public class Configuration {

    public int movementSpeed;
    public MotorPort leftMotorPort;
    public MotorPort rightMotorPort;
    public int lineColor;
    public int backgroundColor;
    public double pid_kp;
    public double pid_ki;
    public double pid_kd;

    public Configuration() {
        //EDIT DEFAULTS HERE
        this.movementSpeed = 20;
        this.leftMotorPort = MotorPort.A;
        this.rightMotorPort = MotorPort.B;
        this.lineColor = 35;
        this.backgroundColor = 55;
        this.pid_kp = 1.2;
        this.pid_ki = 0.0008;
        this.pid_kd = 5;
    }

    /*
    
     Setters for configuration values
    
    */
    
    public void setPid_kp(double pid_kp) {
        this.pid_kp = pid_kp;
    }

    public void setPid_ki(double pid_ki) {
        this.pid_ki = pid_ki;
    }

    public void setPid_kd(double pid_kd) {
        this.pid_kd = pid_kd;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setLeftMotorPort(char motor) {
        this.leftMotorPort = CharMotorPortTools.charToMotorPort(motor);
    }

    public void setRightMotorPort(char motor) {
        this.rightMotorPort = CharMotorPortTools.charToMotorPort(motor);
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    /*
    
     Getters for configuration values
    
    */
    
    public int getBackgroundColor() {
        return backgroundColor;
    }

    public double getPid_kp() {
        return pid_kp;
    }

    public double getPid_ki() {
        return pid_ki;
    }

    public double getPid_kd() {
        return pid_kd;
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

}

package util;

import lejos.nxt.MotorPort;

/*

  This class includes two utility methods to convert char value to MotorPort
  object and the other way around.

*/

public class CharMotorPortTools {

    //Convert char value to MotorPort
    public static MotorPort charToMotorPort(char c) {
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

    //Conver MotorPort to char value
    public static char motorPortToChar(MotorPort port) {
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

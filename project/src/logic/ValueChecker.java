package logic;

import ui.Error;
import util.Configuration;

/*

  This class is used to check if user given configuration values are valid.

*/

public class ValueChecker {

    private Configuration config;

    public ValueChecker(Configuration config) {
        this.config = config;
    }

    public boolean check() {
        Error error;

        if (this.config.getLeftMotorPort().equals(this.config.getRightMotorPort())) {
            error = new Error("Left and right motor can't be the same!");
            error.display();
            return false;
        }else if (this.config.getMovementSpeed() <= 0 || this.config.getMovementSpeed() > 100) {
            error = new Error("Movement speed must be between 0 and 100.");
            error.display();
            return false;
        }

        return true;
    }

}

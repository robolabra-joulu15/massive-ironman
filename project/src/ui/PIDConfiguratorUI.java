package ui;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import util.Configuration;

public class PIDConfiguratorUI {

    private Configuration config;

    public PIDConfiguratorUI(Configuration config) {
        this.config = config;
    }

    public boolean start() {
        this.displayInterface();
        Pointer pointer = new Pointer(0, 1, '>');
        pointer.render();
        int selection = 1;

        while (true) {

            int press = Button.waitForPress();

            if (press == Button.ID_ESCAPE) {
                return false;
            }else if (press == Button.ID_ENTER) {
                LCD.clear();

                if (selection == 1) {
                    //Select p constant
                    NumberSelector kpSelect = new NumberSelector(10, this.config.getPID_kp(), 5);
                    this.config.setPID_kp(kpSelect.select());
                }else if (selection == 2) {
                    //Select i constant
                    NumberSelector kiSelect = new NumberSelector(10, this.config.getPID_ki(), 5);
                    this.config.setPID_ki(kiSelect.select());
                }else if (selection == 3) {
                    //Select d constant
                    NumberSelector kdSelect = new NumberSelector(10, this.config.getPID_kd(), 5);
                    this.config.setPID_kd(kdSelect.select());
                }else if (selection == 4) {
                    //Back!
                    return false;
                }else if (selection == 5) {
                    //Start robot
                    return true;
                }

                LCD.clear();
                this.displayInterface();
            }else if (press == Button.ID_LEFT) {
                selection--;
                if (selection < 1) {
                    selection = 5;
                }
            }else if (press == Button.ID_RIGHT) {
                selection++;
                if (selection > 5) {
                    selection = 1;
                }
            }

            pointer.setY(selection);
        }
    }

    private void displayInterface() {
        //Display texts
        LCD.drawString("PID Config", 0, 0);
        LCD.drawString("KP: ", 1, 1);
        LCD.drawString("KI: ", 1, 2);
        LCD.drawString("KD: ", 1, 3);
        LCD.drawString("BACK ", 1, 4);
        LCD.drawString("START! ", 1, 5);

        //Display values
        LCD.drawString("" + this.config.getPID_kp(), 5, 1);
        LCD.drawString("" + this.config.getPID_ki(), 5, 2);
        LCD.drawString("" + this.config.getPID_kd(), 5, 3);
    }
}

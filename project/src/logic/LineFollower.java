package logic;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import ui.ConfiguratorUI;
import util.Configuration;

public class LineFollower {

    public void start() {
        //Initialize general objects
        Configuration config = new Configuration();
        ConfiguratorUI configUI = new ConfiguratorUI(config);
        LightSensor light = new LightSensor(SensorPort.S1, false);
        PIDController pid = new PIDController(config, light);
        ValueChecker valueChecker = new ValueChecker(config);

        while (true) {
            //Start configurator UI
            if (!configUI.start()) {
                break;
            }
            LCD.clear();

            //Check if configuration values are valid
            if (valueChecker.check()) {
                //OK -> Start PID controller
                pid.start();
            }
        }

        Sound.beep();

    }

}

package logic;

import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import ui.ConfiguratorUI;
import util.Configuration;

/*

  This class initializes all generally needed objects for the line follower,
  and is the core of the program.

*/

public class LineFollower {

    private Configuration config;
    private ConfiguratorUI configUI;
    private LightSensor light;
    private PIDController pid;
    private ValueChecker valueChecker;
    
    public LineFollower() {
        //Initialize general objects
        this.config = new Configuration();
        this.configUI = new ConfiguratorUI(this.config);
        this.light = new LightSensor(SensorPort.S1, false);
        this.pid = new PIDController(this.config, this.light);
        this.valueChecker = new ValueChecker(this.config);
    }

    public void start() {
        while (true) {
            //Start configurator UI
            if (!this.configUI.start()) {
                break;
            }
            LCD.clear();

            //Check if configuration values are valid
            if (this.valueChecker.check()) {
                //OK -> Start PID controller
                this.pid.start();
            }
        }

        Sound.beep();
    }

}

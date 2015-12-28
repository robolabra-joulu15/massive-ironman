

import lejos.hardware.Button;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;


	class ControlThread extends Thread
	{
	    private RegulatedMotor arm;
	    private RegulatedMotor feeder;
	    private RegulatedMotor belt;
	    private ColorSensorThread colorSensorThread;

	    ControlThread(RegulatedMotor m2, RegulatedMotor feeder, RegulatedMotor belt)
	    {
	    	this.arm = m2;
	    	this.feeder = feeder;
	    	this.belt = belt;
	    	
	    }
	    
	    public void run()
	    {
	        
	    	colorSensorThread = new ColorSensorThread(arm,belt);
			colorSensorThread.start();
	    	
	    	belt.setSpeed(250);
			belt.backward();
			
			feeder.backward();
			Delay.msDelay(220);
			feeder.stop();
	    	
	    	while (true)
	        {
	        	Button.waitForAnyPress();
	    		if(Button.ESCAPE.isDown()) {
	    			arm.rotateTo(0);
	    			belt.stop();	    			
	    			Button.waitForAnyPress();
	    			if(Button.ESCAPE.isDown()) {
	    				System.exit(0);
	    			}
	    			if (Button.ENTER.isDown()) {
		    			belt.backward();
	    				feeder.rotate(-30);
		    		}
	    		}
	    		if (Button.ENTER.isDown()) {
	    			feeder.rotate(-30);
	    		}
	            
	        }
	        
	    }
	    
	    
	}
	
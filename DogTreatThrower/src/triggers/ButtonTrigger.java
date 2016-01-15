package triggers;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class ButtonTrigger implements Trigger, Runnable {
	private TouchSensor buttonTrigger;
	private boolean flag = false;
	private boolean pause = false;
	private String name = "ButtonTrigger";

	public ButtonTrigger() {
		buttonTrigger = new TouchSensor(SensorPort.S1);
	}

	@Override
	public void run() {
		for (;;) {
			if (buttonTrigger.isPressed()) {
				setFlag(true);
				setPause(true);
				waiting(1000);
			}
			while (pause) {
				waiting(1000);
			}
			waiting(200);
		}
	}

	private void waiting(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean getFlag() {
		return this.flag;
	}

	@Override
	public boolean getPause() {
		return this.pause;
	}

	@Override
	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	@Override
	public void setPause(boolean pause) {
		this.pause = pause;
	};

	@Override
	public String getName() {
		return this.name;
	}
}
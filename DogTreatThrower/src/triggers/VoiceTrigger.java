package triggers;

import lejos.nxt.SensorPort;
import lejos.nxt.SoundSensor;

public class VoiceTrigger implements Runnable, Trigger {
	private SoundSensor voiceTrigger;
	private boolean flag = false;
	private boolean pause = false;
	private String name = "VoiceTrigger";
	private int limit; // Limit for SoundSensor's launch, set in constructor

	public VoiceTrigger() {
		this.voiceTrigger = new SoundSensor(SensorPort.S2);
		this.limit = 20;
	}

	@Override
	public void run() {
		while (true) {
			if (voiceTrigger.readValue() > this.limit) {
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
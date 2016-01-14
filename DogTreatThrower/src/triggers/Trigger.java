package triggers;

public interface Trigger extends Runnable {
	/*
	 * New SensorTriggers can be implemented with this interface.
	 * RoboRoutine-class store's active triggers and TriggerListener interacts
	 * with those active triggers.
	 * 
	 * ButtonTrigger- and VoiceTrigger-classes are good examples for new
	 * [Something]Trigger class.
	 */

	public void run();

	public boolean getFlag();

	public boolean getPause();

	public void setFlag(boolean flag);

	public void setPause(boolean pause);

	public String getName();
}

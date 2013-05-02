package pjak.psmelter.util;

import org.powerbot.game.api.util.Timer;

public class Sleep {

	/*
	 * Waits for a condition to be met.
	 */
	public static boolean waitFor(Condition c, long i) {
		Timer time = new Timer(i);
		while (time.isRunning()) {
			if (c.validate()) {
				return true;
			}
		}
		return false;
	}
	
	
}

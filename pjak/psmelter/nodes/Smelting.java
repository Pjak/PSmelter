package pjak.psmelter.nodes;

import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;
import pjak.psmelter.PSmelter;
import pjak.psmelter.util.Condition;
import pjak.psmelter.util.Paint;
import pjak.psmelter.util.Sleep;

public class Smelting extends Node {

	final SceneObject FURNACE = SceneEntities.getNearest(PSmelter.location.getFurnaceID());

	@Override
	public boolean activate() {
		return PSmelter.bar.hasMinimum() && FURNACE.isOnScreen();
	}

	@Override
	public void execute() {
		Paint.Status = "Smelting...";
		if (FURNACE.isOnScreen() && !Widgets.get(1251).validate()) {
			if (FURNACE != null) {
				if (!Widgets.get(1371).validate()) {
					FURNACE.interact("Smelt", "Furnace");
					Sleep.waitFor(new Condition() {

						@Override
						public boolean validate() {
							return Widgets.get(1371).validate();
						}
					}, 4000);
				}
			}
		}

		if (Widgets.get(1370).getChild(56).isOnScreen()
				&& !Widgets.get(1370).getChild(56).getText()
						.contains(PSmelter.bar.getName())) {
			Widgets.get(1371).getChild(44)
					.getChild(PSmelter.bar.getWidgChild()).interact("Select");
			sleep(Random.nextInt(750, 1500));
		}

		while (Widgets.get(1371).validate()) {
			Widgets.get(1370).getChild(38).click(true);
			Task.sleep(1000);
		}
	}
}

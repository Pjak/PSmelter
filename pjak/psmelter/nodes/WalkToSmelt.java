package pjak.psmelter.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.SceneObject;
import pjak.psmelter.PSmelter;
import pjak.psmelter.util.Condition;
import pjak.psmelter.util.Sleep;

public class WalkToSmelt extends Node {
	
	final SceneObject FURNACE = SceneEntities.getNearest(PSmelter.location.getFurnaceID());

	@Override
	public boolean activate() {
		return !FURNACE.isOnScreen() && PSmelter.bar.hasMinimum() && !Bank.isOpen();
	}

	@Override
	public void execute() {
		Walking.walk(PSmelter.location.getFurnaceArea().getCentralTile());
		Sleep.waitFor(new Condition() {

			@Override
			public boolean validate() {
				return FURNACE.isOnScreen();
			}
		}, 4000);
	}

}

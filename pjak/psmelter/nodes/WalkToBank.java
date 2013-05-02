package pjak.psmelter.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.widget.Bank;
import pjak.psmelter.PSmelter;
import pjak.psmelter.util.BankingUtil;


public class WalkToBank extends Node {

	@Override
	public boolean activate() {
		return !Bank.isOpen() && !PSmelter.bar.hasMinimum();
	}

	@Override
	public void execute() {
		BankingUtil.open();
	}

}

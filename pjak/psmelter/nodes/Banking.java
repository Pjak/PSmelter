package pjak.psmelter.nodes;

import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import pjak.psmelter.PSmelter;
import pjak.psmelter.util.BankingUtil;
import pjak.psmelter.util.Condition;
import pjak.psmelter.util.Sleep;

public class Banking extends Node {

	@Override
	public boolean activate() {
		return (!PSmelter.bar.hasMinimum() && Bank.isOpen());
	}

	@Override
	public void execute() {
		if (Inventory.contains(PSmelter.bar.getBarID())) {
			BankingUtil.depositInventory();
		} else {
			if (PSmelter.bar.HasSecondary()) {
				if (Inventory.contains(PSmelter.bar.getPrimaryOreID())) {
					BankingUtil.withdraw(PSmelter.bar.getSecondaryOreID(),
							PSmelter.bar.getWithdrawAmount()[1]);
				} else {
					BankingUtil.withdraw(PSmelter.bar.getPrimaryOreID(),
							PSmelter.bar.getWithdrawAmount()[0]);
				}
			} else {
				if (Inventory.contains(PSmelter.bar.getPrimaryOreID())) {
					BankingUtil.close();
				} else {
					BankingUtil.withdraw(PSmelter.bar.getPrimaryOreID(),
							PSmelter.bar.getWithdrawAmount()[0]);
				}
			}
		}
		if (PSmelter.bar.HasSecondary()) {
			if (Inventory.contains(PSmelter.bar.getPrimaryOreID())
					&& Inventory.contains(PSmelter.bar.getSecondaryOreID())) {
				Bank.close();
				Sleep.waitFor(new Condition() {

					@Override
					public boolean validate() {
						return !Bank.isOpen();
					}
				}, 4000);
			}
		} else {
			if (Inventory.contains(PSmelter.bar.getPrimaryOreID())) {
				Bank.close();
				Sleep.waitFor(new Condition() {

					@Override
					public boolean validate() {
						return !Bank.isOpen();
					}
				}, 4000);
			}
		}
	}
}

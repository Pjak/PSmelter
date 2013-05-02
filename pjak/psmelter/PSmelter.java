package pjak.psmelter;

import java.awt.Graphics;
import java.util.ArrayList;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.util.Random;
import pjak.psmelter.enums.Bars;
import pjak.psmelter.enums.Locations;
import pjak.psmelter.nodes.Banking;
import pjak.psmelter.nodes.Smelting;
import pjak.psmelter.nodes.WalkToBank;
import pjak.psmelter.nodes.WalkToSmelt;
import pjak.psmelter.util.Paint;
import pjak.psmelter.util.PSmelterGUI;

@Manifest(authors = { "Pjak" }, name = "pSmelter", description = "Smelts ore at edgeville or Al-Kharid!", version = 1.1)
public class PSmelter extends ActiveScript implements PaintListener,
		MessageListener {

	public static boolean guiWait = true;
	public static ArrayList<Node> nodeCollection = new ArrayList<>();
	public Paint p;

	public static Bars bar;
	public static Locations location;

	public void onStart() {
		PSmelterGUI gui = new PSmelterGUI();
		gui.setVisible(true);
		p = new Paint();
		p.start();
		while (guiWait) {
			Task.sleep(100, 200);
		}
		if (Game.isLoggedIn()) {
			Paint.startTime = System.currentTimeMillis();
			Paint.startLevel = Skills.getLevel(Skills.SMITHING);	
			Paint.log("Script started!");
			Paint.log("Bar chosen: " + bar.getName());
			nodeCollection.add(new Banking());
			nodeCollection.add(new WalkToBank());
			nodeCollection.add(new Smelting());
			nodeCollection.add(new WalkToSmelt());
		}

	}

	@Override
	public int loop() {
		if (Game.isLoggedIn()) {
				for (Node node : nodeCollection) {
					if (node.activate()) {
						node.execute();
					}
				}
		}

		return Random.nextInt(50, 100);
	}


	@Override
	public void onRepaint(Graphics g) {
		g.equals(Paint.getPaint(g));
	}

	@Override
	public void messageReceived(MessageEvent e) {
		String message = e.getMessage();
		if (message.contains("You retrieve a bar of "
				+ bar.getName().toLowerCase())) {
			Paint.barsMade++;
		}
	}

}

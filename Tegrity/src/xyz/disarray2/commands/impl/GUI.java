package xyz.disarray2.commands.impl;

import xyz.disarray2.Launcher;
import xyz.disarray2.tegrity.TegrityGUI;

public class GUI extends Command {
	public GUI(String name, String description) {
		super(name, description);
	}

	public void run(String[] args) {
		if(Launcher.TEGRITY.getDb() == null) {
			System.out.println("Error - Please select or create a database first.");
			return;
		}
		
		// TODO: Turn the adder into editor and eventually standalone app
		new TegrityGUI().run();
	}
}

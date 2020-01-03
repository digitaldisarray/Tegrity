package xyz.disarray.tegrity.commands.impl;

import xyz.disarray.Launcher;
import xyz.disarray.tegrity.Standalone3;

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
		new Standalone3().run();
	}
}

package xyz.disarray2.commands.impl;

import xyz.disarray2.AdderGUI;
import xyz.disarray2.Launcher;

public class FileAdder extends Command {
	public FileAdder(String name, String description) {
		super(name, description);
	}

	public void run() {
		// TODO: Test if a database is selected to modify
		if(!Launcher.TEGRITY.hasDb()) {
			System.out.println("Error - Please select or create a database first.");
			return;
		}
		
		// TODO: Turn the adder into editor and eventually standalone app
		
		new AdderGUI().run();
	}
}

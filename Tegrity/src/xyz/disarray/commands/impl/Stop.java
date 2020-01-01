package xyz.disarray.commands.impl;

import xyz.disarray.Launcher;

public class Stop extends Command {

	public Stop(String name, String description) {
		super(name, description);
	}
	
	public void run(String[] args) {
		System.out.println("Shutting down, goodbye...");
		Launcher.TEGRITY.stop();
	}
}

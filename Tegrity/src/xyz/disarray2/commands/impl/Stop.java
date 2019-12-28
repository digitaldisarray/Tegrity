package xyz.disarray2.commands.impl;

import xyz.disarray2.Launcher;

public class Stop extends Command {

	public Stop(String name, String description) {
		super(name, description);
	}
	
	public void run(String[] args) {
		System.out.println("Shutting down, goodbye...");
		Launcher.TEGRITY.stop();
	}
}

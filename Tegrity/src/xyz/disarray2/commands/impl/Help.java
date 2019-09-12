package xyz.disarray2.commands.impl;

import xyz.disarray2.Launcher;

public class Help extends Command {

	public Help(String name, String description) {
		super(name, description);
	}

	public void run() {
		System.out.println("Commands: ");
		for (Command c : Launcher.TEGRITY.getCommandManager().getCommands()) {
			System.out.println(c.toString());
		}
		System.out.println();
	}

}

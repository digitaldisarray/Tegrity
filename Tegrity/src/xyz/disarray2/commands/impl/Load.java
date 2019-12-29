package xyz.disarray2.commands.impl;

import xyz.disarray2.Launcher;

public class Load extends Command {

	public Load(String name, String description) {
		super(name, description);
	}

	public void run(String[] args) {
		// TODO: Parse and check args? Maybe just put all command execution in try catch
		// for improper command usage.

		if (!Launcher.TEGRITY.getDb().load(args[0])) {
			System.out.println("Failed to load database. File not found.");
		}
	}

}

package xyz.disarray2;

import java.util.Scanner;

import xyz.disarray2.commands.CommandManager;

public class Tegrity implements Runnable {

	private Database db;
	private CommandManager commandManager;
	private Scanner in;

	private boolean running;

	@Override
	public void run() {
		// Setup
		running = true;
		commandManager = new CommandManager();
		in = new Scanner(System.in);
		System.out.println("Tegrity - v" + Launcher.VERSION);
		
		// Loop
		String input;
		while (running) {
			System.out.print("> ");
			input = in.nextLine();

			try {
				commandManager.runCommand(input.substring(0, input.indexOf(' ')),
						input.substring(input.indexOf(' ') + 1, input.length()).split(" "));
			} catch (StringIndexOutOfBoundsException e) {
				// TODO: Get good at programming :/
				commandManager.runCommand(input.substring(0, input.length()), null);
			}
		}

		in.close();
		System.exit(0);
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}

	public boolean hasDb() {
		if (db == null)
			return false;
		else
			return true;
	}

	public void setDb(Database db) {
		this.db = db;
	}

	public void stop() {
		running = false;
	}

}

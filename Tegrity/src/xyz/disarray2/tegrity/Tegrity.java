package xyz.disarray2.tegrity;

import java.util.Scanner;

import xyz.disarray2.Launcher;
import xyz.disarray2.commands.CommandManager;
import xyz.disarray2.tegrity.db.Database;

public class Tegrity implements Runnable {

	private Database db;
	private CommandManager commandManager;
	private Scanner in;

	private boolean running;

	@Override
	public void run() {
		// Setup
		running = true;
		db = new Database();
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

	public Database getDb() {
		return db;
	}
	
	public void stop() {
		running = false;
	}

}

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

		// Loop
		String input;
		while (running) {
			System.out.print("> ");
			input = in.nextLine();

			commandManager.runCommand(input, null);
		}

		in.close();
		System.exit(0);
	}

	public CommandManager getCommandManager() {
		return commandManager;
	}
	
	public boolean hasDb() {
		if(db.equals(null))
			return false;
		else
			return true;
	}
	
	public void setDb(Database db) {
		
	}

}

package xyz.disarray2;

import xyz.disarray2.commands.CommandManager;

public class Tegrity implements Runnable {

	private CommandManager commandManager;
	
	@Override
	public void run() {
		// Setup
		commandManager = new CommandManager();
		
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}

}

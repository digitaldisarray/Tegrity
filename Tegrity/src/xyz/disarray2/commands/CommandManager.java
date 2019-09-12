package xyz.disarray2.commands;

import java.util.ArrayList;

import xyz.disarray2.commands.impl.Command;
import xyz.disarray2.commands.impl.Help;

public class CommandManager {
	ArrayList<Command> commands = new ArrayList<>();
	
	public CommandManager() {
		// Init all commands here
		commands.add(new Help("help", "Displays a list of commands"));
	}
	
	public ArrayList<Command> getCommands() {
		return commands;
	}
	
	public void runCommand(String name, String[] args) {
		for(Command c : commands) {
			if(c.getName().equalsIgnoreCase(name)) {
				c.run();
			}
		}
	}
	
}

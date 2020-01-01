package xyz.disarray.commands;

import java.util.ArrayList;

import xyz.disarray.commands.impl.Command;
import xyz.disarray.commands.impl.GUI;
import xyz.disarray.commands.impl.Help;
import xyz.disarray.commands.impl.Load;
import xyz.disarray.commands.impl.Stop;

public class CommandManager {
	private ArrayList<Command> commands = new ArrayList<>();

	public CommandManager() {
		// Init all commands here
		commands.add(new Help("help", "Displays a list of commands"));
		commands.add(new Load("load <path>", "Loads a database from disk"));
		commands.add(new GUI("gui", "Launches the database editor gui"));
		commands.add(new Stop("stop", "Stops Tegrity"));
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public void runCommand(String name, String[] args) {
		/*
		System.out.println("Name: " + name);
		System.out.print("Args: ");
		for(String s : args) {
			System.out.print(s + " ");
		}
		System.out.println();
		*/
		
		boolean hasRun = false;
		for (Command c : commands) {
			if (c.getName().startsWith(name.toLowerCase())) {
				c.run(args);
				hasRun = true;
			}
		}

		if (!hasRun) {
			System.out.println("Command not found.");
		}
	}

}

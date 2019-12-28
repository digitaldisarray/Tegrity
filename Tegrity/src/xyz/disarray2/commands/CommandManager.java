package xyz.disarray2.commands;

import java.util.ArrayList;

import xyz.disarray2.commands.impl.Command;
import xyz.disarray2.commands.impl.FileAdder;
import xyz.disarray2.commands.impl.Help;

public class CommandManager {
	private ArrayList<Command> commands = new ArrayList<>();

	public CommandManager() {
		// Init all commands here
		commands.add(new Help("help", "Displays a list of commands"));
		commands.add(new FileAdder("addergui", "Opens the file adder gui"));
	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public void runCommand(String name, String[] args) {
		boolean hasRun = false;
		for (Command c : commands) {
			if (c.getName().equalsIgnoreCase(name)) {
				hasRun = true;

				if (c.HAS_ARGS)
					c.run(args);
				else
					c.run();
			}
		}

		if (!hasRun) {
			System.out.println("Command not found.");
		}
	}

}

package xyz.disarray.tegrity.commands.impl;

public class Command {

	private String name, description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String toString() {
		return name + " - " + description;
	}
	
	public void run(String[] args) {
		System.out.println("Call for command \"" + name + "\" recived with args.");
	}

	public String getName() {
		return name;
	}

}

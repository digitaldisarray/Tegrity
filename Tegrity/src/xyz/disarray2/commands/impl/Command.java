package xyz.disarray2.commands.impl;

public class Command {

	public final boolean HAS_ARGS = false;
	private String name, description;
	
	public Command(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String toString() {
		return name + " - " + description;
	}

	public void run() {
		System.out.println("Call for command \"" + name + "\" recived.");
	}
	
	public void run(String[] args) {
		System.out.println("Call for command \"" + name + "\" recived with args.");
	}

	public String getName() {
		return name;
	}

}

package xyz.disarray;

import java.util.Scanner;

public class Tegrity implements Runnable {
	
	private void command(String input) {
		input = input.toLowerCase();
		
		if(input.equals("help")) {
			System.out.println("Commands:\n"
					+ "help - Display a list of commands\n"
					+ "new <db name> - Create a new file database");
		}
		
		if(input.startsWith("new")) {
			FileAdding fAdder = new FileAdding();
			fAdder.run();
			fAdder.setName(input.split(" ")[1]);
		}
	}

	@Override
	public void run() {
		Scanner in = new Scanner(System.in);
		String input;
		
		while(true) {
			System.out.print("> ");
			input = in.next();
			
			command(input);
		}
	}
	
}

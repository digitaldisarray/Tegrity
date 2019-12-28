package xyz.disarray2.commands.impl;

import xyz.disarray2.Database;
import xyz.disarray2.Launcher;

public class Load extends Command {

	public final boolean HAS_ARGS = true;
	
	public Load(String name, String description) {
		super(name, description);
	}
	
	public void run(String[] args) {
		Database loaded = new Database();
		
		// TODO: Parse and check args
		
		if(loaded.load(args[0])) {
			Launcher.TEGRITY.setDb(loaded);
		}
	}

}

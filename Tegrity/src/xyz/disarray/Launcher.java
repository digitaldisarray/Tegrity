package xyz.disarray;

import xyz.disarray.tegrity.Standalone;

public class Launcher {
	public static Standalone STANDALONE;
	public final static double VERSION = 0.1;
	
	public static void main(String[] args) {
		
		if(args.length == 0) {
			STANDALONE = new Standalone();
			STANDALONE.run();
		} else {
			// Parse args
			// Do stuff
		}
		
	}
}

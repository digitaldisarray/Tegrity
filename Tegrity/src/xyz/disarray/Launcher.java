package xyz.disarray;

import xyz.disarray.tegrity.Standalone;

public class Launcher {
	public static Standalone STANDALONE;
	public final static double VERSION = 0.1;

	public static void main(String[] args) {

		if (args.length == 0) {
			STANDALONE = new Standalone();
			STANDALONE.run();
		} else {
			// Parse args

			// Variables to store assigned tasks
			boolean loadDb = false;
			String db = null;
			boolean check = false;

			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("-db") && i < args.length - 1) {
					loadDb = true;
					db = args[i + 1];
				}
			}

			// Do stuff

		}

	}
}

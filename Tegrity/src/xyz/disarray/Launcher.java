package xyz.disarray;

import xyz.disarray.tegrity.Standalone3;

public class Launcher {
	public static Standalone3 TEGRITY;
	public final static double VERSION = 0.1;
	
	public static void main(String[] args) {
		TEGRITY = new Standalone3();
		TEGRITY.run();
	}
}

package xyz.disarray;

import xyz.disarray.tegrity.Standalone;

public class Launcher {
	public static Standalone TEGRITY;
	public final static double VERSION = 0.1;
	
	public static void main(String[] args) {
		TEGRITY = new Standalone();
		TEGRITY.run();
	}
}

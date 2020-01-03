package xyz.disarray;

import xyz.disarray.tegrity.Standalone3;
import xyz.disarray.tegrity.Tegrity;

public class Launcher {
	public static Tegrity TEGRITY;
	public final static double VERSION = 0.1;
	
	public static void main(String[] args) {
		//TEGRITY = new Tegrity();
		//TEGRITY.run();
		
		new Standalone3().run();
	}
}

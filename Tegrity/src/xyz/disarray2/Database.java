package xyz.disarray2;

import java.io.File;

public class Database {

	private File database;

	/**
	 * Loads a given database file.
	 * 
	 * @param path - Path to the database file EX: "C:\database.json"
	 * @return Returns true if it was loaded, false if it does not exist.
	 */
	public boolean load(String path) {
		database = new File(path);

		if (!database.exists())
			return false;
		else
			return true;
	}

}

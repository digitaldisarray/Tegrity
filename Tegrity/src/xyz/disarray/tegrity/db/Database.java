package xyz.disarray.tegrity.db;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import xyz.disarray.tegrity.HashingUtils;

public class Database {

	private File databaseFile;
	private ArrayList<DBFile> files = new ArrayList<>();

	/**
	 * Loads a given database file.
	 * 
	 * @param path - Path to the database file EX: "C:\database.json"
	 * @return Returns true if it was loaded, false if it does not exist.
	 */
	public boolean load(String path) {
		databaseFile = new File(path);
		System.out.println(databaseFile.getAbsolutePath());

		if (!databaseFile.exists())
			return false;

		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(databaseFile.getAbsolutePath()));

			JSONArray jsonFile = (JSONArray) obj;
			for (Object o : jsonFile.toArray()) {
				JSONObject jo = (JSONObject) o;
				files.add(new DBFile((String) jo.get("path"), (String) jo.get("md5"), (String) jo.get("sha1")));
			}
		} catch (IOException | ParseException e1) {
			return false;
		}

		return true;
	}
	
	public void save() {
		JSONArray dbArr = new JSONArray();
		
		for(DBFile f : files) {
			JSONObject fileObj = new JSONObject();
			fileObj.put("path", f.getPath());
			fileObj.put("md5", f.getMD5());
			fileObj.put("sha1", f.getSha1());
			dbArr.add(fileObj);
		}
		
		try {
			FileWriter file = new FileWriter(databaseFile.getAbsolutePath());
			file.write(dbArr.toJSONString());
			file.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void addFile(File file) {
		files.add(new DBFile(file.getAbsolutePath(), HashingUtils.getMD5(file), HashingUtils.getSHA1(file)));
	}
	
	public void removeFile(int index) {
		files.remove(index);
	}

	public String[] getFilePaths() {
		String[] paths = new String[files.size()];
		for (int i = 0; i < files.size(); i++) {
			paths[i] = files.get(i).getPath();
		}
		return paths;
	}
}

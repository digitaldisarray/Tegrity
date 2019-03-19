package xyz.disarray.gui;

import javafx.beans.property.SimpleStringProperty;

public class InputTableItem {
	private SimpleStringProperty name;
	private SimpleStringProperty shaHash;
	private SimpleStringProperty aesHash;
	private SimpleStringProperty location;
	
	/**
	 * Create a new input table item for adding to the table
	 * 
	 * @param name - File name
	 * @param shaHash - File sha hash
	 * @param aesHash - File aes hash
	 * @param location - File location
	 */
	public InputTableItem(String name, String shaHash, String aesHash, String location) {
		this.name = new SimpleStringProperty(name);
		this.shaHash = new SimpleStringProperty(shaHash);
		this.aesHash = new SimpleStringProperty(aesHash);
		this.location = new SimpleStringProperty(location);
	}
	
	public SimpleStringProperty getName() {
		return name;
	}
	
	public SimpleStringProperty getShaHash() {
		return shaHash;
	}
	
	public SimpleStringProperty getAesHash() {
		return aesHash;
	}
	
	public SimpleStringProperty getLocation() {
		return location;
	}
}

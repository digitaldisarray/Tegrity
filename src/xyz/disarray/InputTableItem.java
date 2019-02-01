package xyz.disarray;

import javafx.beans.property.SimpleStringProperty;

public class InputTableItem {
	private SimpleStringProperty name;
	private SimpleStringProperty shaHash;
	private SimpleStringProperty aesHash;
	private SimpleStringProperty location;
	
	public InputTableItem(String name, String shaHash, String aesHash, String location) {
		this.name = new SimpleStringProperty(name);
		this.shaHash = new SimpleStringProperty(shaHash);
		this.aesHash = new SimpleStringProperty(aesHash);
		this.location = location;
	}
	
	public String getName() {
		return name;
	}
	
	public String getShaHash() {
		return shaHash;
	}
	
	public String getAesHash() {
		return aesHash;
	}
	
	public String getLocation() {
		return location;
	}
}

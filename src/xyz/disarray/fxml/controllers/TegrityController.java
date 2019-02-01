package xyz.disarray.fxml.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import xyz.disarray.InputTableItem;

public class TegrityController {

	@FXML
	private Button runButton;

	@FXML
	private MenuItem importButton;

	@FXML
	private MenuItem exportButton;

	@FXML
	private ProgressBar progressBar;

	@FXML
	private TableView<InputTableItem> fileTable;

	@FXML
	private Button addFileButton;

	@FXML
	private Button retargetButton;

	@FXML
	private Label progressLabel;

	@FXML
	private TableView<String> resultTabe;

	@FXML
	private Label currentFileLabel;

	@FXML
	private Spinner<?> threadSpinner;

	// File choosing objects
	FileChooser chooser;
	java.util.List<File> filesToAdd;
	ArrayList<String> hashes;
	MessageDigest digest;

	@FXML
	void addFile(ActionEvent event) {
		// Open file selector
		chooser = new FileChooser();
		chooser.setTitle("Choose files to add...");
		chooser.getExtensionFilters().add(new ExtensionFilter("All Files", "*.*"));
		filesToAdd = chooser.showOpenMultipleDialog(null);

		// Make sure the user selected files
		if (filesToAdd.isEmpty()) {
			return;
		}

		// Compute hashes
		hashes = new ArrayList<>();
		for (File file : filesToAdd) {
			try {
				// TODO: Add both md5 and sha, maybe let user choose which they want
				digest = MessageDigest.getInstance("MD5");
				// digest = MessageDigest.getInstance("SHA-1");

				hashes.add(getFileChecksum(digest, file));
			} catch (NoSuchAlgorithmException | IOException e) {
				// TODO: Add error notifications
			}
		}

		// Debug print
		for(String hash : hashes) {
			System.out.println(hash);
		}
		
		// Check that file(s) aren't already on table
		

		// Add file(s) to table
		

		// Cleanup
		chooser = null;
	}

	@FXML
	void retarget(ActionEvent event) {
		// TODO: Make this button only selectable when items in the table are selected
	}

	@FXML
	void run(ActionEvent event) {
		// Check we have files to scan

		// Check we have at least 1 thread

		// Check we don't have too many threads
	}

	private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
		// Get file input stream for reading the file content
		FileInputStream fis = new FileInputStream(file);

		// Create byte array to read data in chunks
		byte[] byteArray = new byte[1024];
		int bytesCount = 0;

		// Read file data and update in message digest
		while ((bytesCount = fis.read(byteArray)) != -1) {
			digest.update(byteArray, 0, bytesCount);
		}

		fis.close();

		byte[] bytes = digest.digest();

		// Convert bytes[] (decimal) to hexidecimal
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}
}

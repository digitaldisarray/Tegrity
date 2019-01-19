package xyz.disarray.fxml.controllers;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;

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
	private TableView<?> fileTable;

	@FXML
	private Button addFileButton;

	@FXML
	private Button retargetButton;

	@FXML
	private Label progressLabel;

	@FXML
	private TableView<?> resultTabe;

	@FXML
	private Label currentFileLabel;

	@FXML
	private Spinner<?> threadSpinner;

	// File choosing objects
	JFileChooser chooser;
	ArrayList<File> filesToAdd;
	
	@FXML
	void addFile(ActionEvent event) {
		// Open file selector
		filesToAdd = new ArrayList<>();
		chooser = new JFileChooser();
		
		
		// Check file(s) were selected
		
		// Check that file(s) aren't already on table
		
		// Compute hash
		
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
}

package xyz.disarray;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class Tegrity extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane pane = (Pane) FXMLLoader.load(getClass().getClassLoader().getResource("./xyz/disarray/fxml/Tegrity.fxml"));
			primaryStage.setTitle("Tegrity");
			primaryStage.setScene(new Scene(pane));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// Run the gui
		// launch(args);
		
		// Launch the console version
		
		
		
	}
}

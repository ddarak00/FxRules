package application;

//import java.net.*;
//import java.io.*;
import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import application.FxRulesWindowController;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		
			application.FxRulesWindowController.connectToDatabase();
			 
			Parent root = FXMLLoader.load(getClass().getResource("/FXRulesWindow.fxml"));
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Rules for Life Database");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			//application.FxRulesWindowController.initializeList();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

package application;
	
//import com.sun.prism.paint.Color;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.BorderPane;
import javafx.geometry.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,300);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("Rules for Life");
			
			
			TextField b = new TextField("Enter Life Rule");
			b.setMaxWidth(900);
			BorderPane.setMargin(b, new Insets(50));
			BorderPane.setAlignment(b, Pos.CENTER);
			root.setTop(b);
			primaryStage.setScene(scene);
			primaryStage.show(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}

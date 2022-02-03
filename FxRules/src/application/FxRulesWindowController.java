package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class FxRulesWindowController {
	
	@FXML
	private TextField ruleTextField;
	private static Connection connection;
	@FXML
	private ListView<String> listOfRules;
	
	//private ObservableList<String> observableList = FXCollections.observableArrayList();
	
	@FXML
	public void buttonClicked() {
		String newRule = ruleTextField.getText();
		
		try {
			String dbCommand = "INSERT INTO Statement VALUES(?)";
			PreparedStatement stmt = connection.prepareStatement(dbCommand);
			
			/*Statement st = connection.createStatement();
			st.executeUpdate("INSERT INTO Statement " + 
	                "VALUES ('" + newRule + "')");
			st.close();*/
			stmt.setString(1,newRule);
			int affectedRows = stmt.executeUpdate();
			//stmt.close();
			System.out.println(ruleTextField.getText());
			
			/////////////////////////////////
			String sql = "select * from Statement";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			//listOfRules.getItems().add(newRule);
			while(result.next())
			{
				System.out.println(result.getString(1));
				listOfRules.getItems().add(result.getString(1));
			}
			////////////////////////////////
		}
		catch(SQLException e) {
			System.out.println("Error making/sending statement");
			e.printStackTrace();
		}
		
	}
	
	public static void connectToDatabase() {
		
		String connectionString = "jdbc:sqlserver://DANIEL-PC\\SQLEXPRESS;databaseName=lifeRules;integratedSecurity=true";
		try 
		{
		    connection = DriverManager.getConnection(connectionString);
			System.out.println("Connection Established");
		}
	
		catch(SQLException e) {
			System.out.println("Error connecting to SQL");
			e.printStackTrace();
		}
	}
	
	public void initialize()
	{
		try
		{
			String sql = "select * from Statement";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				listOfRules.getItems().add(result.getString(1));
				System.out.println(result.getString(1));
				
			}
		}
		catch(SQLException e) {
			System.out.println("Error initializing ListView");
			e.printStackTrace();
		}
	}
}

package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;

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
	
	
	@FXML
	public void buttonClicked() {
		String newRule = ruleTextField.getText();
		ruleTextField.clear();
		
		try {
			String dbCommand = "INSERT INTO ruleList VALUES(?,?,?)";
			PreparedStatement stmt = connection.prepareStatement(dbCommand);
			
			stmt.setString(1,newRule);
			stmt.setString(2,"reason");
			stmt.setString(3, null);
			int affectedRows = stmt.executeUpdate();
			//stmt.close();
			System.out.println(ruleTextField.getText());
			
			refreshListView();
		}
		catch(SQLException e) {
			System.out.println("Error making/sending statement");
			e.printStackTrace();
		}
	}
	
	public void refreshListView()
	{
		try {
			String sql = "select * from ruleList";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			listOfRules.getItems().clear();
			while(result.next())
			{
				listOfRules.getItems().add(result.getString(1));
				System.out.println(result.getString(1));
				
			}
			System.out.println("Listview Refreshed.");
		}
		catch(SQLException e) {
			System.out.println("Error refreshing Listview.");
			e.printStackTrace();
		}
	}
	
	@FXML
	public void deleteButtonClicked() {
		System.out.println("Delete Button Pushed.");
		String theRule;
		ResultSetMetaData rsMetaData = null;
		if((theRule = listOfRules.getSelectionModel().getSelectedItem()) != null)
		{			
			try {
			//Creating a Statement object
		      Statement stmt = connection.createStatement();
		      //Retrieving the data
		      ResultSet rs = stmt.executeQuery("select * from ruleList");
		      //Retrieving the ResultSetMetadata object
		      rsMetaData = rs.getMetaData();
		      System.out.println("List of column names in the current table: ");
		      //Retrieving the list of column names
		      int count = rsMetaData.getColumnCount();
		      for(int i = 1; i<=count; i++) {
		         System.out.println(rsMetaData.getColumnName(i));
		      }
			}
			catch(SQLException e) {
				System.out.println("List Columns failed");
				e.printStackTrace();
			}
///////////////////////////			
			System.out.println(theRule);

			try
			{
				String sql ="DELETE FROM ruleList WHERE theRule= '" + theRule + "'";
				System.out.println(sql);
				Statement deleteStatement = connection.createStatement();
				//int x = deleteStatement.executeUpdate("DELETE FROM nameTable WHERE Name= 'Donato'"); WORKED
				int x = deleteStatement.executeUpdate(sql);
				//x.close();
				refreshListView();

			}
			catch(SQLException e) {
				System.out.println("Delete Statement failed");
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Nothing Selected");
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
		refreshListView();
	}
}

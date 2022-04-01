package com.tuttur.database;



import  java.sql.Connection;			
import  java.sql.ResultSet;
import java.sql.Statement;
import  java.util.Properties;
import  java.sql.DriverManager;		


public class DatabasePage {


	Properties props = new Properties();	
	
    String dbUrl = "jdbc:postgresql://10.49.4.153:5432/tuttur";						
	String username = "tutturapplication";		
	String password = "";
		
	public void executeQuery(String dbQuery) {
			
		try {
			
			Connection dbConnect = DriverManager.getConnection(dbUrl,username,password);
			Class.forName("org.postgresql.Driver");
			Statement statement = dbConnect.createStatement();
			Thread.sleep(4000);
			ResultSet result = statement.executeQuery(dbQuery);
			
			while (result.next()) {
		        System.out.println(result.getInt(1) + " " + result.getString(2));
		    }
		} 
		catch (Exception e) {
			
			e.printStackTrace();
		}
	  }	
	public String getValidationCode(String dbQuery) {
		
		int code = 0;
		
		try {
			Connection dbConnect = DriverManager.getConnection(dbUrl,username,password);
			Class.forName("org.postgresql.Driver");
			Statement statement = dbConnect.createStatement();
			Thread.sleep(4000);
			ResultSet result = statement.executeQuery(dbQuery);
			Thread.sleep(3000);
			
			while (result.next()) {
		        code = result.getInt(1);
		    }
		} 
		
		catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return Integer.toString(code);	
	  }

	public int getValidationCodeInt (String dbQuery, int colomnIndex) {

		int code = 0;

		try {
			Connection dbConnect = DriverManager.getConnection(dbUrl,username,password);
			Class.forName("org.postgresql.Driver");
			Statement statement = dbConnect.createStatement();
			Thread.sleep(4000);
			ResultSet result = statement.executeQuery(dbQuery);
			Thread.sleep(3000);

			while (result.next()) {
				code = result.getInt(colomnIndex);
			}
		}

		catch (Exception e) {

			e.printStackTrace();
		}

		return code;
	}
    }
	
	




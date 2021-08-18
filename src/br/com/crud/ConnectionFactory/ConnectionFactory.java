package br.com.crud.ConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	// MySQL Username
	private static final String USERNAME = "root";
	
	// DB Password
	private static final String PASSWORD = "chris123";
	
	// DB Path, port, DB Name
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sampledb";
	
	
	/*
	 * Connection with DB 
	 */
	
	public static Connection createConnectiontoMySQL() throws ClassNotFoundException, SQLException {
		// Loads the class by JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		// Creates a connection with the DB
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		// Recovery connection with the DB
		Connection con = createConnectiontoMySQL();
		
		// Checks if the connection is NULL
		if(con != null) {
			System.out.println("Connection made!");
			con.close();
		}
	}
}

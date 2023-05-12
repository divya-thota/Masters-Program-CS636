package cs636.vinylstation.config;


//You need to import the java.sql package to use JDBC
import java.sql.*;


public class dbConfig {
	 

	 
	 private final String  user  = "divya";
	 private final String  password ="divya02";
	 private final String connStr =  "jdbc:oracle:thin:@localhost:1521:dbs3";
	 private   Connection conn;
	 
	 
	 public void init() throws SQLException {
		 
		 // Load the Oracle JDBC driver
		 DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		 
		 conn = DriverManager.getConnection (connStr,
				   user, password);
		
		 conn.setAutoCommit(true); 
		 System.out.println ("connected.");
		 
	 }
	 
	 public Connection getConnection() throws SQLException {
	 
		return conn;
	
	 
	 }
	 
	 public void closeConnection() {
		 
			 try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		 
		 }


	 
	 

}

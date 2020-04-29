package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	
	public static Connection conn = null;
	
	public static Connection getConnection() {
		
		if(conn == null) {
			
			String endpoint = "...";
			String username = "...";
			String password = "...";
			String driver = "oracle.jdbc.driver.OracleDriver";
			
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(endpoint, username, password);
				System.out.println("The connection is successful");
				return conn;
			} catch (SQLException e) {
				System.out.println("An error occurred while connecting with databse");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		} 
		else {
			return conn;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		Connection conn1 = getConnection();
		
		System.out.println(conn1);

}
}

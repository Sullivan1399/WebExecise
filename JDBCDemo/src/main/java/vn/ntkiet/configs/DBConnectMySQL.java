package vn.ntkiet.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectMySQL {
	private static String USERNAME = "root";
	private static String PASSWORD = "1234";
	private static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static String URL = "jdbc:mysql://localhost:3306/jdbcdemo";

	public static Connection getDatabaseConnection() throws SQLException, Exception {

		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		try (Connection conn = getDatabaseConnection()) {
			if (conn != null) {
				System.out.println("Successfully connected to the database.");
			} else {
				System.out.println("Connection failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

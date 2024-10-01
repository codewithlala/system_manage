package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import pojo.UserPojo;

public class UserDao {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/showroom_management";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "";

	public boolean saveUser(UserPojo user) {
		boolean isSaved = false;
		String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Set parameters for the SQL query
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());

			// Execute the query
			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				isSaved = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return isSaved;
	}
}

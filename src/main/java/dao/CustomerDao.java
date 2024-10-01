package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pojo.Customer;

public class CustomerDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/showroom_management";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";

	private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
	private static final String SELECT_CUSTOMER_BY_ID = "SELECT id, name, email, phone, address FROM customer WHERE id = ?";
	private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM customer";
	private static final String DELETE_CUSTOMER_SQL = "DELETE FROM customer WHERE id = ?";
	private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// Method to add a new customer
	public boolean addCustomer(Customer customer) {
//        String sql = "INSERT INTO Customer (name, email, phone, address) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement(INSERT_CUSTOMER_SQL, Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, customer.getName());
			pstmt.setString(2, customer.getEmail());
			pstmt.setString(3, customer.getPhone());
			pstmt.setString(4, customer.getAddress());
			pstmt.executeUpdate();

			// Retrieve the generated ID
			try (ResultSet rs = pstmt.getGeneratedKeys()) {
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					customer.setId(generatedId); // Set the generated ID
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Method to get a customer by ID
	public Customer getCustomerById(int id) {
		Customer customer = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("address"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	// Method to get all customers
	public List<Customer> getAllCustomers() {
		List<Customer> customers = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
						rs.getString("phone"), rs.getString("address"));
				customers.add(customer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}

	// Method to delete a customer
	public boolean deleteCustomer(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER_SQL)) {
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowDeleted;
	}

	// Method to update a customer
	public boolean updateCustomer(Customer customer) {
		boolean rowUpdated = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getEmail());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setString(4, customer.getAddress());
			preparedStatement.setInt(5, customer.getId());
			rowUpdated = preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}

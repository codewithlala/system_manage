package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.SalesPojo;

public class SalesDao {

	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/showroom_management";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}

	public void addSale(SalesPojo sale) throws Exception {
		String sql = "INSERT INTO sales (car_id, customer_id, sale_date, sale_amount) VALUES (?, ?, ?, ?)";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, sale.getCarId());
			ps.setInt(2, sale.getCustomerId());
			ps.setDate(3, (Date) sale.getSaleDate());
			ps.setFloat(4, sale.getSaleAmount());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace(); // Handle exception
		}
	}

	public List<SalesPojo> getAllSales() throws Exception {
		List<SalesPojo> sales = new ArrayList<SalesPojo>();
		String sql = "SELECT * FROM sales";
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				SalesPojo sale = new SalesPojo();
				sale.setSaleId(rs.getInt("sale_id"));
				sale.setCarId(rs.getInt("car_id"));
				sale.setCustomerId(rs.getInt("customer_id"));
				sale.setSaleDate(rs.getDate("sale_date"));
				sale.setSaleAmount(rs.getFloat("sale_amount"));
				sales.add(sale);
			}
		}
		return sales;
	}

//    public void deleteSale(int saleId) throws Exception {
//        String sql = "DELETE FROM sales WHERE sale_id = ?";
//        try (Connection con = getConnection();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//            ps.setInt(1, saleId);
//            ps.executeUpdate();
//        }
//    }

	public Object listAllSales() {
		// TODO Auto-generated method stub
		return null;
	}

//	public SalesPojo getSaleById(int saleId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	// Method to get a sale by its ID
	public SalesPojo getSaleById(int saleId) {
		String sql = "SELECT * FROM sales WHERE sale_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_URL, JDBC_PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, saleId);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					SalesPojo sale = new SalesPojo();
					sale.setSaleId(resultSet.getInt("sale_id"));
					sale.setCarId(resultSet.getInt("car_id"));
					sale.setCustomerId(resultSet.getInt("customer_id"));
					sale.setSaleDate(resultSet.getDate("sale_date"));
					sale.setSaleAmount(resultSet.getFloat("sale_amount"));
					return sale;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Method to delete a sale
	public boolean deleteSale(int saleId) {
		String sql = "DELETE FROM sales WHERE sale_id = ?";
		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_URL, JDBC_PASSWORD);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, saleId);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSale(SalesPojo sale) {
		boolean rowUpdated = false;

		String sql = "UPDATE sales SET car_id = ?, customer_id = ?, sale_date = ?, sale_amount = ? WHERE sale_id = ?";

		try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setInt(1, sale.getCarId());
			pstmt.setInt(2, sale.getCustomerId());
			pstmt.setDate(3, (Date) sale.getSaleDate()); // Ensure sale.getSaleDate() returns a java.sql.Date
			pstmt.setDouble(4, sale.getSaleAmount());
			pstmt.setInt(5, sale.getSaleId());

			// Execute update query
			rowUpdated = pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return rowUpdated;
	}

}

package controller;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SalesUpdateController")
public class SalesUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/showroom_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        int carId = Integer.parseInt(request.getParameter("carId"));
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Date saleDate = Date.valueOf(request.getParameter("saleDate"));
        BigDecimal saleAmount = new BigDecimal(request.getParameter("saleAmount"));
        
        // Call the method to update sale record
        boolean updateSuccessful = updateSaleRecord(saleId, carId, customerId, saleDate, saleAmount);
        
        // Redirect to managesales.jsp with appropriate message
        if (updateSuccessful) {
            response.sendRedirect("managesales.jsp?message=Sale updated successfully");
        } else {
            response.sendRedirect("managesales.jsp?message=Failed to update sale");
        }
    }

    // Method to update sale record in the database
    private boolean updateSaleRecord(int saleId, int carId, int customerId, Date saleDate, BigDecimal saleAmount) {
        String sql = "UPDATE sales SET car_id = ?, customer_id = ?, sale_date = ?, sale_amount = ? WHERE sale_id = ?";
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            // Set parameters
            preparedStatement.setInt(1, carId);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setDate(3, saleDate);
            preparedStatement.setBigDecimal(4, saleAmount);
            preparedStatement.setInt(5, saleId);
            
            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

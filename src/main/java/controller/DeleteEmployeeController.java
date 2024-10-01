package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteEmployeeController")
public class DeleteEmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/showroom_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve employee ID from the request
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        
        // Call the method to delete the employee record
        boolean deleteSuccessful = deleteEmployeeRecord(employeeId);
        
        // Redirect to manageemployees.jsp with appropriate message
        if (deleteSuccessful) {
            response.sendRedirect("manageemployees.jsp?message=Employee deleted successfully");
        } else {
            response.sendRedirect("manageemployees.jsp?message=Failed to delete employee");
        }
    }

    // Method to delete employee record from the database
    private boolean deleteEmployeeRecord(int employeeId) {
        String sql = "DELETE FROM employees WHERE employee_id = ?";
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            // Set parameters
            preparedStatement.setInt(1, employeeId);
            
            // Execute delete
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; // Return true if the delete was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

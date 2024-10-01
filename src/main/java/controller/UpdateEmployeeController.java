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

@WebServlet("/UpdateEmployeeController")
public class UpdateEmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/showroom_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String department = request.getParameter("department");
        Date hireDate = Date.valueOf(request.getParameter("hireDate"));
        BigDecimal salary = new BigDecimal(request.getParameter("salary"));
        
        // Call the method to update employee record
        boolean updateSuccessful = updateEmployeeRecord(employeeId, firstName, lastName, department, hireDate, salary);
        
        // Redirect to manageemployees.jsp with appropriate message
        if (updateSuccessful) {
            response.sendRedirect("manageemployees.jsp?message=Employee updated successfully");
        } else {
            response.sendRedirect("manageemployees.jsp?message=Failed to update employee");
        }
    }

    // Method to update employee record in the database
    private boolean updateEmployeeRecord(int employeeId, String firstName, String lastName, String department, Date hireDate, BigDecimal salary) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, department = ?, hire_date = ?, salary = ? WHERE employee_id = ?";
        
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            // Set parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, department);
            preparedStatement.setDate(4, hireDate);
            preparedStatement.setBigDecimal(5, salary);
            preparedStatement.setInt(6, employeeId);
            
            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();
            
            return rowsAffected > 0; // Return true if the update was successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

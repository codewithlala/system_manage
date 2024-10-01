package dao;
import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import pojo.EmployeePojo;

public class EmployeeDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/showroom_management";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // Replace with your actual password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL JDBC driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean addEmployee(EmployeePojo employee) {
        String sql = "INSERT INTO employees (first_name, last_name, department, hire_date, salary) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getDepartment());
            statement.setDate(4, employee.getHireDate());
            statement.setDouble(5, employee.getSalary());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<EmployeePojo> getAllEmployees() {
        List<EmployeePojo> employeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                EmployeePojo employee = new EmployeePojo();
                employee.setEmployeeId(resultSet.getInt("employee_id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setDepartment(resultSet.getString("department"));
                employee.setHireDate(resultSet.getDate("hire_date")); // Convert java.sql.Date to java.util.Date
                employee.setSalary(resultSet.getDouble("salary"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Log the exception
        }
        return employeeList;
    }

	

    public boolean updateEmployee(EmployeePojo employee) {
        boolean rowUpdated = false;
       String UPDATE_EMPLOYEE_SQL= "UPDATE employees SET first_name = ?, last_name = ?, department = ?, hire_date = ?, salary = ? WHERE employee_id = ?";
        // Try-with-resources to ensure that resources are closed properly
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD); // Method to get a database connection
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL)) {

            // Set parameters for the SQL statement
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getDepartment());
            preparedStatement.setDate(4, employee.getHireDate());
            preparedStatement.setDouble(5, employee.getSalary());
            preparedStatement.setInt(6, employee.getEmployeeId());

            // Execute the update
            int rowsAffected = preparedStatement.executeUpdate();
            rowUpdated = (rowsAffected > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowUpdated;
    }
}

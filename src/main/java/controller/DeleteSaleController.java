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

@WebServlet("/DeleteSaleController")
public class DeleteSaleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get saleId parameter from the request
		String saleIdParam = request.getParameter("saleId");
		int saleId = Integer.parseInt(saleIdParam);

		// Database connection details
		String dbURL = "jdbc:mysql://localhost:3306/showroom_management";
		String dbUser = "root";
		String dbPassword = "";

		// SQL DELETE query
		String sql = "DELETE FROM sales WHERE sale_id = ?";

		try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Set the saleId in the query
			preparedStatement.setInt(1, saleId);

			// Execute the delete statement
			int rowsAffected = preparedStatement.executeUpdate();

			// If the deletion was successful, redirect to managesales.jsp
			if (rowsAffected > 0) {
				response.sendRedirect("managesales.jsp?status=success");
			} else {
				// If no rows were affected, something went wrong
				response.sendRedirect("managesales.jsp?status=error");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			response.sendRedirect("managesales.jsp?status=error");
		}
	}
}

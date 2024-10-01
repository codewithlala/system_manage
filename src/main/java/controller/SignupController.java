package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import pojo.UserPojo;

@WebServlet("/SignupController")
public class SignupController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve form data from the request
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");

		// Validate password confirmation
		if (!password.equals(repassword)) {
			request.setAttribute("errorMessage", "Passwords do not match!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		// Create a UserPojo object
		UserPojo user = new UserPojo(username, email, password);

		// Create an instance of UserDao and save the user
		UserDao userDao = new UserDao();
		boolean isUserSaved = userDao.saveUser(user);

		// Handle the result of the save operation
		if (isUserSaved) {
			request.setAttribute("successMessage", "Signup successful! You can now login.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			request.setAttribute("errorMessage", "Signup failed! Please try again.");
			request.getRequestDispatcher("signup.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}
}

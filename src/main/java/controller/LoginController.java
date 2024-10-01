package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.LoginDao;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve login credentials from the request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create an instance of LoginDao to validate the user
        LoginDao loginDao = new LoginDao();
        System.out.printf(username,password);
        boolean isValidUser = loginDao.validateUser(username, password);

        if (isValidUser) {
            // Successful login, redirect to home page
            response.sendRedirect("admin_dashboard.jsp");
        } else {
            // Failed login, set error message and forward to login page
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("logout.jsp").forward(request, response);
        }
    }
}

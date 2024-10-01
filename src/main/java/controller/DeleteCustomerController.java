package controller;

import dao.CustomerDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCustomerController")
public class DeleteCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get customer ID from the form
        int customerId = Integer.parseInt(request.getParameter("customerId"));

        // Initialize DAO and delete customer
        CustomerDao customerDao = new CustomerDao();
        boolean success = customerDao.deleteCustomer(customerId);

        // Redirect with a message
        if (success) {
            response.sendRedirect("managecustomers.jsp?message=Customer%20deleted%20successfully");
        } else {
            response.sendRedirect("managecustomers.jsp?message=Failed%20to%20delete%20customer");
        }
    }
}

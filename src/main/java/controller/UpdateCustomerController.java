package controller;

import dao.CustomerDao;
import pojo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateCustomerController")
public class UpdateCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get updated customer details from the form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Create a Customer object with updated details
        Customer customer = new Customer(id, name, email, phone, address);

        // Initialize DAO and update customer
        CustomerDao customerDao = new CustomerDao();
        boolean success = customerDao.updateCustomer(customer);

        // Redirect with a message
        if (success) {
            response.sendRedirect("managecustomers.jsp?message=Customer%20updated%20successfully");
        } else {
            response.sendRedirect("managecustomers.jsp?message=Failed%20to%20update%20customer");
        }
    }
}

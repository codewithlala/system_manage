package controller;

import dao.CustomerDao;
import pojo.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddCustomerController")
public class AddCustomerController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Handles POST request for adding a customer
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve customer details from the request
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        // Create a new Customer object
        Customer newCustomer = new Customer();
        newCustomer.setName(name);
        newCustomer.setEmail(email);
        newCustomer.setPhone(phone);
        newCustomer.setAddress(address);

        // Create a CustomerDao instance and add the new customer to the database
        CustomerDao customerDao = new CustomerDao();
        boolean isAdded = customerDao.addCustomer(newCustomer);

        // Redirect to manage customers page with a success or error message
        if (isAdded) {
            response.sendRedirect("managecustomers.jsp?message=Customer added successfully.");
        } else {
            response.sendRedirect("managecustomers.jsp?message=Error adding customer.");
        }
    }
}

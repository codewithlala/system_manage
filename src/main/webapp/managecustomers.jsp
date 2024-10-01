<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="pojo.Customer" %>
<%@ page import="dao.CustomerDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Customers - Car Showroom Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .footer {
            background-color: #f8f9fa;
            padding: 1rem 0;
            border-top: 1px solid #dee2e6;
            margin-top: 2rem;
        }
        .feature-card {
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
            padding: 1.5rem;
            text-align: center;
        }
        .feature-card:hover {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
    </style>
</head>
<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="homepage.jsp">
            <img src="https://cdn3.f-cdn.com/contestentries/2134350/52484024/62f53400da1bc_thumb900.jpg" alt="Car Showroom Logo" style="max-height: 60px; border-radius: 50%;">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link btn btn-danger text-white" href="logout.jsp">Logout</a>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container mt-5">
        <h2>Manage Customers</h2>

        <!-- Display success or error messages -->
        <%
            String message = request.getParameter("message");
            if (message != null && !message.isEmpty()) {
        %>
        <div class="alert alert-info" role="alert">
            <%= message %>
        </div>
        <%
            }
        %>

        <!-- Customer Table -->
        <%
            // Initialize DAO and fetch customers
            CustomerDao customerDao = new CustomerDao();
            List<Customer> customers = customerDao.getAllCustomers();
        %>

        <!-- Show the customer table if there are customers -->
        <%
            if (customers != null && !customers.isEmpty()) {
        %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Customer customer : customers) {
                %>
                <tr>
                    <td><%= customer.getId() %></td>
                    <td><%= customer.getName() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getPhone() %></td>
                    <td><%= customer.getAddress() %></td>
                    <td>
                        <!-- View button -->
                        <a href="viewcustomer.jsp?id=<%= customer.getId() %>" class="btn btn-info btn-sm">View</a>
                        <!-- Delete form -->
                        <form action="DeleteCustomerController" method="post" style="display:inline;">
                            <input type="hidden" name="customerId" value="<%= customer.getId() %>">
                            <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                        </form>
                        <!-- Update button -->
                        <a href="updatecustomer.jsp?id=<%= customer.getId() %>" class="btn btn-warning btn-sm">Update</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <div class="alert alert-warning" role="alert">
            No customers found.
        </div>
        <%
            }
        %>

        <!-- Add new customer button -->
        <a href="addcustomer.jsp" class="btn btn-primary">Add New Customer</a>
    </div>

    <!-- Footer -->
    <footer class="footer text-center">
        <div class="container">
            <p class="mb-0">&copy; 2024 Car Showroom Management System. All rights reserved.</p>
            <p>123 Car Street, Auto City, AC 12345</p>
            <p>Email: <a href="mailto:info@carshowroom.com">info@carshowroom.com</a> | Phone: +1 (123) 456-7890</p>
        </div>
    </footer>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

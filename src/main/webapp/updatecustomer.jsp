<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="pojo.Customer" %>
<%@ page import="dao.CustomerDao" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Customer - Car Showroom Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .footer {
            background-color: #f8f9fa;
            padding: 1rem 0;
            border-top: 1px solid #dee2e6;
            margin-top: 2rem;
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
        <h2>Update Customer</h2>

        <%
            // Get customer ID from request
            int customerId = Integer.parseInt(request.getParameter("id"));
            CustomerDao customerDao = new CustomerDao();
            Customer customer = customerDao.getCustomerById(customerId);
        %>

        <!-- Display customer details form -->
        <%
            if (customer != null) {
        %>
        <form action="UpdateCustomerController" method="post">
            <input type="hidden" name="id" value="<%= customer.getId() %>">
            <div class="form-group">
                <label for="name">Name</label>
                <input type="text" class="form-control" id="name" name="name" value="<%= customer.getName() %>" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="<%= customer.getEmail() %>" required>
            </div>
            <div class="form-group">
                <label for="phone">Phone</label>
                <input type="text" class="form-control" id="phone" name="phone" value="<%= customer.getPhone() %>" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" id="address" name="address" value="<%= customer.getAddress() %>" required>
            </div>
            <button type="submit" class="btn btn-primary">Update Customer</button>
        </form>
        <%
            } else {
        %>
        <div class="alert alert-danger" role="alert">
            Customer not found.
        </div>
        <%
            }
        %>
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

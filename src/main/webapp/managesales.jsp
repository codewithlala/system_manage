<%@ page import="java.sql.*"%>
<%@ page import="pojo.SalesPojo"%>
<%@ page import="dao.SalesDao"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Sales - Car Showroom Management System</title>
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
    body {
        background-color: #e9ecef;
        margin: 0;
        padding: 0;
        display: flex;
        flex-direction: column;
        min-height: 100vh;
        font-family: 'Arial', sans-serif;
    }

    .navbar {
        margin-bottom: 30px;
        background-color: #343a40;
        padding: 15px 20px;
    }

    .navbar-brand {
        font-size: 24px;
        font-weight: bold;
        color: #fff;
    }

    .navbar .nav-link {
        color: #f8f9fa;
        margin-left: 15px;
        font-size: 18px;
    }

    .navbar .nav-link:hover {
        color: #ffc107;
    }

    .card {
        margin-bottom: 20px;
        border: none;
        border-radius: 12px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.15);
        background-color: #ffffff;
    }

    .card-title {
        color: #343a40;
        font-size: 20px;
        font-weight: bold;
        margin-bottom: 20px;
    }

    .btn-primary, .btn-warning, .btn-danger {
        font-size: 14px;
        padding: 8px 15px;
        border-radius: 8px;
    }

    .btn-primary {
        background-color: #007bff;
        border: none;
    }

    .btn-primary:hover {
        background-color: #0056b3;
    }

    .btn-danger {
        background-color: #e74c3c;
    }

    .btn-danger:hover {
        background-color: #c0392b;
    }

    .table {
        margin-top: 20px;
    }

    .table th {
        background-color: #343a40;
        color: #fff;
        text-align: center;
        padding: 15px;
    }

    .table td {
        text-align: center;
        vertical-align: middle;
        padding: 12px;
    }

    .footer {
        background-color: #343a40;
        color: white;
        padding: 10px 0;
        width: 100%;
        text-align: center;
        font-size: 14px;
        margin-top: auto;
    }

    .footer a {
        color: #ffffff;
        text-decoration: none;
    }

    .footer a:hover {
        text-decoration: underline;
    }

    .container {
        max-width: 1200px;
        margin: 0 auto;
    }

    form .form-control {
        border-radius: 8px;
        padding: 10px;
        border: 1px solid #ced4da;
    }

    .btn {
        transition: all 0.3s;
    }

    .btn-success {
        background-color: #28a745;
        border: none;
    }

    .btn-success:hover {
        background-color: #218838;
    }
</style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark">
        <a class="navbar-brand" href="admin_dashboard.jsp">Car Showroom Management</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarNav" aria-controls="navbarNav"
            aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link"
                    href="admin_dashboard.jsp">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="managecars.jsp">Manage Cars</a></li>
                <li class="nav-item"><a class="nav-link" href="managecustomers.jsp">Manage Customers</a></li>
                <li class="nav-item"><a class="nav-link active" href="managesales.jsp">Manage Sales</a></li>
                <li class="nav-item"><a class="nav-link" href="manageemployees.jsp">Manage Employees</a></li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </nav>

    <!-- Main Content -->
    <div class="container">
        <h2 class="text-center mb-4">Manage Sales</h2>

        <!-- Form to Add New Sales Record -->
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Add New Sale</h5>
                <form action="SalesController" method="post">
                    <div class="form-group">
                        <label for="carId">Car ID</label>
                        <input type="text" class="form-control" id="carId" name="carId" required>
                    </div>
                    <div class="form-group">
                        <label for="customerId">Customer ID</label>
                        <input type="text" class="form-control" id="customerId" name="customerId" required>
                    </div>
                    <div class="form-group">
                        <label for="saleDate">Sale Date</label>
                        <input type="date" class="form-control" id="saleDate" name="saleDate" required>
                    </div>
                    <div class="form-group">
                        <label for="saleAmount">Sale Amount</label>
                        <input type="number" class="form-control" id="saleAmount" name="saleAmount"
                            step="0.01" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add Sale</button>
                </form>
            </div>
        </div>

        <!-- Table to Display Sales Records -->
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Sales Records</h5>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr>
                            <th>Sale ID</th>
                            <th>Car ID</th>
                            <th>Customer ID</th>
                            <th>Sale Date</th>
                            <th>Sale Amount</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        SalesDao salesDao = new SalesDao();
                        List<SalesPojo> salesList = salesDao.getAllSales();

                        for (SalesPojo sale : salesList) {
                        %>
                        <tr>
                            <td><%= sale.getSaleId() %></td>
                            <td><%= sale.getCarId() %></td>
                            <td><%= sale.getCustomerId() %></td>
                            <td><%= sale.getSaleDate() %></td>
                            <td><%= sale.getSaleAmount() %></td>
                            <td>
                                <form action="editSale.jsp" method="post" style="display: inline;">
                                    <input type="hidden" name="saleId" value="<%= sale.getSaleId() %>">
                                    <button type="submit" name="action" value="Edit" class="btn btn-success btn-sm">Edit</button>
                                </form>
                                <!-- Delete Button -->
                                <form action="DeleteSaleController" method="post" style="display: inline;">
                                    <input type="hidden" name="saleId" value="<%= sale.getSaleId() %>">
                                    <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                </form>
                            </td>
                        </tr>
                        <%
                        }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2024 Car Showroom Management System. All rights reserved.</p>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page import="java.sql.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Sale - Car Showroom Management System</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f1f5f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        .navbar {
            margin-bottom: 30px;
        }

        .card {
            margin-bottom: 20px;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }

        .btn-warning {
            background-color: #ffc107;
            border: none;
        }

        .btn-warning:hover {
            background-color: #e0a800;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        .table {
            margin-top: 20px;
        }

        .footer {
            background-color: #007bff;
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
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="admin_dashboard.jsp">Car Showroom Management</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item"><a class="nav-link" href="admin_dashboard.jsp">Dashboard</a></li>
                <li class="nav-item"><a class="nav-link" href="managecars.jsp">Manage Cars</a></li>
                <li class="nav-item"><a class="nav-link" href="managecustomers.jsp">Manage Customers</a></li>
                <li class="nav-item"><a class="nav-link" href="managesales.jsp">Manage Sales</a></li>
                <li class="nav-item"><a class="nav-link" href="manageemployees.jsp">Manage Employees</a></li>
            </ul>
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </nav>
    
    <!-- Main Content -->
    <div class="container">
        <h2 class="text-center mb-4">Edit Sale</h2>
        
        
<%
    // Load the MySQL JDBC Driver
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
        throw new SQLException("MySQL Driver not found!");
    }

    // Initialize variables
    int saleId = Integer.parseInt(request.getParameter("saleId"));
    int carId = 0;
    int customerId = 0;
    Date saleDate = null;
    BigDecimal saleAmount = null;

    String sql = "SELECT * FROM sales WHERE sale_id = ?";
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/showroom_management", "root", "");
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        preparedStatement.setInt(1, saleId); // You may replace 1 with a dynamic sale ID
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                saleId = resultSet.getInt("sale_id");
                carId = resultSet.getInt("car_id");
                customerId = resultSet.getInt("customer_id");
                saleDate = resultSet.getDate("sale_date");
                saleAmount = resultSet.getBigDecimal("sale_amount");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>



        <!-- Form to Edit Sale Record -->
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Edit Sale</h5>
                <form action="SalesUpdateController" method="post">
                    <input type="hidden" name="saleId" value="<%= saleId %>">
                    <div class="form-group">
                        <label for="carId">Car ID</label>
                        <input type="text" class="form-control" id="carId" name="carId" value="<%= carId %>" required>
                    </div>
                    <div class="form-group">
                        <label for="customerId">Customer ID</label>
                        <input type="text" class="form-control" id="customerId" name="customerId" value="<%= customerId %>" required>
                    </div>
                    <div class="form-group">
                        <label for="saleDate">Sale Date</label>
                        <input type="date" class="form-control" id="saleDate" name="saleDate" 
                               value="<%= (saleDate != null) ? new SimpleDateFormat("yyyy-MM-dd").format(saleDate) : "" %>" required>
                    </div>
                    <div class="form-group">
                        <label for="saleAmount">Sale Amount</label>
                        <input type="number" class="form-control" id="saleAmount" name="saleAmount" value="<%= saleAmount %>" step="0.01" required>
                    </div>
                    <button type="submit" class="btn btn-primary" name="action" value="update">Update Sale</button>
                </form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <p>&copy; 2024 Car Showroom Management System. All rights reserved.</p>
    </footer>
</body>
</html>

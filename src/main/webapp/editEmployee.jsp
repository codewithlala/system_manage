<%@ page import="java.sql.*, java.math.BigDecimal"%>
<%@ page import="pojo.EmployeePojo"%>
<%@ page import="dao.EmployeeDao"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Employee - Car Showroom Management System</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .navbar {
            margin-bottom: 20px;
            border-radius: 0;
        }

        .card {
            margin-bottom: 20px;
            border: 1px solid #dee2e6;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        .card:hover {
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.2);
            transform: translateY(-5px);
        }

        .card-body {
            padding: 20px;
        }

        .btn-primary {
            background-color: #007bff;
            border: 1px solid #007bff;
        }

        .btn-primary:hover {
            background-color: #0056b3;
            border: 1px solid #0056b3;
        }

        .footer {
            background-color: #343a40;
            color: #ffffff;
            padding: 15px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            margin-top: 20px;
        }

        .footer p {
            margin: 0;
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
        <a class="navbar-brand" href="#">Car Showroom Management</a>
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
        <h2 class="text-center mb-4">Edit Employee</h2>

        <!-- Form to Edit Employee -->
        <div class="card">
            <div class="card-body">
                <%
                    String employeeIdStr = request.getParameter("employeeId");
                    int employeeId = Integer.parseInt(employeeIdStr);

                    String dbUrl = "jdbc:mysql://localhost:3306/showroom_management";
                    String dbUser = "root";
                    String dbPassword = "";
                    String sql = "SELECT * FROM employees WHERE employee_id = ?";

                    try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
                            PreparedStatement statement = connection.prepareStatement(sql)) {

                        statement.setInt(1, employeeId);
                        ResultSet resultSet = statement.executeQuery();

                        if (resultSet.next()) {
                            String firstName = resultSet.getString("first_name");
                            String lastName = resultSet.getString("last_name");
                            String department = resultSet.getString("department");
                            java.sql.Date hireDate = resultSet.getDate("hire_date");
                            BigDecimal salary = resultSet.getBigDecimal("salary");
                %>
                <form action="UpdateEmployeeController" method="post">
                    <input type="hidden" name="employeeId" value="<%= employeeId %>">
                    <div class="form-group">
                        <label for="firstName">First Name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="<%= firstName %>" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last Name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="<%= lastName %>" required>
                    </div>
                    <div class="form-group">
                        <label for="department">Department</label>
                        <input type="text" class="form-control" id="department" name="department" value="<%= department %>" required>
                    </div>
                    <div class="form-group">
                        <label for="hireDate">Hire Date</label>
                        <input type="date" class="form-control" id="hireDate" name="hireDate" value="<%= hireDate.toLocalDate() %>" required>
                    </div>
                    <div class="form-group">
                        <label for="salary">Salary</label>
                        <input type="number" class="form-control" id="salary" name="salary" step="0.01" value="<%= salary %>" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Update Employee</button>
                </form>
                <%
                        } else {
                            out.println("<p class='text-danger'>Employee not found.</p>");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        out.println("<p class='text-danger'>Error retrieving employee data.</p>");
                    }
                %>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <div class="footer">
        <p>&copy; 2024 Car Showroom Management System. All rights reserved.</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

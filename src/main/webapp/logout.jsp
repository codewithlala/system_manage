<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Logout - Car Showroom Management System</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="homepage.jsp">
            <img src="https://cdn3.f-cdn.com/contestentries/2134350/52484024/62f53400da1bc_thumb900.jpg" alt="Car Showroom Logo">
        </a>
    </nav>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-12 text-center">
                <!-- Logout Process -->
                <%
                    // Invalidate the session to log out the user
                    session.invalidate();
                %>
                <h2>You have been logged out successfully.</h2>
                <p>You will be redirected to the login page shortly.</p>
                <a href="login.jsp" class="btn btn-primary">Go to Login Page</a>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer text-center mt-5">
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

<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard - Car Showroom Management System</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
<style>
body {
	background-color: #f1f5f9;
}

.navbar {
	margin-bottom: 30px;
}

.card {
	margin-bottom: 20px;
	border: none;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	transition: transform 0.3s ease;
}

.card:hover {
	transform: scale(1.05);
}

.card-icon {
	font-size: 3rem;
	color: #007bff;
	margin-bottom: 20px;
}

.card-body {
	text-align: center;
}

.btn-primary {
	background-color: #007bff;
	border: none;
}

.btn-primary:hover {
	background-color: #0056b3;
}

.footer {
	background-color: #007bff;
	color: white;
	padding: 10px 0;
	position: fixed;
	bottom: 0;
	width: 100%;
	text-align: center;
}

.logout-btn {
	margin-top: 30px;
	font-size: 1.1rem;
	border-radius: 20px;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="#">Car Showroom Management</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="admin_dashboard.jsp">Dashboard</a></li>
				<li class="nav-item"><a class="nav-link" href="managecars.jsp">Manage
						Cars</a></li>
				<li class="nav-item"><a class="nav-link" href="managecustomers.jsp">Manage
						Customers</a></li>
				<li class="nav-item"><a class="nav-link" href="managesales.jsp">Manage
						Sales</a></li>
				<li class="nav-item"><a class="nav-link"
					href="manageemployees.jsp">Manage Employees</a></li>
			</ul>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="logout.jsp">Logout</a>
				</li>
			</ul>
		</div>
	</nav>

	<!-- Main Content -->
	<div class="container">
		<h2 class="text-center mb-4">Dashboard</h2>
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-car card-icon"></i>
						<h5 class="card-title">Manage Cars</h5>
						<p class="card-text">Add, update, view, or delete car details.</p>
						<a href="managecars.jsp" class="btn btn-primary">Go to Manage
							Cars</a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-users card-icon"></i>
						<h5 class="card-title">Manage Customers</h5>
						<p class="card-text">Add, update, or delete Customers accounts.</p>
						<a href="managecustomers.jsp" class="btn btn-primary">Go to
							Manage Users</a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-dollar-sign card-icon"></i>
						<h5 class="card-title">Manage Sales</h5>
						<p class="card-text">Track and manage sales transactions.</p>
						<a href="managesales.jsp" class="btn btn-primary">Go to Manage
							Sales</a>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-calendar-alt card-icon"></i>
						<h5 class="card-title">Manage Employees</h5>
						<p class="card-text">Schedule and manage Employees
							appointments.</p>
						<a href="manageemployees.jsp" class="btn btn-primary">Go to
							Manage Appointments</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Footer -->
	<footer class="footer">
		<p>&copy; 2024 Car Showroom Management System. All rights
			reserved.</p>
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

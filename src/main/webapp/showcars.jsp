<%@ page import="java.util.List"%>
<%@ page import="pojo.CarPojo"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Show Cars - Car Showroom Management System</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.table-container {
	margin-top: 2rem;
}
</style>
</head>

<body>

	<!-- Navbar -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="homepage.jsp"> <img
			src="https://cdn3.f-cdn.com/contestentries/2134350/52484024/62f53400da1bc_thumb900.jpg"
			alt="Car Showroom Logo" style="max-height: 60px;">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a
					class="nav-link btn btn-danger text-white" href="logout.jsp">Logout</a>
				</li>
			</ul>
		</div>
	</nav>

	<div class="container table-container">
		<h2 class="text-center">All Cars</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Car ID</th>
					<th>Model</th>
					<th>Brand</th>
					<th>Price</th>
					<th>Year</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<CarPojo> carList = (List<CarPojo>) request.getAttribute("carList");
				if (carList != null && !carList.isEmpty()) {
					for (CarPojo car : carList) {
				%>
				<tr>
					<td><%=car.getCarID()%></td>
					<td><%=car.getCarModel()%></td>
					<td><%=car.getCarBrand()%></td>
					<td><%=car.getCarPrice()%></td>
					<td><%=car.getCarYear()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5" class="text-center">No cars available</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<!-- Footer -->
	<footer class="footer text-center mt-5">
		<div class="container">
			<p class="mb-0">&copy; 2024 Car Showroom Management System. All
				rights reserved.</p>
			<p>123 Car Street, Auto City, AC 12345</p>
			<p>
				Email: <a href="mailto:info@carshowroom.com">info@carshowroom.com</a>
				| Phone: +1 (123) 456-7890
			</p>
		</div>
	</footer>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>

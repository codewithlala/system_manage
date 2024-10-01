<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Manage Cars - Car Showroom Management System</title>
<!-- Bootstrap CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.form-container {
	margin-top: 5rem;
}

.card {
	margin-bottom: r1em;
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

	<div class="container">
		<!-- Add Car Form -->
		<div class="card form-container">
			<div class="card-header">Add New Car</div>
			<div class="card-body">
				<form action="InsertCarController" method="post">

					<div class="form-group">
						<label for="carModel">Car Model</label> <input type="text"
							class="form-control" id="carModel" name="carModel" required>
					</div>
					<div class="form-group">
						<label for="carBrand">Car Brand</label> <input type="text"
							class="form-control" id="carBrand" name="carBrand" required>
					</div>
					<div class="form-group">
						<label for="carPrice">Price</label> <input type="number"
							class="form-control" id="carPrice" name="carPrice" required>
					</div>
					<div class="form-group">
						<label for="carYear">Year</label> <input type="number"
							class="form-control" id="carYear" name="carYear" required>
					</div>
					<button type="submit" class="btn btn-primary">Add Car</button>
				</form>
			</div>
		</div>

		<!-- Update Car Form -->
		<div class="card form-container">
			<div class="card-header">Update Car Details</div>
			<div class="card-body">
				<form action="UpdateCarController" method="post">
					<div class="form-group">
						<label for="carId">Car ID</label> <input type="number"
							class="form-control" id="carId" name="carId" required>
					</div>
					<div class="form-group">
						<label for="updateCarModel">New Car Model</label> <input
							type="text" class="form-control" id="updateCarModel"
							name="updateCarModel">
					</div>
					<div class="form-group">
						<label for="updateCarBrand">New Car Brand</label> <input
							type="text" class="form-control" id="updateCarBrand"
							name="updateCarBrand">
					</div>
					<div class="form-group">
						<label for="updateCarPrice">New Price</label> <input type="number"
							class="form-control" id="updateCarPrice" name="updateCarPrice">
					</div>
					<div class="form-group">
						<label for="updateCarYear">New Year</label> <input type="number"
							class="form-control" id="updateCarYear" name="updateCarYear">
					</div>
					<button type="submit" class="btn btn-primary">Update Car</button>
				</form>
			</div>
		</div>

		<!-- Delete Car Form -->
		<div class="card form-container">
			<div class="card-header">Delete Car</div>
			<div class="card-body">
				<form action="DeleteCarController" method="post">
					<div class="form-group">
						<label for="deleteCarId">Car ID</label> <input type="number"
							class="form-control" id="deleteCarId" name="carId" required>
					</div>
					<button type="submit" class="btn btn-danger">Delete Car</button>
				</form>
			</div>
		</div>

		<!-- Show Cars -->
		<div class="card form-container">
			<div class="card-header">Show All Cars</div>
			<div class="card-body">
				<form action="ShowCarsController" method="get">
					<button type="submit" class="btn btn-info">Show Cars</button>
				</form>
				<!-- Table to display cars will be populated by the ShowCarsController -->
			</div>
		</div>
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

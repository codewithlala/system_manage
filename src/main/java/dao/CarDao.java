package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pojo.CarPojo;

public class CarDao {

	private static final String URL = "jdbc:mysql://localhost:3306/showroom_management"; // Update with your database
																							// URL
	private static final String USER = "root"; // Update with your database username
	private static final String PASSWORD = ""; // Update with your database password

	 public boolean addCar(CarPojo car) {
	        String sql = "INSERT INTO cars (car_model, car_brand, car_price, car_year) VALUES (?, ?, ?, ?)";
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is loaded
	            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
	                 PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

	                // Set parameters for the prepared statement
	                preparedStatement.setString(1, car.getCarModel());
	                preparedStatement.setString(2, car.getCarBrand());
	                preparedStatement.setDouble(3, car.getCarPrice());
	                preparedStatement.setInt(4, car.getCarYear());

	                // Execute the insert operation
	                int rowsAffected = preparedStatement.executeUpdate();

	                // Retrieve generated car_id if insert was successful
	                if (rowsAffected > 0) {
	                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	                        if (generatedKeys.next()) {
	                            int newCarId = generatedKeys.getInt(1);
	                            car.setCarID(newCarId); // Set the generated car_id to the CarPojo object
	                            return true; // Return true if a row was inserted successfully
	                        }
	                    }
	                }
	                return false; // Return false if no rows were affected or no ID was retrieved
	            } catch (SQLException e) {
	                e.printStackTrace(); // Log the exception
	                return false; // Return false if an error occurred
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace(); // Log the exception
	            return false; // Return false if the driver class is not found
	        }
	    }
	

	// Method to update a car in the database
	public boolean updateCar(CarPojo car) {
		String sql = "UPDATE cars SET car_model = ?, car_brand = ?, car_price = ?, car_year = ? WHERE car_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is loaded
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setString(1, car.getCarModel());
				preparedStatement.setString(2, car.getCarBrand());
				preparedStatement.setDouble(3, car.getCarPrice());
				preparedStatement.setInt(4, car.getCarYear());

				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected > 0; // Return true if a row was updated successfully

			} catch (SQLException e) {
				e.printStackTrace(); // Log the exception
				return false; // Return false if an error occurred
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception
			return false; // Return false if the driver class is not found
		}
	}

	// Method to delete a car from the database
	public boolean deleteCar(int carId) {
		String sql = "DELETE FROM cars WHERE car_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is loaded
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				preparedStatement.setInt(1, carId); // Set the car ID parameter for the SQL query

				int rowsAffected = preparedStatement.executeUpdate();
				return rowsAffected > 0; // Return true if a row was deleted successfully

			} catch (SQLException e) {
				e.printStackTrace(); // Log the exception
				return false; // Return false if an error occurred
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception
			return false; // Return false if the driver class is not found
		}
	}

	// Method to get all cars from the database
	public List<CarPojo> getAllCars() {
		List<CarPojo> cars = new ArrayList<>();
		String sql = "SELECT * FROM cars";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // Ensure the driver is loaded
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
					PreparedStatement preparedStatement = connection.prepareStatement(sql);
					ResultSet resultSet = preparedStatement.executeQuery()) {

				while (resultSet.next()) {
					int carId = resultSet.getInt("car_id");
					String carModel = resultSet.getString("car_model");
					String carBrand = resultSet.getString("car_brand");
					double carPrice = resultSet.getDouble("car_price");
					int carYear = resultSet.getInt("car_year");

					CarPojo car = new CarPojo(carId,carModel, carBrand, carPrice, carYear);
					cars.add(car);
				}
			} catch (SQLException e) {
				e.printStackTrace(); // Log the exception
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace(); // Log the exception
		}
		return cars;
	}
}

package controller;

import dao.CarDao;
import pojo.CarPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateCarController")
public class UpdateCarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarDao carDao;

	@Override
	public void init() throws ServletException {
		// Initialize the DAO object
		carDao = new CarDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve form parameters
//        int carId = Integer.parseInt(request.getParameter("carId"));
		String carModel = request.getParameter("updateCarModel");
		String carBrand = request.getParameter("updateCarBrand");
		double carPrice = Double.parseDouble(request.getParameter("updateCarPrice"));
		int carYear = Integer.parseInt(request.getParameter("updateCarYear"));

		// Create a new CarPojo object with updated details
		CarPojo car = new CarPojo();
		
		car.setCarModel(carModel);
		car.setCarBrand(carBrand);
		car.setCarPrice(carPrice);
		car.setCarYear(carYear);
		// Update the car in the database
		boolean isUpdated = carDao.updateCar(car);

		// Forward to the appropriate page based on success/failure
		if (isUpdated) {
			request.setAttribute("message", "Car updated successfully!");
		} else {
			request.setAttribute("message", "Failed to update car.");
		}

		request.getRequestDispatcher("managecars.jsp").forward(request, response);
	}
}

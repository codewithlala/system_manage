package controller;

import dao.CarDao;
import pojo.CarPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/InsertCarController")
public class InsertCarController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CarDao insertCarDao;

	@Override
	public void init() throws ServletException {
		// Initialize the DAO object
		insertCarDao = new CarDao();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve form parameters

		String carModel = request.getParameter("carModel");
		String carBrand = request.getParameter("carBrand");
		double carPrice = Double.parseDouble(request.getParameter("carPrice"));
		int carYear = Integer.parseInt(request.getParameter("carYear"));

		// Create a new InsertCarPojo object
		CarPojo car = new CarPojo();
		car.setCarModel(carModel);
		car.setCarBrand(carBrand);
		car.setCarPrice(carPrice);
		car.setCarYear(carYear);

		// Add the car to the database
		boolean isAdded = insertCarDao.addCar(car);
		System.out.println(isAdded);
		// Forward to the appropriate page based on success/failure
		if (isAdded) {
			request.setAttribute("message", "Car added successfully!");
		} else {
			request.setAttribute("message", "Failed to add car.");
		}

		request.getRequestDispatcher("managecars.jsp").forward(request, response);
	}
}

package controller;

import dao.CarDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteCarController")
public class DeleteCarController extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CarDao carDao;

    @Override
    public void init() {
        carDao = new CarDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String carIdString = request.getParameter("carId");
        if (carIdString != null && !carIdString.isEmpty()) {
            try {
                int carId = Integer.parseInt(carIdString);
                boolean isDeleted = carDao.deleteCar(carId);
                if (isDeleted) {
                    request.setAttribute("message", "Car deleted successfully!");
                } else {
                    request.setAttribute("message", "Failed to delete car.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "Invalid car ID format.");
            }
        } else {
            request.setAttribute("message", "Car ID is required.");
        }
        request.getRequestDispatcher("managecars.jsp").forward(request, response);
    }
}


 package controller;

import dao.CarDao;
import pojo.CarPojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowCarsController")
public class ShowCarController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDao carDao = new CarDao();
        List<CarPojo> cars = carDao.getAllCars();
        
        // Set the car list as an attribute in the request
        request.setAttribute("carList", cars);

        // Forward to the JSP page for displaying cars
        request.getRequestDispatcher("showcars.jsp").forward(request, response);
    }
}

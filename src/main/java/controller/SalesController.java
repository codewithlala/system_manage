package controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SalesDao;
import pojo.SalesPojo;

@WebServlet("/SalesController")
public class SalesController extends HttpServlet {

	private SalesDao salesDao = new SalesDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int carId = Integer.parseInt(req.getParameter("carId"));
			int customerId = Integer.parseInt(req.getParameter("customerId"));
			Date saleDate = Date.valueOf(req.getParameter("saleDate"));
			float saleAmount = Float.parseFloat(req.getParameter("saleAmount"));

			SalesPojo sale = new SalesPojo();
			sale.setCarId(carId);
			sale.setCustomerId(customerId);
			sale.setSaleAmount(saleAmount);
			sale.setSaleDate(saleDate);
			sale.setSaleId(customerId);

			salesDao.addSale(sale);

			resp.sendRedirect("managesales.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("error.jsp");
		}
	}

	

		

}
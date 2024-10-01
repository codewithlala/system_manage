package controller;

import dao.EmployeeDao;
import pojo.EmployeePojo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/EmployeeController")
public class EmployeeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String department = request.getParameter("department");
        Date hireDate = Date.valueOf(request.getParameter("hireDate"));
        double salary = Double.parseDouble(request.getParameter("salary"));

        // Create EmployeePojo object
        EmployeePojo employee = new EmployeePojo();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(department);
        employee.setHireDate(hireDate);
        employee.setSalary(salary);

        // Call DAO to save employee
        EmployeeDao employeeDao = new EmployeeDao();
        boolean success = employeeDao.addEmployee(employee);

        // Redirect or forward to a success/failure page
        if (success) {
            response.sendRedirect("manageemployees.jsp?status=success");
        } else {
            response.sendRedirect("manageemployees.jsp?status=failure");
        }
    }
    
}

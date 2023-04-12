package ntukhpi.semit.dde.webapphbn.servlets.employees;

import ntukhpi.semit.dde.webapphbn.daoemployees.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.model.Employee;
import ntukhpi.semit.dde.webapphbn.model.EmployeeList;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeAllServlet extends HttpServlet {

    public static EmployeeList mylist = new EmployeeList();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EmployeeAllServlet#doGet");
        List<Employee> mylist = DAOEmployeesHBN.getEmployeeList();
        //EmployeeList mylist = new EmployeeList(5);
        request.setAttribute("employees", mylist);
        String path = "/views/employees/employees.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EmployeeAllServlet#doPost ===> call doGet");
        doGet(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("EmployeeAddServlet#destroy");
        HibernateUtil.shutdown();
    }
}

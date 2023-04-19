package ntukhpi.semit.dde.webapphbn.servlets.employees;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
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
import java.util.List;

@WebServlet("/employees")
public class EmployeeAllServlet extends HttpServlet {

    public static List<Employee> mylist;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("EmployeeAllServlet#doGet");
        mylist = DAOEmployeesHBN.getEmployeeList();
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

package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.daoemployees.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

public class CreateDemoSetForDB {

    public static void main(String[] args) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("\n\nHIBERNATE підключивася до БД!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(666);
        }

        //START ENTRANCE OF DATABASE

        // If create Session ok
        //INSERT DATA
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();
            //INSERT IN employees
            System.out.println("Insert test data in Employees");
            //session.save(new Employee(0l, "Zhuk", true, 50, 2500000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Zhuk", true, 35, 120000.0));
            //session.save(new Employee(0l, "Kot", true, 51, 2500000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Kot", true, 55, 55000.0));
            //session.save(new Employee(0l, "Gus'", true, 60, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Gus'", true, 34, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Zhatova", false, 28, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Shatova", false, 29, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Svatok", true, 32, 70000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Katz", true, 37, 75000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Kotov", true, 39, 55000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Lomov", true, 33, 90000.0));
            DAOEmployeesHBN.insert(new Employee(0l, "Popova", false, 32, 50000.0));
            // commit transaction
            transaction.commit();

        }

        //SELECT AND OUTPUT DATA
        List<Employee> employees = new ArrayList<Employee>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SELECT AND OUTPUT employees
            System.out.println("\nOUTPUT employees");
            employees = DAOEmployeesHBN.getEmployeeList();
            outputList(employees);
        }
    }

    private static void outputList(List list) {
        if (list.isEmpty()) {
            System.out.println("employees is empty");
        } else {
            list.stream().forEach(System.out::println);
        }
    }
}

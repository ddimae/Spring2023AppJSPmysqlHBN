package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAO_INN_HBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.INN;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        String[] names = new String[]{"Zhuk","Kot","Gusin","Zhatova","Shatova","Svatok","Katz","Kotov","Lomov","Popova"};

        // If create Session ok
        //INSERT DATA
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // start a transaction
            transaction = session.beginTransaction();
            //INSERT IN employees
            System.out.println("Insert test data in Employees");
            //session.save(new Employee(0l, "Zhuk", true, 50, 2500000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[0], true, 35, 120000.0));
            //session.save(new Employee(0l, "Kot", true, 51, 2500000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[1], true, 55, 55000.0));
            //session.save(new Employee(0l, "Gus'", true, 60, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[2], true, 34, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[3], false, 28, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[4], false, 29, 99000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[5], true, 32, 70000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[6], true, 37, 75000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[7], true, 39, 55000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[8], true, 33, 90000.0));
            DAOEmployeesHBN.insert(new Employee(0l, names[9], false, 32, 50000.0));
            // commit transaction
            Employee owner = null;
            transaction.commit();
            //INSERT IN inns
            // start a transaction
            transaction = session.beginTransaction();
            owner = DAOEmployeesHBN.getEmployeeByName(names[0]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 25634747474l, "Podatkova Shevchenkivskogo rajinu",
                    LocalDate.of(LocalDate.now().getYear()- owner.getAge()+18,
                            new Random().nextInt(12)+1,
                            new Random().nextInt(28)+1),
                    owner)
            );
            owner = DAOEmployeesHBN.getEmployeeByName(names[1]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2563272727l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear()- owner.getAge()+18,
                                    new Random().nextInt(12)+1,
                                    new Random().nextInt(28)+1),
                            owner)
            );

            // commit transaction
            transaction.commit();

        }

        //SELECT AND OUTPUT DATA
        //Output from employees
        List<Employee> employees = new ArrayList<Employee>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SELECT AND OUTPUT employees
            System.out.println("\nOUTPUT employees");
            employees = DAOEmployeesHBN.getEmployeeList();
            outputList(employees);
        }

        //Output from inns
        List<INN> inns = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //SELECT AND OUTPUT inns
            System.out.println("\nOUTPUT inns");
            inns = DAO_INN_HBN.getINNList();
            outputList(inns);
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

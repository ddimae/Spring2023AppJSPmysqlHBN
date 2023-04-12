package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.dbconnect.DBConnect;
import ntukhpi.semit.dde.webapphbn.model.Employee;
import ntukhpi.semit.dde.webapphbn.model.INN;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.time.LocalDate;

public class DemoINN2 {
    public static void main(String[] args) {
        Connection con = DBConnect.getConnectionMySQL();
        String nameForInsert ="Kovrova";
        if (con == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(777);
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
//                Employee owner = DAOEmployeesHBN.getEmployeeById(3);
                Employee owner = new Employee(-1l, nameForInsert, false, 25, 100000.0);
                System.out.println(owner.toString());
                session.save(owner);
                owner = (Employee) session.createCriteria(Employee.class)
                        .add(Restrictions.like("name", nameForInsert))
                        .list().get(0);
                System.out.println("Inn will be added to owner =" + owner.toString());
                INN inn = new INN(-1l, 2563994455l, "Podatkova KhObl", LocalDate.of(1998, 12, 25), owner);
                System.out.println(inn.toString());
                session.save(inn);
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("===== save Inn == Something went wrong! ====");
                if (transaction != null) {
                    transaction.rollback();
                }

            }
            //
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                Employee owner = (Employee) session.createCriteria(Employee.class)
                        .add(Restrictions.like("name", nameForInsert))
                        .list().get(0);
                INN inn = new INN(-1l, 2563994477l, "Podatkova KhObl too", LocalDate.of(1998, 12, 25), owner);
                System.out.println(inn.toString());
                System.out.println(owner);
                session.save(inn);
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("===== save Inn == Something went wrong! Duplicate?====");
                if (transaction != null) {
                    transaction.rollback();
                }

            }

        }
    }

}

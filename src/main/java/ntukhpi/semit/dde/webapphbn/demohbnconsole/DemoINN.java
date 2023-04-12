package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.daoemployees.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.dbconnect.DBConnect;
import ntukhpi.semit.dde.webapphbn.model.Employee;
import ntukhpi.semit.dde.webapphbn.model.INN;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.time.LocalDate;

/**
 * Класс для того, щоб записати через Хибернейт інфу до таблицы 'inns'
 * спочатку створюється новий Employee, потім йому призначається inn.
 * якщо Employee існує в БД (для цього здійснюється пошук за іменем, то інформація витягуєтьсяз БД
 */
public class DemoINN {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        if (sf == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(777);
        } else {
            Transaction transaction = null;
            try (Session session = sf.openSession()) {
                transaction = session.beginTransaction();
//                Employee owner = DAOEmployeesHBN.getEmployeeById(3);
                String nameForInsert = "Kovrov";
                Employee owner = new Employee(-1l, nameForInsert, true, 35, 50000.0);
                System.out.println(owner);
                try {
                    session.save(owner);
                } catch (Exception ex) {
                    System.err.println("===== save Employee == Duplicate Key! ====");
                }
//                owner = session.get(Employee.class, owner.getId());
                try {
                    owner = (Employee) session.createCriteria(Employee.class)
                            .add(Restrictions.like("name", nameForInsert))
                            .list().get(0);
                    System.out.println("Inn will be added to owner =" + owner.toString());
                } catch (Exception ex) {
                    System.out.println("******* No owner in db ********");
                    // owner = new Employee(-1l, nameForInsert, true, 35, 50000.0);
                    owner = new Employee();
                }
                INN inn = new INN(-1l, 2563994455l, "Podatkova KhObl", LocalDate.of(1998, 12, 25), owner);
                System.out.println(inn.toString());
                session.save(inn);
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("===== save Inn == Something went wrong! ====");
            } finally {
                if (transaction != null) {
                    transaction.rollback();
                }
            }

        }
    }

}

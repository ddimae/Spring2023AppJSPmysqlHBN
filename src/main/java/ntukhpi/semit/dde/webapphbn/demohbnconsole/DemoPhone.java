package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.dbconnect.DBConnect;
import ntukhpi.semit.dde.webapphbn.model.Employee;
import ntukhpi.semit.dde.webapphbn.model.Phone;
import ntukhpi.semit.dde.webapphbn.model.PhoneNumberType;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.sql.Connection;
import java.time.LocalDate;

public class DemoPhone {
    public static void main(String[] args) {
        Connection con = DBConnect.getConnectionMySQL();
        if (con == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(999);
        } else {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
//                Employee owner = DAOEmployeesHBN.getEmployeeById(3);
                String nameForInsert = "Kovrova";
                Employee owner = new Employee(-1l, nameForInsert, true, 35, 150000.0);
                System.out.println(owner);
                session.save(owner);
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
                Phone modile = new Phone(-1l, "0951203066", PhoneNumberType.MOBILE, true, true,owner);
                System.out.println(modile.toString());
                session.save(modile);
                Phone home = new Phone(-1l, "0573153686", PhoneNumberType.HOME, true, false,owner);
                System.out.println(home.toString());
                session.save(home);
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("===== save Inn == Something went wrong! ====");
                if (transaction != null) {
                    transaction.rollback();
                }

            }

        }
    }

}

package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.dbconnect.DBConnect;
import ntukhpi.semit.dde.webapphbn.model.Employee;
import ntukhpi.semit.dde.webapphbn.model.Phone;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.List;

public class DemoPhoneList {
    public static void main(String[] args) {
        Connection con = DBConnect.getConnectionMySQL();
        if (con == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(1111);
        } else {
            List<Phone> phones = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                System.out.println("\n\nHIBERNATE підключивася до БД!!!");
                phones = session.createQuery("from Phone", Phone.class).list();
                phones.forEach(System.out::println);
            } catch (Exception e) {

                e.printStackTrace();
            }

        }
    }
}

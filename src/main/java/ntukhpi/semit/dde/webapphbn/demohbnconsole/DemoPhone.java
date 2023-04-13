package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.INN;
import ntukhpi.semit.dde.webapphbn.entities.Phone;
import ntukhpi.semit.dde.webapphbn.entities.PhoneNumberType;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DemoPhone {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        String phoneNumber = "";
        String newPhoneNumber = "";
        List<Phone> results = null;
        String nameForInsert = "";
        Employee owner = null;
        Phone phoneFromDB = null;
        if (sf == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(777);
        } else {
            Transaction transaction = null;
            try (Session session = sf.openSession()) {
                transaction = session.beginTransaction();
                //Add new Employee
                nameForInsert = "Lozhkin";
                owner = new Employee(-1l, nameForInsert, false, 33, 77000.0);
                System.out.println(owner);
                if (DAOEmployeesHBN.insert(owner)) {
                    System.out.println("\nNew Employee added");
                }
                owner = DAOEmployeesHBN.getEmployeeByName(nameForInsert);
                //Add Phones to new Employee

                System.out.println("\nGo add phones!");
                Phone modile = new Phone(-1l, "0951203066", PhoneNumberType.MOBILE, true, true,owner);
                System.out.println(modile.toString());
                session.save(modile);
                Phone home = new Phone(-1l, "0573153686", PhoneNumberType.HOME, true, false,owner);
                System.out.println(home.toString());
                session.save(home);
                Phone office = new Phone(-1l, "0677573344", PhoneNumberType.OFFICE, true, false,owner);
                System.out.println(home.toString());
                session.save(office);
                transaction.commit();
            } catch (Exception ex) {
                System.err.println("===== save Phone == Something went wrong! ====");
                if (transaction != null) {
                    transaction.rollback();
                }
            }
            //Read phones by owner
            //Find in DB by owner
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                owner = DAOEmployeesHBN.getEmployeeByName(nameForInsert);
                System.out.println("\nPhones for owner "+owner);
                CriteriaBuilder cb = session.getCriteriaBuilder();
                CriteriaQuery<Phone> cr = cb.createQuery(Phone.class);
                Root<Phone> root = cr.from(Phone.class);
                cr.select(root).where(cb.equal(root.get("owner"), owner));
                Query<Phone> query = session.createQuery(cr);
                results = query.getResultList();
                if (!results.isEmpty()) {
                    System.out.println("Pones list");
                    results.stream().forEach(System.out::println);
                } else {
                    System.out.println("Pones ABSENT!");
                }
            } catch (Exception e) {
                System.err.println("******* Find INN in db (ByNumber) PROBLeM ********");
            }

        }
    }

}

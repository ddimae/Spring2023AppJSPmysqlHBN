package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAOPhonesHBN;
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
                owner = new Employee(-1l, nameForInsert, false, 33, 77000.0,null);
                System.out.println(owner);
                if (DAOEmployeesHBN.insert(owner)) {
                    System.out.println("\nNew Employee added");
                }
                owner = DAOEmployeesHBN.getEmployeeByName(nameForInsert);
                //Add Phones to new Employee

                System.out.println("\nGo add phones!");
                Phone modile = new Phone(-1l, "0951203066", PhoneNumberType.MOBILE, true, owner);
                System.out.println(modile.toString());
                session.save(modile);
                Phone home = new Phone(-1l, "0573153686", PhoneNumberType.HOME, true, owner);
                System.out.println(home.toString());
                session.save(home);
                Phone office = new Phone(-1l, "0677573344", PhoneNumberType.OFFICE, true,owner);
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
                    System.out.println("Phones ABSENT!");
                }
            } catch (Exception e) {
                System.err.println("******* Find Phones in db (ByNumber) PROBLeM ********");
            }
            //Delay for sequence output in console
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nAdd Phones - using DAOPhonesHBN");
            owner = DAOEmployeesHBN.getEmployeeByName(nameForInsert);
            Phone modile2 = new Phone(-1l, "0672203486", PhoneNumberType.MOBILE, false, owner);
            System.out.println(modile2);
            if (DAOPhonesHBN.insert(modile2)) {
                System.out.println(modile2.getPhoneNumber()+ " successfully added!");
            }
            Phone home2 = new Phone(-1l, "0572639615", PhoneNumberType.HOME, true, owner);
            System.out.println(home2.toString());
            if (DAOPhonesHBN.insert(home2)) {
                System.out.println(home2.getPhoneNumber()+ " successfully added!");
            }
            //Delay for sequence output in console
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nInfo about Phones for owner "+owner.getName());
            results = DAOPhonesHBN.getPhonesListOfOwner(nameForInsert);
            if (!results.isEmpty()){
                results.stream().forEach((phone)-> System.out.println(phone.getPhoneNumber()+" "+phone.getPhoneNumberType().name()));
                            } else {
                System.out.println("Phones ABSENT!");
            }
            //Delay for sequence output in console
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n========== Find By Number and Update ==========");
            phoneFromDB = DAOPhonesHBN.getPhoneByPhoneNumber("0672203486");
            System.out.println(phoneFromDB);
            Phone phoneUpdate1 = new Phone(-1l, "0684302267", PhoneNumberType.MOBILE, true, null);
            if (DAOPhonesHBN.update(phoneFromDB.getId(),phoneUpdate1)){
                phoneFromDB = DAOPhonesHBN.getPhoneByPhoneNumber("0684302267");
                System.out.println(phoneFromDB);
            }
            //Delay for sequence output in console
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\n========== Find By ID and Update ========== ");
            //Data truncation: Data too long for column 'phone_number' at row 1 - Mistake appeared for phone with more 10 numbers
            Phone phoneUpdate2 = new Phone(-1l, "0562002200", PhoneNumberType.HOME, false, null);
            phoneFromDB = DAOPhonesHBN.getPhoneById(1l);
            System.out.println(phoneFromDB);
            if (DAOPhonesHBN.update(phoneFromDB.getId(),phoneUpdate2)){
                phoneFromDB = DAOPhonesHBN.getPhoneById(1l);
                System.out.println(phoneFromDB);
            }
            //Delay for sequence output in console
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nInfo about Phones for owner after UPDATE"+owner.getName());
            results = DAOPhonesHBN.getPhonesListOfOwner(nameForInsert);
            if (!results.isEmpty()){
                results.stream().forEach((phone)-> System.out.println(phone.getPhoneNumber()+" "+phone.getPhoneNumberType().name()));
            } else {
                System.out.println("Phones ABSENT!");
            }
            System.out.println("\nPhone will deleted...");
            if (DAOPhonesHBN.deleteByOwner(owner)){
                System.out.println("\n===== Done!!! =====");
            }
            System.out.println("\nTry take Info about Phones for owner "+owner.getName());
            results = DAOPhonesHBN.getPhonesListOfOwner(nameForInsert);
            if (!results.isEmpty()){
                results.stream().forEach((phone)-> System.out.println(phone.getPhoneNumber()+" "+phone.getPhoneNumberType().name()));
            } else {
                System.out.println("Phones ABSENT!");
            }

            if (DAOEmployeesHBN.deleteByID(owner.getId())) {
                System.out.println();
                System.out.println(owner.getName() + " was deleted from emloyees");
            }
        }
    }

}

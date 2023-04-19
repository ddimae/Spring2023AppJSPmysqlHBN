package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAO_INN_HBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.INN;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс для того, щоб записати через Хибернейт інфу до таблицы 'inns'
 * спочатку створюється новий Employee, потім йому призначається inn.
 * якщо Employee існує в БД (для цього здійснюється пошук за іменем, то інформація витягуєтьсяз БД
 */
public class DemoINN {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Long innNumber = 1l;
        Long newINNNumber = 1l;
        List<Employee> results = null;
        Employee owner = null;
        INN innFromDB = null;
        if (sf == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(777);
        } else {
            Transaction transaction = null;
            try (Session session = sf.openSession()) {
                String nameForInsert = "Kovrov";
                owner = new Employee(-1l, nameForInsert, true, 35, 50000.0,null);
                System.out.println(owner);
                try {
                    transaction = session.beginTransaction();
                    session.save(owner);
                    transaction.commit();
                } catch (Exception ex) {
                    System.err.println("===== save Employee == Duplicate Key! ====");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
//              owner = session.get(Employee.class, owner.getId());
                try {
                    // Session session = HibernateUtil.getHibernateSession();
                    //transaction = session.beginTransaction();
//                    CriteriaBuilder cb = session.getCriteriaBuilder();
//                    CriteriaQuery<Employee> cr = cb.createQuery(Employee.class);
//                    Root<Employee> root = cr.from(Employee.class);
//                    cr.select(root).where(cb.equal(root.get("name"),nameForInsert));   //nameForInsert
//                    Query<Employee> query = session.createQuery(cr);
//                    List<Employee> results = query.getResultList();
//                    owner = results.get(0);
                     results = session.createCriteria(Employee.class)
                            .add(Restrictions.eq("name", nameForInsert))    //nameForInsert
                            .list();
                     if (results!=null) {
                         owner = results.get(0);
                         System.out.println("Inn will be added to owner =" + owner.toString());
                     } else {
                         System.out.println("Owner with name " + nameForInsert + " not found");
                     }

                    //transaction.commit();
                } catch (Exception ex) {
                    System.out.println("******* No owner in db ********");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                    System.exit(666);
                }
                innNumber = 2563994455l;
                INN inn = new INN(-1l, innNumber, "Podatkova KhObl", LocalDate.of(1998, 12, 25), owner);
                System.out.println(inn.toString());
                try {
                    transaction = session.beginTransaction();
                    session.save(inn);
                    transaction.commit();
                } catch (Exception ex) {
                    System.err.println("===== save INN == Duplicate Record! ====");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                System.out.println("\nINN in db (ByOwner)");

                try {
                    innFromDB = (INN) session.createCriteria(INN.class)
                            .add(Restrictions.eq("owner", owner))
                            .list().get(0);
                    System.out.println("Inn id for owner " + owner.getName() + " = " + innFromDB);
                } catch (Exception ex) {
                    System.out.println("******* No INN in db (ByOwner) ********");
                }
                //Delay for sequence output in console
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                innFromDB = null;
                System.out.println("\nINN in db (ByNumber)");
                //Long number = 2563994455l;
                try {
                    innFromDB = DAO_INN_HBN.getINNbyNumber(innNumber);
                    System.out.println("Inn id for number " + innNumber + " = " + innFromDB);
                } catch (Exception ex) {
                    System.out.println("******* No INN in db (ByNumber)********");
                }
                //Delay for sequence output in console
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try (Session session = sf.openSession()) {
                //Edit INN
                INN INNToUpdate = null;
                newINNNumber = innNumber + 1234567;
                INN INNForUpdating = new INN(-1l, newINNNumber, "Podatkova Shevchenkivskogo rajinu", LocalDate.of(2000, 05, 05), owner);
                try {
                    INNToUpdate = DAO_INN_HBN.getINNbyNumber(innNumber);
                    INNToUpdate.setNumber(INNForUpdating.getNumber());
                    INNToUpdate.setIssued(INNForUpdating.getIssued());
                    INNToUpdate.setDateIssued(INNForUpdating.getDateIssued());
                    transaction = session.beginTransaction();
                    session.update(INNToUpdate);
                    transaction.commit();
                    System.out.println("Inn with number " + innNumber + " updated");
                    INNToUpdate = DAO_INN_HBN.getINNbyNumber(newINNNumber);
                    System.out.println(INNToUpdate);
                } catch (Exception ex) {
                    System.out.println("******* Update INN ====> problem ********");
                    ex.printStackTrace();
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                //Delay for sequence output in console
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try (Session session = sf.openSession()) {
                //Delete INN
                INN INNToDelete = null;
                try {
                    INNToDelete = DAO_INN_HBN.getINNbyNumber(newINNNumber);
                    transaction = session.beginTransaction();
                    session.delete(INNToDelete);
                    transaction.commit();
                    System.out.println("Inn with number " + newINNNumber + " deleted");
                    //Пробуем найти - не должны
                    innFromDB = DAO_INN_HBN.getINNbyOwner(owner);
                    if (innFromDB == null) {
                        System.out.println("Inn id for owner " + owner.getName() + " absent in DB");
                    }
                } catch (Exception ex) {
                    System.out.println("******* Delete INN ====> problem ********");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
                //Delay for sequence output in console
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //Delete Owner
                try {
                    transaction = session.beginTransaction();
                    session.delete(owner);
                    transaction.commit();
                    System.out.println("Owner deleted");
                } catch (Exception ex) {
                    System.out.println("******* Delete OWNER ====> problem ********");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
            }
        }
    }

}

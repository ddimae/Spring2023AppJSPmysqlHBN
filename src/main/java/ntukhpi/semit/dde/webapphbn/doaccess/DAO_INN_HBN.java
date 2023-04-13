package ntukhpi.semit.dde.webapphbn.doaccess;

import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.INN;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * class DAO_INN_HBN
 * <p>
 * DAO - data access object
 * Provide implementation of CRUD with specified table
 * In presented view can work with any type of DBMS
 */
public class DAO_INN_HBN {
     /**
     * Method returned list of inn get from DB table inns
     *
     * @return  ArrayList<INN>
     */
    public static List<INN> getINNList() {
        List<INN> myList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myList = session.createQuery("from INN", INN.class).list();
            //myList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    /**
     * Method returned record from table INN by owner
     *
     * @return INN
     */
    public static INN getINNbyOwner(Employee owner) {
        List<INN> results = null;
        INN inn = null;
        //Find in DB by owner
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //New approach
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<INN> cr = cb.createQuery(INN.class);
            Root<INN> root = cr.from(INN.class);
            cr.select(root).where(cb.equal(root.get("owner"), owner));
            Query<INN> query = session.createQuery(cr);
            results = query.getResultList();
            if (!results.isEmpty()) {
                inn = results.get(0);
            } else {
            }
        } catch (Exception e) {
            System.err.println("******* DAO_INN_HBN#getINNbyOwner Find INN in db (ByOwner) PROBLeM ********");
        }
        return inn;
    }

    /**
     * Method returned record from table INN by Id
     *
     * @return INN
     */
    public static INN getINNbyId(Long id) {
        List<INN> results = null;
        INN inn = null;
        //Find in DB by owner
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //New approach
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<INN> cr = cb.createQuery(INN.class);
            Root<INN> root = cr.from(INN.class);
            cr.select(root).where(cb.equal(root.get("Id"), id));
            Query<INN> query = session.createQuery(cr);
            results = query.getResultList();
            if (!results.isEmpty()) {
                inn = results.get(0);
            } else {
            }
        } catch (Exception e) {
            System.err.println("******* Find INN in db (ByID) PROBLEM ********");
        }
        return inn;
    }

    /**
     * Method returned list of Employee get from DB table Employee
     *
     * @return INN
     */
    public static INN getINNbyNumber(Long number) {
        List<INN> results = null;
        INN inn = null;
        //Find in DB by owner
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //New approach
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<INN> cr = cb.createQuery(INN.class);
            Root<INN> root = cr.from(INN.class);
            cr.select(root).where(cb.equal(root.get("number"), number));
            Query<INN> query = session.createQuery(cr);
            results = query.getResultList();
            if (!results.isEmpty()) {
                inn = results.get(0);
            } else {
            }
        } catch (Exception e) {
            System.err.println("******* Find INN in db (ByNumber) PROBLEM ********");
        }
        return inn;
    }

    /**
     * Method to insert new record of INN
     *
     * @param newINN - instance of Employee for storing in table
     * @return boolean - true if record has been added, false - in other case
     */
    public static boolean insert(INN newINN) {
        boolean insertOk = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(newINN);
            // commit transaction
            transaction.commit();
            insertOk = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("DAO_INN_HBN#insert ===> Something went wrong!");
            insertOk = false;
        }
        return insertOk;
    }

    /**
     * Method to update record of INN with specified id
     *
     * @param id          - specified id
     * @param newINN - instance of INN for storing in table
     * @return boolean - true if record has been updated, false - in other case
     */
    public static boolean update(Long id, INN newINN) {
        boolean updateOk = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            INN innToUpdate = session.get(INN.class, id);
            innToUpdate.setNumber(newINN.getNumber()); //!!!
            innToUpdate.setIssued(newINN.getIssued());
            innToUpdate.setDateIssued(newINN.getDateIssued());
            session.update(innToUpdate);
            transaction.commit();
            updateOk = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("DAO_INN_HBN#update ===> Something went wrong!");
            updateOk = false;
        }
        return updateOk;
    }

    /**
     * Method to delete record with specified id
     *
     * @param id  - specified id
     * @return boolean - true if record has been updated, false - in other case
     */
    public static boolean deleteByID(Long id) {
        boolean deleteOk = false;
        if (id != -1) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // start a transaction
                transaction = session.beginTransaction();
                INN innToDelete = session.get(INN.class, id);
                // delete the INN objects
                session.delete(innToDelete);
                // commit transaction
                transaction.commit();
                deleteOk = true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("DAO_INN_HBN#delete ===> Something went wrong!");
                deleteOk = false;
            }
        }
        return deleteOk;
    }


}



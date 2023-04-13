package ntukhpi.semit.dde.webapphbn.doaccess;

import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.Team;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * class DAOTeamsHBN
 * <p>
 * DAO - data access object
 * Provide implementation of CRUD with specified table
 * In presented view can work with any type of DBMS
 */
public class DAOTeamsHBN {
    /**
     * Method returned list of Team get from DB table Team
     *
     * @return TeamList (extends ArrayList<Team>)
     */
    public static List<Team> getTeamList() {
        List<Team> myList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            myList = session.createQuery("from Team", Team.class).list();
            //myList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myList;
    }

    /**
     * Method to found record id by name
     *
     * @param id - key field in table Team, specified value for looking in table
     * @return Team object value -!!!
     */
    public static Team getTeamById(long id) {
        Team team = null;
        //Find in DB by id
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            team = session.get(Team.class, id);
        } catch (Exception e) {
            System.err.println("=== DAOTeamsHBN#getTeamById ====> Something went wrong!");
        }
        return team;
    }

    /**
     * Method to found record id by name
     *
     * @param name - key field in table Team, specified value for looking in table
     * @return Team value - id found record or -1, record with specified name not it DB
     */
    public static Team getTeamByName(String teamCod) {
        Team team = null;
        List<Team> results = null;
        //Find in DB by id
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Team> cr = cb.createQuery(Team.class);
            Root<Team> root = cr.from(Team.class);
            cr.select(root).where(cb.equal(root.get("teamCod"), teamCod));
            Query<Team> query = session.createQuery(cr);
            results = query.getResultList();
            if (!results.isEmpty()) {
                team = results.get(0);
            } else {
            }
        } catch (Exception e) {
            System.err.println("=== DAOTeamsHBN#getTeamByName === Something went wrong!");
        }
        return team;
    }

    /**
     * Method to insert new record of Team
     *
     * @param newTeam - instance of Team for storing in table
     * @return boolean - true if record has been added, false - in other case
     */
    public static boolean insert(Team newTeam) {
        boolean insertOk = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the team objects
            session.save(newTeam);
            // commit transaction
            transaction.commit();
            insertOk = true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("DAOTeamsHBN#insert ===> Something went wrong!");
            insertOk = false;
        }
        return insertOk;
    }

    /**
     * Method to update record of Team with specified id
     *
     * @param id          - specified id
     * @param newTeam - instance of Team for storing in table
     * @return boolean - true if record has been updated, false - in other case
     */
    public static boolean update(Long id, Team newTeam) {
        boolean updateOk = false;
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("id =" + id);
            Team teamToUpdate = session.get(Team.class, id);
            teamToUpdate.setTeamCod(newTeam.getTeamCod()); //!!!
            teamToUpdate.setPl(newTeam.getPl());
            session.update(teamToUpdate);
            transaction.commit();
            updateOk = true;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("DAOTeamsHBN#update ===> Something went wrong!");
            updateOk = false;
        }
        return updateOk;
    }

    /**
     * Method to delete record with specified id
     *
     * @param id - specified id
     * @return boolean - true if record has been updated, false - in other case
     */
    public static boolean deleteByID(Long id) {
        boolean deleteOk = false;
        if (id != -1) {
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                // start a transaction
                transaction = session.beginTransaction();
                Team teamToDelete = session.get(Team.class, id);
                // delete the Team objects
                session.delete(teamToDelete);
                // commit transaction
                transaction.commit();
                deleteOk = true;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("DAOTeamHBN#delete ===> Something went wrong!");
                deleteOk = false;
            }
        }
        return deleteOk;
    }
}



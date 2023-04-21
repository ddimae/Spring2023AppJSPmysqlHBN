package ntukhpi.semit.dde.webapphbn.doaccess;

import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.entities.Team;
import ntukhpi.semit.dde.webapphbn.entities.WorkInterval;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.*;

/**
 * class DAOWorkIntervalHBN
 * <p>
 * DAO - data access object
 * Provide implementation of CRUD with specified table
 * In presented view can work with any type of DBMS
 */
public class DAOWorkIntervalHBN {


    /**
     * Method add info about taking part employee in team by add to Team
     *
     * @return boolean if no problem during adding
     */
    public static boolean addInfoAboutWorkingEmployeeForTeam(Employee employee, Team team, WorkInterval period) {
        boolean flAddOk = false;
//        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//            Employee employeeInDB = session.get(Employee.class, employee.getId());
//            Team teamInDB = session.get(Team.class, team.getId());
        Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        teamInDB.addEmployee(employeeInDB, period);
        //employeeInDB.addTeam(teamInDB,period);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teamInDB);
            transaction.commit();
            flAddOk = true;
        } catch (Exception e) {
            flAddOk = false;
            System.err.println("=== DAOWorkIntervalHBN#addInfoAboutWorkingEmployeeForTeam === Something went wrong!");
        }
        return flAddOk;
    }

    /**
     * Method add info about taking part employee in team by add to Team
     *
     * @return boolean if no problem during adding
     */
    public static boolean addInfoAboutWorkingInTeamForEmployee(Team team, Employee employee, WorkInterval period) {
        boolean flAddOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
        employeeInDB.addTeam(teamInDB, period);
        //employeeInDB.addTeam(teamInDB,period);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employeeInDB);
            transaction.commit();
            flAddOk = true;
        } catch (Exception e) {
            flAddOk = false;
            System.err.println("=== DAOWorkIntervalHBN#addInfoAboutWorkingInTeamForEmployee === Something went wrong!");
        }
        return flAddOk;
    }

    /**
     * Method add info about taking part employee in team by add to Team
     * with now date of start and null value for end
     *
     * @return boolean if no problem during adding
     */
    public static boolean addInfoAboutWorkingInTeamForEmployee(Team team, Employee employee) {
        boolean flAddOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
        employeeInDB.addTeam(teamInDB, new WorkInterval(LocalDate.now(), null));
        //employeeInDB.addTeam(teamInDB,period);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(employeeInDB);
            transaction.commit();
            flAddOk = true;
        } catch (Exception e) {
            flAddOk = false;
            System.err.println("=== DAOWorkIntervalHBN#addInfoAboutWorkingInTeamForEmployee === Something went wrong!");
        }
        return flAddOk;
    }

    /**
     * Method add info about taking part employees in team by add to Team
     *
     * @return boolean if no problem during adding
     */
    public static boolean addInfoAboutWorkingEmployeesListForTeam(List<Employee> employees, Team team, WorkInterval period) {
        boolean flInsertAnyOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        for (Employee employee : employees) {
            boolean flAddOk = false;
            Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
            teamInDB.addEmployee(employeeInDB, period);
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.saveOrUpdate(teamInDB);
                transaction.commit();
                flAddOk = true;
            } catch (Exception e) {
                flAddOk = false;
                System.err.println("=== DAOWorkIntervalHBN#addInfoAboutWorkingEmployeesListForTeam === Something went wrong <--- " + employee.getName());
            }
            flInsertAnyOk = flInsertAnyOk || flAddOk;
        }
        return flInsertAnyOk;
    }

    /**
     * Method add info about taking part employees in team by add to Team with now date of start and null value for end
     *
     * @return boolean if no problem during adding
     */
    public static boolean addInfoAboutWorkingEmployeesListForTeam(List<Employee> employees, Team team) {
        boolean flInsertAnyOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        for (Employee employee : employees) {
            boolean flAddOk = false;
            Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
            teamInDB.addEmployee(employeeInDB, new WorkInterval(LocalDate.now(), null));
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.saveOrUpdate(teamInDB);
                transaction.commit();
                flAddOk = true;
            } catch (Exception e) {
                flAddOk = false;
                System.err.println("=== DAOWorkIntervalHBN#addInfoAboutWorkingEmployeesListForTeam === Something went wrong <--- " + employee.getName());
            }
            flInsertAnyOk = flInsertAnyOk || flAddOk;
        }
        return flInsertAnyOk;
    }

    /**
     * Method delete info about taking part employee in team by add to Team
     *
     * @return boolean if no problem during adding
     */
    public static boolean deleteInfoAboutWorkingEmployeeForTeam(Employee employee, Team team) {
        boolean flDeleteOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
        teamInDB.delEmployee(employee);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teamInDB);
            transaction.commit();
            flDeleteOk = DAOTeamsHBN.getTeamByName(team.getTeamCod()).getEmployeesOfTeam().isEmpty();
        } catch (Exception e) {
            flDeleteOk = false;
            System.err.println("=== DAOWorkIntervalHBN#deleteInfoAboutWorkingEmployeeForTeam === Something went wrong");
        }
        return flDeleteOk;
    }

    /**
     * Method delete all info about taking part employees in team by add to Team
     *
     * @return boolean if no problem during adding
     */
    public static boolean deleteInfoAboutWorkingEmployeesListForTeam(Team team) {
        boolean flDeleteOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        teamInDB.setEmployeesOfTeam(null);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teamInDB);
            transaction.commit();
            flDeleteOk = DAOTeamsHBN.getTeamByName(team.getTeamCod()).getEmployeesOfTeam().isEmpty();
        } catch (Exception ex) {
            flDeleteOk = false;
            System.err.println("=== DAOWorkIntervalHBN#deleteInfoAboutWorkingEmployeesListForTeam === Something went wrong");
        }
        return flDeleteOk;
    }

    //Methods for change dates

    /**
     * Method set WorkEnd as now() for all Employee in specified Team list
     *
     * @return boolean true - if at least one record
     */
    public static boolean changeWorkEndForEmployeesListOfTeam(Team team) {
        boolean flUpdateAnyOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        Set<Map.Entry<Employee, WorkInterval>> employeeEntries = teamInDB.getEmployeesOfTeam().entrySet();
        for (Map.Entry<Employee, WorkInterval> e : employeeEntries) {
            boolean flOk = false;
            Employee employee = e.getKey();
            WorkInterval period = e.getValue();
            LocalDate start = period.getWorkStart();
            LocalDate end = period.getWorkEnd();
            if (end == null) {
                Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
                teamInDB.addEmployee(employeeInDB, new WorkInterval(start, LocalDate.now()));
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    transaction = session.beginTransaction();
                    session.saveOrUpdate(teamInDB);
                    transaction.commit();
                    flOk = true;
                } catch (Exception ex) {
                    flOk = false;
                    System.err.println("=== DAOWorkIntervalHBN#changeWorkEndForEmployeesListOfTeam === Something went wrong");
                }
            }
            flUpdateAnyOk = flUpdateAnyOk || flOk;
        }
        return flUpdateAnyOk;
    }

    /**
     * Method set WorkEnd as now() for specified Employee in specified Team list
     *
     * @return boolean true  - if record was changed
     */
    public static boolean changeWorkEndForEmployeeOfTeam(Employee employee, Team team) {
        boolean flOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        WorkInterval period = teamInDB.getEmployeesOfTeam().get(employee);
        LocalDate start = period.getWorkStart();
        LocalDate end = period.getWorkEnd();
        if (end == null) {
            Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
            teamInDB.addEmployee(employeeInDB, new WorkInterval(start, LocalDate.now()));
            Transaction transaction = null;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                transaction = session.beginTransaction();
                session.saveOrUpdate(teamInDB);
                transaction.commit();
                flOk = true;
            } catch (Exception ex) {
                flOk = false;
                System.err.println("=== DAOWorkIntervalHBN#changeWorkEndForEmployeeOfTeam === Something went wrong");
            }
        }
        return flOk;
    }

    /**
     * Method set new period for specified Employee in specified Team list
     *
     * @return boolean true - if record was changed
     */
    public static boolean changeWorkEndForEmployeeOfTeam(Employee employee, Team team, WorkInterval period) {
        boolean flOk = false;
        Team teamInDB = DAOTeamsHBN.getTeamByName(team.getTeamCod());
        Employee employeeInDB = DAOEmployeesHBN.getEmployeeByName(employee.getName());
        teamInDB.addEmployee(employeeInDB, period);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(teamInDB);
            transaction.commit();
            flOk = true;
        } catch (Exception e) {
            flOk = false;
            System.err.println("=== DAOWorkIntervalHBN#changeWorkEndForEmployeeOfTeam === Something went wrong");
        }
        return flOk;
    }
}



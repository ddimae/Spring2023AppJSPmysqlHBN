package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAOTeamsHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAOWorkIntervalHBN;
import ntukhpi.semit.dde.webapphbn.entities.*;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DemoWorkInterval {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Employee employee = null;
        List<Employee> employees = null;
        Team team = null;
        List<Team> teams = null;
        LocalDate start = null;
        LocalDate end = null;
        WorkInterval interval = null;
        if (sf == null) {
            System.err.println("Connect to database not executed!!!");
            System.exit(777);
        } else {
            //Select employees and teams
            employees = DAOEmployeesHBN.getEmployeeList();
            teams = DAOTeamsHBN.getTeamList();
            //Info about 2022 year
            //Girl work in JavaScript from 15/09/2022 to 25/11/2022, boys - in Java001 from 15/09/22 to 23/12/2022
            for (Employee empl : employees) {
                if (empl.isPol()) {
                    team = teams.get(0);
                    start = LocalDate.of(2022, 9, 15);
                    end = LocalDate.of(2022, 11, 25);
                } else {
                    team = teams.get(1);
                    start = LocalDate.of(2022, 9, 22);
                    end = LocalDate.of(2022, 12, 23);
                }
                interval = new WorkInterval(start, end);
                //empl.addTeam(team, interval);
                team.addEmployee(empl, interval);
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    transaction = session.beginTransaction();
                    session.saveOrUpdate(team);
                    transaction.commit();
                } catch (Exception ex) {
                    System.out.println("WorkInterval not insert, maybe it present in Database");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
            }
            //Info about 2023 year
            //All work in Java002 from 12/01/23
            team = teams.get(3);
            start = LocalDate.of(2023, 1, 12);
            end = null;
            interval = new WorkInterval(start, end);
            for (Employee empl : employees) {
                //empl.addTeam(team, interval);
                team.addEmployee(empl, interval);
                Transaction transaction = null;
                try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                    transaction = session.beginTransaction();
                    session.saveOrUpdate(team);
                    transaction.commit();
                } catch (Exception ex) {
                    System.out.println("WorkInterval not insert, maybe it present in Database");
                    if (transaction != null) {
                        transaction.rollback();
                    }
                }
            }
            //Test add with DAOWorkIntervalHBN (Add Employee to Team)
            team = teams.get(2);
            employee = employees.get(5);
            start = LocalDate.of(2022, 12, 22);
            end = LocalDate.of(2023, 1, 10);
            ;
            interval = new WorkInterval(start, end);
            boolean flAddEmplOK = DAOWorkIntervalHBN.addInfoAboutWorkingEmployeeForTeam(employee, team, interval);
            if (flAddEmplOK) {
                System.out.println("====== " + employee.getName() + " added to " + team.getTeamCod() + " ======");
            }
            //Test add with DAOWorkIntervalHBN (Add Team to Employees)
            team = teams.get(4);
            employee = employees.get(7);
            start = LocalDate.of(2022, 1, 22);
            end = LocalDate.of(2022, 4, 10);
            ;
            interval = new WorkInterval(start, end);
            boolean flAddTeamOK = DAOWorkIntervalHBN.addInfoAboutWorkingEmployeeForTeam(employee, team, interval);
            if (flAddTeamOK) {
                System.out.println("====== " + team.getTeamCod() + " added to " + employee.getName() + " ======");
            }
            employee = employees.get(8);
            interval = new WorkInterval(start, end);
            flAddTeamOK = DAOWorkIntervalHBN.addInfoAboutWorkingEmployeeForTeam(employee, team, interval);
            if (flAddTeamOK) {
                System.out.println("====== " + team.getTeamCod() + " added to " + employee.getName() + " ======");
            }
            //Look current state
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputTeamsEmplyeesList();
            //======================================================
            //Test clear list
            System.out.println("\nTest clear list");
            team = teams.get(0);
            DAOWorkIntervalHBN.deleteInfoAboutWorkingEmployeesListForTeam(team);
            team = teams.get(3);
            DAOWorkIntervalHBN.deleteInfoAboutWorkingEmployeesListForTeam(team);
            //Look current state
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputTeamsEmplyeesList();
            //======================================================
            //======================================================
            //Test add list
            System.out.println("\nTest add list");
            team = teams.get(0);
            employees=DAOEmployeesHBN.getEmployeeList().stream().filter((empl)->empl.isPol()).toList();
            DAOWorkIntervalHBN.addInfoAboutWorkingEmployeesListForTeam(employees,team, new WorkInterval(LocalDate.now(),null));
            team = teams.get(3);
            employees=DAOEmployeesHBN.getEmployeeList();
            DAOWorkIntervalHBN.addInfoAboutWorkingEmployeesListForTeam(employees,team, new WorkInterval(LocalDate.now(),null));
            //Look current state
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputTeamsEmplyeesList();
            //======================================================
            //======================================================
            //Test delete from list employee for team
            System.out.println("\nTest delete some employee from list");
            employees=DAOEmployeesHBN.getEmployeeList();
            team = teams.get(1);
            employee = employees.get(3);
            DAOWorkIntervalHBN.deleteInfoAboutWorkingEmployeeForTeam(employee, team);
            employee = employees.get(4);
            DAOWorkIntervalHBN.deleteInfoAboutWorkingEmployeeForTeam(employee, team);
            //Look current state
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            outputTeamsEmplyeesList();
            //======================================================

        }
    }

    private static void outputTeamsEmplyeesList() {
        //OUTPUT INFO ABOUT WORK
        System.out.println("\nInfo about teams");
        List<Team> teams = DAOTeamsHBN.getTeamList();
        for (Team t : teams) {
            System.out.println(t.getTeamCod() + " " + t.getPl());
            Map<Employee, WorkInterval> sklad = t.getEmployeesOfTeam();
            if (!sklad.isEmpty()) {
                for (Map.Entry<Employee, WorkInterval> entry : sklad.entrySet()) {
                    System.out.println(entry.getKey() + " ("
                            + entry.getValue().getWorkStart() + ","
                            + entry.getValue().getWorkEnd() + ")");
                }
            } else {
                System.out.println("No info");
            }
        }
    }
}


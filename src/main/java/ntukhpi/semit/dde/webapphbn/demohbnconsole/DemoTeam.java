package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.entities.ProgramLanguage;
import ntukhpi.semit.dde.webapphbn.entities.Team;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DemoTeam {
    public static void main(String[] args) {
        Transaction transaction = null;
        List<Team> teams = new ArrayList<>();
        //INSERT
        System.out.println("INSERT");
        String cod="JavaSuper1";
        Team team = new Team(1l,cod, ProgramLanguage.JAVA);

        //INSERT NEW and Show Employee after insert
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Current list of teams");
            teams = session.createQuery("from Team", Team.class).list();
            teams.forEach(System.out::println);
            try {
                // start a transaction
                transaction = session.beginTransaction();
                // save the student objects
                session.save(team);
                // commit transaction
                transaction.commit();
                System.out.println("\nAfter adding");
                teams = session.createQuery("from Team", Team.class).list();
                teams.forEach(System.out::println);
            }  catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                System.err.println("===== save Team == Duplicate Key! ====");
            }

            System.out.println("\nResult of found team by id");
            Team teamFromDB = session.get(Team.class,5L);
            System.out.println(teamFromDB);
        }


    }
}

package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOTeamsHBN;
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
        Team teamFromBD;

        //INSERT and Show TEAMS after insert
        System.out.println("INSERT");
        String cod1="Java001";
        Team team1 = new Team(1l,cod1, ProgramLanguage.JAVA);
        if (DAOTeamsHBN.insert(team1)){
            System.out.println(team1.getTeamCod()+" added.");
        }
        String cod2="JavaScript";
        Team team2 = new Team(1l,cod2, ProgramLanguage.JavaScript);
        if (DAOTeamsHBN.insert(team2)){
            System.out.println(team2.getTeamCod()+" added.");
        }
        String cod3="JavaScript02";
        Team team3 = new Team(1l,cod3, ProgramLanguage.JavaScript);
        if (DAOTeamsHBN.insert(team3)){
            System.out.println(team3.getTeamCod()+" added.");
        }
        String cod4="Java002";
        Team team4 = new Team(1l,cod4, ProgramLanguage.JAVA);
        if (DAOTeamsHBN.insert(team4)){
            System.out.println(team4.getTeamCod()+" added.");
        }
        String cod5="CSharp2022";
        Team team5 = new Team(1l,cod5, ProgramLanguage.CSHARP);
        if (DAOTeamsHBN.insert(team5)){
            System.out.println(team5.getTeamCod()+" added.");
        }
        showTeam(DAOTeamsHBN.getTeamList(),"\nCurrent teams list");
        //Find By ID
        teamFromBD =DAOTeamsHBN.getTeamById(3l);
        if (teamFromBD!=null) {
            System.out.println(teamFromBD);
        } else {
            System.out.println("Team is ABSENT");
        }
        teamFromBD =DAOTeamsHBN.getTeamById(12l);
        if (teamFromBD!=null) {
            System.out.println(teamFromBD);
        } else {
            System.out.println("Team is ABSENT");
        }

        //Find By Name
        teamFromBD =DAOTeamsHBN.getTeamByName(cod2);
        if (teamFromBD!=null) {
            System.out.println(teamFromBD);
        } else {
            System.out.println("Team is ABSENT");
        }
        teamFromBD =DAOTeamsHBN.getTeamByName("Python");
        if (teamFromBD!=null) {
            System.out.println(teamFromBD);
        } else {
            System.out.println("Team is ABSENT");
        }
        //UPDATE
        teamFromBD =DAOTeamsHBN.getTeamByName(cod1);
        Team teamForUpdate = new Team(-1l,"Python2301", ProgramLanguage.PYTHON);
        if (DAOTeamsHBN.update(teamFromBD.getId(),teamForUpdate)){
            System.out.println("Updated");
        };
        showTeam(DAOTeamsHBN.getTeamList(),"\nCurrent teams list AFTER uPdate");
        //DELETE
        if (DAOTeamsHBN.deleteByID(teamFromBD.getId())) {
            System.out.println("Deleted");
        }
        showTeam(DAOTeamsHBN.getTeamList(),"\nCurrent teams list AFTER DELETE");

    }

    private static void showTeam(List<Team> list,String text) {
        System.out.println(text);
        if (list.isEmpty()) {
            System.out.println("teams list is empty");
        } else {

            list.stream().forEach(System.out::println);
        }
    }
}

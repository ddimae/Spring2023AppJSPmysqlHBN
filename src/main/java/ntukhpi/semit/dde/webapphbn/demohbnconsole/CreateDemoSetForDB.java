package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.doaccess.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAOPhonesHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAOTeamsHBN;
import ntukhpi.semit.dde.webapphbn.doaccess.DAO_INN_HBN;
import ntukhpi.semit.dde.webapphbn.entities.*;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateDemoSetForDB {

    public static void main(String[] args) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("\n\nHIBERNATE підключивася до БД!!!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(666);
        }

        //START ENTRANCE OF DATABASE

        String[] names = new String[]{"Zhuk", "Kot", "Gusin", "Zhatova", "Shatova", "Svatok", "Katz", "Kotov", "Lomov", "Popova"};

        // If create Session ok
        //INSERT DATA

        //=======================  INSERT IN employees ====================================
        System.out.println("Insert test data in Employees");
        try {
            DAOEmployeesHBN.insert(new Employee(names[0], true, 35, 120000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[1], true, 55, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[2], true, 34, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[3], false, 28, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[4], false, 29, 99000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[5], true, 32, 70000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[6], true, 37, 75000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[7], true, 39, 55000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[8], true, 33, 90000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        try {
            DAOEmployeesHBN.insert(new Employee(names[9], false, 32, 50000.0));
        } catch (Exception ex) {
            System.out.println(names[0] + " - not created, present in employees");
        }
        //============================================================================

        Employee owner = null;
        //=======================  INSERT IN inns ====================================
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[0]);

            DAO_INN_HBN.insert(
                    new INN(-1l, 2563474747l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[1]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2563272727l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[2]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 3492767676l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[3]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2592292929l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[4]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2533334433l, "Podatkova Kharkiv region",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[5]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2593939393l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[6]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 3562626262l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[7]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 3583839092l, "Podatkova Shevchenkivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[8]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 2626552525l, "Podatkova Slobidskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }
        try {
            owner = DAOEmployeesHBN.getEmployeeByName(names[9]);
            DAO_INN_HBN.insert(
                    new INN(-1l, 3578787878l, "Podatkova Dergachivskogo rajonu",
                            LocalDate.of(LocalDate.now().getYear() - owner.getAge() + 18,
                                    new Random().nextInt(12) + 1,
                                    new Random().nextInt(28) + 1),
                            owner)
            );
        } catch (Exception ex) {
            System.out.println("INN not insert, present in INNs");
        }

        //============================================================================
        //=======================  INSERT IN phones ====================================
        System.out.println("Insert test data in Phones");
        Random rnd = new Random();
        int numPhones;
        boolean activePhone;
        PhoneNumberType[] ptypes = PhoneNumberType.values();
        PhoneNumberType type;
        for (int i = 0; i < 10; i++) {
            owner = DAOEmployeesHBN.getEmployeeByName(names[i]);
            List<Phone> phones = DAOPhonesHBN.getPhonesListOfOwner(owner.getName());
            if (phones.isEmpty()) {
                // numPhones - how much pones will generated
                numPhones = rnd.nextInt(3) + 1;
                for (int j = 0; j < numPhones; j++) {
                    String generatedNums = "";
                    for (int k = 0; k < 9; k++) {
                        generatedNums += rnd.nextInt(10);
                    }
                    activePhone = Math.random() > 0.4999999999;
                    type = ptypes[rnd.nextInt(3)];
                    try {
                        DAOPhonesHBN.insert(
                                new Phone(-1l, "0" + generatedNums, type, activePhone, owner)
                        );
                    } catch (Exception ex) {
                        System.out.println("Phone not insert, maybe it present in Phones");
                    }
                }
            }
        }
        //============================================================================
        //=======================  INSERT IN teams ==================================
        System.out.println("Insert test data in teams");
        try {
            Team team1 = new Team("Java001", ProgramLanguage.JAVA);

            DAOTeamsHBN.insert(team1);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team2 = new Team("JavaScript", ProgramLanguage.JavaScript);
            DAOTeamsHBN.insert(team2);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team3 = new Team("JavaScript02", ProgramLanguage.JavaScript);
            DAOTeamsHBN.insert(team3);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team4 = new Team("Java002", ProgramLanguage.JAVA);
            DAOTeamsHBN.insert(team4);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        try {
            Team team5 = new Team("CSharp2022", ProgramLanguage.CSHARP);
            DAOTeamsHBN.insert(team5);
        } catch (Exception ex) {
            System.out.println("Team not insert, maybe it present in Database");
        }
        //============================================================================
        //=======================  INSERT IN teams ==================================
        System.out.println("Insert test data in teams");

        //============================================================================
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //****************************************************************************
        //SELECT AND OUTPUT DATA
        //Output from employees
        List<Employee> employees = new ArrayList<Employee>();
        //SELECT AND OUTPUT employees
        System.out.println("\nOUTPUT employees");
        employees = DAOEmployeesHBN.getEmployeeList();
        outputList(employees);

        //Output from inns
        List<INN> inns = new ArrayList<>();
        //SELECT AND OUTPUT inns
        System.out.println("\nOUTPUT inns");
        inns = DAO_INN_HBN.getINNList();
        outputList(inns);

        //Output Employees with Phones
        System.out.println("\nOUTPUT PHONES");
        employees = DAOEmployeesHBN.getEmployeeList();
        List<Phone> phones = new ArrayList<>();
        for (Employee own : employees) {
            System.out.println(own);
            phones = DAOPhonesHBN.getPhonesListOfOwner(own.getName());
            if (!phones.isEmpty()) {
                phones.stream().forEach((phone) -> System.out.println(phone.getPhoneNumber() + " " + phone.getPhoneNumberType().name()));
            } else {
                System.out.println("Phones ABSENT!");
            }
        }
        //Output Teams
        System.out.println("\nOUTPUT TEAMS");
        List<Team> teams = new ArrayList<>();
        //SELECT AND OUTPUT teams
        teams = DAOTeamsHBN.getTeamList();
        outputList(teams);

        //OUTPUT Employee work
//        System.out.println("\nOUTPUT TEAMS Employees list");
//        Employee empl1 = DAOEmployeesHBN.getEmployeeById(1);
//        if (empl1!=null) {
//            outputList(Arrays.asList(empl1.getTeamsEmpl().entrySet().toArray()));
//        }


    }

    private static void outputList(List list) {
        if (list.isEmpty()) {
            System.out.println("list is empty");
        } else {
            list.stream().forEach(System.out::println);
        }
    }
}

package ntukhpi.semit.dde.webapphbn.demohbnconsole;

import ntukhpi.semit.dde.webapphbn.daoemployees.DAOEmployeesHBN;
import ntukhpi.semit.dde.webapphbn.entities.Employee;
import ntukhpi.semit.dde.webapphbn.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DemoEmployee {
    public static void main(String[] args) {

        List< Employee > employees = null;
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("\n\nHIBERNATE підключивася до БД!!!");
            // start a transaction
            // transaction = session.beginTransaction();
            //employees = session.createQuery("from Employee", Employee.class).list();
            employees = DAOEmployeesHBN.getEmployeeList();
            employees.forEach(System.out::println);
            // commit transaction
            //     transaction.commit();
        } catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
            e.printStackTrace();
        }


        //INSERT
        System.out.println("INSERT");
        String nameNewEmpl="Morgun";
        Employee employee = new Employee(1l,nameNewEmpl, true, 50, 10000000.0);

        //INSERT NEW and Show Employee after insert
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(employee);
            // commit transaction
            transaction.commit();
            System.out.println("\nAfter adding");
            employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        //Find in DB by id
        Employee emplToUpdate =  null;
        long idToFind = 0;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Now in employee new snapshot of table.
            //Lets find object just inserted.
            idToFind = employees.stream().filter(empl -> empl.getName().equals(nameNewEmpl))
                    .findAny().get().getId();
            //Find employee
            //emplToUpdate = session.get(Employee.class, idToFind);
            System.out.println("idToFind ="+idToFind);
            emplToUpdate = DAOEmployeesHBN.getEmployeeById(idToFind);
                    // Show found instance
            System.out.println("\nFIND BY ID\nWe have been found "+nameNewEmpl);
            System.out.println(emplToUpdate);
        } catch (Exception e) {
            System.err.println("Something went wrong!");
        }

        //after Employee has been found we can or update or delete it
        // reseached - after close session something happen and emplToUpdate lost link with record in db
        // decision - reread object by id
        //UPDATE NEW and Show Employee after updating
        //!!! Impotant !!! Until you not implements delete, before new start DELETE record with Rebrov from DB or change it
        //ERROR: Duplicate entry 'Morgun' for key 'employee.name_UNIQUE'
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            //emplToUpdate = new Employee(3,"Morgul'",false,45, 64000.0); //ERROR: Duplicate entry 'Rebrov' for key 'employee.name_UNIQUE' //NO UPdATED!!!
            emplToUpdate = session.get(Employee.class, idToFind);
            emplToUpdate.setName("Rebrov");
            emplToUpdate.setAge(33);
            emplToUpdate.setSalary(77000.0);
            // update the Employee objects
            session.update(emplToUpdate);
            // commit transaction
            transaction.commit();
            System.out.println("\nAfter updating");
            employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        //DELETE NEW&Updated and Show Employee after deleting
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            //Employee emplToDelete = session.get(Employee.class, idToFind);
            Employee emplToDelete =DAOEmployeesHBN.getEmployeeByName("Rebrov");
            // start a transaction
            transaction = session.beginTransaction();

            // delete the Employee objects
            session.delete(emplToDelete);
            // commit transaction
            transaction.commit();
            System.out.println("\nAfter deleting");
            employees = session.createQuery("from Employee", Employee.class).list();
            employees.forEach(System.out::println);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

                System.out.println("\n\n============================ HIBERNATE відпрацював!!! =======================");
        }
    }





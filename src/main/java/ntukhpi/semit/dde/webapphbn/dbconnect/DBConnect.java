package ntukhpi.semit.dde.webapphbn.dbconnect;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * class DBConnect
 * Provide connecting to database in format DBMS MySQL
 */
public class DBConnect {

    private static Connection con = null;

    private static String stateCon = "No data";

    // edit 13/11/22
    //remove throw + add try-catch
    //Questions - how to organize desciption of mistakes to users or (and) to admins?!
    public static Connection getConnectionMySQL() {// throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            String url = "jdbc:mysql://localhost";
            String databaseName = "employees";
            String options = "";//"?serverTimezone=Europe/Kiev&useSSL=false";
            String username = "root";
            String password = "root";
            String connectLink = url + "/" + databaseName + options;

            con = DriverManager.getConnection(connectLink, username, password);

            stateCon = "Ok";

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Not found jdbc driver for MySQL");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("Wrong db MySQL connection parameters!!!");
            stateCon = "Wrong db MySQL connection parameters!!!";
            throw new RuntimeException(e);
        }

        return con;
    }
}

package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Lenovo
 */
public class DatabaseManager {

    private static final String dbUrl = "jdbc:mysql://localhost:3306/xor_db";
    private static final String dbUsername = "root";
    private static final String dbPassword = "";
    public static Connection conn;

    //A static function used to connect to database
    //and it returns a connection to databse that is used later to execute querry
    public static Connection CONNECT() {
        //Setting MySQL JDBC Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find MySQL JDBC Driver?" + e);
        }
        System.out.println("MySQL JDBC Driver Registered!");

        //Establish Connection
        try {
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
        }
        return conn;
    }

    //A static function used to close the databse connection
    public static void DISCONNECT() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
        System.out.println("Database Connection closed");
    }
}

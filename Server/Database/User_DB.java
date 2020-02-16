package Database;

import Client.Model.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_DB {

    private static final String dbUrl = "jdbc:mysql://localhost:3306/xor_db";
    private static final String dbUsername = "root";
    private static final String dbPassword = "";
    private static Connection conn;

    //A static function used to connect to database
    //and it returns a connection to databse that is used later to execute querry
    public static Connection connect() {
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
    public static void Disconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //A static function used to insert data of newely signed up user
    //it inserts data into the user table
    public static void insertUser(String userName, String password, String email) throws SQLException, ClassNotFoundException {
        conn = connect();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO `user` (`user_id`, `user_name`, `password`, `email`, `user_status`) VALUES (NULL, ?, ?, ?, 0);");
        pst.setString(1, userName);
        pst.setString(2, password);
        pst.setString(3, email);
        pst.execute();
    }

    //A static function used to retrieve data from database
    //and store the data into an ArrayList
    //and it returns the arraylist
    //to be used late for checking user authintication status 
    public static ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = new ArrayList<>();
        conn = connect();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM `user`;");
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            usersList.add(new User(res.getInt("user_id"), res.getString("user_name"), res.getString("password"), res.getString("email"), res.getString("user_status")));
        }
        return usersList;
    }

    //A static function used to retrieve the online users only
    //it returns the arraylist
    //this function is used to display a list of the online users in the GUI
    //to send invitation for playing a game 
    public static ArrayList<User> getOnlineUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = new ArrayList<>();
        conn = connect();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM `user` WHERE `user_status` = 'online'");
        ResultSet res = pst.executeQuery();

        while (res.next()) {
            usersList.add(new User( res.getString("user_name"), res.getString("user_status")));
        }
        return usersList;
    }

    //A sratic function used to check check for authentication status 
    //it take the username and password as a parameter
    //and it uses the getUsers() and it traversers the retrieved list looking for the user
    //and if the user is found, then he became authenticated to use log-in
    public static String checkUser(String userName, String password) throws SQLException, ClassNotFoundException {
        System.out.println(getUsers().get(0).getUserName());
        System.out.println(userName);
        System.out.println(getUsers().get(0).getPassword());
        System.out.println(password);

        String authenticationStatus = "userNotFound";

        for (User u : getUsers()) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                System.out.println(u.getPassword());
                if (u.getPassword().equalsIgnoreCase(password)) {
                    authenticationStatus = "userFound";
                    new User(u.getUserID(), u.getUserName(), u.getPassword(),u.getEmail(), u.getUserStatus());
                    break;
                } else {
                    authenticationStatus = "invalid password";
                    break;
                }
            } else {
                authenticationStatus = "userNotFound";
            }
        }
        return authenticationStatus;
    }

    //this function is used to update user status 
    //between three status online, offline, or busy (playing a game)
    public static void updateUserStatus(String userName, String userStatus) throws SQLException, ClassNotFoundException {
        conn = connect();
        System.out.println(userStatus);
        PreparedStatement pst = conn.prepareStatement("UPDATE `user` SET `user_status` = 'online' WHERE `user`.`user_name` = ?;");
        pst.setString(1, userStatus);
//        pst.setString(2, userName);
        pst.execute();
    }
}
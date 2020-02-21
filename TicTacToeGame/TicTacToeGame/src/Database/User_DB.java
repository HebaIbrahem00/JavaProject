package Database;

import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;

public class User_DB {

    public static Connection connect() throws ClassNotFoundException, SQLException {
        //Setting MySQL JDBC Driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is MySQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }
        System.out.println("MySQL JDBC Driver Registered!");

        //Establish Connection
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/xor_db", "root", "");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    public static void insertUser(String userName, String password, String email) throws SQLException, ClassNotFoundException {
        Connection conn = connect();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO `user` (`user_id`, `user_name`, `password`, `email`, `status`) VALUES (NULL, ?, ?, ?, NULL);");
        pst.setString(1, userName);
        pst.setString(2, password);
        pst.setString(3, email);
        pst.execute();
    }

    public static ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = new ArrayList<>();
        Connection conn = connect();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM `user`");
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            usersList.add(new User(res.getInt("user_id"), res.getString("user_name"), res.getString("password"), res.getString("email"), res.getInt("status")));
        }
        return usersList;
    }

    public static int checkUser(String userName, String password) throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = getUsers();
        int status = 0;
        for (User u : usersList) 
        {
            if (u.getUserName().equalsIgnoreCase(userName)) 
            {
                if (u.getPassword().equalsIgnoreCase(password)) 
                {
                    switch (status) 
                    {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 0:
                            break;
                    }
                }
            }
        }
        return status;
    }
    
}
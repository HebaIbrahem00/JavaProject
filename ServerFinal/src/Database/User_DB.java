package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import org.json.JSONArray;
//import org.json.JSONObject;

public class User_DB {

    private static Connection conn;
    private static PreparedStatement pst;
    private static ResultSet res;

    //A static function used to retrieve data from database
    //and store the data into an ArrayList and retur arraylist of all users
    //to be used late for checking user authintication status 
    public static ArrayList<User> getUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User> usersList = new ArrayList<>();
        conn = DatabaseManager.CONNECT();
        pst = conn.prepareStatement("SELECT * FROM `user`;");
        res = pst.executeQuery();

        while (res.next()) {
            usersList.add(new User(res.getInt("user_id"), res.getString("user_name"), res.getString("password"), res.getString("email"), res.getString("user_status")));
        }

        DatabaseManager.DISCONNECT();
        return usersList;
    }

    //A sratic function used to check check for authentication status 
    //it take the username and password as a parameter
    //and it uses the getUsers() and it traversers the retrieved list looking for the user
    //and if the user is found, then he became authenticated to use log-in
    public static String checkUser(String userName, String password) throws SQLException, ClassNotFoundException {
        String authenticationStatus = "User not Exist";
        for (User u : getUsers()) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                if (u.getPassword().equalsIgnoreCase(password)) {
                    authenticationStatus = "User Exist";
                    //if user exit and authinticated to log in
                    //we chaange its status to online
                    setUserOnline(u.getUserName());
                    break;
                } else {
                    authenticationStatus = "Wrong Psssword";
                    break;
                }
            } else {
                authenticationStatus = "User not Exist";
            }
        }
        DatabaseManager.DISCONNECT();
        return authenticationStatus;
    }

    //A static function used to insert data of newely signed up user
    //and it uses the getUsers() and it traversers the retrieved list looking for a user with the same userName or email exists
    //it take the username, email, password and email as a parameter
    //email and username are unique for each user, we check if this email exist
    public static String validateNewUserData(String userName, String email, String password) throws SQLException, ClassNotFoundException {
        String isDataValid = "";
        boolean isUserExist = false;
        for (User u : getUsers()) {
            if (u.getUserName().equalsIgnoreCase(userName)) {
                isDataValid = "User with User Name already exists";
                isUserExist = true;
                break;
            } else if (u.getEmail().equalsIgnoreCase(email)) {
                isDataValid = "User with Email address already exists";
                isUserExist = true;
                break;
            }
        }

        if (!isUserExist) {
            conn = DatabaseManager.CONNECT();
            pst = conn.prepareStatement("INSERT INTO `user` (`user_id`, `user_name`, `email`, `password`, `user_status`) VALUES (NULL, ?, ?, ?, 'offline');");
            pst.setString(1, userName);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.execute();
            DatabaseManager.DISCONNECT();
            isDataValid = "validData";
        }
        return isDataValid;
    }

    //A static function used to retrieve the online users only, it returns the arraylist
    //this function is used to display a list of the online users in the GUI
    //to send invitation for playing a game 
    /*public static JSONArray reteriveOnlinUser() throws SQLException, ClassNotFoundException {
        JSONArray JSONUsersList = new JSONArray();
        for (User u : getUsers()) {
            JSONObject JSONUSer = new JSONObject();
            JSONUSer.put("userName", u.getUserName());
            JSONUSer.put("email", u.getEmail());
            JSONUSer.put("userStatus", u.getUserStatus());
            JSONUSer.put("score", "invite");
            JSONUsersList.put(JSONUSer);
        }
        return JSONUsersList;
    }*/

    //this function is used to update user status to online
    public static void setUserOnline(String userName) throws SQLException, ClassNotFoundException {
        conn = DatabaseManager.CONNECT();
        pst = conn.prepareStatement("UPDATE `user` SET `user_status` = 'online' WHERE `user`.`user_name` = ?;");
        pst.setString(1, userName);
        pst.execute();
        DatabaseManager.DISCONNECT();
    }

    //this function is used to update user status to offline
    public static void setUserOffline(String userName) throws SQLException, ClassNotFoundException {
        conn = DatabaseManager.CONNECT();
        pst = conn.prepareStatement("UPDATE `user` SET `user_status` = 'offline' WHERE `user`.`user_name` = ?;");
        pst.setString(1, userName);
        pst.execute();
        DatabaseManager.DISCONNECT();
    }

    //this function is used to update user status to busy (playing a game)
    public static void setUserBusy(String userName) throws SQLException, ClassNotFoundException {
        conn = DatabaseManager.CONNECT();
        pst = conn.prepareStatement("UPDATE `user` SET `user_status` = 'busy' WHERE `user`.`user_name` = ?;");
        pst.setString(1, userName);
        pst.execute();
        DatabaseManager.DISCONNECT();
    }
}

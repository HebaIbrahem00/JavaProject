package Database;

//import org.json.JSONObject;

public class User {

    //Declare user Table Columns
    private int userID;
    private String userName;
    private String password;
    private String email;
    private String userStatus;

    //constructor
    public User(int userID, String userName, String password, String email, String userStatus) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
    }

    //a customized User constructor used when we want to retrieve uset
    public User(String userName, String email, String userStatus) {
        this.userName = userName;
        this.email = email;
        this.userStatus = userStatus;
    }

    public User(String userName, String userStatus) {
        this.userName = userName;
        this.userStatus = userStatus;
    }

    //setter methods
    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    //getter methods
    public int getUserID() {
        return this.userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserStatus() {
        return this.userStatus;
    }
}

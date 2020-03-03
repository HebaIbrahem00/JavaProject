package Model;

public class CurrentUser {

    static private String userName;
    static private String email;
    static private String userStatus;
    static private String score;

    //setter methods
    static public void setUserName(String _userName) {
        userName = _userName;
    }

    static public void setEmail(String _email) {
        email = _email;
    }

    static public void setUserStatus(String _userStatus) {
        userStatus = _userStatus;
    }

    //getter methods
    static public String getUserName() {
        return userName;
    }

    static public String getEmail() {
        return email;
    }
}

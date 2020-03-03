package Model;

/**
 * @author Lenovo
 */
public class OnlineUser {

    private String userName;
    private String email;
    private String userStatus;
    private String score;
    private String invite;

    public OnlineUser(String userName, String email, String userStatus, String score, String invite) {
        this.userName = userName;
        this.email = email;
        this.userStatus = userStatus;
        this.score = score;
        this.invite = invite;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getInvite() {
        return invite;
    }

    public void setInvite(String invite) {
        this.invite = invite;
    }

}


abstract class Users {
    protected String userID;
    protected String password = "abc123";

    public Users(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void printUser() {
        System.out.println("UserID:\t" + getUserID());
        System.out.println("Password:\t" + getPassword());
        System.out.println("");
    }
}
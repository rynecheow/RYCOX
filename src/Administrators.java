
class Administrators extends Users {

    public Administrators(String userID, String password) {
        super(userID, password);
    }

    public void printUser() {
        System.out.println("Staff type: Administrator");
        System.out.println("UserID:\t" + getUserID());
        System.out.println("Password:\t" + getPassword());
        System.out.println("");
    }
}

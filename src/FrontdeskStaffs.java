
class FrontdeskStaffs extends Users {

    public FrontdeskStaffs(String userID, String password) {
        super(userID, password);
    }

    public void printUser() {
        System.out.println("Staff type: Front Desk Staff");
        System.out.println("UserID:\t" + getUserID());
        System.out.println("Password:\t" + getPassword());
        System.out.println("");
    }
}


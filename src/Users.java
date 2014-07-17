import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

abstract class Users implements Serializable {

    protected String userID;
    protected String password = "abc123";
    protected String lastLoggedIn;

    public Users(String userID, String password) {
        this.userID = userID;
        this.password = password;
        this.lastLoggedIn = "--";
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

    public void setLastLoggedIn() {
        lastLoggedIn = DateFormat.getInstance().format(new Date());
    }

    public String getLastLoggedIn() {
        return lastLoggedIn;
    }

    abstract void printUser();
}

/**************************************************************************
 * (C) Copyright 2012 by Ryne Cheow Yeong Chi , Ng Jia Jiun               *
 * Lai Li Hao. All rights reserved.                                       *
 *                                                                        *
 * DISCLAIMER: The writer of this particular program have used their      *
 * best efforts in preparing this program. These efforts include the      *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The writers make                     *
 * no warranty of any kind, expressed or implied, with regard to this     *
 * program. The writers shall not be liable in                            *
 * any event for incidental or                                            *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of this program.                       *
 *************************************************************************/
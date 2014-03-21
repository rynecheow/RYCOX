import java.text.DateFormat;
import java.util.Date;

class LogFile {

    private String date;
    private String user;
    private String action;

    public LogFile(String user, String action) {
        this.date = "[" + DateFormat.getInstance().format(new Date()) + "]";
        this.user = user;
        this.action = action;
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public void setDate(String d) {
        this.date = d;
    }

    public String getDate() {
        return date;
    }

    public void showLog() {
        System.out.println(getDate() + " " + getUser() + " " + getAction());
    }
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
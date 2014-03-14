
@SuppressWarnings("serial")
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
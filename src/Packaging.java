import java.io.Serializable;

@SuppressWarnings("serial")
class Packaging implements Serializable {
    private String pkgCode;                    //initialise variables
    private String progCode;

    public Packaging(String pkgCode, String progCode) { //constructor
        this.pkgCode = pkgCode;
        this.progCode = progCode;
    }

    //accessors
    public String getPkgCode() { //to return package's code
        return pkgCode;
    }

    public String getProgCode() { //to return programme's code

        return progCode;
    }

    public void printPckging() {
        System.out.println(getProgCode());
        System.out.println();
    }

    public void printSecPckging() {
        System.out.println(getProgCode() + " is linked to " + getPkgCode());
        System.out.println();
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
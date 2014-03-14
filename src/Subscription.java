import java.io.Serializable;

@SuppressWarnings("serial")
class Subscription implements Serializable {

    public int subsNo;
    public String smartCardNo;
    public String pkgCode;

    public Subscription(String smartCardNo, int subsNo, String pkgCode) {
        this.smartCardNo = smartCardNo;
        this.subsNo = subsNo;
        this.pkgCode = pkgCode;
    }

    public void printSubs() {
        System.out.println("Subscibe Number: " + subsNo);
        System.out.println("Smart Card Number: " + smartCardNo);
    }

    public String getSmartCardNo() {
        return smartCardNo;
    }

    public int getSubsNo() {
        return subsNo;
    }

    public String getPkgCode() {
        return pkgCode;
    }

    public void setSmartCardNo(String smartCardNo) {
        this.smartCardNo = smartCardNo;
    }

    public void setSubsNo(int subsNo) {
        this.subsNo = subsNo;
    }

    public void setPkgCode(String pkgCode) {
        this.pkgCode = pkgCode;
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
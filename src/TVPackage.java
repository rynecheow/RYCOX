import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("serial")
class TVPackage implements Serializable {

    private String pkgCode;                    //initialise variables
    private String pkgName;
    private String startDate;
    private String terminationDate = "N/A";
    private double chargePrice;
    private String chargeType;
    private String pkgStatus = "ACTIVE";

    public TVPackage(String pkgCode, String pkgName, double chargePrice, String chargeType, String pkgStatus) { //constructor{
        this.pkgCode = pkgCode;
        this.pkgName = pkgName;
        this.startDate = DateFormat.getInstance().format(new Date());
        this.terminationDate = "N/A";
        this.chargePrice = chargePrice;
        this.chargeType = chargeType;
        this.pkgStatus = pkgStatus;
    }

    public TVPackage(String pkgCode, String pkgName, String startDate, String terminationDate, double chargePrice, String chargeType, String pkgStatus) { //constructor{
        this.pkgCode = pkgCode;
        this.pkgName = pkgName;
        this.startDate = DateFormat.getInstance().format(new Date());
        this.terminationDate = "N/A";
        this.chargePrice = chargePrice;
        this.chargeType = chargeType;
        this.pkgStatus = pkgStatus;
    }

    //accessors
    public String getPkgCode() { //to return package's code
        return pkgCode;
    }

    public String getPkgName() { //to return package's name
        return pkgName;
    }

    public String getStartDate() { //to return the date that a package starts
        return startDate;
    }

    public String getTerminationDate() { //to return the date that a package terminates
        return terminationDate;
    }

    public double getChargePrice() { //to return the price charges by the package
        return chargePrice;
    }

    public String getChargeType() { //to return the type that the package charges by
        return chargeType;
    }

    public String getPkgStatus() { //to return the package's status
        return pkgStatus;
    }

    //mutators
    public void setPkgCode(String pkgCode) {//to change package's code
        this.pkgCode = pkgCode;
    }

    public void setPkgName(String pkgName) { //to change package's name
        this.pkgName = pkgName;
    }

    public void setChargePrice(double chargePrice) { //to change package's charge price
        this.chargePrice = chargePrice;
    }

    public void setChargeType(String chargeType) { //to change package's charge type
        this.chargeType = chargeType;
    }

    public void setStatus(String pkgStatus) {  //to change package's status
        this.pkgStatus = pkgStatus;
    }

    public void setTerminationDate(String terminationDate) {  //to set termination date
        this.terminationDate = terminationDate;
    }

    public void printPkg() {
        System.out.println("Package Code: " + pkgCode);
        System.out.println("Package Name: " + pkgName);
        System.out.println("Start Date: " + startDate);
        System.out.println("Termination Date: " + terminationDate);
        System.out.println("Charge Price: RM" + chargePrice);
        System.out.println("Charge Type: " + chargeType);
        System.out.println("Status: " + pkgStatus + "\n");
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
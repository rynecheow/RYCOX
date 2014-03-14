import java.io.Serializable;

@SuppressWarnings("serial")
class TVPackage implements Serializable {
    private String pkgCode;                    //initialise variables
    private String pkgName;
    private String startDate;
    private String terminationDate = "N/A";
    private double chargePrice;
    private String chargeType;
    private String pkgStatus = "Active";

    public TVPackage(String pkgCode, String pkgName, String startDate, double chargePrice, String chargeType, String pkgStatus) { //constructor{
        this.pkgCode = pkgCode;
        this.pkgName = pkgName;
        this.startDate = startDate;
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
        System.out.println("\nPackage Name: " + pkgName);
        System.out.println("\nStart Date: " + startDate);
        System.out.println("\nTermination Date: " + terminationDate);
        System.out.println("\nCharge Price: RM" + chargePrice);
        System.out.println("\nCharge Type: " + chargeType);
        System.out.println("\nStatus: " + pkgStatus);
    }
}
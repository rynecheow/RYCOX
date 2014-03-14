import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("serial")
abstract class ClientAccount implements Serializable {
    protected String name;
    protected String billingAddress;
    protected String clientID;
    protected String creationDate;
    protected String terminationDate = "N/A";
    protected String accStatus = "INACTIVE";

    public ClientAccount(String name, String billingAddress, String clientID, String accStatus) {
        this.name = name;
        this.billingAddress = billingAddress;
        this.clientID = clientID;
        this.creationDate = DateFormat.getInstance().format(new Date());
        this.accStatus = accStatus;
    }

    public String getName() {
        return name;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public String getClientID() {
        return clientID;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getAccountStatus() {
        return accStatus;
    }

    public String getTerminationDate() {
        return terminationDate;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setBillingAddress(String b) {
        this.billingAddress = b;
    }

    public void setAccountStatus(String as) {
        this.accStatus = as;
    }

    public void setTerminationDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public boolean terminationStatus() {
        if (this.accStatus.equalsIgnoreCase("TERMINATED"))
            return true;
        else
            return false;
    }

    public void printClient() {
        System.out.println("Client Name:\t" + getName());
        System.out.println("Client Address:\t" + getBillingAddress());
        System.out.println("Client ID:\t" + getClientID());
        System.out.println("Creation Date:\t" + getCreationDate());

        if (terminationStatus() == true)
            System.out.println("Termination Date:\t" + getTerminationDate());

        System.out.println("Account Status:\t" + getAccountStatus());
        System.out.println("");
    }
}
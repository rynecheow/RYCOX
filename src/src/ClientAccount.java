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
    protected String email;

    public ClientAccount(String name, String billingAddress, String clientID, String accStatus, String email) {
        this.name = name;
        this.billingAddress = billingAddress;
        this.clientID = clientID;
        this.creationDate = DateFormat.getInstance().format(new Date());
        this.terminationDate = "N/A";
        this.accStatus = accStatus;
        this.email = email;
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
        if (this.accStatus.equalsIgnoreCase("TERMINATED")) {
            return true;
        } else {
            return false;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String e) {
        email = e;
    }

    abstract void printClient();

    abstract String stringClient();
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
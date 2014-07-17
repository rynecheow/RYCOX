import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

class Service implements Serializable {

    private String smartCardNo;
    private String clientID;
    private String decoderNo;
    private String address;
    private String servStatus = "ACTIVE";
    private String registrationDate;
    private String terminationDate = "N/A";
    private double currOstBal = 0;
    private double prevBillAmount = 0;

    public Service(String smartCardNo, String clientID, String decoderNo, String address) {
        this.smartCardNo = smartCardNo;
        this.clientID = clientID;
        this.decoderNo = decoderNo;
        this.address = address;
        registrationDate = DateFormat.getInstance().format(new Date());
    }

    public String getRegDate() {
        return registrationDate;
    }

    public String getTermDate() {
        return terminationDate;
    }

    public String getSmartCardNo() {
        return smartCardNo;
    }

    public String getClientID() {
        return clientID;
    }

    public String getDecodeNo() {
        return decoderNo;
    }

    public String getServStatus() {
        return servStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setSmartCardNo(String smartCardNo) {

        this.smartCardNo = smartCardNo;
    }

    public void setDecoderNo(String decoderNo) {
        this.decoderNo = decoderNo;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setServStatus(String servStatus) {
        String s = servStatus.toLowerCase();
        switch (s) {
            case "active":
                this.servStatus = "ACTIVE";
                break;
            case "barred":
                this.servStatus = "BARRED";
                break;
            case "terminated":
                this.servStatus = "TERMINATED";
                setTermDate(DateFormat.getInstance().format(new Date()));
                break;
        }
    }

    public void setTermDate(String terminationDate) {
        this.terminationDate = terminationDate;
    }

    public boolean terminationStatus() {
        return this.servStatus.equalsIgnoreCase("TERMINATED");
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
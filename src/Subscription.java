import java.io.Serializable;

@SuppressWarnings("serial")
class Subscription implements Serializable {

    public String smartCardNo;
    public int subsNo;
    public String pkgCode;

    public Subscription(String smartCardNo, int subsNo) {
        this.smartCardNo = smartCardNo;
        this.subsNo = subsNo;
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
} 
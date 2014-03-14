import java.io.Serializable;

class IndividualClient extends ClientAccount implements Serializable {
    private int age;
    private String ic;

    public IndividualClient(String name, int age, String ic, String address, String clientID, String creationDate, String accStatus) {
        super(name, address, clientID, creationDate, accStatus);
        this.age = age;
        this.ic = ic;
    }

    public int getAge() {
        return age;
    }

    public String getIC() {
        return ic;
    }

    //Mutators
    //Set age
    public void setAge(int age) {
        this.age = age;
    }

    //Set IC
    public void setIC(String ic) {
        this.ic = ic;
    }

    //Print Basic Details
    public void printClient() {
        System.out.println();
        System.out.println("Client Name:\t" + getName());
        System.out.println("Client Age:\t" + getAge());
        System.out.println("Client IC:\t" + getIC());
        System.out.println("Client Address:\t" + getBillingAddress());
        System.out.println("Client ID:\t" + getClientID());
        System.out.println("Creation Date:\t" + getCreationDate());
        System.out.println("Account Status:\t" + getAccStatus());
        System.out.println();
    }
}
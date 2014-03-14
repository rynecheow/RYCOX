import java.io.Serializable;

@SuppressWarnings("serial")
class IndividualClient extends ClientAccount implements Serializable {
    private int age;
    private String ic;

    public IndividualClient(String name, int age, String ic, String address, String clientID, String accStatus) {
        super(name, address, clientID, accStatus);
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
        System.out.println("Account Status:\t" + getAccountStatus());
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
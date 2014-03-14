@SuppressWarnings("serial")
class IndividualClient extends ClientAccount {
    private int age;
    private String ic;

    public IndividualClient(String name, int age, String ic, String address, String clientID, String accStatus, String email) {
        super(name, address, clientID, accStatus, email);
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

    String stringClient() {
        String name = "Client Name:\t" + getName();
        String age = "Client Age:\t" + getAge();
        String Ic = "Client IC:\t" + getIC();
        String add = "Client Address:\t" + getBillingAddress();
        String Id = "Client ID:\t" + getClientID();
        String accStats = "Creation Date:\t" + getCreationDate();
        String creaDate = "Creation Date:\t" + getCreationDate();
        String termDate;
        if (terminationStatus() == true)
            termDate = "Termination Date:\t" + getTerminationDate() + "\n";
        else
            termDate = "";

        String cl = name + "\n" + age + "\n" + Ic + "\n" + add + "\n" + Id + "\n" + accStats + "\n" + creaDate + termDate + "\n";
        return cl;
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
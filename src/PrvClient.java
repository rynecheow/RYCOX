@SuppressWarnings("serial")
class PrvClient extends ClientAccount {

    public PrvClient(String name, String address, String clientID, String accStatus) {
        super(name, address, clientID, accStatus);
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
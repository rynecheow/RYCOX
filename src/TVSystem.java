import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class TVSystem {

    Scanner input = new Scanner(System.in);
    String username = "";
    String password = "";
    String menu1_opt = "";
    String menu2_opt = "";
    int i = 0, f = 0, u = 0, menu1_opt_1 = 0, menu2_opt_1 = 0;
    boolean login = false;
    PrintStream p = System.out;

    LinkedList<Users> userList = new LinkedList<Users>();
    LinkedList<ClientAccount> clientList = new LinkedList<ClientAccount>();
    LinkedList<Service> servList = new LinkedList<Service>();
    LinkedList<Subscription> subsList = new LinkedList<Subscription>();
    LinkedList<TVPackage> pkgList = new LinkedList<TVPackage>();
    LinkedList<TVProgramme> prgList = new LinkedList<TVProgramme>();
    LinkedList<LogFile> logList = new LinkedList<LogFile>();
    LogFile log = new LogFile("", "");
    List<Integer> t = new LinkedList<Integer>();
    File client_file = new File("cl_data.dat");
    boolean existFile = client_file.exists();
    StringBuffer contents = new StringBuffer();
    BufferedReader reader = null;

    @SuppressWarnings("unchecked")
    public TVSystem() {
        userList.add(new Administrators("admin", "nimda"));
        userList.add(new FrontdeskStaffs("staff", "123abc"));
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I00001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "17th Apr 2011", "INACTIVE"));
        clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new PrvClient("Private Organisation", "Address", "P000001", "17th Apr 2011", "ACTIVE"));
        servList.add(new Service("S000001", "I00001", "D999999", "5, Jalan Sungai Beranang", "17th Apr 2011"));
        servList.add(new Service("S000002", "G00001", "D999998", "Lot 1-3 Starhill", "17th Apr 2011"));
        servList.add(new Service("S000003", "N00001", "D999997", "Lot 3-10 Jalan Taylor", "17th Apr 2011"));
        servList.add(new Service("S000004", "P00001", "D999996", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        subsList.add(new Subscription("S000001", 1, "P01"));
        subsList.add(new Subscription("S000001", 2, "P01"));
        subsList.add(new Subscription("S000001", 3, "P02"));
        subsList.add(new Subscription("S000002", 1, "P01"));
        subsList.add(new Subscription("S000003", 1, "P03"));
        subsList.add(new Subscription("S000004", 1, "P04"));
        pkgList.add(new TVPackage("P01", "Variety", "18/04/2012", 40.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P02", "Fun", "18/04/2012", 350.00, "Yearly", "ACTIVE"));
        //TVPackage(String pkgCode, String pkgName, String startDate, double chargePrice, String chargeType, String pkgStatus)
        if (!existFile) {    //if client's file do not exist
            //cl_data.dat file
            try {
                FileOutputStream client_fostream = new FileOutputStream("cl_data.dat");
                ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream);
//				ObjectOutputStream client_oostream = new ObjectOutputStream(new FileOutputStream("cl_data.dat"));
                for (i = 0; i < clientList.size(); i++) {
                    if (clientList.get(i) != null) {
                        client_oostream.writeObject(clientList);
                    }
                }
                client_oostream.flush();
                client_oostream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //serv_data.dat files
            try {
                FileOutputStream serv_fostream = new FileOutputStream("serv_data.dat");
                ObjectOutputStream serv_oostream = new ObjectOutputStream(serv_fostream);
                for (i = 0; i < servList.size(); i++) {
                    if (servList.get(i) != null) {
                        serv_oostream.writeObject(servList);
                    }
                }
                serv_oostream.flush();
                serv_oostream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            //pkg_data.dat files
            try {
                FileOutputStream pkg_fostream = new FileOutputStream("pkg_data.dat");
                ObjectOutputStream pkg_oostream = new ObjectOutputStream(pkg_fostream);
                for (i = 0; i < pkgList.size(); i++) {
                    if (pkgList.get(i) != null) {
                        pkg_oostream.writeObject(pkgList);
                    }
                }
                pkg_oostream.flush();
                pkg_oostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //subsc_data.dat
            try {
                FileOutputStream subsc_fostream = new FileOutputStream("subsc_data.dat");
                ObjectOutputStream subsc_oostream = new ObjectOutputStream(subsc_fostream);
                for (i = 0; i < subsList.size(); i++) {
                    if (subsList.get(i) != null) {
                        subsc_oostream.writeObject(subsList);
                    }
                }
                subsc_oostream.flush();
                subsc_oostream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {        //reading files
            //reading cl_data.dat
            try {
                FileInputStream client_fistream = new FileInputStream("cl_data.dat");            //read from specified file
                ObjectInputStream client_oistream = new ObjectInputStream(client_fistream);

                for (i = 0; i < clientList.size(); i++) {
                    if (clientList.get(i) != null) {
                        clientList = (LinkedList<ClientAccount>) client_oistream.readObject();
                    }
                }

                client_oistream.close();
            } catch (Exception e) {
            }

            //reading serv_data.dat
            try {
                FileInputStream serv_fistream = new FileInputStream("serv_data.dat");            //read from specified file
                ObjectInputStream serv_oistream = new ObjectInputStream(serv_fistream);

                for (i = 0; i < servList.size(); i++) {
                    if (servList.get(i) != null) {
                        servList = (LinkedList<Service>) serv_oistream.readObject();
                    }
                }

                serv_oistream.close();
            } catch (Exception e) {
            }

            //reading pkg_data.dat
            try {
                FileInputStream pkg_fistream = new FileInputStream("pkg_data.dat");            //read from specified file
                ObjectInputStream pkg_oistream = new ObjectInputStream(pkg_fistream);

                for (i = 0; i < pkgList.size(); i++) {
                    if (pkgList.get(i) != null) {
                        pkgList = (LinkedList<TVPackage>) pkg_oistream.readObject();
                    }
                }

                pkg_oistream.close();
            } catch (Exception e) {
            }

            //reading subsc_data.dat
            try {
                FileInputStream subsc_fistream = new FileInputStream("subsc_data.dat");            //read from specified file
                ObjectInputStream subsc_oistream = new ObjectInputStream(subsc_fistream);

                for (i = 0; i < subsList.size(); i++) {
                    if (subsList.get(i) != null) {
                        subsList = (LinkedList<Subscription>) subsc_oistream.readObject();
                    }
                }

                subsc_oistream.close();
            } catch (Exception e) {
            }

        } //end else
    }


    /*-------------------------------------METHOD ONE - USER LOGIN--------------------------------------------*/
    public void loginMenu() {
        do {
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1. User login");
            p.println("2. Exit");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu1_opt = input.next();

            if ((menu1_opt.matches("^[1-9]{1}$"))/*||(menu1_opt.matches("^[1]([0]|[1])$"))*/) {
                menu1_opt_1 = Integer.parseInt(menu1_opt);

                switch (menu1_opt_1) {
                    case 1:                                                //User login part
                        p.println("\n-----------------------");
                        p.println("USER LOGIN INTERFACE:");
                        p.println("-----------------------");

                        boolean usernameVal = false;
                        boolean pwVal = false;

                        while (usernameVal == false) {
                            p.print("Username: ");
                            username = input.next();

                            for (u = 0; u < userList.size(); u++) {
                                if ((username.equalsIgnoreCase(userList.get(u).getUserID())) == false) {
                                    if (u == (userList.size() - 1)) {
                                        p.println("User not found!\n");
                                        usernameVal = false;
                                    }
                                } else {
                                    usernameVal = true;
                                    break;
                                }
                            }
                        }

                        while (pwVal == false) {
                            p.print("Password: ");
                            password = input.next();

                            for (i = 0; i < userList.size(); i++) {
                                if ((password.equals(userList.get(u).getPassword())) == false) {
                                    if (i == (userList.size() - 1)) {
                                        p.println("Password does not matched!\n");
                                        pwVal = false;
                                    } //end inner if
                                } else {
                                    pwVal = true;
                                    login = true;

                                    log = new LogFile(username, "has logged into the system.");
                                    logList.addLast(log);

                                    break;
                                } //end else
                            }//end for
                        }// end while
                        showMenu();                        //show menubar
                        break;

                    case 2:        //Good Bye!
                        p.print("Thank you for using the system. Good Bye!\n");
                        System.exit(0);

                        break;

                    default:

                        p.println("WRONG OPTION! PLEASE RE-ENTER YOUR OPTION!\n");
                        break;
                }
            } else {
                p.println("ERROR! Please select the function in the menu list only!\n");
            }
        } while ((menu1_opt_1 != 2) && (login == false));
    } //login method

    public void showMenu() {
        do {
            p.println("\nMENU");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1.\tDisplay all clients");
            p.println("2.\tDisplay clients by type");
            p.println("3.\tDisplay specific client's Details");
            p.println("4.\tManage client profiles - Add/Edit/Terminate");
            p.println("5.\tManage client services - Add/Edit/Terminate");
            p.println("6.\tManage subscriptions - Add/Edit/Terminate");
            p.println("7.\tManage TV packages- Add/Edit/Terminate");
            p.println("8.\tManage TV Programmes - Add/Edit/Terminate");
            p.println("9.\tManage User - Add/Change Password/Terminate");
            p.println("10.\tGenerate Report");
            p.println("11.\tLog off");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu2_opt = input.next();

            if ((menu2_opt.matches("^[1-9]{1}$")) || (menu2_opt.matches("^[1][0-2]$"))) {
                menu2_opt_1 = Integer.parseInt(menu2_opt);

                switch (menu2_opt_1) {
                    case 1:
                        displayClients();
                        break;
                    case 2:
                        displayClientsByType();
                        break;
                    case 3:
                        displayCLDetails();
                        break;
                    case 4:
                        manageClients();
                        break;

                }
            }
        } while ((menu2_opt_1 != 10) && (login));
    } //end showMenu

    public void displayClients() {
        p.println("\nDisplay Clients");
        p.println("---------------");

        log = new LogFile(username, "has chosen the 'Display Clients' function.");
        logList.addLast(log);

        for (i = 0; i < clientList.size(); i++) {
            p.println(clientList.get(i).getName());
        }

        log = new LogFile(username, "has displayed clients.");
        logList.addLast(log);
    } //end display client

    public void displayClientsByType() {
        String type;
        boolean val1_o2 = false, val2_o2 = false;

        log = new LogFile(username, "has chosen the 'Display Client by Type' function.");
        logList.addLast(log);

        p.println("\nDisplay Client by Type");
        p.println("----------------------");

        do {
            p.print("Enter the client's type(Individual,Gov,NGO,Private): ");
            type = input.next();

            if (type.length() > 0) {
                val1_o2 = true;
            } else {
                p.println("ERROR! WRONG INPUT! PLEASE TRY AGAIN!\n");
            }

            boolean val_idv = false;
            boolean val_gov = false;
            boolean val_ngo = false;
            boolean val_prv = false;

            for (i = 0; i < clientList.size(); i++) {
                if (clientList.get(i) instanceof IndividualClient) {
                    val_idv = true;
                    break;
                }
            }

            for (i = 0; i < clientList.size(); i++) {
                if (clientList.get(i) instanceof GovClient) {
                    val_gov = true;
                    break;
                }
            }

            for (i = 0; i < clientList.size(); i++) {
                if (clientList.get(i) instanceof NGOClient) {
                    val_ngo = true;
                    break;
                }
            }

            for (i = 0; i < clientList.size(); i++) {
                if (clientList.get(i) instanceof PrvClient) {
                    val_prv = true;
                    break;
                }
            }

            //Individual
            if (type.equalsIgnoreCase("Individual")) {
                if (val_idv) {
                    p.println("\n\nRegistered Individual Client(s) is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof IndividualClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Individual type.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client register for Individual type yet.");

                    log = new LogFile(username, "has not displayed the clients in Individual type[NO CLIENTS].");
                    logList.addLast(log);
                }
            }

            //Government
            else if (type.equalsIgnoreCase("Gov")) {
                if (val_gov) {
                    p.println("Registered Government Client(s) is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof GovClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Government type.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client register for Government type yet.");

                    log = new LogFile(username, "has not displayed the clients in Government type[NO CLIENTS].");
                    logList.addLast(log);
                }
            }

            //NGO
            else if (type.equalsIgnoreCase("NGO")) {
                if (val_ngo) {
                    p.println("Registered NGO Client(s) is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof NGOClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in NGO type.");
                    logList.addLast(log);

                    val2_o2 = true;

                } else {

                    p.println("There is no client register for NGO type yet.");

                    log = new LogFile(username, "has not displayed the clients in NGO type[NO CLIENTS].");
                    logList.addLast(log);
                }
            }

            //Private Organisation
            else if (type.equalsIgnoreCase("Private")) {
                if (val_prv) {
                    p.println("Registered Private Organisation Client(s) is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof PrvClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Private Organisation type.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client register for Private Organisation type yet.");

                    log = new LogFile(username, "has not displayed the clients in Private Organisation type[NO CLIENTS].");
                    logList.addLast(log);
                }
            } //end else if

            else {
                p.println("Error! Client type does not exist!");

                log = new LogFile(username, "has not displayed the clients in type[TYPE DOES NOT EXIST].");
                logList.addLast(log);
            } //end else

        } while (val1_o2 == false); //end do-while loop

        val1_o2 = false;
    } // end display client by type

    public void displayCLDetails() {
        String valID_f3;
        boolean val1_f3 = false;    //check emptiness

        log = new LogFile(username, "has chosen the 'Display Client's Details' function.");
        logList.addLast(log);

        p.println("\nDisplay Client's Details");
        p.println("------------------------");

        do {
            p.print("Please enter ID of client you would like to display the details for: ");
            valID_f3 = input.next();

            if (valID_f3.length() > 0) {
                val1_f3 = true;
            } else {
                p.println("ERROR! WRONG INPUT! PLEASE TRY AGAIN!\n");
            }
        } while (val1_f3 == false);

        for (i = 0; i < clientList.size(); i++) {
            if (valID_f3.equalsIgnoreCase(clientList.get(i).getClientID())) {
                clientList.get(i).printClient();
                System.out.println("Service details");

                //Display services
                int a = 0, b = 0, c = 0;
                for (f = 0; f < servList.size(); f++) {
                    if ((clientList.get(i).getClientID()).equals(servList.get(f).getClientID())) {
                        servList.get(f).printServ();
                    }
                    a = f;
                }

                //Display subscriptions
                for (f = 0; f < subsList.size(); f++) {
                    if ((servList.get(a).getSmartCardNo().equals(subsList.get(f).getSmartCardNo()))) {
                        subsList.get(f).printSubs();
                    }
                    b = f;
                }

                //Display packages
                for (f = 0; f < pkgList.size(); f++) {
                    if ((subsList.get(b).getPkgCode().equals(pkgList.get(f).getPkgCode()))) {
                        pkgList.get(f).printPkg();
                    }
                    c = f;
                }


                log = new LogFile(username, "has displayed Client '" + valID_f3 + "' details.");
                logList.addLast(log);

                break;
            } else {
                if (i == (clientList.size() - 1)) {
                    p.println("Client '" + valID_f3 + "' does not exist!");
                    log = new LogFile(username, "has not displayed Client '" + valID_f3 + "' details[CLIENT DOES NOT EXIST].");
                    logList.addLast(log);
                }
            }
        }
    }// end display client details

    public void manageClients() {
        String ch1_f4, ch2_f4, valID_f4;
        boolean val1_f4 = false;  //check emptiness and valid string
        boolean val2_f4 = false;

        log = new LogFile(username, "has chosen the 'Manage Client's Profile-Add/Edit/Terminate' function.");
        logList.addLast(log);

        p.println("\nManage Client's Profile-Add/Edit/Terminate");
        p.println("------------------------------------------");

        do {
            p.print("Please select a function(Add/Edit/Terminate): ");
            ch1_f4 = input.next();

            ch1_f4 = ch1_f4.toLowerCase();

            if ((ch1_f4.length() > 0) && ((ch1_f4.equalsIgnoreCase("add") || ch1_f4.equalsIgnoreCase("edit")) || (ch1_f4.equalsIgnoreCase("terminate")))) {
                val1_f4 = true;
            } else {
                p.println("ERROR! WRONG INPUT! PLEASE TRY AGAIN!\n");
            }
        } while (val1_f4 == false);

        switch (ch1_f4) {
            case "add":        //Add a new client's profile
                log = new LogFile(username, "has chosen to add a new client.");
                logList.addLast(log);

                do {
                    p.print("Please enter client's type(Individual,Gov,NGO,Private):\n ");
                    String type = input.next();
                    p.println();

                    switch (type.toLowerCase()) {

                        case "individual":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.next();
                                p.println();

                                p.print("Please enter client's age: ");
                                int age = input.nextInt();
                                p.println();

                                p.print("Please enter client's IC number: ");
                                String ic = input.next();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.next();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.next();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.next();
                                p.println();

                                p.print("Please enter status of Account: ");
                                String accStatus = input.next();
                                p.println();

                                clientList.add(new IndividualClient(name, age, ic, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "gov":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.next();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.next();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.next();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.next();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.next();
                                p.println();

                                clientList.add(new GovClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "ngo":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.next();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.next();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.next();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.next();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.next();
                                p.println();

                                clientList.add(new NGOClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "private":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.next();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.next();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.next();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.next();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.next();
                                p.println();

                                clientList.add(new PrvClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        default:
                            p.println("ERROR! WRONG INPUT! PLEASE TRY AGAIN!\n");
                            break;
                    }
                } while (val2_f4 == false);

                break;
            case "edit":
                log = new LogFile(username, "has chosen to edit a client.");
                logList.addLast(log);

                p.print("Enter the Client's ID that you would like to edit: ");
                valID_f4 = input.next();

                for (i = 0; i < clientList.size(); i++) {
                    if (valID_f4.equalsIgnoreCase(clientList.get(i).getName())) {
                        p.print("Select data to edit(Name/Age/IC/Address/CreationDate/AccountStatus): ");
                        ch2_f4 = input.next();

                        switch (ch2_f4.toLowerCase()) {
                            case "name":
                                p.println("Enter the new name: ");
                                String newName_f4 = input.next();

                                clientList.get(i).setName(newName_f4);

                                p.println("\nClient '" + valID_f4 + "' 's name has been edited to '" + newName_f4 + "'.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's name to '" + newName_f4 + "'.");
                                logList.addLast(log);
                                break;

                            case "age":
                                if (clientList.get(i) instanceof IndividualClient) {    //Re-casting
                                    IndividualClient client = (IndividualClient) clientList.get(i);

                                    p.println("Enter the new age: ");
                                    int newAge_f4 = input.nextInt();

                                    client.setAge(newAge_f4);

                                    p.println("\nClient '" + valID_f4 + "' 's age has been edited.");
                                    log = new LogFile(username, "has edited client '" + valID_f4 + "' 's age.");
                                    logList.addLast(log);
                                    break;
                                } else {
                                    p.println("\nThis is only for Individual Client!");
                                    log = new LogFile(username, "has not edited Client '" + valID_f4 + "' details[CLIENT TYPE NOT ALLOWED].");
                                    logList.addLast(log);
                                    break;
                                }
                            case "ic":
                                if (clientList.get(i) instanceof IndividualClient) {    //Re-casting
                                    IndividualClient client = (IndividualClient) clientList.get(i);

                                    p.println("Enter the new IC: ");
                                    String f4nic = input.next();

                                    client.setIC(f4nic);

                                    p.println("\nClient '" + valID_f4 + "' 's IC has been edited.");
                                    log = new LogFile(username, "has edited client '" + valID_f4 + "' 's IC.");
                                    logList.addLast(log);
                                    break;
                                } else {
                                    p.println("\nThis is only for Individual Client!");
                                    log = new LogFile(username, "has not edited Client '" + valID_f4 + "' details[CLIENT TYPE NOT ALLOWED].");
                                    logList.addLast(log);
                                    break;
                                }
                            case "address":
                                p.println("Enter the new address: ");
                                String f4naddress = input.next();

                                clientList.get(i).setName(f4naddress);

                                p.println("\nClient '" + valID_f4 + "' 's address has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's address.");
                                logList.addLast(log);

                                break;

                            case "creationdate":
                                p.println("Enter the new creation date: ");
                                String f4ndate = input.next();

                                clientList.get(i).setName(f4ndate);

                                p.println("\nClient '" + valID_f4 + "' 's creation date has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's creation date.");
                                logList.addLast(log);
                                break;
                            case "accountstatus":
                                p.println("Enter the new account status(ACTIVE/INACTIVE): ");
                                String f4naccstatus = input.next();

                                clientList.get(i).setName(f4naccstatus);

                                p.println("\nClient '" + valID_f4 + "' 's account status has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's account status.");
                                logList.addLast(log);
                                break;
                            default:
                                p.println("Action '" + ch2_f4 + "' does not exist!");
                                log = new LogFile(username, "has not edited Client '" + valID_f4 + "' details[ACTION DOES NOT EXIST].");
                                logList.addLast(log);
                                break;
                        }
                    } else {
                        if (i == (clientList.size() - 1)) {
                            p.println("\nClient '" + valID_f4 + "' does not exist!");

                            log = new LogFile(username, "has not edited client '" + valID_f4 + "' details[CLIENT DOES NOT EXIST].");
                            logList.addLast(log);
                        }
                    }
                }
                break;
            //Terminate a client's profile
            case "terminate":
                log = new LogFile(username, "has chosen to terminate a client.");
                logList.addLast(log);

                do {
                    p.print("Enter the client's name that you would like to terminate: ");
                    valID_f4 = input.next();

                    if (valID_f4.length() > 0) {
                        val2_f4 = true;
                    } else {
                        p.println("ERROR! WRONG INPUT! PLEASE TRY AGAIN!\n");
                    }
                } while (val2_f4 == false);

                for (i = 0; i < clientList.size(); i++) {
                    if (valID_f4.equalsIgnoreCase(clientList.get(i).getName())) {
                        //clientList.remove(i);
                        clientList.get(i).setAccountStatus("Terminated");

                        p.println("\nClient '" + valID_f4 + "' has been terminated.");

                        log = new LogFile(username, "has terminated Client '" + valID_f4 + "'.");
                        logList.addLast(log);

                        break;
                    } else {
                        if (i == (clientList.size() - 1)) {
                            p.println("Client '" + valID_f4 + "' does not exist!");
                            log = new LogFile(username, "has not terminated Client '" + valID_f4 + "' details[CLIENT DOES NOT EXIST].");
                            logList.addLast(log);
                        }
                    }
                }

                break;
        }
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
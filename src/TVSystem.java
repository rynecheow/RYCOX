import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

class TVSystem {

    Scanner input = new Scanner(System.in);
    String username = "";
    String password = "";
    String menu1_opt = "";
    String menu2_opt = "";
    int i = 0, f = 0, u = 0, menu1_opt_1 = 0, menu2_opt_1 = 0, menu3_opt_1 = 0;
    boolean login = false;
    PrintStream p = System.out;
    boolean check = false;
    boolean repeat = false;
    String clientID;
    String smartCardNo;
    String decoderNo;
    String menu3_opt = "";

    LinkedList<Users> userList = new LinkedList<Users>();
    LinkedList<ClientAccount> clientList = new LinkedList<ClientAccount>();
    LinkedList<Service> servList = new LinkedList<Service>();
    LinkedList<Subscription> subsList = new LinkedList<Subscription>();
    LinkedList<TVPackage> pkgList = new LinkedList<TVPackage>();
    LinkedList<Packaging> pckgingList = new LinkedList<Packaging>();
    LinkedList<TVProgramme> prgList = new LinkedList<TVProgramme>();
    LinkedList<LogFile> logList = new LinkedList<LogFile>();
    LogFile log = new LogFile("", "");
    //List<Integer> t=new LinkedList<Integer>();
    File client_file = new File("cl_data.dat");
    boolean existFile = client_file.exists();

    @SuppressWarnings("unchecked")
    public TVSystem() {
        //pre-defined objects
        userList.add(new Administrators("admin", "nimda"));
        userList.add(new FrontdeskStaffs("staff", "123abc"));
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I000001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "17th Apr 2011", "INACTIVE"));
        clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "17th Apr 2011", "ACTIVE"));
        clientList.add(new PrvClient("Private Organisation", "Address", "P000001", "17th Apr 2011", "ACTIVE"));
        servList.add(new Service("S000001", "I00001", "D999999", "5, Jalan Sungai Beranang", "17th Apr 2011"));
        servList.add(new Service("S000002", "G00001", "D999998", "Lot 1-3 Starhill", "17th Apr 2011"));
        servList.add(new Service("S000003", "N00001", "D999997", "Lot 3-10 Jalan Taylor", "17th Apr 2011"));
        servList.add(new Service("S000004", "P00001", "D999996", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        servList.add(new Service("S000005", "I00002", "D999995", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        servList.add(new Service("S000006", "P000002", "D999994", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        subsList.add(new Subscription("S000001", 1, "P01"));
        subsList.add(new Subscription("S000001", 2, "P01"));
        subsList.add(new Subscription("S000001", 3, "P02"));
        subsList.add(new Subscription("S000002", 1, "P01"));
        subsList.add(new Subscription("S000003", 1, "P03"));
        subsList.add(new Subscription("S000004", 1, "P04"));
        pkgList.add(new TVPackage("P01", "Variety", "18/04/2012", 40.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P02", "Fun", "18/04/2012", 350.00, "Yearly", "ACTIVE"));
        pckgingList.add(new Packaging("P01", "F001"));
        prgList.add(new TVProgramme("F001", "My Boss My Hero", "Japanese Comedy Drama series about Yakuza members.", "Japan", "27/11/2002", "Active", "18SG", "Comedy", ""));


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

            //pckging_data.dat files
            try {
                FileOutputStream pckging_fostream = new FileOutputStream("pckging_data.dat");
                ObjectOutputStream pckging_oostream = new ObjectOutputStream(pckging_fostream);
                for (i = 0; i < pckgingList.size(); i++) {
                    if (pckgingList.get(i) != null) {
                        pckging_oostream.writeObject(pckgingList);
                    }
                }
                pckging_oostream.flush();
                pckging_oostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //prg_data.dat files
            try {
                FileOutputStream prg_fostream = new FileOutputStream("prg_data.dat");
                ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream);
                for (i = 0; i < prgList.size(); i++) {
                    if (prgList.get(i) != null) {
                        prg_oostream.writeObject(pckgingList);
                    }
                }
                prg_oostream.flush();
                prg_oostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            //

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

            //reading pckging_data.dat
            try {
                FileInputStream pckging_fistream = new FileInputStream("pckging_data.dat");            //read from specified file
                ObjectInputStream pckging_oistream = new ObjectInputStream(pckging_fistream);

                for (i = 0; i < pckgingList.size(); i++) {
                    if (pckgingList.get(i) != null) {
                        pckgingList = (LinkedList<Packaging>) pckging_oistream.readObject();
                    }
                }

                pckging_oistream.close();
            } catch (Exception e) {
            }

            //reading prg_data.dat
            try {
                FileInputStream prg_fistream = new FileInputStream("prg_data.dat");            //read from specified file
                ObjectInputStream prg_oistream = new ObjectInputStream(prg_fistream);

                for (i = 0; i < prgList.size(); i++) {
                    if (prgList.get(i) != null) {
                        prgList = (LinkedList<TVProgramme>) prg_oistream.readObject();
                    }
                }
                prg_oistream.close();
            } catch (Exception e) {
            }
        } //end else

        loginMenu();
    }


    /*--------------------------------------------------------------------------METHOD ONE- USER LOGIN----------------------------------------------------------------------------------------*/
    public void loginMenu() {
        do {
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1. User login");
            p.println("2. Exit");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu1_opt = input.nextLine();

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
                            username = input.nextLine();

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
                            password = input.nextLine();

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

                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while ((menu1_opt_1 != 2) && (login == false));
    } //login method

    /*--------------------------------------------------------------------------METHOD TWO - MENU----------------------------------------------------------------------------------------*/
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
            menu2_opt = input.nextLine();

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
                    case 5:
                        manageService();
                        break;
                    case 6:
                        manageSubscription();
                        break;
                    case 7:
                        //managePackage();
                        break;
                    case 8:
                        //manageProgramme();
                        break;
                    case 9:
                        manageUsers();
                        break;
                    case 10:
                        //reportGener();
                        break;
                    case 11:
                        logout();
                        break;
                    case 12:
                        //secretFunction();
                        break;
                }
            }
        } while ((menu2_opt_1 != 10) && (login));
    } //end showMenu

    /*--------------------------------------------------------------------------METHOD THREE - DISPLAY CLIENTS----------------------------------------------------------------------------------------*/
    public void displayClients() {
        p.println("\nDisplay Clients");
        p.println("---------------");

        log = new LogFile(username, "has chosen the 'Display Clients' function.");
        logList.addLast(log);

        for (i = 0; i < clientList.size(); i++) {
            p.println(clientList.get(i).getName() + "\t" + clientList.get(i).getClientID());
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
            p.print("Enter the client's type(Individual,Gov,NGO,Private): \n\n");
            p.println("Enter INDIVIDUAL to display all clients from the 'Individual' category.\n");
            p.println("Enter GOV to display all clients from the 'Government' category.\n");
            p.println("Enter NGO to display all clients from the 'NGO' category.\n");
            p.println("Enter PRIVATE to display all clients from the 'Private Organisation' category..\n");
            p.println("-----------------------------------------------------");
            type = input.nextLine();

            if (type.length() > 0) {
                val1_o2 = true;
            } else {
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
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
                    p.println("\n\nRegistered client(s) from the 'Individual' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof IndividualClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Individual category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Individual category yet.");

                    log = new LogFile(username, "has not displayed the clients in Individual category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } else if (type.equalsIgnoreCase("Gov")) {//Government
                if (val_gov) {
                    p.println("Registered client(s) from the 'Government' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof GovClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Government category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Government category yet.");

                    log = new LogFile(username, "has not displayed the clients in Government category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } else if (type.equalsIgnoreCase("NGO")) {//NGO
                if (val_ngo) {
                    p.println("Registered client(s) from the 'NGO' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof NGOClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in NGO type.");
                    logList.addLast(log);

                    val2_o2 = true;

                } else {

                    p.println("There is no client registered under the NGO category yet.");

                    log = new LogFile(username, "has not displayed the clients in NGO category[NO CLIENTS].");
                    logList.addLast(log);
                }
            }

            //Private Organisation
            else if (type.equalsIgnoreCase("Private")) {
                if (val_prv) {
                    p.println("Registered client(s) from the 'Private Organisation' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof PrvClient) {
                            p.println(clientList.get(i).getName());
                        }
                    }

                    log = new LogFile(username, "has displayed the clients in Private Organisation category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Private Organisation category yet.");

                    log = new LogFile(username, "has not displayed the clients in Private Organisation category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } //end else if

            else {
                p.println("Error! Client type does not exist!");

                log = new LogFile(username, "has not displayed the any client.[TYPE DOES NOT EXIST].");
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
            valID_f3 = input.nextLine();

            if (valID_f3.length() > 0) {
                val1_f3 = true;
            } else {
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while (val1_f3 == false);

        for (i = 0; i < clientList.size(); i++) {
            p.println("Client details");
            if (valID_f3.equalsIgnoreCase(clientList.get(i).getClientID())) {
                clientList.get(i).printClient();
                p.println("\nService details");

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

    /*--------------------------------------------------------------------------METHOD FOUR - MANAGE CLIENTS----------------------------------------------------------------------------------------*/
    public void manageClients() {
        String ch1_f4, ch2_f4, valID_f4;
        boolean val1_f4 = false;  //check emptiness and valid string
        boolean val2_f4 = false;

        log = new LogFile(username, "has chosen the 'Manage Client's Profile' function.");
        logList.addLast(log);

        p.println("\nManage Client's Profile");
        p.println("-----------------------------------------------------");


        do {
            p.println("Please select a function(Add/ Edit/ Terminate): \n");
            p.println("Enter ADD to add a new client.\n");
            p.println("Enter EDIT to edit an existing client.\n");
            p.println("Enter TERMINATE to terminate an existing client.\n");
            p.println("-----------------------------------------------------");
            ch1_f4 = input.nextLine();

            ch1_f4 = ch1_f4.toLowerCase();

            if ((ch1_f4.length() > 0) && ((ch1_f4.equalsIgnoreCase("add") || ch1_f4.equalsIgnoreCase("edit")) || (ch1_f4.equalsIgnoreCase("terminate")))) {
                val1_f4 = true;
            } else {
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while (val1_f4 == false);

        switch (ch1_f4) {
            case "add":        //Add a new client's profile
                log = new LogFile(username, "has chosen to add a new client profile.");
                logList.addLast(log);

                do {
                    p.print("Please enter client's type (Individual,Gov,NGO,Private):\n ");
                    String type = input.nextLine();
                    p.println();

                    switch (type.toLowerCase()) {

                        case "individual":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.nextLine();
                                p.println();

                                p.print("Please enter client's age: ");
                                int age = input.nextInt();
                                p.println();

                                p.print("Please enter client's IC number: ");
                                String ic = input.nextLine();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.nextLine();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.nextLine();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                p.print("Please enter status of Account: ");
                                String accStatus = input.nextLine();
                                p.println();

                                clientList.add(new IndividualClient(name, age, ic, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "gov":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.nextLine();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.nextLine();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.nextLine();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.nextLine();
                                p.println();

                                clientList.add(new GovClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "ngo":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.nextLine();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.nextLine();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.nextLine();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.nextLine();
                                p.println();

                                clientList.add(new NGOClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "private":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.nextLine();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.nextLine();
                                p.println();

                                p.print("Please enter client's ID: ");
                                String clientID = input.nextLine();
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                p.print("Please enter Status of Account: ");
                                String accStatus = input.nextLine();
                                p.println();

                                clientList.add(new PrvClient(name, address, clientID, creationDate, accStatus));
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        default:
                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                            break;
                    }
                } while (val2_f4 == false);

                break;
            case "edit":
                log = new LogFile(username, "has chosen to edit a client.");
                logList.addLast(log);

                p.print("Enter the Client's ID that you would like to edit: ");
                valID_f4 = input.nextLine();

                for (i = 0; i < clientList.size(); i++) {
                    if (valID_f4.equalsIgnoreCase(clientList.get(i).getClientID())) {
                        p.print("Select data to edit(Name/Age/IC/Address/CreationDate/AccountStatus): ");
                        ch2_f4 = input.nextLine();

                        switch (ch2_f4.toLowerCase()) {
                            case "name":
                                p.println("Enter the new name: ");
                                String newName_f4 = input.nextLine();

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
                                    String f4nic = input.nextLine();

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
                                String f4naddress = input.nextLine();

                                clientList.get(i).setName(f4naddress);

                                p.println("\nClient '" + valID_f4 + "' 's address has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's address.");
                                logList.addLast(log);

                                break;

                            case "creationdate":
                                p.println("Enter the new creation date: ");
                                String f4ndate = input.nextLine();

                                clientList.get(i).setName(f4ndate);

                                p.println("\nClient '" + valID_f4 + "' 's creation date has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's creation date.");
                                logList.addLast(log);
                                break;
                            case "accountstatus":
                                p.println("Enter the new account status(ACTIVE/INACTIVE): ");
                                String f4naccstatus = input.nextLine();

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
                    p.print("Enter the client's ID that you would like to terminate: ");
                    valID_f4 = input.nextLine();

                    if (valID_f4.length() > 0) {
                        val2_f4 = true;
                    } else {
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                    }
                } while (val2_f4 == false);

                for (i = 0; i < clientList.size(); i++) {
                    if (valID_f4.equalsIgnoreCase(clientList.get(i).getClientID())) {
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
    } // end manage client

    /*--------------------------------------------------------------------------METHOD FIVE - MANAGE SERVICES----------------------------------------------------------------------------------------*/
    public void manageService() {
        check = false;

        do {
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1. Create new Service");
            p.println("2. Edit Service");
            p.println("3. Terminated Service");
            p.println("4. Back");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu3_opt = input.nextLine();

            if ((menu3_opt.matches("^[1-4]{1}$")) || (menu3_opt.matches("^[0]([0]|[1])$"))) {
                menu3_opt_1 = Integer.parseInt(menu3_opt);

                String clientID;
                String smartCardNo;
                String decoderNo;

                switch (menu3_opt_1) {
                    case 1:
                        check = false;

                        do {
                            p.println("Please Enter the Client ID");
                            clientID = input.nextLine();

                            for (i = 0; i < clientList.size(); i++) {
                                if (clientID.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                    check = true;
                                }
                            }

                            if (check == false)
                                p.println("Invalid Client ID, Please Check the Client ID.");


                        } while (check == false);

                        do {
                            check = true;

                            p.println("Please Enter the Smart Card Number: ");
                            smartCardNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    check = false;
                                    p.println("This is not a new Smart Card");
                                    break;
                                }
                            }
                        } while (check == false);

                        do {
                            check = true;

                            p.println("Please Enter the Decoder Number: ");
                            decoderNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (decoderNo.equalsIgnoreCase(servList.get(i).getDecodeNo())) {
                                    check = false;
                                    p.println("This is not a new Decoder");
                                    break;
                                }
                            }
                        } while (check == false);

                        p.println("Please Enter the Address: ");
                        String address = input.nextLine();

                        p.println("Please Enter Rigistration Date.");
                        String registrationDate = input.nextLine();

                        servList.add(new Service(smartCardNo, decoderNo, clientID, address, registrationDate));

                        for (int i = 0; i < servList.size(); i++) {
                            if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                servList.get(i).setServStatus("Active");
                                p.println("Your Service has been Active.");
                            }
                        }

                        log = new LogFile(username, "has created a new Service");
                        logList.addLast(log);
                        break;


                    case 2:
                        check = false;

                        do {
                            p.println("Please Enter the Smart Card Number:");
                            smartCardNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    check = true;

                                    do {
                                        p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                        p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
                                        p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                        p.println("1. Edit Address");
                                        p.println("2. Edit Smart Card Number");
                                        p.println("3. Edit Decoder Number");
                                        p.println("4. Edit Status");
                                        p.println("5. Back");
                                        p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                        p.print("\nEnter Option: ");
                                        menu3_opt = input.nextLine();

                                        if ((menu3_opt.matches("^[1-5]{1}$")) || (menu3_opt.matches("^[0]([0]|[1])$"))) {
                                            menu3_opt_1 = Integer.parseInt(menu3_opt);

                                            switch (menu3_opt_1) {
                                                case 1:
                                                    check = false;

                                                    p.println("Please enter the new address.");
                                                    address = input.nextLine();

                                                    servList.get(i).setAddress(address);
                                                    check = true;

                                                    log = new LogFile(username, "has edited address.");
                                                    logList.addLast(log);
                                                    break;

                                                case 2:
                                                    do {
                                                        check = true;

                                                        p.println("Please enter the new Smart Card Number.");
                                                        smartCardNo = input.nextLine();

                                                        for (int j = 0; j < servList.size(); j++) {
                                                            if (smartCardNo.equalsIgnoreCase(servList.get(j).getSmartCardNo())) {
                                                                check = false;
                                                                break;
                                                            }
                                                        }

                                                        if (check == false)
                                                            p.println("It isn't a new Smart Card.");

                                                        else if (check = true) {
                                                            servList.get(i).setSmartCardNo(smartCardNo);
                                                            p.println("Smart Card change Success.");
                                                        }
                                                    } while (check == false);

                                                    log = new LogFile(username, "has edited Smart Card Number.");
                                                    logList.addLast(log);
                                                    break;

                                                case 3:
                                                    do {
                                                        check = true;

                                                        p.println("Please enter the new Decoder Number.");
                                                        decoderNo = input.nextLine();

                                                        for (int j = 0; j < servList.size(); j++) {
                                                            if (decoderNo.equalsIgnoreCase(servList.get(j).getDecodeNo())) {
                                                                check = false;
                                                                break;
                                                            }
                                                        }

                                                        if (check == false) {
                                                            p.println("It isn't a new Decoder.");
                                                        } else if (check == true) {
                                                            servList.get(i).setDecoderNo(decoderNo);
                                                            p.println("Decoder change Success.");
                                                        }
                                                    } while (check == false);

                                                    log = new LogFile(username, "has edited Decoder Number.");
                                                    logList.addLast(log);
                                                    break;

                                                case 4:
                                                    check = false;

                                                    do {
                                                        p.println("Please Enter the Service Status (Active, Inactive or Barred): ");
                                                        String servStatus = input.nextLine();
                                                        servStatus.toLowerCase();

                                                        if (servStatus.equals("active")) {
                                                            check = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else if (servStatus.equals("inactive")) {
                                                            check = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else if (servStatus.equals("barred")) {
                                                            check = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else {
                                                            check = false;
                                                            p.println("Invalid Enter.");
                                                        }
                                                    } while (check == false);

                                                    log = new LogFile(username, "has edited the status.");
                                                    logList.addLast(log);
                                                    break;

                                                case 5:
                                                    check = true;
                                                    break;

                                                default:
                                                    check = false;
                                                    p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                                    break;
                                            }
                                        } else {
                                            check = false;
                                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                        }
                                    } while ((menu3_opt_1 != 5) || (check == false));
                                    break;
                                }
                            }

                            if (check == false) {
                                p.println("Invalid Smart Card Number, Please Check the Smart Card Number.");
                            }
                        } while (check == false);

                        break;

                    case 3:
                        for (u = 0; u < userList.size(); u++) {
                            if (userList.get(u) instanceof FrontdeskStaffs) {
                                p.println("Only Admin Can Access.");
                                break;
                            } else if (userList.get(u) instanceof Administrators) {
                                do {
                                    check = false;
                                    p.println("Please Enter the Smart Card No: ");
                                    smartCardNo = input.nextLine();

                                    for (i = 0; i < servList.size(); i++) {
                                        if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                            check = true;

                                            servList.get(i).setServStatus("terminated");
                                            p.println("The Smart Card has been terminated.");
                                        }
                                    }

                                    if (check == false) {
                                        p.println("Invalid Smart Card Enter.");
                                    }
                                } while (check == false);
                                break;
                            }
                        }

                        log = new LogFile(username, "has access the terminated service.");
                        logList.addLast(log);
                        break;

                    case 4:
                        showMenu();
                        break;

                    default:
                        check = false;
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                check = false;
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 4) || (check == false));
    } // end manage service

    /*--------------------------------------------------------------------------METHOD SIX - MANAGE SUBSCRIPTION----------------------------------------------------------------------------------------*/
    public void manageSubscription() {
        check = false;

        do {
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1. Create new Subsciption");
            p.println("2. Edit Subsciption");
            p.println("3. Back");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu3_opt = input.nextLine();

            if ((menu3_opt.matches("^[1-3]{1}$")) || (menu3_opt.matches("^[0]([0]|[1])$"))) {
                menu3_opt_1 = Integer.parseInt(menu3_opt);

                String clientID;
                String smartCardNo;
                String pkgCode;
                String select;
                boolean repeat = false;
                int subsNo = 1;

                switch (menu3_opt_1) {
                    case 1:

                        do {
                            check = true;

                            p.println("Please Enter the Smart Card want to Subscibe package: ");
                            smartCardNo = input.nextLine();

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    p.println("This Smart Card have a subsciption already.");
                                    check = false;
                                    break;
                                }
                            }

                            if (check == true) {
                                check = false;
                                for (i = 0; i < servList.size(); i++) {
                                    if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                        check = true;
                                        break;
                                    }
                                }

                                if (check == true) {
                                    clientID = servList.get(i).getClientID();

                                    for (i = 0; i < servList.size(); i++) {
                                        if (clientID == servList.get(i).getClientID()) {
                                            subsNo++;
                                        }
                                    }
                                } else if (check == false) {
                                    p.println("Invalid Smart Card enter, Please Enter again.");
                                }
                            }
                        } while (check == false);

                        do {
                            check = false;

                            p.println("Please Enter the Package Code want to Subscribe: ");
                            pkgCode = input.nextLine();

                            for (i = 0; i < pkgList.size(); i++) {
                                if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                    check = true;
                                    p.println("Package has added successfully.");
                                    break;
                                }
                            }

                            if (check == true) {
                                subsList.add(new Subscription(smartCardNo, subsNo, pkgCode));

                                do {
                                    check = false;
                                    repeat = false;

                                    p.println("Do you want to add more package?");
                                    p.println("Please Enter 'Y' for Yes or 'N' for No: ");
                                    String enter = input.nextLine();

                                    if ((enter.equalsIgnoreCase("y")) || (enter.equalsIgnoreCase("n"))) {
                                        check = true;

                                        if (enter.equalsIgnoreCase("y")) {
                                            repeat = true;
                                        } else if (enter.equalsIgnoreCase("n")) {
                                            repeat = false;
                                        }

                                        break;
                                    }

                                    if (check == false) {
                                        p.println("Invalid Enter, Please Enter again.");
                                    }
                                } while (check == false);
                            } else if (check == false) {
                                p.println("Invalid Package Code, Please Enter Again!");
                            }

                        } while ((check == false) || (repeat == true));

                        log = new LogFile(username, "has create a new subscription.");
                        logList.addLast(log);
                        break;

                    case 2:
                        check = false;

                        do {
                            p.println("Please Enter the Smart Card Number: ");
                            smartCardNo = input.nextLine();

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    check = true;
                                    subsNo = subsList.get(i).getSubsNo();
                                    break;
                                }
                            }

                            if (check == false) {
                                p.println("Invalid Smart Card Number, Please Enter again.");
                            }
                        } while (check == false);

                        check = false;

                        do {
                            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
                            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                            p.println("1. Add Package");
                            p.println("2. Remove Package");
                            p.println("3. Back");
                            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                            p.print("\nEnter Option: ");
                            menu3_opt = input.nextLine();

                            if ((menu3_opt.matches("^[1-3]{1}$")) || (menu3_opt.matches("^[0]([0]|[1])$"))) {
                                menu3_opt_1 = Integer.parseInt(menu3_opt);

                                switch (menu3_opt_1) {
                                    case 1:
                                        check = false;

                                        do {
                                            p.println("Please Enter the Pakage want to add: ");
                                            pkgCode = input.nextLine();

                                            for (i = 0; i < subsList.size(); i++) {
                                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                    if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                        check = true;
                                                        p.println("You have already Subscibe the package.");
                                                    }
                                                }
                                            }

                                            if (check == false) {
                                                for (i = 0; i < pkgList.size(); i++) {
                                                    if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                                        check = true;
                                                        break;
                                                    }
                                                }

                                                if (check == true) {
                                                    subsList.add(new Subscription(smartCardNo, subsNo, pkgCode));
                                                    p.println("Package add successful");
                                                } else if (check == false) {
                                                    p.println("Invalid Package Code, Please Enter again.");
                                                }
                                            }
                                        } while (check == false);

                                        log = new LogFile(username, "has added an new package to a service.");
                                        logList.addLast(log);
                                        break;

                                    case 2:
                                        check = false;

                                        do {
                                            p.println("Please Enter the Pakage want to remove: ");
                                            pkgCode = input.nextLine();

                                            for (i = 0; i < pkgList.size(); i++) {
                                                if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                                    check = true;
                                                    break;
                                                }
                                            }

                                            if (check == false) {
                                                p.println("Invalid Package Code Enter, Please Enter agian");
                                            } else if (check == true) {
                                                check = false;

                                                for (i = 0; i < subsList.size(); i++) {
                                                    if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                        if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                            check = true;
                                                            subsList.remove(subsList.get(i));
                                                            p.println("Package remove succeessful");
                                                            break;
                                                        }
                                                    }
                                                }

                                                if (check == false) {
                                                    check = true;
                                                    p.println("You are not subscibe the package.");
                                                }
                                            }
                                        } while (check == false);

                                        log = new LogFile(username, "has remove a package from a service.");
                                        logList.addLast(log);
                                        break;

                                    case 3:
                                        break;

                                    default:
                                        check = false;
                                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                        break;
                                }
                            } else {
                                check = false;
                                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                            }
                        } while ((menu3_opt_1 != 3) || (check == false));

                        break;

                    case 3:
                        showMenu();
                        break;

                    default:
                        check = false;
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                check = false;
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 3) || (check == false));
        ;
    }

    /*--------------------------------------------------------------------------METHOD SEVEN - MANAGE PACKAGES----------------------------------------------------------------------------------------*/
    public void managePackage() {

    }

    /*--------------------------------------------------------------------------METHOD NINE - MANAGE USERS----------------------------------------------------------------------------------------*/
    @SuppressWarnings("unused")
    public void manageUsers() {
        String choice_f9, type_f9, un_f9, oldpw_f9, newpw_1_f9, newpw_2_f9;
        boolean val1_f9 = false, val2_f9 = false, val3_f9 = false;

        log = new LogFile(username, "has chosen the 'Manage User-Add/Change Password/Terminate' function.");
        logList.addLast(log);

        p.println("\nManage User-Add/Change Password/Terminate");
        p.println("--------------------------------------");

        do {
            p.print("Please enter the function you would like to proceed(Add/Change Password/Terminate): ");
            choice_f9 = input.nextLine();
            choice_f9 = choice_f9.toLowerCase();

            if ((choice_f9.length() > 0) && ((choice_f9.equalsIgnoreCase("add") || choice_f9.equalsIgnoreCase("changepassword")) || (choice_f9.equalsIgnoreCase("terminate")))) {
                val1_f9 = true;
            } else {
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while (val1_f9 == false);

        //Add a new user
        switch (choice_f9) {
            case "add":
                log = new LogFile(username, "has chosen to add a new user.");
                logList.addLast(log);

                p.print("Enter the type of new user(Case-insensitive): \n");
                p.print("Enter ADMIN for administrator type user.\n");
                p.print("Enter STAFF for staff type user.\n");
                type_f9 = input.nextLine();
                type_f9 = type_f9.toLowerCase();

                if ((type_f9.equals("admin")) || (type_f9.equals("staff"))) {
                    p.print("Enter new username: ");
                    un_f9 = input.nextLine();

                    for (i = 0; i < userList.size(); i++) {
                        if (un_f9.equalsIgnoreCase(userList.get(i).getUserID())) {
                            p.println("Username already exist!");

                            log = new LogFile(username, "has not added any new user[USERNAME ALREADY EXIST].");
                            logList.addLast(log);
                            break;
                        } else {
                            p.print("Enter password: ");
                            newpw_1_f9 = input.nextLine();

                            p.print("Re-enter password: ");
                            newpw_2_f9 = input.nextLine();

                            if (newpw_1_f9.equals(newpw_2_f9)) {
                                if (type_f9.equals("admin")) {
                                    if (userList.get(u) instanceof Administrators) {
                                        userList.add(new Administrators(un_f9, newpw_2_f9));
                                        p.println("\nNew user '" + un_f9 + "' has been added successfully!");
                                        log = new LogFile(username, "has added a new user '" + un_f9 + "'.");
                                        logList.addLast(log);
                                    } else {
                                        p.println("\nOnly Admin has the accessibility to Terminate a user.");

                                    }

                                } else {
                                    userList.add(new FrontdeskStaffs(un_f9, newpw_2_f9));
                                    p.println("\nNew user '" + un_f9 + "' has been added successfully!");
                                    log = new LogFile(username, "has added a new user '" + un_f9 + "'.");
                                    logList.addLast(log);
                                }
                                break;
                            } else {
                                p.println("\nRe-entered password mis-matched!");
                                log = new LogFile(username, "has not added new user[RE-ENTERED PASSWORD MIS-MATCHED].");
                                logList.addLast(log);
                                break;
                            }
                        }
                    }
                } else {
                    p.println("\nThe user type is not available.");
                    log = new LogFile(username, "has not added any new user[USER TYPE NOT AVAILABLE].");
                    logList.addLast(log);
                }
                break;
            //Change a user's password
            case "changepassword":
                log = new LogFile(username, "has chosen to change user '" + username + "' 's password");
                logList.addLast(log);

                do {
                    p.print("\nEnter the existing password: ");
                    oldpw_f9 = input.nextLine();

                    if (oldpw_f9.length() > 0) {
                        if (oldpw_f9.equals(userList.get(u).getPassword())) {
                            p.print("Enter a new password: ");
                            newpw_1_f9 = input.nextLine();

                            p.print("Re-enter password: ");
                            newpw_2_f9 = input.nextLine();

                            if (newpw_1_f9.equals(newpw_2_f9)) { /*change password*/
                                userList.get(u).changePassword(newpw_2_f9);

                                p.println("\nPassword changed successfully!");

                                log = new LogFile(username, "has changed password.");
                                logList.addLast(log);
                                val3_f9 = true;
                            } else {
                                p.println("\nRe-entered password mis-matched!");

                                log = new LogFile(username, "has not changed password[RE-ENTERED PASSWORD MIS-MATCHED].");
                                logList.addLast(log);
                                break;
                            }
                        } else {
                            p.println("\nPassword mismatch!");

                            log = new LogFile(username, "has not changed password[PASSWORD MIS-MATCHED].");
                            logList.addLast(log);
                            break;
                        }
                    } else {
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                    }
                } while (val3_f9 == false);
                break;

            case "terminate": //Terminate a user
                if (userList.get(u) instanceof Administrators) {
                    log = new LogFile(username, "has chosen to Terminate a user.");
                    logList.addLast(log);

                    do {
                        p.print("Enter the user's name that you would like to Terminate: ");
                        un_f9 = input.nextLine();

                        if (un_f9.length() > 0) {
                            val2_f9 = true;
                        } else {
                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        }
                    } while (val2_f9 == false);

                    if (un_f9.equalsIgnoreCase(username)) {
                        p.println("\nUser cannot Terminate itself!");
                        log = new LogFile(username, "has not Terminated user '" + un_f9 + "' details[USER CANNOT Terminate ITSELF].");
                        logList.addLast(log);
                        break;
                    }

                    for (i = 0; i < userList.size(); i++) {
                        if (un_f9.equalsIgnoreCase(userList.get(i).getUserID())) {
                            userList.remove(i);
                            p.println("\nUser '" + un_f9 + "' has been Terminated.");

                            log = new LogFile(username, "has Terminate user '" + un_f9 + "'.");
                            logList.addLast(log);
                            break;
                        } else {
                            if (i == (userList.size() - 1)) {
                                p.println("\nUser '" + un_f9 + "' does not exist!");
                                log = new LogFile(username, "has not Terminated user '" + un_f9 + "' details[USER DOES NOT EXIST].");
                                logList.addLast(log);
                            }
                        }
                    }
                } else {
                    p.println("\nOnly Admin has the accessibility to Terminate a user.");
                    log = new LogFile(username, "has not Terminated any user[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                    logList.addLast(log);
                }
                break;
            default:
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                break;
        } //end switch
    } //end manage users

    /*--------------------------------------------------------------------------METHOD TEN - LOGOUT----------------------------------------------------------------------------------------*/
    public void logout() {
        log = new LogFile(username, "has chosen the 'Log Off' function.");
        logList.addLast(log);

        p.println("\nYou are successfully logged off from the system.");
        p.println("\nLOG FILE\n------------\n");

        login = false;
        log = new LogFile(username, "has logged off from the system.");
        logList.addLast(log);

        for (i = 0; i < logList.size(); i++) {
            logList.get(i).showLog();
        }
        p.println();

        //file handling for log.txt
        try {
            PrintWriter pw_log2 = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));

            for (i = 0; i < logList.size(); i++) {
                if (logList.get(i) != null) {
                    pw_log2.print((logList.get(i)).getUser() + " ");
                    pw_log2.println((logList.get(i)).getAction());
                }
            }
            pw_log2.close();
        } catch (Exception e) {
        }

        //saving added data to cl_data.dat
        try {
            FileOutputStream client_fostream = new FileOutputStream("cl_data.dat");
            ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream);
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

        //saving added data serv_data.dat
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

        //saving added data subsc_data.dat
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

        //saving added data pkg_data.dat
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
        //end of file handling
        //logList.clear();
        menu2_opt_1 = 0;
    } //end logout
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
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.InputMismatchException;
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
    boolean val = false;
    boolean repeat = false;
    boolean stat = false;
    boolean val_age = false;
    String clientID;
    String smartCardNo;
    String decoderNo;
    String menu3_opt = "";

    /*extra*/
    String pckCode = "", pckCode2 = "", pckCode3 = "", packageCode = "";
    String progCode = "", progCode2 = "", progCode3 = "", progCode4 = "", progCode5 = "", progCode6 = "", programmeCode = "";
    //String username = "";
    //String password = "";
    String pckName = "", pkgName2 = "";
    String chargeType = "", chargeType2 = "";
    String contentOrigin = "", contentOrigin2 = "";
    String strtDate, termDate, crtDate;
    String programmeTitle = "", programmeTitle2 = "";
    String desc = "", desc2 = "";
    String viewerStatus = "", viewerStatus2 = "";
    String notes = "", notes2 = "";
    String progType = "", progType2 = "";
    String choice1 = "Yes";

    int option = 0, option2 = 0, option3 = 0;
    int /*i = 0,*/ j = 0, k = 0;
    int selection = 0, selection2 = 0;
    double chargePrice = 0.0, chargePrice2 = 0.0;
    boolean flag = true, flag2 = true, flag3 = true, flag4 = true, flag5 = true, flag6 = true;
    boolean check = true, check1, check2, check3, check4, check5;

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
    boolean exist_cl_data = client_file.exists();


    @SuppressWarnings("unchecked")
    public TVSystem() {
        //pre-defined objects
        userList.add(new Administrators("admin", "nimda"));
        //userList.add(new FrontdeskStaffs("staff", "123abc"));
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I000001", "17th Apr 2011", "ACTIVE"));
        //clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "17th Apr 2011", "INACTIVE"));
        //clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "17th Apr 2011", "ACTIVE"));
        //clientList.add(new PrvClient("Private Organisation", "Address", "P000001", "17th Apr 2011", "ACTIVE"));	
        servList.add(new Service("S000001", "I000001", "D999999", "5, Jalan Sungai Beranang", "17th Apr 2011"));
        servList.add(new Service("S000002", "I000001", "D999998", "Lot 1-3 Starhill", "17th Apr 2011"));
        //servList.add(new Service("S000003", "N000001", "D999997", "Lot 3-10 Jalan Taylor", "17th Apr 2011"));
        //servList.add(new Service("S000004", "P000001", "D999996", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        //servList.add(new Service("S000005", "I000002", "D999995", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        //servList.add(new Service("S000006", "P000002", "D999994", "32 Jalan Kota Kemuning ", "17th Apr 2011"));
        subsList.add(new Subscription("S000001", 1, "P01"));
        //subsList.add(new Subscription("S000001", 2,"P01"));
        //subsList.add(new Subscription("S000001", 3,"P02"));
        //subsList.add(new Subscription("S000002", 1,"P01"));
        //subsList.add(new Subscription("S000003", 1,"P03"));
        //subsList.add(new Subscription("S000004", 1,"P04"));
        pkgList.add(new TVPackage("P01", "Variety", "18/04/2012", 40.00, "Monthly", "ACTIVE"));
        //pkgList.add(new TVPackage("P02","Fun","18/04/2012",350.00,"Yearly","ACTIVE"));
        //pckgingList.add(new Packaging("P01","F001"));
        //prgList.add(new TVProgramme("F001", "My Boss My Hero","Japanese Comedy Drama series about Yakuza members.", "Japan", "27/11/2002","Active","18SG", "Comedy",""));


        if (!exist_cl_data) {    //if client's file do not exist
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
                        managePackage();
                        break;
                    case 8:
                        manageProgramme();
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
        @SuppressWarnings("unused")
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
        p.println("Client details\n----------------");
        int a = 0, b = 0;
        for (i = 0; i < clientList.size(); i++) {
            if (valID_f3.equalsIgnoreCase(clientList.get(i).getClientID())) {
                clientList.get(i).printClient();

                //Display services
                p.println("\nService details");
                for (int p = 0; p < servList.size(); p++) {
                    if ((clientList.get(i).getClientID()).equalsIgnoreCase(servList.get(p).getClientID())) {
                        servList.get(p).printServ();
                        a = p;
                        break;
                    }
                }

                //Display subscriptions
                for (int q = 0; q < subsList.size(); q++) {
                    if ((servList.get(a).getSmartCardNo().equalsIgnoreCase(subsList.get(q).getSmartCardNo()))) {
                        subsList.get(q).printSubs();
                        b = q;
                        break;
                    }
                }

                //Display packages
                for (int r = 0; r < pkgList.size(); r++) {
                    if ((subsList.get(b).getPkgCode().equalsIgnoreCase(pkgList.get(r).getPkgCode()))) {
                        pkgList.get(r).printPkg();
                        break;
                    }
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
                    input.nextLine(); //fix
                    p.print("Please enter client's type (Individual,Gov,NGO,Private):\n ");
                    String type = input.nextLine();
                    p.println();

                    switch (type.toLowerCase()) {

                        case "individual":
                            try {
                                p.print("Please enter client's name: ");
                                String name = input.nextLine();
                                p.println();
                                int g = 0;
                                do {
                                    p.print("Please enter client's age: ");
                                    String age = input.nextLine();
                                    if (age.matches("^[1-9][0-9]$") || age.matches("^[0][1-9][0-9]$")) {
                                        g = Integer.parseInt(age);
                                        val_age = true;
                                        break;
                                    } else
                                        p.println("Only integer is allowed. [10-99]");
                                    val_age = false;
                                } while (val_age == false);
                                p.println();

                                //input.nextLine(); 												
                                p.print("Please enter client's IC number: ");
                                String ic = input.nextLine();
                                p.println();

                                p.print("Please enter client's address: ");
                                String address = input.nextLine();
                                p.println();

                                String y = "";
                                do {
                                    p.print("Please enter client's ID: ");
                                    String s = input.nextLine();
                                    for (i = 0; i < clientList.size(); i++) {
                                        if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                            p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                            check = false;
                                            break;
                                        } else
                                            y = s;
                                        check = true;
                                    }
                                } while (check == false);
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                String a = "";
                                do {
                                    p.print("Please enter status of Account: ");
                                    String accStatus = input.nextLine();
                                    p.println();
                                    if (accStatus.equalsIgnoreCase("active") || accStatus.equalsIgnoreCase("inactive") || accStatus.equalsIgnoreCase("terminated")) {
                                        a = accStatus;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new IndividualClient(name, g, ic, address, y, creationDate, a));
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

                                String y = "";
                                do {
                                    p.print("Please enter client's ID: ");
                                    String s = input.nextLine();
                                    for (i = 0; i < clientList.size(); i++) {
                                        if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                            p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                            check = false;
                                            break;
                                        } else
                                            y = s;
                                        check = true;
                                    }
                                } while (check == false);
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                String a = "";
                                do {
                                    p.print("Please enter status of Account: ");
                                    String accStatus2 = input.nextLine();
                                    p.println();
                                    if (accStatus2.equalsIgnoreCase("active") || accStatus2.equalsIgnoreCase("inactive") || accStatus2.equalsIgnoreCase("terminated")) {
                                        a = accStatus2;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new GovClient(name, address, y, creationDate, a));
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

                                String y = "";
                                do {
                                    p.print("Please enter client's ID: ");
                                    String s = input.nextLine();
                                    for (i = 0; i < clientList.size(); i++) {
                                        if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                            p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                            check = false;
                                            break;
                                        } else
                                            y = s;
                                        check = true;
                                    }
                                } while (check == false);
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                String a = "";
                                do {
                                    p.print("Please enter status of Account: ");
                                    String accStatus3 = input.nextLine();
                                    p.println();
                                    if (accStatus3.equalsIgnoreCase("active") || accStatus3.equalsIgnoreCase("inactive") || accStatus3.equalsIgnoreCase("terminated")) {
                                        a = accStatus3;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new NGOClient(name, address, y, creationDate, a));
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

                                String y = "";
                                do {
                                    p.print("Please enter client's ID: ");
                                    String s = input.nextLine();
                                    for (i = 0; i < clientList.size(); i++) {
                                        if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                            p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                            check = false;
                                            break;
                                        } else
                                            y = s;
                                        check = true;
                                    }
                                } while (check == false);
                                p.println();

                                p.print("Please enter date of creation: ");
                                String creationDate = input.nextLine();
                                p.println();

                                String a = "";
                                do {
                                    p.print("Please enter status of Account: ");
                                    String accStatus4 = input.nextLine();
                                    p.println();
                                    if (accStatus4.equalsIgnoreCase("active") || accStatus4.equalsIgnoreCase("inactive") || accStatus4.equalsIgnoreCase("terminated")) {
                                        a = accStatus4;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new PrvClient(name, address, y, creationDate, a));
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
                        p.println("Enter NAME to edit the name of the client.\n");
                        p.println("Enter AGE to edit the age of the client.\n");
                        p.println("Enter IC to edit the IC of the client.\n");
                        p.println("Enter ADDRESS to edit the address of the client.\n");
                        p.println("Enter CRDATE to creation date the name of the client.\n");
                        p.println("Enter ACCSTAT to edit the account status of the client.\n");
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
                                    //input.nextLine();
                                    p.println("Enter the new age: ");
                                    String newAge_f4 = input.nextLine();

                                    int newAgeI_f4 = Integer.parseInt(newAge_f4);

                                    client.setAge(newAgeI_f4);

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

                            case "crdate":
                                p.println("Enter the new creation date: ");
                                String f4ndate = input.nextLine();

                                clientList.get(i).setName(f4ndate);

                                p.println("\nClient '" + valID_f4 + "' 's creation date has been edited.");
                                log = new LogFile(username, "has edited client '" + valID_f4 + "' 's creation date.");
                                logList.addLast(log);
                                break;
                            case "accstat":
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
                        break;
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
        val = false;

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
                        val = false;

                        do {
                            p.println("Please Enter the Client ID");
                            clientID = input.nextLine();

                            for (i = 0; i < clientList.size(); i++) {
                                if (clientID.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                    val = true;
                                }
                            }

                            if (val == false)
                                p.println("Invalid Client ID, Please Check the Client ID.");


                        } while (val == false);

                        do {
                            val = true;

                            p.println("Please Enter the Smart Card Number: ");
                            smartCardNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    val = false;
                                    p.println("This is not a new Smart Card");
                                    break;
                                }
                            }
                        } while (val == false);

                        do {
                            val = true;

                            p.println("Please Enter the Decoder Number: ");
                            decoderNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (decoderNo.equalsIgnoreCase(servList.get(i).getDecodeNo())) {
                                    val = false;
                                    p.println("This is not a new Decoder");
                                    break;
                                }
                            }
                        } while (val == false);

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
                        val = false;

                        do {
                            p.println("Please Enter the Smart Card Number:");
                            smartCardNo = input.nextLine();

                            for (int i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    val = true;

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
                                                    val = false;

                                                    p.println("Please enter the new address.");
                                                    address = input.nextLine();

                                                    servList.get(i).setAddress(address);
                                                    val = true;

                                                    log = new LogFile(username, "has edited address.");
                                                    logList.addLast(log);
                                                    break;

                                                case 2:
                                                    do {
                                                        val = true;

                                                        p.println("Please enter the new Smart Card Number.");
                                                        smartCardNo = input.nextLine();

                                                        for (int j = 0; j < servList.size(); j++) {
                                                            if (smartCardNo.equalsIgnoreCase(servList.get(j).getSmartCardNo())) {
                                                                val = false;
                                                                break;
                                                            }
                                                        }

                                                        if (val == false)
                                                            p.println("It isn't a new Smart Card.");

                                                        else if (val = true) {
                                                            servList.get(i).setSmartCardNo(smartCardNo);
                                                            p.println("Smart Card change Success.");
                                                        }
                                                    } while (val == false);

                                                    log = new LogFile(username, "has edited Smart Card Number.");
                                                    logList.addLast(log);
                                                    break;

                                                case 3:
                                                    do {
                                                        val = true;

                                                        p.println("Please enter the new Decoder Number.");
                                                        decoderNo = input.nextLine();

                                                        for (int j = 0; j < servList.size(); j++) {
                                                            if (decoderNo.equalsIgnoreCase(servList.get(j).getDecodeNo())) {
                                                                val = false;
                                                                break;
                                                            }
                                                        }

                                                        if (val == false) {
                                                            p.println("It isn't a new Decoder.");
                                                        } else if (val == true) {
                                                            servList.get(i).setDecoderNo(decoderNo);
                                                            p.println("Decoder change Success.");
                                                        }
                                                    } while (val == false);

                                                    log = new LogFile(username, "has edited Decoder Number.");
                                                    logList.addLast(log);
                                                    break;

                                                case 4:
                                                    val = false;

                                                    do {
                                                        p.println("Please Enter the Service Status (Active, Inactive or Barred): ");
                                                        String servStatus = input.nextLine();
                                                        servStatus.toLowerCase();

                                                        if (servStatus.equals("active")) {
                                                            val = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else if (servStatus.equals("inactive")) {
                                                            val = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else if (servStatus.equals("barred")) {
                                                            val = true;
                                                            servList.get(i).setServStatus(servStatus);
                                                        } else {
                                                            val = false;
                                                            p.println("Invalid Enter.");
                                                        }
                                                    } while (val == false);

                                                    log = new LogFile(username, "has edited the status.");
                                                    logList.addLast(log);
                                                    break;

                                                case 5:
                                                    val = true;
                                                    break;

                                                default:
                                                    val = false;
                                                    p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                                    break;
                                            }
                                        } else {
                                            val = false;
                                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                        }
                                    } while ((menu3_opt_1 != 5) || (val == false));
                                    break;
                                }
                            }

                            if (val == false) {
                                p.println("Invalid Smart Card Number, Please Check the Smart Card Number.");
                            }
                        } while (val == false);

                        break;

                    case 3:
                        for (u = 0; u < userList.size(); u++) {
                            if (userList.get(u) instanceof FrontdeskStaffs) {
                                p.println("Only Admin Can Access.");
                                break;
                            } else if (userList.get(u) instanceof Administrators) {
                                do {
                                    val = false;
                                    p.println("Please Enter the Smart Card No: ");
                                    smartCardNo = input.nextLine();

                                    for (i = 0; i < servList.size(); i++) {
                                        if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                            val = true;

                                            servList.get(i).setServStatus("terminated");
                                            p.println("The Smart Card has been terminated.");
                                        }
                                    }

                                    if (val == false) {
                                        p.println("Invalid Smart Card Enter.");
                                    }
                                } while (val == false);
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
                        val = false;
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                val = false;
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 4) || (val == false));
    } // end manage service

    /*--------------------------------------------------------------------------METHOD SIX - MANAGE SUBSCRIPTION----------------------------------------------------------------------------------------*/
    public void manageSubscription() {
        val = false;

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

            if ((menu3_opt.matches("^[1-3]{1}$"))) {
                menu3_opt_1 = Integer.parseInt(menu3_opt);

                String clientID;
                String smartCardNo;
                String pkgCode;
                boolean repeat = false;
                int subsNo = 1;

                switch (menu3_opt_1) {
                    case 1:

                        do {
                            val = true;

                            p.println("Please Enter the Smart Card want to Subscibe package: ");
                            smartCardNo = input.nextLine();

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    p.println("This Smart Card have a subsciption already.");
                                    val = false;
                                    break;
                                }
                            }

                            if (val == true) {
                                val = false;
                                for (i = 0; i < servList.size(); i++) {
                                    if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                        val = true;
                                        break;
                                    }
                                }

                                if (val == true) {
                                    clientID = servList.get(i).getClientID();

                                    for (i = 0; i < servList.size(); i++) {
                                        if (clientID == servList.get(i).getClientID()) {
                                            subsNo++;
                                        }
                                    }
                                } else if (val == false) {
                                    p.println("Invalid Smart Card enter, Please Enter again.");
                                }
                            }
                        } while (val == false);

                        do {
                            val = false;

                            p.println("Please Enter the Package Code want to Subscribe: ");
                            pkgCode = input.nextLine();

                            for (i = 0; i < pkgList.size(); i++) {
                                if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                    val = true;
                                    p.println("Package has added successfully.");
                                    break;
                                }
                            }

                            if (val == true) {
                                subsList.add(new Subscription(smartCardNo, subsNo, pkgCode));

                                do {
                                    val = false;
                                    repeat = false;

                                    p.println("Do you want to add more package?");
                                    p.println("Please Enter 'Y' for Yes or 'N' for No: ");
                                    String enter = input.nextLine();

                                    if ((enter.equalsIgnoreCase("y")) || (enter.equalsIgnoreCase("n"))) {
                                        val = true;

                                        if (enter.equalsIgnoreCase("y")) {
                                            repeat = true;
                                        } else if (enter.equalsIgnoreCase("n")) {
                                            repeat = false;
                                        }

                                        break;
                                    }

                                    if (val == false) {
                                        p.println("Invalid Enter, Please Enter again.");
                                    }
                                } while (val == false);
                            } else if (val == false) {
                                p.println("Invalid Package Code, Please Enter Again!");
                            }

                        } while ((val == false) || (repeat == true));

                        log = new LogFile(username, "has create a new subscription.");
                        logList.addLast(log);
                        break;

                    case 2:
                        val = false;

                        do {
                            p.println("Please Enter the Smart Card Number: ");
                            smartCardNo = input.nextLine();

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    val = true;
                                    subsNo = subsList.get(i).getSubsNo();
                                    break;
                                }
                            }

                            if (val == false) {
                                p.println("Invalid Smart Card Number, Please Enter again.");
                            }
                        } while (val == false);

                        val = false;

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
                                        val = false;

                                        do {
                                            p.println("Please Enter the Pakage want to add: ");
                                            pkgCode = input.nextLine();

                                            for (i = 0; i < subsList.size(); i++) {
                                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                    if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                        val = true;
                                                        p.println("You have already Subscibe the package.");
                                                    }
                                                }
                                            }

                                            if (val == false) {
                                                for (i = 0; i < pkgList.size(); i++) {
                                                    if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                                        val = true;
                                                        break;
                                                    }
                                                }

                                                if (val == true) {
                                                    subsList.add(new Subscription(smartCardNo, subsNo, pkgCode));
                                                    p.println("Package add successful");
                                                } else if (val == false) {
                                                    p.println("Invalid Package Code, Please Enter again.");
                                                }
                                            }
                                        } while (val == false);

                                        log = new LogFile(username, "has added an new package to a service.");
                                        logList.addLast(log);
                                        break;

                                    case 2:
                                        val = false;

                                        do {
                                            p.println("Please Enter the Pakage want to remove: ");
                                            pkgCode = input.nextLine();

                                            for (i = 0; i < pkgList.size(); i++) {
                                                if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                                    val = true;
                                                    break;
                                                }
                                            }

                                            if (val == false) {
                                                p.println("Invalid Package Code Enter, Please Enter agian");
                                            } else if (val == true) {
                                                val = false;

                                                for (i = 0; i < subsList.size(); i++) {
                                                    if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                        if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                            val = true;
                                                            subsList.remove(subsList.get(i));
                                                            p.println("Package remove succeessful");
                                                            break;
                                                        }
                                                    }
                                                }

                                                if (val == false) {
                                                    val = true;
                                                    p.println("You are not subscibe the package.");
                                                }
                                            }
                                        } while (val == false);

                                        log = new LogFile(username, "has remove a package from a service.");
                                        logList.addLast(log);
                                        break;

                                    case 3:
                                        break;

                                    default:
                                        val = false;
                                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                        break;
                                }
                            } else {
                                val = false;
                                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                            }
                        } while ((menu3_opt_1 != 3) || (val == false));

                        break;

                    case 3:
                        showMenu();
                        break;

                    default:
                        val = false;
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                val = false;
                p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 3) || (val == false));
        ;
    }

    /*--------------------------------------------------------------------------METHOD SEVEN - MANAGE PACKAGES----------------------------------------------------------------------------------------*/
    public void managePackage() {
        do {
            try {
                check1 = true;
                p.println("===========================================");
                p.println("Manage Packages item");
                p.println("===========================================");
                p.println("1. Create a new TV Package");
                p.println("2. Edit a TV Package");
                p.println("3. Terminate a TV Package");
                p.println("4. Check a TV Package");
                p.print("Please enter the option number: ");
                option2 = input.nextInt();

                switch (option2) {
                    case 1:
                        p.println();
                        p.println("1. Create a new TV Package: ");
                        p.println("Please key in the following details for the new package below: ");
                        input.nextLine();

                        do {
                            do {
                                p.print("a) Package Code: ");
                                pckCode = input.nextLine();
                                p.println();

                                if (pckCode.length() == 0) {
                                    p.println("Blank input occured, please enter a package code.");
                                    p.println();
                                }
                            } while (pckCode.length() == 0);

                            for (i = 0; i < pkgList.size(); i++) {
                                if (pckCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                    p.println("Sorry, the package code that you entered is already existed.");
                                    p.println("Please try another one. ");
                                    check = false;
                                    break;
                                } else
                                    check = true;
                            }
                        } while (check == false);

                        do {
                            p.print("b) Package Name: ");
                            pckName = input.nextLine();
                            p.println();
                            if (pckName.length() == 0) {
                                p.println("Blank input occured, please enter a package name.");
                                p.println();
                            }
                        } while (pckName.length() == 0);

                        do {
                            try {
                                check2 = true;
                                p.print("c) Charge Price: RM");
                                chargePrice = input.nextDouble();
                                p.println();
                            } catch (InputMismatchException ime) {
                                p.println();
                                p.println("Invalid input, please try again");
                                input.next();
                                check2 = false;
                            }
                        } while (check2 == false);

                        input.nextLine();
                        do {
                            do {

                                p.println("d) Charge Type: ");
                                p.println("1)Monthly");
                                p.println("2)Quarterly");
                                p.println("3)Annually");
                                p.print("Please enter a type (Monthly/Quarterly/Annually) to the package: ");

                                chargeType = input.nextLine();


                                if (chargeType.length() == 0) {
                                    p.println("Blank input occured, please enter a charge type.");
                                    p.println();
                                }
                            } while (chargeType.length() == 0);

                            if ((!(chargeType.equalsIgnoreCase("Monthly"))) && (!(chargeType.equalsIgnoreCase("Quarterly"))) && (!(chargeType.equalsIgnoreCase("Annually")))) {

                                p.println("Sorry, the type that you entered is invalid, please enter it again.");
                                p.println();
                            }
                        }
                        while ((!(chargeType.equalsIgnoreCase("Monthly"))) && (!(chargeType.equalsIgnoreCase("Quarterly"))) && (!(chargeType.equalsIgnoreCase("Annually"))));

                        p.println();

                        Date startDate = new Date();
                        strtDate = DateFormat.getInstance().format(startDate);
                        pkgList.add(new TVPackage(pckCode, pckName, strtDate, chargePrice, chargeType, "Active"));

                        p.println("e) TV Programme: ");
                        do {
                            do {
                                do {

                                    p.print("Please enter TV Programme Code that you would like to add: ");
                                    progCode = input.nextLine();

                                    if (progCode.length() == 0) {
                                        p.println("Blank input occured, please enter a package name. TV Package must contain at least one TV Programme.");
                                        p.println();
                                    }
                                } while (progCode.length() == 0);

                                for (k = 0; k < pckgingList.size(); k++) {
                                    if (pckCode.equalsIgnoreCase(pckgingList.get(k).getPkgCode())) {
                                        if (progCode.equalsIgnoreCase(pckgingList.get(k).getProgCode())) {

                                            p.println("Sorry, the programme code that you entered is already existed in this package.");
                                            p.println("Please try another one.");
                                            p.println();
                                            check = false;
                                            break;
                                        } else
                                            check = true;
                                    }
                                }

                            } while (check == false);


                            for (i = 0; i < prgList.size(); i++) {
                                if (progCode.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                    if ((prgList.get(i).getPrgStatus()).equalsIgnoreCase("Inactive")) {

                                        flag = false;
                                        break;
                                    }

                                    pckgingList.add(new Packaging(pckCode, progCode));
                                    p.println("You have successfully added a new TV Programme into the package.");

                                    do {
                                        do {
                                            p.println();
                                            p.println("Would you like to add another TV Programme into this package?");
                                            p.print("Enter 'Yes' to add more programme, enter 'No' to refuse adding any programme: ");
                                            choice1 = input.nextLine();

                                            if (choice1.length() == 0) {
                                                p.println("Blank input occured, please enter a choice.");
                                                p.println();
                                            }
                                        } while (choice1.length() == 0);


                                        if ((!(choice1.equalsIgnoreCase("Yes"))) && (!(choice1.equalsIgnoreCase("No")))) {
                                            p.println("Invalid choice, please reenter again");
                                            p.println();
                                        }
                                    }
                                    while ((!(choice1.equalsIgnoreCase("Yes"))) && (!(choice1.equalsIgnoreCase("No"))));

                                    flag = true;
                                    break;
                                } else {
                                    flag = false;
                                }
                            }

                            if (flag == false) {
                                p.println("Sorry, the TV Programme's code that you entered doesn't exist or had been terminated. Please reenter it.");
                                p.println();
                            }

                        } while ((!(choice1.equalsIgnoreCase("No"))) || (flag == false));

                        p.println("You have successfully created a new package '" + pckCode + "'.");
                        p.println();

                        logList.addLast(new LogFile(username, "has created a TV Package item of ID '" + pckCode + "'."));

                        break;

                    case 2:
                        p.println();
                        input.nextLine();

                        do {
                            p.println("2. Edit a TV Package: ");
                            p.print("Please enter a package code that you want to edit: ");

                            pckCode2 = input.nextLine();

                            if (pckCode2.length() == 0) {
                                p.println("Blank input occured, please enter a package code.");
                                p.println();
                            }
                        } while (pckCode2.length() == 0);

                        for (i = 0; i < pkgList.size(); i++) {
                            if (pckCode2.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                if ((pkgList.get(i).getPkgStatus()).equalsIgnoreCase("Inactive")) {

                                    flag = false;
                                    break;
                                }
                                pkgList.get(i).printPkg();
                                p.println(" TV Programme Code: ");
                                for (j = 0; j < pckgingList.size(); j++) {
                                    if (pckCode2.equalsIgnoreCase(pckgingList.get(j).getPkgCode())) {
                                        p.print("'" + pckgingList.get(j).getProgCode() + "'\n");
                                    }
                                }

                                flag = true;
                                break;
                            } else
                                flag = false;
                        }

                        if (flag == false) {
                            p.println("Sorry, the package code that you entered doesn't exist or the package had already been terminated.");
                            p.println();
                        } else if (flag == true) {
                            do {
                                try {
                                    check3 = true;
                                    p.println("-----------------------------------------------------");
                                    p.println("Function");
                                    p.println("-----------------------------------------------------");
                                    p.println(" 1)Package Name \n 2)Charge Price \n 3)Charge Type \n 4)TV Programme \n 5)Stop Edit");
                                    p.print("Please enter the selection number of contain that you would like to edit with: ");
                                    selection = input.nextInt();

                                    switch (selection) {
                                        case 1:
                                            input.nextLine();
                                            do {
                                                p.print("Please enter a package name that you would like to change for: ");
                                                pkgName2 = input.nextLine();
                                                p.println();

                                                if (pkgName2.length() == 0) {
                                                    p.println("Blank input occured, please enter a package name.");
                                                    p.println();
                                                }
                                            } while (pkgName2.length() == 0);

                                            pkgList.get(i).setPkgName(pkgName2);
                                            p.println("You have changed the package name for package '" + pckCode2 + "'!");
                                            p.println();
                                            logList.addLast(new LogFile(username, "has changed a package name for package '" + pckCode2 + "'."));

                                            break;

                                        case 2:
                                            do {
                                                try {
                                                    check4 = true;
                                                    p.print("Please enter a charge price that you would like to change for: RM");
                                                    chargePrice2 = input.nextDouble();
                                                    p.println();
                                                } catch (InputMismatchException ime) {

                                                    p.println();
                                                    p.println("Invalid input, please try again");
                                                    input.next();
                                                    check4 = false;
                                                }
                                            } while (check4 == false);

                                            pkgList.get(i).setChargePrice(chargePrice2);
                                            p.println("You have changed the charge price for package '" + pckCode2 + "'!");
                                            p.println();
                                            logList.addLast(new LogFile(username, "has changed a charge price for package '" + pckCode2 + "'."));

                                            break;

                                        case 3:
                                            input.nextLine();
                                            do {
                                                do {

                                                    p.println("Charge Type: ");
                                                    p.println("1)Monthly");
                                                    p.println("2)Quarterly");
                                                    p.println("3)Annually");
                                                    p.print("Please enter a type (Monthly/Quarterly/Annually) to the package: ");
                                                    chargeType2 = input.nextLine();

                                                    if (chargeType2.length() == 0) {
                                                        p.println("Blank input occured, please enter a charge type.");
                                                        p.println();
                                                    }
                                                } while (chargeType2.length() == 0);

                                                if ((!(chargeType2.equalsIgnoreCase("Monthly"))) && (!(chargeType2.equalsIgnoreCase("Quarterly"))) && (!(chargeType2.equalsIgnoreCase("Annually")))) {
                                                    p.println("Sorry, the type that you entered is invalid, please enter it again.");
                                                    p.println();
                                                }

                                            }
                                            while ((!(chargeType2.equalsIgnoreCase("Monthly"))) && (!(chargeType2.equalsIgnoreCase("Quarterly"))) && (!(chargeType2.equalsIgnoreCase("Annually"))));

                                            p.println();

                                            pkgList.get(i).setChargeType(chargeType2);
                                            p.println("You have changed the charge type for package '" + pckCode2 + "'!");
                                            p.println();
                                            logList.addLast(new LogFile(username, "has changed a charge type for package '" + pckCode2 + "'."));

                                            break;

                                        case 4:
                                            do {
                                                try {

                                                    check5 = true;
                                                    p.println("1) Add a TV Programme to package " + pckCode2);
                                                    p.println("2) Delete a TV Programme from package " + pckCode2);
                                                    p.print("Please enter the selection number (1/2): ");
                                                    selection2 = input.nextInt();

                                                    if (selection2 == 1) {
                                                        p.println();
                                                        input.nextLine();
                                                        do {
                                                            do {

                                                                p.print("Please enter a TV Programme code which you would like to add into the package: ");
                                                                progCode2 = input.nextLine();
                                                                if (progCode2.length() == 0) {
                                                                    p.println("Blank input occured, please enter a programme code.");
                                                                    p.println();
                                                                }
                                                            } while (progCode2.length() == 0);

                                                            for (k = 0; k < pckgingList.size(); k++) {
                                                                if (pckCode2.equalsIgnoreCase(pckgingList.get(k).getPkgCode())) {

                                                                    if (progCode2.equalsIgnoreCase(pckgingList.get(k).getProgCode())) {

                                                                        p.println("Sorry, the programme code that you entered is already existed in this package.");
                                                                        p.println("Please try another one.");
                                                                        p.println();
                                                                        check = false;
                                                                        break;
                                                                    } else
                                                                        check = true;
                                                                }
                                                            }
                                                        } while (check == false);


                                                        for (i = 0; i < prgList.size(); i++) {
                                                            if (progCode2.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                                                if ((prgList.get(i).getPrgStatus()).equalsIgnoreCase("Inactive")) {
                                                                    p.println("Sorry, the programme had already been terminated.");
                                                                    break;
                                                                }

                                                                pckgingList.add(new Packaging(pckCode2, progCode2));
                                                                p.println("You have successfully added a tv programme into package '" + pckCode2 + "'!");
                                                                p.println();
                                                                logList.addLast(new LogFile(username, "has added a tv programme into package '" + pckCode2 + "'."));
                                                                flag2 = true;
                                                                break;
                                                            } else
                                                                flag2 = false;
                                                        }
                                                        if (flag2 == false) {
                                                            p.println("Sorry, the programme code you entered doesn't exist or had been terminated.");
                                                            p.println();
                                                        }
                                                    } else if (selection2 == 2) {
                                                        p.println();
                                                        input.nextLine();
                                                        do {
                                                            p.print("Please enter a TV Programme code which you would like to delete from the package: ");
                                                            progCode3 = input.nextLine();

                                                            if (progCode3.length() == 0) {
                                                                p.println("Blank input occured, please enter a programme code.");
                                                                p.println();
                                                            }

                                                        } while (progCode3.length() == 0);

                                                        int count = 0;
                                                        for (k = 0; k < pckgingList.size(); k++) {
                                                            if (pckCode2.equalsIgnoreCase(pckgingList.get(k).getPkgCode())) {
                                                                count += 1;
                                                            }
                                                        }

                                                        if (count == 1) {
                                                            flag6 = false;
                                                        } else
                                                            flag6 = true;

                                                        if (flag6 == true) {
                                                            for (j = 0; j < pckgingList.size(); j++) {
                                                                if (progCode3.equalsIgnoreCase(pckgingList.get(j).getProgCode())) {
                                                                    if (pckCode2.equalsIgnoreCase(pckgingList.get(j).getPkgCode())) {
                                                                        pckgingList.remove(j);
                                                                        p.println("You have successfully removed a tv programme from package '" + pckCode2 + "'!");
                                                                        logList.addLast(new LogFile(username, "has removed a tv programme from package '" + pckCode2 + "'."));
                                                                        p.println();
                                                                        flag3 = true;
                                                                        break;
                                                                    } else
                                                                        flag3 = false;
                                                                } else
                                                                    flag3 = false;
                                                            }
                                                        }
                                                        if (flag3 == false) {
                                                            p.println("Sorry, the programme code you entered is not inside the package.");
                                                            p.println();
                                                        } else if (flag6 == false) {
                                                            p.println("Sorry you cannot delete programme inside this package as there is only one TV Programme contain in package '" + pckCode2 + "'. You cannot delete it.");
                                                            p.println();
                                                        }

                                                    } else {
                                                        p.println("Sorry, the number you entered is invalid. Please reenter it.");
                                                        p.println();
                                                    }
                                                } catch (InputMismatchException ime) {

                                                    p.println();
                                                    p.println("Invalid input, please try again");
                                                    input.next();
                                                    check5 = false;
                                                }
                                            } while ((selection2 < 1) || (selection2 > 2) || (check5 == false));

                                            break;

                                        case 5:
                                            check3 = false;
                                            break;

                                        default:
                                            check3 = false;
                                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                            break;
                                    }
                                } catch (InputMismatchException ime) {
                                    p.println();
                                    p.println("Invalid input, please try again");
                                    input.next();
                                    check3 = false;
                                }
                            } while (check3 == false);
                        }
                        break;

                    case 3:
                        p.println();
                        input.nextLine();
                        do {
                            p.println("3. Terminate a TV Package: ");
                            p.print("Please enter a package code that you want to terminate: ");
                            pckCode3 = input.nextLine();
                            if (pckCode3.length() == 0) {
                                p.println("Blank input occured, please enter a package code.");
                                p.println();
                            }
                        } while (pckCode3.length() == 0);

                        for (i = 0; i < pkgList.size(); i++) {
                            if (pckCode3.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                if (pkgList.get(i).getPkgStatus().equalsIgnoreCase("Inactive")) {
                                    flag4 = false;
                                    break;
                                } else {
                                    Date terminationDate = new Date();
                                    termDate = DateFormat.getInstance().format(terminationDate);

                                    pkgList.get(i).setTerminationDate(termDate);
                                    pkgList.get(i).setStatus("Inactive");
                                    p.println("You have successfully terminated package '" + pckCode3 + "'!");
                                    logList.addLast(new LogFile(username, "has terminated a package which is '" + pckCode3 + "'."));
                                    p.println();
                                    flag4 = true;
                                    break;
                                }
                            } else
                                flag4 = false;
                        }
                        if (flag4 == false)
                            p.println("Sorry, the package code that you entered doesn't exist or had been terminated.");
                        break;

                    case 4:
                        p.println();
                        input.nextLine();
                        do {
                            p.println("4. Check a TV Package: ");
                            p.print("Please enter a package code that you want to check with: ");
                            packageCode = input.nextLine();

                            if (packageCode.length() == 0) {
                                p.println("Blank input occured, please enter a package code.");
                                p.println();
                            }
                        } while (packageCode.length() == 0);

                        for (i = 0; i < pkgList.size(); i++) {
                            if (packageCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                pkgList.get(i).printPkg();
                                p.println(" TV Programme Code: ");

                                for (j = 0; j < pckgingList.size(); j++) {
                                    if (packageCode.equalsIgnoreCase(pckgingList.get(j).getPkgCode())) {

                                        p.print("'" + pckgingList.get(j).getProgCode() + "'\n");

                                        logList.addLast(new LogFile(username, "has displayed the details of package'" + packageCode + "' ."));
                                        p.println();
                                    }
                                }
                                flag5 = true;
                                break;
                            } else
                                flag5 = false;
                        }

                        if (flag5 == false)
                            p.println("Sorry, the programme code that you entered doesn't exist.");

                        p.println();

                        break;

                    case 5:
                        showMenu();
                        break;

                    default:
                        check = false;
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } catch (InputMismatchException ime) {

                p.println();
                p.println("Invalid input, please try again");
                input.next();
                check1 = false;
            }
        } while (check1 == false);
    }

    /*--------------------------------------------------------------------------METHOD EIGHT - MANAGE PROGRAMMES----------------------------------------------------------------------------------------*/
    public void manageProgramme() {
        do {
            try {
                check1 = true;

                p.println("===========================================");
                p.println("Manage TV Programmes item");
                p.println("===========================================");
                p.println("1. Create a new TV Programme");
                p.println("2. Edit a TV Programme");
                p.println("3. Terminate a TV Programme");
                p.println("4. Check a TV Programme");
                p.println("5. Back to main menu");
                p.print("Please enter the option number: ");
                option3 = input.nextInt();

                switch (option3) {
                    case 1:
                        p.println();
                        p.println("1. Create a new TV Programme: ");
                        p.println("Please key in the following details for the new programme below: ");

                        input.nextLine();
                        do {

                            do {

                                p.print("a) Programme Code: ");
                                programmeCode = input.nextLine();
                                p.println();

                                if (programmeCode.length() == 0) {
                                    p.println("Blank input occured, please enter a programme code.");
                                    p.println();
                                }

                            } while (programmeCode.length() == 0);


                            for (i = 0; i < prgList.size(); i++) {
                                if (programmeCode.equalsIgnoreCase(prgList.get(i).getProgCode())) {

                                    p.println("Sorry, the package code that you entered is already existed.");
                                    p.println("Please try another one: ");
                                    p.println();
                                    check = false;
                                    break;
                                } else
                                    check = true;
                            }

                        } while (check == false);

                        do {
                            p.print("b) Programme Title: ");

                            programmeTitle = input.nextLine();
                            p.println();

                            if (programmeTitle.length() == 0) {
                                p.println("Blank input occured, please enter a programme title.");
                                p.println();
                            }
                        } while (programmeTitle.length() == 0);

                        do {

                            p.print("c) Description: ");

                            desc = input.nextLine();
                            p.println();

                            if (desc.length() == 0) {
                                p.println("Blank input occured, please enter a description.");
                                p.println();
                            }
                        } while (desc.length() == 0);

                        do {

                            p.print("d) Content Origin: ");

                            contentOrigin = input.nextLine();
                            p.println();

                            if (contentOrigin.length() == 0) {
                                p.println("Blank input occured, please enter a content origin.");
                                p.println();
                            }
                        } while (contentOrigin.length() == 0);

                        Date creationDate = new Date();
                        crtDate = DateFormat.getInstance().format(creationDate);

                        do {

                            do {

                                p.println("e) Viewer Status: ");
                                p.println("1) U (For general viewing without age limit)");
                                p.println("2) PG13 (Parents guidance needed for audience under the age of 13)");
                                p.println("3) 18 (For 18 and above only)");
                                p.print("Enter (U/PG13/18) to set to the viewer status: ");

                                viewerStatus = input.nextLine();

                                if (viewerStatus.length() == 0) {
                                    p.println("Blank input occured, please enter a viewer status.");
                                    p.println();
                                }

                            } while (viewerStatus.length() == 0);

                            if ((!(viewerStatus.equalsIgnoreCase("U"))) && (!(viewerStatus.equalsIgnoreCase("PG13"))) && (!(viewerStatus.equalsIgnoreCase("18")))) {
                                p.println("\nSorry, the viewer status that you entered is invalid, please reenter it.");
                            }
                            p.println();
                        }
                        while ((!(viewerStatus.equalsIgnoreCase("U"))) && (!(viewerStatus.equalsIgnoreCase("PG13"))) && (!(viewerStatus.equalsIgnoreCase("18"))));

                        do {
                            do {

                                p.println("f) Type: ");
                                p.println("1) Movie");
                                p.println("2) Series");
                                p.println("3) Comedy");
                                p.println("4) Concert");
                                p.print("Enter (Movie/Series/Comedy/Concert) to set to the programme's type: ");

                                progType = input.nextLine();

                                if (progType.length() == 0) {
                                    p.println("Blank input occured, please enter a programme type.");
                                    p.println();
                                }
                            } while (progType.length() == 0);

                            if ((!(progType.equalsIgnoreCase("Movie"))) && (!(progType.equalsIgnoreCase("Series"))) &&
                                    (!(progType.equalsIgnoreCase("Comedy"))) && (!(progType.equalsIgnoreCase("Concert")))) {
                                p.println();
                                p.println("Sorry, the type that you entered is invalid, please reenter it.");
                            }
                            p.println();
                        } while ((!(progType.equalsIgnoreCase("Movie"))) && (!(progType.equalsIgnoreCase("Series"))) &&
                                (!(progType.equalsIgnoreCase("Comedy"))) && (!(progType.equalsIgnoreCase("Concert"))));

                        do {
                            p.print("g) Notes: ");
                            notes = input.nextLine();
                            p.println();
                            if (notes.length() == 0) {
                                p.println("Blank input occured, please enter a notes.");
                                p.println();
                            }
                        } while (notes.length() == 0);

                        prgList.add(new TVProgramme(programmeCode, programmeTitle, desc, contentOrigin, crtDate, "Active", viewerStatus, progType, notes));

                        p.println("You have successfully created a new TV Programme which is '" + programmeCode + "'.");
                        logList.addLast(new LogFile(username, "has created a new TV Programme which is '" + programmeCode + "'."));
                        p.println();

                        break;

                    case 2:
                        p.println();
                        input.nextLine();

                        do {
                            p.println("2. Edit a TV Programme: ");
                            p.print("Please enter a programme code that you want to edit: ");
                            progCode4 = input.nextLine();

                            if (progCode4.length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }

                        } while (progCode4.length() == 0);

                        for (i = 0; i < prgList.size(); i++) {
                            if (progCode4.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                if ((prgList.get(i).getPrgStatus()).equalsIgnoreCase("Inactive")) {
                                    flag = false;
                                    break;
                                }
                                prgList.get(i).printList();
                                p.println();

                                flag = true;
                                break;
                            } else
                                flag = false;
                        }

                        if (flag == false) {
                            p.println("Sorry, the package code that you entered doesn't exist or had been terminated.");
                        } else if (flag == true) {
                            do {
                                try {
                                    check2 = true;
                                    p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                    p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
                                    p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                    p.println("1. Programme Title");
                                    p.println("2. Description");
                                    p.println("3. Content Origin");
                                    p.println("4. Viewer Status");
                                    p.println("5. Type");
                                    p.println("6. Notes");
                                    p.println("7. Stop Edit");
                                    p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
                                    p.print("Please enter the selection number of contain that you would like to edit with: ");
                                    selection = input.nextInt();

                                    switch (selection) {
                                        case 1:
                                            input.nextLine();
                                            do {
                                                p.print("Please enter a programme title that you would like to change for: ");

                                                programmeTitle2 = input.nextLine();
                                                p.println();

                                                if (programmeTitle2.length() == 0) {
                                                    p.println("Blank input occured, please enter a programme title.");
                                                    p.println();
                                                }
                                            } while (programmeTitle2.length() == 0);

                                            prgList.get(i).setProgTitle(programmeTitle2);
                                            p.println("You have changed the programme title for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(username, "has changed a programme title for programme '" + progCode4 + "'."));
                                            p.println();

                                            break;

                                        case 2:
                                            input.nextLine();
                                            do {
                                                p.print("Please type some description that you would like to change for: ");

                                                desc2 = input.nextLine();
                                                p.println();

                                                if (desc2.length() == 0) {
                                                    p.println("Blank input occured, please enter a description.");
                                                    p.println();
                                                }
                                            } while (desc2.length() == 0);

                                            prgList.get(i).setDesc(desc2);
                                            p.println("You have changed the description for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(username, "has changed a description for programme '" + progCode4 + "'."));
                                            p.println();

                                            break;

                                        case 3:
                                            input.nextLine();
                                            do {
                                                p.print("Please enter a content origin that you would like to change for: ");
                                                contentOrigin2 = input.nextLine();
                                                p.println();

                                                if (contentOrigin2.length() == 0) {
                                                    p.println("Blank input occured, please enter a content origin.");
                                                    p.println();
                                                }
                                            } while (contentOrigin2.length() == 0);

                                            prgList.get(i).setContentOrigin(contentOrigin2);
                                            p.println("You have changed the content origin for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(username, "has changed a content origin for programme '" + progCode4 + "'."));
                                            p.println();

                                            break;

                                        case 4:
                                            input.nextLine();
                                            do {
                                                do {
                                                    p.println("1) U (For general viewing without age limit)");
                                                    p.println("2) PG13 (Parents guidance needed for audience under the age of 13)");
                                                    p.println("3) 18 (For 18 and above only)");
                                                    p.print("Enter (U/PG13/18) to change the viewer status: ");
                                                    viewerStatus2 = input.nextLine();

                                                    if (viewerStatus2.length() == 0) {
                                                        p.println("Blank input occured, please enter a viewer status.");
                                                        p.println();
                                                    }
                                                } while (viewerStatus2.length() == 0);

                                                if ((viewerStatus2.equalsIgnoreCase("U")) || (viewerStatus2.equalsIgnoreCase("PG13")) || (viewerStatus2.equalsIgnoreCase("18"))) {
                                                    p.println("You have changed the viewer status for programme '" + progCode4 + "'!");
                                                    logList.addLast(new LogFile(username, "has changed a viewer status for programme '" + progCode4 + "'."));
                                                    p.println();
                                                    prgList.get(i).setViewerStatus(viewerStatus2);
                                                } else {
                                                    p.println("Sorry, the viewer status that you entered is invalid, please reenter it.");
                                                    p.println();
                                                }
                                            }
                                            while ((!(viewerStatus2.equalsIgnoreCase("U"))) && (!(viewerStatus2.equalsIgnoreCase("PG13"))) && (!(viewerStatus2.equalsIgnoreCase("18"))));

                                            break;

                                        case 5:
                                            input.nextLine();
                                            do {
                                                do {

                                                    p.println("1) Movie");
                                                    p.println("2) Series");
                                                    p.println("3) Comedy");
                                                    p.println("4) Concert");
                                                    p.print("Enter (Movie/Series/Comedy/Concert) to set to the programme's type: ");
                                                    progType2 = input.nextLine();

                                                    if (progType2.length() == 0) {
                                                        p.println("Blank input occured, please enter a programme type.");
                                                        p.println();
                                                    }


                                                } while (progType2.length() == 0);

                                                if ((progType2.equalsIgnoreCase("Movie")) || (progType2.equalsIgnoreCase("Series")) ||
                                                        (progType2.equalsIgnoreCase("Comedy")) || (progType2.equalsIgnoreCase("Concert"))) {

                                                    p.println("You have changed the type of programme '" + progCode4 + "'!");
                                                    logList.addLast(new LogFile(username, "has changed a programme type for programme '" + progCode4 + "'."));
                                                    p.println();
                                                    prgList.get(i).setType(progType2);
                                                } else {
                                                    p.println("Sorry, the type that you entered is invalid, please reenter it.");
                                                    p.println();
                                                }
                                            }
                                            while ((!(progType2.equalsIgnoreCase("Movie"))) && (!(progType2.equalsIgnoreCase("Series"))) &&
                                                    (!(progType2.equalsIgnoreCase("Comedy"))) && (!(progType2.equalsIgnoreCase("Concert"))));

                                            break;

                                        case 6:
                                            input.nextLine();
                                            do {
                                                p.print("Please enter some notes that you would like to change for: ");
                                                notes2 = input.nextLine();
                                                p.println();

                                                if (notes2.length() == 0) {
                                                    p.println("Blank input occured, please enter a notes.");
                                                    p.println();
                                                }
                                            } while (notes2.length() == 0);

                                            prgList.get(i).setNotes(notes2);
                                            p.println("You have changed the notes for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(username, "has changed the notes for programme '" + progCode4 + "'."));
                                            p.println();

                                            break;

                                        case 7:
                                            check2 = false;
                                            break;

                                        default:
                                            p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                                            break;
                                    }
                                } catch (InputMismatchException ime) {
                                    p.println();
                                    p.println("Invalid input, please try again");
                                    input.next();
                                    check2 = false;
                                }
                            } while (check2 == false);

                        }

                        break;

                    case 3:
                        p.println();
                        input.nextLine();

                        do {
                            p.println("3. Terminate a TV Programme: ");
                            p.print("Please enter a programme code that you want to terminate: ");

                            progCode5 = input.nextLine();
                            if (progCode5.length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }

                        } while (progCode5.length() == 0);


                        for (i = 0; i < prgList.size(); i++) {
                            if (progCode5.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                if ((prgList.get(i).getPrgStatus()).equalsIgnoreCase("Inactive")) {
                                    flag4 = false;
                                    break;
                                }
                                prgList.get(i).setPrgStatus("Inactive");

                                for (j = 0; j < pckgingList.size(); j++) {
                                    if (progCode5.equalsIgnoreCase(pckgingList.get(j).getProgCode())) {
                                        String a = pckgingList.get(j).getPkgCode();

                                        for (k = 0; k < pkgList.size(); k++) {
                                            if (a.equalsIgnoreCase(pkgList.get(k).getPkgCode())) {
                                                pkgList.get(k).setStatus("Inactive");
                                                break;
                                            }
                                        }

                                    }

                                }

                                p.println("You have successfully terminated a TV Programme '" + progCode5 + "'!");
                                logList.addLast(new LogFile(username, "has terminate a TV Programme '" + progCode5 + "'."));
                                p.println();
                                flag4 = true;
                                break;
                            } else
                                flag4 = false;
                        }

                        if (flag4 == false) {
                            p.println("Sorry, the programme code that you entered doesn't exist or had already been terminated.");
                            p.println();
                        }

                        break;

                    case 4:
                        p.println();
                        input.nextLine();
                        do {

                            p.println("4. Check a TV Programme: ");
                            p.print("Please enter a programme code that you want to check with: ");
                            progCode6 = input.nextLine();

                            if (progCode6.length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }
                        } while (progCode6.length() == 0);

                        for (i = 0; i < prgList.size(); i++) {
                            if (progCode6.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                prgList.get(i).printList();
                                p.println();
                                logList.addLast(new LogFile(username, "has displayed the details for programme '" + progCode6 + "'."));
                                flag5 = true;
                                break;
                            } else
                                flag5 = false;
                        }
                        if (flag5 == false)
                            p.println("Sorry, the programme code that you entered doesn't exist.");
                        p.println();

                        break;

                    case 5:
                        showMenu();
                        break;

                    default:
                        p.println("INVALID SELECTION, PLEASE RE-ENTER.\n");
                        break;
                }
            } catch (InputMismatchException ime) {
                p.println();
                p.println("Invalid input, please try again");
                input.next();
                check1 = false;
            }
        } while (check1 == false);
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
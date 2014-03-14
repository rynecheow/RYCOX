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
    int i = 0, f = 0, u = 0, menu1_opt_1 = 0, menu3_opt_1 = 0;
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
    String oldSmartCard;
    String oldDecoder;
    String servStatus;
    String address;
    String pkgCode;
    String sel;
    /*extra*/
    String pckCode = "", pckCode2 = "", pckCode3 = "", packageCode = "";
    String progCode = "", progCode2 = "", progCode3 = "", progCode4 = "", progCode5 = "", progCode6 = "", programmeCode = "";
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
    String Cl_IDregex = "[iIgGnNpP][0-9]{6}";
    String InCl_IDregex = "^[iI][0-9]{6}$";
    String GovCl_IDregex = "^[gG][0-9]{6}$";
    String NGOCl_IDregex = "^[nN][0-9]{6}$";
    String PrvCl_IDregex = "^[pP][0-9]{6}$";
    String ScNo_IDregex = "^[sS][0-9]{6}$";
    String decNo_IDregex = "^[dD][0-9]{6}$";
    String Pkg_IDregex = "^[pP][0-9]{2}$";
    String Prg_IDregex = "^[Ff][0-9]{3}$";

    int option = 0, option2 = 0, option3 = 0;
    int /*i = 0,*/ j = 0, k = 0;
    int selection = 0, selection2 = 0;
    double chargePrice = 0.0, chargePrice2 = 0.0;
    boolean flag = true, flag2 = true, flag3 = true, flag4 = true, flag5 = true, flag6 = true;
    boolean check = true, check1, check2, check3, check4, check5;
    LinkedList<Users> userList;
    LinkedList<ClientAccount> clientList;
    LinkedList<Service> servList;
    LinkedList<Subscription> subsList;
    LinkedList<TVPackage> pkgList;
    LinkedList<Packaging> pckgingList;
    LinkedList<TVProgramme> prgList;
    LinkedList<LogFile> logList;
    LogFile log;

    @SuppressWarnings("unchecked")
    public TVSystem() {
        //pre-defined objects
        userList = new LinkedList<Users>();
        clientList = new LinkedList<ClientAccount>();
        servList = new LinkedList<Service>();
        subsList = new LinkedList<Subscription>();
        pkgList = new LinkedList<TVPackage>();
        pckgingList = new LinkedList<Packaging>();
        prgList = new LinkedList<TVProgramme>();
        logList = new LinkedList<LogFile>();
        log = new LogFile("", "", "");
        userList.add(new Administrators("admin", "nimda"));
        userList.add(new FrontdeskStaffs("staff", "123abc"));
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I000001", "ACTIVE"));
        clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "INACTIVE"));
        clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "ACTIVE"));
        clientList.add(new PrvClient("Private Organisation", "Address", "P000001", "ACTIVE"));
        servList.add(new Service("S000001", "I000001", "D999999", "5, Jalan Sungai Beranang"));
        servList.add(new Service("S000002", "I000001", "D999998", "Lot 1-3 Starhill"));
        servList.add(new Service("S000003", "N000001", "D999997", "Lot 3-10 Jalan Taylor"));
        servList.add(new Service("S000004", "P000001", "D999996", "32 Jalan Kota Kemuning "));
        servList.add(new Service("S000005", "I000002", "D999995", "32 Jalan Kota Kemuning "));
        servList.add(new Service("S000006", "P000002", "D999994", "32 Jalan Kota Kemuning "));
        subsList.add(new Subscription("S000001", 1, "P01"));
        subsList.add(new Subscription("S000001", 1, "P02"));
        subsList.add(new Subscription("S000002", 2, "P02"));
        subsList.add(new Subscription("S000002", 2, "P01"));
        subsList.add(new Subscription("S000003", 1, "P03"));
        subsList.add(new Subscription("S000004", 1, "P04"));
        pkgList.add(new TVPackage("P01", "Variety", "18/04/2012", 40.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P02", "Fun", "18/04/2012", 350.00, "Yearly", "ACTIVE"));
        pckgingList.add(new Packaging("P01", "F001"));
        prgList.add(new TVProgramme("F001", "My Boss My Hero", "Japanese Comedy Drama series about Yakuza members.", "Japan", "27/11/2002", "Active", "18SG", "Comedy", ""));

        File client_file = new File("cl_data.dat");
        boolean exist_cl_data = client_file.exists();
        if (exist_cl_data == false) {    //if client's file do not exist
            //cl_data.dat file
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
                        prg_oostream.writeObject(prgList);
                    }
                }
                prg_oostream.flush();
                prg_oostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //user_data.dat files
            try {
                FileOutputStream user_fostream = new FileOutputStream("user_data.dat");
                ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream);
                for (i = 0; i < userList.size(); i++) {
                    if (userList.get(i) != null) {
                        user_oostream.writeObject(userList);
                    }
                }
                user_oostream.flush();
                user_oostream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (exist_cl_data == true) {        //reading files
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

            //reading user_data.dat
            try {
                FileInputStream user_fistream = new FileInputStream("user_data.dat");            //read from specified file
                ObjectInputStream user_oistream = new ObjectInputStream(user_fistream);

                for (i = 0; i < userList.size(); i++) {
                    if (userList.get(i) != null) {
                        userList = (LinkedList<Users>) user_oistream.readObject();
                    }
                }
                user_oistream.close();
            } catch (Exception e) {
            }

        } //end else
        loginMenu();
    } //end constructor


    /*--------------------------------------------------------------------------SECTION ONE- USER LOGIN----------------------------------------------------------------------------------------*/
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
                                    Date logTime = new Date();
                                    String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
                                    log = new LogFile(lgTime, username, "has logged into the system.");
                                    logList.addLast(log);

                                    break;
                                } //end else
                            }//end for
                        }// end while
                        showMenu();                        //show menubar
                        break;

                    case 2:        //Good Bye!
                        p.print("Thank you for using the system. GOOD BYE.\n");
                        System.exit(0);

                        break;

                    default:

                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while ((menu1_opt_1 != 2) && (login == false));
    } //login method

    /*--------------------------------------------------------------------------SECTION TWO - MENU----------------------------------------------------------------------------------------*/
    public void showMenu() {

        do {
            p.println("\nMENU");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1.\tDisplay all clients");
            p.println("2.\tDisplay clients by type");
            p.println("3.\tDisplay specific client's Details");
            p.println("4.\tManage client profiles - Add/Edit/Terminate/Recover");
            p.println("5.\tManage client services - Add/Edit/Terminate");
            p.println("6.\tManage subscriptions - Add/Edit");
            p.println("7.\tManage TV packages- Add/Edit/Terminate/Display");
            p.println("8.\tManage TV Programmes - Add/Edit/Terminate/Display");
            p.println("9.\tManage User - Add/Change Password/Terminate");
            p.println("10.\tGenerate Report");
            p.println("11.\tLog off");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");

            menu2_opt = input.nextLine();

            if ((menu2_opt.matches("^[1-9]{1}$")) || (menu2_opt.matches("^[1][0-2]$"))) {


                switch (menu2_opt) {
                    case "1":
                        displayClients();
                        break;
                    case "2":
                        displayClientsByType();
                        break;
                    case "3":
                        displayCLDetails();
                        break;
                    case "4":
                        manageClients();
                        break;
                    case "5":
                        manageService();
                        break;
                    case "6":
                        manageSubscription();
                        break;
                    case "7":
                        if ((userList.get(u)) instanceof FrontdeskStaffs) {
                            String lgTime = "[" + DateFormat.getInstance().format(new Date()) + "]\t";

                            p.println("\nOnly Administators have the accessibility to manage a package.");
                            log = new LogFile(lgTime, userList.get(u).getUserID(), "has not managed a package[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                            logList.addLast(log);
                            break;
                        } else {
                            managePackage();
                            break;
                        }
                    case "8":
                        if ((userList.get(u)) instanceof FrontdeskStaffs) {
                            String lgTime = "[" + DateFormat.getInstance().format(new Date()) + "]\t";

                            p.println("\nOnly Administators have the accessibility to manage a programme.");
                            log = new LogFile(lgTime, userList.get(u).getUserID(), "has not managed a programme[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                            logList.addLast(log);
                            break;
                        } else {
                            manageProgramme();
                            break;
                        }
                    case "9":
                        manageUsers();
                        break;
                    case "10":
                        reportGen();
                        break;
                    case "11":
                        logout();
                        break;
                    case "12":
                        printLists();
                        break;
                }
            } else
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.");
        } while (login);
    } //end showMenu

    /*--------------------------------------------------------------------------SECTION THREE - DISPLAY CLIENTS----------------------------------------------------------------------------------------*/
    public void displayClients() {
        p.println("\nDisplay Clients");
        p.println("---------------");
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Display Clients' function.");
        logList.addLast(log);

        for (i = 0; i < clientList.size(); i++) {
            if (clientList.get(i).getAccountStatus().equalsIgnoreCase("terminated")) {
                continue;
            } else {
                p.println(clientList.get(i).getName() + "\t" + clientList.get(i).getClientID());
            }
        }

        log = new LogFile(lgTime, username, "has displayed clients.");
        logList.addLast(log);
    } //end display client

    public void displayClientsByType() {
        String type;
        @SuppressWarnings("unused")
        boolean val1_o2 = false, val2_o2 = false;
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Display Client by Type' function.");
        logList.addLast(log);

        p.println("\nDisplay Client by Type");
        p.println("--------------------------------------------");

        do {
            p.print("Enter the client's type(Individual,Gov,NGO,Private): \n\n");
            p.println("Enter IND to display all clients from the 'Individual' category.\n");
            p.println("Enter GOV to display all clients from the 'Government' category.\n");
            p.println("Enter NGO to display all clients from the 'NGO' category.\n");
            p.println("Enter PRV to display all clients from the 'Private Organisation' category..\n");
            p.println("-----------------------------------------------------");
            type = input.nextLine();

            if (type.trim().length() > 0) {
                val1_o2 = true;
            } else {
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
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
            if (type.equalsIgnoreCase("Ind")) {
                if (val_idv) {
                    p.println("\n\nRegistered client(s) from the 'Individual' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof IndividualClient) {
                            if (clientList.get(i).getAccountStatus().equalsIgnoreCase("terminated")) {
                                continue;
                            } else {
                                p.println(clientList.get(i).getName());
                            }
                        }
                    }

                    log = new LogFile(lgTime, username, "has displayed the clients in Individual category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Individual category yet.");

                    log = new LogFile(lgTime, username, "has not displayed the clients in Individual category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } else if (type.equalsIgnoreCase("Gov")) {//Government
                if (val_gov) {
                    p.println("Registered client(s) from the 'Government' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof GovClient) {
                            if (clientList.get(i).getAccountStatus().equalsIgnoreCase("terminated")) {
                                continue;
                            } else {
                                p.println(clientList.get(i).getName());
                            }
                        }
                    }

                    log = new LogFile(lgTime, username, "has displayed the clients in Government category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Government category yet.");

                    log = new LogFile(lgTime, username, "has not displayed the clients in Government category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } else if (type.equalsIgnoreCase("NGO")) {//NGO
                if (val_ngo) {
                    p.println("Registered client(s) from the 'NGO' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof NGOClient) {
                            if (clientList.get(i).getAccountStatus().equalsIgnoreCase("terminated")) {
                                continue;
                            } else {
                                p.println(clientList.get(i).getName());
                            }
                        }
                    }

                    log = new LogFile(lgTime, username, "has displayed the clients in NGO type.");
                    logList.addLast(log);

                    val2_o2 = true;

                } else {

                    p.println("There is no client registered under the NGO category yet.");

                    log = new LogFile(lgTime, username, "has not displayed the clients in NGO category[NO CLIENTS].");
                    logList.addLast(log);
                }
            }

            //Private Organisation
            else if (type.equalsIgnoreCase("Prv")) {
                if (val_prv) {
                    p.println("Registered client(s) from the 'Private Organisation' category is/are:");

                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) instanceof PrvClient) {
                            if (clientList.get(i).getAccountStatus().equalsIgnoreCase("terminated")) {
                                continue;
                            } else {
                                p.println(clientList.get(i).getName());
                            }
                        }
                    }

                    log = new LogFile(lgTime, username, "has displayed the clients in Private Organisation category.");
                    logList.addLast(log);

                    val2_o2 = true;
                } else {
                    p.println("There is no client registered under the Private Organisation category yet.");

                    log = new LogFile(lgTime, username, "has not displayed the clients in Private Organisation category [NO CLIENTS].");
                    logList.addLast(log);
                }
            } //end else if

            else {
                p.println("Error! Client type does not exist!");

                log = new LogFile(lgTime, username, "has not displayed the any client.[TYPE DOES NOT EXIST].");
                logList.addLast(log);
            } //end else

        } while (val1_o2 == false); //end do-while loop

        val1_o2 = false;
    } // end display client by type

    public void displayCLDetails() {
        String valID_f3;
        boolean val1_f3 = false;    //check emptiness
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Display Client's Details' function.");
        logList.addLast(log);

        p.println("\nDisplay Client's Details");
        p.println("------------------------");

        do {
            p.print("Please enter ID of client you would like to display the details for: ");
            valID_f3 = input.nextLine();

            if (valID_f3.trim().length() > 0) {
                val1_f3 = true;
            } else {
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while (val1_f3 == false);
        p.println("Client details\n----------------");
        int a = 0, b = 0;
        for (i = 0; i < clientList.size(); i++) {
            if (valID_f3.equalsIgnoreCase(clientList.get(i).getClientID())) {
                int c = 0, z = 0;
                String d = "", e = "";
                clientList.get(i).printClient();
                //Display services
                p.println("\nService details: ");
                for (z = 0; z < servList.size(); z++) {
                    if ((clientList.get(i).getClientID()).equalsIgnoreCase(servList.get(z).getClientID())) {
                        servList.get(z).printServ();
                        p.println();

                        //Display subscriptions
                        for (a = 0; a < subsList.size(); a++) {
                            if ((servList.get(z).getSmartCardNo()).equalsIgnoreCase(subsList.get(a).getSmartCardNo())) {
                                subsList.get(a).printSubs();
                                p.println();
                                d = subsList.get(a).getPkgCode();

                                //Display packages
                                p.println("Subscribed Package(s):");
                                for (b = 0; b < pkgList.size(); b++) {
                                    if (d.equalsIgnoreCase(pkgList.get(b).getPkgCode())) {
                                        pkgList.get(b).printPkg();
                                        p.println();
                                        e = pkgList.get(b).getPkgCode();

                                        //Display packaging
                                        p.println("This package consist of ");
                                        for (c = 0; c < pckgingList.size(); c++) {
                                            if (e.equalsIgnoreCase(pckgingList.get(c).getPkgCode())) {
                                                pckgingList.get(c).printPckging();
                                                p.println("-----------------------------------");
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }// end display client details

    /*--------------------------------------------------------------------------SECTION FOUR - MANAGE CLIENTS----------------------------------------------------------------------------------------*/
    @SuppressWarnings("unused")
    public void manageClients() {
        String ch1_f4, ch2_f4, valID_f4;
        boolean val1_f4 = false;  //check emptiness and valid string
        boolean val2_f4 = false;
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Manage Client's Profile' function.");
        logList.addLast(log);

        p.println("\nManage Client's Profile");
        p.println("-----------------------------------------------------");


        do {
            p.println("Please select a function(Add/ Edit/ Terminate): \n");
            p.println("Enter ADD to add a new client.\n");
            p.println("Enter EDIT to edit an existing client.\n");
            p.println("Enter TERMINATE to terminate an existing client.\n");
            p.println("Enter RECOVER to recover an existed and terminated client.\n");
            p.println("-----------------------------------------------------");
            ch1_f4 = input.nextLine();

            ch1_f4 = ch1_f4.toLowerCase();

            if ((ch1_f4.trim().length() > 0) && ((ch1_f4.equalsIgnoreCase("add") || ch1_f4.equalsIgnoreCase("edit")) || (ch1_f4.equalsIgnoreCase("terminate")) || (ch1_f4.equalsIgnoreCase("recover")))) {

                val1_f4 = true;
            } else {
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while (val1_f4 == false);

        switch (ch1_f4) {
            case "add":        //Add a new client's profile
                log = new LogFile(lgTime, username, "has chosen to add a new client profile.");
                logList.addLast(log);

                do {
                    String type = "";
                    do {
                        p.print("Please enter client's type:\n");
                        p.println("Enter IND to add a client to the 'Individual' category.\n");
                        p.println("Enter GOV to add a client to the 'Government' category.\n");
                        p.println("Enter NGO to add a client to the 'NGO' category.\n");
                        p.println("Enter PRV to add a client to the 'Private Organisation' category..\n");
                        p.println("-----------------------------------------------------");
                        type = input.nextLine();
                        p.println();

                        if (type.trim().length() == 0) {
                            p.println("Blank input occured, please enter a type.");
                            p.println();
                        }
                    } while (type.trim().length() == 0);

                    switch (type.toLowerCase()) {

                        case "ind":
                            try {
                                String name = "";
                                do {
                                    p.print("Please enter client's name: ");
                                    name = input.nextLine();
                                    p.println();

                                    if (name.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your name.");
                                        p.println();
                                    }

                                } while (name.trim().length() == 0);

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
                                String ic = "";
                                do {

                                    p.print("Please enter client's IC number: ");
                                    ic = input.nextLine();
                                    p.println();

                                    if (ic.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your IC number.");
                                        p.println();
                                    }

                                } while (ic.trim().length() == 0);

                                String address = "";
                                do {
                                    p.print("Please enter client's address: ");
                                    address = input.nextLine();
                                    p.println();

                                    if (address.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your address.");
                                        p.println();
                                    }

                                } while (address.trim().length() == 0);


                                String y = "";
                                do {
                                    String s = "";
                                    do {

                                        p.print("Please enter client's ID: [I######]");
                                        s = input.nextLine();
                                        p.println();

                                        if (s.trim().length() == 0) {
                                            p.println("Blank input occured, please enter client's ID.");
                                            p.println();
                                        }
                                    } while (s.trim().length() == 0);


                                    if (s.matches(InCl_IDregex)) {
                                        for (i = 0; i < clientList.size(); i++) {
                                            if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                                p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                                check = false;
                                                break;
                                            } else {
                                                y = s;
                                                check = true;
                                            }
                                        }
                                        val = true;
                                    } else {
                                        p.println("Invalid ID format.");
                                        val = false;
                                    }

                                } while ((check == false) || (val == false));
                                p.println();

                                String a = "";
                                do {
                                    String accStatus = "";
                                    do {
                                        p.print("Please enter status of Account <active/inactive>: ");
                                        accStatus = input.nextLine();
                                        p.println();
                                        if (accStatus.trim().length() == 0) {
                                            p.println("Blank input occured, please enter a status.");
                                            p.println();
                                        }
                                    } while (accStatus.trim().length() == 0);

                                    if (accStatus.equalsIgnoreCase("active") || accStatus.equalsIgnoreCase("inactive") || accStatus.equalsIgnoreCase("terminated")) {
                                        a = accStatus;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new IndividualClient(name, g, ic, address, y, a));
                                p.println("You have successfully created a new account!");
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "gov":
                            try {
                                String name = "";
                                do {

                                    p.print("Please enter client's name: ");
                                    name = input.nextLine();
                                    p.println();

                                    if (name.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your name.");
                                        p.println();
                                    }

                                } while (name.trim().length() == 0);

                                String address = "";
                                do {
                                    p.print("Please enter client's address: ");
                                    address = input.nextLine();
                                    p.println();

                                    if (address.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your address.");
                                        p.println();
                                    }

                                } while (address.trim().length() == 0);

                                String y = "";
                                do {
                                    String s = "";
                                    do {
                                        p.print("Please enter client's ID: [G######]");
                                        s = input.nextLine();
                                        p.println();
                                        if (s.trim().length() == 0) {
                                            p.println("Blank input occured, please enter client's ID.");
                                            p.println();
                                        }
                                    } while (s.trim().length() == 0);

                                    if (s.matches(GovCl_IDregex)) {
                                        for (i = 0; i < clientList.size(); i++) {
                                            if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                                p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                                check = false;
                                                break;
                                            } else {
                                                y = s;
                                                check = true;
                                            }
                                        }
                                        val = true;
                                    } else {
                                        p.println("Invalid ID format.");
                                        val = false;
                                    }

                                } while (check == false || (val == false));
                                p.println();


                                String a = "";
                                do {
                                    String accStatus2 = "";
                                    do {
                                        p.print("Please enter status of Account <active/inactive>: ");
                                        accStatus2 = input.nextLine();
                                        p.println();
                                        if (accStatus2.trim().length() == 0) {
                                            p.println("Blank input occured, please enter a status.");
                                            p.println();
                                        }

                                    } while (accStatus2.trim().length() == 0);

                                    if (accStatus2.equalsIgnoreCase("active") || accStatus2.equalsIgnoreCase("inactive") || accStatus2.equalsIgnoreCase("terminated")) {
                                        a = accStatus2;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new GovClient(name, address, y, a));
                                p.println("You have successfully created a new account!");
                                val2_f4 = true;
                            } catch (Exception e) {
                            }
                            break;
                        case "ngo":
                            try {
                                String name = "";
                                do {

                                    p.print("Please enter client's name: ");
                                    name = input.nextLine();
                                    p.println();

                                    if (name.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your name.");
                                        p.println();
                                    }

                                } while (name.trim().length() == 0);

                                String address = "";
                                do {

                                    p.print("Please enter client's address: ");
                                    address = input.nextLine();
                                    p.println();

                                    if (address.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your address.");
                                        p.println();
                                    }

                                } while (address.trim().length() == 0);

                                String y = "";

                                do {
                                    String s = "";
                                    do {
                                        p.print("Please enter client's ID:[N######] ");
                                        s = input.nextLine();
                                        p.println();

                                        if (s.trim().length() == 0) {
                                            p.println("Blank input occured, please enter client's ID.");
                                            p.println();
                                        }
                                    } while (s.trim().length() == 0);

                                    if (s.matches(NGOCl_IDregex)) {
                                        for (i = 0; i < clientList.size(); i++) {
                                            if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                                p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                                check = false;
                                                break;
                                            } else {
                                                y = s;
                                                check = true;
                                            }
                                        }
                                        val = true;
                                    } else {
                                        p.println("Invalid ID format.");
                                        val = false;
                                    }

                                } while (check == false || (val == false));
                                p.println();


                                String a = "";
                                do {
                                    String accStatus3 = "";
                                    do {

                                        p.print("Please enter status of Account <active/inactive>: ");
                                        accStatus3 = input.nextLine();
                                        p.println();

                                        if (accStatus3.trim().length() == 0) {
                                            p.println("Blank input occured, please enter a status.");
                                            p.println();
                                        }

                                    } while (accStatus3.trim().length() == 0);

                                    if (accStatus3.equalsIgnoreCase("active") || accStatus3.equalsIgnoreCase("inactive") || accStatus3.equalsIgnoreCase("terminated")) {
                                        a = accStatus3;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new NGOClient(name, address, y, a));
                                p.println("You have successfully created a new account!");
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        case "prv":
                            try {
                                String name = "";
                                do {

                                    p.print("Please enter client's name: ");
                                    name = input.nextLine();
                                    p.println();

                                    if (name.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your name.");
                                        p.println();
                                    }

                                } while (name.trim().length() == 0);

                                String address = "";
                                do {

                                    p.print("Please enter client's address: ");
                                    address = input.nextLine();
                                    p.println();

                                    if (address.trim().length() == 0) {
                                        p.println("Blank input occured, please enter your address.");
                                        p.println();
                                    }

                                } while (address.trim().length() == 0);

                                String y = "";
                                do {
                                    String s = "";
                                    do {

                                        p.print("Please enter client's ID:[P######]");
                                        s = input.nextLine();
                                        p.println();

                                        if (s.trim().length() == 0) {
                                            p.println("Blank input occured, please enter client's ID.");
                                            p.println();
                                        }

                                    } while (s.trim().length() == 0);

                                    if (s.matches(PrvCl_IDregex)) {
                                        for (i = 0; i < clientList.size(); i++) {
                                            if (s.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                                p.println("Sorry, the ID has been taken. Please enter an unused ID.");
                                                check = false;
                                                break;
                                            } else {
                                                y = s;
                                                check = true;
                                            }
                                        }
                                    } else {
                                        p.println("Invalid ID format.");
                                        val = false;
                                    }

                                } while (check == false || (val == false));
                                p.println();

                                String a = "";
                                do {
                                    String accStatus4 = "";
                                    do {

                                        p.print("Please enter status of Account <active/inactive>: ");
                                        accStatus4 = input.nextLine();
                                        p.println();

                                        if (accStatus4.trim().length() == 0) {
                                            p.println("Blank input occured, please enter a status.");
                                            p.println();
                                        }

                                    } while (accStatus4.trim().length() == 0);

                                    if (accStatus4.equalsIgnoreCase("active") || accStatus4.equalsIgnoreCase("inactive") || accStatus4.equalsIgnoreCase("terminated")) {
                                        a = accStatus4;
                                        stat = true;
                                        break;
                                    } else {
                                        p.println("Invalid status, please re-enter.");
                                        stat = false;
                                    }
                                } while (stat == false);

                                clientList.add(new PrvClient(name, address, y, a));
                                p.println("You have successfully created a new account!");
                                val2_f4 = true;
                            } catch (Exception e) {
                            }

                            break;
                        default:
                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                            break;
                    }
                } while (val2_f4 == false);

                break;
            case "edit":
                log = new LogFile(lgTime, username, "has chosen to edit a client.");
                logList.addLast(log);

                do {
                    p.print("Enter the Client's ID that you would like to edit: ");
                    valID_f4 = input.nextLine();
                    p.println();

                    if (valID_f4.trim().length() == 0) {
                        p.println("Blank input occured, please enter a Client's ID.");
                        p.println();
                    }

                } while (valID_f4.trim().length() == 0);

                for (i = 0; i < clientList.size(); i++) {
                    if (valID_f4.equalsIgnoreCase(clientList.get(i).getClientID())) {

                        if (((clientList.get(i).getAccountStatus()).equalsIgnoreCase("inactive")) || ((clientList.get(i).getAccountStatus()).equalsIgnoreCase("active"))) {

                            p.println("Select data to edit(Name/Age/IC/Address/CreationDate/AccountStatus): ");
                            p.println("Enter NAME to edit the name of the client.\n");
                            p.println("Enter AGE to edit the age of the client.\n");
                            p.println("Enter IC to edit the IC of the client.\n");
                            p.println("Enter ADDRESS to edit the address of the client.\n");
                            p.println("Enter CRDATE to creation date the name of the client.\n");
                            p.println("Enter ACCSTAT to edit the account status of the client.\n");
                            ch2_f4 = input.nextLine();


                            switch (ch2_f4.toLowerCase()) {
                                case "name":
                                    String newName_f4 = "";
                                    do {

                                        p.println("Enter the new name: ");
                                        newName_f4 = input.nextLine();
                                        p.println();

                                        if (newName_f4.trim().length() == 0) {
                                            p.println("Blank input occured, please enter your name.");
                                            p.println();
                                        }
                                    } while (newName_f4.trim().length() == 0);

                                    clientList.get(i).setName(newName_f4);

                                    p.println("\nClient '" + valID_f4 + "' 's name has been edited to '" + newName_f4 + "'.");
                                    log = new LogFile(lgTime, username, "has edited client '" + valID_f4 + "' 's name to '" + newName_f4 + "'.");
                                    logList.addLast(log);
                                    break;

                                case "age":
                                    if (clientList.get(i) instanceof IndividualClient) {    //Re-casting
                                        IndividualClient client = (IndividualClient) clientList.get(i);
                                        //input.nextLine();

                                        String newAgeI_f4 = "";
                                        int g = 0;
                                        do {
                                            p.print("Enter the new age: ");
                                            newAgeI_f4 = input.nextLine();

                                            if (newAgeI_f4.matches("^[1-9][0-9]$") || newAgeI_f4.matches("^[0][1-9][0-9]$")) {
                                                g = Integer.parseInt(newAgeI_f4);
                                                val_age = true;
                                                break;
                                            } else
                                                p.println("Only integer is allowed. [10-99]");
                                            val_age = false;
                                        } while (val_age == false);

                                        client.setAge(g);
                                        p.println("\nClient '" + valID_f4 + "' 's age has been edited.");
                                        log = new LogFile(lgTime, username, "has edited client '" + valID_f4 + "' 's age.");
                                        logList.addLast(log);
                                        break;
                                    } else {
                                        p.println("\nThis is only for Individual Client!");
                                        log = new LogFile(lgTime, username, "has not edited Client '" + valID_f4 + "' details[CLIENT TYPE NOT ALLOWED].");
                                        logList.addLast(log);
                                        break;
                                    }
                                case "ic":
                                    if (clientList.get(i) instanceof IndividualClient) {    //Re-casting
                                        IndividualClient client = (IndividualClient) clientList.get(i);
                                        String f4nic = "";
                                        do {
                                            p.println("Enter the new IC: ");
                                            f4nic = input.nextLine();
                                            p.println();

                                            if (f4nic.trim().length() == 0) {
                                                p.println("Blank input occured, please enter your IC.");
                                                p.println();
                                            }

                                        } while (f4nic.trim().length() == 0);

                                        client.setIC(f4nic);
                                        p.println("\nClient '" + valID_f4 + "' 's IC has been edited.");
                                        log = new LogFile(lgTime, username, "has edited client '" + valID_f4 + "' 's IC.");
                                        logList.addLast(log);
                                        break;
                                    } else {
                                        p.println("\nThis is only for Individual Client!");
                                        log = new LogFile(lgTime, username, "has not edited Client '" + valID_f4 + "' details[CLIENT TYPE NOT ALLOWED].");
                                        logList.addLast(log);
                                        break;
                                    }
                                case "address":
                                    String f4naddress = "";
                                    do {
                                        p.println("Enter the new address: ");
                                        f4naddress = input.nextLine();
                                        p.println();
                                        if (f4naddress.trim().length() == 0) {
                                            p.println("Blank input occured, please enter your address.");
                                            p.println();
                                        }
                                    } while (f4naddress.trim().length() == 0);

                                    clientList.get(i).setName(f4naddress);
                                    p.println("\nClient '" + valID_f4 + "' 's address has been edited.");
                                    log = new LogFile(lgTime, username, "has edited client '" + valID_f4 + "' 's address.");
                                    logList.addLast(log);
                                    break;

                                case "accstat":
                                    String f4naccstatus = "";
                                    do {
                                        do {
                                            p.println("Enter the new account status(active/inactive): ");
                                            f4naccstatus = input.nextLine();
                                            p.println();

                                            if (f4naccstatus.trim().length() == 0) {
                                                p.println("Blank input occured, please enter a status.");
                                                p.println();
                                            }

                                        } while (f4naccstatus.trim().length() == 0);

                                        if ((!(f4naccstatus.equalsIgnoreCase("active"))) && (!(f4naccstatus.equalsIgnoreCase("inactive"))))
                                            p.println("Sorry, invalid status input. Please input again.");
                                    }
                                    while ((!(f4naccstatus.equalsIgnoreCase("active"))) && (!(f4naccstatus.equalsIgnoreCase("inactive"))));

                                    clientList.get(i).setName(f4naccstatus);
                                    p.println("\nClient '" + valID_f4 + "' 's account status has been edited.");
                                    log = new LogFile(lgTime, username, "has edited client '" + valID_f4 + "' 's account status.");
                                    logList.addLast(log);
                                    break;
                                default:
                                    p.println("Action '" + ch2_f4 + "' does not exist!");
                                    log = new LogFile(lgTime, username, "has not edited Client '" + valID_f4 + "' details[ACTION DOES NOT EXIST].");
                                    logList.addLast(log);
                                    break;
                            }
                            break;
                        } else {
                            p.println("\nClient '" + valID_f4 + "' cannot be EDITED as it has already been terminated.");
                            log = new LogFile(lgTime, username, "has not edited Client '" + valID_f4 + "' details[CLIENT WAS TERMINATED].");
                            logList.addLast(log);
                            break;
                        }

                    } else {
                        if (i == (clientList.size() - 1)) {
                            p.println("\nClient '" + valID_f4 + "' does not exist!");

                            log = new LogFile(lgTime, username, "has not edited client '" + valID_f4 + "' details[CLIENT DOES NOT EXIST].");
                            logList.addLast(log);
                        }
                    }
                }
                break;
            //Terminate a client's profile
            case "terminate":
                if (userList.get(u) instanceof Administrators) {
                    log = new LogFile(lgTime, username, "has chosen to terminate a client.");
                    logList.addLast(log);

                    do {
                        p.print("Enter the client's ID that you would like to terminate: ");
                        valID_f4 = input.nextLine();

                        if (valID_f4.trim().length() > 0) {
                            val2_f4 = true;
                        } else {
                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        }
                    } while (val2_f4 == false);

                    for (i = 0; i < clientList.size(); i++) {
                        if (valID_f4.equalsIgnoreCase(clientList.get(i).getClientID())) {

                            if (!((clientList.get(i).getAccountStatus()).equalsIgnoreCase("terminated"))) {

                                //clientList.remove(i);
                                clientList.get(i).setAccountStatus("Terminated");
                                clientList.get(i).setTerminationDate(DateFormat.getInstance().format(new Date()));
                                for (j = 0; j < servList.size(); j++) {
                                    if (clientList.get(i).getClientID().equalsIgnoreCase(servList.get(j).getClientID())) {
                                        servList.get(j).setServStatus("Terminated");
                                        servList.get(j).setTermDate(DateFormat.getInstance().format(new Date()));
                                        break;
                                    }
                                }
                                p.println("\nClient '" + valID_f4 + "' has been terminated.");
                                log = new LogFile(lgTime, username, "has terminated Client '" + valID_f4 + "'.");
                                logList.addLast(log);
                                break;

                            } else {
                                p.println("\nClient '" + valID_f4 + "' cannot be TERMINATED as it has already been terminated.");
                                log = new LogFile(lgTime, username, "has not terminated Client '" + valID_f4 + "' details[CLIENT WAS TERMINATED].");
                                logList.addLast(log);
                                break;
                            }
                        } else {
                            if (i == (clientList.size() - 1)) {
                                p.println("Client '" + valID_f4 + "' does not exist!");
                                log = new LogFile(lgTime, username, "has not terminated Client '" + valID_f4 + "' details[CLIENT DOES NOT EXIST].");
                                logList.addLast(log);
                            }
                        }
                    }
                    break;
                } else {
                    p.println("\nOnly Admin has the accessibility to recover a client.");
                    log = new LogFile(lgTime, username, "has not terminated any client[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                    logList.addLast(log);
                }
            case "recover":
                if (userList.get(u) instanceof Administrators) {
                    log = new LogFile(lgTime, username, "has chosen to recover a client.");
                    logList.addLast(log);

                    boolean sel_f4 = false;
                    do {
                        p.print("Enter the client's ID that you would like to recover: ");
                        valID_f4 = input.nextLine();
                        if (valID_f4.trim().length() > 0) {
                            val2_f4 = true;
                        } else {
                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        }
                    } while (val2_f4 == false);

                    for (i = 0; i < clientList.size(); i++) {
                        if (valID_f4.equalsIgnoreCase(clientList.get(i).getClientID())) {
                            if (clientList.get(i).terminationStatus() == true) {
                                clientList.get(i).setAccountStatus("Active");
                                clientList.get(i).setTerminationDate("N/A");

                                for (j = 0; j < servList.size(); j++) {
                                    if (clientList.get(i).getClientID().equalsIgnoreCase(servList.get(j).getClientID())) {
                                        String termSCD = servList.get(j).getSmartCardNo();
                                        p.println("Recover service?");


                                        do {
                                            p.println("Enter YES to recover.");
                                            p.println("Enter NO to clear all the service information.\n");
                                            sel = input.nextLine();
                                            if (sel.trim().length() > 0) {
                                                sel_f4 = true;
                                            } else {
                                                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                                            }
                                        } while (sel_f4 == false);

                                        do {
                                            switch (sel.toLowerCase()) {
                                                case "yes":
                                                    val = true;
                                                    servList.get(j).setServStatus("Active");
                                                    servList.get(j).setTermDate("N/A");

                                                case "no":
                                                    val = true;
                                                    servList.remove(j);
                                                    for (k = 0; k < subsList.size(); k++) {
                                                        if ((servList.get(j).getSmartCardNo()).equalsIgnoreCase(subsList.get(k).getSmartCardNo())) {
                                                            subsList.remove(k);
                                                            p.println("You can register for a new smart card number at the 'add service' function.");
                                                            break;
                                                        }
                                                    }
                                                    break;
                                                default:
                                                    p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                                                    val = false;

                                            } //end switch
                                        } while (val == false);
                                        p.println("\nClient '" + valID_f4 + "' has been recovered.");

                                        log = new LogFile(lgTime, username, "has recovered Client '" + valID_f4 + "'.");
                                        logList.addLast(log);
                                        break;
                                    }
                                }
                            } else {
                                p.println("\nClient '" + valID_f4 + "' cannot be recovered as it is not terminated.");
                                log = new LogFile(lgTime, username, "has not recover Client '" + valID_f4 + "' details[CLIENT IS ACTIVE].");
                                logList.addLast(log);
                                break;
                            }
                            flag2 = true;
                            break;
                        } else
                            flag2 = false;
                    }
                    if (flag2 == false) {
                        p.println("Client '" + valID_f4 + "' does not exist!");
                        log = new LogFile(lgTime, username, "has not recover Client '" + valID_f4 + "' details[CLIENT DOES NOT EXIST].");
                        logList.addLast(log);
                    }
                } else {
                    p.println("\nOnly Admin has the accessibility to recover a client.");
                    log = new LogFile(lgTime, username, "has not recovered any client[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                    logList.addLast(log);
                    break;
                }
        }
    } // end manage client

    /*--------------------------------------------------------------------------SECTION FIVE - MANAGE SERVICES----------------------------------------------------------------------------------------*/
    public void manageService() {
        val = false;
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        do {
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("UNITV RYCOX CUSTOMER MANAGEMENT MODULE(CMM)");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.println("1. Create new Service");
            p.println("2. Edit Service");
            p.println("3. Terminated Service");
            p.println("4. Check Service detail");
            p.println("5. Back");
            p.println("=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=");
            p.print("\nEnter Option: ");
            menu3_opt = input.nextLine();

            if ((menu3_opt.matches("^[1-5]{1}$")) || (menu3_opt.matches("^[0]([0]|[1])$"))) {
                menu3_opt_1 = Integer.parseInt(menu3_opt);

                switch (menu3_opt_1) {
                    case 1:
                        val = false;

                        do {
                            do {
                                p.println("Please Enter the Client ID");
                                String clientID = input.nextLine();

                                if (clientID.matches(Cl_IDregex)) {
                                    val = true;
                                } else {
                                    val = false;
                                    p.println("Incorrect Client ID format.\n");
                                }
                            } while (val == false);

                            val = false;

                            for (i = 0; i < clientList.size(); i++) {
                                if (clientID.equalsIgnoreCase(clientList.get(i).getClientID())) {
                                    val = true;
                                    break;
                                }
                            }

                            if (val == false)
                                p.println("Invalid Client ID, Please Check the Client ID.\n");


                        } while (val == false);

                        if (clientList.get(i).getAccountStatus().equalsIgnoreCase("active")) {
                            do {
                                val = true;
                                do {
                                    p.println("Please Enter the Smart Card Number: [S######] ");
                                    smartCardNo = input.nextLine();

                                    if (smartCardNo.matches(ScNo_IDregex)) {
                                        val = true;
                                    } else {
                                        val = false;
                                        p.println("Incorrect Smart Card format.\n");
                                    }
                                } while (val == false);
                                for (int i = 0; i < servList.size(); i++) {
                                    if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                        val = false;
                                        p.println("This is not a new Smart Card.\n");
                                        break;
                                    }
                                }
                            } while (val == false);

                            do {
                                val = true;
                                do {
                                    p.println("Please Enter the Decoder Number: [D######]");
                                    decoderNo = input.nextLine();

                                    if (decoderNo.matches(decNo_IDregex)) {
                                        val = true;
                                    } else {
                                        val = false;
                                        p.println("Incorrect Decoder Number format.\n");
                                    }
                                } while (val == false);
                                for (int i = 0; i < servList.size(); i++) {
                                    if (decoderNo.equalsIgnoreCase(servList.get(i).getDecodeNo())) {
                                        val = false;
                                        p.println("This is not a new Decoder.\n");
                                        break;
                                    }
                                }
                            } while (val == false);

                            p.println("Please Enter the Address: ");
                            String address = input.nextLine();

                            servList.add(new Service(smartCardNo, clientID, decoderNo, address));

                            for (int i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    servList.get(i).setServStatus("Active");
                                    p.println("Your Service has been Active.\n");
                                }
                            }

                            log = new LogFile(lgTime, username, "has created a new Service for " + clientID + " Smart Card " + smartCardNo);
                            logList.addLast(log);
                        } else
                            p.println("The Client ID is not active.\n");

                        break;


                    case 2:
                        val = false;
                        do {
                            do {
                                p.println("Please Enter the Smart Card Number: ");
                                smartCardNo = input.nextLine();

                                if (smartCardNo.matches(ScNo_IDregex)) {
                                    val = true;
                                } else {
                                    val = false;
                                    p.println("Incorrect Smart Card format.\n");
                                }
                            } while (val == false);
                            val = false;

                            for (i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    val = true;
                                    break;
                                }
                            }

                            if (val == false) {
                                p.println("Invalid Smart Card Number, Please Check the Smart Card Number.\n");
                            }
                        } while (val == false);

                        if (servList.get(i).getServStatus().equalsIgnoreCase("terminated")) {
                            p.println("The Smart Card has been terminated.");
                            break;
                        }

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

                                        log = new LogFile(lgTime, username, "has edited the address of " + smartCardNo);
                                        logList.addLast(log);
                                        break;

                                    case 2:
                                        do {
                                            val = true;
                                            oldSmartCard = smartCardNo;
                                            do {
                                                p.println("Please Enter the Smart Card Number: ");
                                                smartCardNo = input.nextLine();

                                                if (smartCardNo.matches(ScNo_IDregex)) {
                                                    val = true;
                                                } else {
                                                    val = false;
                                                    p.println("Incorrect Smart Card format.\n");
                                                }
                                            } while (val == false);

                                            for (int j = 0; j < servList.size(); j++) {
                                                if (smartCardNo.equalsIgnoreCase(servList.get(j).getSmartCardNo())) {
                                                    val = false;
                                                    break;
                                                }
                                            }

                                            if (val == false)
                                                p.println("It isn't a new Smart Card.\n");

                                            else if (val = true) {
                                                servList.get(i).setSmartCardNo(smartCardNo);
                                                p.println("Smart Card change Success.\n");
                                            }
                                        } while (val == false);

                                        log = new LogFile(lgTime, username, "has changed the Smart Card Number " + oldSmartCard + " to " + smartCardNo);
                                        logList.addLast(log);
                                        break;

                                    case 3:
                                        do {
                                            val = true;
                                            oldDecoder = decoderNo;
                                            do {
                                                p.println("Please Enter the Decoder Number: ");
                                                decoderNo = input.nextLine();

                                                if (decoderNo.matches(decNo_IDregex)) {
                                                    val = true;
                                                } else {
                                                    val = false;
                                                    p.println("Incorrect Decoder Number format.\n");
                                                }
                                            } while (val == false);

                                            for (int j = 0; j < servList.size(); j++) {
                                                if (decoderNo.equalsIgnoreCase(servList.get(j).getDecodeNo())) {
                                                    val = false;
                                                    break;
                                                }
                                            }

                                            if (val == false) {
                                                p.println("It isn't a new Decoder.\n");
                                            } else if (val == true) {
                                                servList.get(i).setDecoderNo(decoderNo);
                                                p.println("Decoder change Success.\n");
                                            }
                                        } while (val == false);

                                        log = new LogFile(lgTime, username, "has changed the Decoder from " + oldDecoder + " to " + decoderNo);
                                        logList.addLast(log);
                                        break;

                                    case 4:
                                        val = false;

                                        do {
                                            p.println("Please Enter the Service Status (Active, Inactive or Barred): ");
                                            servStatus = input.nextLine();
                                            servStatus.toLowerCase();

                                            if (servStatus.equals("active")) {
                                                val = true;
                                                servList.get(i).setServStatus(servStatus);
                                                p.println("The Smart Card status has set to Active.\n");
                                            } else if (servStatus.equals("inactive")) {
                                                val = true;
                                                servList.get(i).setServStatus(servStatus);
                                                p.println("The Smart Card status has set to Inactive.\n");
                                            } else if (servStatus.equals("barred")) {
                                                val = true;
                                                servList.get(i).setServStatus(servStatus);
                                                p.println("The Smart Card status has set to Barred.\n");
                                            } else {
                                                val = false;
                                                p.println("Invalid Enter.\n");
                                            }
                                        } while (val == false);

                                        log = new LogFile(lgTime, username, "has change the " + smartCardNo + " status to " + servStatus);
                                        logList.addLast(log);
                                        break;

                                    case 5:
                                        val = true;
                                        break;

                                    default:
                                        val = false;
                                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                                        break;
                                }
                            } else {
                                val = false;
                                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                            }
                        } while ((menu3_opt_1 != 5) || (val == false));
                        break;

                    case 3:
                        //	for (u=0; u<userList.size(); u++){
                        if (userList.get(u) instanceof FrontdeskStaffs) {
                            p.println("Only Admin has the accessibility to use this hidden function.\n");

                            log = new LogFile(lgTime, username, "has not Terminated any user[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                            logList.addLast(log);
                            break;
                        } else if (userList.get(u) instanceof Administrators) {
                            do {
                                val = false;
                                do {
                                    p.println("Please Enter the Smart Card Number: ");
                                    smartCardNo = input.nextLine();

                                    if (smartCardNo.matches(ScNo_IDregex)) {
                                        val = true;
                                    } else {
                                        val = false;
                                        p.println("Incorrect Smart Card format.\n");
                                    }
                                } while (val == false);
                                val = false;

                                for (i = 0; i < servList.size(); i++) {
                                    if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                        val = true;
                                        break;
                                    }
                                }

                                if (val == true) {
                                    if (servList.get(i).getServStatus().equalsIgnoreCase("terminated")) {
                                        p.println("The Smart Card was terminated before.\n");
                                    } else {
                                        servList.get(i).setServStatus("terminated");
                                        p.println("The Smart Card has been terminated successful.\n");

                                        log = new LogFile(lgTime, username, "has terminated the Smart Card " + smartCardNo);
                                        logList.addLast(log);
                                    }
                                } else if (val == false) {
                                    p.println("Invalid Smart Card Entered.\n");
                                }
                            } while (val == false);
                            break;
                        }
                        //	}

                        break;

                    case 4:
                        val = false;

                        do {
                            do {
                                p.println("Please Enter the Smart Card Number: ");
                                smartCardNo = input.nextLine();

                                if (smartCardNo.matches(ScNo_IDregex)) {
                                    val = true;
                                } else {
                                    val = false;
                                    p.println("Incorrect Smart Card format.\n");
                                }
                            } while (val == false);
                            val = false;

                            for (i = 0; i < servList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(servList.get(i).getSmartCardNo())) {
                                    val = true;
                                    break;
                                }
                            }

                            if (val == false) {
                                p.println("Invalid Smart Card Number, Please Check the Smart Card Number.\n");
                            }
                        } while (val == false);

                        servList.get(i).printServ();
                        break;

                    case 5:
                        val = true;
                        showMenu();
                        break;

                    default:
                        val = false;
                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                val = false;
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 5) || (val == false));
    } // end manage service

    /*--------------------------------------------------------------------------SECTION SIX - MANAGE SUBSCRIPTION----------------------------------------------------------------------------------------*/
    public void manageSubscription() {
        val = false;
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
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

                boolean repeat = false;
                int subsNo = 0;

                switch (menu3_opt_1) {
                    case 1:
                        do {
                            val = true;

                            do {
                                p.println("Please Enter the Smart Card Number: ");
                                smartCardNo = input.nextLine();

                                if (smartCardNo.matches(ScNo_IDregex)) {
                                    val = true;
                                } else {
                                    val = false;
                                    p.println("Incorrect Smart Card format.\n");
                                }
                            } while (val == false);

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    p.println("This Smart Card have a subsciption already.\n");
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

                                    for (j = 0; j < servList.size(); j++) {
                                        if (clientID.equalsIgnoreCase(servList.get(j).getClientID())) {
                                            subsNo++;
                                        }
                                    }
                                } else if (val == false) {
                                    p.println("Invalid Smart Card Entered, Please Enter again.\n");
                                }
                            }
                        } while (val == false);

                        if (servList.get(i).getServStatus().equalsIgnoreCase("active")) {
                            do {
                                val = false;

                                p.println("Please Enter the Package Code want to Subscribe: ");
                                pkgCode = input.nextLine();

                                for (i = 0; i < subsList.size(); i++) {
                                    if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                        if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                            val = true;
                                            p.println("You have already Subscibe the package.\n");
                                        }
                                    }
                                }

                                if (val == false) {
                                    for (i = 0; i < pkgList.size(); i++) {
                                        if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                            val = true;
                                            p.println("Package has added successfully.\n");
                                            break;
                                        }
                                    }

                                    if (val == true) {
                                        subsList.add(new Subscription(smartCardNo, subsNo, pkgCode));

                                        log = new LogFile(lgTime, username, "has created a subsciption " + subsNo + " for Client " + clientID + " Smart Card" + smartCardNo + " with package " + pkgCode);
                                        logList.addLast(log);

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
                                                p.println("Invalid Enter, Please Enter again.\n");
                                            }
                                        } while (val == false);
                                    } else if (val == false) {
                                        p.println("Invalid Package Code, Please Enter Again!\n");
                                    }
                                }

                            } while ((val == false) || (repeat == true));
                        } else {
                            p.println("The Smart Card is not Active.\n");
                        }

                        break;

                    case 2:
                        val = false;

                        do {
                            do {
                                p.println("Please Enter the Smart Card Number: ");
                                smartCardNo = input.nextLine();

                                if (smartCardNo.matches(ScNo_IDregex)) {
                                    val = true;
                                } else {
                                    val = false;
                                    p.println("Incorrect Smart Card format.\n");
                                }
                            } while (val == false);
                            val = false;

                            for (i = 0; i < subsList.size(); i++) {
                                if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                    val = true;
                                    subsNo = subsList.get(i).getSubsNo();
                                    break;
                                }
                            }

                            if (val == false) {
                                p.println("Invalid Smart Card Number, Please Enter again.\n");
                            }
                        } while (val == false);

                        if (servList.get(i).getServStatus().equalsIgnoreCase("active")) {
                            do {
                                val = false;
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
                                                p.println("Please Enter the package want to add: ");
                                                pkgCode = input.nextLine();

                                                for (i = 0; i < subsList.size(); i++) {
                                                    if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                        if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                            val = true;
                                                            p.println("You have already Subscibe the package.\n");
                                                            break;
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
                                                        p.println("Package add successful.\n");
                                                    } else if (val == false) {
                                                        p.println("Invalid Package Code, Please Enter again.\n");
                                                    }
                                                }
                                            } while (val == false);

                                            log = new LogFile(lgTime, username, "has added a package " + pkgCode + " to Smart Card " + smartCardNo);
                                            logList.addLast(log);
                                            break;

                                        case 2:
                                            val = false;

                                            do {
                                                p.println("Please Enter the package want to remove: ");
                                                pkgCode = input.nextLine();

                                                for (i = 0; i < pkgList.size(); i++) {
                                                    if (pkgCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                                        val = true;
                                                        break;
                                                    }
                                                }

                                                if (val == false) {
                                                    p.println("Invalid Package Code Enter, Please Enter agian.\n");
                                                } else if (val == true) {
                                                    val = false;

                                                    for (i = 0; i < subsList.size(); i++) {
                                                        if (smartCardNo.equalsIgnoreCase(subsList.get(i).getSmartCardNo())) {
                                                            if (pkgCode.equalsIgnoreCase(subsList.get(i).getPkgCode())) {
                                                                val = true;
                                                                subsList.remove(subsList.get(i));
                                                                p.println("Package remove succeessful.\n");
                                                                break;
                                                            }
                                                        }
                                                    }

                                                    if (val == false) {
                                                        val = true;
                                                        p.println("You are not subscibe the package.\n");
                                                    }
                                                }
                                            } while (val == false);

                                            log = new LogFile(lgTime, username, "has removed a package " + pkgCode + " from Smart Card " + smartCardNo);
                                            logList.addLast(log);
                                            break;

                                        case 3:
                                            val = true;
                                            break;

                                        default:
                                            val = false;
                                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                                            break;
                                    }
                                } else {
                                    val = false;
                                    p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                                }
                            } while ((menu3_opt_1 != 3) || (val == false));
                        } else {
                            p.println("The Smart Card is not Active.\n");
                        }

                        break;

                    case 3:
                        val = true;
                        showMenu();
                        break;

                    default:
                        val = false;
                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        break;
                }
            } else {
                val = false;
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while ((menu3_opt_1 != 3) || (val == false));
        ;
    }//end manage subscription

    /*--------------------------------------------------------------------------SECTION SEVEN - MANAGE PACKAGES----------------------------------------------------------------------------------------*/
    public void managePackage() {
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
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
                p.println("5. Back");
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
                                p.print("a) Package Code(P##): ");
                                pckCode = input.nextLine();
                                p.println();

                                if (pckCode.trim().length() == 0) {
                                    p.println("Blank input is not allowed.");
                                    p.println();
                                }
                            } while (pckCode.trim().length() == 0);

                            if (pckCode.matches(Pkg_IDregex)) {

                                for (i = 0; i < pkgList.size(); i++) {
                                    if (pckCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                        p.println("Sorry, the package code that you entered is already existed.");
                                        p.println("Please try another one. ");
                                        check = false;
                                        break;
                                    } else
                                        check = true;
                                }
                                val = true;
                            } else {
                                p.println("Wrong Package ID format. Please reenter again.");
                                p.println();
                                val = false;
                            }
                        } while ((check == false) || (val == false));

                        do {
                            p.print("b) Package Name: ");
                            pckName = input.nextLine();
                            p.println();
                            if (pckName.trim().length() == 0) {
                                p.println("Blank input occured, please enter a package name.");
                                p.println();
                            }
                        } while (pckName.trim().length() == 0);

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

                                if (chargeType.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a charge type.");
                                    p.println();
                                }
                            } while (chargeType.trim().length() == 0);

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

                                    if (progCode.trim().length() == 0) {
                                        p.println("Blank input occured, please enter a package name. TV Package must contain at least one TV Programme.");
                                        p.println();
                                    }
                                } while (progCode.trim().length() == 0);

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

                                            if (choice1.trim().length() == 0) {
                                                p.println("Blank input occured, please enter a choice.");
                                                p.println();
                                            }
                                        } while (choice1.trim().length() == 0);


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
                        logList.addLast(new LogFile(lgTime, username, "has created a TV Package item of ID '" + pckCode + "'."));

                        break;

                    case 2:
                        p.println();
                        input.nextLine();

                        do {
                            p.println("2. Edit a TV Package: ");
                            p.print("Please enter a package code that you want to edit: ");

                            pckCode2 = input.nextLine();

                            if (pckCode2.trim().length() == 0) {
                                p.println("Blank input is not allowed.");
                                p.println();
                            }
                        } while (pckCode2.trim().length() == 0);

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

                                                if (pkgName2.trim().length() == 0) {
                                                    p.println("Blank input occured, please enter a package name.");
                                                    p.println();
                                                }
                                            } while (pkgName2.trim().length() == 0);

                                            pkgList.get(i).setPkgName(pkgName2);
                                            p.println("You have changed the package name for package '" + pckCode2 + "'!");
                                            p.println();
                                            logList.addLast(new LogFile(lgTime, username, "has changed a package name for package '" + pckCode2 + "'."));

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
                                            logList.addLast(new LogFile(lgTime, username, "has changed a charge price for package '" + pckCode2 + "'."));
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

                                                    if (chargeType2.trim().length() == 0) {
                                                        p.println("Blank input occured, please enter a charge type.");
                                                        p.println();
                                                    }
                                                } while (chargeType2.trim().length() == 0);

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
                                            logList.addLast(new LogFile(lgTime, username, "has changed a charge type for package '" + pckCode2 + "'."));

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
                                                                if (progCode2.trim().length() == 0) {
                                                                    p.println("Blank input occured, please enter a programme code.");
                                                                    p.println();
                                                                }
                                                            } while (progCode2.trim().length() == 0);

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
                                                                logList.addLast(new LogFile(lgTime, username, "has added a tv programme into package '" + pckCode2 + "'."));
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

                                                            if (progCode3.trim().length() == 0) {
                                                                p.println("Blank input occured, please enter a programme code.");
                                                                p.println();
                                                            }

                                                        } while (progCode3.trim().length() == 0);

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
                                                                        logList.addLast(new LogFile(lgTime, username, "has removed a tv programme from package '" + pckCode2 + "'."));
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
                                            check3 = true;
                                            input.nextLine();
                                            break;

                                        default:
                                            check3 = false;
                                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
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
                            if (pckCode3.trim().length() == 0) {
                                p.println("Blank input is not allowed.");
                                p.println();
                            }
                        } while (pckCode3.trim().length() == 0);

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
                                    for (j = 0; j < subsList.size(); j++) {
                                        if ((pkgList.get(i).getPkgCode()).equalsIgnoreCase(subsList.get(j).getPkgCode())) {
                                            subsList.remove(j);
                                            break;
                                        }
                                    }
                                    p.println("You have successfully terminated package '" + pckCode3 + "'!");
                                    logList.addLast(new LogFile(lgTime, username, "has terminated a package which is '" + pckCode3 + "'."));
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

                            if (packageCode.trim().length() == 0) {
                                p.println("Blank input is not allowed.");
                                p.println();
                            }
                        } while (packageCode.trim().length() == 0);

                        for (i = 0; i < pkgList.size(); i++) {
                            if (packageCode.equalsIgnoreCase(pkgList.get(i).getPkgCode())) {
                                pkgList.get(i).printPkg();
                                p.println(" TV Programme Code: ");

                                for (j = 0; j < pckgingList.size(); j++) {
                                    if (packageCode.equalsIgnoreCase(pckgingList.get(j).getPkgCode())) {

                                        p.print("'" + pckgingList.get(j).getProgCode() + "'\n");
                                        logList.addLast(new LogFile(lgTime, username, "has displayed the details of package'" + packageCode + "' ."));
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
                        input.nextLine();
                        showMenu();
                        break;

                    default:
                        check = false;
                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
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

    /*--------------------------------------------------------------------------SECTION EIGHT - MANAGE PROGRAMMES----------------------------------------------------------------------------------------*/
    public void manageProgramme() {
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
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
                p.println("5. Back");
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
                                p.print("a) Programme Code(F###): ");
                                programmeCode = input.nextLine();
                                p.println();

                                if (programmeCode.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a programme code.");
                                    p.println();
                                }
                            } while (programmeCode.trim().length() == 0);
                            if (programmeCode.matches(Prg_IDregex)) {
                                for (i = 0; i < prgList.size(); i++) {
                                    if (programmeCode.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                        p.println("Sorry, the package code that you entered is already existed.");
                                        p.println("Please try another one: ");
                                        p.println();
                                        check = false;
                                        break;
                                    } else {
                                        check = true;
                                    }
                                }
                                val = true;
                            } else {
                                p.println("Wrong input format. Please reenter it.");
                                p.println();
                                val = false;
                            }
                        } while ((check == false) || (val == false));

                        do {
                            p.print("b) Programme Title: ");
                            programmeTitle = input.nextLine();
                            p.println();
                            if (programmeTitle.trim().length() == 0) {
                                p.println("Blank input occured, please enter a programme title.");
                                p.println();
                            }
                        } while (programmeTitle.trim().length() == 0);
                        do {

                            p.print("c) Description: ");
                            desc = input.nextLine();
                            p.println();

                            if (desc.trim().length() == 0) {
                                p.println("Blank input occured, please enter a description.");
                                p.println();
                            }
                        } while (desc.trim().length() == 0);

                        do {
                            p.print("d) Content Origin: ");
                            contentOrigin = input.nextLine();
                            p.println();
                            if (contentOrigin.trim().length() == 0) {
                                p.println("Blank input occured, please enter a content origin.");
                                p.println();
                            }
                        } while (contentOrigin.trim().length() == 0);

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
                                if (viewerStatus.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a viewer status.");
                                    p.println();
                                }
                            } while (viewerStatus.trim().length() == 0);

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

                                if (progType.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a programme type.");
                                    p.println();
                                }
                            } while (progType.trim().length() == 0);

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
                            if (notes.trim().length() == 0) {
                                p.println("Blank input occured, please enter a notes.");
                                p.println();
                            }
                        } while (notes.trim().length() == 0);

                        prgList.add(new TVProgramme(programmeCode, programmeTitle, desc, contentOrigin, crtDate, "Active", viewerStatus, progType, notes));
                        p.println("You have successfully created a new TV Programme which is '" + programmeCode + "'.");
                        logList.addLast(new LogFile(lgTime, username, "has created a new TV Programme which is '" + programmeCode + "'."));
                        p.println();
                        break;

                    case 2:
                        p.println();
                        input.nextLine();

                        do {
                            p.println("2. Edit a TV Programme: ");
                            p.print("Please enter a programme code that you want to edit: ");
                            progCode4 = input.nextLine();

                            if (progCode4.trim().length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }

                        } while (progCode4.trim().length() == 0);

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

                                                if (programmeTitle2.trim().length() == 0) {
                                                    p.println("Blank input occured, please enter a programme title.");
                                                    p.println();
                                                }
                                            } while (programmeTitle2.trim().length() == 0);

                                            prgList.get(i).setProgTitle(programmeTitle2);
                                            p.println("You have changed the programme title for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(lgTime, username, "has changed a programme title for programme '" + progCode4 + "'."));
                                            p.println();
                                            break;

                                        case 2:
                                            input.nextLine();
                                            do {
                                                p.print("Please type some description that you would like to change for: ");

                                                desc2 = input.nextLine();
                                                p.println();

                                                if (desc2.trim().length() == 0) {
                                                    p.println("Blank input occured, please enter a description.");
                                                    p.println();
                                                }
                                            } while (desc2.trim().length() == 0);

                                            prgList.get(i).setDesc(desc2);
                                            p.println("You have changed the description for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(lgTime, username, "has changed a description for programme '" + progCode4 + "'."));
                                            p.println();
                                            break;

                                        case 3:
                                            input.nextLine();
                                            do {
                                                p.print("Please enter a content origin that you would like to change for: ");
                                                contentOrigin2 = input.nextLine();
                                                p.println();

                                                if (contentOrigin2.trim().length() == 0) {
                                                    p.println("Blank input occured, please enter a content origin.");
                                                    p.println();
                                                }
                                            } while (contentOrigin2.trim().length() == 0);

                                            prgList.get(i).setContentOrigin(contentOrigin2);
                                            p.println("You have changed the content origin for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(lgTime, username, "has changed a content origin for programme '" + progCode4 + "'."));
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

                                                    if (viewerStatus2.trim().length() == 0) {
                                                        p.println("Blank input occured, please enter a viewer status.");
                                                        p.println();
                                                    }
                                                } while (viewerStatus2.trim().length() == 0);

                                                if ((viewerStatus2.equalsIgnoreCase("U")) || (viewerStatus2.equalsIgnoreCase("PG13")) || (viewerStatus2.equalsIgnoreCase("18"))) {
                                                    p.println("You have changed the viewer status for programme '" + progCode4 + "'!");
                                                    logList.addLast(new LogFile(lgTime, username, "has changed a viewer status for programme '" + progCode4 + "'."));
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

                                                    if (progType2.trim().length() == 0) {
                                                        p.println("Blank input occured, please enter a programme type.");
                                                        p.println();
                                                    }


                                                } while (progType2.trim().length() == 0);

                                                if ((progType2.equalsIgnoreCase("Movie")) || (progType2.equalsIgnoreCase("Series")) ||
                                                        (progType2.equalsIgnoreCase("Comedy")) || (progType2.equalsIgnoreCase("Concert"))) {

                                                    p.println("You have changed the type of programme '" + progCode4 + "'!");
                                                    logList.addLast(new LogFile(lgTime, username, "has changed a programme type for programme '" + progCode4 + "'."));
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

                                                if (notes2.trim().length() == 0) {
                                                    p.println("Blank input occured, please enter a notes.");
                                                    p.println();
                                                }
                                            } while (notes2.trim().length() == 0);

                                            prgList.get(i).setNotes(notes2);
                                            p.println("You have changed the notes for programme '" + progCode4 + "'!");
                                            logList.addLast(new LogFile(lgTime, username, "has changed the notes for programme '" + progCode4 + "'."));
                                            p.println();
                                            break;

                                        case 7:
                                            check2 = false;
                                            break;

                                        default:
                                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
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
                            if (progCode5.trim().length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }

                        } while (progCode5.trim().length() == 0);

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
                                logList.addLast(new LogFile(lgTime, username, "has terminate a TV Programme '" + progCode5 + "'."));
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

                            if (progCode6.trim().length() == 0) {
                                p.println("Blank input occured, please enter a programme code.");
                                p.println();
                            }
                        } while (progCode6.trim().length() == 0);

                        for (i = 0; i < prgList.size(); i++) {
                            if (progCode6.equalsIgnoreCase(prgList.get(i).getProgCode())) {
                                prgList.get(i).printList();
                                p.println();
                                logList.addLast(new LogFile(lgTime, username, "has displayed the details for programme '" + progCode6 + "'."));
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
                        input.nextLine();
                        showMenu();
                        break;

                    default:
                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
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

    /*--------------------------------------------------------------------------SECTION NINE - MANAGE USERS----------------------------------------------------------------------------------------*/
    @SuppressWarnings("unused")
    public void manageUsers() {
        String choice_f9, type_f9, un_f9, oldpw_f9, newpw_1_f9, newpw_2_f9;
        boolean val1_f9 = false, val2_f9 = false, val3_f9 = false;
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Manage User-Add/Change Password/Terminate' function.");
        logList.addLast(log);

        p.println("\nManage User-Add/Change Password/Terminate");
        p.println("--------------------------------------");

        do {
            p.print("Please enter the function you would like to proceed(Add/Change Password/Terminate): ");
            choice_f9 = input.nextLine();
            choice_f9 = choice_f9.toLowerCase();

            if ((choice_f9.trim().length() > 0) && ((choice_f9.equalsIgnoreCase("add") || choice_f9.equalsIgnoreCase("changepassword")) || (choice_f9.equalsIgnoreCase("terminate")))) {
                val1_f9 = true;
            } else {
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
            }
        } while (val1_f9 == false);

        //Add a new user
        switch (choice_f9) {
            case "add":
                if (userList.get(u) instanceof Administrators) {
                    log = new LogFile(lgTime, username, "has chosen to add a new user.");
                    logList.addLast(log);

                    do {
                        p.print("Enter the type of new user(Case-insensitive): \n");
                        p.print("Enter ADMIN for administrator type user.\n");
                        p.print("Enter STAFF for staff type user.\n");
                        type_f9 = input.nextLine();
                        p.println();
                        if (type_f9.trim().length() == 0) {
                            p.println("Blank input occured, please enter a type of user.");
                            p.println();
                        }

                    } while (type_f9.trim().length() == 0);

                    type_f9 = type_f9.toLowerCase();

                    if ((type_f9.equals("admin")) || (type_f9.equals("staff"))) {
                        do {
                            p.print("Enter new username: ");
                            un_f9 = input.nextLine();
                            p.println();
                            if (un_f9.trim().length() == 0) {
                                p.println("Blank input occured, please enter an username.");
                                p.println();
                            }

                        } while (un_f9.trim().length() == 0);

                        for (i = 0; i < userList.size(); i++) {
                            if (un_f9.equalsIgnoreCase(userList.get(i).getUserID())) {
                                p.println("Username already exist!");
                                log = new LogFile(lgTime, username, "has not added any new user[USERNAME ALREADY EXIST].");
                                logList.addLast(log);
                                break;
                            } else {

                                do {
                                    p.print("Enter password: ");
                                    newpw_1_f9 = input.nextLine();
                                    p.println();
                                    if (newpw_1_f9.trim().length() == 0) {
                                        p.println("Blank input occured, please enter a password.");
                                        p.println();
                                    }
                                } while (newpw_1_f9.trim().length() == 0);

                                do {
                                    p.print("Re-enter password: ");
                                    newpw_2_f9 = input.nextLine();
                                    p.println();
                                    if (newpw_2_f9.trim().length() == 0) {
                                        p.println("Blank input occured, please enter a confirmation password.");
                                        p.println();
                                    }
                                } while (newpw_2_f9.trim().length() == 0);

                                if (newpw_1_f9.equals(newpw_2_f9)) {
                                    if (type_f9.equals("admin")) {
                                        userList.add(new Administrators(un_f9, newpw_2_f9));
                                        p.println("\nNew user '" + un_f9 + "' has been added successfully!");
                                        log = new LogFile(lgTime, username, "has added a new user '" + un_f9 + "'.");
                                        logList.addLast(log);
                                    } else {
                                        userList.add(new FrontdeskStaffs(un_f9, newpw_2_f9));
                                        p.println("\nNew user '" + un_f9 + "' has been added successfully!");
                                        log = new LogFile(lgTime, username, "has added a new user '" + un_f9 + "'.");
                                        logList.addLast(log);
                                    }
                                    break;
                                } else {
                                    p.println("\nRe-entered password mis-matched!");
                                    log = new LogFile(lgTime, username, "has not added new user[RE-ENTERED PASSWORD MIS-MATCHED].");
                                    logList.addLast(log);
                                    break;
                                }
                            }
                        }
                    } else {
                        p.println("\nThe user type is not available.");
                        log = new LogFile(lgTime, username, "has not added any new user[USER TYPE NOT AVAILABLE].");
                        logList.addLast(log);
                    }
                    break;
                } else {
                    p.println("\nOnly Admin has the accessibility to add a user.");
                    log = new LogFile(lgTime, username, "has not added any user[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                    logList.addLast(log);
                }
                //Change a user's password
            case "changepassword":
                log = new LogFile(lgTime, username, "has chosen to change user '" + username + "' 's password");
                logList.addLast(log);

                do {
                    do {
                        p.print("\nEnter the existing password: ");
                        oldpw_f9 = input.nextLine();
                        p.println();

                        if (oldpw_f9.trim().length() == 0) {
                            p.println("Blank input occured, please enter the existing password.");
                            p.println();
                        }
                    } while (oldpw_f9.trim().length() == 0);

                    if (oldpw_f9.trim().length() > 0) {
                        if (oldpw_f9.equals(userList.get(u).getPassword())) {
                            do {
                                p.print("Enter a new password: ");
                                newpw_1_f9 = input.nextLine();
                                p.println();
                                if (newpw_1_f9.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a type of user.");
                                    p.println();
                                }
                            } while (newpw_1_f9.trim().length() == 0);

                            do {
                                p.print("Re-enter password: ");
                                newpw_2_f9 = input.nextLine();
                                p.println();
                                if (newpw_2_f9.trim().length() == 0) {
                                    p.println("Blank input occured, please enter a type of user.");
                                    p.println();
                                }

                            } while (newpw_2_f9.trim().length() == 0);

                            if (newpw_1_f9.equals(newpw_2_f9)) { /*change password*/
                                userList.get(u).changePassword(newpw_2_f9);

                                p.println("\nPassword changed successfully!");

                                log = new LogFile(lgTime, username, "has changed password.");
                                logList.addLast(log);
                                val3_f9 = true;
                            } else {
                                p.println("\nRe-entered password mis-matched!");

                                log = new LogFile(lgTime, username, "has not changed password[RE-ENTERED PASSWORD MIS-MATCHED].");
                                logList.addLast(log);
                                break;
                            }
                        } else {
                            p.println("\nPassword mismatch!");

                            log = new LogFile(lgTime, username, "has not changed password[PASSWORD MIS-MATCHED].");
                            logList.addLast(log);
                            break;
                        }
                    } else {
                        p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                    }
                } while (val3_f9 == false);
                break;

            case "terminate": //Terminate a user
                if (userList.get(u) instanceof Administrators) {
                    log = new LogFile(lgTime, username, "has chosen to Terminate a user.");
                    logList.addLast(log);

                    do {
                        do {
                            p.print("Enter the user's name that you would like to Terminate: ");
                            un_f9 = input.nextLine();

                            if (un_f9.trim().length() == 0) {
                                p.println("Blank input occured, please enter a user's name.");
                                p.println();
                            }

                        } while (un_f9.trim().length() == 0);
                        if (un_f9.trim().length() > 0) {
                            val2_f9 = true;
                        } else {
                            p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                        }
                    } while (val2_f9 == false);

                    if (un_f9.equalsIgnoreCase(username)) {
                        p.println("\nUser cannot Terminate itself!");
                        log = new LogFile(lgTime, username, "has not Terminated user '" + un_f9 + "' details[USER CANNOT Terminate ITSELF].");
                        logList.addLast(log);
                        break;
                    }

                    for (i = 0; i < userList.size(); i++) {
                        if (un_f9.equalsIgnoreCase(userList.get(i).getUserID())) {
                            userList.remove(i);
                            p.println("\nUser '" + un_f9 + "' has been Terminated.");

                            log = new LogFile(lgTime, username, "has Terminate user '" + un_f9 + "'.");
                            logList.addLast(log);
                            break;
                        } else {
                            if (i == (userList.size() - 1)) {
                                p.println("\nUser '" + un_f9 + "' does not exist!");
                                log = new LogFile(lgTime, username, "has not Terminated user '" + un_f9 + "' details[USER DOES NOT EXIST].");
                                logList.addLast(log);
                            }
                        }
                    }
                } else {
                    p.println("\nOnly Admin has the accessibility to Terminate a user.");
                    log = new LogFile(lgTime, username, "has not Terminated any user[USER TYPE DO NOT HAVE ACCESSIBILITY].");
                    logList.addLast(log);
                }
                break;
            default:
                p.println("INVALID SELECTION OR EMPTY INPUT, PLEASE RE-ENTER.\n");
                break;
        } //end switch
    } //end manage users
    /*--------------------------------------------------------------------------SECTION TEN - GENERATE REPORT----------------------------------------------------------------------------------------*/

    public void reportGen() {
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Generate Report' function.");
        logList.addLast(log);

        if ((userList.get(u)) instanceof FrontdeskStaffs) {
            p.println("\nOnly Admin or Manager have the accessibility to generate a report.");
            log = new LogFile(lgTime, username, "has not generated a report[USER TYPE DO NOT HAVE ACCESSIBILITY].");
            logList.addLast(log);
        } else {
            p.println("\nGenerate Report");
            p.println("---------------");

            int a = 0, b = 0, c = 0, z = 0;
            String d = "", e = "", f = "";
            for (i = 0; i < clientList.size(); i++) {
                clientList.get(i).printClient();
                f = clientList.get(i).getClientID();
                //Display services

                for (z = 0; z < servList.size(); z++) {
                    if (z == 0)
                        p.println("\nService details: ");

                    if (f.equalsIgnoreCase(servList.get(z).getClientID())) {
                        servList.get(z).printServ();
                        p.println();

                        //Display subscriptions
                        for (a = 0; a < subsList.size(); a++) {
                            if ((servList.get(z).getSmartCardNo()).equalsIgnoreCase(subsList.get(a).getSmartCardNo())) {
                                subsList.get(a).printSubs();
                                p.println();
                                d = subsList.get(a).getPkgCode();

                                //Display packages

                                for (b = 0; b < pkgList.size(); b++) {
                                    if (b == 0) {
                                        p.println("Subscribed Package(s):");
                                    }
                                    if (d.equalsIgnoreCase(pkgList.get(b).getPkgCode())) {
                                        pkgList.get(b).printPkg();
                                        p.println();
                                        e = pkgList.get(b).getPkgCode();

                                        //Display packaging
                                        p.println("This package consist of ");
                                        for (c = 0; c < pckgingList.size(); c++) {
                                            if (e.equalsIgnoreCase(pckgingList.get(c).getPkgCode())) {
                                                pckgingList.get(c).printPckging();
                                                p.println("-----------------------------------");
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                p.println("--------------------------------------------------------------------");

                log = new LogFile(lgTime, username, "has generated details of client '" + clientList.get(i).getClientID() + "'.");
                logList.addLast(log);

                log = new LogFile(lgTime, username, "has generated a report.");
                logList.addLast(log);

            }
        }
    }
	/*--------------------------------------------------------------------------SECTION ELEVEN - LOGOUT----------------------------------------------------------------------------------------*/

    public void logout() {
        Date logTime = new Date();
        String lgTime = "[" + DateFormat.getInstance().format(logTime) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the 'Log Off' function.");
        logList.addLast(log);

        p.println("\nYou are successfully logged off from the system.");
        p.println("\nLOG FILE\n------------\n");

        login = false;
        log = new LogFile(lgTime, username, "has logged off from the system.");
        logList.addLast(log);

        for (i = 0; i < logList.size(); i++) {
            logList.get(i).showLog();
        }
        p.println();
        p.println("You've successfully logged off from the system, please login to continue or exit the system.");
        //file handling for log.txt
        try {
            PrintWriter pw_log2 = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)));

            for (i = 0; i < logList.size(); i++) {
                if (logList.get(i) != null) {
                    pw_log2.print(lgTime + "\t");
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
        //saving added data pckging_data.dat
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

        //saving added data prg_data.dat
        try {
            FileOutputStream prg_fostream = new FileOutputStream("prg_data.dat");
            ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream);
            for (i = 0; i < prgList.size(); i++) {
                if (prgList.get(i) != null) {
                    prg_oostream.writeObject(prgList);
                }
            }
            prg_oostream.flush();
            prg_oostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //saving added data user_data.dat
        try {
            FileOutputStream user_fostream = new FileOutputStream("user_data.dat");
            ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream);
            for (i = 0; i < userList.size(); i++) {
                if (userList.get(i) != null) {
                    user_oostream.writeObject(userList);
                }
            }
            user_oostream.flush();
            user_oostream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //end of file handling
        //logList.clear();
    } //end logout

    /*--------------------------------------------------------------------------SECTION TWELVE - DISPLAY LIST----------------------------------------------------------------------------------------*/
    public void printLists() {
        String lgTime = "[" + DateFormat.getInstance().format(new Date()) + "]\t";
        log = new LogFile(lgTime, username, "has chosen the hidden function to print out all data in LinkedList.");
        logList.addLast(log);

        if (userList.get(u) instanceof Administrators) {
            p.println("\nData List");
            p.println("----------");

            //Users
            p.println("USERS LIST\n");

            for (i = 0; i < userList.size(); i++) {
                userList.get(i).printUser();
            }

            log = new LogFile(lgTime, username, "has printed out data in 'userList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Clients
            p.println("CLIENTS LIST\n");
            for (i = 0; i < clientList.size(); i++) {
                clientList.get(i).printClient();
            }

            log = new LogFile(lgTime, username, "has printed out data in 'clientList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Services
            p.println("SERVICE LIST\n");

            for (i = 0; i < servList.size(); i++) {
                servList.get(i).printServ();
            }

            log = new LogFile(lgTime, username, "has printed out data in 'servList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Subscriptions
            p.println("SUBSCRIPTION LIST\n");

            for (i = 0; i < subsList.size(); i++) {
                subsList.get(i).printSecSubs();
            }
            log = new LogFile(lgTime, username, "has printed out data in 'subsList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Package
            p.println("PACKAGE LIST\n");

            for (i = 0; i < pkgList.size(); i++) {
                pkgList.get(i).printPkg();
            }
            log = new LogFile(lgTime, username, "has printed out data in 'pkgList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Packaging
            p.println("PACKAGING LIST\n");

            for (i = 0; i < pckgingList.size(); i++) {
                pckgingList.get(i).printSecPckging();
            }
            log = new LogFile(lgTime, username, "has printed out data in 'pckgingList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");

            //Programme
            p.println("PACKAGE LIST\n");

            for (i = 0; i < prgList.size(); i++) {
                prgList.get(i).printList();
            }
            log = new LogFile(lgTime, username, "has printed out data in 'prgList'.");
            logList.addLast(log);

            p.println("--------------------------------------------------------------------");


            //Logs
            p.println("LOGS LIST\n");

            for (i = 0; i < logList.size(); i++) {
                logList.get(i).showLog();
            }
            log = new LogFile(lgTime, username, "has printed out data in 'logList'.");
            logList.addLast(log);

            log = new LogFile(lgTime, username, "has printed out all data in LinkedList.");
            logList.addLast(log);
        } else {
            p.println("\nOnly Admin has the accessibility to use this hidden function.");
            log = new LogFile(lgTime, username, "has not printed out all data in LinkedList[USER TYPE DO NOT HAVE ACCESSIBILITY].");
            logList.addLast(log);
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
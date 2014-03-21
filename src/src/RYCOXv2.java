import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("serial")
class RYCOXv2 extends JFrame {

    public RYCOXv2() {
        super("RYCOX System - UniTV Customer Care and Billing Management");
        setVisible(true);
        userlogin();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit RYCOX CMM?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    static LinkedList<Users> userList;
    static LinkedList<ClientAccount> clientList;
    static LinkedList<Service> servList;
    static LinkedList<Subscription> subsList;
    static LinkedList<TVPackage> pkgList;
    static LinkedList<Packaging> pckgingList;
    static LinkedList<TVProgramme> prgList;
    static LinkedList<LogFile> logList;
    static LogFile log;
    static int i, u;
    static int currentUser;
    static String user;//index for current user
    Container mainFrame = getContentPane();
    /*
     * USER LOGIN FRAME
     */
    private JPanel ulpanelleft, ulpanelright;
    private JPanel unpanel, pwpanel;
    private JLabel ulwelcomeMsg, pw_msg, un_msg;
    private JLabel ullogo, uldisp;
    private JTextField un_input;
    private JPasswordField pw_input;
    private JButton ulsubmit, ulclear;
    private FlowLayout flay = new FlowLayout();
    private Color bgcolor = new Color(23, 28, 30);
    private Color fieldcolor = new Color(50, 60, 64);
    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 14);


    /*
     * MAIN MENU PAGE
     */
   /*
    * ------------------------------------------------------------------------
    * USER LOGIN INTERFACE
    * ---------------------------------------------------------------
    */
    private void userlogin() {
        setTitle("RYCOX System - Customer Management Module Log in");
        setSize(600, 500);
        getContentPane().setBackground(bgcolor);
        ulpanelleft = new JPanel();
        ulpanelleft.setLayout(null);
        ulpanelleft.setBackground(Color.WHITE);
        ullogo = new JLabel();
        ullogo.setIcon(new ImageIcon(getClass().getResource("/resources/rycox.png")));
        ulpanelleft.add(ullogo).setBounds(0, 0, 200, 500);

        ulpanelleft.setBounds(0, 0, 200, 500);
        ulpanelright = new JPanel();
        ulpanelright.setBounds(200, 0, 400, 500);

        unpanel = new JPanel();
        unpanel.setBackground(bgcolor);
        pwpanel = new JPanel();
        pwpanel.setBackground(bgcolor);
        ulwelcomeMsg = new JLabel("Please log in to continue.");
        ulwelcomeMsg.setFont(defont);
        ulwelcomeMsg.setForeground(Color.WHITE);
        pw_msg = new JLabel("Password: ");
        pw_msg.setFont(defont);
        pw_msg.setForeground(Color.WHITE);
        un_msg = new JLabel("Username: ");
        un_msg.setFont(defont);
        un_msg.setForeground(Color.WHITE);
        un_input = new JTextField(15);
        un_input.setBackground(fieldcolor);
        un_input.setForeground(Color.WHITE);
        pw_input = new JPasswordField(15);
        pw_input.setBackground(fieldcolor);
        pw_input.setForeground(Color.WHITE);
        ulsubmit = new JButton("Log in");
        ulsubmit.setFont(defont);
        ulclear = new JButton("Close");
        ulclear.setFont(defont);

        setLayout(null);
        add(ulpanelleft);
        add(ulpanelright);

        ulpanelright.setLayout(null);
        ulpanelright.setBackground(bgcolor);
        ulpanelright.add(ulwelcomeMsg);
        uldisp = new JLabel();
        uldisp.setIcon(new ImageIcon(getClass().getResource("/resources/CMM-LOGIN.png")));
        ulpanelright.add(uldisp).setBounds(50, 50, 300, 100);
        ulwelcomeMsg.setBounds(50, 150, 300, 50);
        ulpanelright.add(unpanel);
        ulpanelright.add(pwpanel);
        unpanel.setLayout(flay);
        unpanel.add(un_msg);
        unpanel.add(un_input);
        unpanel.setBounds(50, 200, 300, 50);
        pwpanel.setLayout(flay);
        pwpanel.add(pw_msg);
        pwpanel.add(pw_input);
        pwpanel.setBounds(50, 250, 300, 50);
        ulpanelright.add(ulsubmit);
        ulsubmit.setBounds(70, 300, 100, 25);

        UserLoginhandler L = new UserLoginhandler();
        ulsubmit.addActionListener(L);
        ulpanelright.add(ulclear).setBounds(220, 300, 100, 25);
        ulclear.addActionListener(L);
        pw_input.addKeyListener(L);

    }

    /*
     * -----------------------------------------------------------------------------
     * MAIN METHOD
     * -------------------------------------------------------------------
     */
    public static void main(String[] rycox) {
        new RYCOXv2();
        initialise();
    }

    @SuppressWarnings("unchecked")
    static void initialise() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
        }

        userList = new LinkedList<>();
        clientList = new LinkedList<>();
        servList = new LinkedList<>();
        subsList = new LinkedList<>();
        pkgList = new LinkedList<>();
        pckgingList = new LinkedList<>();
        prgList = new LinkedList<>();
        logList = new LinkedList<>();
        log = new LogFile("", "");

        File client_file = new File("cl_data.rycox");
        boolean exist_cl_data = client_file.exists();
        if (exist_cl_data == false) {    //if client's file do not exist
            //cl_data.rycox file
            try {
                FileOutputStream client_fostream = new FileOutputStream("cl_data.rycox");
                try (ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream)) {
                    clientList.add(new IndividualClient("Izhar Husin", 39, "631220-05-1243", "9, Trafalgar Road", "I000001", "ACTIVE", "Izhar@gmail.com"));
                    clientList.add(new IndividualClient("Rimi Azizi", 21, "901010-05-2828", "10, Jalan Taylor's", "I000002", "ACTIVE", "Rimi@gmail.com"));
                    clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "INACTIVE", "doe@gov.my"));
                    clientList.add(new NGOClient("WWE KL Branch", "56, Taylor's Street", "N000001", "ACTIVE", "WWE@gmail.com"));
                    clientList.add(new PrvClient("Chanel", "88, Sohai's Street", "P000001", "ACTIVE", "chanel@gmail.com"));
                    clientList.add(new PrvClient("Loreal", "80, TROLLOLOL's Street", "P000002", "ACTIVE", "loreal@gmail.com"));
                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) != null) {
                            client_oostream.writeObject(clientList);
                        }
                    }
                    client_oostream.flush();
                }

            } catch (Exception e) {
            }

            //serv_data.rycox files
            try {
                FileOutputStream serv_fostream = new FileOutputStream("serv_data.rycox");
                try (ObjectOutputStream serv_oostream = new ObjectOutputStream(serv_fostream)) {
                    servList.add(new Service("S000001", "I000001", "D999999", "5, Jalan Sungai Beranang"));
                    servList.add(new Service("S000002", "I000001", "D999998", "Lot 1-3 Starhill"));
                    servList.add(new Service("S000007", "I000002", "D999993", "Jalan Ara 2"));
                    servList.add(new Service("S000008", "I000002", "D999992", "Jalan Tebrau"));
                    servList.add(new Service("S000003", "N000001", "D999997", "Lot 3-10 Jalan Taylor"));
                    servList.add(new Service("S000004", "P000001", "D999996", "32, Jalan Kota Kemuning"));
                    servList.add(new Service("S000005", "G000001", "D999995", "30, Jalan Kota Baru"));
                    servList.add(new Service("S000006", "P000002", "D999994", "99, Jalan Kota Tua"));
                    for (i = 0; i < servList.size(); i++) {
                        if (servList.get(i) != null) {
                            serv_oostream.writeObject(servList);
                        }
                    }
                    serv_oostream.flush();
                }

            } catch (Exception e) {
            }

            //subsc_data.rycox
            try {
                FileOutputStream subsc_fostream = new FileOutputStream("subsc_data.rycox");
                try (ObjectOutputStream subsc_oostream = new ObjectOutputStream(subsc_fostream)) {
                    subsList.add(new Subscription("S000001", 1, "P01"));
                    subsList.add(new Subscription("S000001", 1, "P02"));
                    subsList.add(new Subscription("S000002", 2, "P02"));
                    subsList.add(new Subscription("S000002", 2, "P10"));
                    subsList.add(new Subscription("S000003", 1, "P03"));
                    subsList.add(new Subscription("S000003", 1, "P04"));
                    subsList.add(new Subscription("S000003", 1, "P05"));
                    subsList.add(new Subscription("S000004", 1, "P05"));
                    subsList.add(new Subscription("S000004", 1, "P07"));
                    subsList.add(new Subscription("S000005", 1, "P05"));
                    subsList.add(new Subscription("S000005", 1, "P06"));
                    subsList.add(new Subscription("S000005", 1, "P07"));
                    subsList.add(new Subscription("S000005", 1, "P08"));
                    subsList.add(new Subscription("S000007", 1, "P05"));
                    subsList.add(new Subscription("S000007", 1, "P07"));
                    subsList.add(new Subscription("S000007", 1, "P01"));
                    subsList.add(new Subscription("S000008", 2, "P02"));
                    subsList.add(new Subscription("S000008", 2, "P03"));
                    for (i = 0; i < subsList.size(); i++) {

                        if (subsList.get(i) != null) {
                            subsc_oostream.writeObject(subsList);
                        }
                    }
                    subsc_oostream.flush();
                }

            } catch (Exception e) {
            }

            //pkg_data.rycox files
            try {
                FileOutputStream pkg_fostream = new FileOutputStream("pkg_data.rycox");
                try (ObjectOutputStream pkg_oostream = new ObjectOutputStream(pkg_fostream)) {
                    pkgList.add(new TVPackage("P01", "Variety", "5/14/2012", "N/A", 70.00, "Monthly", "ACTIVE"));
                    pkgList.add(new TVPackage("P02", "Anime Fun", "4/18/2012", "N/A", 50.00, "Monthly", "ACTIVE"));
                    pkgList.add(new TVPackage("P03", "MTV", "4/18/2012", "N/A", 45.00, "Monthly", "ACTIVE"));
                    pkgList.add(new TVPackage("P04", "Golden Music", "4/18/2012", "N/A", 40.00, "Monthly", "ACTIVE"));
                    pkgList.add(new TVPackage("P05", "Fox Movie", "12/7/2011", "N/A", 350.00, "Yearly", "ACTIVE"));
                    pkgList.add(new TVPackage("P06", "Celebrities Movie", "6/8/2011", "N/A", 250.00, "Yearly", "ACTIVE"));
                    pkgList.add(new TVPackage("P07", "HBO", "21/9/2011", "N/A", 210.00, "Yearly", "ACTIVE"));
                    pkgList.add(new TVPackage("P08", "Double Star", "4/11/2012", "N/A", 100.00, "Yearly", "ACTIVE"));
                    pkgList.add(new TVPackage("P09", "ZhiZun", "1/12/2012", "N/A", 90.00, "Quarterly", "ACTIVE"));
                    pkgList.add(new TVPackage("P10", "Series", "2/3/2012", "N/A", 150.00, "Quarterly", "ACTIVE"));
                    for (i = 0; i < pkgList.size(); i++) {
                        if (pkgList.get(i) != null) {
                            pkg_oostream.writeObject(pkgList);
                        }
                    }
                    pkg_oostream.flush();
                }
            } catch (Exception e) {
            }

            //pckging_data.rycox files
            try {
                FileOutputStream pckging_fostream = new FileOutputStream("pckging_data.rycox");
                try (ObjectOutputStream pckging_oostream = new ObjectOutputStream(pckging_fostream)) {
                    pckgingList.add(new Packaging("P01", "F001"));
                    pckgingList.add(new Packaging("P01", "F002"));
                    pckgingList.add(new Packaging("P01", "F007"));
                    pckgingList.add(new Packaging("P01", "F011"));
                    pckgingList.add(new Packaging("P02", "F001"));
                    pckgingList.add(new Packaging("P02", "F002"));
                    pckgingList.add(new Packaging("P02", "F003"));
                    pckgingList.add(new Packaging("P02", "F004"));
                    pckgingList.add(new Packaging("P02", "F005"));
                    pckgingList.add(new Packaging("P03", "F017"));
                    pckgingList.add(new Packaging("P03", "F018"));
                    pckgingList.add(new Packaging("P04", "F016"));
                    pckgingList.add(new Packaging("P04", "F018"));
                    pckgingList.add(new Packaging("P05", "F011"));
                    pckgingList.add(new Packaging("P05", "F012"));
                    pckgingList.add(new Packaging("P05", "F013"));
                    pckgingList.add(new Packaging("P06", "F014"));
                    pckgingList.add(new Packaging("P06", "F015"));
                    pckgingList.add(new Packaging("P07", "F012"));
                    pckgingList.add(new Packaging("P07", "F015"));
                    pckgingList.add(new Packaging("P08", "F010"));
                    pckgingList.add(new Packaging("P08", "F009"));
                    pckgingList.add(new Packaging("P09", "F006"));
                    pckgingList.add(new Packaging("P10", "F007"));
                    pckgingList.add(new Packaging("P10", "F008"));
                    for (i = 0; i < pckgingList.size(); i++) {
                        if (pckgingList.get(i) != null) {
                            pckging_oostream.writeObject(pckgingList);
                        }
                    }
                    pckging_oostream.flush();
                }
            } catch (Exception e) {
            }

            //prg_data.rycox files
            try {
                FileOutputStream prg_fostream = new FileOutputStream("prg_data.rycox");
                try (ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream)) {
                    prgList.add(new TVProgramme("F1", "My Boss My Hero", "Japanese Comedy Drama series about Yakuza members.", "Japan", "27/11/2002", "N/A", "ACTIVE", "18SG", "Comedy", "5stars"));
                    prgList.add(new TVProgramme("F2", "Naruto", "Story about a ninja world that a child want to be a leader of the ninja.", "Japan", "27/11/1999", "N/A", "ACTIVE", "U", "Comedy", "4stars"));
                    prgList.add(new TVProgramme("F3", "One Piece", "The adventures of Monkey D. Luffy, a 17-year-old boy who gains elastic abilities.", "Japan", "4/8/1997", "N/A", "ACTIVE", "U", "Comedy", "3stars"));
                    prgList.add(new TVProgramme("F4", "Pokemon", "Ash Ketchum and his friends (human and Pokemon) that he makes on the way.", "Japan", "1/4/1997", "N/A", "ACTIVE", "U", "Comedy", "2stars"));
                    prgList.add(new TVProgramme("F5", "Tom & Jerry", "A Cat and Mouse cannot be a friend forever.", "Japan", "3/10/96", "N/A", "ACTIVE", "U", "Comedy", "1star"));
                    prgList.add(new TVProgramme("F6", "City Hunter", "Korean Drama from the comic.", "Korea", "27/06/2011", "N/A", "ACTIVE", "PG13", "Series", "1star"));
                    prgList.add(new TVProgramme("F7", "Gloves Come Off", "A Story about a group of boxer.", "Hong Kong", "19/4/2012", "N/A", "ACTIVE", "18SG", "Series", "5stars"));
                    prgList.add(new TVProgramme("F8", "Sergeant Tabloid", "A Drama about work and love for a female cop.", "Hong Kong", "2/4/2012", "N/A", "ACTIVE", "PG13", "Series", "5stars"));
                    prgList.add(new TVProgramme("F9", "A Song To Remember", "A Romance story between a musician and a pretty protg.", "Singapore", "1/4/2012", "N/A", "ACTIVE", "PG13", "Series", "5stars"));
                    prgList.add(new TVProgramme("F10", "Office Girls", "A Shopping mall heir discovers a fine mentoe in his female co-worker.", "Taiwan", "27/3/2012", "N/A", "ACTIVE", "PG13", "Series", "5stars"));
                    prgList.add(new TVProgramme("F11", "I Am Number 4", "An extraordinary teen masks his true idntity.", "US", "2/5/2002", "N/A", "ACTIVE", "PG13", "Movie", "3stars"));
                    prgList.add(new TVProgramme("F12", "TRON", "A hacker is literally abducted into the world of computer.", "US", "4/6/2011", "N/A", "ACTIVE", "PG13", "Movie", "2stars"));
                    prgList.add(new TVProgramme("F13", "Green Zone", "At the start of war in Iraq.", "US", "15/3/2009", "N/A", "ACTIVE", "18SG", "Movie", "1star"));
                    prgList.add(new TVProgramme("F14", "Kung Fu Mahjong", "Suave mahjong pro ken finds himself playing for the highest stakes ever.", "Hong Kong", "21/5/2009", "N/A", "ACTIVE", "PG13", "Movie", "4stars"));
                    prgList.add(new TVProgramme("F15", "Treasure Inn", "Law Enforcers who are pulled into a dangerous and seductive adventure.", "China", "11/9/2011", "N/A", "ACTIVE", "18SG", "Movie", "5stars"));
                    prgList.add(new TVProgramme("F16", "MayDay", "MayDay Concert.", "China", "11/9/2011", "N/A", "ACTIVE", "U", "Concert", "3stars"));
                    prgList.add(new TVProgramme("F17", "Simple Plan", "Simple Plan Concert.", "China", "11/9/2011", "N/A", "ACTIVE", "U", "Concert", "1star"));
                    prgList.add(new TVProgramme("F18", "BigBang", "BigBang Concert.", "China", "11/9/2011", "N/A", "ACTIVE", "U", "Concert", "4stars"));
                    for (i = 0; i < prgList.size(); i++) {
                        if (prgList.get(i) != null) {
                            prg_oostream.writeObject(prgList);
                        }
                    }
                    prg_oostream.flush();
                }
            } catch (Exception e) {
            }

            //user_data.rycox files
            try {
                FileOutputStream user_fostream = new FileOutputStream("user_data.rycox");
                try (ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream)) {
                    userList.add(new Administrators("admin", "nimda"));
                    userList.add(new FrontdeskStaffs("staff", "123abc"));
                    for (i = 0; i < userList.size(); i++) {
                        if (userList.get(i) != null) {
                            user_oostream.writeObject(userList);
                        }
                    }
                    user_oostream.flush();
                }
            } catch (Exception e) {
            }

        } else {        //reading files
            //reading cl_data.rycox
            try {
                FileInputStream client_fistream = new FileInputStream("cl_data.rycox");            //read from specified file
                try (ObjectInputStream client_oistream = new ObjectInputStream(client_fistream)) {
                    clientList.add(new PrvClient("", "", "", "", ""));
                    for (i = 0; i < clientList.size(); i++) {
                        if (clientList.get(i) != null) {
                            clientList = (LinkedList<ClientAccount>) client_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading serv_data.rycox
            try {
                FileInputStream serv_fistream = new FileInputStream("serv_data.rycox");            //read from specified file
                try (ObjectInputStream serv_oistream = new ObjectInputStream(serv_fistream)) {
                    servList.add(new Service("", "", "", ""));
                    for (i = 0; i < servList.size(); i++) {
                        if (servList.get(i) != null) {
                            servList = (LinkedList<Service>) serv_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading subsc_data.rycox
            try {
                FileInputStream subsc_fistream = new FileInputStream("subsc_data.rycox");            //read from specified file
                try (ObjectInputStream subsc_oistream = new ObjectInputStream(subsc_fistream)) {
                    subsList.add(new Subscription("", 0, ""));
                    for (i = 0; i < subsList.size(); i++) {
                        if (subsList.get(i) != null) {
                            subsList = (LinkedList<Subscription>) subsc_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading pkg_data.rycox
            try {
                FileInputStream pkg_fistream = new FileInputStream("pkg_data.rycox");            //read from specified file
                try (ObjectInputStream pkg_oistream = new ObjectInputStream(pkg_fistream)) {
                    pkgList.add(new TVPackage("", "", "", "", 0, "", ""));
                    for (i = 0; i < pkgList.size(); i++) {
                        if (pkgList.get(i) != null) {
                            pkgList = (LinkedList<TVPackage>) pkg_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading pckging_data.rycox
            try {
                FileInputStream pckging_fistream = new FileInputStream("pckging_data.rycox");            //read from specified file
                try (ObjectInputStream pckging_oistream = new ObjectInputStream(pckging_fistream)) {
                    pckgingList.add(new Packaging("", ""));
                    for (i = 0; i < pckgingList.size(); i++) {
                        if (pckgingList.get(i) != null) {
                            pckgingList = (LinkedList<Packaging>) pckging_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading prg_data.rycox
            try {
                FileInputStream prg_fistream = new FileInputStream("prg_data.rycox");            //read from specified file
                try (ObjectInputStream prg_oistream = new ObjectInputStream(prg_fistream)) {
                    prgList.add(new TVProgramme("", "", "", "", "", "", "", "", "", ""));
                    for (i = 0; i < prgList.size(); i++) {
                        if (prgList.get(i) != null) {
                            prgList = (LinkedList<TVProgramme>) prg_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }

            //reading user_data.rycox
            try {
                FileInputStream user_fistream = new FileInputStream("user_data.rycox");            //read from specified file
                try (ObjectInputStream user_oistream = new ObjectInputStream(user_fistream)) {
                    userList.add(new Administrators("", ""));
                    for (i = 0; i < userList.size(); i++) {
                        if (userList.get(i) != null) {
                            userList = (LinkedList<Users>) user_oistream.readObject();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
            }
        }
    }

    /*
     * ----------------------------------------------------------------------------
     * HANDLER CLASSES
     * -----------------------------------------------------------------
     */
    private class UserLoginhandler extends KeyAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ulsubmit) {
                try {
                    //handle user login logic
                    loginCheck();
                } catch (IOException ex) {
                    Logger.getLogger(RYCOXv2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == ulclear) {
                int option = JOptionPane.showConfirmDialog(null, "Exit RYCOX CMM?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                try {
                    loginCheck();
                } catch (IOException ex) {
                    Logger.getLogger(RYCOXv2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    static void printLog() {
        try {
            try (PrintWriter pw_log2 = new PrintWriter(new BufferedWriter(new FileWriter("log.txt", true)))) {
                for (int lg = 0; lg < logList.size(); lg++) {
                    if (RYCOXv2.logList.get(lg) != null) {
                        pw_log2.print(logList.get(lg).getDate() + "\t");
                        pw_log2.print((logList.get(lg)).getUser() + " ");
                        pw_log2.println((logList.get(lg)).getAction());
                    }
                }
            }
        } catch (Exception ex) {
        }
    }

    @SuppressWarnings("unused")
    public void loginCheck() throws IOException {
        boolean usernameVal = false;
        boolean pwVal = false;
        int ux;

        String username = un_input.getText().trim();
        @SuppressWarnings("deprecation")
        String password = pw_input.getText().trim();

        if ((username == null) || (username.equals(""))) {
            JOptionPane.showMessageDialog(null, "Please enter username!");
        } else if ((password == null) || (password.trim().equals(""))) {
            JOptionPane.showMessageDialog(null, "Please enter password!");
        } else {
            for (ux = 0; ux < userList.size(); ux++) {
                if ((username.equalsIgnoreCase(userList.get(ux).getUserID())) == false) {
                    if (ux == (userList.size() - 1)) {
                        usernameVal = false;
                        JOptionPane.showMessageDialog(null, "User not found!");
                    }
                } else {
                    usernameVal = true;
                    break;
                }
            }

            if (usernameVal) {
                for (int v = 0; v < userList.size(); v++) {
                    if ((password.equals(userList.get(ux).getPassword())) == false) {
                        if (v == (userList.size() - 1)) {
                            pwVal = false;
                            JOptionPane.showMessageDialog(null, "Password does not matched!");
                        }
                    } else {
                        pwVal = true;
                        JOptionPane.showMessageDialog(null, "Welcome back, " + userList.get(ux).getUserID() + "!");
                        currentUser = ux;
                        user = userList.get(currentUser).getUserID();

                        log = new LogFile(user, " has logged into the system.");
                        logList.addLast(log);
                        userList.get(ux).setLastLoggedIn();

                        //save lastloggedindate
                        try {
                            FileOutputStream user_fostream = new FileOutputStream("user_data.rycox");
                            try (ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream)) {
                                for (v = 0; v < RYCOXv2.userList.size(); v++) {
                                    if (RYCOXv2.userList.get(v) != null) {
                                        user_oostream.writeObject(RYCOXv2.userList);
                                    }
                                }
                                user_oostream.flush();
                            }
                        } catch (Exception ex) {
                        }

                        dispose();
                        new MainUI().setVisible(true);
                        break;
                    }
                }
            }
        }
    }

    public static void saveClientFile() {
        try {
            FileOutputStream client_fostream = new FileOutputStream("cl_data.rycox");
            try (ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream)) {
                for (i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (RYCOXv2.clientList.get(i) != null) {
                        client_oostream.writeObject(RYCOXv2.clientList);
                    }
                }

                client_oostream.flush();
            }
            RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[CLIENT]");
            RYCOXv2.logList.add(RYCOXv2.log);
            RYCOXv2.printLog();

        } catch (Exception ex) {
        }
    }

    public static void saveServiceFile() {
        try {
            FileOutputStream FOS = new FileOutputStream("serv_data.rycox");
            try (ObjectOutputStream OOS = new ObjectOutputStream(FOS)) {
                for (i = 0; i < RYCOXv2.servList.size(); i++) {
                    if (RYCOXv2.servList.get(i) != null) {
                        OOS.writeObject(RYCOXv2.servList);
                    }
                }
                OOS.flush();
            }
            RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[SERVICE]");
            RYCOXv2.logList.add(RYCOXv2.log);
            RYCOXv2.printLog();
        } catch (Exception ex) {
        }
    }

    public static void saveSubscriptionFile() {
        try {
            FileOutputStream subsc_fostream = new FileOutputStream("subsc_data.rycox");
            try (ObjectOutputStream subsc_oostream = new ObjectOutputStream(subsc_fostream)) {
                for (i = 0; i < RYCOXv2.subsList.size(); i++) {
                    if (RYCOXv2.subsList.get(i) != null) {
                        subsc_oostream.writeObject(RYCOXv2.subsList);
                    }
                }
                subsc_oostream.flush();
            }
        } catch (Exception ex) {
        }
    }

    public static void savePackageFile() {
        try {
            FileOutputStream pkg_fostream = new FileOutputStream("pkg_data.rycox");
            try (ObjectOutputStream pkg_oostream = new ObjectOutputStream(pkg_fostream)) {
                for (i = 0; i < RYCOXv2.pkgList.size(); i++) {
                    if (RYCOXv2.pkgList.get(i) != null) {
                        pkg_oostream.writeObject(RYCOXv2.pkgList);
                    }
                }
                pkg_oostream.flush();
            }
        } catch (Exception ex) {
        }
    }

    public static void savePackagingFile() {
        try {
            FileOutputStream pckging_fostream = new FileOutputStream("pckging_data.rycox");
            try (ObjectOutputStream pckging_oostream = new ObjectOutputStream(pckging_fostream)) {
                for (i = 0; i < RYCOXv2.pckgingList.size(); i++) {
                    if (RYCOXv2.pckgingList.get(i) != null) {
                        pckging_oostream.writeObject(RYCOXv2.pckgingList);
                    }
                }
                pckging_oostream.flush();
            }
        } catch (Exception ex) {
        }
    }

    public static void saveProgramFile() {
        try {
            FileOutputStream prg_fostream = new FileOutputStream("prg_data.rycox");
            try (ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream)) {
                for (i = 0; i < RYCOXv2.prgList.size(); i++) {
                    if (RYCOXv2.prgList.get(i) != null) {
                        prg_oostream.writeObject(RYCOXv2.prgList);
                    }
                }
                prg_oostream.flush();
            }
        } catch (Exception ex) {
        }
    }

    public static void saveUserFile() {
        try {
            FileOutputStream user_fostream = new FileOutputStream("user_data.rycox");
            try (ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream)) {
                for (i = 0; i < RYCOXv2.userList.size(); i++) {
                    if (RYCOXv2.userList.get(i) != null) {
                        user_oostream.writeObject(RYCOXv2.userList);
                    }
                }
                user_oostream.flush();
            }
        } catch (Exception ex) {
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
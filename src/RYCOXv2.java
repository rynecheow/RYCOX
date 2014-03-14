import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.LinkedList;

@SuppressWarnings("serial")
class RYCOXv2 extends JFrame {

    public RYCOXv2() {
        super("RYCOX System - UniTV Customer Care and Billing Management");
        setVisible(true);
        superMenu();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
    static int currentUser;    //index for current user
    Container mainFrame = getContentPane();

    /*USER LOGIN FRAME*/
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
    private Color phcolor = new Color(211, 211, 211);


    /*MAIN MENU PAGE*/
    JTabbedPane tabbedPane;
    JPanel mainmessage;

    /*---------------------------------------------------------------------------- SUPER MENU ---------------------------------------------------------------------*/
    public void superMenu() {
        userlogin();
    }

	/*------------------------------------------------------------------------ USER LOGIN INTERFACE ---------------------------------------------------------------*/

    public void userlogin() {
        setTitle("RYCOX System User Login");
        setTitle("RYCOX System - Customer Management Module Log in");
        setSize(600, 500);
        getContentPane().setBackground(bgcolor);
        ulpanelleft = new JPanel();
        ulpanelleft.setLayout(null);
        ulpanelleft.setBackground(Color.WHITE);
        ullogo = new JLabel();
        ullogo.setIcon(new ImageIcon(getClass().getResource("rycox.png")));
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
        uldisp.setIcon(new ImageIcon(getClass().getResource("CMM-LOGIN.png")));
        ulpanelright.add(uldisp).setBounds(50, 50, 300, 100);
        ulwelcomeMsg.setBounds(50, 150, 300, 50);
//		PlaceHolderText a = new PlaceHolderText(un_input, "Please enter your User ID");
//		PlaceHolderText b = new PlaceHolderText(pw_input, "Please enter your Password");
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
    }

	/*----------------------------------------------------------------------------- MAIN METHOD -------------------------------------------------------------------*/

    @SuppressWarnings("unchecked")
    public static void main(String[] rycox) {
        RYCOXv2 inu = new RYCOXv2();
        //inu.setSize(1600,900);

        userList = new LinkedList<Users>();
        clientList = new LinkedList<ClientAccount>();
        servList = new LinkedList<Service>();
        subsList = new LinkedList<Subscription>();
        pkgList = new LinkedList<TVPackage>();
        pckgingList = new LinkedList<Packaging>();
        prgList = new LinkedList<TVProgramme>();
        logList = new LinkedList<LogFile>();
        log = new LogFile("", "");
        userList.add(new Administrators("admin", "nimda"));
        userList.add(new FrontdeskStaffs("staff", "123abc"));
        //userList.add(new FrontdeskStaffs("", ""));
        clientList.add(new IndividualClient("Izhar", 39, "631220-05-1243", "9, Trafalgar Road", "I000001", "ACTIVE"));
        clientList.add(new IndividualClient("Rimi Azizi", 21, "901010-05-2828", "10, Jalan Taylor's", "I000002", "ACTIVE"));
        clientList.add(new GovClient("Dept. of Education", "12, Long Fave Strt.", "G000001", "INACTIVE"));
        clientList.add(new NGOClient("NGO", "56, Taylor's Street", "N000001", "ACTIVE"));
        clientList.add(new PrvClient("Private Organisation", "88, Sohai's Street", "P000001", "ACTIVE"));
        clientList.add(new PrvClient("Private Org2", "80, TROLLOLOL's Street", "P000002", "ACTIVE"));
        servList.add(new Service("S000001", "I000001", "D999999", "5, Jalan Sungai Beranang"));
        servList.add(new Service("S000002", "I000001", "D999998", "Lot 1-3 Starhill"));
        servList.add(new Service("S000007", "I000002", "D999993", "Jalan Ara 2"));
        servList.add(new Service("S000008", "I000002", "D999992", "Jalan Tebrau"));
        servList.add(new Service("S000003", "N000001", "D999997", "Lot 3-10 Jalan Taylor"));
        servList.add(new Service("S000004", "P000001", "D999996", "32, Jalan Kota Kemuning"));
        servList.add(new Service("S000005", "G000001", "D999995", "30, Jalan Kota Baru"));
        servList.add(new Service("S000006", "P000002", "D999994", "99, Jalan Kota Tua"));
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
        pkgList.add(new TVPackage("P01", "Variety", "18/04/2012", 70.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P02", "Anime Fun", "18/04/2012", 50.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P03", "MTV", "18/04/2012", 45.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P04", "Golden Music", "18/04/2012", 40.00, "Monthly", "ACTIVE"));
        pkgList.add(new TVPackage("P05", "Fox Movie", "7/12/2011", 350.00, "Yearly", "ACTIVE"));
        pkgList.add(new TVPackage("P06", "Celebrities Movie", "6/8/2011", 250.00, "Yearly", "ACTIVE"));
        pkgList.add(new TVPackage("P07", "HBO", "9/21/2011", 210.00, "Yearly", "ACTIVE"));
        pkgList.add(new TVPackage("P08", "Double Star", "11/04/2012", 100.00, "Yearly", "ACTIVE"));
        pkgList.add(new TVPackage("P09", "ZhiZun", "18/01/2012", 90.00, "Quarterly", "ACTIVE"));
        pkgList.add(new TVPackage("P10", "Series", "18/03/2012", 150.00, "Quarterly", "ACTIVE"));
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
        prgList.add(new TVProgramme("F001", "My Boss My Hero", "Japanese Comedy Drama series about Yakuza members.", "Japan", "27/11/2002", "Active", "18SG", "Comedy", "5stars"));
        prgList.add(new TVProgramme("F002", "Naruto", "Story about a ninja world that a child want to be a leader of the ninja.", "Japan", "27/11/1999", "Active", "U", "Comedy", "4stars"));
        prgList.add(new TVProgramme("F003", "One Piece", "The adventures of Monkey D. Luffy, a 17-year-old boy who gains elastic abilities after inadvertently eating a supernatural fruit.", "Japan", "4/8/1997", "Active", "U", "Comedy", "3stars"));
        prgList.add(new TVProgramme("F004", "Pokemon", "Ash Ketchum and his friends (human and Pok��mon) that he makes on the way as he travels the world catching new Pok��mon.", "Japan", "1/4/1997", "Active", "U", "Comedy", "2stars"));
        prgList.add(new TVProgramme("F005", "Tom & Jerry", "A Cat and Mouse cannot be a friend forever.", "Japan", "3/10/96", "Active", "U", "Comedy", "1star"));
        prgList.add(new TVProgramme("F006", "City Hunter", "Korean Drama from the comic.", "Korea", "27/06/2011", "Active", "PG13", "Series", ""));
        prgList.add(new TVProgramme("F007", "Gloves Come Off", "A Story about a group of boxer.", "Hong Kong", "19/4/2012", "Active", "18SG", "Series", "5stars"));
        prgList.add(new TVProgramme("F008", "Sergeant Tabloid", "A Drama about work and love for a female cop.", "Hong Kong", "2/4/2012", "Active", "PG13", "Series", "5stars"));
        prgList.add(new TVProgramme("F009", "A Song To Remember", "A Romance story between a musician and a pretty protg.", "Singapore", "1/4/2012", "Active", "PG13", "Series", "5stars"));
        prgList.add(new TVProgramme("F010", "Office Girls", "A Shopping mall heir discovers a fine mentoe in his female co-worker.", "Taiwan", "27/3/2012", "Active", "PG13", "Series", "5stars"));
        prgList.add(new TVProgramme("F011", "I Am Number 4", "An extraordinary teen masks his true idntity.", "US", "2/5/2002", "Active", "PG13", "Movie", "3stars"));
        prgList.add(new TVProgramme("F012", "TRON", "A hacker is literally abducted into the world of computer.", "US", "4/6/2011", "Active", "PG13", "Movie", "2stars"));
        prgList.add(new TVProgramme("F013", "Green Zone", "At the start of war in Iraq.", "US", "15/3/2009", "Active", "18SG", "Movie", ""));
        prgList.add(new TVProgramme("F014", "Kung Fu Mahjong", "Suave mahjong pro ken finds himself playing for the highest stakes ever.", "Hong Kong", "21/5/2009", "Active", "PG13", "Movie", "4stars"));
        prgList.add(new TVProgramme("F015", "Treasure Inn", "Law Enforcers who are pulled into a dangerous and seductive adventure.", "China", "11/9/2011", "Active", "18SG", "Movie", "5stars"));
        prgList.add(new TVProgramme("F016", "MayDay", "MayDay Concert.", "China", "11/9/2011", "Active", "U", "Concert", ""));
        prgList.add(new TVProgramme("F017", "Simple Plan", "Simple Plan Concert.", "China", "11/9/2011", "Active", "U", "Concert", "1star"));
        prgList.add(new TVProgramme("F018", "BigBang", "BigBang Concert.", "China", "11/9/2011", "Active", "U", "Concert", ""));


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
        }
    }

    /*---------------------------------------------------------------------------- HANDLER CLASSES -----------------------------------------------------------------*/
    private class UserLoginhandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == ulsubmit) {
                //handle user login logic
                boolean checkUsername = false;
                boolean checkPassword = false;
                int u = 0;

                String username = un_input.getText().trim();
                String password = pw_input.getText().trim();

                if ((username == null) || (username.equals(""))) {
                    JOptionPane.showMessageDialog(null, "Please enter username!");
                } else if ((password == null) || (password.trim().equals(""))) {
                    JOptionPane.showMessageDialog(null, "Please enter password!");
                } else {
                    for (u = 0; u < userList.size(); u++) {
                        if ((username.equalsIgnoreCase(userList.get(u).getUserID())) == false) {
                            if (u == (userList.size() - 1)) {
                                checkUsername = false;
                                JOptionPane.showMessageDialog(null, "User not found!");
                            }
                        } else {
                            checkUsername = true;
                            break;
                        }
                    }

                    if (checkUsername) {
                        for (int i = 0; i < userList.size(); i++) {
                            if ((password.equals(userList.get(u).getPassword())) == false) {
                                if (i == (userList.size() - 1)) {
                                    checkPassword = false;
                                    JOptionPane.showMessageDialog(null, "Password does not matched!");
                                }
                            } else {
                                checkPassword = true;
                                JOptionPane.showMessageDialog(null, "Welcome back, " + userList.get(u).getUserID() + "!");
                                currentUser = u;

                                log = new LogFile(userList.get(currentUser).getUserID(), " has logged into the system.");
                                logList.addLast(log);

                                mainFrame.removeAll();
                                mainUI();
                                mainFrame.repaint();
                                mainFrame.validate();

                                break;
                            }
                        }
                    }
                }
            } else if (e.getSource() == ulclear) {
                int option = JOptionPane.showConfirmDialog(null, "Exit RYCOX CMM?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        }
    }

    public void mainUI() {
        setBackground(Color.WHITE);
    }
    /*---------------------------------------------------------------------------- PLACEHOLDER ---------------------------------------------------------------------*/
//	private class PlaceHolderText {
//
//		private final JTextField textField;
//		private final FocusListener focusListener = new FieldFocusListener();
//		private final DocumentListener docListener = new FieldDocumentListener();
//		private String placeholder;
//		private String previousText = "";
//		private Color previousColor;
//		private boolean settingPlaceholder = false;
//
//		/**
//		 * Create a new PlaceholderText.
//		 * @param textField The field in which to show placeholder text.
//		 * @param placeholder The placeholder text to display when the field is empty.
//		 */
//		public PlaceHolderText(JTextField textField, String placeholder) {
//			this.textField = textField;
//			this.placeholder = placeholder;
//			this.textField.addFocusListener(this.focusListener);
//			this.textField.getDocument().addDocumentListener(this.docListener);
//		}
//
//		/**
//		 * @return The placeholder text displayed by this object.
//		 */
//		@SuppressWarnings("unused")
//		public String getPlaceholder() {
//			return this.placeholder;
//		}
//
//		/**
//		 * @param placeholder The placeholder text this object should display.
//		 */
//		@SuppressWarnings("unused")
//		public void setPlaceholder(String placeholder) {
//			this.placeholder = placeholder;
//		}
//
//		/**
//		 * Tells this object to remove any listeners from its textfield.
//		 */
//		@SuppressWarnings("unused")
//		public void dispose() {
//			this.textField.removeFocusListener(this.focusListener);
//			this.textField.getDocument().removeDocumentListener(this.docListener);
//		}
//
//		private class FieldFocusListener implements FocusListener {
//
//			public void focusGained(FocusEvent e) {
//				textField.setForeground(previousColor);
//				textField.setText(previousText);
//			}
//
//			public void focusLost(FocusEvent e) {
//				previousText = textField.getText();
//				previousColor = Color.WHITE;
//				if (previousText.length() == 0||previousText==null) {
//					settingPlaceholder = true;
//					textField.setForeground(phcolor);
//					textField.setText(placeholder);
//					settingPlaceholder = false;
//				}
//			}
//		}
//
//		private class FieldDocumentListener implements DocumentListener {
//
//			public void changedUpdate(DocumentEvent e) {
//				this.textChanged();
//			}
//
//			public void insertUpdate(DocumentEvent e) {
//				this.textChanged();
//			}
//
//			public void removeUpdate(DocumentEvent e) {
//				this.textChanged();
//			}
//
//			private void textChanged() {
//				if (!settingPlaceholder) {
//					previousText = textField.getText();
//				}
//			}
//
//		}
//
//	}
}

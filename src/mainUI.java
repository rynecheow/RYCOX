import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements ActionListener {

    private JTabbedPane tabbedPane;
    private JPanel homePanel, clientPanel, servPanel, subsPanel, pkgPanel, prgPanel;
    private Color mainbgColor = new Color(23, 28, 30);
    private JMenuBar menubar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;
    private JMenuItem saveMI, exitMI, aboutMI, viewLogMI, logoutMI, saveClientMI;
    private int i;
    private JMenu adminMenu;


    public MainUI() {
        getContentPane().setBackground(mainbgColor);
        setTitle("RYCOX System - Customer Management Module");
        setSize(1600, 900);
        setLocationRelativeTo(null);
        //setLayout(null);
        menubar = new JMenuBar();
        add(menubar);
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");
        adminMenu = new JMenu("Admin");
        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);
        saveMI = new JMenuItem("Save All...");
        exitMI = new JMenuItem("Exit...");
        aboutMI = new JMenuItem("About..");
        viewLogMI = new JMenuItem("View Log...");
        logoutMI = new JMenuItem("Log out '" + RYCOXv2.user + "'...");
        saveClientMI = new JMenuItem("Save clients as...");
        fileMenu.add(saveMI);
        fileMenu.add(exitMI);
        fileMenu.add(logoutMI);
        helpMenu.add(aboutMI);
        viewMenu.add(viewLogMI);
        adminMenu.add(saveClientMI);
        viewLogMI.addActionListener(this);
        logoutMI.addActionListener(this);
        setJMenuBar(menubar);

        saveMI.addActionListener(this);
        exitMI.addActionListener(this);
        homePanel = new JPanel();
        homePanel.add(new JLabel(new ImageIcon(getClass().getResource("mainWelcome.png"))));
        homePanel.setBackground(new Color(41, 50, 51));
        clientTab();
        servTab();
        subsTab();
        prgTab();
        pkgTab();

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(mainbgColor);
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        tabbedPane.addTab("Main Menu", homePanel);
        tabbedPane.addTab("", new ImageIcon(getClass().getResource("clmanagetab.png")), clientPanel);
        tabbedPane.addTab("", new ImageIcon(getClass().getResource("servmanagetab.png")), servPanel);
        tabbedPane.addTab("", new ImageIcon(getClass().getResource("subsmanagetab.png")), subsPanel);
        tabbedPane.addTab("", new ImageIcon(getClass().getResource("prgmanagetab.png")), prgPanel);
        tabbedPane.addTab("", new ImageIcon(getClass().getResource("pkgmanagetab.png")), pkgPanel);
        tabbedPane.setForegroundAt(tabbedPane.getTabCount() - 1, Color.ORANGE);
        tabbedPane.setOpaque(true);
        add(tabbedPane);

        setVisible(true);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit without saving?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (closeCf == JOptionPane.YES_OPTION) {
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has logged out.");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                    System.exit(0);
                }
            }

        });
        if (RYCOXv2.userList.get(RYCOXv2.currentUser) instanceof Administrators) {
            adminMenu.setVisible(false);
        }
    }

    private void clientTab() {
        clientPanel = new ClientPanel();
    }


    private void servTab() {
        servPanel = new ServicePanel();
    }

    private void subsTab() {
        subsPanel = new JPanel();
    }

    private void prgTab() {
        prgPanel = new ProgrammePanel();
    }

    private void pkgTab() {
        pkgPanel = new PackagePanel();
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMI) {
            try {
                FileOutputStream client_fostream = new FileOutputStream("cl_data.rycox");
                ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream);
                for (i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (RYCOXv2.clientList.get(i) != null) {
                        client_oostream.writeObject(RYCOXv2.clientList);
                    }
                }
                client_oostream.flush();
                client_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //saving added data serv_data.rycox
            try {
                FileOutputStream serv_fostream = new FileOutputStream("serv_data.rycox");
                ObjectOutputStream serv_oostream = new ObjectOutputStream(serv_fostream);
                for (i = 0; i < RYCOXv2.servList.size(); i++) {
                    if (RYCOXv2.servList.get(i) != null) {
                        serv_oostream.writeObject(RYCOXv2.servList);
                    }
                }
                serv_oostream.flush();
                serv_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //saving added data subsc_data.rycox
            try {
                FileOutputStream subsc_fostream = new FileOutputStream("subsc_data.rycox");
                ObjectOutputStream subsc_oostream = new ObjectOutputStream(subsc_fostream);
                for (i = 0; i < RYCOXv2.subsList.size(); i++) {
                    if (RYCOXv2.subsList.get(i) != null) {
                        subsc_oostream.writeObject(RYCOXv2.subsList);
                    }
                }
                subsc_oostream.flush();
                subsc_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //saving added data pkg_data.rycox
            try {
                FileOutputStream pkg_fostream = new FileOutputStream("pkg_data.rycox");
                ObjectOutputStream pkg_oostream = new ObjectOutputStream(pkg_fostream);
                for (i = 0; i < RYCOXv2.pkgList.size(); i++) {
                    if (RYCOXv2.pkgList.get(i) != null) {
                        pkg_oostream.writeObject(RYCOXv2.pkgList);
                    }
                }
                pkg_oostream.flush();
                pkg_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //saving added data pckging_data.rycox
            try {
                FileOutputStream pckging_fostream = new FileOutputStream("pckging_data.rycox");
                ObjectOutputStream pckging_oostream = new ObjectOutputStream(pckging_fostream);
                for (i = 0; i < RYCOXv2.pckgingList.size(); i++) {
                    if (RYCOXv2.pckgingList.get(i) != null) {
                        pckging_oostream.writeObject(RYCOXv2.pckgingList);
                    }
                }
                pckging_oostream.flush();
                pckging_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //saving added data prg_data.rycox
            try {
                FileOutputStream prg_fostream = new FileOutputStream("prg_data.rycox");
                ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream);
                for (i = 0; i < RYCOXv2.prgList.size(); i++) {
                    if (RYCOXv2.prgList.get(i) != null) {
                        prg_oostream.writeObject(RYCOXv2.prgList);
                    }
                }
                prg_oostream.flush();
                prg_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            //saving added data user_data.rycox
            try {
                FileOutputStream user_fostream = new FileOutputStream("user_data.rycox");
                ObjectOutputStream user_oostream = new ObjectOutputStream(user_fostream);
                for (i = 0; i < RYCOXv2.userList.size(); i++) {
                    if (RYCOXv2.userList.get(i) != null) {
                        user_oostream.writeObject(RYCOXv2.userList);
                    }
                }
                user_oostream.flush();
                user_oostream.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[CLIENT]");
            RYCOXv2.logList.add(RYCOXv2.log);
            RYCOXv2.printLog();
        } else if (e.getSource() == exitMI) { //end if
            int closeCf = JOptionPane.showConfirmDialog(null, "Exit without saving?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
            if (closeCf == JOptionPane.YES_OPTION) {
                System.exit(0);
                RYCOXv2.log = new LogFile(RYCOXv2.user, " has logged out.");
                RYCOXv2.logList.add(RYCOXv2.log);
                RYCOXv2.printLog();
            }
        } else if (e.getSource() == viewLogMI) {
            LogDialog ld = new LogDialog(this);
            ld.setVisible(true);
        } else if (e.getSource() == logoutMI) {
            int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out? Any unsaved changes will be discarded.", "Confirm logout", JOptionPane.WARNING_MESSAGE);
            System.out.println("option");

            if (option == JOptionPane.YES_OPTION) {
                RYCOXv2.log = new LogFile(RYCOXv2.user, " has logged out.");
                RYCOXv2.logList.add(RYCOXv2.log);
                RYCOXv2.printLog();
                dispose();
                new RYCOXv2();
                RYCOXv2.initialise();
            }
        }
    }
}

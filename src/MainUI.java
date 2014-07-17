//~--- JDK imports ------------------------------------------------------------

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

@SuppressWarnings("serial")
public class MainUI extends JFrame implements ActionListener {
    private JPanel clientPanel;
    private JPanel servPanel;
    private JPanel pkgPanel;
    private JPanel prgPanel;
    private JMenuItem saveMI, exitMI, aboutMI, viewLogMI, logoutMI, reportMI, manageUserMI, chgpwMI;

    @SuppressWarnings("LeakingThisInConstructor")
    public MainUI() throws IOException {
        super("RYCOX System - Customer Management Module");
        Color mainbgColor = new Color(23, 28, 30);
        getContentPane().setBackground(mainbgColor);
        setSize(1600, 900);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JMenuBar menubar = new JMenuBar();
        add(menubar);
        JMenu fileMenu = new JMenu("File");
        JMenu viewMenu = new JMenu("View");
        JMenu helpMenu = new JMenu("Help");
        JMenu adminMenu = new JMenu("Admin");
        menubar.add(fileMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);
        menubar.add(adminMenu);
        saveMI = new JMenuItem("Save All...");
        exitMI = new JMenuItem("Exit...");
        aboutMI = new JMenuItem("About..");
        viewLogMI = new JMenuItem("View Log...");
        logoutMI = new JMenuItem("Log out '" + App.user + "'...");
        reportMI = new JMenuItem("View Report...");
        manageUserMI = new JMenuItem("Manage users..");
        chgpwMI = new JMenuItem("Change log in password...");
        fileMenu.add(saveMI);
        fileMenu.add(exitMI);
        fileMenu.add(logoutMI);
        helpMenu.add(aboutMI);
        helpMenu.add(chgpwMI);
        viewMenu.add(viewLogMI);
        adminMenu.add(reportMI);
        adminMenu.add(manageUserMI);
        viewLogMI.addActionListener(this);
        aboutMI.addActionListener(this);
        logoutMI.addActionListener(this);
        setJMenuBar(menubar);
        saveMI.addActionListener(this);
        exitMI.addActionListener(this);
        reportMI.addActionListener(this);
        manageUserMI.addActionListener(this);
        chgpwMI.addActionListener(this);
        JPanel homePanel = new JPanel();
        homePanel.add(
                new JLabel(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/mainwelcome.png")))));
        homePanel.setBackground(new Color(41, 50, 51));
        clientTab();
        servTab();
        prgTab();
        pkgTab();

        if (App.userList.get(App.currentUser) instanceof FrontdeskStaffs) {
            adminMenu.setVisible(false);
        }

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(mainbgColor);
        tabbedPane.setTabPlacement(SwingConstants.LEFT);
        tabbedPane.addTab("Main Menu", homePanel);
        tabbedPane.addTab(
                "", new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/clmanagetab.png"))),
                clientPanel);
        tabbedPane.addTab(
                "", new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/clmanagetab.png"))),
                clientPanel);
        tabbedPane.addTab(
                "", new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/servmanagetab.png"))),
                servPanel);
        tabbedPane.addTab(
                "", new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/pkgmanagetab.png"))),
                pkgPanel);
        tabbedPane.addTab(
                "", new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/resources/prgmanagetab.png"))),
                prgPanel);
        tabbedPane.setForegroundAt(tabbedPane.getTabCount() - 1, Color.ORANGE);
        tabbedPane.setOpaque(true);
        add(tabbedPane);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent winEvt) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Exit without saving?", "Confirm exit",
                        JOptionPane.YES_NO_OPTION);

                if (closeCf == JOptionPane.YES_OPTION) {
                    App.log = new LogFile(App.user, " has logged out.");
                    App.logList.add(App.log);
                    App.printLog();
                    System.exit(0);
                }
            }
        });
    }

    private void clientTab() {
        clientPanel = new ClientPanel();
    }

    private void servTab() {
        servPanel = new ServicePanel();
    }

    private void prgTab() {
        prgPanel = new ProgrammePanel();
    }

    private void pkgTab() {
        pkgPanel = new PackagePanel();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveMI) {
            App.saveClientFile();
            App.saveServiceFile();
            App.saveSubscriptionFile();
            App.savePackageFile();
            App.savePackagingFile();
            App.saveProgramFile();
            App.saveUserFile();
            App.log = new LogFile(App.user, " has saved the data.[CLIENT]");
            App.logList.add(App.log);
            App.printLog();
        } else if (e.getSource() == exitMI) {    // end if
            int closeCf = JOptionPane.showConfirmDialog(null, "Exit without saving?", "Confirm exit",
                    JOptionPane.YES_NO_OPTION);

            if (closeCf == JOptionPane.YES_OPTION) {
                System.exit(0);
                App.log = new LogFile(App.user, " has logged out.");
                App.logList.add(App.log);
                App.printLog();
            }
        } else if (e.getSource() == viewLogMI) {
            LogDialog ld = new LogDialog(this);

            ld.setVisible(true);
        } else if (e.getSource() == logoutMI) {
            int option = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to log out? Any unsaved changes will be discarded.",
                    "Confirm logout", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                App.log = new LogFile(App.user, " has logged out.");
                App.logList.add(App.log);
                App.printLog();
                dispose();
                new App();
                App.initialise();
            }
        } else if (e.getSource() == manageUserMI) {
            new ManageUsers(this).setVisible(true);
        } else if (e.getSource() == reportMI) {
            new GenerateReportDialog(this).setVisible(true);
        } else if (e.getSource() == chgpwMI) {
            new ChangePasswordDialog(this).setVisible(true);
        } else if (e.getSource() == aboutMI) {
            new AboutDialog(this).setVisible(true);
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
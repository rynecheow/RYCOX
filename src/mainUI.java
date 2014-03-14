import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

@SuppressWarnings("serial")
public class mainUI extends JFrame implements ActionListener {

    private JTabbedPane tabbedPane;
    private JPanel homePanel, clientPanel, servPanel, subsPanel, pkgPanel, prgPanel;
    private Color mainbgColor = new Color(23, 28, 30);
    private JMenuBar menubar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;
    private JMenuItem saveMI, exitMI, aboutMI;
    private int i;

    public mainUI() {
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
        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(viewMenu);
        menubar.add(helpMenu);
        saveMI = new JMenuItem("Save");
        exitMI = new JMenuItem("Exit");
        aboutMI = new JMenuItem("About");
        fileMenu.add(saveMI);
        fileMenu.add(exitMI);
        helpMenu.add(aboutMI);
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
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    private void clientTab() {
        clientPanel = new ClientPanel();
    }


    private void servTab() {
        servPanel = new servicePanel();
    }

    private void subsTab() {
        subsPanel = new JPanel();
    }

    private void prgTab() {
        prgPanel = new ProgrammePanel();
    }

    private void pkgTab() {
        pkgPanel = new JPanel();
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
        } else if (e.getSource() == exitMI) { //end if
            int closeCf = JOptionPane.showConfirmDialog(null, "Exit without saving?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
            if (closeCf == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

    }
}

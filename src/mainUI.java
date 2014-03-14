import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class mainUI extends JFrame implements ActionListener {

    private JTabbedPane tabbedPane;
    private JPanel homePanel, clientPanel, servPanel, subsPanel, pkgPanel, prgPanel;
    private Color mainbgColor = new Color(23, 28, 30);
    private JMenuBar menubar;
    private JMenu fileMenu, editMenu, viewMenu, helpMenu;
    private JMenuItem saveMI, exitMI, aboutMI;

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


    public void actionPerformed(ActionEvent arg0) {

    }
}

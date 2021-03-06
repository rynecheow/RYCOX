import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import org.jfree.chart.ChartFactory;
//import org.jfree.chart.ChartFrame;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.plot.PiePlot3D;
//import org.jfree.data.general.DefaultPieDataset;

public class GenerateReportDialog extends JDialog implements ActionListener {

    private String clientReportString;
    int indNo = 0;
    int govNo = 0;
    int prvNo = 0;
    int ngoNo = 0;
    int actNo = 0;
    int inactNo = 0;
    int termNo = 0;
    int servActNo = 0;
    int servInactNo = 0;
    int servTermNo = 0;
    int prgActNo = 0;
    int prgInactNo = 0;
    int prgTermNo = 0;
    int movieNo = 0;
    int seriesNo = 0;
    int comedyNo = 0;
    int concertNo = 0;
    int pkgActNo = 0;
    int pkgInactNo = 0;
    int pkgTermNo = 0;
    private String serviceReportString = "";
    private String programmeReportString = "";
    private String packageReportString = "";

    // End of variables declaration
    public GenerateReportDialog(JFrame frame) {
        super(frame, "RYCOX System - Report", true);
        setLocation(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        JTabbedPane reportTabbedPane = new JTabbedPane();
        JPanel clientReportPanel = new JPanel();
        JScrollPane jScrollPane2 = new JScrollPane();
        JTextArea clientReportTA = new JTextArea();
        JButton viewClientGraphButton = new JButton();
        JPanel serviceReportPanel = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea serviceReportTA = new JTextArea();
        JPanel packageReportPanel = new JPanel();
        JScrollPane jScrollPane3 = new JScrollPane();
        JTextArea packageReportTA = new JTextArea();
        JPanel programmeReportPanel = new JPanel();
        JScrollPane jScrollPane4 = new JScrollPane();
        JTextArea programmeReportTA = new JTextArea();
        JButton viewProgrammeGraphButton = new JButton();
        clientReportTA.setEditable(false);
        clientReportTA.setBackground(Color.WHITE);
        serviceReportTA.setEditable(false);
        serviceReportTA.setBackground(Color.WHITE);
        packageReportTA.setEditable(false);
        packageReportTA.setBackground(Color.WHITE);
        programmeReportTA.setEditable(false);
        programmeReportTA.setBackground(Color.WHITE);

        clientReporting();
        serviceReporting();
        packageReporting();
        programmeReporting();

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        reportTabbedPane.setBackground(new Color(23, 28, 31));
        reportTabbedPane.setForeground(new Color(23, 28, 31));

        clientReportPanel.setBackground(new Color(23, 28, 31));

        clientReportTA.setColumns(20);
        clientReportTA.setRows(5);
        jScrollPane2.setViewportView(clientReportTA);

        viewClientGraphButton.setText("View graph and close window");
        viewClientGraphButton.addActionListener(this);

        GroupLayout clientReportPanelLayout = new GroupLayout(clientReportPanel);
        clientReportPanel.setLayout(clientReportPanelLayout);
        clientReportPanelLayout.setHorizontalGroup(
                clientReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(clientReportPanelLayout.createSequentialGroup().addContainerGap().addGroup(clientReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, clientReportPanelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(viewClientGraphButton))).addContainerGap()));
        clientReportPanelLayout.setVerticalGroup(
                clientReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(clientReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 315, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(viewClientGraphButton).addContainerGap(20, Short.MAX_VALUE)));

        reportTabbedPane.addTab("Clients", clientReportPanel);

        serviceReportPanel.setBackground(new Color(23, 28, 31));

        serviceReportTA.setColumns(20);
        serviceReportTA.setRows(5);
        jScrollPane1.setViewportView(serviceReportTA);

        GroupLayout serviceReportPanelLayout = new GroupLayout(serviceReportPanel);
        serviceReportPanel.setLayout(serviceReportPanelLayout);
        serviceReportPanelLayout.setHorizontalGroup(
                serviceReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(serviceReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE).addContainerGap()));
        serviceReportPanelLayout.setVerticalGroup(
                serviceReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(serviceReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE).addContainerGap(29, Short.MAX_VALUE)));

        reportTabbedPane.addTab("Services", serviceReportPanel);

        packageReportPanel.setBackground(new Color(23, 28, 31));

        packageReportTA.setColumns(20);
        packageReportTA.setRows(5);
        jScrollPane3.setViewportView(packageReportTA);

        GroupLayout packageReportPanelLayout = new GroupLayout(packageReportPanel);
        packageReportPanel.setLayout(packageReportPanelLayout);
        packageReportPanelLayout.setHorizontalGroup(
                packageReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(packageReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE).addContainerGap()));
        packageReportPanelLayout.setVerticalGroup(
                packageReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(packageReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE).addContainerGap(29, Short.MAX_VALUE)));

        reportTabbedPane.addTab("Packages", packageReportPanel);

        programmeReportPanel.setBackground(new Color(23, 28, 31));

        programmeReportTA.setColumns(20);
        programmeReportTA.setRows(5);
        jScrollPane4.setViewportView(programmeReportTA);

        viewProgrammeGraphButton.setText("View graph and close window");
        viewProgrammeGraphButton.addActionListener(this);

        GroupLayout programmeReportPanelLayout = new GroupLayout(programmeReportPanel);
        programmeReportPanel.setLayout(programmeReportPanelLayout);
        programmeReportPanelLayout.setHorizontalGroup(
                programmeReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(programmeReportPanelLayout.createSequentialGroup().addContainerGap().addGroup(programmeReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane4, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE).addGroup(GroupLayout.Alignment.TRAILING, programmeReportPanelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(viewProgrammeGraphButton))).addContainerGap()));
        programmeReportPanelLayout.setVerticalGroup(
                programmeReportPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(programmeReportPanelLayout.createSequentialGroup().addContainerGap().addComponent(jScrollPane4, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(viewProgrammeGraphButton).addContainerGap(21, Short.MAX_VALUE)));

        reportTabbedPane.addTab("Programme", programmeReportPanel);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reportTabbedPane));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(reportTabbedPane, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE));

        clientReportTA.setText(clientReportString);
        serviceReportTA.setText(serviceReportString);
        packageReportTA.setText(packageReportString);
        programmeReportTA.setText(programmeReportString);
        pack();
    }

    private void clientReporting() {
        for (int a = 0; a < App.clientList.size(); a++) {
            if (App.clientList.get(a) instanceof IndividualClient) {
                indNo++;
            } else if (App.clientList.get(a) instanceof GovClient) {
                govNo++;
            } else if (App.clientList.get(a) instanceof PrvClient) {
                prvNo++;
            } else if (App.clientList.get(a) instanceof NGOClient) {
                ngoNo++;
            }
        }

        for (int a = 0; a < App.clientList.size(); a++) {
            String status = App.clientList.get(a).getAccountStatus();
            switch (status) {
                case "ACTIVE":
                    actNo++;
                    break;
                case "INACTIVE":
                    inactNo++;
                    break;
                case "TERMINATED":
                    termNo++;
                    break;
            }
        }

        clientReportString = ("Number of client registered to the system:\t" + App.clientList.size() + "\n\n"
                + "Number of active clients:\t" + actNo + "\n"
                + "Number of inactive clients:\t" + inactNo + "\n"
                + "Number of terminated clients:\t" + termNo + "\n\n"
                + "Number of individual clients:\t" + indNo + "\n"
                + "Number of government clients:\t" + govNo) + "\n"
                + "Number of NGO clients:\t" + ngoNo + "\n"
                + "Number of private clients:\t" + prvNo + "\n";
    }

    private void serviceReporting() {
        for (int a = 0; a < App.servList.size(); a++) {
            String status = App.servList.get(a).getServStatus();
            switch (status) {
                case "ACTIVE":
                    servActNo++;
                    break;
                case "BARRED":
                    servInactNo++;
                    break;
                case "TERMINATED":
                    servTermNo++;
                    break;
            }
        }

        serviceReportString = ("Number of services registered to the system:\t" + App.servList.size() + "\n\n"
                + "Number of active services:\t\t" + servActNo + "\n"
                + "Number of barred services:\t\t" + servInactNo + "\n"
                + "Number of terminated services:\t\t" + servTermNo + "\n\n");
    }

    private void packageReporting() {
        for (int a = 0; a < App.pkgList.size(); a++) {
            String status = App.pkgList.get(a).getPkgStatus();
            switch (status) {
                case "ACTIVE":
                    pkgActNo++;
                    break;
                case "INACTIVE":
                    pkgInactNo++;
                    break;
                case "TERMINATED":
                    pkgTermNo++;
                    break;
            }
        }
        packageReportString = ("Number of packages registered to the system:\t" + App.pkgList.size() + "\n\n"
                + "Number of active packages:\t\t" + pkgActNo + "\n"
                + "Number of barred packages:\t\t" + pkgInactNo + "\n"
                + "Number of terminated packages:\t" + pkgTermNo + "\n\n");
    }

    private void programmeReporting() {
        for (int a = 0; a < App.prgList.size(); a++) {
            String status = App.prgList.get(a).getPrgStatus();
            String type = App.prgList.get(a).getType();
            switch (status) {
                case "ACTIVE":
                    prgActNo++;
                    break;
                case "INACTIVE":
                    prgInactNo++;
                    break;
                case "TERMINATED":
                    prgTermNo++;
                    break;
            }

            switch (type) {
                case "Comedy":
                    comedyNo++;
                    break;
                case "Series":
                    seriesNo++;
                    break;
                case "Concert":
                    concertNo++;
                    break;
                case "Movie":
                    movieNo++;
                    break;
            }
        }

        programmeReportString = ("Number of TV programmes registered to the system:\t" + App.prgList.size() + "\n\n"
                + "Number of active TV programmes:\t\t" + prgActNo + "\n"
                + "Number of barred TV programmes:\t\t" + prgInactNo + "\n"
                + "Number of terminated TV programmes:\t\t" + prgTermNo + "\n\n"
                + "Number of TV programme of the type \"Comedy\":\t\t" + comedyNo + "\n"
                + "Number of TV programme of the type \"Series\":\t\t" + seriesNo + "\n"
                + "Number of TV programme of the type \"Concert\":\t\t" + comedyNo + "\n"
                + "Number of TV programme of the type \"Movie\":\t\t" + movieNo + "\n\n");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//	if (e.getSource() == viewClientGraphButton) {
//	    DefaultPieDataset pieDataset = new DefaultPieDataset();
//	    pieDataset.setValue("Individual", indNo);
//	    pieDataset.setValue("Government", govNo);
//	    pieDataset.setValue("NGO", ngoNo);
//	    pieDataset.setValue("Private Organisation", prvNo);
//	    JFreeChart chart = ChartFactory.createPieChart3D("Client type pie chart", pieDataset, true, true, true);
//	    PiePlot3D p = (PiePlot3D) chart.getPlot();
//	    p.setForegroundAlpha(0.5f);
//	    dispose();
//	    ChartFrame frame1 = new ChartFrame("Client type Graph", chart);
//	    frame1.setAlwaysOnTop(true);
//	    frame1.setAutoRequestFocus(true);
//	    frame1.setVisible(true);
//	    frame1.setSize(600, 400);
//	    frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    frame1.setLocation((int) (1920 - frame1.getWidth()) / 2, (int) (1080 - frame1.getHeight()) / 2);
//	} else if (e.getSource() == viewProgrammeGraphButton) {
//	    DefaultPieDataset pieDataset = new DefaultPieDataset();
//	    pieDataset.setValue("Comedy", comedyNo);
//	    pieDataset.setValue("Movies", movieNo);
//	    pieDataset.setValue("Series", seriesNo);
//	    pieDataset.setValue("Concert", concertNo);
//	    JFreeChart chart = ChartFactory.createPieChart3D("Programme type pie chart", pieDataset, true, true, true);
//	    PiePlot3D p = (PiePlot3D) chart.getPlot();
//	    p.setForegroundAlpha(0.5f);
//	    dispose();
//	    ChartFrame frame2 = new ChartFrame("Programme type Graph", chart);
//	    frame2.setAlwaysOnTop(true);
//	    frame2.setVisible(true);
//	    frame2.setSize(600, 400);
//	    frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    frame2.setLocation((int) (1920 - frame2.getWidth()) / 2, (int) (1080 - frame2.getHeight()) / 2);
//	}
    }
}

/**************************************************************************
 * (C) Copyright 2012 by Ryne Cheow Yeong Chi , Ng Jia Jiun               *
 * Lai Li Hao. All rights reserved.                                       *
 * Charts are created using the JFreeChart library.			  *                                                                     *
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
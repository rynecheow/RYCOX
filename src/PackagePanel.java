import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("serial")
class PackagePanel extends JPanel {

    private static JTable pkgTable;
    private JButton pkgAddButton;
    private JButton pkgDeleteButton;
    private JButton editPkgButton;
    private JScrollPane scrollPane;
    //private JButton redoButton;
    private JPanel toolbar;
    private JButton saveButton;
    //private JButton undoButton;
    private JButton viewButton;
    private JButton pkgActivateButton;
    private JButton pkgDeactButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editPkgMI, deletePkgMI, activateMI, viewMI, deactivateMI;
    private String[][] pkgData;
    private static AbstractTableModel pkgModel;
    static String[] pkgtemp;
    private int rowno;


    @SuppressWarnings({"unchecked", "rawtypes"})
    public PackagePanel() {
        toolbar = new JPanel();
        pkgAddButton = new JButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        pkgAddButton.setBackground(bColor);
        editPkgButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editPkgButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        saveButton.setBackground(bColor);
        //redoButton = new JButton("",new ImageIcon(getClass().getResource("redobutton.png")));
        //redoButton.setBackground(bColor);
        //undoButton = new JButton("",new ImageIcon(getClass().getResource("undobutton.png")));
        //undoButton.setBackground(bColor);
        pkgDeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        pkgDeleteButton.setBackground(bColor);
        viewButton = new JButton("", new ImageIcon(getClass().getResource("viewbutton.png")));
        viewButton.setBackground(bColor);
        pkgActivateButton = new JButton("", new ImageIcon(getClass().getResource("activatebutton.png")));
        pkgActivateButton.setBackground(bColor);
        pkgDeactButton = new JButton("", new ImageIcon(getClass().getResource("deactivatebutton.png")));
        pkgDeactButton.setBackground(bColor);
        scrollPane = new JScrollPane();
        pkgTable = new JTable();
        pkgTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel("You are logged in as " + RYCOXv2.user + ".");
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));

        setBackground(new Color(23, 28, 30));
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));

        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(pkgAddButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(editPkgButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pkgDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                        //.addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        //.addPreferredGap(ComponentPlacement.UNRELATED)
                                        //.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                        //.addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pkgActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pkgDeactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(pkgAddButton)
                                        .addComponent(saveButton)
                                        .addComponent(editPkgButton)
                                        .addComponent(viewButton)
                                                //.addComponent(redoButton)
                                                //.addComponent(undoButton)
                                        .addComponent(pkgActivateButton)
                                        .addComponent(pkgDeactButton)
                                        .addComponent(loginInfo)
                                        .addComponent(pkgDeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pkgTable.setBackground(new Color(227, 226, 226));
        pkgTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N

        pkgData = new String[RYCOXv2.pkgList.size()][5];
        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            for (int j = 0; j <= 5; j++) {

                switch (j) {
                    case 0:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgCode();
                        break;
                    case 1:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgName();
                        break;
                    case 2:
                        pkgData[i][j] = Double.toString(RYCOXv2.pkgList.get(i).getChargePrice());
                        break;
                    case 3:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getChargeType();
                        break;
                    case 4:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgStatus();
                        break;

                }
            }
        }

        pkgModel = new DefaultTableModel(
                pkgData,
                new String[]{
                        "Package Code", "Package Name", "Charge Price(RM)", "Charge Type", "Package Status"
                }
        ) {

            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
            };

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        pkgTable.setModel(pkgModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(pkgModel);
        pkgTable.setRowSorter(sorter);
        pkgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        pkgTable.setName("");
        pkgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(pkgTable);


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(toolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1480, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(toolbar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE)
                                .addContainerGap())
        );

        pkgTable.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {          //////////////////
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = pkgTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = pkgTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                    rowno = pkgTable.rowAtPoint(e.getPoint());
                    pkgtemp = new String[7];
                    ////////////////////////////////////////
                    pkgtemp[0] = (String) pkgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < RYCOXv2.pkgList.size(); j++) {
                        if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.pkgList.get(j).getPkgCode())) {
                            pkgtemp[1] = RYCOXv2.pkgList.get(j).getPkgName();
                            pkgtemp[2] = Double.toString(RYCOXv2.pkgList.get(j).getChargePrice());
                            pkgtemp[3] = RYCOXv2.pkgList.get(j).getChargeType();
                            pkgtemp[4] = RYCOXv2.pkgList.get(j).getStartDate();
                            pkgtemp[5] = RYCOXv2.pkgList.get(j).getTerminationDate();
                            pkgtemp[6] = RYCOXv2.pkgList.get(j).getPkgStatus();


                            break;
                        }
                    }
                    changeMode();
                    showPopup(e);
                } else if (SwingUtilities.isLeftMouseButton(e)) {

                    ListSelectionModel model = pkgTable.getSelectionModel();
                    model.setSelectionInterval(pkgTable.rowAtPoint(e.getPoint()), pkgTable.rowAtPoint(e.getPoint()));
                    rowno = pkgTable.rowAtPoint(e.getPoint());
                    pkgtemp = new String[7];
                    ////////////////////////////////////////
                    pkgtemp[0] = (String) pkgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < RYCOXv2.pkgList.size(); j++) {
                        if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.pkgList.get(j).getPkgCode())) {
                            pkgtemp[1] = RYCOXv2.pkgList.get(j).getPkgName();
                            pkgtemp[2] = Double.toString(RYCOXv2.pkgList.get(j).getChargePrice());
                            pkgtemp[3] = RYCOXv2.pkgList.get(j).getChargeType();
                            pkgtemp[4] = RYCOXv2.pkgList.get(j).getStartDate();
                            pkgtemp[5] = RYCOXv2.pkgList.get(j).getTerminationDate();
                            pkgtemp[6] = RYCOXv2.pkgList.get(j).getPkgStatus();


                            break;
                        }
                    }
                    changeMode();

                }
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
        ); //table click            /////////////////////////////////////////////////

        editPkgMI = new JMenuItem("Edit Package...");
        deletePkgMI = new JMenuItem("Terminate Package...");
        activateMI = new JMenuItem("Activate Package...");
        deactivateMI = new JMenuItem("Deactivate Package...");
        viewMI = new JMenuItem("View Package...");


        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(editPkgMI);
        popupMenu.add(deletePkgMI);
        popupMenu.add(activateMI);
        popupMenu.add(deactivateMI);
        popupMenu.add(viewMI);
        
		/*-------------------------- MENU ITEM LISTENER --------------------------*/

        activateMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String ID = (String) pkgTable.getValueAt(pkgTable.getSelectedRow(), 0);
                for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                    if (ID.equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                        if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("INACTIVE")) {

                            int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Package '" + RYCOXv2.pkgList.get(i).getPkgCode() + "'?");
                            if (opt == JOptionPane.YES_OPTION) {
                                RYCOXv2.pkgList.get(i).setStatus("ACTIVE");
                                JOptionPane.showMessageDialog(null, "Package " + RYCOXv2.pkgList.get(i).getPkgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                updatePackageTable();
                                pkgTable.clearSelection();
                                pkgDeactButton.setEnabled(true);
                                deactivateMI.setEnabled(true);
                                pkgActivateButton.setEnabled(true);
                                activateMI.setEnabled(true);
                                break;
                            } else if (opt == JOptionPane.NO_OPTION) {
                                break;
                            }
                        }
                    }
                }
                //				pkgTable.clearSelection();
                //				pkgDeactButton.setEnabled(true);
                //				deactivateMI.setEnabled(true);
                //				pkgActivateButton.setEnabled(true);
                //				activateMI.setEnabled(true);


            }

        });


        deactivateMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String ID = (String) pkgTable.getValueAt(pkgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                            if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("ACTIVE")) {
                                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Package " + RYCOXv2.pkgList.get(i).getPkgCode() + " ?", "Package Code found!", JOptionPane.WARNING_MESSAGE);
                                if (choice == JOptionPane.YES_OPTION) {
                                    RYCOXv2.pkgList.get(i).setStatus("INACTIVE");
                                    JOptionPane.showMessageDialog(null, "Package '" + RYCOXv2.pkgList.get(i).getPkgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updatePackageTable();
                                    pkgTable.clearSelection();
                                    pkgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    pkgActivateButton.setEnabled(true);
                                    activateMI.setEnabled(true);
                                    break;
                                } else if (choice == JOptionPane.NO_OPTION) {
                                    break;
                                }
                            }
                        }
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


        viewMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LogFile log = new LogFile(RYCOXv2.user, "has viewed a TV Package '" + pkgtemp[0] + "'.");
                RYCOXv2.logList.add(log);
                new ViewPackageDialog((JFrame) popupMenu.getParent());
                pkgTable.clearSelection();
            }

        });


        editPkgMI.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                EditPackageDialog epd = new EditPackageDialog((JFrame) popupMenu.getParent());
                updatePackageTable();
                pkgTable.clearSelection();
            }
        });


        deletePkgMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Package " + pkgtemp[0] + " ?", "Package Code found!", JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    boolean flag = false;
                    for (int check = 0; check < RYCOXv2.subsList.size(); check++) {

                        if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.subsList.get(check).getPkgCode())) {
                            JOptionPane.showMessageDialog(null, "The package is included in one or more services. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                            flag = false;
                            break;
                        } else flag = true;
                    }


                    if (flag == true) {

                        for (int counts = 0; counts < RYCOXv2.pkgList.size(); counts++) {
                            if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.pkgList.get(counts).getPkgCode())) {
                                if ((RYCOXv2.pkgList.get(counts).getPkgStatus().equalsIgnoreCase("ACTIVE")) || (RYCOXv2.pkgList.get(counts).getPkgStatus().equalsIgnoreCase("INACTIVE"))) {

                                    RYCOXv2.pkgList.get(counts).setStatus("TERMINATED");
                                    String date = DateFormat.getInstance().format(new Date());
                                    RYCOXv2.pkgList.get(counts).setTerminationDate(date);
                                    JOptionPane.showMessageDialog(null, "TV Programme " + pkgtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                    LogFile log = new LogFile(RYCOXv2.user, "has terminated a TV Programme '" + pkgtemp[0] + "'.");
                                    RYCOXv2.logList.add(log);
                                    break;

                                }


                            }
                        }
                    }

                } else {
                }

                updatePackageTable();
                pkgTable.clearSelection();
            }
        });

		/*-------------------------- BUTTON LISTENER --------------------------*/
        pkgActivateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {

                    String ID = (String) pkgTable.getValueAt(pkgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                            if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("INACTIVE")) {

                                int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Package '" + RYCOXv2.pkgList.get(i).getPkgCode() + "'?");
                                if (opt == JOptionPane.YES_OPTION) {
                                    RYCOXv2.pkgList.get(i).setStatus("ACTIVE");
                                    JOptionPane.showMessageDialog(null, "Package " + RYCOXv2.pkgList.get(i).getPkgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updatePackageTable();
                                    pkgTable.clearSelection();
                                    pkgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    pkgActivateButton.setEnabled(true);
                                    activateMI.setEnabled(true);
                                    break;
                                } else if (opt == JOptionPane.NO_OPTION) {
                                    break;
                                }
                            }
                        }
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        });


        pkgDeactButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String ID = (String) pkgTable.getValueAt(pkgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                            if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("ACTIVE")) {
                                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Package " + RYCOXv2.pkgList.get(i).getPkgCode() + " ?", "Package Code found!", JOptionPane.WARNING_MESSAGE);
                                if (choice == JOptionPane.YES_OPTION) {
                                    RYCOXv2.pkgList.get(i).setStatus("INACTIVE");
                                    JOptionPane.showMessageDialog(null, "Package '" + RYCOXv2.pkgList.get(i).getPkgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updatePackageTable();
                                    pkgTable.clearSelection();
                                    pkgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    pkgActivateButton.setEnabled(true);
                                    activateMI.setEnabled(true);
                                    break;
                                } else if (choice == JOptionPane.NO_OPTION) {
                                    break;
                                }
                            }
                        }
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


        viewButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    LogFile log = new LogFile(RYCOXv2.user, "has viewed a TV Package '" + pkgtemp[0] + "'.");
                    RYCOXv2.logList.add(log);
                    new ViewPackageDialog((JFrame) popupMenu.getParent());
                    pkgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

        pkgAddButton.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                NewPackageDialog npd1 = new NewPackageDialog((JFrame) popupMenu.getParent());
                updatePackageTable();
                pkgTable.clearSelection();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream prg_fostream = new FileOutputStream("pkg_data.rycox");
                    ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream);
                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (RYCOXv2.pkgList.get(i) != null) {
                            prg_oostream.writeObject(RYCOXv2.pkgList);
                        }
                    }
                    prg_oostream.flush();
                    prg_oostream.close();
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[PACKAGE]");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                    JOptionPane.showMessageDialog(null, "You have successfully saved the changes of TV Packages !", "Save successfully", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        editPkgButton.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                try {
                    EditPackageDialog epd = new EditPackageDialog((JFrame) popupMenu.getParent());
                    updatePackageTable();
                    pkgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        pkgDeleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Package " + pkgtemp[0] + " ?", "Package Code found!", JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        boolean flag = false;
                        for (int check = 0; check < RYCOXv2.subsList.size(); check++) {

                            if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.subsList.get(check).getPkgCode())) {
                                JOptionPane.showMessageDialog(null, "The package is included in one or more services. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                                flag = false;
                                break;
                            } else flag = true;
                        }


                        if (flag == true) {

                            for (int counts = 0; counts < RYCOXv2.pkgList.size(); counts++) {
                                if (pkgtemp[0].equalsIgnoreCase(RYCOXv2.pkgList.get(counts).getPkgCode())) {
                                    if ((RYCOXv2.pkgList.get(counts).getPkgStatus().equalsIgnoreCase("ACTIVE")) || (RYCOXv2.pkgList.get(counts).getPkgStatus().equalsIgnoreCase("INACTIVE"))) {

                                        RYCOXv2.pkgList.get(counts).setStatus("TERMINATED");
                                        String date = DateFormat.getInstance().format(new Date());
                                        RYCOXv2.pkgList.get(counts).setTerminationDate(date);
                                        JOptionPane.showMessageDialog(null, "TV Package " + pkgtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                        LogFile log = new LogFile(RYCOXv2.user, "has terminated a TV Package '" + pkgtemp[0] + "'.");
                                        RYCOXv2.logList.add(log);
                                        break;

                                    }


                                }
                            }
                        }
                    } else {
                    }

                    updatePackageTable();
                    pkgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }//end constructor


    public void updatePackageTable() {
        pkgData = new String[RYCOXv2.pkgList.size()][5];
        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            for (int j = 0; j <= 5; j++) {

                switch (j) {
                    case 0:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgCode();
                        break;
                    case 1:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgName();
                        break;
                    case 2:
                        pkgData[i][j] = Double.toString(RYCOXv2.pkgList.get(i).getChargePrice());
                        break;
                    case 3:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getChargeType();
                        break;
                    case 4:
                        pkgData[i][j] = RYCOXv2.pkgList.get(i).getPkgStatus();
                        break;

                }
            }
        }

        pkgModel = new DefaultTableModel(
                pkgData,
                new String[]{
                        "Package Code", "Package Name", "Charge Price(RM)", "Charge Type", "Package Status"
                }
        ) {

            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
            };

            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            @SuppressWarnings({"unchecked", "rawtypes"})
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        pkgTable.setModel(pkgModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(pkgModel);
        pkgTable.setRowSorter(sorter);
        pkgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        pkgTable.setName("");
        pkgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(pkgTable);
    }


    private void changeMode() {
        rowno = pkgTable.getSelectedRow();
        String ID = (String) pkgTable.getValueAt(rowno, 0);

        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            if (ID.equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {

                if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("ACTIVE")) {
                    pkgDeactButton.setEnabled(true);
                    deactivateMI.setEnabled(true);
                    pkgActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                } else if (RYCOXv2.pkgList.get(i).getPkgStatus().equalsIgnoreCase("INACTIVE")) {
                    pkgDeactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    pkgActivateButton.setEnabled(true);
                    activateMI.setEnabled(true);
                } else {
                    pkgDeactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    pkgActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                    editPkgMI.setEnabled(false);
                    editPkgButton.setEnabled(false);
                    pkgDeleteButton.setEnabled(false);
                    deletePkgMI.setEnabled(false);
                }
            }
        }
    }


}
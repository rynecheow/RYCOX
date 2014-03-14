
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
class ProgrammePanel extends JPanel {

    private static JTable prgTable;
    private JButton prgAddButton;
    private static JButton prgDeleteButton;
    private static JButton editPrgButton;
    private JScrollPane scrollPane;
    //private JButton redoButton;
    private JPanel toolbar;
    private JButton saveButton;
    //private JButton undoButton;
    private static JButton viewButton;
    private static JButton prgActivateButton;
    private static JButton prgDeactButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editPrgMI, deletePrgMI, activateMI, viewMI, deactivateMI;
    private String[][] prgData;
    private static AbstractTableModel prgModel;
    static String[] progtemp;
    private int rowno;
    static int lol;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ProgrammePanel() {
        toolbar = new JPanel();
        prgAddButton = new JButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        prgAddButton.setBackground(bColor);
        editPrgButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editPrgButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        saveButton.setBackground(bColor);
        prgDeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        prgDeleteButton.setBackground(bColor);
        viewButton = new JButton("", new ImageIcon(getClass().getResource("viewbutton.png")));
        viewButton.setBackground(bColor);
        prgActivateButton = new JButton("", new ImageIcon(getClass().getResource("activatebutton.png")));
        prgActivateButton.setBackground(bColor);
        prgDeactButton = new JButton("", new ImageIcon(getClass().getResource("deactivatebutton.png")));
        prgDeactButton.setBackground(bColor);
        scrollPane = new JScrollPane();
        prgTable = new JTable();
        prgTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel("You are logged in as " + RYCOXv2.user + ".");
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));

        setBackground(new Color(23, 28, 30));
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));
        defaultButtonSet();
        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup().addContainerGap().addComponent(prgAddButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(editPrgButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(prgDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED) //.addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                        //.addPreferredGap(ComponentPlacement.UNRELATED)
                        //.addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                        //.addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(prgActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(prgDeactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addContainerGap()));
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(toolbarLayout.createSequentialGroup().addContainerGap().addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(prgAddButton).addComponent(saveButton).addComponent(editPrgButton).addComponent(viewButton) //.addComponent(redoButton)
                        //.addComponent(undoButton)
                        .addComponent(prgActivateButton).addComponent(prgDeactButton).addComponent(loginInfo).addComponent(prgDeleteButton)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        prgTable.setBackground(new Color(227, 226, 226));
        prgTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N

        prgData = new String[RYCOXv2.prgList.size()][6];
        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            for (int j = 0; j <= 6; j++) {

                switch (j) {
                    case 0:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getProgCode();
                        break;
                    case 1:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getProgTitle();
                        break;
                    case 2:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getContentOrigin();
                        break;
                    case 3:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getViewerStatus();
                        break;
                    case 4:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getType();
                        break;
                    case 5:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getPrgStatus();
                        break;
                }
            }
        }

        prgModel = new DefaultTableModel(
                prgData,
                new String[]{
                        "Programme Code", "Programme Title", "Content Origin", "Viewer Status", "Type", "Programme Status"
                }) {

            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,};
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        prgTable.setModel(prgModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(prgModel);
        prgTable.setRowSorter(sorter);
        prgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        prgTable.setName("");
        prgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(prgTable);


        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(toolbar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1480, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(toolbar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE).addContainerGap()));

        prgTable.addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent e) {          //////////////////
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = prgTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = prgTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                    rowno = prgTable.rowAtPoint(e.getPoint());
                    progtemp = new String[10];
                    ////////////////////////////////////////
                    progtemp[0] = (String) prgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < RYCOXv2.prgList.size(); j++) {
                        if (progtemp[0].equalsIgnoreCase(RYCOXv2.prgList.get(j).getProgCode())) {
                            progtemp[1] = RYCOXv2.prgList.get(j).getProgTitle();
                            progtemp[2] = RYCOXv2.prgList.get(j).getDesc();
                            progtemp[3] = RYCOXv2.prgList.get(j).getContentOrigin();
                            progtemp[4] = RYCOXv2.prgList.get(j).getCreationDate();
                            progtemp[5] = RYCOXv2.prgList.get(j).getTerminationDate();
                            progtemp[6] = RYCOXv2.prgList.get(j).getPrgStatus();
                            progtemp[7] = RYCOXv2.prgList.get(j).getViewerStatus();
                            progtemp[8] = RYCOXv2.prgList.get(j).getType();
                            progtemp[9] = RYCOXv2.prgList.get(j).getNotes();
                            lol = j;
                            break;
                        }
                    }

                    changeMode();
                    showPopup(e);
                } else if (SwingUtilities.isLeftMouseButton(e)) {

                    ListSelectionModel model = prgTable.getSelectionModel();
                    model.setSelectionInterval(prgTable.rowAtPoint(e.getPoint()), prgTable.rowAtPoint(e.getPoint()));
                    rowno = prgTable.rowAtPoint(e.getPoint());
                    progtemp = new String[10];
                    ////////////////////////////////////////
                    progtemp[0] = (String) prgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < RYCOXv2.prgList.size(); j++) {
                        if (progtemp[0].equalsIgnoreCase(RYCOXv2.prgList.get(j).getProgCode())) {
                            progtemp[1] = RYCOXv2.prgList.get(j).getProgTitle();
                            progtemp[2] = RYCOXv2.prgList.get(j).getDesc();
                            progtemp[3] = RYCOXv2.prgList.get(j).getContentOrigin();
                            progtemp[4] = RYCOXv2.prgList.get(j).getCreationDate();
                            progtemp[5] = RYCOXv2.prgList.get(j).getTerminationDate();
                            progtemp[6] = RYCOXv2.prgList.get(j).getPrgStatus();
                            progtemp[7] = RYCOXv2.prgList.get(j).getViewerStatus();
                            progtemp[8] = RYCOXv2.prgList.get(j).getType();
                            progtemp[9] = RYCOXv2.prgList.get(j).getNotes();

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
        }); //table click            /////////////////////////////////////////////////

        editPrgMI = new JMenuItem("Edit Programme...");
        deletePrgMI = new JMenuItem("Terminate Programme...");
        activateMI = new JMenuItem("Activate Programme...");
        deactivateMI = new JMenuItem("Deactivate Prgramme...");
        viewMI = new JMenuItem("View Programme...");


        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(editPrgMI);
        popupMenu.add(deletePrgMI);
        popupMenu.add(activateMI);
        popupMenu.add(deactivateMI);
        popupMenu.add(viewMI);

        if (RYCOXv2.userList.get(RYCOXv2.currentUser) instanceof Administrators) {
            prgAddButton.setVisible(true);
            saveButton.setVisible(true);
            editPrgButton.setVisible(true);
            viewButton.setVisible(true);
            prgDeleteButton.setVisible(true);
            prgActivateButton.setVisible(true);
            prgDeactButton.setVisible(true);
            editPrgMI.setVisible(true);
            deletePrgMI.setVisible(true);
            activateMI.setVisible(true);
            deactivateMI.setVisible(true);
            viewMI.setVisible(true);
        } else {
            prgAddButton.setVisible(false);
            saveButton.setVisible(false);
            editPrgButton.setVisible(false);
            viewButton.setVisible(true);
            prgDeleteButton.setVisible(false);
            prgActivateButton.setVisible(false);
            prgDeactButton.setVisible(false);
            editPrgMI.setVisible(false);
            deletePrgMI.setVisible(false);
            activateMI.setVisible(false);
            deactivateMI.setVisible(false);
            viewMI.setVisible(true);
        }



	/*
     * -------------------------- MENU ITEM LISTENER --------------------------
	 */
        activateMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try {
                    String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                            if (RYCOXv2.prgList.get(i).getPrgStatus().equalsIgnoreCase("INACTIVE")) {

                                int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Programme '" + RYCOXv2.prgList.get(i).getProgCode() + "'?");
                                if (opt == JOptionPane.YES_OPTION) {
                                    RYCOXv2.prgList.get(i).setPrgStatus("ACTIVE");
                                    JOptionPane.showMessageDialog(null, "Programme " + RYCOXv2.prgList.get(i).getProgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updateProgrammeTable();
                                    prgTable.clearSelection();
                                    prgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    prgActivateButton.setEnabled(true);
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


        deactivateMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {


                try {
                    String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                            if (RYCOXv2.prgList.get(i).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
                                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Programme " + RYCOXv2.prgList.get(i).getProgCode() + " ?", "Programme Code found!", JOptionPane.WARNING_MESSAGE);
                                if (choice == JOptionPane.YES_OPTION) {
                                    RYCOXv2.prgList.get(i).setPrgStatus("INACTIVE");
                                    JOptionPane.showMessageDialog(null, "Programme '" + RYCOXv2.prgList.get(i).getProgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updateProgrammeTable();
                                    prgTable.clearSelection();
                                    prgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    prgActivateButton.setEnabled(true);
                                    activateMI.setEnabled(true);
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
                LogFile log = new LogFile(RYCOXv2.user, "has viewed a TV Programme '" + progtemp[0] + "'.");
                RYCOXv2.logList.add(log);
                new ViewProgrammeDialog((JFrame) popupMenu.getParent());
                prgTable.clearSelection();
            }
        });

        editPrgMI.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                EditProgrammeDialog epd = new EditProgrammeDialog((JFrame) popupMenu.getParent());
                updateProgrammeTable();
                prgTable.clearSelection();
            }
        });


        deletePrgMI.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                boolean flag = false;
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Programme " + progtemp[0] + " ?", "Programme Code found!", JOptionPane.WARNING_MESSAGE);
                if (choice == JOptionPane.YES_OPTION) {
                    for (int check = 0; check < RYCOXv2.pckgingList.size(); check++) {

                        if (progtemp[0].equalsIgnoreCase(RYCOXv2.pckgingList.get(check).getProgCode())) {
                            JOptionPane.showMessageDialog(null, "The programme is included in one or more packages. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                            flag = false;
                            break;
                        } else {
                            flag = true;
                        }
                    }

                    if (flag == true) {

                        for (int counts = 0; counts < RYCOXv2.prgList.size(); counts++) {
                            if (progtemp[0].equalsIgnoreCase(RYCOXv2.prgList.get(counts).getProgCode())) {
                                if ((RYCOXv2.prgList.get(counts).getPrgStatus().equalsIgnoreCase("ACTIVE")) || (RYCOXv2.prgList.get(counts).getPrgStatus().equalsIgnoreCase("INACTIVE"))) {

                                    RYCOXv2.prgList.get(counts).setPrgStatus("TERMINATED");
                                    String date = DateFormat.getInstance().format(new Date());
                                    RYCOXv2.prgList.get(counts).setTerminationDate(date);
                                    JOptionPane.showMessageDialog(null, "TV Programme " + progtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                    LogFile log = new LogFile(RYCOXv2.user, "has terminated a TV Programme '" + progtemp[0] + "'.");
                                    RYCOXv2.logList.add(log);
                                    break;

                                }


                            }
                        }
                    }

                } else {
                }

                updateProgrammeTable();
                prgTable.clearSelection();
            }
        });

	/*
	 * -------------------------- BUTTON LISTENER --------------------------
	 */
        prgActivateButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                            if (RYCOXv2.prgList.get(i).getPrgStatus().equalsIgnoreCase("INACTIVE")) {

                                int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Programme '" + RYCOXv2.prgList.get(i).getProgCode() + "'?");
                                if (opt == JOptionPane.YES_OPTION) {
                                    RYCOXv2.prgList.get(i).setPrgStatus("ACTIVE");
                                    JOptionPane.showMessageDialog(null, "Programme " + RYCOXv2.prgList.get(i).getProgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updateProgrammeTable();
                                    prgTable.clearSelection();
                                    prgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    prgActivateButton.setEnabled(true);
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


        prgDeactButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                    for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                        if (ID.equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                            if (RYCOXv2.prgList.get(i).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
                                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Programme " + RYCOXv2.prgList.get(i).getProgCode() + " ?", "Programme Code found!", JOptionPane.WARNING_MESSAGE);
                                if (choice == JOptionPane.YES_OPTION) {
                                    RYCOXv2.prgList.get(i).setPrgStatus("INACTIVE");
                                    JOptionPane.showMessageDialog(null, "Programme '" + RYCOXv2.prgList.get(i).getProgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                                    updateProgrammeTable();
                                    prgTable.clearSelection();
                                    prgDeactButton.setEnabled(true);
                                    deactivateMI.setEnabled(true);
                                    prgActivateButton.setEnabled(true);
                                    activateMI.setEnabled(true);
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
                    LogFile log = new LogFile(RYCOXv2.user, "has viewed a TV Programme '" + progtemp[0] + "'.");
                    RYCOXv2.logList.add(log);
                    new ViewProgrammeDialog((JFrame) popupMenu.getParent());
                    prgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        prgAddButton.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                NewProgrammeDialog npd = new NewProgrammeDialog((JFrame) popupMenu.getParent());
                updateProgrammeTable();
                prgTable.clearSelection();
            }
        });

        saveButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    FileOutputStream prg_fostream = new FileOutputStream("prg_data.rycox");
                    ObjectOutputStream prg_oostream = new ObjectOutputStream(prg_fostream);
                    for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                        if (RYCOXv2.prgList.get(i) != null) {
                            prg_oostream.writeObject(RYCOXv2.prgList);
                        }
                    }
                    prg_oostream.flush();
                    prg_oostream.close();
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[PROGRAMME]");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                    //					JOptionPane.showMessageDialog(null, "You have successfully saved the changes of TV Programmes !", "Save successfully", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        editPrgButton.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {
                try {
                    EditProgrammeDialog epd = new EditProgrammeDialog((JFrame) popupMenu.getParent());
                    updateProgrammeTable();
                    prgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        prgDeleteButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Programme " + progtemp[0] + " ?", "Programme Code found!", JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        boolean flag = false;
                        for (int check = 0; check < RYCOXv2.pckgingList.size(); check++) {

                            if (progtemp[0].equalsIgnoreCase(RYCOXv2.pckgingList.get(check).getProgCode())) {
                                JOptionPane.showMessageDialog(null, "The programme is included in one or more packages. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                                flag = false;
                                break;
                            } else {
                                flag = true;
                            }
                        }


                        if (flag == true) {
                            for (int counts = 0; counts < RYCOXv2.prgList.size(); counts++) {
                                if (progtemp[0].equalsIgnoreCase(RYCOXv2.prgList.get(counts).getProgCode())) {
                                    if ((RYCOXv2.prgList.get(counts).getPrgStatus().equalsIgnoreCase("ACTIVE")) || (RYCOXv2.prgList.get(counts).getPrgStatus().equalsIgnoreCase("INACTIVE"))) {

                                        RYCOXv2.prgList.get(counts).setPrgStatus("TERMINATED");
                                        String date = DateFormat.getInstance().format(new Date());
                                        RYCOXv2.prgList.get(counts).setTerminationDate(date);
                                        JOptionPane.showMessageDialog(null, "TV Programme " + progtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                        LogFile log = new LogFile(RYCOXv2.user, "has terminated a TV Programme '" + progtemp[0] + "'.");
                                        RYCOXv2.logList.add(log);
                                        break;

                                    }


                                }
                            }
                        }

                    } else {
                    }

                    updateProgrammeTable();
                    prgTable.clearSelection();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

    }//end constructor

    public void updateProgrammeTable() {
        prgData = new String[RYCOXv2.prgList.size()][6];
        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            for (int j = 0; j <= 6; j++) {
                switch (j) {
                    case 0:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getProgCode();
                        break;
                    case 1:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getProgTitle();
                        break;
                    case 2:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getContentOrigin();
                        break;
                    case 3:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getViewerStatus();
                        break;
                    case 4:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getType();
                        break;
                    case 5:
                        prgData[i][j] = RYCOXv2.prgList.get(i).getPrgStatus();
                        break;
                }
            }
        }

        prgModel = new DefaultTableModel(
                prgData,
                new String[]{
                        "Programme Code", "Programme Title", "Content Origin", "Viewer Status", "Type", "Programme Status"
                }) {

            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,};
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
        prgTable.setModel(prgModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(prgModel);
        prgTable.setRowSorter(sorter);
        prgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        prgTable.setName("");
        prgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(prgTable);
    }

    private void changeMode() {
        rowno = prgTable.getSelectedRow();
        String ID = (String) prgTable.getValueAt(rowno, 0);

        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            if (ID.equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                lol = i;
                if (RYCOXv2.prgList.get(lol).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
                    prgDeleteButton.setEnabled(true);
                    editPrgButton.setEnabled(true);
                    prgActivateButton.setEnabled(false);
                    prgDeactButton.setEnabled(true);
                    deletePrgMI.setEnabled(true);
                    editPrgMI.setEnabled(true);
                    activateMI.setEnabled(false);
                    deactivateMI.setEnabled(true);
                    deletePrgMI.setEnabled(true);
                    viewButton.setEnabled(true);
                    prgDeleteButton.setVisible(true);
                    editPrgButton.setVisible(true);
                    prgActivateButton.setVisible(true);
                    prgDeactButton.setVisible(true);
                    editPrgMI.setVisible(true);
                    activateMI.setVisible(true);
                    deactivateMI.setVisible(true);
                    deletePrgMI.setVisible(true);
                } else if (RYCOXv2.prgList.get(lol).getPrgStatus().equalsIgnoreCase("INACTIVE")) {
                    prgDeleteButton.setEnabled(true);
                    editPrgButton.setEnabled(true);
                    prgActivateButton.setEnabled(true);
                    prgDeactButton.setEnabled(false);
                    deletePrgMI.setEnabled(true);
                    editPrgMI.setEnabled(true);
                    activateMI.setEnabled(true);
                    deactivateMI.setEnabled(false);
                    deletePrgMI.setEnabled(true);
                    viewButton.setEnabled(true);
                    prgDeleteButton.setVisible(true);
                    editPrgButton.setVisible(true);
                    prgActivateButton.setVisible(true);
                    prgDeactButton.setVisible(true);
                    editPrgMI.setVisible(true);
                    activateMI.setVisible(true);
                    deactivateMI.setVisible(true);
                    deletePrgMI.setVisible(true);
                } else if (RYCOXv2.prgList.get(lol).getPrgStatus().equalsIgnoreCase("TERMINATED")) {
                    prgDeleteButton.setVisible(false);
                    editPrgButton.setVisible(false);
                    prgActivateButton.setVisible(false);
                    prgDeactButton.setVisible(false);
                    deletePrgMI.setEnabled(false);
                    editPrgMI.setVisible(false);
                    activateMI.setVisible(false);
                    deactivateMI.setVisible(false);
                    deletePrgMI.setVisible(false);
                    viewButton.setEnabled(true);
                }
            }
        }
    }

    static void defaultButtonSet() {
        editPrgButton.setEnabled(false);
        viewButton.setEnabled(false);
        prgDeleteButton.setEnabled(false);
        prgActivateButton.setEnabled(false);
        prgDeactButton.setEnabled(false);
    }
}
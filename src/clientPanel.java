import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author RYNE
 */

@SuppressWarnings("serial")
class ClientPanel extends JPanel {

    private static JTable clTable;
    @SuppressWarnings("rawtypes")
    // Variables declaration
    private JComboBox clTypeCombo;
    private ToolbarButton cldeleteButton;
    private ToolbarButton editclButton;
    private JScrollPane scrollPane;
    private ToolbarButton newclButton;
    private ToolbarButton saveButton;
    private JPanel toolbar;
    private ToolbarButton recoverButton;
    private ToolbarButton viewButton;
    private ToolbarButton deactButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JMenuItem editclMI, deleteclMI, addservMI, activateMI, viewMI, deactivateMI;
    private ToolbarButton clActivateButton;
    private static String[][] clData;
    private static AbstractTableModel model;
    static String[] editionData;
    static int row;
    static int editing;
    private JPopupMenu popupMenu;
    private TableRowSorter<TableModel> sorter;
    // End of variables declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ClientPanel() {
        setBackground(bColor);
        toolbar = new JPanel();
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));
        newclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        editclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        saveButton = new ToolbarButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        viewButton = new ToolbarButton("", new ImageIcon(getClass().getResource("viewbutton.png")));
        cldeleteButton = new ToolbarButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        cldeleteButton.setVisible(true);
        recoverButton = new ToolbarButton("", new ImageIcon(getClass().getResource("recoverbutton.png")));
        recoverButton.setVisible(true);
        clActivateButton = new ToolbarButton("", new ImageIcon(getClass().getResource("activatebutton.png")));
        deactButton = new ToolbarButton("", new ImageIcon(getClass().getResource("deactivatebutton.png")));
        scrollPane = new JScrollPane();
        clTypeCombo = new JComboBox();
        clTable = new JTable();
        clTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel();
        loginInfo.setText("You are logged in as : " + RYCOXv2.user);
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));

        editclButton.setEnabled(false);
        viewButton.setEnabled(false);
        cldeleteButton.setEnabled(false);
        recoverButton.setEnabled(false);
        clActivateButton.setEnabled(false);
        deactButton.setEnabled(false);

        clTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"All", "Individual", "Government", "Private Organisation", "NGO"}));

        if (RYCOXv2.userList.get(RYCOXv2.currentUser) instanceof Administrators) {
            cldeleteButton.setVisible(true);
            recoverButton.setVisible(true);
        } else {
            cldeleteButton.setVisible(false);
            recoverButton.setVisible(false);
        }
        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(newclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(editclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(cldeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(recoverButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(clActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(deactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                .addComponent(clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newclButton)
                                        .addComponent(saveButton)
                                        .addComponent(editclButton)
                                        .addComponent(clActivateButton)
                                        .addComponent(deactButton)
                                        .addComponent(viewButton)
                                        .addComponent(recoverButton)
                                        .addComponent(loginInfo)
                                        .addComponent(cldeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clTable.setBackground(new Color(227, 226, 226));
        clTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12));
        int count = RYCOXv2.clientList.size();

        clData = new String[count][4];
        int next = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            for (int j = 0; j <= 4; j++) {
                switch (j) {
                    case 0:
                        clData[next][j] = RYCOXv2.clientList.get(i).getClientID();
                        break;
                    case 1:
                        clData[next][j] = RYCOXv2.clientList.get(i).getName();
                        break;
                    case 2:
                        clData[next][j] = RYCOXv2.clientList.get(i).getBillingAddress();
                        break;
                    case 3:
                        clData[next][j] = RYCOXv2.clientList.get(i).getAccountStatus();
                        break;
                }
            }
            next++;
        }

        model = new DefaultTableModel(
                clData,
                new String[]{
                        "Client ID", "Name", "Address", "Account Status"
                }) {

            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        clTable.setModel(model);
        sorter = new TableRowSorter<TableModel>(model);
        clTable.setRowSorter(sorter);
        clTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        clTable.setName("");
        clTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(clTable);

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

        /////////*-------------------------- MOUSE LISTENER --------------------------*////////
        clTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = clTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    storeData();
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = clTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    storeData();
                    showPopup(e);
                }
            }

            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = clTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    storeData();
                }

                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = clTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    storeData();
                    showPopup(e);
                }
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
        ); //table click rows
        /*-------------------------- MOUSE LISTENER --------------------------*/

        //////////*-------------------------- DIALOG LISTENER --------------------------*//////////
        DialogHandler dialoghandler = new DialogHandler();
        newclButton.addActionListener(dialoghandler);
        saveButton.addActionListener(dialoghandler);
        editclButton.addActionListener(dialoghandler);
        cldeleteButton.addActionListener(dialoghandler);
        viewButton.addActionListener(dialoghandler);
        recoverButton.addActionListener(dialoghandler);
        clActivateButton.addActionListener(dialoghandler);
        deactButton.addActionListener(dialoghandler);
		/*-------------------------- DIALOG LISTENER --------------------------*/

        /////////*------------------------------POP UP MENU-------------------------*//////////
        viewMI = new JMenuItem("View Client...");
        viewMI.addActionListener(dialoghandler);
        editclMI = new JMenuItem("Edit Client...");
        editclMI.addActionListener(dialoghandler);
        deleteclMI = new JMenuItem("Terminate Client...");
        deleteclMI.addActionListener(dialoghandler);
        activateMI = new JMenuItem("Activate client...");
        activateMI.addActionListener(dialoghandler);
        deactivateMI = new JMenuItem("Deactivate client...");
        deactivateMI.addActionListener(dialoghandler);
        addservMI = new JMenuItem("Add Service...");
        addservMI.addActionListener(dialoghandler);
        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(viewMI);
        popupMenu.add(editclMI);
        popupMenu.add(deleteclMI);
        popupMenu.add(activateMI);
        popupMenu.add(deactivateMI);
        popupMenu.add(addservMI);
		/*-----------------------------------------------------------------------*/
        ChangeView aListener = new ChangeView();
        clTypeCombo.addItemListener(aListener);
    }//end constructor

    /**
     * This method is called from the dialog when user finish doing
     * input and new data is to be updated.
     */
    static void updateAddTable() {
        int n = RYCOXv2.clientList.size() - 1;
        String[] a = {RYCOXv2.clientList.get(n).getClientID(), RYCOXv2.clientList.get(n).getName(), RYCOXv2.clientList.get(n).getBillingAddress(), RYCOXv2.clientList.get(n).getAccountStatus()};
        ((DefaultTableModel) model).addRow(a);
        clTable.tableChanged(new TableModelEvent(model));
        clTable.setModel(model);
        clTable.repaint();
    }
	/*--- START DIALOG HANDLER*/


    /**
     * @author ANDRE
     *         This class is to handle all the pressed buttons and returns a dialog if necessary.
     *         Other actions are handled independently
     */
    private class DialogHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newclButton) {
                NewClientDialog ncd = new NewClientDialog((JFrame) popupMenu.getParent());
                ncd.setVisible(true);
            } else if (e.getSource() == saveButton) {
                try {
                    FileOutputStream client_fostream = new FileOutputStream("cl_data.rycox");
                    ObjectOutputStream client_oostream = new ObjectOutputStream(client_fostream);
                    for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                        if (RYCOXv2.clientList.get(i) != null) {
                            client_oostream.writeObject(RYCOXv2.clientList);
                        }
                    }

                    client_oostream.flush();
                    client_oostream.close();
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[CLIENT]");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    FileOutputStream FOS = new FileOutputStream("serv_data.rycox");
                    ObjectOutputStream OOS = new ObjectOutputStream(FOS);
                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if (RYCOXv2.servList.get(i) != null) {
                            OOS.writeObject(RYCOXv2.servList);
                        }
                    }
                    OOS.flush();
                    OOS.close();
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[SERVICE]");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == editclButton || e.getSource() == editclMI) {
                EditClientDialog ecd = new EditClientDialog((JFrame) popupMenu.getParent());
                ecd.setVisible(true);

            } else if (e.getSource() == cldeleteButton || e.getSource() == deleteclMI) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Terminate client " + editionData[0] + "?", "Termination", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    RYCOXv2.clientList.get(editing).setAccountStatus("TERMINATED");
                    updateEditTable();
                    clTable.clearSelection();
                    deactButton.setEnabled(true);
                    deactivateMI.setEnabled(true);
                    clActivateButton.setEnabled(true);
                    activateMI.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Client '" + RYCOXv2.clientList.get(editing).getClientID() + "' is now terminated.", "RYCOX System - Termination Successful", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == recoverButton) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Recover client " + editionData[0] + "?", "Recovery", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    RYCOXv2.clientList.get(editing).setAccountStatus("ACTIVE");
                    updateEditTable();
                    clTable.clearSelection();
                    deactButton.setEnabled(true);
                    deactivateMI.setEnabled(true);
                    clActivateButton.setEnabled(true);
                    activateMI.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Client '" + RYCOXv2.clientList.get(editing).getClientID() + "' is now recovered and activated.", "RYCOX System - Recovery Successful", JOptionPane.INFORMATION_MESSAGE);
                }

            } else if (e.getSource() == viewButton || e.getSource() == viewMI) {
                ViewClientDialog vcd = new ViewClientDialog((JFrame) popupMenu.getParent());
                vcd.setVisible(true);

            } else if (e.getSource() == clActivateButton || e.getSource() == activateMI) {
                String ID = (String) clTable.getValueAt(clTable.getSelectedRow(), 0);
                for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                        if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Inactive")) {
                            RYCOXv2.clientList.get(i).setAccountStatus("ACTIVE");
                            int opt = JOptionPane.showConfirmDialog(null, "Reactivate client '" + RYCOXv2.clientList.get(i).getClientID() + "'?");
                            if (opt == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(null, "Client " + RYCOXv2.clientList.get(i).getClientID() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                updateEditTable();
                                clTable.clearSelection();
                                deactButton.setEnabled(true);
                                deactivateMI.setEnabled(true);
                                clActivateButton.setEnabled(true);
                                activateMI.setEnabled(true);
                                break;
                            } else if (opt == JOptionPane.NO_OPTION) {
                                break;
                            }
                        }
                    }
                }
            } else if (e.getSource() == deactButton || e.getSource() == deactivateMI) {
                String ID = (String) clTable.getValueAt(clTable.getSelectedRow(), 0);
                for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                        if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Active")) {
                            RYCOXv2.clientList.get(i).setAccountStatus("INACTIVE");
                            JOptionPane.showMessageDialog(null, "Client '" + RYCOXv2.clientList.get(i).getClientID() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
                updateEditTable();
                clTable.clearSelection();
                deactButton.setEnabled(true);
                deactivateMI.setEnabled(true);
                clActivateButton.setEnabled(true);
                activateMI.setEnabled(true);
            } else if (e.getSource() == addservMI) {
                NewServiceDialog n = new NewServiceDialog((JFrame) popupMenu.getParent());
                n.setVisible(true);
            }
        }
    }
	/*---END DIALOG HANDLER*/

	/*--START ITEM HANDLER*/

    /**
     * @author ANDRE
     *         This class is created to handle the filtering of the JComboBox
     *         with the view of the JTable.
     */
    private class ChangeView implements ItemListener {

        public void itemStateChanged(ItemEvent e) {
            if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("All")) {
                sorter.setRowFilter(RowFilter.regexFilter("[IiGgNnPp][0-9]{6}"));
                sorter.setSortKeys(null);
            } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Individual")) {
                sorter.setRowFilter(RowFilter.regexFilter("[Ii][0-9]{6}"));
                sorter.setSortKeys(null);
            } else if (((String) clTypeCombo.getSelectedItem()).equals("Government")) {
                sorter.setRowFilter(RowFilter.regexFilter("[Gg][0-9]{6}"));
                sorter.setSortKeys(null);
            } else if (((String) clTypeCombo.getSelectedItem()).equals("Private Organisation")) {
                sorter.setRowFilter(RowFilter.regexFilter("[Nn][0-9]{6}"));
                sorter.setSortKeys(null);
            } else if (((String) clTypeCombo.getSelectedItem()).equals("NGO")) {
                sorter.setRowFilter(RowFilter.regexFilter("[Pp][0-9]{6}"));
                sorter.setSortKeys(null);
            }
        }
    }
	/*--END ITEM HANDLER*/

    /**
     * This method is to update the
     * JTable in the view after editing.
     */
    public static void updateEditTable() {
        int count = RYCOXv2.clientList.size();

        clData = new String[count][4];
        int next = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            for (int j = 0; j <= 4; j++) {
                switch (j) {
                    case 0:
                        clData[next][j] = RYCOXv2.clientList.get(i).getClientID();
                        break;
                    case 1:
                        clData[next][j] = RYCOXv2.clientList.get(i).getName();
                        break;
                    case 2:
                        clData[next][j] = RYCOXv2.clientList.get(i).getBillingAddress();
                        break;
                    case 3:
                        clData[next][j] = RYCOXv2.clientList.get(i).getAccountStatus();
                        break;
                }
            }
            next++;
        }

        model = new DefaultTableModel(
                clData,
                new String[]{
                        "Client ID", "Name", "Address", "Account Status"
                }) {

            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            @SuppressWarnings({"unchecked", "rawtypes"})
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        clTable.setModel(model);
        clTable.revalidate();
        clTable.repaint();
        clTable.clearSelection();
    }

    /**
     * This method is called when a row is selected. The corresponding
     * data will be stored in an array and declared static to allow access
     * within the same package.
     */
    private void storeData() {
        row = clTable.getSelectedRow();
        String ID = (String) clTable.getValueAt(row, 0);

        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                editing = i;
                if (ID.matches("[Ii][0-9]{6}")) {
                    editionData = new String[]{RYCOXv2.clientList.get(i).getClientID(), RYCOXv2.clientList.get(i).getName(), RYCOXv2.clientList.get(i).getAccountStatus(), RYCOXv2.clientList.get(i).getBillingAddress(), RYCOXv2.clientList.get(i).getEmail(), Integer.toString(((IndividualClient) RYCOXv2.clientList.get(i)).getAge()), ((IndividualClient) RYCOXv2.clientList.get(i)).getIC()};
                    //ID, NAME, ACCOUNTSTATUS, BILLING ADDRESS, EMAIL, AGE,IC
                } else {
                    editionData = new String[]{RYCOXv2.clientList.get(i).getClientID(), RYCOXv2.clientList.get(i).getName(), RYCOXv2.clientList.get(i).getAccountStatus(), RYCOXv2.clientList.get(i).getBillingAddress(), RYCOXv2.clientList.get(i).getEmail(), "N/A", "N/A"};
                    //ID, NAME, ACCOUNTSTATUS, BILLING ADDRESS, EMAIL,N/A,N/A
                }
                if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Active")) {
                    deactButton.setEnabled(true);
                    deactivateMI.setEnabled(true);
                    clActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                    editclButton.setEnabled(true);
                    editclMI.setEnabled(false);
                    recoverButton.setEnabled(false);
                    cldeleteButton.setEnabled(true);
                    viewButton.setEnabled(true);
                } else if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Inactive")) {
                    deactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    clActivateButton.setEnabled(true);
                    activateMI.setEnabled(true);
                    editclButton.setEnabled(true);
                    editclMI.setEnabled(true);
                    recoverButton.setEnabled(false);
                    cldeleteButton.setEnabled(true);
                    viewButton.setEnabled(true);
                } else if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Terminated")) {
                    deactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    clActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                    editclButton.setEnabled(false);
                    editclMI.setEnabled(false);
                    recoverButton.setEnabled(true);
                    cldeleteButton.setEnabled(false);
                    viewButton.setEnabled(true);
                }
            }
        }
    }

    /**
     * This class is called by JButtons that appears above the toolbar.
     * It takes in the parameter of a string, with an ImageIcon.
     */
    private class ToolbarButton extends JButton {
        public ToolbarButton(String string, ImageIcon imageIcon) {
            super("", imageIcon);
            this.setBackground(bColor);
        }
    }
}
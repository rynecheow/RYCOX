//~--- JDK imports ------------------------------------------------------------

import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author RYNE
 */
@SuppressWarnings("serial")
class ClientPanel extends JPanel {
    private static JTable clTable;
    private static ToolbarButton cldeleteButton;
    private static ToolbarButton editclButton;
    private static ToolbarButton addServButton;
    private static ToolbarButton recoverButton;
    private static ToolbarButton viewButton;
    private static ToolbarButton deactButton;
    private static ToolbarButton clActivateButton;
    private static String[][] clData;
    private static AbstractTableModel model;
    static String[] editionData;
    static int row;
    static int editing;
    private Color bColor = new Color(23, 28, 30);
    @SuppressWarnings("rawtypes")

    // Variables declaration
    private JComboBox clTypeCombo;
    private JScrollPane scrollPane;
    private ToolbarButton newclButton;
    private ToolbarButton saveButton;
    private JPanel toolbar;
    private JLabel loginInfo;
    private JMenuItem recoverMI, editclMI, deleteclMI, addservMI, activateMI, viewMI, deactivateMI;
    private JPopupMenu popupMenu;
    private TableRowSorter<TableModel> sorter;
    private JTextField searchbox;
    private JLabel searchLabel;

    // End of variables declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ClientPanel() {
        setBackground(bColor);
        toolbar = new JPanel();
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));
        newclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/newbutton.png")));
        editclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/editbutton.png")));
        saveButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/savebutton.png")));
        viewButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/viewbutton.png")));
        cldeleteButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/deletebutton.png")));
        cldeleteButton.setVisible(true);
        recoverButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/recoverbutton.png")));
        recoverButton.setVisible(true);
        clActivateButton = new ToolbarButton("",
                new ImageIcon(getClass().getResource("/resources/activatebutton.png")));
        deactButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/deactivatebutton.png")));
        addServButton = new ToolbarButton("", new ImageIcon(getClass().getResource("/resources/addservice.png")));
        scrollPane = new JScrollPane();
        clTypeCombo = new JComboBox();
        clTable = new JTable();
        clTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel();
        loginInfo.setText("You are logged in as : " + RYCOXv2.user);
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));
        viewMI = new JMenuItem("View Client...");
        editclMI = new JMenuItem("Edit Client...");
        deleteclMI = new JMenuItem("Terminate Client...");
        activateMI = new JMenuItem("Activate Client...");
        deactivateMI = new JMenuItem("Deactivate Client...");
        addservMI = new JMenuItem("Add Service...");
        recoverMI = new JMenuItem("Recover Client...");
        popupMenu = new JPopupMenu("Menu");
        searchbox = new JTextField(16);
        searchbox.setForeground(Color.BLACK);
        searchbox.setText("");
        searchLabel = new JLabel();
        searchLabel.setText("Search:");
        searchLabel.setForeground(Color.WHITE);
        defaultButtonSet();
        clTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"All", "Individual", "Government",
                "Private Organisation", "NGO"}));

        if (RYCOXv2.userList.get(RYCOXv2.currentUser) instanceof Administrators) {
            cldeleteButton.setVisible(true);
            recoverMI.setVisible(false);
            deleteclMI.setVisible(true);
            recoverButton.setVisible(false);
        } else {
            cldeleteButton.setVisible(false);
            recoverButton.setVisible(false);
            recoverMI.setVisible(false);
            deleteclMI.setVisible(false);
        }

        GroupLayout toolbarLayout = new GroupLayout(toolbar);

        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(

                // toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup().addContainerGap().addComponent(newclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(addServButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(editclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(cldeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(recoverButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(clActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(deactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE).addComponent(clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap()));
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                        GroupLayout.Alignment.TRAILING,
                        toolbarLayout.createSequentialGroup().addContainerGap().addComponent(newclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).
                                addPreferredGap(ComponentPlacement.UNRELATED).addComponent(addServButton, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                        ComponentPlacement.UNRELATED)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(editclButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(cldeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
                                recoverButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                ComponentPlacement.UNRELATED).addComponent(
                                clActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                ComponentPlacement.UNRELATED).addComponent(
                                deactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                ComponentPlacement.UNRELATED).addComponent(
                                loginInfo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE).addPreferredGap(
                                ComponentPlacement.RELATED, 109, Short.MAX_VALUE).addComponent(
                                clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
                                searchLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(
                                searchbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE).addContainerGap()
                )
        );
        toolbarLayout.setVerticalGroup(

                // toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(toolbarLayout.createSequentialGroup().addContainerGap().addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(newclButton).addComponent(saveButton).addComponent(editclButton).addComponent(addServButton).addComponent(clActivateButton).addComponent(deactButton).addComponent(viewButton).addComponent(recoverButton).addComponent(loginInfo).addComponent(cldeleteButton)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                        toolbarLayout.createSequentialGroup().addContainerGap().addGroup(
                                toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(
                                        clTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE).addComponent(newclButton).addComponent(saveButton).addComponent(
                                        editclButton).addComponent(addServButton).addComponent(clActivateButton).addComponent(
                                        deactButton).addComponent(viewButton).addComponent(recoverButton).addComponent(
                                        loginInfo).addComponent(cldeleteButton).addComponent(searchLabel).addComponent(
                                        searchbox)
                        ).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                )
        );
        clTable.setBackground(new Color(227, 226, 226));
        clTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12));

        int count = RYCOXv2.clientList.size();

        clData = new String[count][5];

        int next = 0;

        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            for (int j = 0; j <= 5; j++) {
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
                        clData[next][j] = RYCOXv2.clientList.get(i).getEmail();

                        break;

                    case 4:
                        clData[next][j] = RYCOXv2.clientList.get(i).getAccountStatus();

                        break;
                }
            }

            next++;
        }

        model = new DefaultTableModel(clData, new String[]{"Client ID", "Name", "Address", "E-mail address",
                "Account Status"}) {
            Class[] types = new Class[]{java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class, java.lang.String.class};
            boolean[] canEdit = new boolean[]{false, false, false, false, false};

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
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
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(toolbar,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE).addGroup(layout.createSequentialGroup().addContainerGap().addComponent(scrollPane,
                GroupLayout.DEFAULT_SIZE, 1480, Short.MAX_VALUE).addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
                        layout.createSequentialGroup().addComponent(
                                toolbar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE).addGap(
                                18, 18, 18).addComponent(
                                scrollPane, GroupLayout.DEFAULT_SIZE, 779, Short.MAX_VALUE).addContainerGap()
                )
        );

        /////////*-------------------------- MOUSE LISTENER --------------------------*////////
        clTable.addMouseListener(new MouseAdapter() {
            @Override
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

            @Override
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
        });    // table click rows

        /*
         * -------------------------- MOUSE LISTENER --------------------------
         */

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
        addServButton.addActionListener(dialoghandler);

        /*
         * -------------------------- DIALOG LISTENER --------------------------
         */

        /////////*------------------------------POP UP MENU-------------------------*//////////
        viewMI.addActionListener(dialoghandler);
        editclMI.addActionListener(dialoghandler);
        deleteclMI.addActionListener(dialoghandler);
        activateMI.addActionListener(dialoghandler);
        deactivateMI.addActionListener(dialoghandler);
        addservMI.addActionListener(dialoghandler);
        recoverMI.addActionListener(dialoghandler);
        popupMenu.add(viewMI);
        popupMenu.add(editclMI);
        popupMenu.add(deleteclMI);
        popupMenu.add(activateMI);
        popupMenu.add(deactivateMI);
        popupMenu.add(addservMI);
        popupMenu.add(recoverMI);

        /*
         * -----------------------------------------------------------------------
         */
        ChangeView aListener = new ChangeView();

        clTypeCombo.addItemListener(aListener);
        searchbox.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                try {
                    if (!"".equals(searchbox.getText())) {
                        sorter.setRowFilter(RowFilter.regexFilter(searchbox.getText()));
                        sorter.setSortKeys(null);
                    } else {
                        sorter.setRowFilter(RowFilter.regexFilter("[IiGgNnPp][0-9]{6}"));
                        sorter.setSortKeys(null);
                    }
                } catch (Exception ex) {
                }

//              System.out.println(searchbox.getText());
                repaint();
            }
        });
    }    // end constructor

    /**
     * This method is called from the dialog when user finish doing input and
     * new data is to be updated.
     */
    static void updateAddTable() {
        int n = RYCOXv2.clientList.size() - 1;
        String[] a = {RYCOXv2.clientList.get(n).getClientID(), RYCOXv2.clientList.get(n).getName(),
                RYCOXv2.clientList.get(n).getBillingAddress(), RYCOXv2.clientList.get(n).getEmail(),
                RYCOXv2.clientList.get(n).getAccountStatus()};

        ((DefaultTableModel) model).addRow(a);
        clTable.tableChanged(new TableModelEvent(model));
        clTable.setModel(model);
        clTable.repaint();
    }

    /*
     * --- START DIALOG HANDLER
     */

    /*
     * --START ITEM HANDLER
     */

    /**
     * @author ANDRE This class is created to handle the filtering of the
     *         JComboBox with the view of the JTable.
     */
    private class ChangeView implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            switch ((String) clTypeCombo.getSelectedItem()) {
                case "All":
                    sorter.setRowFilter(RowFilter.regexFilter("[IiGgNnPp][0-9]{6}"));
                    sorter.setSortKeys(null);

                    break;

                case "Individual":
                    sorter.setRowFilter(RowFilter.regexFilter("[Ii][0-9]{6}"));
                    sorter.setSortKeys(null);

                    break;

                case "Government":
                    sorter.setRowFilter(RowFilter.regexFilter("[Gg][0-9]{6}"));
                    sorter.setSortKeys(null);

                    break;

                case "Private Organisation":
                    sorter.setRowFilter(RowFilter.regexFilter("[Nn][0-9]{6}"));
                    sorter.setSortKeys(null);

                    break;

                case "NGO":
                    sorter.setRowFilter(RowFilter.regexFilter("[Pp][0-9]{6}"));
                    sorter.setSortKeys(null);

                    break;
            }
        }
    }


    /**
     * @author ANDRE This class is to handle all the pressed buttons and returns
     *         a dialog if necessary. Other actions are handled independently
     */
    private class DialogHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == newclButton) {
                NewClientDialog ncd = new NewClientDialog((JFrame) popupMenu.getParent());

                ncd.setVisible(true);
            } else if (e.getSource() == saveButton) {
                RYCOXv2.saveClientFile();
                RYCOXv2.saveServiceFile();
                JOptionPane.showMessageDialog(null,
                        "You have saved all the changes under client management successfully.",
                        "Saved successfully", JOptionPane.INFORMATION_MESSAGE);
            } else if ((e.getSource() == editclButton) || (e.getSource() == editclMI)) {
                EditClientDialog ecd = new EditClientDialog((JFrame) popupMenu.getParent());

                ecd.setVisible(true);
            } else if ((e.getSource() == cldeleteButton) || (e.getSource() == deleteclMI)) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Terminate client " + editionData[0] + "?",
                        "Termination", JOptionPane.WARNING_MESSAGE);

                if (closeCf == JOptionPane.YES_OPTION) {
                    RYCOXv2.clientList.get(editing).setAccountStatus("TERMINATED");

                    String date = DateFormat.getInstance().format(new Date());

                    RYCOXv2.clientList.get(editing).setTerminationDate(date);
                    updateEditTable();
                    clTable.clearSelection();

//                  deactButton.setEnabled(false);
//                  deactivateMI.setEnabled(false);
//                  clActivateButton.setEnabled(false);
//                  activateMI.setEnabled(false);
                    defaultButtonSet();

                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if ((RYCOXv2.servList.get(i).getClientID()).equals(
                                RYCOXv2.clientList.get(editing).getClientID())) {
                            (RYCOXv2.servList.get(i)).setServStatus("TERMINATED");
                            ServicePanel.updateTable();
                        }
                    }

                    JOptionPane.showMessageDialog(null,
                            "Client '" + RYCOXv2.clientList.get(editing).getClientID()
                                    + "' is now terminated.", "RYCOX System - Termination Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } else if ((e.getSource() == recoverButton) || (e.getSource() == recoverMI)) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Recover client " + editionData[0] + "?", "Recovery",
                        JOptionPane.WARNING_MESSAGE);

                if (closeCf == JOptionPane.YES_OPTION) {
                    RYCOXv2.clientList.get(editing).setAccountStatus("ACTIVE");
                    RYCOXv2.clientList.get(editing).setTerminationDate("N/A");
                    updateEditTable();
                    clTable.clearSelection();

//                  deactButton.setEnabled(true);
//                  deactivateMI.setEnabled(true);
//                  clActivateButton.setEnabled(true);
//                  activateMI.setEnabled(true);
                    defaultButtonSet();
                    JOptionPane.showMessageDialog(
                            null,
                            "Client '" + RYCOXv2.clientList.get(editing).getClientID()
                                    + "' is now recovered and activated.", "RYCOX System - Recovery Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } else if ((e.getSource() == viewButton) || (e.getSource() == viewMI)) {
                ViewClientDialog vcd = new ViewClientDialog((JFrame) popupMenu.getParent());

                vcd.setVisible(true);
            } else if ((e.getSource() == clActivateButton) || (e.getSource() == activateMI)) {
                String ID = (String) clTable.getValueAt(clTable.getSelectedRow(), 0);

                for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                        if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Inactive")) {
                            RYCOXv2.clientList.get(i).setAccountStatus("ACTIVE");

                            int opt = JOptionPane.showConfirmDialog(null,
                                    "Reactivate client '" + RYCOXv2.clientList.get(i).getClientID() + "'?");

                            if (opt == JOptionPane.YES_OPTION) {
                                JOptionPane.showMessageDialog(
                                        null, "Client " + RYCOXv2.clientList.get(i).getClientID() + " is now activated.",
                                        "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                updateEditTable();
                                clTable.clearSelection();

//                              deactButton.setEnabled(true);
//                              deactivateMI.setEnabled(true);
//                              clActivateButton.setEnabled(true);
//                              activateMI.setEnabled(true);
                                defaultButtonSet();

                                break;
                            } else if (opt == JOptionPane.NO_OPTION) {
                                break;
                            }
                        }
                    }
                }
            } else if ((e.getSource() == deactButton) || (e.getSource() == deactivateMI)) {
                String ID = (String) clTable.getValueAt(clTable.getSelectedRow(), 0);

                for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                    if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                        if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Active")) {
                            RYCOXv2.clientList.get(i).setAccountStatus("INACTIVE");
                            JOptionPane.showMessageDialog(
                                    null, "Client '" + RYCOXv2.clientList.get(i).getClientID() + "' is now deactivated.",
                                    "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }

                updateEditTable();
                clTable.clearSelection();
                defaultButtonSet();

//              deactButton.setEnabled(true);
//              deactivateMI.setEnabled(true);
//              clActivateButton.setEnabled(true);
//              activateMI.setEnabled(true);
            } else if ((e.getSource() == addservMI) || (e.getSource() == addServButton)) {
                NewServiceDialog n = new NewServiceDialog((JFrame) popupMenu.getParent());

                n.setVisible(true);
            }
        }
    }

    /*
     * ---END DIALOG HANDLER
     */

    /**
     * This method is to update the JTable in the view after editing.
     */
    public static void updateEditTable() {
        int count = RYCOXv2.clientList.size();

        clData = new String[count][5];
        int next = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            for (int j = 0; j <= 5; j++) {
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
                        clData[next][j] = RYCOXv2.clientList.get(i).getEmail();
                        break;
                    case 4:
                        clData[next][j] = RYCOXv2.clientList.get(i).getAccountStatus();
                        break;
                }
            }
            next++;
        }

        model = new DefaultTableModel(
                clData,
                new String[]{
                        "Client ID", "Name", "Address", "E-mail address", "Account Status"
                }
        ) {

            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false
            };

            @SuppressWarnings({"unchecked", "rawtypes"})
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
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
     * This method is called when a row is selected. The corresponding data will
     * be stored in an array and declared static to allow access within the same
     * package.
     */
    private void storeData() {
        row = clTable.getSelectedRow();
        String ID = (String) clTable.getValueAt(row, 0);
//	if (RYCOXv2.userList.get(RYCOXv2.currentUser) instanceof Administrators) {
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (ID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                editing = i;
                if (ID.matches("[Ii][0-9]{6}")) {
                    editionData = new String[]{RYCOXv2.clientList.get(i).getClientID(), RYCOXv2.clientList.get(i).getName(), RYCOXv2.clientList.get(i).getAccountStatus(), RYCOXv2.clientList.get(i).getBillingAddress(), RYCOXv2.clientList.get(i).getEmail(), Integer.toString(((IndividualClient) RYCOXv2.clientList.get(i)).getAge()), ((IndividualClient) RYCOXv2.clientList.get(i)).getIC(), ((IndividualClient) RYCOXv2.clientList.get(i)).getCreationDate(), ((IndividualClient) RYCOXv2.clientList.get(i)).getTerminationDate()};
                    //ID, NAME, ACCOUNTSTATUS, BILLING ADDRESS, EMAIL, AGE,IC,CREATION DATE, TERMINATION DATE
                } else {
                    editionData = new String[]{RYCOXv2.clientList.get(i).getClientID(), RYCOXv2.clientList.get(i).getName(), RYCOXv2.clientList.get(i).getAccountStatus(), RYCOXv2.clientList.get(i).getBillingAddress(), RYCOXv2.clientList.get(i).getEmail(), "N/A", "N/A", RYCOXv2.clientList.get(i).getCreationDate(), RYCOXv2.clientList.get(i).getTerminationDate()};
                    //ID, NAME, ACCOUNTSTATUS, BILLING ADDRESS, EMAIL,N/A,N/A, CREATION DATE, TERMINATION DATE
                }
                addServButton.setEnabled(true);
                if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Active")) {
                    deactButton.setEnabled(true);
                    deactivateMI.setEnabled(true);
                    clActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                    editclButton.setEnabled(true);
                    editclMI.setEnabled(false);
                    recoverButton.setEnabled(false);
                    recoverMI.setEnabled(false);//
                    cldeleteButton.setEnabled(true);
                    deleteclMI.setEnabled(true);//
                    viewButton.setEnabled(true);
                    viewMI.setEnabled(true);
                    addServButton.setEnabled(true);
                    addservMI.setEnabled(true);

                } else if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Inactive")) {
                    deactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    clActivateButton.setEnabled(true);
                    activateMI.setEnabled(true);
                    editclButton.setEnabled(true);
                    editclMI.setEnabled(true);
                    recoverButton.setEnabled(false);
                    recoverMI.setEnabled(false);//
                    cldeleteButton.setEnabled(true);
                    deleteclMI.setEnabled(true);//
                    viewButton.setEnabled(true);
                    viewMI.setEnabled(true);
                    addServButton.setEnabled(false);
                    addservMI.setEnabled(false);
                } else if (RYCOXv2.clientList.get(i).getAccountStatus().equalsIgnoreCase("Terminated")) {
                    deactButton.setEnabled(false);
                    deactivateMI.setEnabled(false);
                    clActivateButton.setEnabled(false);
                    activateMI.setEnabled(false);
                    editclButton.setEnabled(false);
                    editclMI.setEnabled(false);
                    recoverButton.setEnabled(true);
                    recoverMI.setEnabled(true);//
                    cldeleteButton.setEnabled(false);
                    deleteclMI.setEnabled(false);//
                    viewButton.setEnabled(true);
                    viewMI.setEnabled(true);
                    addservMI.setEnabled(false);//
                    addServButton.setEnabled(false);//
                }
            }
        }
//	} else {
//	    cldeleteButton.setVisible(false);
//	    deleteclMI.setVisible(false);
//	    recoverMI.setVisible(false);
//	    recoverButton.setVisible(false);
//	}


    }

    /**
     * This class is called by JButtons that appears above the toolbar. It takes
     * in the parameter of a string, with an ImageIcon.
     */
    private class ToolbarButton extends JButton {

        public ToolbarButton(String string, ImageIcon imageIcon) {
            super("", imageIcon);
            this.setBackground(bColor);
        }
    }

    static void defaultButtonSet() {
        editclButton.setEnabled(false);
        viewButton.setEnabled(false);
        cldeleteButton.setEnabled(false);
        recoverButton.setEnabled(false);
        clActivateButton.setEnabled(false);
        deactButton.setEnabled(false);
        addServButton.setEnabled(false);
    }
}


/*
 * --END ITEM HANDLER
 */


//~ Formatted by Jindent --- http://www.jindent.com

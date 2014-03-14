import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
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

@SuppressWarnings("serial")
class ClientPanel extends JPanel {

    private static JTable clTable;
    @SuppressWarnings("rawtypes")
    private JComboBox clTypeCombo;
    private ToolbarButton cldeleteButton;
    private ToolbarButton editclButton;
    private JScrollPane scrollPane;
    private ToolbarButton newclButton;
    private ToolbarButton saveButton;
    private ToolbarButton redoButton;
    private JPanel toolbar;
    private ToolbarButton undoButton;
    private ToolbarButton recoverButton;
    private ToolbarButton viewButton;
    private ToolbarButton deactButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editclMI, deleteclMI, addservMI;
    private ToolbarButton clActivateButton;
    private static String[][] clData;
    private static AbstractTableModel model;
    static String[] editionData;
    static int row;
    static int editing;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ClientPanel() {
        toolbar = new JPanel();
        clTypeCombo = new JComboBox();
        newclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        editclButton = new ToolbarButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        redoButton = new ToolbarButton("", new ImageIcon(getClass().getResource("redobutton.png")));
        saveButton = new ToolbarButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        viewButton = new ToolbarButton("", new ImageIcon(getClass().getResource("viewbutton.png")));
        undoButton = new ToolbarButton("", new ImageIcon(getClass().getResource("undobutton.png")));
        cldeleteButton = new ToolbarButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        recoverButton = new ToolbarButton("", new ImageIcon(getClass().getResource("recoverbutton.png")));
        clActivateButton = new ToolbarButton("", new ImageIcon(getClass().getResource("activatebutton.png")));
        deactButton = new ToolbarButton("", new ImageIcon(getClass().getResource("deactivatebutton.png")));
        scrollPane = new JScrollPane();
        clTable = new JTable();
        clTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel();
        loginInfo.setText("You are logged in as : " + RYCOXv2.user);
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));

        setBackground(new Color(23, 28, 30));
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));

        clTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"All", "Individual", "Government", "Private Organisation", "NGO"}));
        clTypeCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                //
            }
        }
        );
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
                                .addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(redoButton)
                                        .addComponent(undoButton)
                                        .addComponent(recoverButton)
                                        .addComponent(loginInfo)
                                        .addComponent(cldeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clTable.setBackground(new Color(227, 226, 226));
        clTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12));
        int nonTermCount = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (RYCOXv2.clientList.get(i).terminationStatus() == false) {
                nonTermCount++;
            }
        }
        clData = new String[nonTermCount][4];
        int next = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (RYCOXv2.clientList.get(i).terminationStatus() == false) {
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
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
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

        clTable.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel lsm = clTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    row = clTable.getSelectedRow();
                    System.out.println(row);
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
                        }
                    }

                } else if (SwingUtilities.isRightMouseButton(e)) {

                }
            }
        }
        ); //table right click

        DialogHandler dialoghandler = new DialogHandler();
        newclButton.addActionListener(dialoghandler);
        saveButton.addActionListener(dialoghandler);
        editclButton.addActionListener(dialoghandler);
        cldeleteButton.addActionListener(dialoghandler);
        viewButton.addActionListener(dialoghandler);
        recoverButton.addActionListener(dialoghandler);
        clActivateButton.addActionListener(dialoghandler);
        editclMI = new JMenuItem("Edit Client...");
        deleteclMI = new JMenuItem("Terminate Client...");
        addservMI = new JMenuItem("Add Service...");

        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(editclMI);
        popupMenu.add(deleteclMI);
        popupMenu.add(addservMI);

    }//end constructor

    static void updateAddTable() {
        int size = RYCOXv2.clientList.size();
        int n = size - 1;
        String[] a = {RYCOXv2.clientList.get(n).getClientID(), RYCOXv2.clientList.get(n).getName(), RYCOXv2.clientList.get(n).getBillingAddress(), RYCOXv2.clientList.get(n).getAccountStatus()};
        ((DefaultTableModel) model).addRow(a);
        clTable.tableChanged(new TableModelEvent(model));
        clTable.setModel(model);
        clTable.repaint();
    }

    /*--- START DIALOG HANDLER*/
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
            } else if (e.getSource() == editclButton) {
                EditClientDialog ecd = new EditClientDialog((JFrame) popupMenu.getParent());
                ecd.setVisible(true);
            } else if (e.getSource() == cldeleteButton) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Terminate client " + editionData[0] + "?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    ((DefaultTableModel) model).removeRow(row);
                    RYCOXv2.clientList.get(editing).setAccountStatus("Terminated");
                }
            } else if (e.getSource() == recoverButton) {
                String jop = JOptionPane.showInputDialog(null, "Please input the client ID that you wish to recover:", "RYCOX System-Recover Client", JOptionPane.QUESTION_MESSAGE);
                if (jop.matches("[IiGgNnpP][0-9]{6}")) {
                    for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
                        if (jop.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                            if (RYCOXv2.clientList.get(i).terminationStatus() == true) {
                                RYCOXv2.clientList.get(i).setAccountStatus("Active");
                            } else
                                JOptionPane.showMessageDialog(null, "The account specified is not terminated!", "RYCOX System- Termination Error", JOptionPane.WARNING_MESSAGE);
                        } else
                            JOptionPane.showMessageDialog(null, "The account specified do not exist!", "RYCOX System- Termination Error", JOptionPane.WARNING_MESSAGE);
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Wrong ID format!", "RYCOX System- Termination Error", JOptionPane.WARNING_MESSAGE);

            } else if (e.getSource() == viewButton) {
                ViewClientDialog vcd = new ViewClientDialog((JFrame) popupMenu.getParent());
                vcd.setVisible(true);
            } else if (e.getSource() == clActivateButton) {

            }
        }
    }
    /*---END DIALOG HANDLER*/

    public static void updateEditTable() {
        int nonTermCount = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (RYCOXv2.clientList.get(i).terminationStatus() == false) {
                nonTermCount++;
            }
        }
        clData = new String[nonTermCount][4];
        int next = 0;
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (RYCOXv2.clientList.get(i).terminationStatus() == false) {
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
    }

    private class ToolbarButton extends JButton {
        public ToolbarButton(String string, ImageIcon imageIcon) {
            super(string, imageIcon);
            this.setBackground(bColor);
        }

    }
}
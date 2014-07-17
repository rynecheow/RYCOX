import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author ryne
 */
@SuppressWarnings("serial")
public class ManageUsers extends JDialog implements ActionListener {

    // Variables declaration - do not modify
    private JButton addNewUserButton;
    private JButton deleteUserButton;
    private JPanel BGPanel;
    private JScrollPane scrollpane;
    private static JTable userTable;
    private static String[][] usData;
    private static AbstractTableModel userModel;
    private Color bColor = new Color(23, 28, 30);
    private String selectedUserID;
    int row;
    // End of variables declaration

    public ManageUsers(JFrame frame) {
        super(frame, "RYCOX System - Manage Users", true);
        BGPanel = new JPanel();
        scrollpane = new JScrollPane();
        userTable = new JTable();
        deleteUserButton = new JButton("", new ImageIcon(getClass().getResource("/resources/deleteuser.png")));
        deleteUserButton.setBackground(bColor);
        addNewUserButton = new JButton("", new ImageIcon(getClass().getResource("/resources/adduser.png")));
        addNewUserButton.setBackground(bColor);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        BGPanel.setBackground(bColor);
        deleteUserButton.addActionListener(this);
        addNewUserButton.addActionListener(this);

        loadData();

        userModel = new DefaultTableModel(
                usData,
                new String[]{
                        "User Type", "User ID", "Last logged in:"
                }
        ) {

            @SuppressWarnings("rawtypes")
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false
            };

            @SuppressWarnings({"rawtypes", "unchecked"})
            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        userTable.setModel(userModel);
        scrollpane.setViewportView(userTable);
//	TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(userModel);
//	userTable.setRowSorter(sorter);
//	userTable.getRowSorter().toggleSortOrder(0);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

        GroupLayout BGPanelLayout = new GroupLayout(BGPanel);
        BGPanel.setLayout(BGPanelLayout);
        BGPanelLayout.setHorizontalGroup(
                BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(115, 115, 115).addComponent(addNewUserButton).addGap(98, 98, 98).addComponent(deleteUserButton)).addGroup(BGPanelLayout.createSequentialGroup().addGap(18, 18, 18).addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 548, GroupLayout.PREFERRED_SIZE))).addContainerGap(19, Short.MAX_VALUE)));
        BGPanelLayout.setVerticalGroup(
                BGPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(28, 28, 28).addComponent(scrollpane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(BGPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(addNewUserButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE).addComponent(deleteUserButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)).addContainerGap(23, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

        userTable.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int rowNumber = userTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = userTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    selectedUserID = (String) userTable.getValueAt(userTable.getSelectedRow(), 1);
                    row = userTable.getSelectedRow();
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = userTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = userTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    selectedUserID = (String) userTable.getValueAt(userTable.getSelectedRow(), 1);
                    row = userTable.getSelectedRow();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int rowNumber = userTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = userTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    selectedUserID = (String) userTable.getValueAt(userTable.getSelectedRow(), 1);
                    row = userTable.getSelectedRow();
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = userTable.rowAtPoint(e.getPoint());
                    ListSelectionModel lsm = userTable.getSelectionModel();
                    lsm.setSelectionInterval(rowNumber, rowNumber);
                    selectedUserID = (String) userTable.getValueAt(userTable.getSelectedRow(), 1);
                    row = userTable.getSelectedRow();
                }
            }
        });

        pack();
    }

    /**
     * This method first loads all the users (except the current user) into a 2-
     * dimensional array.
     */
    private void loadData() {
        int count = App.userList.size() - 1;
        usData = new String[count][3];
        int next = 0;
        for (int i = 0; i < App.userList.size(); i++) {
            if ((App.userList.get(i)).equals(App.userList.get(App.currentUser)) == false) {
                for (int j = 0; j <= 3; j++) {
                    switch (j) {
                        case 0:
                            if (App.userList.get(i) instanceof Administrators) {
                                usData[next][j] = "Administrator";
                                break;
                            } else if (App.userList.get(i) instanceof FrontdeskStaffs) {
                                usData[next][j] = "Front Desk Staff";
                                break;
                            }
                        case 1:
                            usData[next][j] = App.userList.get(i).getUserID();
                            break;
                        case 2:
                            usData[next][j] = App.userList.get(i).getLastLoggedIn();
                            break;
                    }
                }
                next++;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteUserButton) {
            if (selectedUserID != null || !"".equals(selectedUserID)) {
                for (int i = 0; i < App.userList.size(); i++) {
                    if (App.userList.get(i).getUserID().equals(selectedUserID)) {
                        int opt = JOptionPane.showConfirmDialog(null, "Are you sure to remove this user? This cannot be undone.", "RYCOX System - Confirm Delete User", JOptionPane.WARNING_MESSAGE);
                        if (opt == JOptionPane.YES_OPTION) {
                            App.userList.remove(i);
                            loadData();
                            ((DefaultTableModel) userModel).removeRow(row);
                            JOptionPane.showMessageDialog(null, "User '" + selectedUserID + "' has been deleted successfully.", "Success user removal", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        } else {
                            break;
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "No user is selected.", "Error", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/resources/warning.png")));
            }
        } else if (e.getSource() == addNewUserButton) {
            new AddNewUserDialog(this).setVisible(true);
        }

    }

    static void updateAdd() {
        int z = App.userList.size() - 1;
        String[] a;
        if (App.userList.get(z) instanceof Administrators) {
            a = new String[]{"Administrator", App.userList.get(z).getUserID(), App.userList.get(z).getLastLoggedIn()};
            ((DefaultTableModel) userModel).addRow(a);
        } else if (App.userList.get(z) instanceof FrontdeskStaffs) {
            a = new String[]{"Front Desk Staff", App.userList.get(z).getUserID(), App.userList.get(z).getLastLoggedIn()};
            ((DefaultTableModel) userModel).addRow(a);
        }
        userTable.tableChanged(new TableModelEvent(userModel));
        userTable.setModel(userModel);
        userTable.repaint();
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
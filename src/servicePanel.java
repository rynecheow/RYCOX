import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
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
class ServicePanel extends JPanel {

    private JTable servTable;
    private JButton servDeleteButton;
    private JButton editServButton;
    private JButton viewButton;
    private JScrollPane scrollPane;
    private JButton redoButton;
    private JPanel toolbar;
    private JButton undoButton;
    private JButton saveButton;
    private JButton recoverButton;
    private JButton servActivateButton;
    private JButton deactButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editServMI, deleteServMI, viewServMI, activeServMI, barredServMI;
    private String[][] servData;
    static String[] temp;
    static DefaultTableModel model;
    private int rowNumber;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ServicePanel() {
        toolbar = new JPanel();
        editServButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editServButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        saveButton.setBackground(bColor);
        viewButton = new JButton("", new ImageIcon(getClass().getResource("viewbutton.png")));
        viewButton.setBackground(bColor);
        redoButton = new JButton("", new ImageIcon(getClass().getResource("redobutton.png")));
        redoButton.setBackground(bColor);
        undoButton = new JButton("", new ImageIcon(getClass().getResource("undobutton.png")));
        undoButton.setBackground(bColor);
        servDeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        servDeleteButton.setBackground(bColor);
        recoverButton = new JButton("", new ImageIcon(getClass().getResource("recoverbutton.png")));
        recoverButton.setBackground(bColor);
        servActivateButton = new JButton("", new ImageIcon(getClass().getResource("activatebutton.png")));
        servActivateButton.setBackground(bColor);
        deactButton = new JButton("", new ImageIcon(getClass().getResource("deactivatebutton.png")));
        deactButton.setBackground(bColor);
        scrollPane = new JScrollPane();
        servTable = new JTable();
        servTable.getTableHeader().setReorderingAllowed(false);
        loginInfo = new JLabel("You are logged in as " + RYCOXv2.user + ".");
        loginInfo.setForeground(Color.WHITE);

        setBackground(new Color(23, 28, 30));
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));

        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(editServButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(servDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(recoverButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(servActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(deactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveButton)
                                        .addComponent(editServButton)
                                        .addComponent(servActivateButton)
                                        .addComponent(deactButton)
                                        .addComponent(viewButton)
                                        .addComponent(recoverButton)
                                        .addComponent(loginInfo)
                                        .addComponent(servDeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        servTable.setBackground(new Color(227, 226, 226));
        servTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N

        servData = new String[RYCOXv2.servList.size()][5];
        for (int i = 0; i < RYCOXv2.servList.size(); i++) {
            for (int j = 0; j <= 5; j++) {
                switch (j) {
                    case 0:
                        servData[i][j] = RYCOXv2.servList.get(i).getSmartCardNo();
                        break;
                    case 1:
                        servData[i][j] = RYCOXv2.servList.get(i).getClientID();
                        break;
                    case 2:
                        servData[i][j] = RYCOXv2.servList.get(i).getDecodeNo();
                        break;
                    case 3:
                        servData[i][j] = RYCOXv2.servList.get(i).getAddress();
                        break;
                    case 4:
                        servData[i][j] = RYCOXv2.servList.get(i).getServStatus();
                }
            }
        }

        model = new DefaultTableModel(
                servData,
                new String[]{
                        "SmartCard No.", "Client ID", "Decoder ID", "Address", "Service Status"
                }
        ) {

            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        servTable.setModel(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        servTable.setRowSorter(sorter);
        servTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        servTable.getRowSorter().toggleSortOrder(0);
        servTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(servTable);

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

        servTable.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = servTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    temp = new String[5];
                    for (int j = 0; j < 5; j++) {
                        temp[j] = (String) servTable.getValueAt(rowNumber, j);
                    }
                    if (temp[4].equalsIgnoreCase("ACTIVE")) {
                        editServButton.setEnabled(true);
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(true);
                        deactButton.setEnabled(true);
                        recoverButton.setEnabled(false);
                        servActivateButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(true);
                        activeServMI.setEnabled(false);
                        barredServMI.setEnabled(true);
                        deleteServMI.setEnabled(true);
                    } else if (temp[4].equalsIgnoreCase("BARRED")) {
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(true);
                        servActivateButton.setEnabled(true);
                        recoverButton.setEnabled(false);
                        editServButton.setEnabled(false);
                        deactButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(false);
                        activeServMI.setEnabled(true);
                        barredServMI.setEnabled(false);
                        deleteServMI.setEnabled(true);
                    } else if (temp[4].equalsIgnoreCase("TERMINATED")) {
                        recoverButton.setEnabled(true);
                        editServButton.setEnabled(false);
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(false);
                        servActivateButton.setEnabled(false);
                        deactButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(false);
                        activeServMI.setEnabled(false);
                        barredServMI.setEnabled(false);
                        deleteServMI.setEnabled(false);
                    }
                    ListSelectionModel model = servTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                    showPopup(e);
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    rowNumber = servTable.rowAtPoint(e.getPoint());
                    temp = new String[5];
                    for (int j = 0; j < 5; j++) {
                        temp[j] = (String) servTable.getValueAt(rowNumber, j);
                    }
                    if (temp[4].equalsIgnoreCase("ACTIVE")) {
                        editServButton.setEnabled(true);
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(true);
                        deactButton.setEnabled(true);
                        recoverButton.setEnabled(false);
                        servActivateButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(true);
                        activeServMI.setEnabled(false);
                        barredServMI.setEnabled(true);
                        deleteServMI.setEnabled(true);
                    } else if (temp[4].equalsIgnoreCase("BARRED")) {
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(true);
                        servActivateButton.setEnabled(true);
                        recoverButton.setEnabled(false);
                        editServButton.setEnabled(false);
                        deactButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(false);
                        activeServMI.setEnabled(true);
                        barredServMI.setEnabled(false);
                        deleteServMI.setEnabled(true);
                    } else if (temp[4].equalsIgnoreCase("TERMINATED")) {
                        recoverButton.setEnabled(true);
                        editServButton.setEnabled(false);
                        viewButton.setEnabled(true);
                        servDeleteButton.setEnabled(false);
                        servActivateButton.setEnabled(false);
                        deactButton.setEnabled(false);
                        viewServMI.setEnabled(true);
                        editServMI.setEnabled(false);
                        activeServMI.setEnabled(false);
                        barredServMI.setEnabled(false);
                        deleteServMI.setEnabled(false);
                    }
                }
            }

            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
        ); //table right click

        editServMI = new JMenuItem("Edit Service...");
        deleteServMI = new JMenuItem("Terminate Service...");
        viewServMI = new JMenuItem("View Service...");
        activeServMI = new JMenuItem("Activate Service...");
        barredServMI = new JMenuItem("Barred Service...");

        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(viewServMI);
        popupMenu.add(editServMI);
        popupMenu.add(activeServMI);
        popupMenu.add(barredServMI);
        popupMenu.add(deleteServMI);

        DialogHandler d = new DialogHandler();
        editServButton.addActionListener(d);
        saveButton.addActionListener(d);
        servDeleteButton.addActionListener(d);
        recoverButton.addActionListener(d);
        viewButton.addActionListener(d);
        servActivateButton.addActionListener(d);
        deactButton.addActionListener(d);
        viewServMI.addActionListener(d);
        editServMI.addActionListener(d);
        activeServMI.addActionListener(d);
        barredServMI.addActionListener(d);
        deleteServMI.addActionListener(d);

    }//end constructor

    public class DialogHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == saveButton) {
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
                try {
                    FileOutputStream FOS = new FileOutputStream("subsc_data.rycox");
                    ObjectOutputStream OOS = new ObjectOutputStream(FOS);
                    for (int i = 0; i < RYCOXv2.subsList.size(); i++) {
                        if (RYCOXv2.subsList.get(i) != null) {
                            OOS.writeObject(RYCOXv2.subsList);
                        }
                    }
                    OOS.flush();
                    OOS.close();
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has saved the data.[SUBSCIPTION]");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == editServButton || e.getSource() == editServMI) {
                EditServiceDialog ESD = new EditServiceDialog((JFrame) popupMenu.getParent());
                ESD.setVisible(true);
                updateTable();
            } else if (e.getSource() == servDeleteButton || e.getSource() == deleteServMI) {
                int terminate = JOptionPane.showConfirmDialog(null, "Terminate " + temp[0] + " ?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (terminate == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if (RYCOXv2.servList.get(i).getSmartCardNo().equalsIgnoreCase(temp[0])) {
                            RYCOXv2.servList.get(i).setServStatus("Terminated");
                            RYCOXv2.log = new LogFile(RYCOXv2.user, " has terminated the service " + temp[0] + ".");
                            RYCOXv2.logList.add(RYCOXv2.log);
                            RYCOXv2.printLog();
                            break;
                        }
                    }
                    updateTable();
                }
            } else if (e.getSource() == recoverButton) {
                int recover = JOptionPane.showConfirmDialog(null, "Recover " + temp[0] + " ?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (recover == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if (RYCOXv2.servList.get(i).getSmartCardNo().equalsIgnoreCase(temp[0])) {
                            RYCOXv2.servList.get(i).setServStatus("Active");
                            RYCOXv2.log = new LogFile(RYCOXv2.user, " has recovered the service " + temp[0] + ".");
                            RYCOXv2.logList.add(RYCOXv2.log);
                            RYCOXv2.printLog();
                            break;
                        }
                    }
                    updateTable();
                }
            } else if (e.getSource() == viewButton || e.getSource() == viewServMI) {
                ViewServiceDialog VSD = new ViewServiceDialog((JFrame) popupMenu.getParent());
                VSD.setVisible(true);
                RYCOXv2.log = new LogFile(RYCOXv2.user, " has viewed the service " + temp[0] + ".");
                RYCOXv2.logList.add(RYCOXv2.log);
                RYCOXv2.printLog();
            } else if (e.getSource() == servActivateButton || e.getSource() == activeServMI) {
                int activate = JOptionPane.showConfirmDialog(null, "Activate " + temp[0] + " ?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (activate == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if (RYCOXv2.servList.get(i).getSmartCardNo().equalsIgnoreCase(temp[0])) {
                            RYCOXv2.servList.get(i).setServStatus("Active");
                            RYCOXv2.log = new LogFile(RYCOXv2.user, " has activated the service " + temp[0] + ".");
                            RYCOXv2.logList.add(RYCOXv2.log);
                            RYCOXv2.printLog();
                            break;
                        }
                    }
                    updateTable();
                }
            } else if (e.getSource() == deactButton || e.getSource() == barredServMI) {
                int deact = JOptionPane.showConfirmDialog(null, "Barred " + temp[0] + " ?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (deact == JOptionPane.YES_OPTION) {
                    for (int i = 0; i < RYCOXv2.servList.size(); i++) {
                        if (RYCOXv2.servList.get(i).getSmartCardNo().equalsIgnoreCase(temp[0])) {
                            RYCOXv2.servList.get(i).setServStatus("Barred");
                            RYCOXv2.log = new LogFile(RYCOXv2.user, " has barred the service " + temp[0] + ".");
                            RYCOXv2.logList.add(RYCOXv2.log);
                            RYCOXv2.printLog();
                            break;
                        }
                    }
                    updateTable();
                }
            }
        }
    }

    public void updateTable() {
        servData = new String[RYCOXv2.servList.size()][5];
        for (int i = 0; i < RYCOXv2.servList.size(); i++) {
            for (int j = 0; j <= 5; j++) {
                switch (j) {
                    case 0:
                        servData[i][j] = RYCOXv2.servList.get(i).getSmartCardNo();
                        break;
                    case 1:
                        servData[i][j] = RYCOXv2.servList.get(i).getClientID();
                        break;
                    case 2:
                        servData[i][j] = RYCOXv2.servList.get(i).getDecodeNo();
                        break;
                    case 3:
                        servData[i][j] = RYCOXv2.servList.get(i).getAddress();
                        break;
                    case 4:
                        servData[i][j] = RYCOXv2.servList.get(i).getServStatus();
                }
            }
        }


        model = new DefaultTableModel(
                servData,
                new String[]{
                        "SmartCard No.", "Client ID", "Decoder ID", "Address", "Service Status"
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
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        servTable.setModel(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        servTable.setRowSorter(sorter);
        servTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        servTable.getRowSorter().toggleSortOrder(0);
        servTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(servTable);
    }

    static void addServ() {
        int row = RYCOXv2.servList.size() - 1;
        String[] newServ = new String[]{RYCOXv2.servList.get(row).getSmartCardNo(), RYCOXv2.servList.get(row).getClientID(), RYCOXv2.servList.get(row).getDecodeNo(), RYCOXv2.servList.get(row).getAddress(), RYCOXv2.servList.get(row).getServStatus()};
        ((DefaultTableModel) model).addRow(newServ);
    }
}
import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
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
public class ClientPanel extends JPanel {

    private static JTable clTable;
    @SuppressWarnings("rawtypes")
    private JComboBox clTypeCombo;
    private JButton cldeleteButton;
    private JButton editclButton;
    private JScrollPane scrollPane;
    private JButton newclButton;
    private JButton saveButton;
    private JButton logoutButton;
    private JButton redoButton;
    private JPanel toolbar;
    private JButton undoButton;
    private JButton recoverButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editclMI, deleteclMI, addservMI;
    private String[][] clData;
    private static DefaultTableModel model;
    private int a = RYCOXv2.clientList.size();

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ClientPanel() {
        toolbar = new JPanel();
        clTypeCombo = new JComboBox();
        newclButton = new JButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        newclButton.setBackground(bColor);
        editclButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editclButton.setBackground(bColor);
        redoButton = new JButton("", new ImageIcon(getClass().getResource("redobutton.png")));
        redoButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        saveButton.setBackground(bColor);
        undoButton = new JButton("", new ImageIcon(getClass().getResource("undobutton.png")));
        undoButton.setBackground(bColor);
        logoutButton = new JButton("", new ImageIcon(getClass().getResource("logout-button.png")));
        logoutButton.setBackground(bColor);
        cldeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        cldeleteButton.setBackground(bColor);
        recoverButton = new JButton("", new ImageIcon(getClass().getResource("recoverbutton.png")));
        recoverButton.setBackground(bColor);
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
                                .addComponent(cldeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(recoverButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                                .addComponent(logoutButton, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
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
                                        .addComponent(redoButton)
                                        .addComponent(undoButton)
                                        .addComponent(recoverButton)
                                        .addComponent(loginInfo)
                                        .addComponent(logoutButton)
                                        .addComponent(cldeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clTable.setBackground(new Color(227, 226, 226));
        clTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N
        clData = new String[RYCOXv2.clientList.size()][4];
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            for (int j = 0; j <= 4; j++) {
                switch (j) {
                    case 0:
                        clData[i][j] = RYCOXv2.clientList.get(i).getClientID();
                        break;
                    case 1:
                        clData[i][j] = RYCOXv2.clientList.get(i).getName();
                        break;
                    case 2:
                        clData[i][j] = RYCOXv2.clientList.get(i).getBillingAddress();
                        break;
                    case 3:
                        clData[i][j] = RYCOXv2.clientList.get(i).getAccountStatus();
                        break;
                }
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
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = clTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = clTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);

                }
            }
        }
        ); //table right click
        DialogHandler dialoghandler = new DialogHandler();
        newclButton.addActionListener(dialoghandler);
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
        model.addRow(a);
        clTable.tableChanged(new TableModelEvent(model));
        clTable.setModel(model);
        clTable.repaint();
    }

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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else if (e.getSource() == editclButton) {
                EditClientDialog ecd = new EditClientDialog((JFrame) popupMenu.getParent());
                ecd.setVisible(true);
            } else if (e.getSource() == cldeleteButton) {

            } else if (e.getSource() == recoverButton) {

            }
        }
    }

}
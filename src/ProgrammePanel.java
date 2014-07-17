import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.util.Date;

/**
 * @author Jia Jiun
 */
@SuppressWarnings("serial")
class ProgrammePanel extends JPanel {
    //Variable declaration
    private static JTable prgTable;
    private JButton prgAddButton;
    private static JButton prgDeleteButton;
    private static JButton editPrgButton;
    private JScrollPane scrollPane;
    private JButton saveButton;
    private static JButton viewButton;
    private static JButton prgActivateButton;
    private static JButton prgDeactButton;
    private JPopupMenu popupMenu;
    private JMenuItem editPrgMI, deletePrgMI, activateMI, viewMI, deactivateMI;
    private String[][] prgData;
    private static AbstractTableModel prgModel;
    static String[] progtemp;
    private int rowno;
    static int lol;
    private JTextField searchbox;
    private TableRowSorter<TableModel> sorter;
    //End of variable declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ProgrammePanel() {
        JPanel toolbar = new JPanel();
        prgAddButton = new JButton("", new ImageIcon(getClass().getResource("/resources/newbutton.png")));
        Color bColor = new Color(23, 28, 30);
        prgAddButton.setBackground(bColor);
        editPrgButton = new JButton("", new ImageIcon(getClass().getResource("/resources/editbutton.png")));
        editPrgButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("/resources/savebutton.png")));
        saveButton.setBackground(bColor);
        prgDeleteButton = new JButton("", new ImageIcon(getClass().getResource("/resources/deletebutton.png")));
        prgDeleteButton.setBackground(bColor);
        viewButton = new JButton("", new ImageIcon(getClass().getResource("/resources/viewbutton.png")));
        viewButton.setBackground(bColor);
        prgActivateButton = new JButton("", new ImageIcon(getClass().getResource("/resources/activatebutton.png")));
        prgActivateButton.setBackground(bColor);
        prgDeactButton = new JButton("", new ImageIcon(getClass().getResource("/resources/deactivatebutton.png")));
        prgDeactButton.setBackground(bColor);
        scrollPane = new JScrollPane();
        prgTable = new JTable();
        prgTable.getTableHeader().setReorderingAllowed(false);
        JLabel loginInfo = new JLabel("You are logged in as " + App.user + ".");
        loginInfo.setForeground(Color.WHITE);
        loginInfo.setFont(new Font("LucidaSansRegular", Font.PLAIN, 14));
        searchbox = new JTextField(16);
        searchbox.setForeground(Color.BLACK);
        searchbox.setText("");
        JLabel searchLabel = new JLabel();
        searchLabel.setText("Search:");
        searchLabel.setForeground(Color.WHITE);

        setBackground(new Color(23, 28, 30));
        toolbar.setBackground(bColor);
        toolbar.setPreferredSize(new Dimension(1500, 30));
        defaultButtonSet();
        GroupLayout toolbarLayout = new GroupLayout(toolbar);
        toolbar.setLayout(toolbarLayout);
        toolbarLayout.setHorizontalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, toolbarLayout.createSequentialGroup().addContainerGap().addComponent(prgAddButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(editPrgButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(viewButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(prgDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(prgActivateButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(prgDeactButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE).addComponent(searchLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(searchbox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap())
        );
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(toolbarLayout.createSequentialGroup().addContainerGap().addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(prgAddButton).addComponent(saveButton).addComponent(editPrgButton).addComponent(viewButton).addComponent(searchLabel).addComponent(searchbox)//.addComponent(redoButton)

                        .addComponent(prgActivateButton).addComponent(prgDeactButton).addComponent(loginInfo).addComponent(prgDeleteButton)).addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        prgTable.setBackground(new Color(227, 226, 226));
        prgTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N

        prgData = new String[App.prgList.size()][6];
        for (int i = 0; i < App.prgList.size(); i++) {
            for (int j = 0; j <= 6; j++) {

                switch (j) {
                    case 0:
                        prgData[i][j] = App.prgList.get(i).getProgCode();
                        break;
                    case 1:
                        prgData[i][j] = App.prgList.get(i).getProgTitle();
                        break;
                    case 2:
                        prgData[i][j] = App.prgList.get(i).getContentOrigin();
                        break;
                    case 3:
                        prgData[i][j] = App.prgList.get(i).getViewerStatus();
                        break;
                    case 4:
                        prgData[i][j] = App.prgList.get(i).getType();
                        break;
                    case 5:
                        prgData[i][j] = App.prgList.get(i).getPrgStatus();
                        break;
                }
            }
        }

        prgModel = new DefaultTableModel(
                prgData,
                new String[]{
                        "Programme Code", "Programme Title", "Content Origin", "Viewer Status", "Type", "Programme Status"
                }
        ) {

            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,};
            boolean[] canEdit = new boolean[]{
                    false, false, false, false, false, false
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
        prgTable.setModel(prgModel);
        sorter = new TableRowSorter<>(prgModel);
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

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = prgTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = prgTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                    rowno = prgTable.rowAtPoint(e.getPoint());
                    progtemp = new String[10];
                    progtemp[0] = (String) prgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < App.prgList.size(); j++) {
                        if (progtemp[0].equalsIgnoreCase(App.prgList.get(j).getProgCode())) {
                            progtemp[1] = App.prgList.get(j).getProgTitle();
                            progtemp[2] = App.prgList.get(j).getDesc();
                            progtemp[3] = App.prgList.get(j).getContentOrigin();
                            progtemp[4] = App.prgList.get(j).getCreationDate();
                            progtemp[5] = App.prgList.get(j).getTerminationDate();
                            progtemp[6] = App.prgList.get(j).getPrgStatus();
                            progtemp[7] = App.prgList.get(j).getViewerStatus();
                            progtemp[8] = App.prgList.get(j).getType();
                            progtemp[9] = App.prgList.get(j).getNotes();
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

                    progtemp[0] = (String) prgTable.getValueAt(rowno, 0);

                    for (int j = 0; j < App.prgList.size(); j++) {
                        if (progtemp[0].equalsIgnoreCase(App.prgList.get(j).getProgCode())) {
                            progtemp[1] = App.prgList.get(j).getProgTitle();
                            progtemp[2] = App.prgList.get(j).getDesc();
                            progtemp[3] = App.prgList.get(j).getContentOrigin();
                            progtemp[4] = App.prgList.get(j).getCreationDate();
                            progtemp[5] = App.prgList.get(j).getTerminationDate();
                            progtemp[6] = App.prgList.get(j).getPrgStatus();
                            progtemp[7] = App.prgList.get(j).getViewerStatus();
                            progtemp[8] = App.prgList.get(j).getType();
                            progtemp[9] = App.prgList.get(j).getNotes();

                            break;
                        }
                    }
                    changeMode();


                }
            }

            /**
             * This method gets the component of each row and column and pop up Menu Item
             *
             */
            private void showPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }); //table click

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

        if (App.userList.get(App.currentUser) instanceof Administrators) {
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
        activateMI.addActionListener(e -> {

            try {
                String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                for (int i = 0; i < App.prgList.size(); i++) {
                    if (ID.equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                        if (App.prgList.get(i).getPrgStatus().equalsIgnoreCase("INACTIVE")) {

                            int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Programme '" + App.prgList.get(i).getProgCode() + "'?");
                            if (opt == JOptionPane.YES_OPTION) {
                                App.prgList.get(i).setPrgStatus("ACTIVE");
                                JOptionPane.showMessageDialog(null, "Programme " + App.prgList.get(i).getProgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
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

        });


        deactivateMI.addActionListener(e -> {


            try {
                String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                for (int i = 0; i < App.prgList.size(); i++) {
                    if (ID.equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                        if (App.prgList.get(i).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
                            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Programme " + App.prgList.get(i).getProgCode() + " ?", "Programme Code found!", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                App.prgList.get(i).setPrgStatus("INACTIVE");
                                JOptionPane.showMessageDialog(null, "Programme '" + App.prgList.get(i).getProgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
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

        });


        viewMI.addActionListener(e -> {
            new ViewProgrammeDialog((JFrame) popupMenu.getParent());
            prgTable.clearSelection();
        });

        editPrgMI.addActionListener(e -> {
            EditProgrammeDialog epd = new EditProgrammeDialog((JFrame) popupMenu.getParent());
            updateProgrammeTable();
            prgTable.clearSelection();
        });


        deletePrgMI.addActionListener(e -> {
            boolean flag = false;
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Programme " + progtemp[0] + " ?", "Programme Code found!", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                for (int check = 0; check < App.pckgingList.size(); check++) {

                    if (progtemp[0].equalsIgnoreCase(App.pckgingList.get(check).getProgCode())) {
                        JOptionPane.showMessageDialog(null, "The programme is included in one or more packages. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }

                if (flag) {

                    for (int counts = 0; counts < App.prgList.size(); counts++) {
                        if (progtemp[0].equalsIgnoreCase(App.prgList.get(counts).getProgCode())) {
                            if ((App.prgList.get(counts).getPrgStatus().equalsIgnoreCase("ACTIVE")) || (App.prgList.get(counts).getPrgStatus().equalsIgnoreCase("INACTIVE"))) {

                                App.prgList.get(counts).setPrgStatus("TERMINATED");
                                String date = DateFormat.getInstance().format(new Date());
                                App.prgList.get(counts).setTerminationDate(date);
                                JOptionPane.showMessageDialog(null, "TV Programme " + progtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                LogFile log = new LogFile(App.user, "has terminated a TV Programme '" + progtemp[0] + "'.");
                                App.logList.add(log);
                                break;

                            }


                        }
                    }
                }

            }

            updateProgrammeTable();
            prgTable.clearSelection();
        });

	/*
     * -------------------------- BUTTON LISTENER --------------------------
	 */
        prgActivateButton.addActionListener(e -> {
            try {
                String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                for (int i = 0; i < App.prgList.size(); i++) {
                    if (ID.equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                        if (App.prgList.get(i).getPrgStatus().equalsIgnoreCase("INACTIVE")) {

                            int opt = JOptionPane.showConfirmDialog(null, "Reactivate TV Programme '" + App.prgList.get(i).getProgCode() + "'?");
                            if (opt == JOptionPane.YES_OPTION) {
                                App.prgList.get(i).setPrgStatus("ACTIVE");
                                JOptionPane.showMessageDialog(null, "Programme " + App.prgList.get(i).getProgCode() + " is now activated.", "RYCOX System - Activation Successful", JOptionPane.INFORMATION_MESSAGE);
                                updateProgrammeTable();
                                prgTable.clearSelection();
                                prgDeactButton.setEnabled(true);
                                deactivateMI.setEnabled(true);
                                prgActivateButton.setEnabled(true);
                                activateMI.setEnabled(true);
                                defaultButtonSet();
                                App.log = new LogFile(App.user, " has activated TV Package '" + App.prgList.get(i).getProgCode() + "'.");
                                App.logList.add(App.log);
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

        });


        prgDeactButton.addActionListener(e -> {
            try {
                String ID = (String) prgTable.getValueAt(prgTable.getSelectedRow(), 0);
                for (int i = 0; i < App.prgList.size(); i++) {
                    if (ID.equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                        if (App.prgList.get(i).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
                            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to deactivate TV Programme " + App.prgList.get(i).getProgCode() + " ?", "Programme Code found!", JOptionPane.YES_NO_OPTION);
                            if (choice == JOptionPane.YES_OPTION) {
                                App.prgList.get(i).setPrgStatus("INACTIVE");
                                JOptionPane.showMessageDialog(null, "Programme '" + App.prgList.get(i).getProgCode() + "' is now deactivated.", "RYCOX System - Deactivation Successful", JOptionPane.INFORMATION_MESSAGE);
                                updateProgrammeTable();
                                prgTable.clearSelection();
                                prgDeactButton.setEnabled(true);
                                deactivateMI.setEnabled(true);
                                prgActivateButton.setEnabled(true);
                                activateMI.setEnabled(true);
                                defaultButtonSet();
                                App.log = new LogFile(App.user, " has deactivated TV Programme '" + App.prgList.get(i).getProgCode() + "'.");
                                App.logList.add(App.log);
                            } else if (choice == JOptionPane.NO_OPTION) {

                                break;
                            }
                        }
                    }
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        viewButton.addActionListener(e -> {
            try {

                new ViewProgrammeDialog((JFrame) popupMenu.getParent());
                prgTable.clearSelection();
                defaultButtonSet();
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        prgAddButton.addActionListener(e -> {
            NewProgrammeDialog npd = new NewProgrammeDialog((JFrame) popupMenu.getParent());
            updateProgrammeTable();
            prgTable.clearSelection();
        });

        saveButton.addActionListener(e -> {
            App.saveProgramFile();
            App.printLog();
            JOptionPane.showMessageDialog(null, "You have saved all the changes under programme management successfully.", "Saved successfully", JOptionPane.INFORMATION_MESSAGE);
        });

        editPrgButton.addActionListener(e -> {
            try {
                EditProgrammeDialog epd = new EditProgrammeDialog((JFrame) popupMenu.getParent());
                updateProgrammeTable();
                prgTable.clearSelection();
                defaultButtonSet();
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        prgDeleteButton.addActionListener(e -> {
            try {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Programme " + progtemp[0] + " ?", "Programme Code found!", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    boolean flag = false;
                    for (int check = 0; check < App.pckgingList.size(); check++) {

                        if (progtemp[0].equalsIgnoreCase(App.pckgingList.get(check).getProgCode())) {
                            JOptionPane.showMessageDialog(null, "The programme is included in one or more packages. It cannot be terminated!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                            flag = false;

                            break;
                        } else {
                            flag = true;
                        }
                    }


                    if (flag) {
                        for (int counts = 0; counts < App.prgList.size(); counts++) {
                            if (progtemp[0].equalsIgnoreCase(App.prgList.get(counts).getProgCode())) {
                                if ((App.prgList.get(counts).getPrgStatus().equalsIgnoreCase("ACTIVE")) || (App.prgList.get(counts).getPrgStatus().equalsIgnoreCase("INACTIVE"))) {

                                    App.prgList.get(counts).setPrgStatus("TERMINATED");
                                    String date = DateFormat.getInstance().format(new Date());
                                    App.prgList.get(counts).setTerminationDate(date);
                                    JOptionPane.showMessageDialog(null, "TV Programme " + progtemp[0] + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                    LogFile log = new LogFile(App.user, "has terminated a TV Programme '" + progtemp[0] + "'.");
                                    App.logList.add(log);

                                    break;

                                }


                            }
                        }
                    }

                }

                updateProgrammeTable();
                prgTable.clearSelection();
                defaultButtonSet();
            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You must select at least one row.", "No row selected", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        searchbox.addCaretListener(e -> {
            try {
                if (!"".equals(searchbox.getText())) {
                    sorter.setRowFilter(RowFilter.regexFilter(searchbox.getText()));
                    sorter.setSortKeys(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("[Ff][0-9]{3}"));
                    sorter.setSortKeys(null);
                }
            } catch (Exception ignored) {


            }


            repaint();
        });

    }//end constructor


    /**
     * This method is called while there are some changes to the JTable.
     * The value in each row and column will be updated immediately when it is called.
     */
    public void updateProgrammeTable() {
        prgData = new String[App.prgList.size()][6];
        for (int i = 0; i < App.prgList.size(); i++) {
            for (int j = 0; j <= 6; j++) {
                switch (j) {
                    case 0:
                        prgData[i][j] = App.prgList.get(i).getProgCode();
                        break;
                    case 1:
                        prgData[i][j] = App.prgList.get(i).getProgTitle();
                        break;
                    case 2:
                        prgData[i][j] = App.prgList.get(i).getContentOrigin();
                        break;
                    case 3:
                        prgData[i][j] = App.prgList.get(i).getViewerStatus();
                        break;
                    case 4:
                        prgData[i][j] = App.prgList.get(i).getType();
                        break;
                    case 5:
                        prgData[i][j] = App.prgList.get(i).getPrgStatus();
                        break;
                }
            }
        }

        prgModel = new DefaultTableModel(
                prgData,
                new String[]{
                        "Programme Code", "Programme Title", "Content Origin", "Viewer Status", "Type", "Programme Status"
                }
        ) {

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
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(prgModel);
        prgTable.setRowSorter(sorter);
        prgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        prgTable.setName("");
        prgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(prgTable);
    }

    /**
     * This method is called to validate JButton and Menu Item availability in every situation.
     * It will disable or hide some specific JButton or Menu Item.
     */
    private void changeMode() {
        rowno = prgTable.getSelectedRow();
        String ID = (String) prgTable.getValueAt(rowno, 0);
        if (App.userList.get(App.currentUser) instanceof Administrators) {
            for (int i = 0; i < App.prgList.size(); i++) {
                if (ID.equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                    lol = i;
                    if (App.prgList.get(lol).getPrgStatus().equalsIgnoreCase("ACTIVE")) {
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
                    } else if (App.prgList.get(lol).getPrgStatus().equalsIgnoreCase("INACTIVE")) {
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
                    } else if (App.prgList.get(lol).getPrgStatus().equalsIgnoreCase("TERMINATED")) {
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
        } else {
            prgAddButton.setVisible(false);
            saveButton.setVisible(false);
            editPrgButton.setVisible(false);
            viewButton.setVisible(true);
            viewButton.setEnabled(true);
            prgDeleteButton.setVisible(false);
            prgActivateButton.setVisible(false);
            prgDeactButton.setVisible(false);
            editPrgMI.setVisible(false);
            deletePrgMI.setVisible(false);
            activateMI.setVisible(false);
            deactivateMI.setVisible(false);
            viewMI.setVisible(true);
            viewMI.setEnabled(true);
        }
    }

    /**
     * This method set the default access permission to JButton
     */
    static void defaultButtonSet() {
        editPrgButton.setEnabled(false);
        viewButton.setEnabled(false);
        prgDeleteButton.setEnabled(false);
        prgActivateButton.setEnabled(false);
        prgDeactButton.setEnabled(false);

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
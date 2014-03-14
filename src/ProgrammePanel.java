import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ProgrammePanel extends JPanel {

    private JTable prgTable;
    private JButton prgAddButton;
    private JButton prgDeleteButton;
    private JButton editPrgButton;
    private JScrollPane scrollPane;
    private JButton redoButton;
    private JPanel toolbar;
    private JButton undoButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editPrgMI, deletePrgMI, addToPkgMI;
    private String[][] prgData;


    @SuppressWarnings({"unchecked", "rawtypes"})
    public ProgrammePanel() {
        toolbar = new JPanel();
        prgAddButton = new JButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        prgAddButton.setBackground(bColor);
        editPrgButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editPrgButton.setBackground(bColor);
        redoButton = new JButton("", new ImageIcon(getClass().getResource("redobutton.png")));
        redoButton.setBackground(bColor);
        undoButton = new JButton("", new ImageIcon(getClass().getResource("undobutton.png")));
        undoButton.setBackground(bColor);
        prgDeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        prgDeleteButton.setBackground(bColor);
        scrollPane = new JScrollPane();
        prgTable = new JTable();
        prgTable.getTableHeader().setReorderingAllowed(false);
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
                                .addComponent(prgAddButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(editPrgButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(prgDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(undoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(redoButton, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(loginInfo, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        toolbarLayout.setVerticalGroup(
                toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(toolbarLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(toolbarLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prgAddButton)
                                        .addComponent(editPrgButton)
                                        .addComponent(redoButton)
                                        .addComponent(undoButton)
                                        .addComponent(loginInfo)
                                        .addComponent(prgDeleteButton))

                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        prgTable.setBackground(new Color(227, 226, 226));
        prgTable.setFont(new Font("LucidaSansRegular", Font.PLAIN, 12)); // NOI18N

        prgData = new String[RYCOXv2.prgList.size()][5];
        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            for (int j = 0; j <= 5; j++) {
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
                }
            }
        }

        DefaultTableModel model = new DefaultTableModel(
                prgData,
                new String[]{
                        "Programme Code", "Programme Title", "Content Origin", "Viewer Status", "Type"
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
        prgTable.setModel(model);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
        prgTable.setRowSorter(sorter);
        prgTable.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        prgTable.setName("");
        prgTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(prgTable);

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

        prgTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = prgTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = prgTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    ListSelectionModel model = prgTable.getSelectionModel();
                    model.setSelectionInterval(prgTable.rowAtPoint(e.getPoint()), prgTable.rowAtPoint(e.getPoint()));
                }
            }
        }
        ); //table right click

        editPrgMI = new JMenuItem("Edit Programme...");
        deletePrgMI = new JMenuItem("Terminate Programme...");
        addToPkgMI = new JMenuItem("Add Service...");

        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(editPrgMI);
        popupMenu.add(deletePrgMI);
        popupMenu.add(addToPkgMI);
    }//end constructor
}
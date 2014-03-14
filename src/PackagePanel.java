import javax.swing.*;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class PackagePanel extends JPanel {

    private static JTable pkgTable;
    private JButton pkgAddButton;
    private JButton pkgDeleteButton;
    private JButton editpkgButton;
    private JScrollPane scrollPane;
    private JButton redoButton;
    private JPanel toolbar;
    private JButton saveButton;
    private JButton undoButton;
    private JLabel loginInfo;
    private Color bColor = new Color(23, 28, 30);
    private JPopupMenu popupMenu;
    private JMenuItem editpkgMI, deletepkgMI, addTopkgMI;
    private String[][] pkgData;
    private static AbstractTableModel pkgModel;


    @SuppressWarnings({"unchecked", "rawtypes"})
    public PackagePanel() {
        toolbar = new JPanel();
        pkgAddButton = new JButton("", new ImageIcon(getClass().getResource("newbutton.png")));
        pkgAddButton.setBackground(bColor);
        editpkgButton = new JButton("", new ImageIcon(getClass().getResource("editbutton.png")));
        editpkgButton.setBackground(bColor);
        saveButton = new JButton("", new ImageIcon(getClass().getResource("savebutton.png")));
        saveButton.setBackground(bColor);
        redoButton = new JButton("", new ImageIcon(getClass().getResource("redobutton.png")));
        redoButton.setBackground(bColor);
        undoButton = new JButton("", new ImageIcon(getClass().getResource("undobutton.png")));
        undoButton.setBackground(bColor);
        pkgDeleteButton = new JButton("", new ImageIcon(getClass().getResource("deletebutton.png")));
        pkgDeleteButton.setBackground(bColor);
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
                                .addComponent(editpkgButton, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addComponent(pkgDeleteButton, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(pkgAddButton)
                                        .addComponent(saveButton)
                                        .addComponent(editpkgButton)
                                        .addComponent(redoButton)
                                        .addComponent(undoButton)
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
                        String x = Double.toString(RYCOXv2.pkgList.get(i).getChargePrice());
                        pkgData[i][j] = x;
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
                        "Package Code", "Package Name", "Charge Price", "Charge Type", "Package Status"
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
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int rowNumber = pkgTable.rowAtPoint(e.getPoint());
                    // Get the ListSelectionModel of the JTable
                    ListSelectionModel model = pkgTable.getSelectionModel();
                    model.setSelectionInterval(rowNumber, rowNumber);
                } else if (SwingUtilities.isLeftMouseButton(e)) {
                    ListSelectionModel model = pkgTable.getSelectionModel();
                    model.setSelectionInterval(pkgTable.rowAtPoint(e.getPoint()), pkgTable.rowAtPoint(e.getPoint()));
                }
            }
        }
        ); //table right click

        editpkgMI = new JMenuItem("Edit Package...");
        deletepkgMI = new JMenuItem("Terminate Package...");
        addTopkgMI = new JMenuItem("Add Package...");

        popupMenu = new JPopupMenu("Menu");
        popupMenu.add(editpkgMI);
        popupMenu.add(deletepkgMI);
        popupMenu.add(addTopkgMI);


		/*
        pkgAddButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				AddPackage ap = new AddPackage((JFrame) popupMenu.getParent());
			}
		});

		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					FileOutputStream pkg_fostream = new FileOutputStream("pkg_data.rycox");
					ObjectOutputStream pkg_oostream = new ObjectOutputStream(pkg_fostream);
					for	(int i=0; i<RYCOXv2.pkgList.size(); i++){
						if(RYCOXv2.pkgList.get(i) != null){
							pkg_oostream.writeObject(RYCOXv2.pkgList);
						}
					}
					pkg_oostream.flush();
					pkg_oostream.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}

				try{
					FileOutputStream pckging_fostream = new FileOutputStream("pckging_data.rycox");
					ObjectOutputStream pckging_oostream = new ObjectOutputStream(pckging_fostream);
					for	(int i=0; i<RYCOXv2.pckgingList.size(); i++){
						if(RYCOXv2.pckgingList.get(i) != null){
							pckging_oostream.writeObject(RYCOXv2.pckgingList);
						}
					}
					pckging_oostream.flush();
					pckging_oostream.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
		});

		editpkgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				EditPackage ep = new EditPackage((JFrame) popupMenu.getParent());
			}
		});


		pkgDeleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				TerminatePackage tp = new TerminatePackage((JFrame) popupMenu.getParent());
			}
		});  */

    }//end constructor


    static void updatePackageTable() {
        int size = RYCOXv2.pkgList.size();
        int n = size - 1;
        String y = Double.toString(RYCOXv2.pkgList.get(n).getChargePrice());


        String[] a = {RYCOXv2.pkgList.get(n).getPkgCode(), RYCOXv2.pkgList.get(n).getPkgName(), y,
                RYCOXv2.pkgList.get(n).getChargeType(), RYCOXv2.pkgList.get(n).getPkgStatus()};
        ((DefaultTableModel) pkgModel).addRow(a);
        pkgTable.tableChanged(new TableModelEvent(pkgModel));
        pkgTable.setModel(pkgModel);
        pkgTable.repaint();


    }

}
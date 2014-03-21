import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author Jia Jiun
 */
@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
class EditPackageDialog extends JDialog {

    //Variable declaration
    private JLabel label1, label2, label3, label4, label5, label6;
    private JLabel error3, error4, error5, error6, error7;
    private JTextField textfield1, textfield2, textfield3;
    private JList leftList;
    private JList rightList;
    private JComboBox combobox1;
    private JButton button1, button2, addBut, rmvBut;
    private Color color, color1, color2;
    private LinkedList<String> leftPrgList;
    private LinkedList<String> selPrgList;
    private AbstractListModel leftModel;
    private AbstractListModel rightModel;
    private String[] leftPrg;
    private String[] selPrg;
    private String[] rightElArr;
    private JScrollPane leftListScroll;
    private JScrollPane rightListScroll;
    private LinkedList<String> rightElements;
    //End of variable declaration

    public EditPackageDialog(JFrame frame) {
        super(frame, "Package Management- Edit a TV Package", true);

        label1 = new JLabel("Package Code: ");
        label2 = new JLabel("Package Name: ");
        error3 = new JLabel("Blank input occured! Please reenter it.");
        label3 = new JLabel("Charge Price(RM): ");
        error4 = new JLabel("Blank input occured! Please reenter it.");
        error6 = new JLabel("Wrong input format. Please enter in numeric format!");
        label4 = new JLabel("Charge Type: ");
        error5 = new JLabel("Blank input occured! Please reenter it.");
        error7 = new JLabel("You must have at least one programme added to your package.");
        label5 = new JLabel("Programmes: ");
        label6 = new JLabel("Programmes inside this Package: ");

        textfield1 = new JTextField(15);
        textfield2 = new JTextField(15);
        textfield3 = new JTextField(15);
        textfield3.setEditable(false);
        combobox1 = new JComboBox();
        button1 = new JButton("Submit");
        button2 = new JButton("Cancel");
        addBut = new JButton("", new ImageIcon(getClass().getResource("/resources/addtoright.png")));
        rmvBut = new JButton("", new ImageIcon(getClass().getResource("/resources/removetoleft.png")));
        addBut.setBackground(new Color(23, 28, 31));
        rmvBut.setBackground(new Color(23, 28, 31));

        color = new Color(23, 28, 30);
        color1 = new Color(244, 219, 234);
        color2 = new Color(239, 237, 237);
        textfield3.setBackground(color2);


        selPrgList = new LinkedList<>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            for (int j = 0; j < RYCOXv2.pckgingList.size(); j++) {
                if (PackagePanel.pkgtemp[0].equalsIgnoreCase(RYCOXv2.pckgingList.get(j).getPkgCode())) {
                    if (RYCOXv2.pckgingList.get(j).getProgCode().equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                        selPrgList.add(RYCOXv2.pckgingList.get(j).getProgCode());
                        ((DefaultListModel) rightModel).addElement(RYCOXv2.pckgingList.get(j).getProgCode() + "-" + RYCOXv2.prgList.get(i).getProgTitle());
                    }
                }
            }
        }
        selPrg = selPrgList.toArray(new String[selPrgList.size()]);

        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            boolean check = false;
            for (int j = 0; j < selPrg.length; j++) {

                if (selPrg[j].equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                    check = true;
                    break;
                }
            }
            if (check == false) {
                if (!(RYCOXv2.prgList.get(i).getPrgStatus().equalsIgnoreCase("TERMINATED"))) {
                    ((DefaultListModel) leftModel).addElement(RYCOXv2.prgList.get(i).getProgCode() + "-" + RYCOXv2.prgList.get(i).getProgTitle());
                }
            }
        }

        leftList = new JList(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(13);
        leftList.setFixedCellWidth(210);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rightList = new JList(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(13);
        rightList.setFixedCellWidth(210);
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);
        rightListScroll = new JScrollPane(rightList);
        rightListScroll.setViewportView(rightList);


        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();

        String[] string1 = {"<-----Please Choose a charge type----->", "Monthly", "Quarterly", "Annually"};
        combobox1.setModel(new DefaultComboBoxModel(string1));
        error3.setForeground(Color.RED);
        error4.setForeground(Color.RED);
        error5.setForeground(Color.RED);
        error6.setForeground(Color.RED);
        error7.setForeground(Color.RED);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);
        error6.setVisible(false);
        error7.setVisible(false);
        label1.setForeground(color1);
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);
        label6.setForeground(color1);


        textfield3.setText(PackagePanel.pkgtemp[0]);
        textfield1.setText(PackagePanel.pkgtemp[1]);
        textfield2.setText(PackagePanel.pkgtemp[2]);
        combobox1.setSelectedItem((String) (PackagePanel.pkgtemp[3]));

        addBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int leftIndex = leftList.getSelectedIndex();
                    String toBeAdded = ((String) leftList.getModel().getElementAt(leftIndex));
                    LinkedList<String> leftElements = new LinkedList<>();
                    for (int p = 0; p < leftList.getModel().getSize(); p++) {
                        leftElements.add((String) leftList.getModel().getElementAt(p));
                    }

                    for (int i = 0; i < leftElements.size(); i++) {
                        if ((leftElements.get(i)).equals(toBeAdded)) {
                            leftElements.remove(i);
                            break;
                        }
                    }

                    String[] leftElArr = leftElements.toArray(new String[leftElements.size()]);
                    leftList.setListData(leftElArr);
                    leftList.revalidate();
                    leftList.repaint();


                    rightElements = new LinkedList<>();
                    for (int z = 0; z < rightList.getModel().getSize(); z++) {
                        rightElements.add((String) rightList.getModel().getElementAt(z));
                    }
                    rightElements.add(toBeAdded);
                    rightElArr = rightElements.toArray(new String[rightElements.size()]);
                    rightList.setListData(rightElArr);
                    rightList.repaint();
                    rightList.revalidate();
                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You can only add a TV Programme from the left list.", "Adding error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        rmvBut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rightIndex = rightList.getSelectedIndex();
                    String toBeRemoved = ((String) rightList.getModel().getElementAt(rightIndex));
                    LinkedList<String> rightElements = new LinkedList<>();
                    for (int p = 0; p < rightList.getModel().getSize(); p++) {
                        rightElements.add((String) rightList.getModel().getElementAt(p));
                    }

                    for (int i = 0; i < rightElements.size(); i++) {
                        if ((rightElements.get(i)).equals(toBeRemoved)) {
                            rightElements.remove(i);
                            break;
                        }
                    }

                    rightElArr = rightElements.toArray(new String[rightElements.size()]);
                    rightList.setListData(rightElArr);
                    rightList.revalidate();
                    rightList.repaint();


                    LinkedList<String> leftElements = new LinkedList<>();
                    for (int z = 0; z < leftList.getModel().getSize(); z++) {
                        leftElements.add((String) leftList.getModel().getElementAt(z));
                    }
                    leftElements.add(toBeRemoved);
                    String[] leftElArr = leftElements.toArray(new String[leftElements.size()]);
                    leftList.setListData(leftElArr);
                    leftList.repaint();
                    leftList.revalidate();

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "You can only remove a TV Programme from the right list when right list is filled in with TV Programme.", "Remove error", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;


                if ((textfield1.getText().trim() == null) || (textfield1.getText().trim().equalsIgnoreCase(""))) {
                    error3.setVisible(true);
                    flag1 = false;
                } else {
                    flag1 = true;
                    error3.setVisible(false);
                }

                if ((textfield2.getText().trim() == null) || (textfield2.getText().trim().equalsIgnoreCase(""))) {
                    error4.setVisible(true);
                    error6.setVisible(false);
                    flag2 = false;
                } else {
                    try {
                        flag2 = true;
                        error4.setVisible(false);
                        error6.setVisible(false);
                        double a = Double.valueOf(textfield2.getText().trim()).doubleValue();
                    } catch (NumberFormatException ex) {
                        error4.setVisible(false);
                        error6.setVisible(true);
                        flag2 = false;
                    }

                }

                if ((((String) combobox1.getSelectedItem())).equals("<-----Please Choose a charge type----->")) {
                    error5.setVisible(true);
                    flag3 = false;
                } else {
                    flag3 = true;
                    error5.setVisible(false);
                }

                try {
                    if (rightElArr.length >= 1) {

                        flag4 = true;
                        error7.setVisible(false);

                    } else {
                        flag4 = false;
                        error7.setVisible(true);


                    }
                } catch (NullPointerException npe) {
                    flag4 = true;
                }


                if ((flag1 == true) && (flag2 == true) && (flag3 == true) && (flag4 == true)) {
                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (RYCOXv2.pkgList.get(i).getPkgCode().equalsIgnoreCase(PackagePanel.pkgtemp[0])) {
                            RYCOXv2.pkgList.get(i).setPkgName(textfield1.getText());
                            break;
                        }
                    }

                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (RYCOXv2.pkgList.get(i).getPkgCode().equalsIgnoreCase(PackagePanel.pkgtemp[0])) {
                            RYCOXv2.pkgList.get(i).setChargePrice(Double.valueOf(textfield2.getText().trim()).doubleValue());
                            break;
                        }
                    }

                    for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
                        if (RYCOXv2.pkgList.get(i).getPkgCode().equalsIgnoreCase(PackagePanel.pkgtemp[0])) {
                            RYCOXv2.pkgList.get(i).setChargeType(combobox1.getSelectedItem().toString());
                            break;
                        }
                    }

                    if (rightElArr != null) {
                        for (int i = 0; i < RYCOXv2.pckgingList.size(); i++) {
                            if (RYCOXv2.pckgingList.get(i).getPkgCode().equalsIgnoreCase(PackagePanel.pkgtemp[0])) {
                                RYCOXv2.pckgingList.remove(i);
                                i--;
                            }
                        }

                        for (int i = 0; i < rightElArr.length; i++) {
                            RYCOXv2.pckgingList.addLast(new Packaging(PackagePanel.pkgtemp[0], (rightElArr[i]).substring(0, 4)));
                        }
                    } else {
                        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
                            for (int j = 0; j < RYCOXv2.pckgingList.size(); j++) {
                                if (PackagePanel.pkgtemp[0].equalsIgnoreCase(RYCOXv2.pckgingList.get(j).getPkgCode())) {
                                    if (RYCOXv2.pckgingList.get(j).getProgCode().equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                                        selPrgList.add(RYCOXv2.pckgingList.get(j).getProgCode());
                                        ((DefaultListModel) rightModel).addElement(RYCOXv2.pckgingList.get(j).getProgCode() + "-" + RYCOXv2.prgList.get(i).getProgTitle());
                                    }
                                }
                            }
                        }
                    }

                    JOptionPane.showMessageDialog(null, "You have successfully changed the details of TV Package " + PackagePanel.pkgtemp[0] + " !", PackagePanel.pkgtemp[0] + " edited", JOptionPane.PLAIN_MESSAGE);
                    LogFile log = new LogFile(RYCOXv2.user, "has edited a TV Package '" + PackagePanel.pkgtemp[0] + "'.");
                    RYCOXv2.logList.add(log);
                    dispose();
                }
            }
        });

        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Stop Adding? All the changes will not be saved.", "Cancel Adding", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        container.setLayout(spring);

        container.add(label1);
        container.add(textfield3);

        spring.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield3, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield3, 15, SpringLayout.NORTH, container);


        container.add(label2);
        container.add(textfield1);
        container.add(error3);

        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 40, SpringLayout.SOUTH, label1);
        spring.putConstraint(SpringLayout.WEST, textfield1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 30, SpringLayout.SOUTH, textfield3);
        spring.putConstraint(SpringLayout.NORTH, error3, -27, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, error3, 260, SpringLayout.EAST, label2);

        container.add(label3);
        container.add(textfield2);
        container.add(error4);
        container.add(error6);
        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 45, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield2, 30, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.NORTH, error4, -26, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.WEST, error4, 251, SpringLayout.EAST, label3);
        spring.putConstraint(SpringLayout.NORTH, error6, -26, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.WEST, error6, 251, SpringLayout.EAST, label3);

        container.add(label4);
        container.add(combobox1);
        container.add(error5);
        spring.putConstraint(SpringLayout.WEST, label4, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label4, 40, SpringLayout.SOUTH, label3);
        spring.putConstraint(SpringLayout.WEST, combobox1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, combobox1, 30, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.NORTH, error5, -26, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.WEST, error5, 320, SpringLayout.EAST, label3);

        container.add(label5);
        container.add(label6);
        spring.putConstraint(SpringLayout.WEST, label5, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label5, 20, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, label6, 280, SpringLayout.EAST, label5);
        spring.putConstraint(SpringLayout.NORTH, label6, 20, SpringLayout.SOUTH, label4);

        container.add(leftListScroll);
        container.add(addBut);
        container.add(rmvBut);
        container.add(rightListScroll);
        container.add(error7);
        spring.putConstraint(SpringLayout.WEST, leftListScroll, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, leftListScroll, 45, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, addBut, 35, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, addBut, 52, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, rmvBut, 25, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, rmvBut, 10, SpringLayout.SOUTH, addBut);
        spring.putConstraint(SpringLayout.WEST, rightListScroll, 24, SpringLayout.EAST, rmvBut);
        spring.putConstraint(SpringLayout.NORTH, rightListScroll, 45, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, error7, 40, SpringLayout.EAST, label4);
        spring.putConstraint(SpringLayout.NORTH, error7, 345, SpringLayout.SOUTH, label4);

        container.add(button1);
        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button1, 222, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, button1, 362, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.WEST, button2, 222, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, button2, 362, SpringLayout.SOUTH, combobox1);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(690, 680);
        setLocation(300, 60);
        setVisible(true);
        PackagePanel.defaultButtonSet();
    }
}
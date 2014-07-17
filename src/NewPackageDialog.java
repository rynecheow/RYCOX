import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author Jia Jiun
 */
class NewPackageDialog extends JDialog {
    //Variable declaration

    private JLabel error3, error4, error5, error6, error7;
    private JTextField textfield1, textfield2;
    private JList<String> leftList;
    private JList<String> rightList;
    private JComboBox<String> combobox1;
    private String[] rightElArr;
    private LinkedList<String> rightElements;
    //End of variable declaration

    public NewPackageDialog(JFrame frame) {
        super(frame, "Package Management- Create new TV Package", true);
        setLocation(300, 150);
        JLabel label2 = new JLabel("Package Name: ");
        error3 = new JLabel("Blank input occured! Please reenter it.");
        JLabel label3 = new JLabel("Charge Price(RM): ");
        error4 = new JLabel("Blank input occured! Please reenter it.");
        error6 = new JLabel("Wrong input format. Please enter in numeric format!");
        JLabel label4 = new JLabel("Charge Type: ");
        error5 = new JLabel("Blank input occured! Please reenter it.");
        error7 = new JLabel("You must have at least one programme added to your package.");
        JLabel label5 = new JLabel("Programmes: ");
        JLabel label6 = new JLabel("Programmes inside this Package: ");

        textfield1 = new JTextField(15);
        textfield2 = new JTextField(15);
        combobox1 = new JComboBox<>();
        JButton button1 = new JButton("Submit");
        JButton button2 = new JButton("Cancel");
        JButton addBut = new JButton("", new ImageIcon(getClass().getResource("/resources/addtoright.png")));
        addBut.setBackground(new Color(23, 28, 31));
        JButton rmvBut = new JButton("", new ImageIcon(getClass().getResource("/resources/removetoleft.png")));
        rmvBut.setBackground(new Color(23, 28, 31));

        Color color = new Color(23, 28, 30);
        Color color1 = new Color(244, 219, 234);

        AbstractListModel<String> leftModel = new DefaultListModel<>();
        AbstractListModel<String> rightModel = new DefaultListModel<>();


        leftList = new JList<>(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(13);
        leftList.setFixedCellWidth(210);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rightList = new JList<>(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(13);
        rightList.setFixedCellWidth(210);
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);
        JScrollPane rightListScroll = new JScrollPane(rightList);
        rightListScroll.setViewportView(rightList);


        for (int i = 0; i < App.prgList.size(); i++) {

            if (!(App.prgList.get(i).getPrgStatus().equalsIgnoreCase("TERMINATED"))) {

                ((DefaultListModel) leftModel).addElement(App.prgList.get(i).getProgCode() + "-" + App.prgList.get(i).getProgTitle());
            }

        }

        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();

        String[] string1 = {"<-----Please Choose a charge type----->", "Monthly", "Quarterly", "Annually"};
        combobox1.setModel(new DefaultComboBoxModel<>(string1));
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
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);
        label6.setForeground(color1);

        addBut.addActionListener(e -> {
            try {
                int leftIndex = leftList.getSelectedIndex();
                String toBeAdded = (leftList.getModel().getElementAt(leftIndex));
                LinkedList<String> leftElements = new LinkedList<>();
                for (int p = 0; p < leftList.getModel().getSize(); p++) {
                    leftElements.add(leftList.getModel().getElementAt(p));
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
                    rightElements.add(rightList.getModel().getElementAt(z));
                }
                rightElements.add(toBeAdded);
                rightElArr = rightElements.toArray(new String[rightElements.size()]);
                rightList.setListData(rightElArr);
                rightList.repaint();
                rightList.revalidate();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You can only add a TV Programme from the left list.", "Adding error", JOptionPane.INFORMATION_MESSAGE);
            }


        });

        rmvBut.addActionListener(e -> {
            try {
                int rightIndex = rightList.getSelectedIndex();

                String toBeRemoved = (rightList.getModel().getElementAt(rightIndex));

                LinkedList<String> rightElements1 = new LinkedList<>();
                for (int p = 0; p < rightList.getModel().getSize(); p++) {
                    rightElements1.add(rightList.getModel().getElementAt(p));
                }

                for (int i = 0; i < rightElements1.size(); i++) {
                    if ((rightElements1.get(i)).equals(toBeRemoved)) {
                        rightElements1.remove(i);
                        break;
                    }
                }

                rightElArr = rightElements1.toArray(new String[rightElements1.size()]);
                rightList.setListData(rightElArr);
                rightList.revalidate();
                rightList.repaint();


                LinkedList<String> leftElements = new LinkedList<>();
                for (int z = 0; z < leftList.getModel().getSize(); z++) {
                    leftElements.add(leftList.getModel().getElementAt(z));
                }
                leftElements.add(toBeRemoved);
                String[] leftElArr = leftElements.toArray(new String[leftElements.size()]);
                leftList.setListData(leftElArr);
                leftList.repaint();
                leftList.revalidate();

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(null, "You can only remove a TV Programme from the right list when right list is filled in with TV Programme.", "Remove error", JOptionPane.INFORMATION_MESSAGE);
            }


        });
        button1.addActionListener(e -> {

            boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false;
            String a = "", b = "", z = "";
            double d = 0.0;

            if ((textfield1.getText().trim().equalsIgnoreCase(""))) {
                error3.setVisible(true);
                flag1 = false;
            } else {
                flag1 = true;
                error3.setVisible(false);
                b = textfield1.getText();
            }

            if ((textfield2.getText().trim().equalsIgnoreCase(""))) {
                error4.setVisible(true);
                error6.setVisible(false);
                flag2 = false;
            } else {

                try {
                    d = Double.valueOf(textfield2.getText().trim());
                    flag2 = true;
                    error4.setVisible(false);
                    error6.setVisible(false);
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

                z = combobox1.getSelectedItem().toString();
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

                flag4 = false;
                error7.setVisible(true);
            }


            if ((flag1) && (flag2) && (flag3) && (flag4)) {
                int existedID = 0;
                for (TVPackage ignored : App.pkgList) {
                    existedID += 1;
                }
                int newID = existedID + 1;
                String newIDS = Integer.toString(newID);
                if (existedID < 10) {
                    a = "P0" + newIDS;
                } else if (existedID >= 10) {
                    a = "P" + newIDS;
                }

                App.pkgList.addLast(new TVPackage(a, b, d, z, "ACTIVE"));

                for (String aRightElArr : rightElArr) {
                    App.pckgingList.addLast(new Packaging(a, (aRightElArr).substring(0, 4)));
                }

                JOptionPane.showMessageDialog(null, "You have successfully created a new TV Package " + a + " !", a + " added", JOptionPane.PLAIN_MESSAGE);
                LogFile log = new LogFile(App.user, "has created a new TV Package '" + a + "'.");
                App.logList.add(log);
                dispose();
            }
        });

        button2.addActionListener(e -> {
            int closeCf = JOptionPane.showConfirmDialog(null, "Stop Adding? All the changes will not be saved.", "Cancel Adding", JOptionPane.YES_NO_OPTION);
            if (closeCf == JOptionPane.YES_OPTION) {
                dispose();
            }
        });

        container.setLayout(spring);
        container.add(label2);
        container.add(textfield1);
        container.add(error3);

        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield1, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.NORTH, error3, -27, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, error3, 260, SpringLayout.EAST, label2);

        container.add(label3);
        container.add(textfield2);
        container.add(error4);
        container.add(error6);
        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 40, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label2);
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
        spring.putConstraint(SpringLayout.WEST, combobox1, 80, SpringLayout.EAST, label2);
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
        setSize(690, 600);
        setLocation(300, 60);
        setVisible(true);
        PackagePanel.defaultButtonSet();
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
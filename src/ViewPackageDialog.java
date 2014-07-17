import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

/**
 * @author Jia Jiun
 */

class ViewPackageDialog extends JDialog {

    public ViewPackageDialog(JFrame frame) {
        super(frame, "Package Management- View a TV Package", true);
        setLocation(300, 150);
        JLabel label1 = new JLabel("Package Code: ");
        JLabel label2 = new JLabel("Package Name: ");
        JLabel label3 = new JLabel("Charge Price(RM): ");
        JLabel label4 = new JLabel("Charge Type: ");
        JLabel label5 = new JLabel("TV Programmes included ");
        JLabel label6 = new JLabel("Start Date: ");
        JLabel label7 = new JLabel("Termination Date: ");


        JTextField textfield1 = new JTextField(15);
        JTextField textfield2 = new JTextField(15);
        JTextField textfield3 = new JTextField(15);
        JTextField textfield4 = new JTextField(15);
        JTextField textfield5 = new JTextField(15);
        JTextField textfield6 = new JTextField(15);
        textfield3.setEditable(false);
        textfield1.setEditable(false);
        textfield2.setEditable(false);
        textfield4.setEditable(false);
        textfield5.setEditable(false);
        textfield6.setEditable(false);


        JButton button2 = new JButton("Ok");


        Color color = new Color(23, 28, 30);
        Color color1 = new Color(244, 219, 234);
        Color color2 = new Color(241, 236, 236);


        LinkedList<String> leftPrgList = new LinkedList<>();
        AbstractListModel leftModel = new DefaultListModel();
        AbstractListModel rightModel = new DefaultListModel();
        for (int i = 0; i < App.prgList.size(); i++) {
            for (int j = 0; j < App.pckgingList.size(); j++) {
                if (PackagePanel.pkgtemp[0].equalsIgnoreCase(App.pckgingList.get(j).getPkgCode())) {
                    if (App.pckgingList.get(j).getProgCode().equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                        leftPrgList.add(App.pckgingList.get(j).getPkgCode());
                        ((DefaultListModel) leftModel).addElement(App.pckgingList.get(j).getProgCode() + "-" + App.prgList.get(i).getProgTitle());
                    }
                }
            }
        }
        String[] leftPrg = leftPrgList.toArray(new String[leftPrgList.size()]);

        for (int i = 0; i < App.pkgList.size(); i++) {
            boolean check = false;
            for (int j = 0; j < leftPrgList.size(); j++) {
                if (leftPrg[j].equalsIgnoreCase(App.prgList.get(i).getProgCode())) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                ((DefaultListModel) rightModel).addElement(App.prgList.get(i).getProgCode());
            }
        }

        JList leftList = new JList(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(6);
        leftList.setBackground(color2);
        leftList.setFixedCellWidth(450);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JList rightList = new JList(rightModel);


        JScrollPane leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);


        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();

        label1.setForeground(color1);
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);
        label6.setForeground(color1);
        label7.setForeground(color1);


        textfield3.setText(PackagePanel.pkgtemp[0]);
        textfield1.setText(PackagePanel.pkgtemp[1]);
        textfield2.setText(PackagePanel.pkgtemp[2]);
        textfield4.setText(PackagePanel.pkgtemp[3]);
        textfield5.setText(PackagePanel.pkgtemp[4]);
        textfield6.setText(PackagePanel.pkgtemp[5]);
        textfield1.setBackground(color2);
        textfield2.setBackground(color2);
        textfield3.setBackground(color2);
        textfield4.setBackground(color2);
        textfield5.setBackground(color2);
        textfield6.setBackground(color2);


        button2.addActionListener(e -> dispose());

        container.setLayout(spring);

        container.add(label1);
        container.add(textfield3);

        spring.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield3, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield3, 15, SpringLayout.NORTH, container);


        container.add(label2);
        container.add(textfield1);


        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 40, SpringLayout.SOUTH, label1);
        spring.putConstraint(SpringLayout.WEST, textfield1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 30, SpringLayout.SOUTH, textfield3);


        container.add(label3);
        container.add(textfield2);

        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 45, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield2, 30, SpringLayout.SOUTH, textfield1);


        container.add(label4);
        container.add(textfield4);

        spring.putConstraint(SpringLayout.WEST, label4, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label4, 40, SpringLayout.SOUTH, label3);
        spring.putConstraint(SpringLayout.WEST, textfield4, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield4, 30, SpringLayout.SOUTH, textfield2);


        container.add(label6);
        container.add(textfield5);

        spring.putConstraint(SpringLayout.WEST, label6, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label6, 44, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, textfield5, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield5, 30, SpringLayout.SOUTH, textfield4);

        container.add(label7);
        container.add(textfield6);

        spring.putConstraint(SpringLayout.WEST, label7, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label7, 44, SpringLayout.SOUTH, label6);
        spring.putConstraint(SpringLayout.WEST, textfield6, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield6, 30, SpringLayout.SOUTH, textfield5);


        container.add(leftListScroll);
        container.add(label5);
        spring.putConstraint(SpringLayout.NORTH, label5, 30, SpringLayout.SOUTH, label7);
        spring.putConstraint(SpringLayout.WEST, label5, 63, SpringLayout.EAST, label7);
        spring.putConstraint(SpringLayout.WEST, leftListScroll, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, leftListScroll, 45, SpringLayout.SOUTH, label7);


        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button2, 118, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, button2, 15, SpringLayout.SOUTH, leftListScroll);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(530, 616);
        setLocation(400, 60);
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
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
class ViewServiceDialog extends JDialog {

    // Variable declaration
    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel, rightLabel, termDateLabel, createDateLabel;
    private JTextField idInput, scInput, dcInput, termDateInput, createDateInput;
    private JTextArea addInput;
    private JButton closeBut;
    private static JList rightList;
    private JScrollPane addScroll;
    private static JScrollPane rightListScroll;
    private JSeparator separator, separator2;
    private Color bColor = new Color(23, 28, 30);
    private Color nColor = new Color(51, 60, 64);
    private Color fColor = new Color(255, 255, 255);
    private LinkedList<String> leftPkgList;
    private LinkedList<String> selPkgList;
    private AbstractListModel leftModel;
    private AbstractListModel rightModel;
    private String[] leftElArr;
    private String[] rightElArr;
    private String[] selPkg;
    private String addPkg;
    private int subsNo;
    private Border line = BorderFactory.createLineBorder(nColor);
    // end of variable declaration

    public ViewServiceDialog(JFrame parent) {
        super(parent, "Service - Edit Service", true);
        setLocation(300, 150);
        idLabel = new JLabel("Client ID: ");
        idLabel.setForeground(fColor);
        scLabel = new JLabel("Smart Card Number: ");
        scLabel.setForeground(fColor);
        dcLabel = new JLabel("Decoder Number: ");
        dcLabel.setForeground(fColor);
        addLabel = new JLabel("Address: ");
        addLabel.setForeground(fColor);
        rightLabel = new JLabel("Packages Subscribed: ");
        rightLabel.setForeground(fColor);
        termDateLabel = new JLabel("Termination Date: ");
        termDateLabel.setForeground(fColor);
        createDateLabel = new JLabel("Creation Date: ");
        createDateLabel.setForeground(fColor);
        idInput = new JTextField(20);
        idInput.setText(ServicePanel.temp[1]);
        idInput.setEditable(false);
        idInput.setBackground(nColor);
        idInput.setForeground(fColor);
        idInput.setBorder(line);
        scInput = new JTextField(20);
        scInput.setText(ServicePanel.temp[0]);
        scInput.setEditable(false);
        scInput.setBackground(nColor);
        scInput.setForeground(fColor);
        scInput.setBorder(line);
        dcInput = new JTextField(20);
        dcInput.setText(ServicePanel.temp[2]);
        dcInput.setEditable(false);
        dcInput.setBackground(nColor);
        dcInput.setForeground(fColor);
        dcInput.setBorder(line);
        addInput = new JTextArea(5, 20);
        addInput.setText(ServicePanel.temp[3]);
        addInput.setEditable(false);
        addInput.setBackground(nColor);
        addInput.setForeground(fColor);
        addInput.setBorder(line);
        termDateInput = new JTextField(20);
        createDateInput = new JTextField(20);
        closeBut = new JButton("Close");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        addScroll.setBorder(line);
//		separator = new JSeparator(JSeparator.VERTICAL);
//		separator.setPreferredSize(new Dimension(2,270));
//		separator2 = new JSeparator(JSeparator.HORIZONTAL);
//		separator2.setPreferredSize(new Dimension(680,2));
        for (int i = 0; i < App.servList.size(); i++) {
            if (ServicePanel.temp[0].equalsIgnoreCase(App.servList.get(i).getSmartCardNo())) {
                termDateInput.setText(App.servList.get(i).getTermDate());
                termDateInput.setEditable(false);
                termDateInput.setForeground(fColor);
                termDateInput.setBackground(nColor);
                termDateInput.setBorder(line);
                createDateInput.setText(App.servList.get(i).getRegDate());
                createDateInput.setEditable(false);
                createDateInput.setForeground(fColor);
                createDateInput.setBackground(nColor);
                createDateInput.setBorder(line);
            }
        }

        selPkgList = new LinkedList<>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        for (int i = 0; i < App.pkgList.size(); i++) {
            for (int j = 0; j < App.subsList.size(); j++) {
                if (ServicePanel.temp[0].equalsIgnoreCase(App.subsList.get(j).getSmartCardNo())) {
                    if (App.subsList.get(j).getPkgCode().equalsIgnoreCase(App.pkgList.get(i).getPkgCode())) {
                        selPkgList.add(App.subsList.get(j).getPkgCode());
                        ((DefaultListModel) rightModel).addElement(App.subsList.get(j).getPkgCode() + "\t\t\t" + App.pkgList.get(i).getPkgName());
                    }
                }
            }
        }
        selPkg = selPkgList.toArray(new String[selPkgList.size()]);

        for (int i = 0; i < App.pkgList.size(); i++) {
            boolean check = false;
            for (int j = 0; j < selPkgList.size(); j++) {
                if (selPkg[j].equalsIgnoreCase(App.pkgList.get(i).getPkgCode())) {
                    check = true;
                    break;
                }
            }
            if (check == false) {
                ((DefaultListModel) leftModel).addElement(App.pkgList.get(i).getPkgCode() + "\t\t\t" + App.pkgList.get(i).getPkgName());
            }
        }

        rightList = new JList(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(16);
        rightList.setFixedCellWidth(160);
        rightList.setSelectionBackground(bColor);
        //rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightList.setBackground(nColor);
        rightList.setForeground(fColor);
        rightList.setBorder(line);

        rightListScroll = new JScrollPane(rightList);
        rightListScroll.setViewportView(rightList);
        rightListScroll.setBorder(line);
//		rightListScroll.setViewportBorder(Border );

        Container c = getContentPane();
        SpringLayout spring = new SpringLayout();
        Border textBorder = addInput.getBorder();

        c.setLayout(spring);
        c.setBackground(new Color(23, 28, 30));
        c.add(idLabel);
        c.add(idInput);
        spring.putConstraint(SpringLayout.WEST, idLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, idLabel, 35, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, idInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, idInput, 35, SpringLayout.NORTH, c);
        c.add(scLabel);
        c.add(scInput);
        spring.putConstraint(SpringLayout.WEST, scLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, scLabel, 35, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 35, SpringLayout.SOUTH, idLabel);
        c.add(dcLabel);
        c.add(dcInput);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 35, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 35, SpringLayout.SOUTH, scLabel);
        c.add(createDateLabel);
        c.add(createDateInput);
        spring.putConstraint(SpringLayout.WEST, createDateLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, createDateLabel, 35, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, createDateInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, createDateInput, 35, SpringLayout.SOUTH, dcLabel);
        c.add(termDateLabel);
        c.add(termDateInput);
        spring.putConstraint(SpringLayout.WEST, termDateLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, termDateLabel, 35, SpringLayout.SOUTH, createDateLabel);
        spring.putConstraint(SpringLayout.WEST, termDateInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, termDateInput, 35, SpringLayout.SOUTH, createDateLabel);
        c.add(addLabel);
        c.add(addScroll);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 35, SpringLayout.SOUTH, termDateLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 35, SpringLayout.SOUTH, termDateLabel);
//		c.add(separator);
//		spring.putConstraint(SpringLayout.WEST, separator, 30, SpringLayout.EAST, idInput);
//		spring.putConstraint(SpringLayout.NORTH, separator, 15, SpringLayout.NORTH, c);
        c.add(rightLabel);
        spring.putConstraint(SpringLayout.WEST, rightLabel, 30, SpringLayout.EAST, idInput);
        spring.putConstraint(SpringLayout.NORTH, rightLabel, 25, SpringLayout.NORTH, c);
        c.add(rightListScroll);
        spring.putConstraint(SpringLayout.WEST, rightListScroll, 28, SpringLayout.EAST, idInput);
        spring.putConstraint(SpringLayout.NORTH, rightListScroll, 50, SpringLayout.NORTH, c);
//		c.add(separator2);
//		spring.putConstraint(SpringLayout.WEST, separator2, 5, SpringLayout.WEST, c);
//		spring.putConstraint(SpringLayout.NORTH, separator2, 25, SpringLayout.SOUTH, rightListScroll);
        c.add(closeBut);
        spring.putConstraint(SpringLayout.WEST, closeBut, 350, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, closeBut, 30, SpringLayout.SOUTH, addScroll);

        ButtonHandler handler = new ButtonHandler();
        closeBut.addActionListener(handler);
        setSize(700, 520);
        ServicePanel.defaultButtonSet();
    }

    /**
     * @author LiHao This class is used for handler the button listener.
     */
    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == closeBut) {
                dispose();
            }
        }
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
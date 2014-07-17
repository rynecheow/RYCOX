import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Jia Jiun
 */
@SuppressWarnings("serial")
class NewProgrammeDialog extends JDialog {
    //Variable declaration

    private JLabel label2, label3, label4, label5, label6, label7;
    private JLabel error3, error4, error5, error6, error7, error8;
    private JTextField textfield2, textfield3, textfield4;
    private JTextArea textarea1;
    @SuppressWarnings("rawtypes")
    private JComboBox combobox1, combobox2;
    private JButton button1, button2;
    private Color color, color1;
    private JScrollPane scrollpane1;
    //End of variable declaration

    @SuppressWarnings({"rawtypes", "unchecked"})
    public NewProgrammeDialog(JFrame frame) {
        super(frame, "Programme Management- Create new TV Programme", true);
        setLocation(300, 150);
        label2 = new JLabel("Programme Title: ");
        error3 = new JLabel("Blank input occured! Please reenter it.");
        label3 = new JLabel("Description: ");
        error4 = new JLabel("Blank input occured! Please reenter it.");
        label4 = new JLabel("Content Origin: ");
        error5 = new JLabel("Blank input occured! Please reenter it.");
        label5 = new JLabel("Viewer Status: ");
        error6 = new JLabel("Please choose one of the viewer status.");
        label6 = new JLabel("Type: ");
        error7 = new JLabel("Please choose one of the programme type.");
        label7 = new JLabel("Notes: ");
        error8 = new JLabel("Blank input occured! Please reenter it.");

        textfield2 = new JTextField(15);
        textarea1 = new JTextArea(5, 20);
        textfield3 = new JTextField(15);
        combobox1 = new JComboBox();
        combobox2 = new JComboBox();
        textfield4 = new JTextField(15);
        button1 = new JButton("Submit");
        button2 = new JButton("Cancel");
        color = new Color(23, 28, 30);
        color1 = new Color(244, 219, 234);

        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();
        Border textBorder = textfield2.getBorder();
        textarea1.setBorder(textBorder);
        scrollpane1 = new JScrollPane(textarea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        String[] string1 = {"<-----Please Choose a viewer status----->", "U", "PG13", "18SG"};
        String[] string2 = {"<----Please Choose a programme type---->", "Movie", "Series", "Comedy", "Concert"};
        combobox1.setModel(new DefaultComboBoxModel(string1));
        combobox2.setModel(new DefaultComboBoxModel(string2));

        error3.setForeground(Color.RED);
        error4.setForeground(Color.RED);
        error5.setForeground(Color.RED);
        error6.setForeground(Color.RED);
        error7.setForeground(Color.RED);
        error8.setForeground(Color.RED);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);
        error6.setVisible(false);
        error7.setVisible(false);
        error8.setVisible(false);
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);
        label6.setForeground(color1);
        label7.setForeground(color1);
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
                String a = "", b = "", y = "", d = "", z = "", f = "", g = "";


                if ((textfield2.getText().trim() == null) || (textfield2.getText().trim().equalsIgnoreCase(""))) {
                    error3.setVisible(true);
                    flag1 = false;
                } else {
                    flag1 = true;
                    error3.setVisible(false);
                    b = textfield2.getText().trim();
                }
                if ((textarea1.getText().trim() == null) || (textarea1.getText().trim().equalsIgnoreCase(""))) {
                    error4.setVisible(true);
                    flag2 = false;
                } else {
                    flag2 = true;
                    error4.setVisible(false);
                    y = textarea1.getText();
                }

                if ((textfield3.getText().trim() == null) || (textfield3.getText().trim().equalsIgnoreCase(""))) {
                    error5.setVisible(true);
                    flag3 = false;
                } else {
                    flag3 = true;
                    error5.setVisible(false);
                    d = textfield3.getText();
                }
                if ((((String) combobox1.getSelectedItem())).equals("<-----Please Choose a viewer status----->")) {
                    error6.setVisible(true);
                    flag4 = false;
                } else {
                    flag4 = true;
                    error6.setVisible(false);

                    z = combobox1.getSelectedItem().toString();
                }

                if ((((String) combobox2.getSelectedItem())).equals("<----Please Choose a programme type---->")) {
                    error7.setVisible(true);
                    flag5 = false;
                } else {
                    flag5 = true;
                    error7.setVisible(false);

                    f = combobox2.getSelectedItem().toString();


                }

                if ((textfield4.getText().trim() == null) || (textfield4.getText().trim().equalsIgnoreCase(""))) {
                    error8.setVisible(true);
                    flag6 = false;
                } else {
                    flag6 = true;
                    error8.setVisible(false);
                    g = textfield4.getText();
                }

                if ((flag1 == true) && (flag2 == true) && (flag3 == true) && (flag4 == true) && (flag5 == true) && (flag6 == true)) {
                    int existedID = 0;
                    for (int i = 0; i < App.prgList.size(); i++) {
                        existedID += 1;
                    }
                    int newID = existedID + 1;
                    String newIDS = Integer.toString(newID);
                    if (existedID < 10) {
                        a = "F00" + newIDS;
                    } else if (existedID < 99) {
                        a = "F0" + newIDS;
                    } else if ((existedID >= 99) && (existedID <= 999)) {
                        a = "F" + newIDS;
                    }

                    App.prgList.addLast(new TVProgramme(a, b, y, d, "ACTIVE", z, f, g));
                    JOptionPane.showMessageDialog(null, "You have successfully created a new TV Programme with programme code " + a + " !", a + " added", JOptionPane.PLAIN_MESSAGE);
                    LogFile log = new LogFile(App.user, "has created a new TV Programme '" + a + "'.");
                    App.logList.add(log);

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


        container.add(label2);
        container.add(textfield2);
        container.add(error3);
        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, textfield2, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.NORTH, error3, 5, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.WEST, error3, 80, SpringLayout.EAST, label2);
        container.add(label3);
        container.add(scrollpane1);
        container.add(error4);
        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 43, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, scrollpane1, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, scrollpane1, 28, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.NORTH, error4, 5, SpringLayout.SOUTH, scrollpane1);
        spring.putConstraint(SpringLayout.WEST, error4, 109, SpringLayout.EAST, label3);
        container.add(label4);
        container.add(textfield3);
        container.add(error5);
        spring.putConstraint(SpringLayout.WEST, label4, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label4, 115, SpringLayout.SOUTH, label3);
        spring.putConstraint(SpringLayout.WEST, textfield3, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, textfield3, 25, SpringLayout.SOUTH, scrollpane1);
        spring.putConstraint(SpringLayout.NORTH, error5, 5, SpringLayout.SOUTH, textfield3);
        spring.putConstraint(SpringLayout.WEST, error5, 93, SpringLayout.EAST, label4);
        container.add(label5);
        container.add(combobox1);
        container.add(error6);
        spring.putConstraint(SpringLayout.WEST, label5, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label5, 41, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, combobox1, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, combobox1, 29, SpringLayout.SOUTH, textfield3);
        spring.putConstraint(SpringLayout.NORTH, error6, 5, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.WEST, error6, 97, SpringLayout.EAST, label5);
        container.add(label6);
        container.add(combobox2);
        container.add(error7);
        spring.putConstraint(SpringLayout.WEST, label6, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label6, 40, SpringLayout.SOUTH, label5);
        spring.putConstraint(SpringLayout.WEST, combobox2, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, combobox2, 29, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.NORTH, error7, 5, SpringLayout.SOUTH, combobox2);
        spring.putConstraint(SpringLayout.WEST, error7, 147, SpringLayout.EAST, label6);
        container.add(label7);
        container.add(textfield4);
        container.add(error8);
        spring.putConstraint(SpringLayout.WEST, label7, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label7, 36, SpringLayout.SOUTH, label6);
        spring.putConstraint(SpringLayout.WEST, textfield4, 80, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, textfield4, 27, SpringLayout.SOUTH, combobox2);
        spring.putConstraint(SpringLayout.NORTH, error8, 5, SpringLayout.SOUTH, textfield4);
        spring.putConstraint(SpringLayout.WEST, error8, 141, SpringLayout.EAST, label7);
        container.add(button1);
        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button1, 200, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, button1, 42, SpringLayout.SOUTH, textfield4);
        spring.putConstraint(SpringLayout.WEST, button2, 200, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, button2, 42, SpringLayout.SOUTH, textfield4);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(580, 550);
        setLocation(400, 60);
        setVisible(true);
        ProgrammePanel.defaultButtonSet();
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
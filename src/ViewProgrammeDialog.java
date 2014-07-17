import javax.swing.*;
import java.awt.*;

/**
 * @author Jia Jiun
 */
class ViewProgrammeDialog extends JDialog {

    //End of variable declaration

    public ViewProgrammeDialog(JFrame frame) {
        super(frame, "Programme details", true);
        setLocation(300, 150);
        JLabel label1 = new JLabel("Programme Code: ");
        JLabel label2 = new JLabel("Programme Title: ");
        JLabel label3 = new JLabel("Description: ");
        JLabel label4 = new JLabel("Content Origin: ");
        JLabel label5 = new JLabel("Viewer Status: ");
        JLabel label6 = new JLabel("Type: ");
        JLabel label7 = new JLabel("Notes: ");
        JLabel label8 = new JLabel("Status: ");
        JLabel label9 = new JLabel("Termination Date: ");
        JLabel label10 = new JLabel("Creation Date: ");
        JLabel label11 = new JLabel(ProgrammePanel.progtemp[0]);
        JLabel label12 = new JLabel(ProgrammePanel.progtemp[1]);
        JLabel label13 = new JLabel(ProgrammePanel.progtemp[2]);
        JLabel label14 = new JLabel(ProgrammePanel.progtemp[3]);
        JLabel label15 = new JLabel(ProgrammePanel.progtemp[7]);
        JLabel label16 = new JLabel(ProgrammePanel.progtemp[8]);
        JLabel label17 = new JLabel(ProgrammePanel.progtemp[9]);
        JLabel label18 = new JLabel(ProgrammePanel.progtemp[6]);
        JLabel label19 = new JLabel(ProgrammePanel.progtemp[5]);
        JLabel label20 = new JLabel(ProgrammePanel.progtemp[4]);
        JButton button1 = new JButton("OK");
        Color color = new Color(23, 28, 30);
        Color color1 = new Color(244, 219, 234);

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
        label8.setForeground(color1);
        label9.setForeground(Color.yellow);
        label10.setForeground(Color.yellow);
        label11.setIcon(new ImageIcon(getClass().getResource("/resources/label1.png")));
        label11.setIconTextGap(-180);
        label11.setForeground(Color.BLACK);
        label12.setForeground(Color.ORANGE);
        label13.setForeground(Color.ORANGE);
        label14.setForeground(Color.ORANGE);
        label15.setForeground(Color.ORANGE);
        label16.setForeground(Color.ORANGE);
        label17.setForeground(Color.ORANGE);
        label18.setForeground(Color.ORANGE);
        label19.setForeground(Color.ORANGE);
        label20.setForeground(Color.ORANGE);
        label15.setBackground(Color.GRAY);
        label14.setBackground(Color.GRAY);


        button1.addActionListener(e -> dispose());

        container.setLayout(spring);
        container.add(label1);
        container.add(label11);
        spring.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, label11, 30, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label11, 15, SpringLayout.NORTH, container);
        container.add(label2);
        container.add(label12);

        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 56, SpringLayout.SOUTH, label1);
        spring.putConstraint(SpringLayout.WEST, label12, 30, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label12, 30, SpringLayout.SOUTH, label11);


        container.add(label4);
        container.add(label14);

        spring.putConstraint(SpringLayout.WEST, label4, 270, SpringLayout.WEST, label11);
        spring.putConstraint(SpringLayout.NORTH, label4, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, label14, 450, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label14, 15, SpringLayout.NORTH, container);

        container.add(label5);
        container.add(label15);

        spring.putConstraint(SpringLayout.WEST, label5, 270, SpringLayout.WEST, label12);
        spring.putConstraint(SpringLayout.NORTH, label5, 27, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, label15, 450, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label15, 29, SpringLayout.SOUTH, label14);

        container.add(label6);
        container.add(label16);

        spring.putConstraint(SpringLayout.WEST, label6, 270, SpringLayout.WEST, label12);
        spring.putConstraint(SpringLayout.NORTH, label6, 30, SpringLayout.SOUTH, label5);
        spring.putConstraint(SpringLayout.WEST, label16, 450, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label16, 29, SpringLayout.SOUTH, label15);

        container.add(label7);
        container.add(label17);

        spring.putConstraint(SpringLayout.WEST, label7, 270, SpringLayout.WEST, label12);
        spring.putConstraint(SpringLayout.NORTH, label7, 26, SpringLayout.SOUTH, label6);
        spring.putConstraint(SpringLayout.WEST, label17, 450, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label17, 27, SpringLayout.SOUTH, label16);

        container.add(label8);
        container.add(label18);

        spring.putConstraint(SpringLayout.WEST, label8, 270, SpringLayout.WEST, label12);
        spring.putConstraint(SpringLayout.NORTH, label8, 26, SpringLayout.SOUTH, label7);
        spring.putConstraint(SpringLayout.WEST, label18, 450, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label18, 27, SpringLayout.SOUTH, label17);

        container.add(label3);
        container.add(label13);

        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 25, SpringLayout.SOUTH, label20);
        spring.putConstraint(SpringLayout.WEST, label13, -40, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, label13, -17, SpringLayout.SOUTH, label3);

        container.add(label9);
        container.add(label19);

        spring.putConstraint(SpringLayout.WEST, label9, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label9, 36, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, label19, 30, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label19, 10, SpringLayout.SOUTH, label9);

        container.add(label10);
        container.add(label20);

        spring.putConstraint(SpringLayout.WEST, label10, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label10, 10, SpringLayout.SOUTH, label19);
        spring.putConstraint(SpringLayout.WEST, label20, 30, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label20, 10, SpringLayout.SOUTH, label10);

        container.add(button1);

        spring.putConstraint(SpringLayout.WEST, button1, 350, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, button1, 70, SpringLayout.SOUTH, label20);


        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(780, 420);
        setLocation(200, 60);
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
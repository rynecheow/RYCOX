import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class TerminateProgramme extends JDialog {
    private JLabel label1;
    private JLabel error1, error2, error3;
    private JButton button1, button2;
    private JTextField textfield1;
    private Color color, color1;

    public TerminateProgramme(JFrame frame) {
        super(frame, "Programme Management- Terminate a TV Programme", true);
        label1 = new JLabel("Programme Code: ");
        error1 = new JLabel("Sorry, your programme code's format is invalid. Please reenter it.");
        error2 = new JLabel("Blank input occured! Please reenter it.");
        error3 = new JLabel("Programme Code doesn't exist or has been terminated. Please retry.");
        button1 = new JButton("SUBMIT");
        button2 = new JButton("CANCEL");
        textfield1 = new JTextField(15);
        color = new Color(23, 28, 30);
        color1 = new Color(244, 219, 234);


        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();
        error1.setForeground(Color.RED);
        error2.setForeground(Color.RED);
        error3.setForeground(Color.RED);
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);
        label1.setForeground(color1);
        button1.addActionListener(new ActionListener() {

            @SuppressWarnings("unused")
            public void actionPerformed(ActionEvent e) {

                boolean flag = false, flag2 = false, flag7 = false, flag8 = false;
                String input = "";
                if ((textfield1.getText().trim() == null) || (textfield1.getText().trim().equalsIgnoreCase(""))) {
                    error2.setVisible(true);
                    error1.setVisible(false);
                    error3.setVisible(false);
                    flag = false;
                } else if (!((textfield1.getText().trim()).matches("^[Ff][0-9]{3}$"))) {
                    error2.setVisible(false);
                    error3.setVisible(false);
                    error1.setVisible(true);
                    flag = false;
                } else flag = true;

                if (flag == true) {

                    for (int count3 = 0; count3 < RYCOXv2.prgList.size(); count3++) {
                        if ((RYCOXv2.prgList.get(count3).getProgCode()).equalsIgnoreCase(textfield1.getText().trim())) {

                            flag7 = true;
                            break;
                        } else flag7 = false;
                    }


                    if (flag7 == false) {

                        error3.setVisible(true);
                        error2.setVisible(false);
                        error1.setVisible(false);

                    }

                }

                if (flag7 == true) {
                    flag8 = true;
                    error2.setVisible(false);
                    error1.setVisible(false);
                    error3.setVisible(false);
                    input = textfield1.getText().trim();
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure you would like to terminate TV Programme " + input + " ?", "Programme Code found!", JOptionPane.WARNING_MESSAGE);
                    if (choice == JOptionPane.YES_OPTION) {
                        for (int counts = 0; counts < RYCOXv2.prgList.size(); counts++) {
                            if (input.equalsIgnoreCase(RYCOXv2.prgList.get(counts).getProgCode())) {
                                if (RYCOXv2.prgList.get(counts).getPrgStatus().equalsIgnoreCase("Active")) {

                                    RYCOXv2.prgList.get(counts).setPrgStatus("Inactive");
                                    JOptionPane.showMessageDialog(null, "TV Programme " + input + " is terminated successfully", "Termination successful!", JOptionPane.PLAIN_MESSAGE);
                                    flag2 = true;
                                    break;

                                } else {
                                    flag2 = false;
                                    JOptionPane.showMessageDialog(null, "TV Programme " + input + " has been terminated. It cannot be terminated twice!", "Termination unsuccessful!", JOptionPane.PLAIN_MESSAGE);
                                    LogFile log = new LogFile(RYCOXv2.user, "has terminated a TV Programme '" + input + "'.");
                                    RYCOXv2.logList.add(log);
                                }
                            }
                        }
                        if (flag2 == true) {
                            dispose();
                        }
                    } else {
                    }


                } else flag8 = false;


            }

        }

        );

        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Stop Terminating? All the changes will not be saved.", "Cancel Terminating", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {

                    dispose();
                } else {
                }

            }


        });


        container.setLayout(spring);
        container.add(label1);
        container.add(textfield1);
        container.add(error2);
        container.add(error1);
        container.add(error3);
        spring.putConstraint(SpringLayout.WEST, label1, 32, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 25, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield1, 32, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.NORTH, error2, 5, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, error2, 5, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, error1, 5, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, error1, 5, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, error3, 5, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, error3, 5, SpringLayout.WEST, container);

        container.add(button1);
        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button1, 95, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, button1, 42, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.WEST, button2, 25, SpringLayout.EAST, button1);
        spring.putConstraint(SpringLayout.NORTH, button2, 42, SpringLayout.SOUTH, textfield1);


        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(400, 170);
        setLocation(480, 260);
        setVisible(true);
    }


}
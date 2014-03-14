import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

class servAdd extends JFrame {
    private JLabel l1, l2, l3, l4;
    private JTextField t1, t2, t3;
    private JTextArea t4;
    private JButton b1, b2;

    public servAdd() {
        super("Service - Create new Service");
        l1 = new JLabel("Client ID: ");
        l2 = new JLabel("Smart Card Number: ");
        l3 = new JLabel("Decoder Number: ");
        l4 = new JLabel("Address: ");
        t1 = new JTextField("1", 15);
        t1.setEditable(false);
        t1.setBackground(Color.DARK_GRAY);
        t1.setForeground(Color.WHITE);
        t2 = new JTextField(15);
        t3 = new JTextField(15);
        t4 = new JTextArea(5, 20);
        b1 = new JButton("OK");
        b2 = new JButton("Cancel");

        Container c = getContentPane();
        SpringLayout spring = new SpringLayout();
        Border textBorder = t1.getBorder();

        c.setLayout(spring);
        c.add(l1);
        c.add(t1);
        spring.putConstraint(SpringLayout.WEST, l1, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, l1, 15, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, t1, 130, SpringLayout.EAST, l1);
        spring.putConstraint(SpringLayout.NORTH, t1, 10, SpringLayout.NORTH, c);
        c.add(l2);
        c.add(t2);
        spring.putConstraint(SpringLayout.WEST, l2, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, l2, 32, SpringLayout.SOUTH, l1);
        spring.putConstraint(SpringLayout.WEST, t2, 130, SpringLayout.EAST, l1);
        spring.putConstraint(SpringLayout.NORTH, t2, 20, SpringLayout.SOUTH, t1);
        c.add(l3);
        c.add(t3);
        spring.putConstraint(SpringLayout.WEST, l3, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, l3, 32, SpringLayout.SOUTH, l2);
        spring.putConstraint(SpringLayout.WEST, t3, 130, SpringLayout.EAST, l1);
        spring.putConstraint(SpringLayout.NORTH, t3, 20, SpringLayout.SOUTH, t2);
        c.add(l4);
        c.add(t4);
        t4.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, l4, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, l4, 32, SpringLayout.SOUTH, l3);
        spring.putConstraint(SpringLayout.WEST, t4, 133, SpringLayout.EAST, l1);
        spring.putConstraint(SpringLayout.NORTH, t4, 20, SpringLayout.SOUTH, t3);
        c.add(b1);
        c.add(b2);
        spring.putConstraint(SpringLayout.WEST, b1, 160, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, b1, 30, SpringLayout.SOUTH, t4);
        spring.putConstraint(SpringLayout.WEST, b2, 160, SpringLayout.EAST, l1);
        spring.putConstraint(SpringLayout.NORTH, b2, 30, SpringLayout.SOUTH, t4);
    }

    public static void main(String[] args) {
        servAdd g = new servAdd();
        g.setSize(500, 400);
        g.setVisible(true);
        g.setResizable(false);
    }
}
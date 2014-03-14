import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class ViewServiceDialog extends JDialog {
    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton okBut;
    private JScrollPane addScroll;
    private JSeparator separator;
    @SuppressWarnings("rawtypes")
    private JComboBox statusBox;
    private String[] status = {"Active", "Inactive", "Barred"};
    private String smartCard, decoder, address, actualStatus;
    private Color fColor = new Color(255, 255, 255);
    private Color bColor = new Color(23, 28, 30);

    @SuppressWarnings({"unchecked", "rawtypes"})
    public ViewServiceDialog(JFrame parent) {
        super(parent, "Service - Edit Service", true);
        idLabel = new JLabel("Client ID: ");
        idLabel.setForeground(fColor);
        scLabel = new JLabel("Smart Card Number: ");
        scLabel.setForeground(fColor);
        dcLabel = new JLabel("Decoder Number: ");
        dcLabel.setForeground(fColor);
        statusLabel = new JLabel("Status: ");
        statusLabel.setForeground(fColor);
        addLabel = new JLabel("Address: ");
        addLabel.setForeground(fColor);

        idInput = new JTextField(25);
        idInput.setText(ServicePanel.temp[1]);
        idInput.setBackground(bColor);
        idInput.setForeground(fColor);
        idInput.setEditable(false);
        idInput.setBorder(null);
        scInput = new JTextField(25);
        scInput.setText(ServicePanel.temp[0]);
        scInput.setEditable(false);
        scInput.setBackground(bColor);
        scInput.setForeground(fColor);
        dcInput = new JTextField(25);
        dcInput.setText(ServicePanel.temp[2]);
        dcInput.setEditable(false);
        dcInput.setBackground(bColor);
        dcInput.setForeground(fColor);
        addInput = new JTextArea(5, 25);
        addInput.setText(ServicePanel.temp[3]);
        addInput.setEditable(false);
        addInput.setBackground(bColor);
        addInput.setForeground(fColor);
        okBut = new JButton("Close Window");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(500, 2));
        statusBox = new JComboBox(status);
        statusBox.setSelectedItem(ServicePanel.temp[4]);
        statusBox.setEnabled(false);

        Container c = getContentPane();
        SpringLayout spring = new SpringLayout();
        Border textBorder = addInput.getBorder();

        c.setLayout(spring);
        c.setBackground(new Color(23, 28, 30));
        c.add(idLabel);
        c.add(idInput);
        spring.putConstraint(SpringLayout.WEST, idLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, idLabel, 15, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, idInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, idInput, 10, SpringLayout.NORTH, c);
        c.add(scLabel);
        c.add(scInput);
        spring.putConstraint(SpringLayout.WEST, scLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, scLabel, 25, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 20, SpringLayout.SOUTH, idLabel);
        c.add(statusLabel);
        c.add(statusBox);
        spring.putConstraint(SpringLayout.WEST, statusLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, statusLabel, 32, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, statusBox, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, statusBox, 20, SpringLayout.SOUTH, scInput);
        c.add(dcLabel);
        c.add(dcInput);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 32, SpringLayout.SOUTH, statusLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 20, SpringLayout.SOUTH, statusLabel);
        c.add(addLabel);
        c.add(addScroll);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 32, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 20, SpringLayout.SOUTH, dcInput);
        c.add(separator);
        spring.putConstraint(SpringLayout.WEST, separator, 3, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, separator, 20, SpringLayout.SOUTH, addScroll);
        c.add(okBut);
        spring.putConstraint(SpringLayout.WEST, okBut, 200, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, okBut, 30, SpringLayout.SOUTH, separator);

        ButtonHandler handler = new ButtonHandler();
        okBut.addActionListener(handler);

        setSize(525, 450);
    }

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okBut) {
                dispose();
            }
        }
    }

    public void updateList() {
        int i = 0;
        for (i = 0; i < RYCOXv2.servList.size(); i++) {
            if (RYCOXv2.servList.get(i).getSmartCardNo().equalsIgnoreCase(ServicePanel.temp[0])) {
                break;
            }
        }
        RYCOXv2.servList.get(i).setSmartCardNo(smartCard);
        RYCOXv2.servList.get(i).setDecoderNo(decoder);
        RYCOXv2.servList.get(i).setAddress(address);
        RYCOXv2.servList.get(i).setServStatus(actualStatus);
    }
}
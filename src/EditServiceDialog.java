import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

@SuppressWarnings("serial")
class EditServiceDialog extends JDialog {
    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton okBut, ccBut, addBut, rmvBut;
    private JList pkgList, selList;
    private JScrollPane addScroll, pkgScroll, selScroll;
    private JSeparator separator;
    @SuppressWarnings("rawtypes")
    private JComboBox statusBox;
    private String[] status = {"Active", "Inactive", "Barred"};
    @SuppressWarnings("unused")
    private String smartCard, decoder, clientID, address, actualStatus;
    private Color fColor = new Color(255, 255, 255);
    private LinkedList<String> leftPkgList;
    private LinkedList<String> selPkgList;
    private String[] leftPkg;
    private String[] selPkg;
    private String addPkg;
    private int subsNo;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public EditServiceDialog(JFrame parent) {
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
        idInput.setEnabled(false);
        scInput = new JTextField(25);
        scInput.setText(ServicePanel.temp[0]);
        dcInput = new JTextField(25);
        dcInput.setText(ServicePanel.temp[2]);
        addInput = new JTextArea(5, 25);
        addInput.setText(ServicePanel.temp[3]);
        okBut = new JButton("OK");
        ccBut = new JButton("Cancel");
        addBut = new JButton("ADD");
        rmvBut = new JButton("REMOVE");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        separator = new JSeparator();
        separator.setPreferredSize(new Dimension(500, 2));
        statusBox = new JComboBox(status);
        statusBox.setSelectedItem(ServicePanel.temp[4]);

        pkgUpdate();

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
        c.add(pkgScroll);
        c.add(addBut);
        c.add(rmvBut);
        c.add(selScroll);
        spring.putConstraint(SpringLayout.WEST, pkgScroll, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, pkgScroll, 32, SpringLayout.SOUTH, separator);
        spring.putConstraint(SpringLayout.WEST, addBut, 65, SpringLayout.EAST, pkgList);
        spring.putConstraint(SpringLayout.NORTH, addBut, 52, SpringLayout.SOUTH, separator);
        spring.putConstraint(SpringLayout.WEST, rmvBut, 55, SpringLayout.EAST, pkgList);
        spring.putConstraint(SpringLayout.NORTH, rmvBut, 10, SpringLayout.SOUTH, addBut);
        spring.putConstraint(SpringLayout.WEST, selScroll, 20, SpringLayout.EAST, rmvBut);
        spring.putConstraint(SpringLayout.NORTH, selScroll, 32, SpringLayout.SOUTH, separator);
        c.add(okBut);
        c.add(ccBut);
        spring.putConstraint(SpringLayout.WEST, okBut, 170, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, okBut, 30, SpringLayout.SOUTH, pkgScroll);
        spring.putConstraint(SpringLayout.WEST, ccBut, 170, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, ccBut, 30, SpringLayout.SOUTH, pkgScroll);

        ButtonHandler handler = new ButtonHandler();
        okBut.addActionListener(handler);
        ccBut.addActionListener(handler);
        addBut.addActionListener(handler);

        setSize(525, 550);
    }

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okBut) {
                smartCard = scInput.getText();
                decoder = dcInput.getText();
                address = addInput.getText();
                actualStatus = (String) statusBox.getSelectedItem();
                updateList();
                dispose();
            } else if (e.getSource() == ccBut)
                dispose();
            else if (e.getSource() == addBut) {
                addPkg = (String) pkgList.getSelectedValue();
                System.out.println(addPkg);

                for (int i = 0; i < RYCOXv2.subsList.size(); i++) {
                    if (ServicePanel.temp[0].equalsIgnoreCase(RYCOXv2.subsList.get(i).getSmartCardNo())) {
                        subsNo = RYCOXv2.subsList.get(i).getSubsNo();
                    }
                }

                RYCOXv2.subsList.add(new Subscription(ServicePanel.temp[0], subsNo, addPkg));

                for (int i = 0; i < RYCOXv2.subsList.size(); i++) {
                    if (ServicePanel.temp[0].equalsIgnoreCase(RYCOXv2.subsList.get(i).getSmartCardNo())) {
                        if (addPkg.equalsIgnoreCase(RYCOXv2.subsList.get(i).getPkgCode())) {
                            System.out.println("yes");
                        }
                    }
                }
                pkgUpdate();
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

    public void pkgUpdate() {
        selPkgList = new LinkedList<String>();
        leftPkgList = new LinkedList<String>();

        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            for (int j = 0; j < RYCOXv2.subsList.size(); j++) {
                if (ServicePanel.temp[0].equalsIgnoreCase(RYCOXv2.subsList.get(j).getSmartCardNo())) {
                    if (RYCOXv2.subsList.get(j).getPkgCode().equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                        selPkgList.add(RYCOXv2.subsList.get(j).getPkgCode());
                    }
                }
            }
        }

        selPkg = selPkgList.toArray(new String[selPkgList.size()]);

        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            boolean check = false;
            for (int j = 0; j < selPkgList.size(); j++) {
                if (selPkg[j].equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                    check = true;
                    break;
                }
            }
            if (check == false) {
                leftPkgList.add(RYCOXv2.pkgList.get(i).getPkgCode());
            }
        }

        leftPkg = leftPkgList.toArray(new String[leftPkgList.size()]);
        pkgList = new JList(leftPkg);
        pkgList.setVisibleRowCount(6);
        pkgList.setFixedCellWidth(160);
        pkgList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        selList = new JList(selPkg);
        selList.setVisibleRowCount(6);
        selList.setFixedCellWidth(160);
        pkgScroll = new JScrollPane(pkgList);
        selScroll = new JScrollPane(selList);
        System.out.println("hi");
    }
}

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
class ViewServiceDialog extends JDialog {

    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel, rightLabel;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton closeBut;
    private static JList rightList;
    private JScrollPane addScroll;
    private static JScrollPane rightListScroll;
    private JSeparator separator, separator2;
    private JComboBox statusBox;
    private String[] status = {"Active", "Inactive", "Barred"};
    private String smartCard, decoder, clientID, address, actualStatus;
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

    public ViewServiceDialog(JFrame parent) {
        super(parent, "Service - Edit Service", true);
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

        idInput = new JTextField(20);
        idInput.setText(ServicePanel.temp[1]);
        idInput.setEditable(false);
        idInput.setBackground(nColor);
        idInput.setForeground(fColor);
        idInput.setBorder(null);
        scInput = new JTextField(20);
        scInput.setText(ServicePanel.temp[0]);
        scInput.setEditable(false);
        scInput.setBackground(nColor);
        scInput.setForeground(fColor);
        scInput.setBorder(null);
        dcInput = new JTextField(20);
        dcInput.setText(ServicePanel.temp[2]);
        dcInput.setEditable(false);
        dcInput.setBackground(nColor);
        dcInput.setForeground(fColor);
        dcInput.setBorder(null);
        addInput = new JTextArea(5, 20);
        addInput.setText(ServicePanel.temp[3]);
        addInput.setEditable(false);
        addInput.setBackground(nColor);
        addInput.setForeground(fColor);
        addInput.setBorder(null);
        closeBut = new JButton("Close");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        addScroll.setBorder(null);
//		separator = new JSeparator(JSeparator.VERTICAL);
//		separator.setPreferredSize(new Dimension(2,270));
//		separator2 = new JSeparator(JSeparator.HORIZONTAL);
//		separator2.setPreferredSize(new Dimension(680,2));
        statusBox = new JComboBox(status);
        statusBox.setSelectedItem(ServicePanel.temp[4]);

        selPkgList = new LinkedList<>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            for (int j = 0; j < RYCOXv2.subsList.size(); j++) {
                if (ServicePanel.temp[0].equalsIgnoreCase(RYCOXv2.subsList.get(j).getSmartCardNo())) {
                    if (RYCOXv2.subsList.get(j).getPkgCode().equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                        selPkgList.add(RYCOXv2.subsList.get(j).getPkgCode());
                        ((DefaultListModel) rightModel).addElement(RYCOXv2.subsList.get(j).getPkgCode() + "\t\t\t" + RYCOXv2.pkgList.get(i).getPkgName());
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
                ((DefaultListModel) leftModel).addElement(RYCOXv2.pkgList.get(i).getPkgCode() + "\t\t\t" + RYCOXv2.pkgList.get(i).getPkgName());
            }
        }

        rightList = new JList(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(12);
        rightList.setFixedCellWidth(160);
        rightList.setSelectionBackground(bColor);
        //rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightList.setBackground(nColor);
        rightList.setForeground(fColor);
        rightList.setBorder(null);

        rightListScroll = new JScrollPane(rightList);
        rightListScroll.setViewportView(rightList);
        rightListScroll.setBorder(null);
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
        spring.putConstraint(SpringLayout.NORTH, idInput, 30, SpringLayout.NORTH, c);
        c.add(scLabel);
        c.add(scInput);
        spring.putConstraint(SpringLayout.WEST, scLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, scLabel, 35, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 30, SpringLayout.SOUTH, idLabel);
        c.add(dcLabel);
        c.add(dcInput);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 35, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 30, SpringLayout.SOUTH, scLabel);
        c.add(addLabel);
        c.add(addScroll);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 35, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 30, SpringLayout.SOUTH, dcInput);
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
        spring.putConstraint(SpringLayout.WEST, closeBut, 400, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, closeBut, 30, SpringLayout.SOUTH, rightListScroll);

        ButtonHandler handler = new ButtonHandler();
        closeBut.addActionListener(handler);
        setSize(700, 420);
        ServicePanel.defaultButtonSet();
    }

    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == closeBut) {
                dispose();
            }
        }
    }
}
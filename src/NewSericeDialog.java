import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

@SuppressWarnings("serial")
class NewServiceDialog extends JDialog {
    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel, leftLabel, rightLabel;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton okBut, ccBut, addBut, rmvBut;
    private static JList leftList;
    private static JList rightList;
    private JScrollPane addScroll;
    private static JScrollPane leftListScroll;
    private static JScrollPane rightListScroll;
    private JSeparator separator, separator2;
    @SuppressWarnings("rawtypes")
    private JComboBox statusBox;
    private String[] status = {"Active", "Inactive", "Barred"};
    @SuppressWarnings("unused")
    private String smartCard, decoder, clientID, address, actualStatus;
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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public NewServiceDialog(JFrame parent) {
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
        leftLabel = new JLabel("Packages Available: ");
        leftLabel.setForeground(fColor);
        rightLabel = new JLabel("Packages Subscribed: ");
        rightLabel.setForeground(fColor);
        idInput = new JTextField(20);
        //idInput.setText(ServicePanel.temp[1]);
        idInput.setEnabled(false);
        scInput = new JTextField(20);
        dcInput = new JTextField(20);
        addInput = new JTextArea(5, 20);
        okBut = new JButton("OK");
        ccBut = new JButton("Cancel");
        addBut = new JButton("ADD");
        rmvBut = new JButton("REMOVE");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(2, 270));
        separator2 = new JSeparator(JSeparator.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(990, 2));
        statusBox = new JComboBox(status);
        statusBox.setSelectedItem(ServicePanel.temp[4]);

        selPkgList = new LinkedList<String>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            for (int j = 0; j < RYCOXv2.subsList.size(); j++) {
                if (ServicePanel.temp[0].equalsIgnoreCase(RYCOXv2.subsList.get(j).getSmartCardNo())) {
                    if (RYCOXv2.subsList.get(j).getPkgCode().equalsIgnoreCase(RYCOXv2.pkgList.get(i).getPkgCode())) {
                        selPkgList.add(RYCOXv2.subsList.get(j).getPkgCode());
                        ((DefaultListModel) rightModel).addElement(RYCOXv2.subsList.get(j).getPkgCode() + RYCOXv2.pkgList.get(i).getPkgName());
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
                ((DefaultListModel) leftModel).addElement(RYCOXv2.pkgList.get(i).getPkgCode() + "\t" + RYCOXv2.pkgList.get(i).getPkgName());
            }
        }

        leftList = new JList(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(12);
        leftList.setFixedCellWidth(160);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rightList = new JList(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(12);
        rightList.setFixedCellWidth(160);
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);
        rightListScroll = new JScrollPane(rightList);
        rightListScroll.setViewportView(rightList);


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
        spring.putConstraint(SpringLayout.NORTH, scLabel, 25, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 20, SpringLayout.SOUTH, idLabel);
        c.add(dcLabel);
        c.add(dcInput);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 25, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 20, SpringLayout.SOUTH, scLabel);
        c.add(addLabel);
        c.add(addScroll);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 25, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 20, SpringLayout.SOUTH, dcInput);
        c.add(separator);
        spring.putConstraint(SpringLayout.WEST, separator, 20, SpringLayout.EAST, idInput);
        spring.putConstraint(SpringLayout.NORTH, separator, 15, SpringLayout.NORTH, c);
        c.add(leftLabel);
        c.add(rightLabel);
        spring.putConstraint(SpringLayout.WEST, leftLabel, 30, SpringLayout.EAST, separator);
        spring.putConstraint(SpringLayout.NORTH, leftLabel, 25, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, rightLabel, 325, SpringLayout.EAST, separator);
        spring.putConstraint(SpringLayout.NORTH, rightLabel, 25, SpringLayout.NORTH, c);
        c.add(leftListScroll);
        c.add(addBut);
        c.add(rmvBut);
        c.add(rightListScroll);
        spring.putConstraint(SpringLayout.WEST, leftListScroll, 25, SpringLayout.EAST, separator);
        spring.putConstraint(SpringLayout.NORTH, leftListScroll, 48, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, addBut, 40, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, addBut, 52, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, rmvBut, 30, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, rmvBut, 10, SpringLayout.SOUTH, addBut);
        spring.putConstraint(SpringLayout.WEST, rightListScroll, 28, SpringLayout.EAST, rmvBut);
        spring.putConstraint(SpringLayout.NORTH, rightListScroll, 50, SpringLayout.NORTH, c);
        c.add(separator2);
        spring.putConstraint(SpringLayout.WEST, separator2, 5, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, separator2, 20, SpringLayout.SOUTH, rightListScroll);
        c.add(okBut);
        c.add(ccBut);
        spring.putConstraint(SpringLayout.WEST, okBut, 400, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, okBut, 30, SpringLayout.SOUTH, separator);
        spring.putConstraint(SpringLayout.WEST, ccBut, 30, SpringLayout.EAST, okBut);
        spring.putConstraint(SpringLayout.NORTH, ccBut, 30, SpringLayout.SOUTH, separator);

        ButtonHandler handler = new ButtonHandler();
        okBut.addActionListener(handler);
        ccBut.addActionListener(handler);
        addBut.addActionListener(handler);
        rmvBut.addActionListener(handler);
        setSize(1000, 400);
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
                updateSubsList();
                dispose();
            } else if (e.getSource() == ccBut) {
                //ADD PROMPT
                dispose();
            } else if (e.getSource() == addBut) {
                pkgAdd();
            } else if (e.getSource() == rmvBut) {
                pkgRemove();
            }
        }
    }

    private void updateSubsList() {
        int i;
        for (i = 0; i < RYCOXv2.subsList.size(); i++) {
            if ((RYCOXv2.subsList.get(i).getSmartCardNo()).equalsIgnoreCase(ServicePanel.temp[0])) {
                subsNo = RYCOXv2.subsList.get(i).getSubsNo();
                RYCOXv2.subsList.remove(i);
                i--;
            }
        }

        LinkedList<String> addElements = new LinkedList<String>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            addElements.add(((String) rightList.getModel().getElementAt(p)).substring(0, 3));
        }
        for (i = 0; i < addElements.size(); i++) {
            RYCOXv2.subsList.add(new Subscription(ServicePanel.temp[0], subsNo, addElements.get(i)));
            System.out.println(addElements.get(i));
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


    @SuppressWarnings("unchecked")
    public void pkgAdd() {
        int leftIndex = leftList.getSelectedIndex();
        String toBeAdded = ((String) leftList.getModel().getElementAt(leftIndex));
        LinkedList<String> leftElements = new LinkedList<String>();
        for (int p = 0; p < leftList.getModel().getSize(); p++) {
            leftElements.add((String) leftList.getModel().getElementAt(p));
        }

        for (int i = 0; i < leftElements.size(); i++) {
            if ((leftElements.get(i)).equals(toBeAdded)) {
                leftElements.remove(i);
                break;
            }
        }

        leftElArr = leftElements.toArray(new String[leftElements.size()]);
        leftList.setListData(leftElArr);
        leftList.revalidate();
        leftList.repaint();

        LinkedList<String> rightElements = new LinkedList<String>();
        for (int z = 0; z < rightList.getModel().getSize(); z++) {
            rightElements.add((String) rightList.getModel().getElementAt(z));
        }
        rightElements.add(toBeAdded);
        rightElArr = rightElements.toArray(new String[rightElements.size()]);
        rightList.setListData(rightElArr);
        rightList.repaint();
        rightList.revalidate();
    }

    public void pkgRemove() {
        int rightIndex = rightList.getSelectedIndex();
        String toBeRemoved = ((String) rightList.getModel().getElementAt(rightIndex));
        LinkedList<String> rightElements = new LinkedList<String>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            rightElements.add((String) rightList.getModel().getElementAt(p));
        }

        for (int i = 0; i < rightElements.size(); i++) {
            if ((rightElements.get(i)).equals(toBeRemoved)) {
                rightElements.remove(i);
                break;
            }
        }

        rightElArr = rightElements.toArray(new String[rightElements.size()]);
        rightList.setListData(rightElArr);
        rightList.revalidate();
        rightList.repaint();


        LinkedList<String> leftElements = new LinkedList<String>();
        for (int z = 0; z < leftList.getModel().getSize(); z++) {
            leftElements.add((String) leftList.getModel().getElementAt(z));
        }
        leftElements.add(toBeRemoved);
        leftElArr = leftElements.toArray(new String[leftElements.size()]);
        leftList.setListData(leftElArr);
        leftList.repaint();
        leftList.revalidate();
    }
}
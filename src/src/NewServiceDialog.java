import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
class NewServiceDialog extends JDialog {
    private JLabel idLabel, scLabel, dcLabel, addLabel, statusLabel, leftLabel, rightLabel, scExample, dcExample;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton okBut, ccBut, addBut, rmvBut;
    private static JList leftList;
    private static JList rightList;
    private JScrollPane addScroll;
    private static JScrollPane leftListScroll;
    private static JScrollPane rightListScroll;
    private JSeparator separator, separator2;
    private Color fColor = new Color(255, 255, 255);
    private LinkedList<String> leftPkgList;
    private LinkedList<String> selPkgList;
    private AbstractListModel leftModel;
    private AbstractListModel rightModel;
    private String[] leftElArr;
    private String[] rightElArr;
    private String addPkg;
    private int subsNo;
    private JCheckBox addCB;
    private JWarnLabel scFormatWarn, scEmptyWarn, scSameWarn, dcFormatWarn, dcEmptyWarn, dcSameWarn, addWarn, subsPkgWarn;

    public NewServiceDialog(JFrame parent) {
        super(parent, "Service - Edit Service", true);
        idLabel = new JLabel("Client ID: ");
        idLabel.setForeground(fColor);
        scLabel = new JLabel("Smart Card Number: ");
        scLabel.setForeground(fColor);
        dcLabel = new JLabel("Decoder Number: ");
        dcLabel.setForeground(fColor);
        addLabel = new JLabel("Address: ");
        addLabel.setForeground(fColor);
        leftLabel = new JLabel("Packages Available: ");
        leftLabel.setForeground(fColor);
        rightLabel = new JLabel("Packages Subscribed: ");
        rightLabel.setForeground(fColor);
        scExample = new JLabel("e.g Sxxxxxx");
        scExample.setForeground(fColor);
        dcExample = new JLabel("e.g Dxxxxxx");
        dcExample.setForeground(fColor);
        idInput = new JTextField(20);
        idInput.setText(ClientPanel.editionData[0]);
        idInput.setEnabled(false);
        scInput = new JTextField(20);
        dcInput = new JTextField(20);
        addInput = new JTextArea(5, 20);
        okBut = new JButton("OK");
        ccBut = new JButton("Cancel");
        addBut = new JButton("ADD");
        rmvBut = new JButton("REMOVE");
        addCB = new JCheckBox("Use default address.");
        addCB.setForeground(fColor);
        scFormatWarn = new JWarnLabel("Please Enter the correct Smart Card Format.");
        scEmptyWarn = new JWarnLabel("Please Enter the Smart Card Number.");
        scSameWarn = new JWarnLabel("Smart Card has been used.");
        dcFormatWarn = new JWarnLabel("Please Enter the Correct Decoder Format.");
        dcEmptyWarn = new JWarnLabel("Please Enter the Decoder Number.");
        dcSameWarn = new JWarnLabel("Decoder has been used.");
        addWarn = new JWarnLabel("Please Enter the Address.");
        subsPkgWarn = new JWarnLabel("Must at least select one package.");
        addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(2, 270));
        separator2 = new JSeparator(JSeparator.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(990, 2));

        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();

        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            ((DefaultListModel) leftModel).addElement(RYCOXv2.pkgList.get(i).getPkgCode() + "\t\t\t" + RYCOXv2.pkgList.get(i).getPkgName());
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
        c.add(scExample);
        c.add(scFormatWarn);
        c.add(scEmptyWarn);
        c.add(scSameWarn);
        spring.putConstraint(SpringLayout.WEST, scLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, scLabel, 35, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 30, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scExample, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scExample, 5, SpringLayout.SOUTH, scInput);
        spring.putConstraint(SpringLayout.WEST, scFormatWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scFormatWarn, 5, SpringLayout.SOUTH, scInput);
        spring.putConstraint(SpringLayout.WEST, scEmptyWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scEmptyWarn, 5, SpringLayout.SOUTH, scInput);
        spring.putConstraint(SpringLayout.WEST, scSameWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scSameWarn, 5, SpringLayout.SOUTH, scInput);
        c.add(dcLabel);
        c.add(dcInput);
        c.add(dcExample);
        c.add(dcFormatWarn);
        c.add(dcEmptyWarn);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 35, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 30, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcExample, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcExample, 5, SpringLayout.SOUTH, dcInput);
        spring.putConstraint(SpringLayout.WEST, dcFormatWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcFormatWarn, 5, SpringLayout.SOUTH, dcInput);
        spring.putConstraint(SpringLayout.WEST, dcEmptyWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcEmptyWarn, 5, SpringLayout.SOUTH, dcInput);
        c.add(addLabel);
        c.add(addScroll);
        c.add(addWarn);
        c.add(addCB);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 35, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 30, SpringLayout.SOUTH, dcInput);
        spring.putConstraint(SpringLayout.WEST, addCB, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addCB, 200, SpringLayout.SOUTH, addInput);
        spring.putConstraint(SpringLayout.WEST, addWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addWarn, 220, SpringLayout.SOUTH, addInput);
        c.add(separator);
        spring.putConstraint(SpringLayout.WEST, separator, 30, SpringLayout.EAST, idInput);
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
        c.add(subsPkgWarn);
        spring.putConstraint(SpringLayout.WEST, leftListScroll, 25, SpringLayout.EAST, separator);
        spring.putConstraint(SpringLayout.NORTH, leftListScroll, 48, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, addBut, 40, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, addBut, 52, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, rmvBut, 30, SpringLayout.EAST, leftListScroll);
        spring.putConstraint(SpringLayout.NORTH, rmvBut, 10, SpringLayout.SOUTH, addBut);
        spring.putConstraint(SpringLayout.WEST, rightListScroll, 28, SpringLayout.EAST, rmvBut);
        spring.putConstraint(SpringLayout.NORTH, rightListScroll, 50, SpringLayout.NORTH, c);
        spring.putConstraint(SpringLayout.WEST, subsPkgWarn, 25, SpringLayout.EAST, separator);
        spring.putConstraint(SpringLayout.NORTH, subsPkgWarn, 5, SpringLayout.SOUTH, leftListScroll);
        c.add(separator2);
        spring.putConstraint(SpringLayout.WEST, separator2, 5, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, separator2, 5, SpringLayout.SOUTH, addWarn);
        c.add(okBut);
        c.add(ccBut);
        spring.putConstraint(SpringLayout.WEST, okBut, 400, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, okBut, 30, SpringLayout.SOUTH, separator2);
        spring.putConstraint(SpringLayout.WEST, ccBut, 30, SpringLayout.EAST, okBut);
        spring.putConstraint(SpringLayout.NORTH, ccBut, 30, SpringLayout.SOUTH, separator2);

        ButtonHandler handler = new ButtonHandler();
        okBut.addActionListener(handler);
        ccBut.addActionListener(handler);
        addBut.addActionListener(handler);
        rmvBut.addActionListener(handler);
        addCB.addActionListener(handler);
        setSize(1000, 480);
        ServicePanel.defaultButtonSet();
    }

    public class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okBut) {
                submit();
            } else if (e.getSource() == ccBut) {
                int confirm = JOptionPane.showConfirmDialog(null, "Exit without changes?", "Confirm exit", JOptionPane.WARNING_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            } else if (e.getSource() == addBut) {
                pkgAdd();
            } else if (e.getSource() == rmvBut) {
                pkgRemove();
            }
            if (addCB.isSelected()) {
                addInput.setText(ClientPanel.editionData[3]);
            } else if (!addCB.isSelected()) {
                addInput.setText(null);
            }
        }
    }

    public void submit() {
        String scNo_IDregex = "^[sS][0-9]{6}$";
        String decNo_IDregex = "^[dD][0-9]{6}$";

        boolean scCheck = false;
        boolean dcCheck = false;
        boolean addCheck = false;
        boolean subsPkgCheck = false;

        if (scInput.getText().length() == 0) {
            scEmptyWarn.setVisible(true);
            scExample.setVisible(false);
            scFormatWarn.setVisible(false);
            scSameWarn.setVisible(false);
        } else if (!scInput.getText().matches(scNo_IDregex)) {
            scFormatWarn.setVisible(true);
            scExample.setVisible(false);
            scEmptyWarn.setVisible(false);
            scSameWarn.setVisible(false);
        } else if (scInput.getText().matches(scNo_IDregex)) {
            for (int j = 0; j < RYCOXv2.servList.size(); j++) {
                if (scInput.getText().equalsIgnoreCase(RYCOXv2.servList.get(j).getSmartCardNo())) {
                    scSameWarn.setVisible(true);
                    scExample.setVisible(false);
                    scFormatWarn.setVisible(false);
                    scEmptyWarn.setVisible(false);
                    scCheck = false;
                    break;
                }
                scExample.setVisible(true);
                scEmptyWarn.setVisible(false);
                scFormatWarn.setVisible(false);
                scSameWarn.setVisible(false);
                scCheck = true;
            }
        }

        if (dcInput.getText().length() == 0) {
            dcEmptyWarn.setVisible(true);
            dcExample.setVisible(false);
        } else if (!dcInput.getText().matches(decNo_IDregex)) {
            dcFormatWarn.setVisible(true);
            dcExample.setVisible(false);
        } else if (dcInput.getText().matches(decNo_IDregex)) {
            for (int j = 0; j < RYCOXv2.servList.size(); j++) {
                if (dcInput.getText().equalsIgnoreCase(RYCOXv2.servList.get(j).getDecodeNo())) {
                    dcExample.setVisible(false);
                    dcSameWarn.setVisible(true);
                    dcCheck = false;
                    break;
                }
                dcExample.setVisible(true);
                dcEmptyWarn.setVisible(false);
                dcFormatWarn.setVisible(false);
                dcSameWarn.setVisible(false);
                dcCheck = true;
            }
        }

        if (addInput.getText().length() == 0) {
            addWarn.setVisible(true);
        } else if (addInput.getText().length() >= 1) {
            addWarn.setVisible(false);
            addCheck = true;
        }
        try {
            if (rightElArr.length == 0) {
                subsPkgWarn.setVisible(true);
            } else if (rightElArr.length >= 1) {
                subsPkgWarn.setVisible(false);
                subsPkgCheck = true;
            }
        } catch (NullPointerException npe) {

            subsPkgCheck = false;
            subsPkgWarn.setVisible(true);
        }
        if (scCheck == true && dcCheck == true && addCheck == true && subsPkgCheck == true) {
            updateList();
            for (int i = 0; i < RYCOXv2.servList.size(); i++) {
            }
            updateSubsList();
            JOptionPane.showMessageDialog(null, "You have successfully create a new service" + scInput.getText(), "New Service Created", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    private void updateSubsList() {
        subsNo = 0;
        for (int i = 0; i < RYCOXv2.servList.size(); i++) {
            if (RYCOXv2.servList.get(i).getClientID().equalsIgnoreCase(ClientPanel.editionData[0])) {
                subsNo++;
            }
        }
        LinkedList<String> addElements = new LinkedList<String>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            addElements.add(((String) rightList.getModel().getElementAt(p)).substring(0, 3));
        }
        for (int i = 0; i < addElements.size(); i++) {
            RYCOXv2.subsList.add(new Subscription(scInput.getText(), subsNo, addElements.get(i)));
            RYCOXv2.subsList.getLast().printSubs();
        }
    }

    public void updateList() {
        RYCOXv2.servList.add(new Service(scInput.getText(), ClientPanel.editionData[0], dcInput.getText(), addInput.getText()));
        ServicePanel.addServ();
    }

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

    public class JWarnLabel extends JLabel {
        public JWarnLabel(String s) {
            super(s);
            setVisible(false);
            setForeground(Color.RED);
        }
    }
}
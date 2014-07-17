import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author LiHao
 */
class NewServiceDialog extends JDialog {
    // Variable declaration

    private JLabel scExample;
    private JLabel dcExample;
    private JTextField scInput;
    private JTextField dcInput;
    private JTextArea addInput;
    private JButton okBut, ccBut, addBut, rmvBut;
    private static JList<String> leftList;
    private static JList<String> rightList;
    private String[] leftElArr;
    private String[] rightElArr;
    private JCheckBox addCB;
    private JWarnLabel scFormatWarn, scEmptyWarn, scSameWarn, dcFormatWarn, dcEmptyWarn, dcSameWarn, addWarn, subsPkgWarn;
    // end of variable declaration

    public NewServiceDialog(JFrame parent) {
        super(parent, "Service - Edit Service", true);
        setLocation(300, 150);
        JLabel idLabel = new JLabel("Client ID: ");
        Color fColor = new Color(255, 255, 255);
        idLabel.setForeground(fColor);
        JLabel scLabel = new JLabel("Smart Card Number: ");
        scLabel.setForeground(fColor);
        JLabel dcLabel = new JLabel("Decoder Number: ");
        dcLabel.setForeground(fColor);
        JLabel addLabel = new JLabel("Address: ");
        addLabel.setForeground(fColor);
        JLabel leftLabel = new JLabel("Packages Available: ");
        leftLabel.setForeground(fColor);
        JLabel rightLabel = new JLabel("Packages Subscribed: ");
        rightLabel.setForeground(fColor);
        scExample = new JLabel("e.g Sxxxxxx");
        scExample.setForeground(fColor);
        dcExample = new JLabel("e.g Dxxxxxx");
        dcExample.setForeground(fColor);
        JTextField idInput = new JTextField(20);
        idInput.setText(ClientPanel.editionData[0]);
        idInput.setEnabled(false);
        scInput = new JTextField(20);
        dcInput = new JTextField(20);
        addInput = new JTextArea(5, 20);
        okBut = new JButton("OK");
        ccBut = new JButton("Cancel");
        addBut = new JButton("", new ImageIcon(getClass().getResource("/resources/addtoright.png")));
        addBut.setBackground(new Color(23, 28, 31));
        rmvBut = new JButton("", new ImageIcon(getClass().getResource("/resources/removetoleft.png")));
        rmvBut.setBackground(new Color(23, 28, 31));
        addCB = new JCheckBox("Use default address.");
        addCB.setForeground(fColor);
        scFormatWarn = new JWarnLabel("Wrong Smart Card Format. e.g Sxxxxxx");
        scEmptyWarn = new JWarnLabel("Smart Card cannot be empty. e.g Sxxxxxx");
        scSameWarn = new JWarnLabel("Smart Card has been used. e.g Sxxxxxx");
        dcFormatWarn = new JWarnLabel("Wrong Decoder Format. e.g Dxxxxxx");
        dcEmptyWarn = new JWarnLabel("Decoder cannot be empty. e.g Dxxxxxx");
        dcSameWarn = new JWarnLabel("Decoder has been used. e.g Dxxxxxx");
        addWarn = new JWarnLabel("Please Enter the Address.");
        subsPkgWarn = new JWarnLabel("Must at least select one package.");
        JScrollPane addScroll = new JScrollPane();
        addScroll.setViewportView(addInput);
        JSeparator separator = new JSeparator(JSeparator.VERTICAL);
        separator.setPreferredSize(new Dimension(2, 270));
        JSeparator separator2 = new JSeparator(JSeparator.HORIZONTAL);
        separator2.setPreferredSize(new Dimension(990, 2));

        AbstractListModel<String> leftModel = new DefaultListModel<>();
        AbstractListModel<String> rightModel = new DefaultListModel<>();

        // get package
        for (int i = 0; i < App.pkgList.size(); i++) {
            if (!(App.pkgList.get(i).getPkgStatus().equalsIgnoreCase("TERMINATED"))) {
                ((DefaultListModel) leftModel).addElement(App.pkgList.get(i).getPkgCode() + "\t\t\t" + App.pkgList.get(i).getPkgName());
            }
        }

        leftList = new JList<>(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(12);
        leftList.setFixedCellWidth(160);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        rightList = new JList<>(rightModel);
        rightList.setModel(rightModel);
        rightList.setVisibleRowCount(12);
        rightList.setFixedCellWidth(160);
        rightList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);
        JScrollPane rightListScroll = new JScrollPane(rightList);
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
        c.add(dcSameWarn);
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
        spring.putConstraint(SpringLayout.WEST, dcSameWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcSameWarn, 5, SpringLayout.SOUTH, dcInput);
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
        spring.putConstraint(SpringLayout.NORTH, addBut, 95, SpringLayout.NORTH, c);
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

    /**
     * @author LiHao This class is used for handler all the button listener and
     *         returns dialog for confirmation if necessary.
     */
    public class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == okBut) {
                submit();
                App.log = new LogFile(App.user, " has create a new service " + scInput.getText().toUpperCase() + " for Client " + ClientPanel.editionData[0]);
                App.logList.add(App.log);
            } else if (e.getSource() == ccBut) {
                int confirm = JOptionPane.showConfirmDialog(null, "Exit without changes?", "Confirm exit", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                }
            } else if (e.getSource() == addBut) {
                try {
                    pkgAdd();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "You can only add a TV Package from the left list.", "Adding error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (e.getSource() == rmvBut) {
                try {
                    pkgRemove();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "You can only remove a TV Package from the right list when right list is filled in with TV Package.", "Remove error", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (addCB.isSelected()) {
                addInput.setText(ClientPanel.editionData[3]);
            } else {
                addInput.setText(null);
            }
        }
    }

    /**
     * This method is used for validate the data entered by user
     * is correct and the smart card number and decoder number is not repeat, if
     * all the data is correct the new service will be added, if not will
     * display the warning message.
     */
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
            for (int j = 0; j < App.servList.size(); j++) {
                if (scInput.getText().equalsIgnoreCase(App.servList.get(j).getSmartCardNo())) {
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
            dcSameWarn.setVisible(false);
            dcFormatWarn.setVisible(false);
        } else if (!dcInput.getText().matches(decNo_IDregex)) {
            dcFormatWarn.setVisible(true);
            dcEmptyWarn.setVisible(false);
            dcSameWarn.setVisible(false);
            dcExample.setVisible(false);
        } else if (dcInput.getText().matches(decNo_IDregex)) {
            for (int j = 0; j < App.servList.size(); j++) {
                if (dcInput.getText().equalsIgnoreCase(App.servList.get(j).getDecodeNo())) {
                    dcExample.setVisible(false);
                    dcEmptyWarn.setVisible(false);
                    dcFormatWarn.setVisible(false);
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
        if (scCheck && dcCheck && addCheck && subsPkgCheck) {
            updateList();
            updateSubsList();
            JOptionPane.showMessageDialog(null, "You have successfully create a new service " + scInput.getText().toUpperCase(), "New Service Created", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    }

    /**
     * This method is used for updating the new subscription from
     * client.
     */
    private void updateSubsList() {
        int subsNo = 0;
        for (int i = 0; i < App.servList.size(); i++) {
            if (App.servList.get(i).getClientID().equalsIgnoreCase(ClientPanel.editionData[0])) {
                subsNo++;
            }
        }
        LinkedList<String> addElements = new LinkedList<>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            addElements.add((rightList.getModel().getElementAt(p)).substring(0, 3));
        }
        for (String addElement : addElements) {
            App.subsList.add(new Subscription(scInput.getText().toUpperCase(), subsNo, addElement));
        }
    }

    /**
     * This method is used for updating the new service's
     * information from client.
     */
    public void updateList() {
        App.servList.add(new Service(scInput.getText().toUpperCase(), ClientPanel.editionData[0], dcInput.getText().toUpperCase(), addInput.getText()));
        ServicePanel.addServ();
    }

    /**
     * This method is used for add the selected package from the
     * not subscribe package list to subscribe package list.
     */
    public void pkgAdd() {
        int leftIndex = leftList.getSelectedIndex();
        String toBeAdded = (leftList.getModel().getElementAt(leftIndex));
        LinkedList<String> leftElements = new LinkedList<>();
        for (int p = 0; p < leftList.getModel().getSize(); p++) {
            leftElements.add(leftList.getModel().getElementAt(p));
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

        LinkedList<String> rightElements = new LinkedList<>();
        for (int z = 0; z < rightList.getModel().getSize(); z++) {
            rightElements.add(rightList.getModel().getElementAt(z));
        }
        rightElements.add(toBeAdded);
        rightElArr = rightElements.toArray(new String[rightElements.size()]);
        rightList.setListData(rightElArr);
        rightList.repaint();
        rightList.revalidate();
    }

    /**
     * This method is used for remove the selected package from
     * the subscribe package list to not subscribe package list.
     */
    public void pkgRemove() {
        int rightIndex = rightList.getSelectedIndex();
        String toBeRemoved = (rightList.getModel().getElementAt(rightIndex));
        LinkedList<String> rightElements = new LinkedList<>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            rightElements.add(rightList.getModel().getElementAt(p));
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


        LinkedList<String> leftElements = new LinkedList<>();
        for (int z = 0; z < leftList.getModel().getSize(); z++) {
            leftElements.add(leftList.getModel().getElementAt(z));
        }
        leftElements.add(toBeRemoved);
        leftElArr = leftElements.toArray(new String[leftElements.size()]);
        leftList.setListData(leftElArr);
        leftList.repaint();
        leftList.revalidate();
    }

    /**
     * @author LiHao This class is used for declare the warning message label.
     */
    public class JWarnLabel extends JLabel {

        public JWarnLabel(String s) {
            super(s);
            setVisible(false);
            setForeground(Color.RED);
        }
    }
}
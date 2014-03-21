import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;

@SuppressWarnings("serial")
class EditServiceDialog extends JDialog {
    private JLabel idLabel, scLabel, dcLabel, addLabel, leftLabel, rightLabel;
    private JTextField idInput, scInput, dcInput;
    private JTextArea addInput;
    private JButton okBut, ccBut, addBut, rmvBut;
    @SuppressWarnings("rawtypes")
    private static JList leftList;
    @SuppressWarnings("rawtypes")
    private static JList rightList;
    private JScrollPane addScroll;
    private static JScrollPane leftListScroll;
    private static JScrollPane rightListScroll;
    private JSeparator separator, separator2;
    private Color fColor = new Color(255, 255, 255);
    @SuppressWarnings("unused")
    private LinkedList<String> leftPkgList;
    private LinkedList<String> selPkgList;
    @SuppressWarnings("rawtypes")
    private AbstractListModel leftModel;
    @SuppressWarnings("rawtypes")
    private AbstractListModel rightModel;
    private String[] leftElArr;
    private String[] rightElArr;
    private String[] selPkg;
    @SuppressWarnings("unused")
    private String addPkg;
    private int subsNo;
    private JWarnLabel scFormatWarn, scEmptyWarn, scSameWarn, dcFormatWarn, dcEmptyWarn, dcSameWarn, addWarn, subsPkgWarn;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public EditServiceDialog(JFrame parent) {
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
        idInput = new JTextField(20);
        idInput.setText(ServicePanel.temp[1]);
        idInput.setEnabled(false);
        scInput = new JTextField(20);
        scInput.setText(ServicePanel.temp[0]);
        dcInput = new JTextField(20);
        dcInput.setText(ServicePanel.temp[2]);
        addInput = new JTextArea(5, 20);
        addInput.setText(ServicePanel.temp[3]);
        okBut = new JButton("OK");
        ccBut = new JButton("Cancel");
        addBut = new JButton("ADD");
        rmvBut = new JButton("REMOVE");
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

        selPkgList = new LinkedList<>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        RYCOXv2.subsList.getLast().printSecSubs();
        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            System.out.println(ServicePanel.temp[0]);
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
        c.add(scFormatWarn);
        c.add(scEmptyWarn);
        c.add(scSameWarn);
        spring.putConstraint(SpringLayout.WEST, scLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, scLabel, 35, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scInput, 30, SpringLayout.SOUTH, idLabel);
        spring.putConstraint(SpringLayout.WEST, scFormatWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scFormatWarn, 5, SpringLayout.SOUTH, scInput);
        spring.putConstraint(SpringLayout.WEST, scEmptyWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scEmptyWarn, 5, SpringLayout.SOUTH, scInput);
        spring.putConstraint(SpringLayout.WEST, scSameWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, scSameWarn, 5, SpringLayout.SOUTH, scInput);
        c.add(dcLabel);
        c.add(dcInput);
        c.add(dcFormatWarn);
        c.add(dcEmptyWarn);
        spring.putConstraint(SpringLayout.WEST, dcLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, dcLabel, 35, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcInput, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcInput, 30, SpringLayout.SOUTH, scLabel);
        spring.putConstraint(SpringLayout.WEST, dcFormatWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcFormatWarn, 5, SpringLayout.SOUTH, dcInput);
        spring.putConstraint(SpringLayout.WEST, dcEmptyWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, dcEmptyWarn, 5, SpringLayout.SOUTH, dcInput);
        c.add(addLabel);
        c.add(addScroll);
        c.add(addWarn);
        addInput.setBorder(textBorder);
        spring.putConstraint(SpringLayout.WEST, addLabel, 20, SpringLayout.WEST, c);
        spring.putConstraint(SpringLayout.NORTH, addLabel, 35, SpringLayout.SOUTH, dcLabel);
        spring.putConstraint(SpringLayout.WEST, addScroll, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addScroll, 30, SpringLayout.SOUTH, dcInput);
        spring.putConstraint(SpringLayout.WEST, addWarn, 130, SpringLayout.EAST, idLabel);
        spring.putConstraint(SpringLayout.NORTH, addWarn, 200, SpringLayout.SOUTH, addInput);
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
        spring.putConstraint(SpringLayout.NORTH, separator2, 5, SpringLayout.SOUTH, subsPkgWarn);
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
        }
    }

    public class mouseHandler extends MouseAdapter {
        public void mouseReleased(ActionEvent e) {
            if (e.getSource() == leftListScroll) {
                addBut.setEnabled(false);
            } else if (e.getSource() == rightListScroll) {
                rmvBut.setEnabled(false);
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
        } else if (!scInput.getText().matches(scNo_IDregex)) {
            scFormatWarn.setVisible(true);
        } else if (scInput.getText().matches(scNo_IDregex)) {
            if (scInput.getText().equalsIgnoreCase(ServicePanel.temp[0])) {
                scEmptyWarn.setVisible(false);
                scFormatWarn.setVisible(false);
                scSameWarn.setVisible(false);
                scCheck = true;
            } else if (!scInput.getText().equalsIgnoreCase(ServicePanel.temp[0])) {
                for (int j = 0; j < RYCOXv2.servList.size(); j++) {
                    if (scInput.getText().equalsIgnoreCase(RYCOXv2.servList.get(j).getSmartCardNo())) {
                        scSameWarn.setVisible(true);
                        scCheck = false;
                        break;
                    }
                    scEmptyWarn.setVisible(false);
                    scFormatWarn.setVisible(false);
                    scSameWarn.setVisible(false);
                    scCheck = true;
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has edited the smart card number from " + ServicePanel.temp[0] + " to " + scInput.getText() + ".");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                }
            }
        }

        if (dcInput.getText().length() == 0) {
            dcEmptyWarn.setVisible(true);
        } else if (!dcInput.getText().matches(decNo_IDregex)) {
            dcFormatWarn.setVisible(true);
        } else if (dcInput.getText().matches(decNo_IDregex)) {
            if (dcInput.getText().equalsIgnoreCase(ServicePanel.temp[2])) {
                dcEmptyWarn.setVisible(false);
                dcFormatWarn.setVisible(false);
                dcSameWarn.setVisible(false);
                dcCheck = true;
            } else if (!dcInput.getText().equalsIgnoreCase(ServicePanel.temp[2])) {
                for (int j = 0; j < RYCOXv2.servList.size(); j++) {
                    if (dcInput.getText().equalsIgnoreCase(RYCOXv2.servList.get(j).getDecodeNo())) {
                        dcSameWarn.setVisible(true);
                        dcCheck = false;
                        break;
                    }
                    dcEmptyWarn.setVisible(false);
                    dcFormatWarn.setVisible(false);
                    dcSameWarn.setVisible(false);
                    dcCheck = true;
                    RYCOXv2.log = new LogFile(RYCOXv2.user, " has edited the decoder number from " + ServicePanel.temp[2] + " to " + dcInput.getText() + ".");
                    RYCOXv2.logList.add(RYCOXv2.log);
                    RYCOXv2.printLog();
                }
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
            subsPkgCheck = true;
        }
        if (scCheck == true && dcCheck == true && addCheck == true && subsPkgCheck == true) {
            updateList();
            updateSubsList();
            JOptionPane.showMessageDialog(null, "You have edited the service" + scInput.getText(), "Service Edited", JOptionPane.INFORMATION_MESSAGE);
            dispose();
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

        LinkedList<String> addElements = new LinkedList<>();
        for (int p = 0; p < rightList.getModel().getSize(); p++) {
            addElements.add(((String) rightList.getModel().getElementAt(p)).substring(0, 3));
        }
        for (i = 0; i < addElements.size(); i++) {
            RYCOXv2.subsList.add(new Subscription(ServicePanel.temp[0], subsNo, addElements.get(i)));
        }
    }

    public void updateList() {
        int k;
        for (k = 0; k < RYCOXv2.servList.size(); k++) {
            if (RYCOXv2.servList.get(k).getSmartCardNo().equalsIgnoreCase(ServicePanel.temp[0])) {
                break;
            }
        }
        RYCOXv2.servList.get(k).setSmartCardNo(scInput.getText());
        RYCOXv2.servList.get(k).setDecoderNo(dcInput.getText());
        RYCOXv2.servList.get(k).setAddress(addInput.getText());
    }

    @SuppressWarnings("unchecked")
    public void pkgAdd() {
        int leftIndex = leftList.getSelectedIndex();
        String toBeAdded = ((String) leftList.getModel().getElementAt(leftIndex));
        LinkedList<String> leftElements = new LinkedList<>();
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

        LinkedList<String> rightElements = new LinkedList<>();
        for (int z = 0; z < rightList.getModel().getSize(); z++) {
            rightElements.add((String) rightList.getModel().getElementAt(z));
        }
        rightElements.add(toBeAdded);
        rightElArr = rightElements.toArray(new String[rightElements.size()]);
        rightList.setListData(rightElArr);
        rightList.repaint();
        rightList.revalidate();
    }

    @SuppressWarnings("unchecked")
    public void pkgRemove() {
        int rightIndex = rightList.getSelectedIndex();
        String toBeRemoved = ((String) rightList.getModel().getElementAt(rightIndex));
        LinkedList<String> rightElements = new LinkedList<>();
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


        LinkedList<String> leftElements = new LinkedList<>();
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
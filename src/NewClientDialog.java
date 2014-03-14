import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
class NewClientDialog extends JDialog {

    // Variables declaration
    private JPanel BGPanel;
    private JSeparator DialogSeparator;
    private JButton cancelbutton;
    private JTextArea clAddInput;
    private JLabel clAddLabel;
    @SuppressWarnings("rawtypes")
    private JComboBox clAgeCombo;
    private JLabel clAgeLabel;
    private JTextField clAppearedName;
    private JLabel clAppearedNameLabel;
    private JTextField clEmailInput;
    private JLabel clEmailLabel;
    private JTextField clFNameInput;
    private JLabel clFNameLabel;
    private JTextField clICInput;
    private JLabel clICLabel;
    private JLabel clIDFormat;
    private JTextField clIDInput;
    private JLabel clIDLabel;
    private JTextField clLNameInput;
    private JLabel clLNameLabel;
    @SuppressWarnings("rawtypes")
    private JComboBox clTypeCombo;
    private JLabel clTypeLabel;
    private JScrollPane jScrollPane1;
    private JButton submitbutton;
    private Color bColor = new Color(23, 28, 30);
    private Color fColor = new Color(255, 255, 255);
    private WarningLabel warningMsgID, warningMsgName, warningMsgAge, warningMsgAddr, warningMsgEmail, warningMsgIC, warningMsgFormat;
    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);
    private String type;
    private WarningLabel warningMsgIDEx;
    // End of variables declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    public NewClientDialog(JFrame parent) {
        super(parent, "RYCOX System - Add new client", true);
        setResizable(false);
        setLocationRelativeTo(null);

        BGPanel = new JPanel();
        BGPanel.setBackground(bColor);
        BGPanel.setForeground(fColor);
        clTypeCombo = new JComboBox();
        clTypeLabel = new JLabel();
        clTypeLabel.setForeground(fColor);
        clLNameInput = new JTextField("");
        clLNameLabel = new JLabel();
        clLNameLabel.setForeground(fColor);
        clFNameInput = new JTextField("");
        clIDInput = new JTextField("");
        clIDLabel = new JLabel();
        clIDLabel.setForeground(fColor);
        clFNameLabel = new JLabel();
        clFNameLabel.setForeground(fColor);
        clAgeLabel = new JLabel();
        clAgeLabel.setForeground(fColor);
        clAgeCombo = new JComboBox();
        clICLabel = new JLabel();
        clICLabel.setForeground(fColor);
        clICInput = new JTextField("");
        clAddLabel = new JLabel();
        clAddLabel.setForeground(fColor);
        jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea();
        submitbutton = new JButton();
        cancelbutton = new JButton();
        DialogSeparator = new JSeparator();
        clIDFormat = new JLabel();
        clIDFormat.setForeground(fColor);
        clEmailLabel = new JLabel();
        clEmailLabel.setForeground(fColor);
        clEmailInput = new JTextField("");
        clAppearedNameLabel = new JLabel();
        clAppearedNameLabel.setForeground(fColor);
        clAppearedName = new JTextField("");


        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        BGPanel.setLayout(null);

        warningMsgID = new WarningLabel("ID cannot be empty.");
        warningMsgName = new WarningLabel("First/Last name cannot be empty.");
        warningMsgAge = new WarningLabel("Select a valid age.");
        warningMsgAddr = new WarningLabel("Address cannot be empty.");
        warningMsgEmail = new WarningLabel("E-mail cannot be empty.");
        warningMsgIC = new WarningLabel("IC cannot be empty.");
        warningMsgFormat = new WarningLabel("Wrong ID format.");
        warningMsgIDEx = new WarningLabel("Existed ID.");

        clTypeCombo.setFont(defont);
        clTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"Individual", "Government", "Private Organisation", "NGO"}));
        BGPanel.add(clTypeCombo);
        clTypeCombo.setBounds(440, 14, 150, 25);
        clTypeCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Individual")) {
                    clAgeCombo.setEnabled(true);
                    clAgeCombo.setSelectedItem("18");
                    clICInput.setEditable(true);
                    clICInput.setEnabled(true);
                    clIDFormat.setText("I");
                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Government")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
                    clIDFormat.setText("G");

                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("NGO")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
                    clIDFormat.setText("N");
                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Private Organisation")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
                    clIDFormat.setText("P");
                }
            }
        });

        clTypeLabel.setFont(defont);
        clTypeLabel.setText("Type of Client:");
        BGPanel.add(clTypeLabel);
        clTypeLabel.setBounds(349, 14, 81, 25);

        clLNameInput.setFont(defont);
        BGPanel.add(clLNameInput);
        clLNameInput.setBounds(420, 75, 175, 25);

        clLNameLabel.setFont(defont);
        clLNameLabel.setText("Last Name  :");
        BGPanel.add(clLNameLabel);
        clLNameLabel.setBounds(316, 75, 100, 25);

        clFNameInput.setFont(defont);
        BGPanel.add(clFNameInput);
        clFNameInput.setBounds(110, 75, 175, 25);

        clIDInput.setFont(defont);
        BGPanel.add(clIDInput);
        clIDInput.setBounds(96, 43, 62, 25);
        clIDLabel.setFont(defont);
        clIDLabel.setText("Client ID   :");
        BGPanel.add(clIDLabel);
        warningMsgID.setBounds(10, 15, 150, 25);
        BGPanel.add(warningMsgID);
        warningMsgFormat.setBounds(10, 15, 150, 25);
        warningMsgIDEx.setBounds(10, 15, 150, 25);
        BGPanel.add(warningMsgIDEx);
        BGPanel.add(warningMsgFormat);
        clIDLabel.setBounds(10, 43, 62, 25);


        clFNameLabel.setFont(defont);
        clFNameLabel.setText("First Name:");
        BGPanel.add(clFNameLabel);
        clFNameLabel.setBounds(10, 75, 100, 25);
        warningMsgName.setBounds(360, 43, 200, 25);
        BGPanel.add(warningMsgName);

        clFNameInput.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                if (clFNameInput.getText().trim() != "" && clFNameInput.getText().trim() != null && clLNameInput.getText().trim() != "" && clLNameInput.getText().trim() != null)
                    clAppearedName.setText(clFNameInput.getText().trim() + " " + clLNameInput.getText().trim());
                repaint();
            }
        });

        clLNameInput.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                if (clFNameInput.getText().trim() != "" && clFNameInput.getText().trim() != null && clLNameInput.getText().trim() != "" && clLNameInput.getText().trim() != null)
                    clAppearedName.setText(clFNameInput.getText().trim() + " " + clLNameInput.getText().trim());
                repaint();
            }
        });

        clAgeLabel.setFont(defont);
        clAgeLabel.setText("Age        :");
        BGPanel.add(clAgeLabel);
        clAgeLabel.setBounds(10, 175, 58, 25);


        clAgeCombo.setFont(defont);
        clAgeCombo.setModel(new DefaultComboBoxModel(new String[]{"--", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"}));
        BGPanel.add(clAgeCombo);
        clAgeCombo.setBounds(86, 175, 50, 25);
        warningMsgAge.setBounds(150, 175, 150, 25);
        BGPanel.add(warningMsgAge);

        clICLabel.setFont(defont);
        clICLabel.setText("Identification Card Number  :");
        BGPanel.add(clICLabel);
        clICLabel.setBounds(10, 205, 175, 25);

        clICInput.setFont(defont);
        BGPanel.add(clICInput);
        clICInput.setBounds(190, 205, 200, 25);
        warningMsgIC.setBounds(400, 205, 150, 25);
        BGPanel.add(warningMsgIC);

        clAddLabel.setFont(defont);
        clAddLabel.setText("Billing Address	:");
        BGPanel.add(clAddLabel);
        clAddLabel.setBounds(10, 250, 88, 25);

        clAddInput.setColumns(18);
        clAddInput.setRows(3);
        jScrollPane1.setViewportView(clAddInput);

        BGPanel.add(jScrollPane1);
        jScrollPane1.setBounds(120, 241, 460, 75);
        warningMsgAddr.setBounds(120, 330, 150, 25);
        BGPanel.add(warningMsgAddr);
        submitbutton.setText("OK");
        submitbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitbuttonActionPerformed(evt);
            }
        });
        BGPanel.add(submitbutton);
        submitbutton.setBounds(221, 374, 62, 33);

        cancelbutton.setText("Cancel");
        cancelbutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });
        BGPanel.add(cancelbutton);
        cancelbutton.setBounds(301, 374, 70, 33);
        BGPanel.add(DialogSeparator);
        DialogSeparator.setBounds(0, 358, 590, 10);

        clIDFormat.setFont(defont);
        clIDFormat.setText("I");
        BGPanel.add(clIDFormat);
        clIDFormat.setBounds(82, 43, 10, 25);

        clEmailLabel.setFont(defont);
        clEmailLabel.setText("E-mail Address :");
        BGPanel.add(clEmailLabel);
        clEmailLabel.setBounds(10, 143, 100, 25);

        clEmailInput.setFont(defont);
        BGPanel.add(clEmailInput);
        clEmailInput.setBounds(115, 143, 209, 25);
        warningMsgEmail.setBounds(350, 143, 150, 25);
        BGPanel.add(warningMsgEmail);
        clAppearedNameLabel.setFont(defont);
        clAppearedNameLabel.setText("Name Appear in System :");
        BGPanel.add(clAppearedNameLabel);
        clAppearedNameLabel.setBounds(10, 107, 139, 25);

        clAppearedName.setEditable(false);
        clAppearedName.setFont(defont);
        clAppearedName.setBorder(null);
        clAppearedName.setBackground(BGPanel.getBackground());
        clAppearedName.setForeground(fColor);

        BGPanel.add(clAppearedName);
        clAppearedName.setBounds(153, 107, 437, 25);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(BGPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(BGPanel, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }//end constructor

    @SuppressWarnings("unused")
    private void submitbuttonActionPerformed(ActionEvent evt) {
        // check empty
        boolean checkEmptyFN = false;
        boolean checkEmptyLN = false;
        boolean checkEmptyID = false;
        boolean checkEmptyIC = false;
        boolean checkEmptyADD = false;
        boolean checkEmptyAGE = false;
        boolean checkEmptyEM = false;
        boolean checkWrongFormat = false;
        boolean existed = false;
        String appendedID = "";

        type = (String) clTypeCombo.getSelectedItem();
        if (type.equals("Individual")) {
            String[] temp = {clFNameInput.getText().trim(), clLNameInput.getText().trim(), clIDInput.getText().trim(), clICInput.getText().trim(), clAddInput.getText().trim(), ((String) clAgeCombo.getSelectedItem()), clEmailInput.getText().trim()};
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 0:
                        if (temp[0].length() == 0 || temp[0] == null || temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyFN = true;
                            warningMsgName.setVisible(true);
                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 1:
                        if (temp[0].length() == 0 || temp[0] == null || temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyFN = true;
                            warningMsgName.setVisible(true);

                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 2:
                        if (temp[2].length() == 0 || temp[2] == null) {
                            checkEmptyID = true;
                            warningMsgID.setVisible(true);
                            warningMsgFormat.setVisible(false);
                            warningMsgIDEx.setVisible(false);
                        } else {
                            warningMsgID.setVisible(false);
                            if (temp[2].matches("[0-9]{6}")) {
                                checkWrongFormat = false;
                                warningMsgFormat.setVisible(false);
                                warningMsgIDEx.setVisible(false);
                                if (((String) clTypeCombo.getSelectedItem()).equals("Individual")) {
                                    appendedID = "I" + temp[2];
                                } else if (((String) clTypeCombo.getSelectedItem()).equals("Government")) {
                                    appendedID = "G" + temp[2];
                                } else if (((String) clTypeCombo.getSelectedItem()).equals("NGO")) {
                                    appendedID = "N" + temp[2];
                                } else if (((String) clTypeCombo.getSelectedItem()).equals("Private Organisation")) {
                                    appendedID = "P" + temp[2];
                                }
                                System.out.println(appendedID);
                                for (int p = 0; p < RYCOXv2.clientList.size(); p++) {
                                    if (appendedID.equals(RYCOXv2.clientList.get(p).getClientID())) {
                                        warningMsgIDEx.setVisible(true);
                                        existed = true;
                                        break;
                                    } else {
                                        warningMsgIDEx.setVisible(false);
                                        existed = false;
                                        break;
                                    }
                                }
                            } else {
                                checkWrongFormat = true;
                                warningMsgFormat.setVisible(true);
                                warningMsgIDEx.setVisible(false);
                            }
                        }
                    case 3:
                        if (temp[3].length() == 0 || temp[3] == null) {
                            checkEmptyIC = true;
                            warningMsgIC.setVisible(true);

                        } else {
                            warningMsgIC.setVisible(false);
                        }
                    case 4:
                        if (temp[4].length() == 0 || temp[4] == null) {
                            checkEmptyADD = true;
                            warningMsgAddr.setVisible(true);
                        } else {
                            warningMsgAddr.setVisible(false);
                        }
                    case 5:
                        if (temp[5].equals("--")) {
                            checkEmptyAGE = true;
                            warningMsgAge.setVisible(true);

                        } else {
                            warningMsgAge.setVisible(false);
                        }
                    case 6:
                        if (temp[6].length() == 0 || temp[6] == null) {
                            checkEmptyEM = true;
                            warningMsgEmail.setVisible(true);
                        } else {
                            warningMsgEmail.setVisible(false);
                        }
                }
                break;
            }
            if (checkEmptyFN == false && checkEmptyLN == false && checkEmptyID == false && checkEmptyIC == false && checkEmptyADD == false && checkEmptyAGE == false && checkEmptyEM == false && checkWrongFormat == false) {
                clientCreation();
                dispose();
            }
        } else if (type.equals("Government") || type.equals("Government") || type.equals("Private Organisation") || type.equals("NGO")) {
            String[] temp = {clFNameInput.getText().trim(), clLNameInput.getText().trim(), clIDInput.getText().trim(), clAddInput.getText().trim(), clEmailInput.getText().trim()};
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 0:
                        if (temp[0].length() == 0 || temp[0] == null || temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyFN = true;
                            warningMsgName.setVisible(true);
                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 1:
                        if (temp[0].length() == 0 || temp[0] == null || temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyFN = true;
                            warningMsgName.setVisible(true);

                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 2:
                        if (temp[2].length() == 0 || temp[2] == null) {
                            checkEmptyID = true;
                            warningMsgID.setVisible(true);
                            warningMsgFormat.setVisible(false);
                        } else {
                            warningMsgID.setVisible(false);
                            if (temp[2].matches("[0-9]{6}")) {
                                checkWrongFormat = false;
                                warningMsgFormat.setVisible(false);
                            } else {
                                checkWrongFormat = true;
                                warningMsgFormat.setVisible(true);
                            }
                        }
                    case 3:
                        if (temp[3].length() == 0 || temp[3] == null) {
                            checkEmptyADD = true;
                            warningMsgAddr.setVisible(true);
                        } else {
                            warningMsgAddr.setVisible(false);
                        }
                    case 4:
                        if (temp[4].length() == 0 || temp[4] == null) {
                            checkEmptyEM = true;
                            warningMsgEmail.setVisible(true);
                        } else {
                            warningMsgEmail.setVisible(false);
                        }
                }
                break;
            }
            if (existed == false && checkEmptyFN == false && checkEmptyLN == false && checkEmptyID == false && checkEmptyADD == false && checkEmptyEM == false && checkWrongFormat == false) {
                clientCreation();
                dispose();
            }
        }
    }

    private void cancelbuttonActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void clientCreation() {
        String name = clFNameInput.getText().trim() + " " + clLNameInput.getText().trim();
        String ageStr = ((String) clAgeCombo.getSelectedItem()).trim();
        String address = clAddInput.getText().trim();
        String clID;
        String type2;
        if (((String) clTypeCombo.getSelectedItem()).equals("Individual")) {
            clID = "I" + clIDInput.getText().trim();
            type2 = "Ind";
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Government")) {
            clID = "G" + clIDInput.getText().trim();
            type2 = "Gov";
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Private Organisation")) {
            clID = "P" + clIDInput.getText().trim();
            type2 = "Prv";
        } else {
            clID = "N" + clIDInput.getText().trim();
            type2 = "NGO";
        }
        String accStatus = "ACTIVE";
        String email = clEmailInput.getText().trim();

        //model
        if (type2.equalsIgnoreCase("Ind")) {
            int age = Integer.parseInt(ageStr);
            String ic = clICInput.getText().trim();
            RYCOXv2.clientList.add(new IndividualClient(name, age, ic, address, clID, accStatus, email));
        } else if (type2.equalsIgnoreCase("Gov")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        } else if (type2.equalsIgnoreCase("prv")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        } else if (type2.equalsIgnoreCase("ngo")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        }

        RYCOXv2.log = new LogFile(RYCOXv2.user, " has created a new client's account of ID '" + clID + "'.");
        RYCOXv2.logList.add(RYCOXv2.log);
        //viewer
        ClientPanel.updateAddTable();
        JOptionPane.showMessageDialog(BGPanel, "Successfully added client '" + clID + "'!", "RYCOX System", JOptionPane.INFORMATION_MESSAGE);
    }

    private class WarningLabel extends JLabel {
        public WarningLabel(String s) {
            super(s);
            setVisible(false);
            //setVisible(true);
            setForeground(Color.RED);
        }
    }
}
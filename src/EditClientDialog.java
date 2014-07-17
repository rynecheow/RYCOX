import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class EditClientDialog extends JDialog {

    private JTextArea clAddInput;
    private JComboBox<String> clAgeCombo;
    private JTextField clAppearedName;
    private JTextField clEmailInput;
    private JTextField clNameInput;
    private JTextField clICInput;
    private WarningLabel warningMsgName;
    private WarningLabel warningMsgAge;
    private WarningLabel warningMsgAddr;
    private WarningLabel warningMsgEmail;
    private WarningLabel warningMsgIC;
    private WarningLabel warningMsgEMEx;
    private WarningLabel warningEmailInvFormat;
    private String[] data = ClientPanel.editionData;
    private String paramType;
    // End of variables declaration

    public EditClientDialog(JFrame parent) {
        super(parent, "RYCOX System - Edit client", true);
        setResizable(false);
        setLocation(300, 150);
        JPanel BGPanel = new JPanel();
        Color bColor = new Color(23, 28, 30);
        BGPanel.setBackground(bColor);
        Color fColor = new Color(255, 255, 255);
        BGPanel.setForeground(fColor);
        JLabel clTypetype;
        JLabel clTypeLabel = new JLabel();
        clTypeLabel.setForeground(fColor);
        clNameInput = new JTextField(data[1]);
        JTextField clIDInput = new JTextField(data[0]);
        JLabel clIDLabel = new JLabel();
        clIDLabel.setForeground(fColor);
        JLabel clNameLabel = new JLabel();
        clNameLabel.setForeground(fColor);
        JLabel clAgeLabel = new JLabel();
        clAgeLabel.setForeground(fColor);
        clAgeCombo = new JComboBox<>();
        JLabel clICLabel = new JLabel();
        clICLabel.setForeground(fColor);
        clICInput = new JTextField(data[6]);
        JLabel clAddLabel = new JLabel();
        clAddLabel.setForeground(fColor);
        JScrollPane jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea(data[3]);
        JButton submitbutton = new JButton();
        JButton cancelbutton = new JButton();
        JSeparator dialogSeparator = new JSeparator();
        JLabel clEmailLabel = new JLabel("");
        clEmailLabel.setForeground(fColor);
        clEmailInput = new JTextField(data[4]);
        JLabel clAppearedNameLabel = new JLabel();
        clAppearedNameLabel.setForeground(fColor);
        clAppearedName = new JTextField("");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        BGPanel.setLayout(null);

        WarningLabel warningMsgID = new WarningLabel("ID cannot be empty.");
        warningMsgName = new WarningLabel("First/Last name cannot be empty.");
        warningMsgAge = new WarningLabel("Select a valid age.");
        warningMsgAddr = new WarningLabel("Address cannot be empty.");
        warningMsgEmail = new WarningLabel("E-mail cannot be empty.");
        warningMsgIC = new WarningLabel("IC cannot be empty.");
        WarningLabel warningMsgFormat = new WarningLabel("Wrong ID format.");
        warningEmailInvFormat = new WarningLabel("Invalid e-mail format.");
        warningMsgEMEx = new WarningLabel("E-mail address in use.");

        if (data[0].matches("[Ii][0-9]{6}")) {
            paramType = "Individual";
            clAgeCombo.setEnabled(true);
            clICInput.setEnabled(true);
            clICInput.setEditable(true);
        } else if (data[0].matches("[Gg][0-9]{6}")) {
            paramType = "Government";
            clAgeCombo.setEnabled(false);
            clICInput.setEnabled(false);
            clICInput.setEditable(false);
        } else if (data[0].matches("[Nn][0-9]{6}")) {
            paramType = "NGO";
            clAgeCombo.setEnabled(false);
            clICInput.setEnabled(false);
            clICInput.setEditable(false);
        } else if (data[0].matches("[Pp][0-9]{6}")) {
            paramType = "Private Organisation";
            clAgeCombo.setEnabled(false);
            clICInput.setEnabled(false);
            clICInput.setEditable(false);
        }


        clTypetype = new JLabel(paramType);
        clTypetype.setBounds(440, 14, 150, 25);
        clTypetype.setForeground(fColor);
        BGPanel.add(clTypetype);
        Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);
        clTypetype.setFont(defont);
        clTypeLabel.setFont(defont);
        clTypeLabel.setText("Type of Client:");
        BGPanel.add(clTypeLabel);
        clTypeLabel.setBounds(349, 14, 81, 25);

        clNameInput.setFont(defont);
        BGPanel.add(clNameInput);
        clNameInput.setBounds(70, 75, 275, 25);

        clIDInput.setFont(defont);
        clIDInput.setEditable(false);
        clIDInput.setBackground(bColor);
        clIDInput.setForeground(fColor);
        clIDInput.setBorder(null);
        BGPanel.add(clIDInput);
        clIDInput.setBounds(86, 43, 62, 25);
        clIDLabel.setFont(defont);
        clIDLabel.setText("Client ID   :");
        BGPanel.add(clIDLabel);
        warningMsgID.setBounds(10, 15, 150, 25);
        BGPanel.add(warningMsgID);
        warningMsgFormat.setBounds(10, 15, 150, 25);
        BGPanel.add(warningMsgFormat);
        clIDLabel.setBounds(10, 43, 62, 25);


        clNameLabel.setFont(defont);
        clNameLabel.setText("Name:");
        BGPanel.add(clNameLabel);
        clNameLabel.setBounds(10, 75, 50, 25);
        warningMsgName.setBounds(360, 43, 200, 25);
        BGPanel.add(warningMsgName);

        clNameInput.addCaretListener(e -> {
            if (!"".equals(clNameInput.getText().trim())) {
                clAppearedName.setText(clNameInput.getText().trim());
            }
            repaint();
        });

        clAgeLabel.setFont(defont);
        clAgeLabel.setText("Age        :");
        BGPanel.add(clAgeLabel);
        clAgeLabel.setBounds(10, 175, 58, 25);


        clAgeCombo.setFont(defont);
        clAgeCombo.setModel(new DefaultComboBoxModel<>(new String[]{"--", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"}));
        BGPanel.add(clAgeCombo);
        clAgeCombo.setSelectedItem(data[5]);
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
        submitbutton.addActionListener(this::submitbuttonActionPerformed);
        BGPanel.add(submitbutton);
        submitbutton.setBounds(221, 374, 62, 33);

        cancelbutton.setText("Cancel");
        cancelbutton.addActionListener(this::cancelbuttonActionPerformed);
        BGPanel.add(cancelbutton);
        cancelbutton.setBounds(301, 374, 70, 33);
        BGPanel.add(dialogSeparator);
        dialogSeparator.setBounds(0, 358, 590, 10);

        clEmailLabel.setFont(defont);
        clEmailLabel.setText("E-mail Address :");
        BGPanel.add(clEmailLabel);
        clEmailLabel.setBounds(10, 143, 100, 25);

        clEmailInput.setFont(defont);
        BGPanel.add(clEmailInput);
        clEmailInput.setBounds(115, 143, 209, 25);
        warningMsgEmail.setBounds(350, 143, 150, 25);
        warningEmailInvFormat.setBounds(350, 143, 150, 25);
        warningMsgEMEx.setBounds(350, 143, 150, 25);
        BGPanel.add(warningMsgEmail);
        BGPanel.add(warningEmailInvFormat);
        BGPanel.add(warningMsgEMEx);
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
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.PREFERRED_SIZE, 422, GroupLayout.PREFERRED_SIZE));
        ClientPanel.defaultButtonSet();
        pack();
    }//end constructor

    @SuppressWarnings("unused")
    private void submitbuttonActionPerformed(ActionEvent evt) {
        // check empty
        boolean checkEmptyN = false;
        boolean checkEmptyIC = false;
        boolean checkEmptyADD = false;
        boolean checkEmptyAGE = false;
        boolean checkEmptyEM = false;
        boolean valEmail = true;
        boolean existed = false;
        switch (paramType) {
            case "Individual": {
                String[] temp = {clNameInput.getText().trim(), clICInput.getText().trim(), clAddInput.getText().trim(), ((String) clAgeCombo.getSelectedItem()), clEmailInput.getText().trim()};
                switch (temp.length) {
                    case 0:
                        if (temp[0].length() == 0 || temp[0] == null || temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyN = true;
                            warningMsgName.setVisible(true);
                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 1:
                        if (temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyIC = true;
                            warningMsgIC.setVisible(true);

                        } else {
                            warningMsgIC.setVisible(false);
                        }
                    case 2:
                        if (temp[2].length() == 0 || temp[2] == null) {
                            checkEmptyADD = true;
                            warningMsgAddr.setVisible(true);
                        } else {
                            warningMsgAddr.setVisible(false);
                        }
                    case 3:
                        if (temp[3].equals("--")) {
                            checkEmptyAGE = true;
                            warningMsgAge.setVisible(true);

                        } else {
                            warningMsgAge.setVisible(false);
                        }
                    case 4:
                        if (temp[4].length() == 0 || temp[4] == null) {
                            checkEmptyEM = true;
                            warningMsgEmail.setVisible(true);
                            warningEmailInvFormat.setVisible(false);
                            warningMsgEMEx.setVisible(false);
                        } else {
                            warningMsgEmail.setVisible(false);
                            if (temp[4].matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
                                warningEmailInvFormat.setVisible(false);
                                valEmail = true;
                                for (int p = 0; p < App.clientList.size(); p++) {
                                    if (temp[4].equalsIgnoreCase(App.clientList.get(p).getEmail())) {
                                        if (!clEmailInput.getText().equalsIgnoreCase(temp[4])) {
                                            warningMsgEMEx.setVisible(true);
                                            existed = true;
                                            break;
                                        } else {
                                            warningMsgEMEx.setVisible(false);
                                            existed = false;
                                        }
                                    } else {
                                        warningMsgEMEx.setVisible(false);
                                        existed = false;
                                    }
                                }
                            } else {
                                valEmail = false;
                                warningEmailInvFormat.setVisible(true);
                            }
                        }
                }
                if (!checkEmptyN && !checkEmptyIC && valEmail && !existed && !checkEmptyADD && !checkEmptyAGE && !checkEmptyEM) {
                    clientEdition();
                    dispose();
                }
            }
            case "Government":
            case "Private Organisation":
            case "NGO": {
                String[] temp = {clNameInput.getText().trim(), clAddInput.getText().trim(), clEmailInput.getText().trim()};
                switch (temp.length) {
                    case 0:
                        if (temp[0].length() == 0 || temp[0] == null) {
                            checkEmptyN = true;
                            warningMsgName.setVisible(true);
                        } else {
                            warningMsgName.setVisible(false);
                        }
                    case 1:
                        if (temp[1].length() == 0 || temp[1] == null) {
                            checkEmptyADD = true;
                            warningMsgAddr.setVisible(true);
                        } else {
                            warningMsgAddr.setVisible(false);
                        }
                    case 2:
                        if (temp[2].length() == 0 || temp[2] == null) {
                            checkEmptyEM = true;
                            warningMsgEmail.setVisible(true);
                            warningEmailInvFormat.setVisible(false);
                            warningMsgEMEx.setVisible(false);
                        } else {
                            warningMsgEmail.setVisible(false);
                            if (temp[2].matches("[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")) {
                                warningEmailInvFormat.setVisible(false);
                                valEmail = true;
                                for (int q = 0; q < App.clientList.size(); q++) {
                                    if (temp[2].equalsIgnoreCase(App.clientList.get(q).getEmail())) {
                                        if (!clEmailInput.getText().equalsIgnoreCase(temp[2])) {
                                            warningMsgEMEx.setVisible(true);
                                            existed = true;
                                            break;
                                        } else {
                                            warningMsgEMEx.setVisible(false);
                                            existed = false;
                                        }
                                    } else {
                                        warningMsgEMEx.setVisible(false);
                                        existed = false;
                                    }
                                }
                            } else {
                                valEmail = false;
                                warningEmailInvFormat.setVisible(true);
                            }
                        }
                }
                if (!checkEmptyN && valEmail && !existed && !checkEmptyADD && !checkEmptyEM) {
                    clientEdition();
                    dispose();
                }
            }
        }
    }

    private void cancelbuttonActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void clientEdition() {
        String name = clNameInput.getText().trim();
        String ageStr = ((String) clAgeCombo.getSelectedItem()).trim();
        String address = clAddInput.getText().trim();
        String clID = data[0];

        String accStatus = "ACTIVE";
        String email = clEmailInput.getText().trim();
        //model
        for (int i = 0; i < App.clientList.size(); i++) {
            if (clID.equalsIgnoreCase(App.clientList.get(i).getClientID())) {
                if (App.clientList.get(i) instanceof IndividualClient) {
                    int age = Integer.parseInt(ageStr);
                    String ic = clICInput.getText().trim();
                    App.clientList.get(i).setName(name);
                    App.clientList.get(i).setBillingAddress(address);
                    App.clientList.get(i).setEmail(email.toLowerCase());
                    App.clientList.get(i).setAccountStatus(accStatus.toUpperCase());
                    ((IndividualClient) App.clientList.get(i)).setAge(age);
                    ((IndividualClient) App.clientList.get(i)).setIC(ic);
                } else if (App.clientList.get(i) instanceof GovClient || App.clientList.get(i) instanceof PrvClient || App.clientList.get(i) instanceof NGOClient) {
                    App.clientList.get(i).setName(name);
                    App.clientList.get(i).setBillingAddress(address);
                    App.clientList.get(i).setEmail(email.toLowerCase());
                    App.clientList.get(i).setAccountStatus(accStatus.toUpperCase());
                }
            }
        }
        App.log = new LogFile(App.user, " has edited a new client's account of ID '" + clID + "'.");
        App.logList.add(App.log);
        ClientPanel.updateEditTable();
    }

    private class WarningLabel extends JLabel {

        public WarningLabel(String s) {
            super(s);
            setVisible(false);
            setForeground(Color.RED);
        }
    }
}

/**************************************************************************
 * (C) Copyright 2012 by Ryne Cheow Yeong Chi , Ng Jia Jiun               *
 * Lai Li Hao. All rights reserved.                                       *
 *                                                                        *
 * DISCLAIMER: The writer of this particular program have used their      *
 * best efforts in preparing this program. These efforts include the      *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The writers make                     *
 * no warranty of any kind, expressed or implied, with regard to this     *
 * program. The writers shall not be liable in                            *
 * any event for incidental or                                            *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of this program.                       *
 *************************************************************************/
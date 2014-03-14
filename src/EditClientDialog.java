import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EditClientDialog extends JDialog {

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
    private JTextField clNameInput;
    private JLabel clNameLabel;
    private JTextField clICInput;
    private JLabel clICLabel;
    private JTextField clIDInput;
    private JLabel clIDLabel;
    //	private JTextField clLNameInput;
    //	private JLabel clLNameLabel;
    private JLabel clTypeLabel;
    private JScrollPane jScrollPane1;
    private JButton submitbutton;
    private Color bColor = new Color(23, 28, 30);
    private Color fColor = new Color(255, 255, 255);
    private WarningLabel warningMsgID, warningMsgName, warningMsgAge, warningMsgAddr, warningMsgEmail, warningMsgIC, warningMsgFormat;
    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);
    private JLabel clTypetype;
    private String[] data = ClientPanel.editionData;
    private String paramType;
    // End of variables declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    public EditClientDialog(JFrame parent) {
        super(parent, "RYCOX System - Edit client", true);
        setResizable(false);
        setLocationRelativeTo(null);

        BGPanel = new JPanel();
        BGPanel.setBackground(bColor);
        BGPanel.setForeground(fColor);
        clTypetype = new JLabel();
        clTypeLabel = new JLabel();
        clTypeLabel.setForeground(fColor);
        //		clLNameInput = new JTextField("");
        //		clLNameLabel = new JLabel();
        //		clLNameLabel.setForeground(fColor);
        clNameInput = new JTextField(data[1]);
        clIDInput = new JTextField(data[0]);
        clIDLabel = new JLabel();
        clIDLabel.setForeground(fColor);
        clNameLabel = new JLabel();
        clNameLabel.setForeground(fColor);
        clAgeLabel = new JLabel();
        clAgeLabel.setForeground(fColor);
        clAgeCombo = new JComboBox();
        clICLabel = new JLabel();
        clICLabel.setForeground(fColor);
        clICInput = new JTextField(data[6]);
        clAddLabel = new JLabel();
        clAddLabel.setForeground(fColor);
        jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea(data[3]);
        submitbutton = new JButton();
        cancelbutton = new JButton();
        DialogSeparator = new JSeparator();
        clEmailLabel = new JLabel("");
        clEmailLabel.setForeground(fColor);
        clEmailInput = new JTextField(data[4]);
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

        clNameInput.addCaretListener(new CaretListener() {
            public void caretUpdate(CaretEvent e) {
                if (clNameInput.getText().trim() != "" && clNameInput.getText().trim() != null)
                    clAppearedName.setText(clNameInput.getText().trim());
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
        clAgeCombo.setSelectedItem((String) data[5]);
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
        boolean checkEmptyN = false;
        boolean checkEmptyIC = false;
        boolean checkEmptyADD = false;
        boolean checkEmptyAGE = false;
        boolean checkEmptyEM = false;

        if (paramType.equals("Individual")) {
            String[] temp = {clNameInput.getText().trim(), clICInput.getText().trim(), clAddInput.getText().trim(), ((String) clAgeCombo.getSelectedItem()), clEmailInput.getText().trim()};
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
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
                        } else {
                            warningMsgEmail.setVisible(false);
                        }
                }
                break;
            }
            if (checkEmptyN == false && checkEmptyIC == false && checkEmptyADD == false && checkEmptyAGE == false && checkEmptyEM == false) {
                clientEdition();
                dispose();
            }
        } else if (paramType.equals("Government") || paramType.equals("Private Organisation") || paramType.equals("NGO")) {
            String[] temp = {clNameInput.getText().trim(), clAddInput.getText().trim(), clEmailInput.getText().trim()};
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
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
                        } else {
                            warningMsgEmail.setVisible(false);
                        }
                }
                break;
            }
            if (checkEmptyN == false && checkEmptyADD == false && checkEmptyEM == false) {
                clientEdition();
                dispose();
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
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            if (clID.equalsIgnoreCase(RYCOXv2.clientList.get(i).getClientID())) {
                if (RYCOXv2.clientList.get(i) instanceof IndividualClient) {
                    int age = Integer.parseInt(ageStr);
                    String ic = clICInput.getText().trim();
                    RYCOXv2.clientList.get(i).setName(name);
                    RYCOXv2.clientList.get(i).setBillingAddress(address);
                    RYCOXv2.clientList.get(i).setEmail(email);
                    RYCOXv2.clientList.get(i).setAccountStatus(accStatus);
                    ((IndividualClient) RYCOXv2.clientList.get(i)).setAge(age);
                    ((IndividualClient) RYCOXv2.clientList.get(i)).setIC(ic);
                } else if (RYCOXv2.clientList.get(i) instanceof GovClient || RYCOXv2.clientList.get(i) instanceof PrvClient || RYCOXv2.clientList.get(i) instanceof NGOClient) {
                    RYCOXv2.clientList.get(i).setName(name);
                    RYCOXv2.clientList.get(i).setBillingAddress(address);
                    RYCOXv2.clientList.get(i).setEmail(email);
                    RYCOXv2.clientList.get(i).setAccountStatus(accStatus);
                }
            }
        }
        RYCOXv2.log = new LogFile(RYCOXv2.user, " has edited a new client's account of ID '" + clID + "'.");
        RYCOXv2.logList.add(RYCOXv2.log);
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
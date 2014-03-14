import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class EditClientDialog extends JDialog {

    // Variables declaration
    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);
    private JPanel BGPanel;
    private JSeparator DialogSeparator;
    private JButton cancelbutton;
    private JTextArea clAddInput;
    private JLabel clAddLabel;
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
    private JComboBox clTypeCombo;
    private JLabel clTypeLabel;
    private JScrollPane jScrollPane1;
    private JButton submitbutton;

    // End of variables declaration
    public EditClientDialog(JFrame parent) {
        super(parent, "RYCOX System - Edit client", true);
        setResizable(false);
        setLocationRelativeTo(null);

        BGPanel = new JPanel();
        BGPanel.setBackground(new Color(203, 229, 223));
        clTypeCombo = new JComboBox();
        clTypeLabel = new JLabel();
        clLNameInput = new JTextField("");
        clLNameLabel = new JLabel();
        clFNameInput = new JTextField("");
        clIDInput = new JTextField("");
        clIDLabel = new JLabel();
        clIDInput.setEnabled(false);
        clFNameLabel = new JLabel();
        clAgeLabel = new JLabel();
        clAgeCombo = new JComboBox();
        clICLabel = new JLabel();
        clICInput = new JTextField("");
        clAddLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea();
        submitbutton = new JButton();
        cancelbutton = new JButton();
        DialogSeparator = new JSeparator();
        clIDFormat = new JLabel();
        clEmailLabel = new JLabel();
        clEmailInput = new JTextField("");
        clAppearedNameLabel = new JLabel();
        clAppearedName = new JTextField("");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        BGPanel.setLayout(null);

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
                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Government")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("NGO")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
                } else if (((String) clTypeCombo.getSelectedItem()).equalsIgnoreCase("Private Organisation")) {
                    clAgeCombo.setSelectedItem("--");
                    clAgeCombo.setEnabled(false);
                    clICInput.setText("");
                    clICInput.setEditable(false);
                    clICInput.setEnabled(false);
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
        clIDInput.setBounds(90, 43, 62, 25);
        clIDLabel.setFont(defont);
        clIDLabel.setText("Client ID   :");
        BGPanel.add(clIDLabel);
        clIDLabel.setBounds(10, 43, 62, 25);


        clFNameLabel.setFont(defont);
        clFNameLabel.setText("First Name:");
        BGPanel.add(clFNameLabel);
        clFNameLabel.setBounds(10, 75, 100, 25);
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

        clICLabel.setFont(defont);
        clICLabel.setText("Identification Card Number  :");
        BGPanel.add(clICLabel);
        clICLabel.setBounds(10, 205, 175, 25);

        clICInput.setFont(defont);
        BGPanel.add(clICInput);
        clICInput.setBounds(190, 205, 200, 25);

        clAddLabel.setFont(defont);
        clAddLabel.setText("Billing Address  :");
        BGPanel.add(clAddLabel);
        clAddLabel.setBounds(10, 241, 88, 25);

        clAddInput.setColumns(20);
        clAddInput.setRows(5);
        jScrollPane1.setViewportView(clAddInput);

        BGPanel.add(jScrollPane1);
        jScrollPane1.setBounds(108, 241, 482, 106);

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
        clIDFormat.setBounds(82, 43, 4, 25);

        clEmailLabel.setFont(defont);
        clEmailLabel.setText("E-mail Address :");
        BGPanel.add(clEmailLabel);
        clEmailLabel.setBounds(10, 143, 100, 25);

        clEmailInput.setFont(defont);
        BGPanel.add(clEmailInput);
        clEmailInput.setBounds(115, 143, 209, 25);

        clAppearedNameLabel.setFont(defont);
        clAppearedNameLabel.setText("Name Appear in System :");
        BGPanel.add(clAppearedNameLabel);
        clAppearedNameLabel.setBounds(10, 107, 139, 25);

        clAppearedName.setEditable(false);
        clAppearedName.setFont(defont);
        clAppearedName.setBorder(null);
        clAppearedName.setBackground(BGPanel.getBackground());

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

    private void submitbuttonActionPerformed(ActionEvent evt) {
        // check empty
        boolean checkEmpty = false;
        String type = (String) clTypeCombo.getSelectedItem();
        if (type.equals("Individual")) {
            String[] temp = {clFNameInput.getText().trim(), clLNameInput.getText().trim(), clIDInput.getText().trim(), clICInput.getText().trim(), clAddInput.getText().trim(), ((String) clAgeCombo.getSelectedItem())};
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    case 0:
                        if (temp[0].length() == 0 || temp[0] == null) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "First name cannot be empty.", "Empty field", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    case 1:
                        if (temp[1].length() == 0 || temp[1] == null) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "Last name cannot be empty.", "Empty field", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    case 2:
                        if (temp[2].length() == 0 || temp[2] == null) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "Client ID cannot be empty.", "Empty field", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    case 3:
                        if (temp[3].length() == 0 || temp[3] == null) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "IC cannot be empty.", "Empty field", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    case 4:
                        if (temp[4].length() == 0 || temp[4] == null) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "Address cannot be empty.", "Empty field", JOptionPane.WARNING_MESSAGE);
                            break;
                        }
                    case 5:
                        if (temp[5].equals("--")) {
                            checkEmpty = true;
                            JOptionPane.showMessageDialog(BGPanel, "Please select a valid age.", "Invalid age ", JOptionPane.WARNING_MESSAGE);
                            break;
                        }

                }
                break;
            }
            if (checkEmpty == false) {
                if (temp[2].matches("[0-9]{6}")) {
                    clientCreation();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(BGPanel, "Wrong Client ID format.", "Wrong format", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

    }

    private void cancelbuttonActionPerformed(ActionEvent evt) {
        dispose();
    }

    private void clientCreation() {
        String name = clFNameInput.getText().trim() + " " + clLNameInput.getText().trim();
        String ageStr = ((String) clAgeCombo.getSelectedItem()).trim();
        int age = Integer.parseInt(ageStr);
        String address = clAddInput.getText().trim();
        String ic = clICInput.getText().trim();
        String clID;
        String type;
        if (((String) clTypeCombo.getSelectedItem()).equals("Individual")) {
            clID = "I" + clIDInput.getText().trim();
            type = "Ind";
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Government")) {
            clID = "G" + clIDInput.getText().trim();
            type = "Gov";
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Private Organisation")) {
            clID = "P" + clIDInput.getText().trim();
            type = "Prv";
        } else {
            clID = "N" + clIDInput.getText().trim();
            type = "NGO";
        }
        String accStatus = "ACTIVE";
        String email = clEmailInput.getText().trim();

        //model
        if (type.equalsIgnoreCase("Ind")) {
            RYCOXv2.clientList.add(new IndividualClient(name, age, ic, address, clID, accStatus, email));
        } else if (type.equalsIgnoreCase("Gov")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        } else if (type.equalsIgnoreCase("prv")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        } else if (type.equalsIgnoreCase("ngo")) {
            RYCOXv2.clientList.add(new GovClient(name, address, clID, accStatus, email));
        }

        LogFile log = new LogFile(RYCOXv2.user, "has created a new client's account of ID '" + clID + "'.");
        RYCOXv2.logList.add(log);
        //viewer
        ClientPanel.updateAddTable();
    }
}
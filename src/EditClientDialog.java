import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EditClientDialog extends JDialog {


    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);

    public EditClientDialog(JFrame parent) {
        super(parent, "RYCOX System - Add new client", true);
        initComponents();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private void initComponents() {
        setResizable(false);
        setLocationRelativeTo(null);
        BGPanel = new JPanel();
        clTypeCombo = new JComboBox();
        clTypeLabel = new JLabel();
        clLNameInput = new JTextField();
        clLNameLabel = new JLabel();
        clFNameInput = new JTextField();
        clIDInput = new JTextField();
        clIDLabel = new JLabel();
        clFNameLabel = new JLabel();
        clAgeLabel = new JLabel();
        clAgeCombo = new JComboBox();
        clICLabel = new JLabel();
        clICInput = new JTextField();
        clAddLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea();
        submitbutton = new JButton();
        cancelbutton = new JButton();
        DialogSeparator = new JSeparator();
        clIDFormat = new JLabel();
        clEmailLabel = new JLabel();
        clEmailInput = new JTextField();
        clAppearedNameLabel = new JLabel();
        clAppearedName = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        BGPanel.setToolTipText("");
        BGPanel.setLayout(null);

        clTypeCombo.setFont(defont);
        clTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"Individual", "Government", "Private Organisation", "NGO"}));
        BGPanel.add(clTypeCombo);
        clTypeCombo.setBounds(440, 14, 150, 25);

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

        clAgeLabel.setFont(defont);
        clAgeLabel.setText("Age        :");
        BGPanel.add(clAgeLabel);
        clAgeLabel.setBounds(10, 175, 58, 25);

        clAgeCombo.setFont(defont);
        clAgeCombo.setModel(new DefaultComboBoxModel(new String[]{"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "77", "78", "79", "80"}));
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
    } //end constructor

    private void submitbuttonActionPerformed(ActionEvent evt) {
        // check empty
        boolean checkEmpty = false;
        String type = (String) clTypeCombo.getSelectedItem();
        if (type.equals("Individual")) {
            if (clLNameInput.getText().trim().length() == 0 || clFNameInput.getText().trim().length() == 0 || clIDInput.getText().trim().length() == 0 || clICInput.getText().trim().length() == 0 || clAddInput.getText().trim().length() == 0) {
                checkEmpty = true;
            } else
                checkEmpty = false;
            clientCreation();
        }
        dispose();
    }

    private void cancelbuttonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
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
    // End of variables declaration

    private void clientCreation() {
        String name = clLNameInput.getText().trim() + " " + clFNameInput.getText().trim();
        String ageStr = ((String) clAgeCombo.getSelectedItem()).trim();
        int age = Integer.parseInt(ageStr);
        String address = clAddInput.getText().trim();
        String ic = clICInput.getText().trim();
        String clID;
        if (((String) clTypeCombo.getSelectedItem()).equals("Individual")) {
            clID = "I" + clIDInput.getText().trim();
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Government")) {
            clID = "G" + clIDInput.getText().trim();
        } else if (((String) clTypeCombo.getSelectedItem()).equals("Private Organisation")) {
            clID = "P" + clIDInput.getText().trim();
        } else {
            clID = "N" + clIDInput.getText().trim();
        }
        String accStatus = "ACTIVE";
        //model
        RYCOXv2.clientList.add(new IndividualClient(name, age, ic, address, clID, accStatus));
        System.out.println(RYCOXv2.clientList.get(6).getName() + "\t" + RYCOXv2.clientList.size());
        LogFile log = new LogFile(RYCOXv2.user, "has created a new client's account of ID '" + clID + "'.");
        RYCOXv2.logList.add(log);
        //viewer
        ClientPanel.updateAddTable();
    }
}
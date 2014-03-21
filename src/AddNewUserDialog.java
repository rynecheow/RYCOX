import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author RYNE
 */
@SuppressWarnings("serial")
public class AddNewUserDialog extends JDialog {

    // Variables declaration - do not modify
    private JPanel BGPanel;
    private JTextField IDInput;
    private JLabel IDLabel;
    private JButton cancelButton;
    private JButton okButton;
    @SuppressWarnings("rawtypes")
    private JComboBox userTypeCombo;
    private JLabel userTypeLabel;
    private JLabel warningID;
    final String defaultPassword = "rycox789";
    // End of variables declaration

    @SuppressWarnings({"unchecked", "rawtypes"})
    /**
     *
     * @param dialog
     */
    public AddNewUserDialog(JDialog dialog) {
        super(dialog, "RYCOX System - Add a new user", true);
        BGPanel = new JPanel();
        IDLabel = new JLabel();
        IDInput = new JTextField();
        okButton = new JButton();
        cancelButton = new JButton();
        userTypeLabel = new JLabel();
        userTypeCombo = new JComboBox();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        BGPanel.setBackground(new Color(23, 28, 30));
        IDLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        IDLabel.setForeground(new Color(255, 255, 255));
        IDLabel.setText("User ID:");
        IDInput.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        okButton.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        okButton.setText("OK");
        warningID = new JLabel("Invalid.");
        warningID.setForeground(new Color(23, 28, 30));


        okButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String type = ((String) userTypeCombo.getSelectedItem());
                String ID = IDInput.getText().trim();
                if (ID.equals("") || ID == null) {
                    warningID.setText("Invalid.");
                    warningID.setForeground(Color.RED);
                } else {
                    boolean flag = false;
                    for (int y = 0; y < RYCOXv2.userList.size(); y++) {
                        if ((RYCOXv2.userList.get(y).getUserID()).equals(ID)) {
                            warningID.setText("Existed.");
                            warningID.setForeground(Color.RED);
                            flag = true;
                            break;
                        }
                    }
                    if (flag == false) {
                        warningID.setForeground(new Color(23, 28, 30));
                        switch (type) {
                            case "Administrator":
                                RYCOXv2.userList.add(new Administrators(ID, defaultPassword));
                                ManageUsers.updateAdd();
                                JOptionPane.showMessageDialog(null, "User '" + ID + "' is created successfully with default password " + defaultPassword + ".", "RYCOX System - Successful Adding", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                break;
                            case "Front Desk Staff":
                                RYCOXv2.userList.add(new FrontdeskStaffs(ID, defaultPassword));
                                ManageUsers.updateAdd();
                                JOptionPane.showMessageDialog(null, "User '" + ID + "' is created successfully with default password " + defaultPassword + ".", "RYCOX System - Successful Adding", JOptionPane.INFORMATION_MESSAGE);
                                dispose();
                                break;
                        }
                    }
                }
            }
        });
        cancelButton.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int opt = JOptionPane.showConfirmDialog(null, "Are you sure to cancel adding user? Data will not be saved.", "RYCOX System - Confirm cancel", JOptionPane.WARNING_MESSAGE);
                if (opt == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        userTypeLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        userTypeLabel.setForeground(new Color(255, 255, 255));
        userTypeLabel.setText("User type:");

        userTypeCombo.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        userTypeCombo.setModel(new DefaultComboBoxModel(new String[]{"Administrator", "Front Desk Staff"}));

        GroupLayout BGPanelLayout = new GroupLayout(BGPanel);
        BGPanel.setLayout(BGPanelLayout);
        BGPanelLayout.setHorizontalGroup(
                BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, BGPanelLayout.createSequentialGroup().addGap(38, 38, 38).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(userTypeLabel).addComponent(IDLabel)).addGap(18, 18, 18).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(userTypeCombo, 0, 229, Short.MAX_VALUE).addComponent(IDInput)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(warningID).addGap(53, 53, 53)).addGroup(BGPanelLayout.createSequentialGroup().addGap(69, 69, 69).addComponent(okButton, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE).addGap(58, 58, 58)));
        BGPanelLayout.setVerticalGroup(
                BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(28, 28, 28).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(userTypeLabel).addComponent(userTypeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(IDLabel).addComponent(IDInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(warningID)).addGap(18, 18, 18).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addContainerGap(33, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));

        pack();
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
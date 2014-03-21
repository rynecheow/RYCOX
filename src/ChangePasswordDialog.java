import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author RYNE
 */
@SuppressWarnings("serial")
public class ChangePasswordDialog extends JDialog implements ActionListener, FocusListener {

    // Variables declaration - do not modify
    private JPanel BGPanel;
    private JButton clearButton;
    private JButton confirmButton;
    private JLabel confirmPwLabel;
    private JPasswordField confirmpw;
    private JPasswordField newpw;
    private JLabel newpwLabel;
    private JPasswordField oldpw;
    private JLabel oldpwLabel;
    private JLabel warningNotMatchNew;
    private JLabel warningNotMatchOld;
    boolean oldValid = false;
    boolean newValid = false;
    boolean newOldMatch = false;
    // End of variables declaration

    public ChangePasswordDialog(JFrame frame) {
        super(frame, "RYCOX System - Change Password", true);
        setResizable(false);
        setSize(300, 400);
        warningNotMatchOld = new JLabel();
        BGPanel = new JPanel();
        newpwLabel = new JLabel();
        oldpwLabel = new JLabel();
        confirmPwLabel = new JLabel();
        clearButton = new JButton();
        confirmButton = new JButton();
        newpw = new JPasswordField();
        confirmpw = new JPasswordField();
        oldpw = new JPasswordField();
        warningNotMatchOld = new JLabel();
        warningNotMatchNew = new JLabel();

        warningNotMatchOld.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        warningNotMatchOld.setForeground(new Color(255, 0, 0));
        warningNotMatchOld.setText("Password not matched!");

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        BGPanel.setBackground(new Color(23, 28, 31));
        BGPanel.setForeground(new Color(255, 255, 255));

        newpwLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        newpwLabel.setForeground(new Color(255, 255, 255));
        newpwLabel.setText("New Password:");

        oldpwLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        oldpwLabel.setForeground(new Color(255, 255, 255));
        oldpwLabel.setText("Old Password:");

        confirmPwLabel.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        confirmPwLabel.setForeground(new Color(255, 255, 255));
        confirmPwLabel.setText("Re-enter new Password:");
        confirmButton.setEnabled(false);
        confirmButton.addActionListener(this);
        clearButton.addActionListener(this);

        oldpw.addFocusListener(this);
        confirmpw.addFocusListener(this);

        clearButton.setText("Clear");

        confirmButton.setText("Confirm change");

        newpw.setFont(new Font("Tahoma", 0, 12)); // NOI18N


        confirmpw.setFont(new Font("Tahoma", 0, 12)); // NOI18N

        oldpw.setFont(new Font("Tahoma", 0, 12)); // NOI18N

        warningNotMatchOld.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        warningNotMatchOld.setForeground(new Color(255, 0, 0));
        warningNotMatchOld.setText("Password not matched!");

        warningNotMatchNew.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        warningNotMatchNew.setForeground(new Color(255, 0, 0));
        warningNotMatchNew.setText("Password not matched!");

        warningNotMatchOld.setForeground(new Color(23, 28, 31));
        warningNotMatchNew.setForeground(new Color(23, 28, 31));

        GroupLayout BGPanelLayout = new GroupLayout(BGPanel);
        BGPanel.setLayout(BGPanelLayout);
        BGPanelLayout.setHorizontalGroup(
                BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(48, 48, 48).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(oldpwLabel).addComponent(newpwLabel).addComponent(confirmPwLabel).addComponent(confirmButton)).addGap(18, 18, 18).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(52, 52, 52).addComponent(clearButton)).addComponent(newpw, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addGroup(BGPanelLayout.createSequentialGroup().addComponent(confirmpw, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(warningNotMatchNew)).addGroup(BGPanelLayout.createSequentialGroup().addComponent(oldpw, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(warningNotMatchOld))).addContainerGap(29, Short.MAX_VALUE)));
        BGPanelLayout.setVerticalGroup(
                BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addGap(28, 28, 28).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(oldpwLabel).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(oldpw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(warningNotMatchOld))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(BGPanelLayout.createSequentialGroup().addComponent(newpwLabel).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(confirmPwLabel).addComponent(confirmpw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addComponent(warningNotMatchNew))).addComponent(newpw, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(BGPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(confirmButton).addComponent(clearButton)).addContainerGap(41, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(BGPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            checkOld();
            checkNew();
            String changedPw = confirmpw.getText();
            if (newValid == true && oldValid == true) {
                RYCOXv2.userList.get(RYCOXv2.currentUser).changePassword(changedPw);
                JOptionPane.showMessageDialog(this, "Your password has been successfully changed.", "Successfully changed password.", JOptionPane.INFORMATION_MESSAGE);
                RYCOXv2.log = new LogFile(RYCOXv2.user, " has changed password.");
                RYCOXv2.logList.add(RYCOXv2.log);
                RYCOXv2.printLog();
                RYCOXv2.saveUserFile();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please correct the errors.", "Error in validation.", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == clearButton) {
            oldpw.setText("");
            newpw.setText("");
            confirmpw.setText("");
            warningNotMatchOld.setForeground(new Color(23, 28, 31));
            warningNotMatchNew.setForeground(new Color(23, 28, 31));
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == oldpw) {
            warningNotMatchOld.setForeground(new Color(23, 28, 31));
        } else if (e.getSource() == confirmpw) {
            warningNotMatchNew.setForeground(new Color(23, 28, 31));
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == oldpw) {
            checkOld();
            if (oldValid == true) {
                warningNotMatchOld.setText("Password matched.    ");
                warningNotMatchOld.setForeground(Color.GREEN);
            } else {
                warningNotMatchOld.setText("Password not matched!");
                warningNotMatchOld.setForeground(new Color(255, 0, 0));
            }
        } else if (e.getSource() == confirmpw) {
            checkNew();
            if (newValid == true) {
                warningNotMatchNew.setText("Password matched.    ");
                warningNotMatchNew.setForeground(Color.GREEN);
                confirmButton.setEnabled(true);
            } else {
                if (newOldMatch == true) {
                    warningNotMatchNew.setText("Password cannot be same.");
                    warningNotMatchNew.setForeground(new Color(255, 0, 0));
                } else {
                    warningNotMatchNew.setText("Password not matched!");
                    warningNotMatchNew.setForeground(new Color(255, 0, 0));
                }
                confirmButton.setEnabled(false);
            }
        }
    }

    @SuppressWarnings("deprecation")
    void checkOld() {
        String a = oldpw.getText();
        String old = RYCOXv2.userList.get(RYCOXv2.currentUser).getPassword();
        if (a.equals(old)) {
            oldValid = true;
        } else {
            oldValid = false;
        }
    }

    @SuppressWarnings("deprecation")
    void checkNew() {
        String newpwd = newpw.getText();
        String confpw = confirmpw.getText();
        String oldpwd = oldpw.getText();
        if (confpw.equals(newpwd)) {
            if (confpw.equals(oldpwd)) {
                newValid = false;
                newOldMatch = true;
            } else {
                newValid = true;
                newOldMatch = false;
            }
        } else {
            newValid = false;
            newOldMatch = false;
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
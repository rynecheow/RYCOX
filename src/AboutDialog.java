import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialog extends JDialog implements ActionListener {

    // Variables declaration - do not modify
    private JButton closeButton;
    private JLabel logo;
    private JPanel bgPanel;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JSeparator jSeparator1;
    private JTextArea ta1;
    private JTextArea ta2;
    // End of variables declaration

    public AboutDialog(JFrame parent) {
        super(parent, "RYCOX System - About", true);
        jScrollPane1 = new JScrollPane();
        ta1 = new JTextArea();
        bgPanel = new JPanel();
        logo = new JLabel();
        jSeparator1 = new JSeparator();
        closeButton = new JButton();
        jScrollPane2 = new JScrollPane();
        ta2 = new JTextArea();

        ta1.setColumns(20);
        ta1.setRows(5);
        jScrollPane1.setViewportView(ta1);

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        bgPanel.setBackground(new Color(23, 28, 31));

        logo.setIcon(new ImageIcon(getClass().getResource("/resources/logo.png"))); // NOI18N

        closeButton.setText("Close Window");

        ta2.setBackground(new Color(0, 23, 23));
        ta2.setColumns(20);
        ta2.setEditable(false);
        ta2.setFont(new Font("Tahoma", 0, 13)); // NOI18N
        ta2.setForeground(new Color(255, 255, 255));
        ta2.setLineWrap(true);
        ta2.setRows(5);
        ta2.setText("RYCOX System is a Customer Management System and is a part of Customer  Care and Billing System. \n\nVersion 2.9.0.1\nBuild I20120601-1700\n\n(c) Copyright RYCOX contributors Lai Li Hao, Ryne Cheow Yeong Chi and Ng Jia Jiun.\n2012, All rights reserved. \n\nFor more information about the product, please visit\nhttp://www.rycox.org/"); // NOI18N
        ta2.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane2.setViewportView(ta2);

        GroupLayout bgPanelLayout = new GroupLayout(bgPanel);
        bgPanel.setLayout(bgPanelLayout);
        bgPanelLayout.setHorizontalGroup(
                bgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bgPanelLayout.createSequentialGroup()
                                .addGroup(bgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                                                .addContainerGap(20, Short.MAX_VALUE)
                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 666, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(bgPanelLayout.createSequentialGroup()
                                                .addGap(297, 297, 297)
                                                .addComponent(closeButton)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(bgPanelLayout.createSequentialGroup()
                                                .addGap(26, 26, 26)
                                                .addComponent(logo)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2)))
                                .addContainerGap())
        );
        bgPanelLayout.setVerticalGroup(
                bgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(bgPanelLayout.createSequentialGroup()
                                .addGroup(bgPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(bgPanelLayout.createSequentialGroup()
                                                .addGap(33, 33, 33)
                                                .addComponent(logo)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, bgPanelLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
                                                .addGap(48, 48, 48)))
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(closeButton)
                                .addGap(34, 34, 34))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(bgPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}

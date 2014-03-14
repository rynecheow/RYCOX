import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ClientReportDialog extends JDialog {

    // Variables declaration
    private JButton closeW;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane sp;
    private JTextComponent ta;

    // End of variables declaration

    public ClientReportDialog(JFrame parent) {
        super(parent, "RYCOX System- Client Report", true);
        setResizable(false);
        this.setLocation(660, 290);
        setSize(600, 500);
        jPanel1 = new JPanel();
        jPanel2 = new JPanel();
        sp = new JScrollPane();
        ta = new JTextArea();
        closeW = new JButton();

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(null);

        ((JTextArea) ta).setColumns(20);
        ((JTextArea) ta).setRows(5);

        ta.setEditable(false);
        ta.setBackground(Color.WHITE);
        sp.setViewportView(ta);
        for (int i = 0; i < RYCOXv2.clientList.size(); i++) {
            ((JTextArea) ta).append(RYCOXv2.clientList.get(i).stringClient() + "-----------------------\n\n");
        }

        jPanel2.add(sp);
        sp.setBounds(10, 11, 599, 396);

        closeW.setFont(closeW.getFont());
        closeW.setText("Close Window");
        jPanel2.add(closeW);
        closeW.setBounds(258, 413, 120, 30);
        closeW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        );

        pack();
    }
}

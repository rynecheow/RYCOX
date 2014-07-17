import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@SuppressWarnings("serial")
class LogDialog extends JDialog {

    // End of variables declaration

    public LogDialog(JFrame parent) {
        super(parent, "RYCOX System- Log File", true);
        setResizable(false);
        this.setLocation(660, 290);
        setSize(600, 500);
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JScrollPane sp = new JScrollPane();
        JTextComponent ta = new JTextArea();
        JButton closeW = new JButton();

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 100, Short.MAX_VALUE));

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setLayout(null);

        ((JTextArea) ta).setColumns(20);
        //ta.setEditable(false);
        ((JTextArea) ta).setRows(5);

        File f = new File("log.txt");
        readin(f.toString(), ta);
        ta.setEditable(false);
        ta.setBackground(Color.WHITE);
        //ta.setText("bla.");
        sp.setViewportView(ta);


        jPanel2.add(sp);
        sp.setBounds(10, 11, 599, 396);

        closeW.setFont(closeW.getFont());
        closeW.setText("Close Window");
        jPanel2.add(closeW);
        closeW.setBounds(258, 413, 120, 30);
        closeW.addActionListener(e -> dispose());

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE));

        pack();
    }

    static void readin(String fn, JTextComponent pane) {
        try {
            try (FileReader fr = new FileReader(fn)) {
                pane.read(fr, null);
            }
        } catch (IOException e) {
            System.err.println(e.getCause().getMessage());
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

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
class ViewClientDialog extends JDialog {

    // Variables declaration 
    private JPanel BGPanel;
    private JSeparator DialogSeparator;
    private JButton cancelbutton;
    private JTextArea clAddInput;
    private JLabel clAddLabel;
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
    private JLabel clTypeLabel;
    private JScrollPane jScrollPane1;
    private Color bColor = new Color(23, 28, 30);
    private Color fColor = new Color(255, 255, 255);
    private Font defont = new Font("LucidaSansRegular", Font.PLAIN, 12);
    private JLabel clTypetype;
    private String[] data = ClientPanel.editionData;
    private String paramType;
    private JTextField clAgeage;
    // End of variables declaration

    public ViewClientDialog(JFrame parent) {
        super(parent, "RYCOX System - View client", true);
        setResizable(false);
        setLocationRelativeTo(null);

        BGPanel = new JPanel();
        BGPanel.setBackground(bColor);
        BGPanel.setForeground(fColor);
        clTypetype = new JLabel();
        clTypeLabel = new JLabel();
        clTypeLabel.setForeground(fColor);
        clNameInput = new JTextField(data[1]);
        clIDInput = new JTextField(data[0]);
        clIDLabel = new JLabel();
        clIDLabel.setForeground(fColor);
        clNameLabel = new JLabel();
        clNameLabel.setForeground(fColor);
        clAgeLabel = new JLabel();
        clAgeLabel.setForeground(fColor);
        clICLabel = new JLabel();
        clICLabel.setForeground(fColor);
        clICInput = new JTextField(data[6]);
        clAddLabel = new JLabel();
        clAddLabel.setForeground(fColor);
        jScrollPane1 = new JScrollPane();
        clAddInput = new JTextArea(data[3]);
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

        if (data[0].matches("[Ii][0-9]{6}")) {
            paramType = "Individual";
        } else if (data[0].matches("[Gg][0-9]{6}")) {
            paramType = "Government";
        } else if (data[0].matches("[Nn][0-9]{6}")) {
            paramType = "NGO";
        } else if (data[0].matches("[Pp][0-9]{6}")) {
            paramType = "Private Organisation";
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
        clNameInput.setEditable(false);
        clNameInput.setBackground(bColor);
        clNameInput.setForeground(fColor);
        clNameInput.setBorder(null);

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
        clIDLabel.setBounds(10, 43, 62, 25);


        clNameLabel.setFont(defont);
        clNameLabel.setText("Name:");
        BGPanel.add(clNameLabel);
        clNameLabel.setBounds(10, 75, 50, 25);

        clNameInput.addCaretListener(new CaretListener() {

            @Override
            public void caretUpdate(CaretEvent e) {
                if (!"".equals(clNameInput.getText().trim()) && clNameInput.getText().trim() != null) {
                    clAppearedName.setText(clNameInput.getText().trim());
                }
                repaint();
            }
        });

        clAgeLabel.setFont(defont);
        clAgeLabel.setText("Age        :");
        BGPanel.add(clAgeLabel);
        clAgeLabel.setBounds(10, 175, 58, 25);

        clAgeage = new JTextField(data[5]);
        clAgeage.setFont(defont);
        clAgeage.setEditable(false);
        clAgeage.setBackground(bColor);
        clAgeage.setForeground(fColor);
        clAgeage.setBorder(null);
        clAgeage.setBounds(86, 175, 50, 25);
        BGPanel.add(clAgeage);

        clICLabel.setFont(defont);
        clICLabel.setText("Identification Card Number  :");
        BGPanel.add(clICLabel);
        clICLabel.setBounds(10, 205, 175, 25);

        clICInput.setFont(defont);
        BGPanel.add(clICInput);
        clICInput.setEditable(false);
        clICInput.setBackground(bColor);
        clICInput.setForeground(fColor);
        clICInput.setBorder(null);
        clICInput.setBounds(190, 205, 200, 25);

        clAddLabel.setFont(defont);
        clAddLabel.setText("Billing Address	:");
        BGPanel.add(clAddLabel);
        clAddLabel.setBounds(10, 250, 88, 25);

        clAddInput.setColumns(18);
        clAddInput.setRows(3);
        clAddInput.setFont(defont);
        clAddInput.setEditable(false);
        clAddInput.setBackground(bColor);
        clAddInput.setForeground(fColor);
        clAddInput.setBorder(null);
        jScrollPane1.setViewportView(clAddInput);

        BGPanel.add(jScrollPane1);
        jScrollPane1.setBounds(120, 241, 460, 75);

        cancelbutton.setText("Close Window");
        cancelbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                cancelbuttonActionPerformed(evt);
            }
        });
        BGPanel.add(cancelbutton);
        cancelbutton.setBounds(301, 374, 120, 33);
        BGPanel.add(DialogSeparator);
        DialogSeparator.setBounds(0, 358, 590, 10);

        clEmailLabel.setFont(defont);
        clEmailLabel.setText("E-mail Address :");
        BGPanel.add(clEmailLabel);
        clEmailLabel.setBounds(10, 143, 100, 25);

        clEmailInput.setFont(defont);
        BGPanel.add(clEmailInput);
        clEmailInput.setBounds(115, 143, 209, 25);
        clEmailInput.setEditable(false);
        clEmailInput.setBackground(bColor);
        clEmailInput.setForeground(fColor);
        clEmailInput.setBorder(null);
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

        pack();
        ClientPanel.defaultButtonSet();
    }//end constructor

    private void cancelbuttonActionPerformed(ActionEvent evt) {
        dispose();
    }
}
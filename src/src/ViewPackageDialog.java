import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * @author Jia Jiun
 */
@SuppressWarnings({"serial", "rawtypes", "unused", "unchecked"})
class ViewPackageDialog extends JDialog {
    //Variable declaration
    private JLabel label1, label2, label3, label4, label5;
    private JTextField textfield1, textfield2, textfield3, textfield4;
    private JList leftList;
    private JList rightList;
    private JButton button2;
    private Color color, color1, color2;
    private LinkedList<String> leftPrgList;
    private LinkedList<String> selPrgList;
    private AbstractListModel leftModel;
    private AbstractListModel rightModel;
    private String[] leftPrg;
    private String[] selPrg;
    private JScrollPane leftListScroll;
    private JScrollPane rightListScroll;
    //End of variable declaration

    public ViewPackageDialog(JFrame frame) {
        super(frame, "Package Management- View a TV Package", true);

        label1 = new JLabel("Package Code: ");
        label2 = new JLabel("Package Name: ");
        label3 = new JLabel("Charge Price(RM): ");
        label4 = new JLabel("Charge Type: ");
        label5 = new JLabel("TV Programmes included ");


        textfield1 = new JTextField(15);
        textfield2 = new JTextField(15);
        textfield3 = new JTextField(15);
        textfield4 = new JTextField(15);
        textfield3.setEditable(false);
        textfield1.setEditable(false);
        textfield2.setEditable(false);
        textfield4.setEditable(false);
        textfield1.setBackground(color);
        textfield2.setBackground(color);
        textfield3.setBackground(color);
        textfield4.setBackground(color);

        button2 = new JButton("Ok");


        color = new Color(23, 28, 30);
        color1 = new Color(244, 219, 234);
        color2 = new Color(241, 236, 236);


        leftPrgList = new LinkedList<>();
        leftModel = new DefaultListModel();
        rightModel = new DefaultListModel();
        for (int i = 0; i < RYCOXv2.prgList.size(); i++) {
            for (int j = 0; j < RYCOXv2.pckgingList.size(); j++) {
                if (PackagePanel.pkgtemp[0].equalsIgnoreCase(RYCOXv2.pckgingList.get(j).getPkgCode())) {
                    if (RYCOXv2.pckgingList.get(j).getProgCode().equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                        leftPrgList.add(RYCOXv2.pckgingList.get(j).getPkgCode());
                        ((DefaultListModel) leftModel).addElement(RYCOXv2.pckgingList.get(j).getProgCode() + "-" + RYCOXv2.prgList.get(i).getProgTitle());
                    }
                }
            }
        }
        leftPrg = leftPrgList.toArray(new String[leftPrgList.size()]);

        for (int i = 0; i < RYCOXv2.pkgList.size(); i++) {
            boolean check = false;
            for (int j = 0; j < leftPrgList.size(); j++) {
                if (leftPrg[j].equalsIgnoreCase(RYCOXv2.prgList.get(i).getProgCode())) {
                    check = true;
                    break;
                }
            }
            if (check == false) {
                ((DefaultListModel) rightModel).addElement(RYCOXv2.prgList.get(i).getProgCode());
            }
        }

        leftList = new JList(leftModel);
        leftList.setModel(leftModel);
        leftList.setVisibleRowCount(6);
        leftList.setBackground(color2);
        leftList.setFixedCellWidth(450);
        leftList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        rightList = new JList(rightModel);


        leftListScroll = new JScrollPane(leftList);
        leftListScroll.setViewportView(leftList);


        Container container = getContentPane();
        container.setBackground(color);
        SpringLayout spring = new SpringLayout();

        label1.setForeground(color1);
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);


        textfield3.setText(PackagePanel.pkgtemp[0]);
        textfield1.setText(PackagePanel.pkgtemp[1]);
        textfield2.setText(PackagePanel.pkgtemp[2]);
        textfield4.setText(PackagePanel.pkgtemp[3]);
        textfield1.setBackground(color2);
        textfield2.setBackground(color2);
        textfield3.setBackground(color2);
        textfield4.setBackground(color2);


        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                dispose();

            }
        });

        container.setLayout(spring);

        container.add(label1);
        container.add(textfield3);

        spring.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield3, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield3, 15, SpringLayout.NORTH, container);


        container.add(label2);
        container.add(textfield1);


        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 40, SpringLayout.SOUTH, label1);
        spring.putConstraint(SpringLayout.WEST, textfield1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 30, SpringLayout.SOUTH, textfield3);


        container.add(label3);
        container.add(textfield2);

        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 45, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield2, 30, SpringLayout.SOUTH, textfield1);


        container.add(label4);
        container.add(textfield4);

        spring.putConstraint(SpringLayout.WEST, label4, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label4, 40, SpringLayout.SOUTH, label3);
        spring.putConstraint(SpringLayout.WEST, textfield4, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield4, 30, SpringLayout.SOUTH, textfield2);

        container.add(leftListScroll);
        container.add(label5);
        spring.putConstraint(SpringLayout.NORTH, label5, 30, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, label5, 80, SpringLayout.EAST, label4);
        spring.putConstraint(SpringLayout.WEST, leftListScroll, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, leftListScroll, 45, SpringLayout.SOUTH, label4);


        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button2, 122, SpringLayout.EAST, label2);
        spring.putConstraint(SpringLayout.NORTH, button2, 202, SpringLayout.SOUTH, textfield4);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(530, 510);
        setLocation(400, 60);
        setVisible(true);
        PackagePanel.defaultButtonSet();
    }
}
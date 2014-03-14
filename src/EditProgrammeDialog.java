import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EditProgrammeDialog extends JDialog {
    private JLabel label1, label2, label3, label4, label5, label6, label7;
    private JLabel error1, error2, error3, error4, error5, error6;
    private JTextField textfield1, textfield2, textfield3, textfield4;
    private JTextArea textarea1;
    @SuppressWarnings("rawtypes")
    private JComboBox combobox1, combobox2;
    private JButton button1, button2;
    private Color color, color1;
    private JScrollPane scrollpane1;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public EditProgrammeDialog(JFrame frame) {
        super(frame, "Programme Management- Edit an existing TV Programme", true);
        label1 = new JLabel("Programme Code:(F###) ");
        label2 = new JLabel("Programme Title: ");
        error1 = new JLabel("Blank input occured! Please reenter it.");
        label3 = new JLabel("Description: ");
        error2 = new JLabel("Blank input occured! Please reenter it.");
        label4 = new JLabel("Content Origin: ");
        error3 = new JLabel("Blank input occured! Please reenter it.");
        label5 = new JLabel("Viewer Status: ");
        error4 = new JLabel("Please choose one of the viewer status.");
        label6 = new JLabel("Type: ");
        error5 = new JLabel("Please choose one of the programme type.");
        label7 = new JLabel("Notes: ");
        error6 = new JLabel("Blank input occured! Please reenter it.");
        textfield1 = new JTextField(15);
        textfield2 = new JTextField(15);
        textarea1 = new JTextArea(5, 20);
        textfield3 = new JTextField(15);
        combobox1 = new JComboBox();
        combobox2 = new JComboBox();
        textfield4 = new JTextField(15);
        button1 = new JButton("SUBMIT");
        button2 = new JButton("CANCEL");
        color = new Color(23, 28, 30);
        color1 = new Color(244, 219, 234);


        Container container = getContentPane();
        container.setBackground(color);
        textfield1.setEditable(false);
        for (int loop = 0; loop < RYCOXv2.prgList.size(); loop++) {

        }
        SpringLayout spring = new SpringLayout();
        Border textBorder = textfield1.getBorder();
        textarea1.setBorder(textBorder);
        scrollpane1 = new JScrollPane(textarea1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        String[] string1 = {"<-----Please Choose a viewer status----->", "U", "PG13", "18SG"};
        String[] string2 = {"<----Please Choose a programme type---->", "Movie", "Series", "Comedy", "Concert"};
        combobox1.setModel(new DefaultComboBoxModel(string1));
        combobox2.setModel(new DefaultComboBoxModel(string2));
        error1.setForeground(Color.RED);
        error2.setForeground(Color.RED);
        error3.setForeground(Color.RED);
        error4.setForeground(Color.RED);
        error5.setForeground(Color.RED);
        error6.setForeground(Color.RED);
        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);
        error4.setVisible(false);
        error5.setVisible(false);
        error6.setVisible(false);
        label1.setForeground(color1);
        label2.setForeground(color1);
        label3.setForeground(color1);
        label4.setForeground(color1);
        label5.setForeground(color1);
        label6.setForeground(color1);
        label7.setForeground(color1);
        //		button1.addActionListener(new ActionListener(){
        //
        //			public void actionPerformed(ActionEvent e){
        //				boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false;
        //				if((textfield2.getText().trim() == null) || (textfield2.getText().trim().equalsIgnoreCase(""))){
        //					error1.setVisible(true);
        //					flag1 = false;
        //				}else {
        //					flag1 = true;
        //				error1.setVisible(false);
        //				for(int count = 0; count < RYCOXv2.prgList.size(); count++){
        //					if(){
        //						RYCOXv2.prgList.get(count).setProgTitle(textfield2.getText());
        //						break;
        //					}
        //				}
        //				}
        //
        //				if((textarea1.getText().trim() == null) || (textarea1.getText().trim().equalsIgnoreCase(""))){
        //					error2.setVisible(true);
        //					flag2 = false;
        //				}else {
        //					flag2 = true;
        //				error2.setVisible(false);
        //				for(int count = 0; count < RYCOXv2.prgList.size(); count++){
        //					if(){
        //						RYCOXv2.prgList.get(count).setDesc(textarea1.getText());
        //						break;
        //					}
        //				}
        //				}
        //
        //				if((textfield3.getText().trim() == null) || (textfield3.getText().trim().equalsIgnoreCase(""))){
        //					error3.setVisible(true);
        //					flag3 = false;
        //				}else {
        //					flag3 = true;
        //				error3.setVisible(false);
        //				for(int count = 0; count < RYCOXv2.prgList.size(); count++){
        //					if(){
        //						RYCOXv2.prgList.get(count).setContentOrigin(textfield3.getText());
        //						break;
        //					}
        //				}
        //
        //				}
        //
        //				if((combobox1.getSelectedItem()).equals("<-----Please Choose a viewer status----->")){
        //					error4.setVisible(true);
        //					flag4 = false;
        //				}else {
        //					flag4 = true;
        //				error4.setVisible(false);
        //
        //
        //						for(int count2 = 0; count2 < RYCOXv2.prgList.size(); count2++){
        //							if(){
        //								RYCOXv2.prgList.get(count2).setViewerStatus(combobox1.getSelectedItem().toString());
        //								break;
        //							}
        //						}
        //
        //				}
        //
        //				if((combobox2.getSelectedItem()).equals("<----Please Choose a programme type---->")){
        //					error5.setVisible(true);
        //					flag5 = false;
        //				}else {
        //					flag5 = true;
        //				error5.setVisible(false);
        //
        //						for(int count3 = 0; count3 < RYCOXv2.prgList.size(); count3++){
        //							if(){
        //								RYCOXv2.prgList.get(count3).setType(combobox2.getSelectedItem().toString());
        //								break;
        //							}
        //						}
        //
        //
        //				}
        //
        //				if((textfield4.getText().trim() == null) || (textfield4.getText().trim().equalsIgnoreCase(""))){
        //					error6.setVisible(true);
        //					flag6 = false;
        //				}else {
        //					flag6 = true;
        //				error6.setVisible(false);
        //
        //				}
        //				if((flag1 == true) && (flag2 == true) && (flag3 == true) && (flag4 == true) && (flag5 == true) && (flag6 == true)){
        //
        //					JOptionPane.showMessageDialog(null, "You have successfully edited the details of a TV Programme which is "+textfield1.getText()+" !", textfield1.getText()+" edited", JOptionPane.PLAIN_MESSAGE);
        //					LogFile log = new LogFile(RYCOXv2.user,"has edited a TV Programme '"+textfield1.getText()+"'.");
        //					RYCOXv2.logList.add(log);
        //					dispose();
        //
        //				}
        //
        //			}
        //
        //		});

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int closeCf = JOptionPane.showConfirmDialog(null, "Stop editing? All the changes will not be saved.", "Cancel Editing", JOptionPane.WARNING_MESSAGE);
                if (closeCf == JOptionPane.YES_OPTION) {
                    dispose();
                } else {
                }
            }
        });

        container.setLayout(spring);
        container.add(label1);
        container.add(textfield1);
        spring.putConstraint(SpringLayout.WEST, label1, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label1, 15, SpringLayout.NORTH, container);
        spring.putConstraint(SpringLayout.WEST, textfield1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield1, 15, SpringLayout.NORTH, container);
        container.add(label2);
        container.add(textfield2);
        container.add(error1);
        spring.putConstraint(SpringLayout.WEST, label2, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label2, 40, SpringLayout.SOUTH, label1);
        spring.putConstraint(SpringLayout.WEST, textfield2, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield2, 30, SpringLayout.SOUTH, textfield1);
        spring.putConstraint(SpringLayout.NORTH, error1, 5, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.WEST, error1, 123, SpringLayout.EAST, label2);
        container.add(label3);
        container.add(scrollpane1);
        container.add(error2);
        spring.putConstraint(SpringLayout.WEST, label3, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label3, 43, SpringLayout.SOUTH, label2);
        spring.putConstraint(SpringLayout.WEST, scrollpane1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, scrollpane1, 28, SpringLayout.SOUTH, textfield2);
        spring.putConstraint(SpringLayout.NORTH, error2, 5, SpringLayout.SOUTH, scrollpane1);
        spring.putConstraint(SpringLayout.WEST, error2, 152, SpringLayout.EAST, label3);
        container.add(label4);
        container.add(textfield3);
        container.add(error3);
        spring.putConstraint(SpringLayout.WEST, label4, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label4, 115, SpringLayout.SOUTH, label3);
        spring.putConstraint(SpringLayout.WEST, textfield3, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield3, 25, SpringLayout.SOUTH, scrollpane1);
        spring.putConstraint(SpringLayout.NORTH, error3, 5, SpringLayout.SOUTH, textfield3);
        spring.putConstraint(SpringLayout.WEST, error3, 136, SpringLayout.EAST, label4);
        container.add(label5);
        container.add(combobox1);
        container.add(error4);
        spring.putConstraint(SpringLayout.WEST, label5, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label5, 41, SpringLayout.SOUTH, label4);
        spring.putConstraint(SpringLayout.WEST, combobox1, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, combobox1, 29, SpringLayout.SOUTH, textfield3);
        spring.putConstraint(SpringLayout.NORTH, error4, 5, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.WEST, error4, 140, SpringLayout.EAST, label5);
        container.add(label6);
        container.add(combobox2);
        container.add(error5);
        spring.putConstraint(SpringLayout.WEST, label6, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label6, 40, SpringLayout.SOUTH, label5);
        spring.putConstraint(SpringLayout.WEST, combobox2, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, combobox2, 29, SpringLayout.SOUTH, combobox1);
        spring.putConstraint(SpringLayout.NORTH, error5, 5, SpringLayout.SOUTH, combobox2);
        spring.putConstraint(SpringLayout.WEST, error5, 190, SpringLayout.EAST, label6);
        container.add(label7);
        container.add(textfield4);
        container.add(error6);
        spring.putConstraint(SpringLayout.WEST, label7, 20, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, label7, 36, SpringLayout.SOUTH, label6);
        spring.putConstraint(SpringLayout.WEST, textfield4, 80, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, textfield4, 27, SpringLayout.SOUTH, combobox2);
        spring.putConstraint(SpringLayout.NORTH, error6, 5, SpringLayout.SOUTH, textfield4);
        spring.putConstraint(SpringLayout.WEST, error6, 184, SpringLayout.EAST, label7);
        container.add(button1);
        container.add(button2);
        spring.putConstraint(SpringLayout.WEST, button1, 200, SpringLayout.WEST, container);
        spring.putConstraint(SpringLayout.NORTH, button1, 42, SpringLayout.SOUTH, textfield4);
        spring.putConstraint(SpringLayout.WEST, button2, 200, SpringLayout.EAST, label1);
        spring.putConstraint(SpringLayout.NORTH, button2, 42, SpringLayout.SOUTH, textfield4);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(620, 600);
        setLocation(400, 60);
        setVisible(true);
    }


}
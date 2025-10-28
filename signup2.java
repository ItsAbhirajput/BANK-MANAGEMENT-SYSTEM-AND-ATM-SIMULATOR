import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class signup2 extends JFrame implements ActionListener {

    private JButton nextButton;
    private JComboBox<String> religionBox, casteBox, eduBox, incomeBox, occBox;   
    private JTextField panField, aadharField;
    private JRadioButton seniorYes, seniorNo, existYes, existNo;
    private String formNo;

    public signup2(String formNo) {
        this.formNo = formNo;
        setTitle("APPLICATION FORM PAGE 2");
        setSize(800, 850);
        setLocation(400, 15);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        addLabel("PAGE NO 2: ADDITIONAL DETAILS", 150, 50, 500, 48, 28);
        addLabel("RELIGION :", 50, 150, 400, 38, 28);
        addLabel("CATEGORY :", 50, 210, 400, 38, 28);
        addLabel("INCOME :", 50, 270, 400, 38, 28);
        addLabel("EDUCATION :", 50, 330, 400, 38, 28);
        addLabel("OCCUPATION :", 50, 400, 400, 38, 28);
        addLabel("PAN NUMBER :", 50, 460, 400, 38, 28);
        addLabel("AADHAR NO :", 50, 520, 400, 38, 28);
        addLabel("SENIOR CITIZEN :", 50, 580, 400, 38, 28);
        addLabel("EXISTING ACCOUNT :", 50, 640, 400, 38, 28);

        religionBox = addComboBox(new String[]{"HINDU", "MUSLIM", "CHRISTIAN", "SIKH", "OTHER"}, 380, 155);
        casteBox = addComboBox(new String[]{"GENERAL", "OBC", "ST", "SC", "OTHER"}, 380, 215);
        incomeBox = addComboBox(new String[]{"NON INCOME","<1,50,000","<2,50,000","<5,00,000","UP TO 10 LAKHS"}, 380, 275);
        eduBox = addComboBox(new String[]{"NON GRADUATE","GRADUATE","POST-GRADUATE","DOCTORATE","OTHER"}, 380, 335);
        occBox = addComboBox(new String[]{"STUDENT","SELF EMPLOYED","BUSINESS","RETIRED","OTHER"}, 380, 405);

        panField = addTextField(380, 465);
        aadharField = addTextField(380, 525);

        ButtonGroup seniorGroup = new ButtonGroup();
        seniorYes = addRadioButton("YES", 380, 585, seniorGroup);
        seniorNo = addRadioButton("NO", 590, 585, seniorGroup);

        ButtonGroup existGroup = new ButtonGroup();
        existYes = addRadioButton("YES", 380, 645, existGroup);
        existNo = addRadioButton("NO", 590, 645, existGroup);

        nextButton = new JButton("NEXT");
        nextButton.setBounds(330, 720, 100, 30);
        nextButton.setBackground(Color.BLACK);
        nextButton.setForeground(Color.WHITE);
        nextButton.addActionListener(this);
        add(nextButton);
    }

    private void addLabel(String text, int x, int y, int width, int height, int fontSize) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Railway", Font.BOLD, fontSize));
        add(label);
    }

    private JComboBox<String> addComboBox(String[] options, int x, int y) {
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setBounds(x, y, 380, 30);
        comboBox.setBackground(Color.WHITE);
        add(comboBox);
        return comboBox;
    }

    private JTextField addTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 380, 30);
        textField.setBackground(Color.WHITE);
        add(textField);
        return textField;
    }

    private JRadioButton addRadioButton(String text, int x, int y, ButtonGroup group) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, 200, 30);
        radioButton.setBackground(Color.WHITE);
        group.add(radioButton);
        add(radioButton);
        return radioButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String religion = (String) religionBox.getSelectedItem();
        String category = (String) casteBox.getSelectedItem();
        String income = (String) incomeBox.getSelectedItem();
        String education = (String) eduBox.getSelectedItem();
        String occupation = (String) occBox.getSelectedItem();
        String pan = panField.getText().trim();
        String aadhar = aadharField.getText().trim();
        String senior = seniorYes.isSelected() ? "YES" : "NO";
        String existing = existYes.isSelected() ? "YES" : "NO";

        if (pan.isEmpty() || aadhar.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill all required fields.");
            return;
        }

        try {
            conn c = new conn();
            String query = "INSERT INTO signup2 VALUES('"+ formNo + "', '" + religion + "', '" + category + "', '" + income + "', '" + education + "', '" + occupation + "', '" + pan + "', '" + aadhar + "', '" + senior + "', '" + existing + "')";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Details saved successfully.");
            setVisible(false);
            new finalpage(formNo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new signup2("");
    }
}

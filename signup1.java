import java.awt.*;
import javax.swing.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class signup1 extends JFrame implements ActionListener {

    // ---------------------- Fields ----------------------
    Random ran;
    Long random;

    JTextField namet, fnamet, addt, statet, cityt, zipt, emailt;
    JButton next;
    JRadioButton male, female, other, nomar, mared;
    JDateChooser chooser;

    // ---------------------- Constructor ----------------------
    signup1() {
        setTitle("APPLICATION FORM PAGE 1");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Generate random form number
        ran = new Random();
        random = Math.abs(ran.nextLong() % 9000L) + 1000L;

        // ---------------------- Form Header ----------------------
        JLabel formNoLabel = new JLabel("APPLICATION FORM NO: " + random);
        formNoLabel.setFont(new Font("Railway", Font.BOLD, 38));
        formNoLabel.setBounds(100, 20, 600, 40);
        add(formNoLabel);

        JLabel personalDetailLabel = new JLabel("Form No: 1  Personal Details");
        personalDetailLabel.setFont(new Font("Railway", Font.BOLD, 28));
        personalDetailLabel.setBounds(200, 70, 600, 25);
        add(personalDetailLabel);

        // ---------------------- Labels ----------------------
        addLabel("NAME :", 100, 200, 400, 20);
        addLabel("FATHER'S NAME :", 100, 250, 400, 20);
        addLabel("DATE OF BIRTH :", 100, 300, 400, 20);
        addLabel("GENDER :", 100, 350, 400, 20);
        addLabel("EMAIL ADDRESS :", 100, 400, 400, 20);
        addLabel("MARITAL STATUS :", 100, 450, 400, 20);
        addLabel("ADDRESS :", 100, 500, 400, 20);
        addLabel("STATE :", 100, 550, 400, 20);
        addLabel("CITY :", 100, 600, 400, 20);
        addLabel("PIN CODE :", 100, 650, 400, 20);

        // ---------------------- Input Fields ----------------------
        namet = addTextField(350, 200, 400, 30);
        fnamet = addTextField(350, 250, 400, 30);
        chooser = new JDateChooser();
        chooser.setBounds(350, 300, 400, 30);
        chooser.setForeground(new Color(105, 105, 105));
        add(chooser);

        emailt = addTextField(350, 400, 400, 30);
        addt = addTextField(350, 500, 400, 30);
        statet = addTextField(350, 550, 400, 30);
        cityt = addTextField(350, 600, 400, 30);
        zipt = addTextField(350, 650, 400, 30);

        // ---------------------- Gender Radio Buttons ----------------------
        male = new JRadioButton("MALE");
        female = new JRadioButton("FEMALE");
        other = new JRadioButton("OTHER");
        setupRadioButton(male, 350, 350);
        setupRadioButton(female, 500, 350);
        setupRadioButton(other, 650, 350);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        // ---------------------- Marital Status Radio Buttons ----------------------
        nomar = new JRadioButton("YES");
        mared = new JRadioButton("NO");
        setupRadioButton(nomar, 350, 450);
        setupRadioButton(mared, 500, 450);

        ButtonGroup maritalGroup = new ButtonGroup();
        maritalGroup.add(nomar);
        maritalGroup.add(mared);

        // ---------------------- NEXT Button ----------------------
        next = new JButton("NEXT");
        next.setBounds(360, 750, 100, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        // ---------------------- Frame Settings ----------------------
        setSize(800, 850);
        setLocation(400, 15);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // ---------------------- Helper Methods ----------------------
    private void addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Railway", Font.BOLD, 20));
        label.setBounds(x, y, width, height);
        add(label);
    }

    private JTextField addTextField(int x, int y, int width, int height) {
        JTextField field = new JTextField();
        field.setFont(new Font("Railway", Font.BOLD, 14));
        field.setBounds(x, y, width, height);
        add(field);
        return field;
    }

    private void setupRadioButton(JRadioButton btn, int x, int y) {
        btn.setBounds(x, y, 100, 20);
        btn.setBackground(Color.WHITE);
        add(btn);
    }

    // ---------------------- Event Handling ----------------------
    @Override
    public void actionPerformed(ActionEvent ae) {
        String formno = Long.toString(random);
        String name = namet.getText();
        String fname = fnamet.getText();
        String dob = ((JTextField) chooser.getDateEditor().getUiComponent()).getText();

        String gender = null;
        if (male.isSelected()) gender = "male";
        else if (female.isSelected()) gender = "female";
        else if (other.isSelected()) gender = "other";
        else {
            JOptionPane.showMessageDialog(null, "SELECT ONE GENDER");
            return;
        }

        String marital = null;
        if (mared.isSelected()) marital = "YES";
        else if (nomar.isSelected()) marital = "NO";
        else {
            JOptionPane.showMessageDialog(null, "SELECT YES OR NO");
            return;
        }

        String email = emailt.getText();
        String address = addt.getText();
        String state = statet.getText();
        String city = cityt.getText();
        String zipcode = zipt.getText();

        if (name.isEmpty() || fname.isEmpty() || address.isEmpty() || zipcode.isEmpty() ||
                state.isEmpty() || city.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "FILL ALL REQUIRED FIELDS");
            return;
        }

        try {
            conn c = new conn();
            String query = "INSERT INTO signup VALUES('" + formno + "','" + name + "','" + fname + "','" + dob + "','" +
                    gender + "','" + email + "','" + marital + "','" + address + "','" + state + "','" + city + "','" + zipcode + "')";
            c.s.executeUpdate(query);
            setVisible(false);
            new signup2(formno);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // ---------------------- Main Method ----------------------
    public static void main(String[] args) {
        new signup1();
    }
}

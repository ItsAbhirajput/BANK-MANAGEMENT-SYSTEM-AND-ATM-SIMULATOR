import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;
import java.sql.*;

public class fast extends JFrame implements ActionListener {

    private JButton btn10000, btn5000, btn2000, btn1000, btn500, backBtn;
    private String pinno;

    public fast(String pinno) {
        this.pinno = pinno;

        setTitle("Fast Cash Withdrawal");
        setLayout(null);
        setLocation(400, 15);
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        JLabel background = new JLabel(new ImageIcon(i2));
        background.setBounds(0, 0, 900, 900);
        add(background);

        // Header Text
        JLabel label = new JLabel("SELECT WITHDRAWAL AMOUNT");
        label.setBounds(170, 300, 400, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SYSTEM", Font.BOLD, 20));
        background.add(label);

        // Buttons for withdrawal amounts
        btn500 = createButton("RS 500", 170, 415, background);
        btn1000 = createButton("RS 1000", 170, 450, background);
        btn2000 = createButton("RS 2000", 350, 415, background);
        btn5000 = createButton("RS 5000", 350, 450, background);
        btn10000 = createButton("RS 10000", 170, 485, background);

        // Back button
        backBtn = createButton("BACK", 350, 485, background);

        setUndecorated(true);
        setVisible(true);
    }

    /** Helper method to create buttons neatly */
    private JButton createButton(String text, int x, int y, JLabel parent) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 160, 30);
        button.addActionListener(this);
        parent.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // Back button functionality
        if (ae.getSource() == backBtn) {
            setVisible(false);
            new transiction(pinno).setVisible(true);
            return;
        }

        // Get amount from clicked button (after "RS ")
        String amount = ((JButton) ae.getSource()).getText().substring(3).trim();

        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinno + "'");
            int balance = 0;

            // Calculate current balance
            while (rs.next()) {
                if (rs.getString("type").equalsIgnoreCase("deposite")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }

            // Check sufficient balance
            if (balance < Integer.parseInt(amount)) {
                JOptionPane.showMessageDialog(null, "INSUFFICIENT BALANCE");
                return;
            }

            // Withdraw amount
            Date date = new Date();
            String query = "INSERT INTO bank VALUES('" + pinno + "', '" + date + "', 'withdrawal', '" + amount + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "RS " + amount + " DEBITED SUCCESSFULLY!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new fast("");
    }
}

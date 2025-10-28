import java.awt.*; 
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class balance extends JFrame implements ActionListener {

    JButton backBtn;
    String pinn;
    int currentBalance = 0;
    JLabel balanceLabel;

    public balance(String pinn) {
        this.pinn = pinn;
        setLayout(null);
        setLocation(400, 0);
        setSize(900, 900);

        // Calculate balance from database
        try {
            conn c = new conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinn + "'");
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));
                if (type.equals("deposite")) {
                    currentBalance += amount;
                } else {
                    currentBalance -= amount;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Display balance
        balanceLabel = new JLabel("YOUR CURRENT BALANCE IS RS: " + currentBalance);
        balanceLabel.setBounds(170, 300, 600, 20);
        balanceLabel.setForeground(Color.WHITE);
        image.add(balanceLabel);

        // Back button
        backBtn = new JButton("BACK");
        backBtn.setBounds(360, 518, 100, 30);
        backBtn.addActionListener(this);
        image.add(backBtn);

        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            setVisible(false);
            new transiction(pinn).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new balance("");
    }
}

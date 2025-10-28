import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class deposite extends JFrame implements ActionListener {

    private JButton depositeBtn, backBtn;
    private String pinn;
    private JTextField amountField;

    public deposite(String pinn) {
        this.pinn = pinn;

        setTitle("Deposit");
        setLayout(null);
        setLocation(400, 15);
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel background = new JLabel(i3);
        background.setBounds(0, 0, 900, 900);
        add(background);

        // Instruction label
        JLabel label = new JLabel("ENTER YOUR AMOUNT");
        label.setBounds(170, 300, 700, 30);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("SYSTEM", Font.BOLD, 25));
        background.add(label);

        // Amount input field
        amountField = new JTextField();
        amountField.setBounds(175, 360, 330, 30);
        background.add(amountField);

        // Deposit button
        depositeBtn = new JButton("DEPOSIT");
        depositeBtn.setBounds(350, 485, 160, 30);
        depositeBtn.addActionListener(this);
        background.add(depositeBtn);

        // Back button
        backBtn = new JButton("BACK");
        backBtn.setBounds(350, 520, 160, 30);
        backBtn.addActionListener(this);
        background.add(backBtn);

        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == backBtn) {
            setVisible(false);
            new transiction(pinn).setVisible(true);
        } else if (ae.getSource() == depositeBtn) {
            String amount = amountField.getText().trim();

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter an amount");
                return;
            }

            try {
                Date date = new Date();
                conn c = new conn();
                String query = "INSERT INTO bank VALUES('" + pinn + "','" + date + "','deposite','" + amount + "')";
                c.s.executeUpdate(query);
                JOptionPane.showConfirmDialog(null, "RS " + amount + " deposited successfully!");
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new deposite("");
    }
}

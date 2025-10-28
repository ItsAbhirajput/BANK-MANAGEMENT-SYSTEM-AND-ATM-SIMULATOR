import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class pinchange extends JFrame implements ActionListener {

    JButton changeBtn, backBtn;
    String pinn;
    JTextField newPinField, reenterPinField;
    JLabel titleLabel, newPinLabel, reenterPinLabel;

    public pinchange(String pinn) {
        this.pinn = pinn;

        setLayout(null);
        setLocation(400, 15);
        setSize(900, 900);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Title
        titleLabel = new JLabel("CHANGE YOUR PIN");
        titleLabel.setBounds(170, 300, 700, 30);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("SYSTEM", Font.BOLD, 25));
        image.add(titleLabel);

        // New PIN label and field
        newPinLabel = new JLabel("NEW PIN");
        newPinLabel.setBounds(170, 400, 700, 20);
        newPinLabel.setForeground(Color.WHITE);
        newPinLabel.setFont(new Font("SYSTEM", Font.BOLD, 20));
        image.add(newPinLabel);

        newPinField = new JTextField();
        newPinField.setBounds(315, 400, 200, 20);
        image.add(newPinField);

        // Re-enter PIN label and field
        reenterPinLabel = new JLabel("RE-ENTER PIN");
        reenterPinLabel.setBounds(170, 435, 700, 20);
        reenterPinLabel.setForeground(Color.WHITE);
        reenterPinLabel.setFont(new Font("SYSTEM", Font.BOLD, 20));
        image.add(reenterPinLabel);

        reenterPinField = new JTextField();
        reenterPinField.setBounds(315, 435, 200, 20);
        image.add(reenterPinField);

        // Buttons
        changeBtn = new JButton("CHANGE");
        changeBtn.setBounds(350, 485, 160, 30);
        changeBtn.addActionListener(this);
        image.add(changeBtn);

        backBtn = new JButton("BACK");
        backBtn.setBounds(350, 520, 160, 30);
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

        if (ae.getSource() == changeBtn) {
            String pin = newPinField.getText();
            String repin = reenterPinField.getText();

            if (pin.isEmpty() || repin.isEmpty()) {
                JOptionPane.showMessageDialog(null, "PLEASE ENTER PIN");
                return;
            }

            if (!pin.equals(repin)) {
                JOptionPane.showMessageDialog(null, "PIN DOES NOT MATCH");
                return;
            }

            // Update PIN in all tables
            try {
                conn c = new conn();
                String query1 = "UPDATE bank SET pin = '" + repin + "' WHERE pin = '" + pinn + "'";
                String query2 = "UPDATE login SET pinno = '" + repin + "' WHERE pinno = '" + pinn + "'";
                String query3 = "UPDATE final SET pinno = '" + repin + "' WHERE pinno = '" + pinn + "'";

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);
                c.s.executeUpdate(query3);

                JOptionPane.showMessageDialog(null, "YOUR PIN HAS BEEN CHANGED");
                setVisible(false);
                new transiction(repin).setVisible(true);

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new pinchange("");
    }
}

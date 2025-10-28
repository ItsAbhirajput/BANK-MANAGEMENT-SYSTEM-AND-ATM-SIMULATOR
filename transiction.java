import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class transiction extends JFrame implements ActionListener {

    JButton deposite, mini, cash, fast, pin, enq, ext;
    String pinn;

    public transiction(String pinn) {
        this.pinn = pinn;

        setTitle("TRANSITION");
        setLayout(null);
        setLocation(400, 15);
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // Label
        JLabel text = new JLabel("SELECT YOUR TRANSITION");
        text.setBounds(170, 300, 700, 30);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("SYSTEM", Font.BOLD, 25));
        image.add(text);

        // Buttons
        deposite = createButton("DEPOSITE", 160, 415, image);
        mini = createButton("MINI STATEMENT", 160, 450, image);
        fast = createButton("FAST DEPOSITE", 160, 485, image);
        cash = createButton("CASH WITHDRAWAL", 350, 415, image);
        pin = createButton("PIN CHANGE", 350, 450, image);
        enq = createButton("BALANCE ENQUIRY", 350, 485, image);
        ext = createButton("EXIT", 350, 520, image);

        setUndecorated(true);
        setVisible(true);
    }

    // Helper method to create buttons
    private JButton createButton(String text, int x, int y, JLabel parent) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 160, 30);
        button.addActionListener(this);
        parent.add(button);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ext) {
            System.exit(0);
            new main().setVisible(true);
        } else if (ae.getSource() == deposite) {
            setVisible(false);
            new deposite(pinn).setVisible(true);
        } else if (ae.getSource() == cash) {
            setVisible(false);
            new withdrow(pinn).setVisible(true);
        } else if (ae.getSource() == mini) {
            new statement(pinn).setVisible(true);
        } else if (ae.getSource() == fast) {
            setVisible(false);
            new fast(pinn).setVisible(true);
        } else if (ae.getSource() == pin) {
            setVisible(false);
            new pinchange(pinn).setVisible(true);
        } else if (ae.getSource() == enq) {
            setVisible(false);
            new balance(pinn).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new transiction("");
    }
}

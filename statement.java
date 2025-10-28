import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;

public class statement extends JFrame implements ActionListener {

    JButton ext;
    String pinn;
    JLabel titleLabel, balanceLabel, cardLabel, transactionLabel;
    int bal = 0;

    public statement(String pinn) {
        this.pinn = pinn;

        setLayout(null);
        setLocation(60, 15);
        setSize(400, 700);

        // Title
        titleLabel = new JLabel("MINI STATEMENT");
        titleLabel.setBounds(50, 20, 300, 25);
        titleLabel.setFont(new Font("SYSTEM", Font.BOLD, 25));
        add(titleLabel);

        // Card number label
        cardLabel = new JLabel("CARD NO IS = ");
        cardLabel.setBounds(10, 60, 200, 20);
        add(cardLabel);

        // Transactions label
        transactionLabel = new JLabel();
        transactionLabel.setBounds(10, 80, 400, 200);
        add(transactionLabel);

        // Fetch transactions and calculate balance
        try {
            conn c = new conn();

            // Calculate balance
            ResultSet rs = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinn + "'");
            while (rs.next()) {
                String type = rs.getString("type");
                int amount = Integer.parseInt(rs.getString("amount"));

                if (type.equals("deposite")) {
                    bal += amount;
                } else {
                    bal -= amount;
                }
            }

            // Display transactions
            ResultSet rn = c.s.executeQuery("SELECT * FROM bank WHERE pin = '" + pinn + "'");
            while (rn.next()) {
                String date = rn.getString("date");
                String type = rn.getString("type");
                String amount = rn.getString("amount");

                transactionLabel.setText(transactionLabel.getText() + "<html>" + date + "&nbsp;&nbsp;&nbsp;&nbsp;" + type + "&nbsp;&nbsp;&nbsp;&nbsp;" + amount + "<br><br></html>");
            }

            // Mask and display card number
            ResultSet rb = c.s.executeQuery("SELECT * FROM login WHERE pinno = '" + pinn + "'");
            while (rb.next()) {
                String cardNo = rb.getString("cardno");
                cardLabel.setText(cardLabel.getText() + cardNo.substring(0, 4) + "XXXXXXXX" + cardNo.substring(8, 12));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        // Balance label
        balanceLabel = new JLabel("Balance = " + bal);
        balanceLabel.setBounds(10, 600, 700, 20);
        add(balanceLabel);

        // Back button
        ext = new JButton("BACK");
        ext.setBounds(120, 650, 160, 30);
        ext.addActionListener(this);
        add(ext);

        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == ext) {
            setVisible(false);
            new transiction(pinn).setVisible(true);
        }
    }

    public static void main(String[] args) {
        new statement("");
    }
}

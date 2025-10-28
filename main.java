/*
 * ============================================================
 *  Project: ATM Management System (Java Swing + JDBC)
 *  File: main.java
 *  Author: [Your Name]
 *
 *  Description:
 *      This class represents the main login window for the
 *      Automated Teller Machine (ATM) application.
 *
 *  Features:
 *      - Allows users to log in using Card Number and PIN.
 *      - Provides options to Sign In, Sign Up, and Clear fields.
 *      - Verifies credentials from a MySQL database.
 *      - Navigates to the transaction window upon successful login.
 *
 *  Technologies Used:
 *      - Java Swing for GUI
 *      - JDBC for database connectivity
 *      - MySQL as backend database
 *
 *  Note:
 *      Ensure that the 'conn' class is configured properly to
 *      connect to your database and that the 'final' table
 *      contains columns: cardno, pinno, etc.
 * ============================================================
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class main extends JFrame implements ActionListener {

    // GUI Components
    JButton loginb, signup, cancelb;
    JTextField cardtext;
    JPasswordField cardpass;

    // Constructor
    main() {
        // Frame properties
        setTitle("Automated Teller Machine");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // ----------------------- Logo -----------------------
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(i2));
        label.setBounds(60, 20, 100, 100);
        add(label);

        // ---------------------- Title ------------------------
        JLabel text = new JLabel("Welcome To ATM");
        text.setFont(new Font("Oswald", Font.BOLD, 38));
        text.setBounds(200, 50, 400, 40);
        add(text);

        // ------------------ Card Number ----------------------
        JLabel card = new JLabel("Card No :");
        card.setFont(new Font("Oswald", Font.BOLD, 32));
        card.setBounds(100, 170, 170, 30);
        add(card);

        cardtext = new JTextField();
        cardtext.setFont(new Font("Arial", Font.BOLD, 14));
        cardtext.setBounds(300, 171, 260, 30);
        add(cardtext);

        // -------------------- Password -----------------------
        JLabel pass = new JLabel("Password :");
        pass.setFont(new Font("Oswald", Font.BOLD, 32));
        pass.setBounds(100, 230, 400, 30);
        add(pass);

        cardpass = new JPasswordField();
        cardpass.setFont(new Font("Arial", Font.BOLD, 14));
        cardpass.setBounds(300, 231, 260, 30);
        add(cardpass);

        // ---------------------- Buttons ----------------------
        loginb = new JButton("SIGN IN");
        loginb.setBackground(Color.BLACK);
        loginb.setForeground(Color.WHITE);
        loginb.setBounds(140, 310, 200, 30);
        loginb.addActionListener(this);
        add(loginb);

        signup = new JButton("SIGN UP");
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.setBounds(350, 310, 200, 30);
        signup.addActionListener(this);
        add(signup);

        cancelb = new JButton("CLEAR");
        cancelb.setBackground(Color.BLACK);
        cancelb.setForeground(Color.WHITE);
        cancelb.setBounds(153, 360, 380, 30);
        cancelb.addActionListener(this);
        add(cancelb);

        // -------------------- Frame Setup --------------------
        setSize(700, 500);
        setLocation(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    // -------------------- Event Handling --------------------
    @Override
    public void actionPerformed(ActionEvent ae) {

        // ---- Login Button ----
        if (ae.getSource() == loginb) {
            String card = cardtext.getText();
            String pinn = new String(cardpass.getPassword());

            conn c = new conn();
            String query = "SELECT * FROM final WHERE cardno ='" + card + "' AND pinno ='" + pinn + "'";

            try {
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new transiction(pinn).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "INCORRECT CARD NO OR PIN");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // ---- Clear Button ----
        else if (ae.getSource() == cancelb) {
            cardtext.setText("");
            cardpass.setText("");
        }

        // ---- Sign Up Button ----
        else if (ae.getSource() == signup) {
            setVisible(false);
            new signup1().setVisible(true);
        }
    }

    // ---------------------- Main Method ----------------------
    public static void main(String[] args) {
        new main();
    }
}

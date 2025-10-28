import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class withdrow extends JFrame implements ActionListener{

    JButton withdrow, ext;
    String pinn;
    JTextField textField;
    public withdrow(String pinn){
        this.pinn=pinn;
        setTitle("Deposite");
        setLayout(null);
        setLocation(400 ,15);
        setSize(900, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ImageIcon i1 =new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        image.setBounds(0,0,900,900);

        JLabel text = new JLabel("ENTER YOUR AMOUNT");
        text.setBounds(170, 300, 700, 30);
        image.add(text);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("SYSTEM ",Font.BOLD,25));

        textField = new JTextField();
        textField.setBounds(175, 360, 330, 30);
        image.add(textField);
        
        withdrow = new JButton("WITHDROW");
        withdrow.setBounds(350, 485, 160, 30);
        image.add(withdrow);
        withdrow.addActionListener(this);

        ext = new JButton("BACK");
        ext.setBounds(350, 520, 160, 30);
        image.add(ext);
        ext.addActionListener(this);


        setUndecorated(true); 
        setVisible(true); 
        }

        public void actionPerformed (ActionEvent ae){

            if (ae.getSource()==ext) {
                setVisible(false);
                new transiction(pinn).setVisible(true);;
            }else if(ae.getSource()==withdrow){
                Date date = new Date();
                String amount = textField.getText();
                if (amount.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "ENTER AMOUNT");
                    return;
                }
               try {
                conn c = new conn();
                String query = "insert into bank values('"+pinn+"','"+date+"','withdrow','"+amount+"')";
                c.s.executeUpdate(query);
                JOptionPane.showConfirmDialog(null, "RS = "+amount+"WITHDROW SUCCESFULLY"); 
               } catch (Exception e) {
                System.out.println(e);
               }                

            }

        }


    public static void main(String[] args) {
        new withdrow("");
    }
}



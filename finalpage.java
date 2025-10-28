import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;
public class finalpage extends JFrame implements ActionListener{

    JLabel frameno,acctype,creadet,pin,service,pinshow,passhow,creadetd,pind;
    JButton submit,cancel;
    JRadioButton saving,current,fixed,recuring;
    String formno;
    JCheckBox estatement,atmcard,mobile,internet,email,cheque,hearby;


    public finalpage(String formno){

        setVisible(true);
        setLayout(null);
      

        hearby=new JCheckBox("I hearby declared that all the above menton details is correct to the best of my knowledge.");
        hearby.setBounds(60, 650, 700, 20);
        hearby.setFont(new Font("Raleway",Font.BOLD,15));
        hearby.setBackground(Color.WHITE);
        add(hearby);

        submit = new JButton("SUBMIT");
        submit.setBounds(400,700,100,30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        add(submit);
        submit.addActionListener(this);

        cancel = new JButton("CANCEL");
        cancel.setBounds(200,700,100,30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        this.formno=formno; 
        frameno = new JLabel("PAGE NO : 3 ACCOUNT DETAIL");
        frameno.setBounds(170,50,600,30);
        frameno.setFont(new Font ("Raleway",Font.BOLD,30));
        add(frameno);

        acctype = new JLabel("ACCOUNT TYPE :");
        acctype.setBounds(60,130,250,28);
        acctype.setFont(new Font ("Raleway",Font.BOLD,28));
        add(acctype);

        creadet = new JLabel("CREADET CARD NO :");
        creadet.setBounds(60,300,300,28);
        creadet.setFont(new Font ("Raleway",Font.BOLD,28));
        add(creadet);

        creadetd = new JLabel("YOUR CREADET CARD NO :");
        creadetd.setBounds(70,330,300,15);
        creadetd.setFont(new Font ("Raleway",Font.BOLD,15));
        add(creadetd);

        pin = new JLabel("PIN :");
        pin.setBounds(60,370,200,28);
        pin.setFont(new Font ("Raleway",Font.BOLD,28));
        add(pin);
        
        pind= new JLabel("YOUR PASSWORD :");
        pind.setBounds(70,400,200,15);
        pind.setFont(new Font ("Raleway",Font.BOLD,15));
        add(pind);

        passhow = new JLabel("XXXX");
        passhow.setBounds(400,370,200,28);
        passhow.setFont(new Font ("Raleway",Font.BOLD,28));
        add(passhow);

        service = new JLabel("SERVICES :");
        service.setBounds(60,450,200,24);
        service.setFont(new Font ("Raleway",Font.BOLD,24));
        add(service);

        pinshow = new JLabel("XXXX-XXXX-XXXX-"+formno);
        pinshow.setBounds(400,300,600,20);
        pinshow.setFont(new Font ("Raleway",Font.BOLD,20));
        add(pinshow);

        saving=new JRadioButton("SAVING ACCOUNT");
        saving.setBounds(60, 180, 300, 20);
        saving.setFont(new Font("Raleway",Font.BOLD,20));
        saving.setBackground(Color.WHITE);
        add(saving);

        current=new JRadioButton("CURRENT ACCOUNT");
        current.setBounds(400, 180, 300, 20);
        current.setFont(new Font("Raleway",Font.BOLD,20));
        current.setBackground(Color.WHITE);
        add(current);

        fixed=new JRadioButton("FIXED DEPOSITE");
        fixed.setBounds(60, 230, 300, 20);
        fixed.setFont(new Font("Raleway",Font.BOLD,20));
        fixed.setBackground(Color.WHITE);
        add(fixed);

        recuring=new JRadioButton("RECURING DEPOSITE ACCOUNT");
        recuring.setBounds(400, 230, 400, 20);
        recuring.setFont(new Font("Raleway",Font.BOLD,20));
        recuring.setBackground(Color.WHITE);
        add(recuring);

        ButtonGroup Group = new ButtonGroup();
        Group.add(saving);
        Group.add(current);
        Group.add(fixed);
        Group.add(recuring);

        atmcard=new JCheckBox("ATM CARD");
        atmcard.setBounds(100, 500, 200, 25);
        atmcard.setFont(new Font("Raleway",Font.BOLD,20));
        atmcard.setBackground(Color.WHITE);
        add(atmcard);

        mobile=new JCheckBox("MOBILE BANKING");
        mobile.setBounds(100, 550, 200, 25);
        mobile.setFont(new Font("Raleway",Font.BOLD,20));
        mobile.setBackground(Color.WHITE);
        add(mobile);

        cheque=new JCheckBox("CHECK BOOK");
        cheque.setBounds(100, 600, 200, 25);
        cheque.setFont(new Font("Raleway",Font.BOLD,20));
        cheque.setBackground(Color.WHITE);
        add(cheque);

        internet=new JCheckBox("INTERNET BANKING");
        internet.setBounds(400, 500, 300, 25);
        internet.setFont(new Font("Raleway",Font.BOLD,20));
        internet.setBackground(Color.WHITE);
        add(internet);

        email=new JCheckBox("EMAIL & SMS");
        email.setBounds(400, 550, 200, 25);
        email.setFont(new Font("Raleway",Font.BOLD,20));
        email.setBackground(Color.WHITE);
        add(email);

        estatement=new JCheckBox("E-STATEMENT");
        estatement.setBounds(400, 600, 300, 25);
        estatement.setFont(new Font("Raleway",Font.BOLD,20));
        estatement.setBackground(Color.WHITE);
        add(estatement);
        
        getContentPane().setBackground(Color.WHITE);
        setLocation(400,10);
        setSize(800,850);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("FINAL PAGE ");

    }

    public void actionPerformed (ActionEvent ae){
        String facilites="";
        String acctype="";
        Random random = new Random();
        String cardno =""+Math.abs(random.nextLong()%9000000L+5040936000000000L);
        System.out.println(cardno);
        String pinno = ""+Math.abs(random.nextLong()%9000L+1000L);
        System.out.println(pinno);
        
       if(ae.getSource()==submit){

         if (saving.isSelected()) {
            acctype="SAVING ACCOUNT";
        }else if (current.isSelected()) {
            acctype="CURRENT ACCOUNT";
        }else if (fixed.isSelected()) {
            acctype="FIXED ACCOUNT";
        }else if (recuring.isSelected()) {
            acctype="RECURSING ACCOUNT";
        }

        
        if (atmcard.isSelected()) {
            facilites+=" ATM CARD, ";
        }else if (mobile.isSelected()) {
            facilites+=" MOBILE BANKING, ";
        }else if (email.isSelected()) {
            facilites+=" SMS & EMAIL, ";
        }else if (internet.isSelected()) {
            facilites+=" INTERNET BANKING, ";
        } else if (cheque.isSelected()) {
            facilites+=" CHECK BOOK, ";
        }else if (estatement.isSelected()) {
            facilites+=" E-STATEMENT, ";
        }

        
         //connesction 

          try {
            if (acctype=="") {
                 JOptionPane.showMessageDialog(null, "SELECT ACCOUNT TYPE");
            } else if (!hearby.isSelected()){
                JOptionPane.showMessageDialog(null, "PLESE SELECT TERMS AND CODITION.");
            }else if (facilites=="") {
                JOptionPane.showMessageDialog(null, "PLEASE PROVIDE FACILITES");
            }
            conn c= new conn();
            String queiry=("insert into final values('"+formno+"','"+acctype+"','"+facilites+"','"+cardno+"','"+pinno+"')");
            String queiry2=("insert into login values('"+formno+"','"+cardno+"','"+pinno+"')");
            c.s.executeUpdate(queiry);
            c.s.executeUpdate(queiry2);
             JOptionPane.showMessageDialog(null, "YOUR CARD NO IS : "+cardno+"\n YOUR PIN IS : "+pinno);
             setVisible(false);
             new deposite(pinno).setVisible(true);

        } catch (Exception e) {
            System.out.println(e);
           
        }
       
        }else if (ae.getSource()==cancel) { 
           setVisible(false);
           new main().setVisible(true);
        }
      
    }
      
    public static void main(String[] args) {
        new finalpage("");
    }
}

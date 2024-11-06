import java.awt.Font;
import java.awt.Image;
import java.awt.Component.*;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;

public class About extends JFrame implements ActionListener{
    About(){

        setBounds(400,100,500,500);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/windows.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel headerIcon = new JLabel(i3);
        headerIcon.setBounds(70,40,400,80);
        add(headerIcon); 

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/notepad.png"));
        Image i5 = i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel Icon = new JLabel(i6);
        Icon.setBounds(50,180,70,70);
        add(Icon);

        JLabel text = new JLabel("<html> Made By Swapnil Fegade  <br> Version 0.1.1 (OS build Java) <br> All rights reserved</html>");
        text.setBounds(150,100,500,200);
        
        JButton b1 = new JButton("OK");
        b1.setBounds(150,350,120,25);
        b1.addActionListener(this);
        add(text);

        setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        this.setVisible(false);
    }
    public static void main(String[] args) {
        new About();
    }
}

package HostelRentingSystem;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Image;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginPage extends JFrame{

	private final JFrame frame=new JFrame("Login Page");
	private JTextField txtPhone;
	private JPasswordField txtPass;
	private JLabel lblphoneno;
	private JLabel lblpassword;
	private JButton btnSingin;
	private JLabel lbllogo;
	private JPanel panel;
	SqlQuery sqlquery = new SqlQuery();
	String[] queryData = new String[3];
	private Container contentPanel;
	
	public LoginPage(String route,String ownername,String roomno,int price,String ownerPhone,String roomId) {
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(400,200);
	
	panel = new JPanel();
	panel.setForeground(new Color(0, 0, 0));
	panel.setBackground(new Color(255, 255, 255));
	//panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Sign In Form", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel.setBounds(10, 11, 564, 439);
	contentPanel.add(panel);
	panel.setLayout(null);
	
	
	
	lblphoneno = new JLabel("Phone No");
	lblphoneno.setForeground(new Color(0, 0, 0));
	lblphoneno.setFont(new Font("Arial", Font.PLAIN, 18));
	//lblNewLabel.setForeground(new Color(0, 191, 255));
	lblphoneno.setBounds(95, 170, 89, 23);
	panel.add(lblphoneno);
	
	lblpassword = new JLabel("Password");
	lblpassword.setFont(new Font("Arial", Font.PLAIN, 18));
	lblpassword.setForeground(new Color(0, 0, 0));
	lblpassword.setBounds(95, 216, 109, 36);
	panel.add(lblpassword);
	
	txtPhone = new JTextField();
	txtPhone.setBounds(312, 164, 215, 29);
	panel.add(txtPhone);
	txtPhone.setColumns(10);
	
	txtPass = new JPasswordField();
	txtPass.setBounds(312, 222, 215, 29);
	panel.add(txtPass);
	
	frame.setVisible(true);
		
	}
	

}
